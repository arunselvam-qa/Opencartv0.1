package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTests.BaseClass;
import pageObjects.HomePage;

public class TC004_ProductSearch extends BaseClass {
	
	@Test
	public void searchItem()
	{
		try 
		{
			HomePage hp = new HomePage(driver);
			logger.info("Entering Product name in search");
			hp.enterSearch("Mac");
			hp.clkSearch();
			
			
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage());
			Assert.assertTrue(false);
			
		}
	}
	
}
