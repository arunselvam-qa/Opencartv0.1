package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTests.BaseClass;
import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.DataProviderClass;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviderClass.class)
	public void verifyLoginDDT(String email, String pass, String expected)
	{
		try 
		{
			logger.info("*Starting Data getDriver()n Login Test*");

			HomePage hp = new HomePage(getDriver());
			hp.clkMyAccount();
			hp.clkLogin();

			LoginPage lp = new LoginPage(getDriver());
			logger.info("Entering Email and Password");
			lp.enterEmail(email);
			lp.enterPassword(pass);
			lp.clkLogin();
			logger.info("Clicked Login");

			AccountPage ap = new AccountPage(getDriver());
			boolean text = ap.textVerification();

			if(expected.equalsIgnoreCase("Valid"))
			{
				if(text==true)
				{
					ap.clkLogout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			if(expected.equalsIgnoreCase("InValid"))
			{
				if(text==true)
				{
					ap.clkLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}


		} 
		catch (Exception e) 
		{
			logger.error("Exception thrown");
			Assert.fail();
		}
	}

}
