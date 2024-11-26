package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTests.BaseClass;
import pageObjects.AccountPage;
import pageObjects.AddtoCartPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import pageObjects.RegisterAccountPage;
import pageObjects.SearchPage;

public class TC_SanityE2E extends BaseClass {


	@Test(priority = 0)
	public void HomePageTitleTest()
	{
		logger.info("Verifying Title of the Home Page");
		Assert.assertEquals(getDriver().getTitle(), "Your Store");
	}

	@Test(priority = 1)
	public void CurrencyDropdownTest()
	{
		try 
		{
			logger.info("Verifying the currency Drop down");
			HomePage hp = new HomePage(getDriver());
			Assert.assertTrue(hp.currencyBtn());

		} 
		catch (Exception e) 
		{
			logger.error("Exception thrown");
			logger.error(e.getMessage());
			Assert.fail();
		}
	}

	@Test(priority = 2, enabled = true)
	public void AccountRegisterTest()
	{
		try 
		{
			HomePage home = new HomePage(getDriver());
			home.clkMyAccount();
			logger.info("System is on Home page");
			home.clkRegister();
			logger.info("Navigating to Registration page");

			RegisterAccountPage ra = new RegisterAccountPage(getDriver());
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
			logger.info("Verifying the Title of My Account page");
			Assert.assertEquals(getDriver().getTitle(), "My Account");
		} 
		catch (Exception e) 
		{
			logger.error("Exception thrown");
			logger.error(e.getMessage());
			Assert.fail();
		}
	}

	@Test(priority = 4)
	public void SearchTest()
	{
		try 
		{
			HomePage hp = new HomePage(getDriver());
			logger.info("Entering Product name in search");
			hp.enterSearch("Mac");
			hp.clkSearch();

			SearchPage sp = new SearchPage(getDriver());
			boolean productexists = sp.isProductExists("MacBook Air");
			if(productexists)
			{
				logger.info("Expected Product is presented");
				Assert.assertTrue(true);
			}
			else
			{
				logger.info("No matching product");
				Assert.assertTrue(false);
			}
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage());
			Assert.assertTrue(false);

		}
	}

	@Test(priority = 5, dependsOnMethods = {"SearchTest"})
	public void ProductDetailsPageNavigationTest()
	{
		try 
		{
			SearchPage sp = new SearchPage(getDriver());
			logger.info("Selecting the Product");
			sp.clickOnProduct("MacBook Air");
			
			ProductPage pp = new ProductPage(getDriver());
			
			if(pp.productHeader().equalsIgnoreCase("MacBook Air"))
			{
				logger.info("Product is displayed in detailed view");
				Assert.assertTrue(true);
			}
			else
			{
				logger.info("Incorrect product");
				Assert.assertTrue(false);
			}

		} catch (Exception e) 
		{
			logger.error(e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority = 6, dependsOnMethods = {"ProductDetailsPageNavigationTest"})
	public void AddToCartTest()
	{
		try 
		{
			ProductPage pp = new ProductPage(getDriver());
			logger.info("Adding number of Quantity");
			pp.noOfQuantity("3");
			logger.info("Clicking Add to Cart button");
			pp.addToCart();
			Thread.sleep(2000);
			if(pp.isMessageDisplayed())
			{
				logger.info("Product added to cart");
				Assert.assertTrue(true);
			}
			else
			{
				logger.info("Product not added to cart");
				Assert.assertTrue(false);
			}
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage());
			Assert.assertTrue(false);
		}
		
	}
	
	@Test(priority = 7, dependsOnMethods = {"AddToCartTest"})
	public void ShoppingCartTest()
	{
		try 
		{
			AddtoCartPage ac = new AddtoCartPage(getDriver());
			logger.info("Navigating to Shopping cart screen");
			ac.clkShoppingCart();
			if(ac.getProductName().equalsIgnoreCase("MacBook Air"))
			{
				logger.info("Product is displayed in Cart table");
				ac.clkRemove();
				logger.info("Removing Product from Cart");
				ac.clkContinue();
				
			}
			else
			{
				ac.clkContinueShp();
			}
			logger.info("Clicked continue shopping");
			logger.info("Navigated to Home Page");
			Assert.assertTrue(true);
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage());
			Assert.assertTrue(false);
		}
	}

}
