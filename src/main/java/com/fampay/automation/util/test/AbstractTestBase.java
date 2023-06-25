/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fampay.automation.util.test;

import com.fampay.automation.util.mobile.android.AndroidOps;
import com.fampay.automation.util.test.driver.DriverManager;
import com.fampay.automation.util.logging.LogUtil;
import com.fampay.automation.util.helper.FileUtil;
import com.fampay.automation.util.config.constants.Ops;
import com.fampay.automation.util.config.ConfigFactory;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

/**
 * @author manoj
 */

public abstract class AbstractTestBase {
    protected final SoftAssert softAssert = new SoftAssert();
    @AfterClass
    public void raiseSoftAssert(){
        softAssert.assertAll();
    }
    @Parameters("SessionPath")
    @BeforeSuite(alwaysRun = true)
    public void startSession(String SessionPath) {
        try {
            ConfigFactory.loadSessionProperties(SessionPath);
            DriverManager.startSession(ConfigFactory.getSessionConfig().mode(), ConfigFactory.getSessionConfig().type());
        }catch (Exception e){
            LogUtil.getConsoleLogger().error(e.getMessage());
            System.exit(0);
        }
        }

    public void installDebugApk() {
        try {
            AndroidOps util = AndroidOps.getObject();
            if(ConfigFactory.getSessionConfig().appPath()!=null) {
                util.installApp(FileUtil.getAppAbsolutePath(ConfigFactory.getSessionConfig().appPath()));
                util.runOrStopApp(Ops.ACTIVE, "com.fampay.in.debug");
            }else{
                util.runOrStopApp(Ops.ACTIVE,"com.fampay.in");
            }
        }catch (Exception e){
            LogUtil.getConsoleLogger().error(e.getMessage());
            System.exit(0);
        }
    }


    public void uninstallDebugApk(){
        try {
            if (ConfigFactory.getSessionConfig().appPath() != null) {
                AndroidOps util = AndroidOps.getObject();
                util.resetOrRemoveApp(Ops.REMOVE, "com.fampay.in.debug");
            }
        }catch (Exception e){
            LogUtil.getConsoleLogger().error(e.toString());
        }
    }
//
    @AfterSuite(alwaysRun = true)
    public void endSession() {
        DriverManager.stopSession();
    }
}
