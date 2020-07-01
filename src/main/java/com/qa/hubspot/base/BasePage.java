package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This method is used to initialize the driver on basis of browser.
 * @author Administrator
 * @return driver
 */

public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public ElementUtil elementUtil;
	
	public WebDriver init_driver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} 
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} 
		else if(browserName.equals("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(browserName.equals("Safari")) {
			WebDriverManager.getInstance(SafariDriver.class);
			driver = new SafariDriver();
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
		
		return driver;
		
	}
	
	

//	private void init_remoteWebDriver(String browserName) {
//		if (browserName.equalsIgnoreCase("chrome")) {
//			DesiredCapabilities cap = DesiredCapabilities.chrome();
//			cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
//			try {
//				driver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		else if (browserName.equalsIgnoreCase("firefox")) {
//			DesiredCapabilities cap = DesiredCapabilities.firefox();
//			cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getFirefoxOptions());
//			try {
//				driver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
	
	
	/**
	 * This method is used to initialize the properties from config.properties file...
	 * @return prop
	 */
	public Properties init_prop() {
		
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream( System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
		
	}
	
	
	/**
	 * this method will take the screenshot
	 */
	public String getScreenshot() {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;

	}

}
