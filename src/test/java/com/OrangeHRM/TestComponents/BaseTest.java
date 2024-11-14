package com.OrangeHRM.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.OrangeHRM.PageObjects.Login;

public class BaseTest {
	
	private static WebDriver driver;
	public Login log;
	
	public static String getGlobalProperties (String Key) throws IOException {
		String propDir = System.getProperty("user.dir") + "\\src\\main\\java\\com\\OrangeHRM\\Resources\\GlobalData.properties";
		Properties prop = new Properties();
		try {
			FileInputStream file = new FileInputStream(propDir);
			prop.load(file);
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find global properties directory location");
		}
		return prop.getProperty(Key);
	}
	
	public static WebDriver InitiateDriver () throws IOException {
		if (driver == null) {
			String automationBrowser = getGlobalProperties("browser");
			if (automationBrowser.equalsIgnoreCase("Chrome")) {
				driver = new ChromeDriver();
			} else if (automationBrowser.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		}
		return driver;
	}
	
	public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
	
	public void LaunchLoginPage () throws IOException {
		driver.get(getGlobalProperties("url"));
		log = new Login(driver);
	}
	
	public String TakeScreenShot(String scenario, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File screenshotSource = ts.getScreenshotAs(OutputType.FILE);
		File screenshotDestination = new File(System.getProperty("user.dir") + "\\ScreenCapture\\" + scenario + ".png");
		FileUtils.copyFile(screenshotSource,screenshotDestination);
		return System.getProperty("user.dir") + "\\ScreenCapture\\" + scenario + ".png";
	}
	
	public WebElement WaitToAppear (WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		WebElement visible = wait.until(ExpectedConditions.visibilityOf(element));
		return visible;
	}
	
	public WebElement WaitForElementClickable(WebElement Element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(Element));
	}
}
