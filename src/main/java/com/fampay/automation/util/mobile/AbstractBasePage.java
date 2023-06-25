/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fampay.automation.util.mobile;

import com.fampay.automation.util.config.ConfigFactory;
import com.fampay.automation.util.logging.LogUtil;
import com.fampay.automation.util.test.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author manoj
 */
public abstract class AbstractBasePage extends AppiumUtil {
    protected AppiumDriver driver;
    protected UiActions actions;

    public AbstractBasePage() {
        if (driver == null) {
            this.driver = DriverManager.getDriver();
            actions = UiActions.getObject();
            this.driver.manage().timeouts().implicitlyWait(ConfigFactory.getFrameWorkConfig().defaultWaitTime(), TimeUnit.SECONDS);
            PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        }
    }
}
