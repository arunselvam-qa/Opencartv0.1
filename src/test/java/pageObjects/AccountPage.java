package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

	public AccountPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	WebElement myAccText;
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement clkLogOut;
	@FindBy(xpath = "//a[normalize-space()='Qafox.com']")
	WebElement clkHome;
	
	public boolean textVerification()
	{
		try 
		{
			return myAccText.isDisplayed();
		} 
		catch (Exception e) 
		{
			return false;
		}
	}
	
	public void clkLogout()
	{
		clkLogOut.click();
	}
	
	public void homeLogo()
	{
		clkHome.click();
	}

}
