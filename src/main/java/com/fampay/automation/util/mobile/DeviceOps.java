/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fampay.automation.util.mobile;

import com.fampay.automation.util.test.driver.DriverManager;
import com.fampay.automation.util.logging.LogUtil;
import com.fampay.automation.util.config.constants.Ops;
import io.appium.java_client.AppiumDriver;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.Dimension;
import com.fampay.automation.util.helper.FileUtil;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.html5.Location;

/**
 * @author manoj
 */
public abstract class DeviceOps {

    protected AppiumDriver driver;
    public DeviceOps() {
        this.driver = DriverManager.getDriver();
    }

    public boolean isInstalled(String bundleid) {
        return this.driver.isAppInstalled(bundleid);
    }


    public void launchOrCloseApp(Ops ops) {
        try {
            if (ops.equals(Ops.START)) {
                LogUtil.getConsoleLogger().info("starting App");
                this.driver.launchApp();
            }
            else if (ops.equals(Ops.STOP)) {
                LogUtil.getConsoleLogger().info("closing App");
                this.driver.close();
            }
            else LogUtil.getConsoleLogger().error("invalid option");
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
        }
    }

    public void runAppInBackground(int time) {
        LogUtil.getConsoleLogger().info(String.format("running app in background for %d sec duration",time));
        this.driver.runAppInBackground(Duration.ofSeconds(time));
    }

    public void runOrStopApp(Ops ops, String packageId) {
        try {
            if (ops.equals(Ops.ACTIVE)) {
                LogUtil.getConsoleLogger().info("running App");
                this.driver.activateApp(packageId);
            } else if (ops.equals(Ops.TERMINATE)) {
                LogUtil.getConsoleLogger().info("closing App");
                this.driver.terminateApp(packageId);
            } else {
                throw new Exception("invalid options");
            }
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
        }
    }


    public boolean resetOrRemoveApp(Ops ops, String... args) {
        try {
            if (ops.equals(Ops.RESET)) {
                LogUtil.getConsoleLogger().info("resetting app data");
                this.driver.resetApp();
            }

            else if (ops.equals(Ops.REMOVE)) {
                if (args.length > 0) {
                    LogUtil.getConsoleLogger().info("uninstalling app");
                    this.driver.removeApp(args[0]);
                } else throw new Exception("package is not provided");
            }
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
            return false;
        }
        return true;
    }

    public void hideKeyboard() {
        LogUtil.getConsoleLogger().info("hiding keyboard from foreground");
        this.driver.hideKeyboard();
    }

    public boolean installApp(String appPath) {
        try {
            if (FileUtil.isPathExist(appPath)) {
                LogUtil.getConsoleLogger().info("installing app on device");
                this.driver.installApp(appPath);

            }
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
            return false;
        }

        return true;
    }

    public ScreenOrientation getDeviceScreenOrientation() {
        LogUtil.getConsoleLogger().info("getting device screen orientation");
        return this.driver.getOrientation();
    }

    public Location getDeviceGeoLocation() {
        LogUtil.getConsoleLogger().info("fetching device location");
        return this.driver.location();
    }

    public boolean setDeviceGeoLocation(Location loc) {
        try {
            LogUtil.getConsoleLogger().info(String.format("setting device loc to %f latitude and %f longitude"));
            this.driver.setLocation(loc);
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
            return false;
        }
        return true;
    }


    public Map<String, Object> getDeviceSetting() {
        LogUtil.getConsoleLogger().info("getting device settings");
        return this.driver.getSettings();
    }

    public Dimension getDeviceScreenSize() {
        LogUtil.getConsoleLogger().info("fetching device screen dimensions");
        return this.driver.manage().window().getSize();
    }

}
