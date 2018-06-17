package tests;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseTests.BaseClass;

public class TestLogin extends BaseClass
{
	
	@DataProvider(name="credentials")
    public String[][] getCredentials() throws InvalidFormatException, IOException
	{
		return util.readExcelData("credentials");
    }
	
	@Test(priority=1,dataProvider="credentials")
	public void verifyLogin(String username,String password) //verify valid and invalid login
	{
		testInfo.log(Status.INFO, "User is entering username: "+username);
		loginObject.enterUsername(username);
		
		loginObject.enterPassword(password);
		testInfo.log(Status.INFO, "User is entering password: "+password);
		
		loginObject.clickOnsignIn();
		testInfo.log(Status.INFO, "User is clicking on login");
		
		//in case of invalid login
		if(loginObject.getLoginErrorMessage().equals("Wrong username or password"))
		{
			testInfo.log(Status.INFO, "User is getting invalid login error message");
			Assert.assertEquals(loginObject.getLoginErrorMessage(),"Wrong username or password");		
		}
		else  // in case of successful login
		{
			testInfo.log(Status.INFO, "User is logged in into Instaifin");
			Assert.assertTrue(homeObject.isLoggedIn());
		}
	}
	
	@Test(priority=2)
	public void verifyLogout() //verify logout
	{
		testInfo.log(Status.INFO, "User is clicking on logout");
		homeObject.clickOnlogOut();
		Assert.assertFalse(homeObject.isLoggedIn());
	}

}
