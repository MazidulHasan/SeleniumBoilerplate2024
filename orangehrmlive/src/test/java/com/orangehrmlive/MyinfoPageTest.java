package com.orangehrmlive;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrmlive.base.TestBase;
import com.orangehrmlive.pages.DashboardPage;
import com.orangehrmlive.pages.LoginPage;
import com.orangehrmlive.pages.MyInfoPage;

public class MyinfoPageTest  extends TestBase{
    LoginPage loginPage;
	DashboardPage dashboardpPage;
	MyInfoPage myInfoPage;

	public MyinfoPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage(driver);
		dashboardpPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        myInfoPage = dashboardpPage.gotToMyInfoPage();
	}
	
	@Test()
	public void myInfoLinkTest(){
        myInfoPage.waitForMyInfoPageLoad();
		Assert.assertEquals(myInfoPage.getMyInfoTitle(), "PIM");
		Assert.assertTrue(myInfoPage.dateOfBirthValueAvailability(),"No data");
		myInfoPage.setValueInDateOfBirth("1996-07-14");
		driver.navigate().refresh();
		Assert.assertEquals(myInfoPage.verifyBeginDateValue(),"1996-07-14","Updated Data Matched");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}