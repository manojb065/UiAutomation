/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fampay.automation.util.mobile.android;

import com.fampay.automation.util.logging.LogUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;

import java.time.Duration;

import com.fampay.automation.util.helper.FileUtil;
import com.fampay.automation.util.mobile.AppiumUtil;

public class AndroidUtil extends AppiumUtil {
    private AndroidDriver driver;
    private static AndroidUtil util;

    private AndroidUtil() {
        this.driver = (AndroidDriver) super.getDriver();
    }

    public static AndroidUtil getObject() {
        if (util == null)
            util = new AndroidUtil();
        return util;
    }

    public String startRecord() {
        LogUtil.getConsoleLogger().info("starting screen recording");
        AndroidStartScreenRecordingOptions opt = new AndroidStartScreenRecordingOptions();
        opt.withTimeLimit(Duration.ofMinutes(30));
        (this.driver).startRecordingScreen(new AndroidStartScreenRecordingOptions().withTimeLimit(Duration.ofMinutes(30)));
        return "strated";
    }

    public String stopRecord(String name, boolean flag) {
        String video = (this.driver).stopRecordingScreen();
        if (flag) {
            String fileName = System.getProperty("recordingPath") + "\\" + name + ".mp4";
            FileUtil.saveRecording(fileName, video);
            return fileName;
        }
        LogUtil.getConsoleLogger().info("stopping screen recording");
        return "stoped";
    }

}
