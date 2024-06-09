package com.orangehrmlive.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrmlive.pages.pageDriver.BasePage;

public class DashboardPage extends BasePage{
    @FindBy(tagName = "h6")
    WebElement pageName;

    @FindBy(linkText = "My Info")
    WebElement myInfoLink;

    @FindBy(css = "[class*='oxd-userdropdown-icon']")
    WebElement userProfileDropdown;

    @FindBy(css = "a[href^='/web/index.php/auth/logout']")
    WebElement logoutButton;
    
    public DashboardPage(WebDriver driver){
      super(driver);
      PageFactory.initElements(driver, this);
	}

    public String getDashboardTitle(){
        return getElementText(pageName);
    }

    public MyInfoPage gotToMyInfoPage(){
        clickBy(myInfoLink, "myInfoLink");
        return new MyInfoPage(driver);
    }

    public void clickProfileDropDown(){
        clickBy(userProfileDropdown, "userProfileDropdown");
    }

    public LoginPage clickLogoutButton(){
        clickBy(logoutButton, "logoutButton");
        return new LoginPage(driver);
    }
}
