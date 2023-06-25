package com.fampay.automation.util.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "file:${user.dir}/src/main/java/com/fampay/automation/resources/properties/frameworkConfig.properties"
})

public interface FrameWorkConfig extends Config {
    String screenRecordingPath();

    String screenShotPath();
    @Key("localServer")
    String appiumServerUrlLocal();
    @DefaultValue("${user.dir}/src/main/java/com/fampay/automation/reports")
    String reportDir();

    @DefaultValue("testing.html")
    String reportName();

    @DefaultValue("5")
    int defaultWaitTime();

}
