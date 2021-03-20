package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage 
{
     //properties for locating elements
	 private RemoteWebDriver driver;
	 private WebDriverWait wait;
	 
	 @FindBy(how=How.NAME,using="identifier")
	 private WebElement uid;
	 
	 @FindBy(how=How.XPATH,using="//*[text()='Next']/parent::button")
	 private WebElement uidnext;
	 
	 @FindBy(how=How.XPATH,using="//div[contains(text(),'Enter an email')]")
	 private WebElement blankuiderr;
	 
	 @FindBy(how=How.XPATH,using="//div[text()=\"Couldn't find your Google Account\" or "
			                        + "contains(text(),'Enter a valid email')]")
	 private WebElement invaliduiderr;
	 
	 //Constructor methods for connecting runner classes(Via Association in OOPs)
	 public HomePage(RemoteWebDriver driver, WebDriverWait wait)
	 {
		 this.driver=driver;
		 PageFactory.initElements(driver,this);
		 this.wait=wait;
	 }
	 
	 //Operational methods to operate and observe elements
	 public void uidfill(String x)
	 {
		 wait.until(ExpectedConditions.visibilityOf(uid)).sendKeys(x);
	 }
	 public void uidNextClick()
	 {
		 wait.until(ExpectedConditions.elementToBeClickable(uidnext)).click();
	 }
	 public boolean isBlankuidError()
	 {
		 try
		 {
			 wait.until(ExpectedConditions.visibilityOf(blankuiderr));
			 return(true);
		 }
		 catch(Exception ex)
		 {
			 return(false);
		 }
	 }
	 
	 public boolean isInvaliduidError()
	 {
		 try
		 {
			 wait.until(ExpectedConditions.visibilityOf(invaliduiderr));
			 return(true);
		 }
		 catch(Exception ex)
		 {
			 return(false);
		 }
	 }
	 
}
