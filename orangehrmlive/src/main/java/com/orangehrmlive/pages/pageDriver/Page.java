package com.orangehrmlive.pages.pageDriver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrmlive.utils.TestUtil;

public abstract class Page {
    public WebDriver driver;
	public WebDriverWait wait;

	
	public Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
	}

	public abstract void waitForPageLoad();

    public abstract String getPageTitle();

    public abstract String getElementText(WebElement webElement);

	public abstract void clickBy(WebElement webElement, String buttonName);
	
	public abstract void inputText(WebElement webElement, String text);

	public abstract String getCssElementText(WebElement webElement);

	public abstract String splitElementWithSpaceAndGetData(WebElement webElement, int index);
	
	public abstract void alertAccept(WebElement webElement);

    public abstract void waitFroElementToBeAppeared(WebElement webElement);

	public abstract void waitFroElementToBeDisappeared(WebElement webElement);

    public abstract void waitWaitForPageTitle(WebElement webElement);
	
	//Can also be implemented, but for now did not use it
	// public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
	// 	try {
	// 		return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
	// 	} catch (Exception e) {
    //         System.out.println("Exception in getInstance method");
	// 		e.printStackTrace();
	// 		return null;
	// 	}
	// }   
}
