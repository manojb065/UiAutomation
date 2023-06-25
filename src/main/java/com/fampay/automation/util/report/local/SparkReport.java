package com.fampay.automation.util.report.local;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fampay.automation.util.logging.LogUtil;
import com.fampay.automation.util.config.ConfigFactory;

import java.io.File;

public class SparkReport extends ReportBase {
    private ExtentSparkReporter sengine;
    private ExtentTest test;
    private ExtentTest nodeTest;
    private String reportName=null;
    public void createNode(String name) {
        this.nodeTest = this.test.createNode(name);
    }

    public ExtentTest getNodeTest(){
        return nodeTest;
    }

    public void setReportName(String name){
         this.reportName=name;
    }

    public SparkReport() {
        setEngine();
    }

    public void initSparkReportEngine() {
        try {
            File f = new File(ConfigFactory.getFrameWorkConfig().reportDir());
            if (!f.exists()) {
                f.mkdir();
            }
            this.reportName = this.reportName == null ? ConfigFactory.getFrameWorkConfig().reportName() : this.reportName;
            LogUtil.getConsoleLogger().info(this.reportName);
            this.sengine = new ExtentSparkReporter(f.getPath() + "/" + this.reportName);
//            this.getEngine().attachReporter(sengine);
            this.getEngine().attachReporter(this.sengine);

        }catch (Exception e){
            LogUtil.getConsoleLogger().trace(e.getMessage());
        }
    }

    public void setTest(String testName) {
        this.test = super.createTest(testName);
    }

    public void setLog(Status s, String mesg, Object... screenshot) {
        if (Status.FAIL == s) {
            super.setCodeLog(test, s, MarkupHelper.createLabel(mesg, ExtentColor.RED));
        } else if (Status.WARNING == s) {
            super.setCodeLog(test, s, MarkupHelper.createLabel(mesg, ExtentColor.YELLOW));

        } else if (Status.PASS == s) {
            super.setCodeLog(test, s, MarkupHelper.createLabel(mesg, ExtentColor.GREEN));

        } else {
            super.setTextLog(test, s, mesg, screenshot);
        }
    }

    public void setExceptionLog(Status s, Throwable e, Object... screenshot) {

        super.setExceptionLog(test, s, e);
    }

    public void addTestScreenShot(String base64) {
        super.setTestScreenShot(test, base64);
    }

    public void setAuthor(String name) {
        this.test.assignAuthor(name);

    }

    public void setXmlLog(Status s, String mesg) {

        super.setCodeLog(this.test, s, MarkupHelper.createCodeBlock(mesg, CodeLanguage.XML));
    }

    public void setJsonLog(Status s, String mesg) {
        super.setCodeLog(this.test, s, MarkupHelper.createCodeBlock(mesg, CodeLanguage.JSON));
    }

    public void setOrderedlistLog(Status s, Object mesg) {
        super.setCodeLog(test, s, MarkupHelper.createOrderedList(mesg));
    }

    public void setUnorderedlistLog(Status s, Object mesg) {
        super.setCodeLog(test, s, MarkupHelper.createUnorderedList(mesg));
    }
}
