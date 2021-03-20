package practice;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Dummy3 
{  
	//properties
	 public RemoteWebDriver driver;
		
	 @FindBy(how=How.XPATH,using="//*[name()='svg']//*[name()='svg']//*[name()='g']//*[name()='circle']")
	 public WebElement closeCircle;
	 
	 @FindBy(how=How.CLASS_NAME,using="white-text")
	 public WebElement closeinto;
	 
	 @FindBy(how=How.LINK_TEXT,using="Got it!")
	 public WebElement gotit;
	 
	 @FindBy(how=How.NAME,using="nights")
	 public WebElement selectDuration;
	 
	 
	 //Constructor method
	 public Dummy3(ChromeDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver,this);
		}
	 
	 //opertional method
	 public void closeBanner1()
	 { 
		 driver.switchTo().frame(1);
		 closeCircle.click();
		 driver.switchTo().defaultContent();
	 }
	 public void closeBanner2()
	 {
		 closeinto.click();
	 }
	 public void clickGotIt()
	 {
		 gotit.click();
	 }
     public void Duration(String x)
     {
    	 Select s=new Select(selectDuration);
    	 s.selectByVisibleText(x);
     }
     
	public static void main(String[] args) throws Exception
	{
		//open browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
        Dummy3 obj=new Dummy3(driver);
        //Launch site
        driver.get("https://www.mercurytravels.co.in/");
        Thread.sleep(10000);
        obj.closeBanner1();
        Thread.sleep(5000);
        obj.closeBanner2();
        Thread.sleep(5000);
        obj.clickGotIt();
        Thread.sleep(5000);
        obj.Duration("2nights");
	}

}
