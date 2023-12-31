package com.avenqo.testfacility.glue;

import static com.avenqo.testfacility.config.ConfigurationManager.configuration;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import com.avenqo.testfacility.drivers.WebDriverProvider;
import com.avenqo.testfacility.props.PropertyProvider;
import com.avenqo.testfacility.selenium.ScreenShotMaker;
import com.avenqo.testfacility.webdriver.WebDriverProviderOld;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hook {

	private final String TEST_NAME;
	private final String URL_AUT;
	
	public Hook() {
		TEST_NAME = configuration().testName();
		URL_AUT = new PropertyProvider().getAutUrlAsString();
	}
	
	/**
	 * Runs before the first step of each scenario.
	 */
	@Before
	public void createDriver() {
		
		
		final WebDriver driver = WebDriverProvider.getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
		
		String url = configuration().url();
		log.info("url: [{}]", url);
		driver.get(URL_AUT);
	}

	/**
	 * Runs after the last step of each scenario, even when the step result is
	 * failed, undefined, pending, or skipped.
	 * 
	 * @param scenario
	 */
	@After
	public void deleteDriver(Scenario scenario) {
		log.info("");
		WebDriverProvider.quitDriver();
	}
	
	
	@After
	public void afterScenario(Scenario scenario) throws Throwable {
		log.info("Scenario '{}' [Feature '{}']", scenario.getName(), scenario.getId());

		String featureName = scenario.getId();
		if (scenario.isFailed()) {
			log.error("Creating screenshot for feature '{}' and scenario '{}'.", featureName, scenario.getName());
			
			try {
				ScreenShotMaker ssm = new ScreenShotMaker(WebDriverProvider.getDriver());
				ssm.createPageSourceShot(TEST_NAME);
				ssm.createScreenshot(TEST_NAME);

				/*
				 * File srcFile = appiumDriver.getScreenshotAs(OutputType.FILE);
				 * 
				 * byte[] screenshot = ((TakesScreenshot)
				 * driver).getScreenshotAs(OutputType.BYTES); String testName =
				 * scenario.getName(); scenario.embed(screenshot, "image/png");
				 * scenario.write(testName);
				 */
			} catch (Exception ex) {
				log.error("Screenshot creation failed.", ex);
				throw ex;
			}

		} else {
			log.info("------------------------------------------------------------------------");
			log.info("Finished: Feature '{}' and scenario '{}'.", featureName, scenario.getName());
			log.info("========================================================================");
		}
	}
}
