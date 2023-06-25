/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fampay.automation.util.mobile.android;

import com.fampay.automation.util.logging.LogUtil;
import io.appium.java_client.HasOnScreenKeyboard;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AuthenticatesByFinger;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import com.fampay.automation.util.mobile.DeviceOps;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AndroidOps extends DeviceOps {
    private AndroidDriver driver;
    private static AndroidOps ops;

    private AndroidOps() {
        this.driver = (AndroidDriver) super.driver;
    }

    public static AndroidOps getObject() {
        if ((ops == null))
            ops = new AndroidOps();
        return ops;
    }

    public void navigateBack(){
        this.driver.navigate().back();
    }

    public void launchAppActivity(String packageId, String opt) {
        Activity act = new Activity(packageId, opt);
        LogUtil.getConsoleLogger().info(String.format("launching %s package id %opt activity",packageId,opt));
        this.driver.startActivity(act);
    }

    public void lockDevice(int... sec) {
        if (!this.driver.isDeviceLocked()) {
            if (sec.length > 0) {
                LogUtil.getConsoleLogger().info("unlocking device");
                this.driver.lockDevice(Duration.ofSeconds(sec[0]));
            } else {
                LogUtil.getConsoleLogger().info("locking device");
                this.driver.lockDevice();
            }
        }
    }

    public void unLockDevice() {
        if (this.driver.isDeviceLocked()) {
            this.driver.unlockDevice();
            LogUtil.getConsoleLogger().info("unlocked device");
        }
    }

    public void notification() {
        this.driver.openNotifications();
        LogUtil.getConsoleLogger().info("device notification panel opened");
    }

    public  String getToastText(){
        try {
            LogUtil.getConsoleLogger().info("getting device toast message");
            return new WebDriverWait(this.driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.Toast"))).getText();
        }catch(Exception e){
            return null;
        }
    }

    public String getClickBoardText(){
        LogUtil.getConsoleLogger().info("returning copied text");
        return this.driver.getClipboardText();
    }

    public void authFinger() {
        AuthenticatesByFinger fig = (AuthenticatesByFinger) this.driver;
        fig.fingerPrint(1);
    }

    public boolean checkKeyboard() {
        HasOnScreenKeyboard key = this.driver;
        LogUtil.getConsoleLogger().info("checking whether keyboard is visible or not");
        return key.isKeyboardShown();
    }
    public String screenShotBase64() {
        LogUtil.getConsoleLogger().info("capturing screenshot of the device");
        return ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BASE64);
    }
    public byte[] screenShot() {
        LogUtil.getConsoleLogger().info("capturing screenshot of the device");
        return ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES);
    }

    public String getCurrentAppActivity(){
        LogUtil.getConsoleLogger().info("fetching current launch activity");
        String activity = this.driver.currentActivity();
        LogUtil.getConsoleLogger().info(String.format("Current running activity is %s",activity));
        return activity;
    }

}
