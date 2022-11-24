package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected static ThreadLocal<WebDriver> driverNew = new ThreadLocal<>();
//    protected static ThreadLocal<FirefoxDriver> driverFirefox = new ThreadLocal<>();

    WebDriver driver;

    @Parameters({"browser", "version"})
    @BeforeMethod
    public void setUp (String browser, String version, Method name) throws MalformedURLException {
//        System.out.println("browser name is: " + browser);
//        MutableCapabilities sauceOptions = new MutableCapabilities();
//        sauceOptions.setCapability("name", name.getName());
//        sauceOptions.setCapability("build", "Java-W3C-Examples");
//        sauceOptions.setCapability("seleniumVersion", "4.6.0");
////        sauceOptions.setCapability("username", "oauth-robin.mangla-e8fb9");
//        sauceOptions.setCapability("username", "robin1985");
////        sauceOptions.setCapability("accessKey", "9be6f7ce-a2e6-4762-85d6-2bb7e8fa2cf3");
//        sauceOptions.setCapability("accessKey", "a0fdde47-c0cb-4e0c-ae91-8ae2dc62f883");
//        sauceOptions.setCapability("tags", "w3c-tests");
//
//        DesiredCapabilities cap = new DesiredCapabilities();
//        cap.setCapability("sauce:options", sauceOptions);
//        cap.setCapability("browserVersion", version);
//        cap.setCapability("platformName", "windows 10");
//
//        if(browser.equals("chrome")) {
//            WebDriverManager.chromedriver().setup();
//            cap.setCapability("browserName", "chrome");

//        }
//             else if (browser.equals("firefox")) {
//                WebDriverManager.firefoxdriver().setup();
//                cap.setCapability("browserName", "firefox");

//        }
//
//        else if (browser.equals("edge")) {
//            WebDriverManager.edgedriver().setup();
////            cap.setCapability("browserName", "edge");
//        }
//
//        // https://oauth-robin.mangla-e8fb9:9be6f7ce-a2e6-4762-85d6-2bb7e8fa2cf3@ondemand.us-west-1.saucelabs.com:443/wd/hub
//
////            driver = new RemoteWebDriver(new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub"), cap);
//            driver = WebDriverManager.chromedriver().create();

        if(browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions co = new ChromeOptions();
            co.setHeadless(true);
            co.setCapability("browserName", "107.0.5304.107");
            driverNew.set(new ChromeDriver(co));
        }
        else if(browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions fo = new FirefoxOptions();
            fo.addArguments("--headless");
            driverNew.set(new FirefoxDriver(fo));
        }

//        driver.manage().window().maximize();
//        if(browser.equals("chrome")) {
//            ChromeOptions co = new ChromeOptions();
//            co.setHeadless(true);
//            co.setBrowserVersion(version);
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver(co);
//
//        } else if (browser.equals("firefox")) {
//            FirefoxOptions fo = new FirefoxOptions();
////            fo.setHeadless(true);
////            fo.setBrowserVersion(version);
//            fo.addArguments("--headless");
//            WebDriverManager.firefoxdriver().setup();
//            driver = new FirefoxDriver(fo);
//        }
    }

    public WebDriver getDriver() {

        return driverNew.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
//        driver.quit();
        getDriver().quit();
    }
}
