package com.fampay.automation.util.helper;

import com.fampay.automation.util.logging.LogUtil;
import com.fampay.automation.util.config.ConfigFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class TestDataUtil {
    private static   Map<String,String> UserData = new HashMap<>();

    public static String getUserData(String key){
        if(UserData.isEmpty()){
            loadUserData();
        }
        return UserData.getOrDefault(key,null);
    }
    private static void loadUserData(){
        try {
         UserData=   new ObjectMapper().readValue(new File(ConfigFactory.getSessionConfig().dataPath()),Map.class);
        } catch (Exception e) {
            LogUtil.getConsoleLogger().trace(e.getStackTrace().toString());
        }
    }
}
