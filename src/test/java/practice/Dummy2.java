package practice;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Dummy2
{     //properties
	 public RemoteWebDriver driver;
		
		
	 @FindBy(how=How.NAME,using="identifier")
	 public WebElement nameofUser;
	 
	 //Constructor method
	 public Dummy2(ChromeDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver,this);
		}
		 
	 
	 //opertional method
	 public void userName(String x)
	 {
		 nameofUser.sendKeys(x);
	 }
	 	 
	 //Main method

	public static void main(String[] args) throws Exception
	{
		//open browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
        Dummy2 obj=new Dummy2(driver);
        //Launch site
        driver.get("http://www.gmail.com");
        Thread.sleep(5000);
	    obj.userName("chanikyareddy231");
	    //close site
	    driver.close();

	}

}
