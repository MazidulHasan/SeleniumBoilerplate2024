package com.orangehrmlive;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.orangehrmlive.utils.UrlConstants.*;

import org.testng.Assert;
import com.orangehrmlive.base.TestBase;
import com.orangehrmlive.pages.DashboardPage;
import com.orangehrmlive.pages.LoginPage;
import com.orangehrmlive.pages.MyInfoPage;

public class DashboardPageTest extends TestBase{
    LoginPage loginPage;
	DashboardPage dashboardpPage;
	MyInfoPage myInfoPage;

	public DashboardPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage(driver);
		dashboardpPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	//here i can test my info link is working or not like this
	// @Test()
	// public void myInfoLinkTest(){
    //     myInfoPage = dashboardpPage.gotToMyInfoPage();
	// 	Assert.assertEquals(myInfoPage.getMyInfoTitle(), "PIM");
	// }

	@Test()
	public void logoutTest(){
        dashboardpPage.clickProfileDropDown();
		loginPage = dashboardpPage.clickLogoutButton();
		Assert.assertEquals(driver.getCurrentUrl(), BASE_URL + LOGIN_PAGE);
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
