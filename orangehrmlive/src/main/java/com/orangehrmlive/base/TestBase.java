package com.orangehrmlive.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.time.Duration;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.orangehrmlive.pages.pageDriver.BasePage;
import com.orangehrmlive.pages.pageDriver.Page;
import com.orangehrmlive.utils.TestUtil;
import com.orangehrmlive.utils.WebEventListener;

import static com.orangehrmlive.utils.UrlConstants.*;


public class TestBase {

    public static Page page;
    public static WebDriver driver;
	public static Properties prop;
	public static WebEventListener eventListener;

    private static final String BROWSER_CONFIG_FILE = "/src/main/java/com/orangehrmlive/config/config.properties";

    public TestBase(){
        try{
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ BROWSER_CONFIG_FILE);
            prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void initialization(){

        String browserName = prop.getProperty("browser").toLowerCase();
        String headlessMode = prop.getProperty("headless").toLowerCase();

        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--ignore-certificate-errors");
                chromeOptions.addArguments("--no-sandbox");// The sandbox is a security feature in Chrome that runs the browser processes in a restricted environment, isolating them from the rest of the system. 
                chromeOptions.addArguments("--disable-dev-shm-usage");///dev/shm is a shared memory file system in Unix-like operating systems. By default, Chrome uses /dev/shm to create a temporary file system for managing shared memory

                if (headlessMode.equals("true")) {
                    chromeOptions.addArguments("--headless");
                }

                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headlessMode.equals("true")) {
                    firefoxOptions.addArguments("-headless");
                }

                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver(edgeOptions);
                break;

            case "safari":
                driver = new SafariDriver();
                break;

            default:
                throw new InvalidArgumentException("No browser was selected in the configuration file!");
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
        page = new BasePage(driver);
		
		driver.get(BASE_URL + LOGIN_PAGE);
    }
}
