package practice;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Dummy1 
{
	 //properties
	 public RemoteWebDriver driver;
	
	
	 @FindBy(how=How.CLASS_NAME,using="VfPpkd-RLmnJb")
	 public WebElement user;
	 
	 //Constructor method
	 public Dummy1(ChromeDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver,this);
		}
		 
	 
	 //opertional method
	 public void userIdClick()
	 {
		 user.click();
	 }
	 	 
	 //Main method
	public static void main(String[] args) throws Exception
	{
		//open browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
        Dummy1 obj=new Dummy1(driver);
        //Launch site
        driver.get("http://www.gmail.com");
        Thread.sleep(5000);
        obj.userIdClick();

	}

}
