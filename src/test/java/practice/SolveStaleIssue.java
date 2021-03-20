package practice;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;



public class SolveStaleIssue 
{
	//properties
	public ChromeDriver driver;
	
	@FindBy(how=How.NAME,using="identifier")
    public WebElement e;
	
	//constructor method
	public SolveStaleIssue(ChromeDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	//Main method to run
	public static void main(String[] args) throws Exception
	{
		//open browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
        SolveStaleIssue obj=new SolveStaleIssue(driver);
        //Launch site
        driver.get("http://www.gmail.com");
        Thread.sleep(5000);
        //operate element and go to next page
        obj.e.sendKeys("chanikyareddy231",Keys.ENTER);
        Thread.sleep(5000);
        //Back to previous page
        driver.navigate().back();
        Thread.sleep(5000);
       //operate element and again go to next page
        obj.e.clear();
        obj.e.sendKeys("chanikyareddy23",Keys.ENTER);
        Thread.sleep(5000);
        //close site 
        driver.close();

	}

	
}
