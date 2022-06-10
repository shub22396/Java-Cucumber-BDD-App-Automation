package com.lambdatest.stepdefs;

import com.lambdatest.RunWebDriverCucumberTests;
import com.lambdatest.pageobjects.HomePage;
import com.lambdatest.util.Utility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AppAutomation {
     public AppiumDriver driver;

    private HomePage homePage;

    @Before
    public void setUp() {
        driver = (AppiumDriver) RunWebDriverCucumberTests.getManagedWebDriver().getWebDriver();

        //homePage = new HomePage(driver);
    }

    @Given("^run my first app automation$")
    public void run_my_first_app_automation() throws Throwable {
        MobileElement color = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/color"));
        color.click();
        Thread.sleep(2000);
        MobileElement text = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/Text");
        //Changes the text to "Proverbial"
        text.click();

        //toast will be visible
        MobileElement toast = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/toast");
        toast.click();

        //notification will be visible
        MobileElement notification = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/notification");
        notification.click();
        Thread.sleep(2000);

        //Opens the geolocation page
        MobileElement geo = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/geoLocation");
        geo.click();
        Thread.sleep(5000);

        //takes back to home page
        MobileElement home = (MobileElement) driver.findElementByAccessibilityId("Home");
        home.click();

        //Takes to speed test page
        MobileElement speedtest = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/speedTest");
        speedtest.click();
        Thread.sleep(5000);

        MobileElement Home = (MobileElement) driver.findElementByAccessibilityId("Home");
        Home.click();

        //Opens the browser
        MobileElement browser = (MobileElement) driver.findElementByAccessibilityId("Browser");
        browser.click();

        MobileElement url = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/url");
        url.sendKeys("https://www.lambdatest.com");

        MobileElement find = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/find");
        find.click();
    }



    @After
    public void teardown(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            Utility.setSessionStatus(driver, "failed");
        } else {
            Utility.setSessionStatus(driver, "passed");
        }
        Thread.sleep(2000);
        driver.quit();
    }
}