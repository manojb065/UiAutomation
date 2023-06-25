package com.fampay.automation.util.test.driver;

import com.fampay.automation.util.logging.LogUtil;
import com.fampay.automation.util.config.constants.DriverType;
import com.fampay.automation.util.config.constants.MODE;
import io.appium.java_client.AppiumDriver;

public final class DriverManager {
    private DriverManager() {
    }

    static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();

    public static void startSession(MODE mode, DriverType type) {
        DriverFactory df = new DriverFactory();
        df.initDriver(mode, type);
        driver.set(df.getDriver());

    }

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void stopSession() {
        driver.get().quit();
        LogUtil.getConsoleLogger().info( "!!!!!!! Session stopped !!!!!!!");
    }
}
