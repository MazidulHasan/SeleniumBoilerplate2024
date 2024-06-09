package com.orangehrmlive.pages.pageDriver;

import java.util.List;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.*;
import com.orangehrmlive.utils.TestUtil;

public class BasePage extends Page {
    public static Logger log = LogManager.getLogger("logger");
    
    public BasePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageTitle() {
        log.info("Inside getPageTitle() method");
        return driver.getTitle();
    }

    @Override
    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
        wait.until(webDriver ->((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @Override
    public String getElementText(WebElement webElement) {
        log.info("Inside getElementText() method");
        waitFroElementToBeAppeared(webElement);
        return webElement.getText();
    }

    @Override
    public void alertAccept(WebElement webElement){
    }

    public List<WebElement> getAllSelecteOptions(WebElement selectElement){
        waitFroElementToBeAppeared(selectElement);
        Select select = new Select(selectElement);
        List<WebElement> selectedOptionList = select.getAllSelectedOptions();
        return  selectedOptionList;
    }

    public Select getSelector(WebElement selecElement){
        waitFroElementToBeAppeared(selecElement);
        Select selector = new Select(selecElement);
        return selector;
    }

    @Override
    public void waitFroElementToBeAppeared(WebElement webElement) {
        log.info("Inside waitFroElementToBeAppeared() method");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (Exception e) {
            System.out.println("Exception while waiting for web element to be clickable: " + e.getMessage());
        }
    }

    @Override
    public void waitWaitForPageTitle(WebElement webElement) {
        log.info("Inside waitWaitForPageTitle() method");
        waitFroElementToBeAppeared(webElement);
    }

    @Override
    public void clickBy(WebElement webElement, String buttonName) {
        log.info("Inside clickBy() method for button " + buttonName);
        System.out.println("Bug101:"+buttonName);
        waitFroElementToBeAppeared(webElement);
        webElement.click();
    }

    @Override
    public void inputText(WebElement webElement, String text) {
        log.info("Inside inputText() method and the text is " + text);
        waitFroElementToBeAppeared(webElement);
        webElement.sendKeys(text);
    }

    @Override
    public void waitFroElementToBeDisappeared(WebElement webElement) {
        log.info("Inside waitFroElementToBeDisappeared() method");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
            wait.until(ExpectedConditions.invisibilityOf(webElement));
        } catch (Exception e) {
            System.out.println("Exception while waiting for web element to be clickable: " + e.getMessage());
        }
    }

    @Override
    public String getCssElementText(WebElement webElement) {
        log.info("Inside getCssElementText() method");
        waitFroElementToBeAppeared(webElement);
        return webElement.getText();
    }

    @Override
    public String splitElementWithSpaceAndGetData(WebElement webElement, int index) {
        log.info("Inside splitElementWithSpaceAndGetData() method");
        String fullText = getCssElementText(webElement);
        String[] splitedText = fullText.split("\\s+");
        return splitedText[index];
    }
}
