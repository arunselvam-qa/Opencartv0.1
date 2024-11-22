package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {

	//Constructor
	public HomePage(WebDriver driver) 
	{
		super(driver);
	}

	//Locators
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement btn_myaccount;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	WebElement btn_register;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement btn_login;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement txt_search;
	
	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	WebElement btn_search;

	//Actions
	public void clkMyAccount()
	{
		btn_myaccount.click();
	}
	public void clkRegister()
	{
		btn_register.click();
	}
	public void clkLogin()
	{
		btn_login.click();
	}
	public void enterSearch(String product_name)
	{
		txt_search.sendKeys(product_name);
	}
	public void clkSearch()
	{
		btn_search.click();
	}
}
