package com.fampay.automation.util.helper;

import com.fampay.automation.util.config.ConfigFactory;
import com.fampay.automation.util.logging.LogUtil;
import com.fampay.automation.util.mobile.AbstractBasePage;
import io.appium.java_client.MobileElement;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LocatorsUtil extends AbstractBasePage {
    private    Map<String,Map<String,Map<String,String>>> m;

    String appType ;
    Yaml y;

    public void loadYaml(String path){
        try{
            y = new Yaml();
            InputStream in = new FileInputStream(new File(path));
            m = y.load(in);
            appType = ConfigFactory.getSessionConfig().appType();
        }catch (Exception e){
            LogUtil.getConsoleLogger().error(e.getMessage());
        }
    }
    public MobileElement getUiElement(String key) {
        try {
            Set<String> keySet = m.get(key).get(appType).keySet();
            if (keySet.contains("id")) {
                return getElementById(m.get(key).get(appType).get("id"));
            } else if (keySet.contains("xpath")) {
                return getElementByXpath(m.get(key).get(appType).get("xpath"));
            } else if (keySet.contains("accessibility")) {
                return getElementByAccessibilityID(m.get(key).get(appType).get("accessibility"));
            }
            return getElementByClassName(m.get(key).get(appType).get("className"));
        }catch (Exception e){
            return null;
        }
    }

    public String getUiSelector(String key,String typeOfSelector){
        return m.get(key).get(appType).getOrDefault(typeOfSelector,null);
    }


    public List<MobileElement> getUiElements(String key) {
        Set<String> keySet = m.get(key).get(appType).keySet();
        if(keySet.contains("id")){
            return getElementsById(m.get(key).get(appType).get("id"));
        }else if(keySet.contains("xpath")){
            return getElementsByXpath(m.get(key).get(appType).get("xpath"));
        }else if (keySet.contains("accessibility")){
            return getElementsByAccessibilityID(m.get(key).get(appType).get("accessibility"));
        }
        return getElementsByClassName(m.get(key).get(appType).get("className"));
    }

}
