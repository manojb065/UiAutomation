package com.fampay.automation.util.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Base64;

public abstract class  LogUtil {
    private static final Logger REPORTPORTALBINDATALOGGER = LoggerFactory.getLogger("binary_data_logger");
    private static final Logger CONSOLELOGGER = LoggerFactory.getLogger("console");

    private static final Logger RPLOGGER = LoggerFactory.getLogger("report_portal");

    public static Logger getConsoleLogger(){
        return CONSOLELOGGER;
    }
    public static Logger getReportLoggerLogger(){
        return RPLOGGER;
    }

    public static void logFile(File file, String message) {
        REPORTPORTALBINDATALOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), message);
    }

    public static void logBytes(byte[] bytes, String message) {
        REPORTPORTALBINDATALOGGER.info("RP_MESSAGE#BASE64#{}#{}", Base64.getEncoder().encodeToString(bytes), message);
    }

    public static void logBase64(String base64, String message) {
        REPORTPORTALBINDATALOGGER.info("RP_MESSAGE#BASE64#{}#{}", base64, message);
    }
}
