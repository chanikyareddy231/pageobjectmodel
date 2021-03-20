package practice;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Dummy8 
{    
	
	//Properties
	public RemoteWebDriver driver;
	
	@FindBy(how=How.XPATH,using="(//*[text()='India 2nd INNINGS ']/following::table[@class='table batsman'])[1]")
	public WebElement wt;
	
	//constructer method
	public Dummy8(ChromeDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	//opertional method
	public int  getRowCount()
	{
		int value=wt.findElements(By.xpath("child::tbody/descendant::a/ancestor::tr")).size();
		return(value);	
	}
	
	public int  getColumnCount(int rownum)
	{
		int value=driver.findElements(By.xpath("(//*[text()='India 2nd INNINGS ']/following::table[@class='table batsman'][1]/tbody/descendant::a/ancestor::tr)["+rownum+"]/td")).size();
		return(value);	
	}
	public String getCellVlaue(int rownum,int colnum)
	{
		String value=wt.findElement(By.xpath("(child::tbody/descendant::a/ancestor::tr)["+rownum+"]/td["+colnum+"]")).getText();
		return(value);	
	}
	
	
	public List<WebElement> getCellChild(int rownum,int colnum,String tagname)
	{
		List<WebElement> eles=wt.findElements(By.xpath("(//*[text()='India 2nd INNINGS ']/following::table[@class='table batsman'][1]/tbody/descendant::a/ancestor::tr)["+rownum+"]/td["+colnum+"]/"+tagname));
		return(eles);	
	}
	public void copyToExcel()throws Exception
	{
		//Copy web table to excel file sheet
		int rc=getRowCount();
		XSSFWorkbook wb=new XSSFWorkbook();
		Sheet sh=wb.createSheet("MySheet");
		//copy web table body(multiple rows with multiple columns)
		Row r=sh.createRow(0);
		for(int j=1;j<=9;j++)
		{
			String x=wt.findElement(By.xpath("child::thead/tr/th["+j+"]")).getText();
		    r.createCell(j-1).setCellValue(x);
		}
		//copy web table body(multiple rows with multiple columns)
		for(int i=1;i<=rc;i++)
		{
			Row nr=sh.createRow(i);
			int cc=getColumnCount(i);
			for(int j=1;j<=cc;j++)
			{
				String output=getCellVlaue(i,j);
				nr.createCell(j-1).setCellValue(output);
			}
		}
		//auto fill all columns
		for(int j=1;j<=9;j++)
		{
			sh.autoSizeColumn(j-1); //auto fit on all columns
		}
		//save in HDD
		File f=new File("target\\dummy8.xlsx");
		FileOutputStream fo=new FileOutputStream(f);
		wb.write(fo);
		wb.close();
		fo.close();		
	}	
	

	public static void main(String[] args) throws Exception
	{
		//open browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		//create object to page classes
		Dummy8 obj=new Dummy8(driver);
		//launch site
		driver.get("https://www.espncricinfo.com/series/india-in-australia-2020-21-1223867"
				             + "/australia-vs-india-1st-test-1223869/full-scorecard");
		Thread.sleep(5000);
		//call methods	
		obj.copyToExcel();
	    //operate element in a column in a row 
		Thread.sleep(25000);
		driver.switchTo().frame("google_osd_static_frame");
		driver.findElement(By.className("Sign me Up!")).click();;
		driver.switchTo().defaultContent();
		List<WebElement> l=obj.getCellChild(5,9,"i");
		l.get(0).click();
		//close site
		driver.close();
		

	}

}
