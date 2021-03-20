package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StaleIssue
{
	public static void main(String[] args) throws Exception
	{
		//open browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.gmail.com");
        Thread.sleep(5000);
        WebElement e=driver.findElement(By.name("identifier"));
        //operate element and go to next page
        e.sendKeys("chanikyareddy1",Keys.ENTER);
        Thread.sleep(5000);
        //Back to previous page
        driver.navigate().back();
      //operate element and again go to next page
        e.sendKeys("chanikyareddy231",Keys.ENTER);
        Thread.sleep(5000);
        //close site 
        driver.close();
	}

}
