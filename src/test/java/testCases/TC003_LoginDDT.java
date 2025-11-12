package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven")// getting data provider from different class
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		logger.info("Executing TC003_LoginDDT");
		
		try 
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link...");
		
		hp.clickLogin();
		logger.info("Clicked on Login Link...");
		
		LoginPage lp=new LoginPage(driver);
		logger.info("Providing EMailID...");
		lp.setEmailId(email);
		
		logger.info("Providing Password...");
		lp.setPassword(pwd);
		
		logger.info("Clicking on Login Button");
		lp.clickLogin();
		
		MyAccountPage macc=new MyAccountPage(driver);
		logger.info("Checking My Account Page Exists or Not");
		
		boolean targetPage = macc.isMyAccountPageExists();
		
		/* data id valid - login success - test pass - logout
		 *               - login failed - test fail
		 * 
		 * data is invalid - login success - test fail - logout
		 *                 - login failed - test pass
		 */
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else 
			{
				Assert.assertTrue(false);
			}
			
		}
		

		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else 
			{
				Assert.assertTrue(true);
			}
			
		}
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("Execution Finished TC003_LoginDDT");
			
	}

}
