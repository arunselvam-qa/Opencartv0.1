package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTests.BaseClass;
import pageObjects.HomePage;
import pageObjects.RegisterAccountPage;

public class TC001_AccountRegister extends BaseClass 
{
	
	@Test(groups = {"Regression","Master"})
	public void verifyRegister()
	{
		try 
		{
			HomePage home = new HomePage(driver);
			home.clkMyAccount();
			logger.info("System is on Home page");
			home.clkRegister();
			logger.info("System is on Registration page");
			
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
			
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage());
			Assert.assertTrue(false);
		}
		
	}

}
