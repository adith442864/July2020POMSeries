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
//	public static boolean highlightElement;
	public ElementUtil elementUtil;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	public WebDriver init_driver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		optionsManager = new OptionsManager(prop);
//		highlightElement = prop.getProperty("highlight").equalsIgnoreCase("yes") ? true : false;
		
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} 
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} 
		else if(browserName.equals("Edge")) {
			WebDriverManager.edgedriver().setup();
			//driver = new EdgeDriver();
			tldriver.set(new EdgeDriver());
		}
		else if(browserName.equals("Safari")) {
			WebDriverManager.getInstance(SafariDriver.class);
			//driver = new SafariDriver();
			tldriver.set(new SafariDriver());
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
//		driver.manage.deleteAllCookies();
//		driver..manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		getDriver().get(prop.getProperty("url"));
		
		
		return getDriver();
		
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
	 * This method is used to initialize the properties from config.properties file on the basis of given env variable..
	 * @return prop
	 */
	public Properties init_prop() {
		
		prop = new Properties();
		String path = null;
		String env = null;
		
		try {

			env = System.getProperty("env");
			System.out.println("Environment value is :-->" +env);
			if(env == null) {
				path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties";
			}
			else {
				switch (env) {
				case "qa":
					path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\hubspot\\config\\qa.config.properties";
					
				case "dev":
					path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\hubspot\\config\\dev.config.properties";
					
				case "stg":
					path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\hubspot\\config\\stg.config.properties";
				
				default:
					System.out.println("Please pass the correct path and env value...---->" + env);
					break;
				}
				
			}
			
			FileInputStream ip = new FileInputStream(path);
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
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
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
