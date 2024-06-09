package com.orangehrmlive.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrmlive.pages.pageDriver.BasePage;

public class LoginPage extends BasePage {

     @FindBy(name="username")
     WebElement username;
     
     @FindBy(name="password")
     WebElement password;
     
     @FindBy(xpath="//button[@type='submit']")
     WebElement loginBtn;

    public LoginPage(WebDriver driver){
      super(driver);
      PageFactory.initElements(driver, this);
	  }

    public String validateLoginPageTitle(){
		  return driver.getTitle();
	  }

    public DashboardPage login(String un, String pwd){
      inputText(username, un);
      inputText(password, pwd);
      clickBy(loginBtn,"LoginButton");
      return new DashboardPage(driver);
	}
}
