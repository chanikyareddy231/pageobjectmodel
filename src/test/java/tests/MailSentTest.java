package tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.ComposePage;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;
import utilities.ExcelUtility;
import utilities.TestUtility;

public class MailSentTest 
{
	public static void main(String[] args) throws Exception
	{
		//Create object To Utility classes(have common methods)
		TestUtility tu=new TestUtility();
		String tdfpath=System.getProperty("user.dir")+"\\src\\test\\resources\\book1.xlsx";
		ExcelUtility eu=new ExcelUtility(tdfpath);
		try
		{
			//Access Excel file Sheet for test data
			eu.openSheet("Sheet2");
			int nour=eu.getRowsCount();
			int nouc=eu.getColumnCount();
			eu.createResultColumn(nouc); //at next column(nouc) to last column(nouc-1)
			//Login functional testing with multiple test data in cross browser environment
			for(int i=1;i<nour;i++)
			{
				String bn=eu.getCellValue(i,0);
				String u=eu.getCellValue(i,1);
				String p=eu.getCellValue(i,2);
				String t=eu.getCellValue(i,3);
				String s=eu.getCellValue(i,4);
				String b=eu.getCellValue(i,5);
				String fp=eu.getCellValue(i,6);
				//Open browser by creating driver object
				RemoteWebDriver driver=tu.openBrowser(bn);
				//Define wit object by using driver object
				WebDriverWait wait=tu.defineWait(driver);
				HomePage hp=new HomePage(driver,wait);
				LoginPage lp=new LoginPage(driver,wait);
				ComposePage cp=new ComposePage(driver,wait);
				LogoutPage lop=new LogoutPage(driver,wait);
				//	Launch site by using url in properties file
				String  url=tu.getPropertyValue("url");
				tu.lanuchSite(url);
				//Do login for valid data
				hp.uidfill(u);
				hp.uidNextClick();
				lp.pwdFill(p);
				lp.pwdNextClick();
				//Go to compose operation
				try
				{
					cp.clickCompose();
					cp.fillTo(t);
					cp.fillSubject(s);
					cp.fillBody(b);
					cp.attachment(fp);
					cp.clickSend();
					//compose testing 
					String msg=cp.getOutPutMessage();
					if(!msg.contains("Sorry"))  //not Contains Sorry
					{
						eu.setCellValue(i,nouc,"Compose test passed");
					}
					else
					{
						eu.setCellValue(i,nouc,"Compose test failed and see "+tu.captureScreenShot());
					}
					//Do logout
					lop.clickProfilePic();
					lop.clickSignOut();
					//close site
					tu.closeSite();
				}
				catch(Exception ex)
				{
					eu.setCellValue(i,nouc,ex.getMessage()+tu.captureScreenShot());
				}
					
			}//loop ending
			//save completly and close excel file
			eu.saveAndCloseExcel();
		}
		catch(Exception exc) 
		{
			eu.saveAndCloseExcel();  //Save excel till exception can raise to stop Corruption
			System.out.println(exc.getMessage());
		}
	}

}
