package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTests.BaseClass;
import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class TC002_Login extends BaseClass {
	
	@Test(groups = {"Sanity","Master"})
	public void verifyLogin()
	{
		try 
		{
			logger.info("*Starting Verify Login Test*");
			
			HomePage hp = new HomePage(getDriver());
			hp.clkMyAccount();
			hp.clkLogin();
			
			LoginPage lp = new LoginPage(getDriver());
			logger.info("Entering Email and Password");
			lp.enterEmail(prop.getProperty("email"));
			lp.enterPassword(prop.getProperty("pass"));
			lp.clkLogin();
			logger.info("Clicked Login");
			
			AccountPage ap = new AccountPage(getDriver());
			Assert.assertTrue(ap.textVerification());
			
		} 
		catch (Exception e) 
		{
			logger.error("Exception thrown");
			Assert.fail();
		}
		
		
		
	}

}
