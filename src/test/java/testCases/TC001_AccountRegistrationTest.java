package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("Executing TC001_AccountRegistrationTest");
		
		try 
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link...");
		
		hp.clickRegister();
		logger.info("Clicked on Account Registration...");
		
		AccountRegistrationPage regpage= new AccountRegistrationPage(driver);
		
		logger.info("Providing Customer Details...");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmailId(randomeString()+"@gmail.com"); //randomly generates emailId
		regpage.setTelephone(randomeNumber());
		
		String password = randomeAlphaNumeic(); 
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.clickSubscribe();
		regpage.clickPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating Expected Message...");
		String confmsg = regpage.getConfirmationmsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
		     Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
		} 
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("Finished TC001_AccountRegistrationTest");
	}
	
}
