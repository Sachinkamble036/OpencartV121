package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//label[normalize-space()='Yes']")
	WebElement btnSubscribe;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPrivacyPolicy;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	public void setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	
	public void setEmailId(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String tphone)
	{
		txtTelephone.sendKeys(tphone);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String cnfpwd)
	{
		txtConfirmPassword.sendKeys(cnfpwd);
	}
	
	public void clickSubscribe()
	{
		Actions act=new Actions(driver);
		act.moveToElement(btnSubscribe).click().perform();
		
		//btnSubscribe.click();
	}
	
	public void clickPrivacyPolicy()
	{
		//Actions act=new Actions(driver);
		//act.moveToElement(chkPrivacyPolicy).click().perform();
	
		chkPrivacyPolicy.click();
	}
	
	public void clickContinue()
	{
		//sol-1
		//btnContinue.click();
		
		//sol-2
		btnContinue.submit();
		
		//sol-3
		//Actions act=new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
		
		//sol-4
		//JavascriptExecutor js=(JavascriptExecutor) driver;
		//js.executeScript("arguments[0].click();",btnContinue );
		
		//sol-5
		//btnContinue.sendKeys(Keys.RETURN);
		
		//sol-6
		//WebDriverWait Mywait=new WebDriverWait(driver, Duration.ofSeconds(10));
		//Mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
				
				
	}
	
	public String getConfirmationmsg()
	{
		try {
			return(msgConfirmation.getText());
		    }
		
		catch(Exception e)
		{  
			return(e.getMessage());
		}
	
		
	}
	
}
