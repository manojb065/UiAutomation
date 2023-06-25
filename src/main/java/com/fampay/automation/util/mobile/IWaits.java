package com.fampay.automation.util.mobile;

import com.fampay.automation.util.logging.LogUtil;
import com.fampay.automation.util.config.ConfigFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.function.Function;
import java.util.function.BiFunction;

public interface IWaits {
    public BiFunction<AppiumDriver, Function,Boolean> defaultTimeWaits = (driver, condition)->{
        try {
            new WebDriverWait(driver, ConfigFactory.getFrameWorkConfig()
                    .defaultWaitTime()).
                    until(condition);
            return true;
        }catch (Exception e){
            LogUtil.getConsoleLogger().error(e.getMessage());
            return false;
        }};
    public BiFunction<WebDriverWait, Function,Boolean> customTimeWaits = (driver, condition)->{
        try {
            driver.until(condition);
            return true;
        }catch (Exception e){
            LogUtil.getConsoleLogger().error(e.getMessage());
            return false;
        }};
}
