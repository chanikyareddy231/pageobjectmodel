package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComposePage
{
	  //properties for locating Elements
	  private RemoteWebDriver driver;
	  private WebDriverWait wait;
	  
	  @FindBy(how=How.XPATH,using="//*[text()='Compose']")
	  private WebElement compose;
	  
	  @FindBy(how=How.NAME,using="to")
	  private WebElement toaddress;
	  
	  @FindBy(how=How.NAME,using="subjectbox")
	  private WebElement subject;
	  
	  @FindBy(how=How.XPATH,using="//div[@aria-label='Message Body']")
	  private WebElement body;
	  
	  @FindBy(how=How.XPATH,using="//input[@name='Filedata' and @type='file']")
	  private WebElement attach;
	  
	  @FindBy(how=How.XPATH,using="//*(contains(@aria-label,'Attachment:')]")
	  private WebElement uploaded;
	  
	  @FindBy(how=How.XPATH,using="//*[text()='Send']")
	  private WebElement send;
	  
	  @FindBy(how=How.XPATH,using="//*[contains(text(),'Message sent')]")
	  private WebElement outputmsg;
	  
	  //Constructor methods for connecting runner classes(via Association)
	  public ComposePage(RemoteWebDriver driver,WebDriverWait wait)
	  {
		  this.driver=driver;
		  PageFactory.initElements(driver,this);
		  this.wait=wait;
	  }
	  
	  //Operational methods to operate elements
	  public boolean isComposeVisible()
	  {	  
		  try
		  {
		      wait.until(ExpectedConditions.visibilityOf(compose));
		      return(true);
		  }
		  catch(TimeoutException ex)
		  {
			  return(false);
		  }
		  
	  }
	  
	  public void clickCompose()
	  {	  
		  wait.until(ExpectedConditions.elementToBeClickable(compose)).click();
	  }
	  
	  public void fillTo(String x)
	  {	 
		  wait.until(ExpectedConditions.visibilityOf(toaddress)).sendKeys(x);
	  }
	  
	  public void fillSubject(String x)
	  {	  
		  wait.until(ExpectedConditions.visibilityOf(subject)).sendKeys(x);
	  }
	  public void fillBody(String x)
	  {	  
		  wait.until(ExpectedConditions.visibilityOf(body)).sendKeys(x);
	  }
	  
	  public void attachment(String x) 
	  {	  
		  wait.until(ExpectedConditions.invisibilityOf(attach));
		  attach.sendKeys(x);
		  wait.until(ExpectedConditions.visibilityOf(uploaded));
	  }
	  
	  public void clickSend()
	  {	  
		  wait.until(ExpectedConditions.elementToBeClickable(send)).click();
	  }
	  
	  public String getOutPutMessage()
	  {
		  try 
		  {
			  wait.until(ExpectedConditions.visibilityOf(outputmsg));
			  return(outputmsg.getText());
		  }
		  catch (TimeoutException ex)
		  {
			  return("Sorry,Mail did not send");
		  }
	  }
}
