package com.fampay.automation.util.report.reportportal;

import com.epam.reportportal.listeners.ItemStatus;
import com.epam.reportportal.testng.BaseTestNGListener;
import com.epam.reportportal.testng.TestNGService;
import com.fampay.automation.util.logging.LogUtil;
import com.fampay.automation.util.report.local.Description;
import com.fampay.automation.util.report.local.Step;
import com.fampay.automation.util.report.local.Steps;
import com.fampay.automation.util.report.local.TestScenario;
import org.testng.ITestResult;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;


public class ReportListener  extends BaseTestNGListener {
    public ReportListener() {
        super(new Listener());
    }
}
   class  Listener extends TestNGService  {

       @Override
       public void startTestMethod(@Nonnull ITestResult testResult) {

//           TODO can check which test to do screen recording and not
//        ((AndroidDriver)DriverManager.getDriver()).startRecordingScreen(
//                new AndroidStartScreenRecordingOptions()
//                        .withTimeLimit(Duration.ofSeconds(240)));
           super.startTestMethod(testResult);
       }

    @Override
    public void finishTestMethod(ItemStatus status, ITestResult result) {
        try{

            Method m = result.getInstance().getClass().getDeclaredMethod(result.getMethod().getMethodName());
            if (m.getDeclaredAnnotation(TestScenario.class) != null)
                LogUtil.getReportLoggerLogger().info(m.getDeclaredAnnotation(TestScenario.class).scenario());
            if (m.getDeclaredAnnotation(Description.class) != null)
                LogUtil.getReportLoggerLogger().info(m.getDeclaredAnnotation(Description.class).description());
            if (m.getDeclaredAnnotation(Step.class) != null)
                LogUtil.getReportLoggerLogger().info(m.getDeclaredAnnotation(Step.class).step());
            if (m.getDeclaredAnnotation(Steps.class) != null)
                for (Step s : m.getDeclaredAnnotation(Steps.class).value())
                    LogUtil.getReportLoggerLogger().info(s.step());
        }catch (Exception e){
        }
//        String video =((AndroidDriver)DriverManager.getDriver()).stopRecordingScreen();
//        if(result.isSuccess()){
//            LogUtil.getReportLoggerLogger().info("success");
//        }else if(!result.isSuccess()){
//            Todo screen recording upload option
//            try {
//                byte[] decode = Base64.decodeBase64(video);
//                FileUtils.writeByteArrayToFile(new File("C:\\Users\\admin\\Desktop\\automationv1\\src\\main\\resources\\androidclip.mp4"), decode);
//                File  f = new File("C:\\Users\\admin\\Desktop\\automationv1\\src\\main\\resources\\androidclip.mp4");
//                LogUtil.log(f, "error");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            LogUtil.logBytes(androidOps.getObject().screenShot(), "Error");
//        }
        super.finishTestMethod(status, result);
    }


}
