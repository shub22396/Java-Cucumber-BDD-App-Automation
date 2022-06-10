package com.lambdatest.webdriver;

import com.lambdatest.util.Utility;
import io.appium.java_client.AppiumDriver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WebDriverFactory {

    private static volatile WebDriverFactory instance;
    private final JSONObject testConfig = this.parseWebDriverConfig();

    private WebDriverFactory() {
    }

    public static WebDriverFactory getInstance() {
        if (instance == null) {
            synchronized(WebDriverFactory.class) {
                if (instance == null) {
                    instance = new WebDriverFactory();
                }
            }
        }

        return instance;
    }

    private JSONObject parseWebDriverConfig() {
        JSONParser parser = new JSONParser();
        String capabilitiesConfigFile = System.getProperty("caps", "src/test/resources/conf/single.conf.json");
        try {
            JSONObject testConfig = (JSONObject) parser.parse(new FileReader(capabilitiesConfigFile));
            return testConfig;
        } catch (IOException | ParseException var6) {
            throw new Error("Unable to parse capabilities file " + capabilitiesConfigFile, var6);
        }
    }

    public List<JSONObject> getPlatforms() {
        JSONArray environments = (JSONArray) testConfig.get("environments");
        List<JSONObject> platforms = new ArrayList<>();
        for (Object obj : environments) {
            JSONObject singleConfig = Utility.getCombinedCapability((Map<String, String>) obj, testConfig);
            platforms.add(singleConfig);
        }
        return platforms;
    }

    public WebDriver createWebDriverForPlatform(JSONObject platform, String testName) {
        try {
            String a = (String) testConfig.get("server");
            String b = (String) testConfig.get("user");
            String c = (String) testConfig.get("key");
            System.out.println(a+"---"+b+"--"+c);
            String URL = String.format("https://%s/wd/hub", testConfig.get("server"));
           // String URL= "https://" + b + ":"+c+a;
            //System.out.println(URL+"--------u--r-----l");
            DesiredCapabilities caps = new DesiredCapabilities(platform);
            caps.setCapability("name", testName);
            //return new RemoteWebDriver(new URL(URL), caps);
            System.out.println("------capapa"+caps);
            return new AppiumDriver<>(new URL(URL), caps);

        } catch (MalformedURLException var4) {
            throw new Error("Unable to create WebDriver", var4);
        }
    }

}
