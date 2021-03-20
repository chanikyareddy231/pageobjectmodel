package tests;


import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.ComposePage;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;
import utilities.ExcelUtility;
import utilities.TestUtility;

public class LoginTest 
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
			eu.openSheet("Sheet1");
			int nour=eu.getRowsCount();
			int nouc=eu.getColumnCount();
			eu.createResultColumn(nouc); //at next column(nouc) to last column(nouc-1)
			//Login functional testing with multiple test data in cross browser environment
			for(int i=1;i<nour;i++)
			{
				String bn=eu.getCellValue(i,0);
				String u=eu.getCellValue(i,1);
				String uic=eu.getCellValue(i,2);
				String pw=eu.getCellValue(i,3);
				String pwc=eu.getCellValue(i,4);
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
				//userid testing
				try
				{
					//Enter userid and click next
					hp.uidfill(u); //parametarization
					hp.uidNextClick();
					//userid testing
					if(u.length()==0)  //Blank userid
					{
						if(hp.isBlankuidError())
						{
							eu.setCellValue(i,nouc,"blank user id test passed");
						}
						else
						{
							eu.setCellValue(i,nouc,"blank user id test failed and see"
																		+tu.captureScreenShot());
						}
					}
					else if(u.length()!=0 && uic.equals("invalid"))
					{
						if(hp.isInvaliduidError())
						{
							eu.setCellValue(i,nouc,"invalid user id test passed");
						}
						else
						{
							eu.setCellValue(i,nouc,"invalid user id test failed and see"
															+tu.captureScreenShot());
						}
					}
					else //Uid is valid value
					{
						//password testing
						try
						{	
						//password filling and click next button
						lp.pwdFill(pw); //parametarization
						lp.pwdNextClick();
						if(pw.length()==0)  //Blank pwd
						{
							if(lp.isBlankPwdError()) //blank pwd
							{
								eu.setCellValue(i,nouc,"blank password id test passed");
							}
							else
							{
								eu.setCellValue(i,nouc,"blank password id test failed and see"
																		+tu.captureScreenShot());
							}
						}
						else if(pw.length()!=0 && pwc.equalsIgnoreCase("invalid"))
						{
							if(lp.isInvalidPwdError())
							{
								eu.setCellValue(i,nouc,"invalid password test passed");
							}
							else
							{
								eu.setCellValue(i,nouc,"invalid password test failed"
																+tu.captureScreenShot());
							}
						}
						else
						{
							if(cp.isComposeVisible())
							{
							eu.setCellValue(i,nouc,"Valid password test passed");
							//Do logout
							lop.clickProfilePic();
							lop.clickSignOut();
							}
							else
							{
								eu.setCellValue(i,nouc,"Valid pwd test failed and see "+
																	tu.captureScreenShot());
							}
						}
					}
					catch(Exception ex4)
					{
						eu.setCellValue(i,nouc,ex4.getMessage()+tu.captureScreenShot());
					}
				}
				//close site
				tu.closeSite();
				}
				catch(Exception ex)
				{
					eu.setCellValue(i,nouc,ex.getMessage()+tu.captureScreenShot());
					//close site
					tu.closeSite();
				}	
			}//loop ending
			//save completely and close excel file
			eu.saveAndCloseExcel();	
		}
	    catch(Exception exc) 
		{
	    	eu.saveAndCloseExcel(); //save excel till exception can raise
	    	System.out.println(exc.getMessage());
	    }
	}

}
