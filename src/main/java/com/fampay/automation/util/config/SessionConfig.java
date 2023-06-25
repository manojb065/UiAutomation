package com.fampay.automation.util.config;

import com.fampay.automation.util.config.constants.DriverType;
import com.fampay.automation.util.config.constants.MODE;
import com.fampay.automation.util.config.type_convertors.AppTypeConvertor;
import com.fampay.automation.util.config.type_convertors.drivertypetc;
import com.fampay.automation.util.config.type_convertors.modetc;
import org.aeonbits.owner.Config;
public interface SessionConfig extends Config {

    @DefaultValue("local")
    @ConverterClass(value = modetc.class)
    MODE mode();


    String appPath();


    @ConverterClass(value = drivertypetc.class)
    @Key("platformName")
    DriverType type();


    @Key("udid")
    String udid();

    String deviceName();

    String dataPath();

    @DefaultValue("debug")
    @ConverterClass(value = AppTypeConvertor.class)
    String appType();

    String senderEmail();
}