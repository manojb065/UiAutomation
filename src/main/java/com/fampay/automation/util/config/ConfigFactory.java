package com.fampay.automation.util.config;


import com.fampay.automation.util.logging.LogUtil;
import org.aeonbits.owner.ConfigCache;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class ConfigFactory {
    static private Properties Sessionproperties;
    static private Properties frameworkProperties;

    static public FrameWorkConfig getFrameWorkConfig(){
        if(frameworkProperties==null){
            frameworkProperties= new Properties();
            try {
                frameworkProperties.load(new FileInputStream(new File("src/main/resources/frameworkConfig.properties")));
            }catch (IOException e){
                LogUtil.getConsoleLogger().error(e.getMessage());
                System.exit(0);
            }
        }
        return ConfigCache.getOrCreate(FrameWorkConfig.class,frameworkProperties);
    }
    static public SessionConfig getSessionConfig() throws Exception {
        if(Sessionproperties==null){
            throw new Exception("Session properties are not loaded");
        }
        return ConfigCache.getOrCreate(SessionConfig.class,Sessionproperties);
    }

    static public void loadSessionProperties(String path) throws IOException {

        Sessionproperties = new Properties();
        Sessionproperties.load(new FileInputStream(new File(path)));

    }


}
