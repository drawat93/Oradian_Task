package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject {

	WebDriver driver;
	Logger logger;
	public LoginPageObject(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		logger=Logger.getLogger(this.getClass());
	}
	
	
	@FindBy(id="us1")
	WebElement username;
	
	@FindBy(id="pw1")
	WebElement password;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement signIn;
	
	@FindBy(xpath="//*[@class='fm_error_msg']")
	WebElement loginErrorMessage;
	
	
	public void clickOnsignIn() 
	{
		logger.info("User is clicking on Sign In");
		signIn.click();
	}
	
	public void enterUsername(String str) 
	{
		username.clear();
		logger.info("User is entering username: "+str);
		username.sendKeys(str);
	}
	
	public void enterPassword(String str) 
	{
		password.clear();
		logger.info("User is entering password "+str);
		password.sendKeys(str);
	}
	
	//Return login error message if there's any otherwise return "No error message" in case of successful login
	public String getLoginErrorMessage() 
	{
		logger.info("Checking if login error message is coming");
		if(loginErrorMessage.isDisplayed())
			return loginErrorMessage.getText();
		else
			return "no error message";
	}
	
}
