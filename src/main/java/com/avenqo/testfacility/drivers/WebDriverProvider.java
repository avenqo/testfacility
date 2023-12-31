package com.avenqo.testfacility.drivers;

import static com.avenqo.testfacility.config.ConfigurationManager.configuration;

import org.openqa.selenium.WebDriver;

public class WebDriverProvider {
	
	private static WebDriver driver;
	private static WebDriverProvider webDriverProvider;
	
	private WebDriverProvider() {
	}

	// --- static ---
	
	public static WebDriver getDriver() {
		if (webDriverProvider == null)
			webDriverProvider = new WebDriverProvider();
		if (driver == null) {
		    driver = new DriverFactory().createInstance(configuration().browser());
		}
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
