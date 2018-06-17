package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageObject {
	
	WebDriver driver;
	WebDriverWait wait;
	Logger logger;
	
	@FindBy(xpath="//*[text()=' Lock Screen']")
	WebElement lockScreenButton;
	
	@FindBy(xpath="//div[@id='lock-container']")
	WebElement lockScreenTab;
	
	@FindBy(id="unlock-password")
	WebElement unlockPassword;
	
	@FindBy(xpath="//div[@id='lock-container']//button[@class='btn btn-primary']")
	WebElement unlockButton;
	
	@FindBy(xpath="//a[@class='btn dropdown-toggle']")
	WebElement profileDropDown;
	
	@FindBy(xpath="//*[text()=' Profile']")
	WebElement profileNameLink;
	
	@FindBy(xpath="//*[@class='js-qa-username']")
	WebElement userNameOnProfile;
	
	@FindBy(xpath="//*[@href='mailto:deepanshutest@test.com']")
	WebElement emailOnProfile;
	
	@FindBy(xpath="//*[text()=' Logout']")
	WebElement logOut;
	

	public HomePageObject(WebDriver driver) 
	{	
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 10);
		logger=Logger.getLogger(this.getClass());
	}
	

	public void clickOnprofileNameLink()
	{
		logger.info("User is clicking on drop down username link");
		profileDropDown.click();
		logger.info("User is clicking on profile");
		profileNameLink.click();
	}
	
	public void clickOnlogOut()
	{	
		logger.info("User is clicking on drop down username link");
		profileDropDown.click();
		logger.info("User is logging out ");
		logOut.click();
	}
	
	public String getUserName()
	{
		logger.info("Retreiving username from profile page");
		return userNameOnProfile.getText();
	}
	
	public String getEmail()
	{
		logger.info("Retreiving email from profile page");
		return emailOnProfile.getText();
	}
	
	public void clickOnLockAccount() 
	{

		logger.info("User is clicking on drop down username link");
		profileDropDown.click();
		logger.info("User is clicking on lock screen");
		lockScreenButton.click();
	}
	
	//Check if user is locked
	public boolean isLocked() 
	{
		logger.info("Checking if screen is locked");
		wait.until(ExpectedConditions.visibilityOf(lockScreenTab));
		return lockScreenTab.isDisplayed();
	}
	
	//Check if user is unlocked
	public boolean isUnLocked() 
	{
		logger.info("Checking if screen is unlocked");
		wait.until(ExpectedConditions.invisibilityOf(lockScreenTab));
		return unlockPassword.isDisplayed(); 
	}
	
	//Check if user is logged in into Instafin, true in case of logged in otherwise false
	public boolean isLoggedIn()
	{
		logger.info("Checking if user is logged in");
		if(driver.getTitle().toLowerCase().equals("instafin")) {
			return false;}
		else {
			return true;}
	}
	
	
	public void enterUnlockPassword(String str)
	{			
		unlockPassword.clear();
		logger.info("User is entering password on unlock screen");
		unlockPassword.sendKeys(str);
	}

	public void clickOnUnlockButton()
	{	
		logger.info("User is clicking on unlock button");
		unlockButton.click();
	}
	
}
