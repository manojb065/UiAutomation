package com.fampay.automation.util.test.driver;

import com.fampay.automation.util.logging.LogUtil;
import com.fampay.automation.util.config.constants.DriverType;
import com.fampay.automation.util.config.ConfigFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class LocalDriver implements IDriver {
    private DesiredCapabilities caps;
    private AppiumDriver driver;
    String url;
    public LocalDriver(DriverType type) {
        try {
            LogUtil.getConsoleLogger().info("creating session .....");
            String sessionInfo = String.format("\nurl :-- %s\nplatformName :-- %s\nautomationName :-- %s",
                    ConfigFactory.getFrameWorkConfig().appiumServerUrlLocal(),
                    ConfigFactory.getSessionConfig().type(),
                    "UiAutomator2");
            LogUtil.getConsoleLogger().info(sessionInfo);
            caps = new DesiredCapabilities();
            this.url = ConfigFactory.getFrameWorkConfig().appiumServerUrlLocal();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, ConfigFactory.getSessionConfig().type());
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            caps.setCapability("autoGrantPermissions", true);
            if (type.equals(DriverType.ANDROID)) {
                driver = initAndroid();
            } else {
                driver = initIos();
            }
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
        }
    }

    private AndroidDriver initAndroid() throws MalformedURLException {
        LogUtil.getConsoleLogger().info("!!!!!! Android session created !!!!!!!!");
        return new AndroidDriver(new URL(this.url), caps);
    }

    @Override
    public AppiumDriver getDriver() {
        return this.driver;
    }


    private IOSDriver initIos() throws MalformedURLException {
        LogUtil.getConsoleLogger().info("!!!!!!!! ios session created !!!!!!!!!");
        return new IOSDriver(new URL(this.url), caps);
    }

}
