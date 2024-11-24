package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterAccountPage extends BasePage {

	public RegisterAccountPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txt_firstname;
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txt_lastname;
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txt_email;
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txt_telephone;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txt_password;
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txt_cnfpass;
	@FindBy(xpath = "//label[normalize-space()='Yes']")
	WebElement radio_News_yes;
	@FindBy(xpath = "//label[normalize-space()='No']")
	WebElement radio_News_no;
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chk_privacy;
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btn_continue;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msg_acntCreated;
	@FindBy(xpath = "//a[normalize-space()='Continue']")
	WebElement btn_acntContinue;
	
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement btn_logout;

	public void setFirstname(String fname)
	{
		txt_firstname.sendKeys(fname);
	}
	public void setLastname(String lname)
	{
		txt_lastname.sendKeys(lname);
	}
	public void setEmail(String email)
	{
		txt_email.sendKeys(email);
	}
	public void setTelephone(String tele)
	{
		txt_telephone.sendKeys(tele);
	}
	public void setPass(String pass)
	{
		txt_password.sendKeys(pass);
	}
	public void setCnfPass(String cpass)
	{
		txt_cnfpass.sendKeys(cpass);
	}
	public void selectNewsletterYes()
	{
		radio_News_yes.click();
	}
	public void selectNewsletterNo()
	{
		radio_News_no.click();
	}
	public void checkPrivacyPol()
	{
		chk_privacy.click();
	}
	public void clickContinue()
	{
		btn_continue.click();
	}

	public String getAccountRegisterMsg()
	{
		try 
		{
			return(msg_acntCreated.getText());
		} 
		catch (Exception e) 
		{
			return(e.getMessage());
		}
	}
	public void finalContinue()
	{
		btn_acntContinue.click();
	}
	public void clkLogout()
	{
		btn_logout.click();
	}

}
