package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTests.BaseClass;
import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterAccountPage;

public class TC_SanityE2E extends BaseClass {
	
	
	@Test(priority = 0)
	public void HomePageTitleTest()
	{
		logger.info("Verifying Title of the Home Page");
		Assert.assertEquals(driver.getTitle(), "Your Store");
	}
	
	@Test(priority = 1)
	public void CurrencyDropdownTest()
	{
		try 
		{
			logger.info("Verifying the currency Dropdown");
			HomePage hp = new HomePage(driver);
			Assert.assertTrue(hp.currencyBtn());
			
		} 
		catch (Exception e) 
		{
			logger.error("Exception thrown");
			logger.error(e.getMessage());
			Assert.fail();
		}
	}
	
	@Test(priority = 2)
	public void AccountRegisterTest()
	{
		try 
		{
			HomePage home = new HomePage(driver);
			home.clkMyAccount();
			logger.info("System is on Home page");
			home.clkRegister();
			logger.info("Navigating to Registration page");
			
			RegisterAccountPage ra = new RegisterAccountPage(driver);
			logger.info("Entering details..");
			ra.setFirstname(randomAlphabeticString(6).toUpperCase());
			ra.setLastname(randomAlphabeticString(5).toUpperCase());
			ra.setEmail(randomAlphabeticString(6)+"@mailinator.com");
			ra.setTelephone(randomNumericString(10));
			
			String pass = randomAphNumString(8);
			ra.setPass(pass);
			ra.setCnfPass(pass);
			
			ra.checkPrivacyPol();
			ra.clickContinue();
			String msg = ra.getAccountRegisterMsg();
			Assert.assertEquals(msg, "Your Account Has Been Created!");
			logger.info("Account created");
			ra.clkLogout();
			logger.info("Logged out");
			
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage());
			Assert.assertTrue(false);
		}
		
	}
	
	@Test (priority = 3)
	public void LoginTest()
	{
		try 
		{
			logger.info("Starting Login Test");
			HomePage hp = new HomePage(driver);
			hp.clkMyAccount();
			hp.clkLogin();
			
			LoginPage lp = new LoginPage(driver);
			logger.info("Entering Email and Password");
			lp.enterEmail(prop.getProperty("email"));
			lp.enterPassword(prop.getProperty("pass"));
			lp.clkLogin();
			logger.info("Clicked Login");
			
			AccountPage ap = new AccountPage(driver);
			Assert.assertTrue(ap.textVerification());
			logger.info("Verifying the Title of My Account page");
			Assert.assertEquals(driver.getTitle(), "My Account");
		} 
		catch (Exception e) 
		{
			logger.error("Exception thrown");
			logger.error(e.getMessage());
			Assert.fail();
		}
	}
	
	

}
