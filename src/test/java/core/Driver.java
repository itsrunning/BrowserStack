package core;

import com.thoughtworks.gauge.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver driver = null;

    private static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    private static final String ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
    private static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";


    @BeforeSuite()
    public void beforeSuite() {
        startDriver();

    }

    @BeforeSpec(tags = {"sampleTests"})
    public void anythingBeforeSpec() {
        // log in if required
    }

    @AfterSpec(tags = {"sampleTests"})
    public void anythingAfterSpec(){
        //log out if required
    }

    @AfterSuite
    public void afterSuite() {
        stopDriver();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    private void startDriver() {
        if (driver == null) {
            if (System.getenv("IS_BROWSERSTACK").equalsIgnoreCase("true")) {
                try {
                    DesiredCapabilities caps = new DesiredCapabilities();

                    // Capabilities from environment
                    caps.setCapability("browser", System.getenv("BROWSER"));
                    caps.setCapability("browser_version", System.getenv("BROWSER_VERSION"));
                    caps.setCapability("os", System.getenv("OS"));
                    caps.setCapability("os_version", System.getenv("OS_VERSION"));
                    caps.setCapability("project", System.getenv("PROJECT"));
                    caps.setCapability("name", System.getenv("NAME"));
                    // Hardcoded capabilities
                    caps.setCapability("browserstack.debug", "true");

                    URL remoteURL = new URL(URL);

                    driver = new RemoteWebDriver(remoteURL, caps);
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                } catch (MalformedURLException e) {

                    System.out.println(e.toString());

                }
            }
            else {
                System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
                System.out.println("chrome path : "+System.getenv("CHROME_DRIVER"));
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
                try {
                    driver.manage().window().maximize();
                } catch (Exception e) {
                    System.out.println("Error starting driver : " + e.getMessage());
                }
            }
            System.out.println("Driver is : " + driver);
        }
    }

    private void stopDriver() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }

}
