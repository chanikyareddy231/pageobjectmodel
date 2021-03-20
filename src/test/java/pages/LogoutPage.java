package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage
{
	 //properties for locating Elements
	  private RemoteWebDriver driver;
	  private WebDriverWait wait;
	  
	  @FindBy(how=How.XPATH,using="//a[starts-with(@aria-label,'Google Account')]/img")
      public WebElement profilepic;
	  
	  @FindBy(how=How.LINK_TEXT,using="Sign out")
      public WebElement signout;
	  
	  @FindBy(how=How.XPATH,using="//div[text()='Use another account']")
      public WebElement relogin;
	  
	  
	  //constructor method for connecting with runner classes(via Association)
	  public LogoutPage(RemoteWebDriver driver,WebDriverWait wait)
	  {
		  this.driver=driver;
		  PageFactory.initElements(driver,this);
		  this.wait=wait;
	  }
	 
	  
	  //Operational methods
	  public void clickProfilePic()
	  {
		  wait.until(ExpectedConditions.elementToBeClickable(profilepic)).click();
	  }
	  
	  public void clickSignOut()
	  {
		  wait.until(ExpectedConditions.elementToBeClickable(signout)).click();
		  wait.until(ExpectedConditions.visibilityOf(relogin));
	  }	  
}
