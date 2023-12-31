package com.avenqo.testfacility.drivers;

import static com.avenqo.testfacility.config.ConfigurationManager.configuration;
import static java.lang.Boolean.TRUE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

//import io.github.bonigarcia.wdm.WebDriverManager;
//import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum BrowserFactory {

    CHROME {
        @Override
        public WebDriver createDriver() {
        	log.info("---------------- Create Chrome ---------------------------------");
            //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();

            return new ChromeDriver(getOptions());
        }

        @Override
        public ChromeOptions getOptions() {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(START_MAXIMIZED);
            chromeOptions.addArguments("--disable-infobars");
            chromeOptions.addArguments("--disable-notifications");
            if (configuration().headless())
            	chromeOptions.addArguments("--headless=new");
            return chromeOptions;
        }
    }, FIREFOX {
        @Override
        public WebDriver createDriver() {
           // WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();

            return new FirefoxDriver(getOptions());
        }

        @Override
        public FirefoxOptions getOptions() {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments(START_MAXIMIZED);
            if (configuration().headless())
            	firefoxOptions.addArguments("--headless=new");
            
            return firefoxOptions;
        }
    }, EDGE {
        @Override
        public WebDriver createDriver() {
            //WebDriverManager.getInstance(DriverManagerType.EDGE).setup();

            return new EdgeDriver(getOptions());
        }

        @Override
        public EdgeOptions getOptions() {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments(START_MAXIMIZED);
            if (configuration().headless())
            	edgeOptions.addArguments("--headless=new");

            return edgeOptions;
        }
    }, SAFARI {
        @Override
        public WebDriver createDriver() {
           // WebDriverManager.getInstance(DriverManagerType.SAFARI).setup();

        	SafariDriver driver = new SafariDriver(getOptions());
        	//driver.manage().window().maximize();
            return driver;
        }

        @Override
        public SafariOptions getOptions() {
            SafariOptions safariOptions = new SafariOptions();
            safariOptions.setAutomaticInspection(false);

            if (TRUE.equals(configuration().headless()))
                throw new HeadlessNotSupportedException();

            return safariOptions;
        }
    };

    private static final String START_MAXIMIZED = "--start-maximized";

    public abstract WebDriver createDriver();
    public abstract AbstractDriverOptions<?> getOptions();
}