package practice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Dummy4 
{
     //properties
	 public ChromeDriver driver;
	 
	 //<div> tag drop down
	 @FindBy(how=How.XPATH,using="//a[@id='selection']/following-sibling::div[2]")
	 WebElement gender;
	 
	 //constructor method
	 public Dummy4(ChromeDriver driver)
	 {
		 this.driver=driver;
		 PageFactory.initElements(driver,this);
	 }
	 
	 
	 //operational method
	 public void SelectItemByIndex(int x)
	 {
		 gender.click(); //open drop down
		 List<WebElement> items=gender.findElements(By.xpath("child::div[2]/div"));
		 if(items.size()<x)
		 {
			 System.out.println("Wrong index");
			 System.exit(0);
		 }
		 else
		 {
			 items.get(x-1).click(); //1st item index is 0 in collections
		 }  
	 }
	 
	 public void selectByName(String x) throws Exception
	 {
		 gender.click(); //open drop down
		 List<WebElement> items=gender.findElements(By.xpath("child::div[2]/div"));
         int flag=0;
         for(WebElement item:items)
         {
        	 if(item.getText().equalsIgnoreCase(x))
        	 {
        		 item.click();
        		 flag=1;
        		 break;
        	 }
         }
         if(flag==0)
         {
        	 System.out.println("Wrong Item name");
        	 System.exit(0); //stop execution forcibly
         }
	 }
	 
	 //main method to run
	 public static void main(String[] args) throws Exception
	 {
		 //open browser
		 WebDriverManager.chromedriver().setup();
		 ChromeDriver driver=new ChromeDriver();
		 driver.manage().window().maximize();
		 Dummy4 obj=new Dummy4(driver);
		 //Launch site
		 driver.get("https://semantic-ui.com/modules/dropdown.html");
		 Thread.sleep(5000);
		 obj.SelectItemByIndex(1);
		 Thread.sleep(5000);
		 obj.selectByName("female");
		 
	 }
			 
	 
}
