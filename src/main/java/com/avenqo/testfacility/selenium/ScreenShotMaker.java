package com.avenqo.testfacility.selenium;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.avenqo.testfacility.util.FileHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScreenShotMaker {

	private final WebDriver driver;
	private final static String deviceDir = "./screenshots";

	public ScreenShotMaker(WebDriver d) {
		driver = d;
	}

	public void createPageSourceShot(String prefix) throws Throwable {
		log.warn("Appium is capturing the PageSource of the page with prefix '{}'.", prefix);
		
		String destination = prefix + ".xml";
		destination = getScreenshotLocationDir() + File.separator + destination;
		File destFile = new File(destination);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(destFile));
	    writer.write(driver.getPageSource());
	    writer.close();

		log.warn("Original location of PageSource-Shot at '{}'.", destFile.getAbsoluteFile());
	}

	public void createScreenshot(String prefix) throws Throwable {
		log.warn("Appium is capturing the snapshot of the page with prefix '{}'.", prefix);
		File srcFiler = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		log.warn("Original location of screenshot at '{}'.", srcFiler.getAbsoluteFile());

		String destination = prefix + ".png";
		destination = getScreenshotLocationDir() + File.separator + destination;
		File destFile = new File(destination);

		log.warn("Screenshot stored at '{}'.", destFile);
		FileUtils.copyFile(srcFiler, destFile);
	}

	public static String getScreenshotLocationDir() throws Throwable {
		/*if (deviceDir == null) {
			throw new EConfigException(
					"Missing a defined destination where to store screenshots - please define property '"
							+ TestProps.PROP_DEST_SCREENSHOT + "'!");
		}*/
		return FileHelper.checkDestPathAndCreateIfNecessary("", deviceDir)
				.getAbsolutePath();
	}
}

