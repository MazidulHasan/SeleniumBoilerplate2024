package com.orangehrmlive.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import com.orangehrmlive.pages.pageDriver.BasePage;


public class MyInfoPage extends BasePage{
    @FindBy(tagName = "h6")
    WebElement pageName;

    @FindBy(xpath = "//label[text()='Date of Birth']/../following-sibling::div//input[@placeholder='yyyy-mm-dd']")
    WebElement dateOfBirth;

    @FindBy(tagName = "//label[text()='Date of Birth']")
    WebElement dateOfBirthTitle;

    @FindBy(xpath="//button[@type='submit']")
     WebElement save;
    
    public MyInfoPage(WebDriver driver){
      super(driver);
      PageFactory.initElements(driver, this);
	}

    public void waitForMyInfoPageLoad(){
        waitForPageLoad();
    }

    public String getMyInfoTitle(){
        return getElementText(pageName);
    }
    public void setValueInDateOfBirth(String value){
        dateOfBirth.sendKeys(value);
        clickBy(save, "save");
    }


    public boolean dateOfBirthValueAvailability(){

        waitFroElementToBeAppeared(dateOfBirthTitle);
        clickBy(dateOfBirth, "dateOfBirthLabel");
        String copiedText = dateFieldValue(dateOfBirth);

        if (copiedText != null && !copiedText.isEmpty()) {
            System.out.println("Birth date value is: " + copiedText);
            return true;
        } else {
            System.out.println("No valid birth date value found.");
        }
        return false;
    }

    public String verifyBeginDateValue(){
        waitFroElementToBeAppeared(dateOfBirthTitle);
        clickBy(dateOfBirth, "dateOfBirthLabel");
        return dateFieldValue(dateOfBirth);
    }

    public String dateFieldValue(WebElement field){
        // Select all text in the input field and copy
        dateOfBirth.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        dateOfBirth.sendKeys(Keys.chord(Keys.CONTROL, "c"));

        // Get the copied text from the clipboard
        java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String copiedText="";
        try {
            copiedText = (String) clipboard.getData(DataFlavor.stringFlavor);
            System.out.println("Copied Text: " + copiedText);
            return copiedText;   
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return copiedText;
    }
}
