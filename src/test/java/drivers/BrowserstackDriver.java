package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.MobileConfig;
import config.UserConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import tests.SearchTests;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {
    public MobileConfig mobileConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());
    public UserConfig configUser = ConfigFactory.create(UserConfig.class, System.getProperties());
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", configUser.getBrowserstackUser());
        mutableCapabilities.setCapability("browserstack.key", configUser.getBrowserstackKey());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", mobileConfig.getAppKey());

        // Specify device and os_version for testing
//        mutableCapabilities.setCapability("device", mobileConfig.getDevice());
        mutableCapabilities.setCapability("device", mobileConfig.getDevice());

        mutableCapabilities.setCapability("os_version", mobileConfig.getOsVersion());

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", "First Java Project");
        mutableCapabilities.setCapability("build", "browserstack-build-1");
        mutableCapabilities.setCapability("name", "first_test");

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            return new RemoteWebDriver(
                    new URL("http://hub.browserstack.com/wd/hub"), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
