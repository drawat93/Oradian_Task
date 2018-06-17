package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseTests.BaseClass;

public class TestProfileAndLock extends BaseClass
{	
	
	@BeforeClass
	public void verifyLogin()
	{
		loginObject.enterUsername(read.getUsername());
		loginObject.enterPassword(read.getPassword());
		loginObject.clickOnsignIn();
		Assert.assertTrue(homeObject.isLoggedIn());
	}
	
	@Test(priority=1)
	public void checkAccountLock()
	{
		testInfo.log(Status.INFO, "User is clicking on lock screen");
		homeObject.clickOnLockAccount();
		Assert.assertTrue(homeObject.isLocked());
		testInfo.log(Status.PASS, "Screen is locked");
		
		testInfo.log(Status.INFO, "User is entering password to unlock");
		homeObject.enterUnlockPassword(read.getPassword());
		
		testInfo.log(Status.INFO, "User is clicking on unlock button");
		homeObject.clickOnUnlockButton();
		Assert.assertFalse(homeObject.isUnLocked());
		
	}
		
	@Test(priority=2)
	public void verifyProfilePage()
	{
		testInfo.log(Status.INFO, "User is clicking on profile");
		homeObject.clickOnprofileNameLink();
		testInfo.log(Status.INFO, "User is verifying username and email on profile page");
		
		Assert.assertEquals(homeObject.getUserName(), read.getUsername());
		testInfo.log(Status.INFO, "Username is verified ");

		Assert.assertTrue(homeObject.getEmail().equals(read.getEmail()));
		testInfo.log(Status.INFO, "Email is verified ");		
	}
	

	

}
