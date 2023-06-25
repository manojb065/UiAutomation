/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fampay.automation.util.report.local;

import com.aventstack.extentreports.Status;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.fampay.automation.util.config.ConfigFactory;
import com.fampay.automation.util.config.SessionConfig;
import com.fampay.automation.util.email.ReportEmailSender;
import com.fampay.automation.util.mobile.android.AndroidOps;
import com.fampay.automation.util.logging.LogUtil;
import lombok.extern.java.Log;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * @author manoj
 */
public class ReportListeners extends SparkReport implements ITestListener, ISuiteListener {

    private SparkReport rep;



    @Override
    public void onTestSkipped(ITestResult result) {
        List<String> skip = new ArrayList<>();
        result.getSkipCausedBy().forEach((action) -> skip.add(action.getMethodName()));
        rep.setUnorderedlistLog(Status.SKIP, skip);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtil.getConsoleLogger().info("!!!!!! test method passed !!!!!!!");
        rep.setLog(Status.PASS, "Executed successfully");
    }


    @Override
    public void onFinish(ISuite suite) {
        rep.generateReport();
        try {
                URL url = new URL("http://www.google.com");
                URLConnection connection = url.openConnection();
                connection.connect();
                LogUtil.getConsoleLogger().info("Internet is connected");
            ReportEmailSender email = new ReportEmailSender();
            if(ConfigFactory.getSessionConfig().senderEmail() !=null && email.sendEmail(ConfigFactory.getSessionConfig().senderEmail(), suite.getName(),
                    String.join("", suite.getName(),".html")))
                LogUtil.getConsoleLogger().info("sent mail successfully");
            else
                LogUtil.getConsoleLogger().info("error while sending mail");
        }catch (MalformedURLException e) {
            LogUtil.getConsoleLogger().error("Internet is not connected");
        } catch (IOException e) {
            LogUtil.getConsoleLogger().error("Internet is not connected");
        }catch (Exception e){
            LogUtil.getConsoleLogger().error(e.getMessage());
        }

    }

    @Override
    public void onStart(ISuite suite) {
        String name = suite.getName();
        rep = new SparkReport();
        rep.setReportName(String.join("",name,".html"));
        rep.initSparkReportEngine();
    }


    @Override
    public void onTestStart(ITestResult result) {

        try {
            Method m = result.getInstance().getClass().getDeclaredMethod(result.getMethod().getMethodName());
            String testName = result.getMethod().getMethodName();
            rep.setTest(testName);
            if (m.getDeclaredAnnotation(TestScenario.class) != null)
                rep.setLog(Status.INFO, m.getDeclaredAnnotation(TestScenario.class).scenario());
            if (m.getDeclaredAnnotation(Description.class) != null)
                rep.setLog(Status.INFO, m.getDeclaredAnnotation(Description.class).description());
            if (m.getDeclaredAnnotation(Step.class) != null)
                rep.setLog(Status.INFO, m.getDeclaredAnnotation(Step.class).step());
            if (m.getDeclaredAnnotation(Steps.class) != null)
                for (Step s : m.getDeclaredAnnotation(Steps.class).value())
                    rep.setLog(Status.INFO, s.step());
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
        }
        rep.setOrderedlistLog(Status.INFO, result.getTestClass().getXmlTest().getAllParameters());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtil.getConsoleLogger().warn(String.format("test method %s failed due to %s ",
                result.getMethod().getMethodName(),result.getThrowable().getMessage()));
        rep.addTestScreenShot(AndroidOps.getObject().screenShotBase64());
        rep.createNode("Exception");
        rep.getNodeTest().log(Status.FAIL, result.getThrowable());
        rep.setLog(Status.FAIL, "failed");
    }

}
