package com.fampay.automation.util.test.driver;

import com.fampay.automation.util.config.constants.DriverType;
import com.fampay.automation.util.config.constants.MODE;
import io.appium.java_client.AppiumDriver;

public class DriverFactory implements IDriver {
    private AppiumDriver adriver;

    public void initDriver(MODE mode, DriverType type) {
        if (MODE.LOCALDRIVER.equals(mode)) {
            LocalDriver l = new LocalDriver(type);
            adriver = l.getDriver();

        } else if (MODE.BROWSERSTACK.equals(mode)) {

        }


    }

    @Override
    public AppiumDriver getDriver() {
        return adriver;
    }


}
