package testCases;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass 
{

	@Test(groups={"Sanity","Master"})
	public void verify_login()
	{
		logger.info("Executing TC002_LoginTest");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link...");
		
		hp.clickLogin();
		logger.info("Clicked on Login Link...");
		
		LoginPage lp=new LoginPage(driver);
		logger.info("Providing EMailID...");
		lp.setEmailId(p.getProperty("email"));
		
		logger.info("Providing Password...");
		lp.setPassword(p.getProperty("password"));
		
		logger.info("Clicking on Login Button");
		lp.clickLogin();
		
		MyAccountPage macc=new MyAccountPage(driver);
		logger.info("Checking My Account Page Exists or Not");
		
		boolean targetPage = macc.isMyAccountPageExists();
		
		Assert.assertTrue(targetPage);
		}
		
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("Finished TC002_LoginTest");
				
	}
	
}
