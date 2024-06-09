package com.orangehrmlive;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrmlive.base.TestBase;
import com.orangehrmlive.pages.DashboardPage;
import com.orangehrmlive.pages.LoginPage;

public class LoginPageTest extends TestBase{
    LoginPage loginPage;
	DashboardPage dashboardpPage;
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage(driver);
	}
	
	@Test(priority=1)
	public void loginTest(){
		dashboardpPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(dashboardpPage.getDashboardTitle(), "Dashboard");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
