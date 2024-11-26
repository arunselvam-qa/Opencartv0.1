package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTests.BaseClass;
import pageObjects.HomePage;
import pageObjects.SearchPage;

public class TC004_ProductSearch extends BaseClass {
	
	@Test
	public void searchItem()
	{
		try 
		{
			logger.info("*Starting Verify Search Test*");
			
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
}
