package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txt_email;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txt_pass;
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btn_login;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement msg_error;

	public void enterEmail(String email)
	{
		txt_email.sendKeys(email);
	}
	public void enterPassword(String pass)
	{
		txt_pass.sendKeys(pass);
	}
	public void clkLogin()
	{
		btn_login.click();
	}
	public boolean errMsg()
	{
		return msg_error.isDisplayed();
	}

}
