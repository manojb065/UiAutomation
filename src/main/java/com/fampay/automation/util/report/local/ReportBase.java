package com.fampay.automation.util.report.local;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.fampay.automation.util.logging.LogUtil;

public abstract class ReportBase {
    ExtentReports engine;

    protected void setEngine() {
        engine = new ExtentReports();
    }

    protected ExtentReports getEngine() {
        return engine;
    }

    public void generateReport() {
        try {
            engine.flush();
            LogUtil.getConsoleLogger().info("!!!!!!!!!! report generated !!!!!!!!!");
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
        }

    }

    protected ExtentTest createTest(String name) {
        return this.engine.createTest(name);
    }

    protected void setOS(ExtentTest test, String osinfo) {
        test.assignDevice(osinfo);
    }

    protected void setTextLog(ExtentTest test, Status s, String mesg, Object... extra) {
        if (extra.length == 0)
            test.log(s, mesg);
        else {

            test.log(s, mesg, MediaEntityBuilder.createScreenCaptureFromBase64String((String) extra[0]).build());
        }

    }

    protected void setExceptionLog(ExtentTest test, Status s, Throwable e, Object... extra) {
        if (extra.length == 0)
            test.log(s, e);
        else {
            test.log(s, e, MediaEntityBuilder.createScreenCaptureFromBase64String((String) extra[0]).build());
        }
    }

    protected void setTestScreenShot(ExtentTest test, String base64) {
        test.addScreenCaptureFromBase64String(base64);
    }


    protected void setCodeLog(ExtentTest test, Status s, Markup m) {
        test.log(s, m);
    }
}
