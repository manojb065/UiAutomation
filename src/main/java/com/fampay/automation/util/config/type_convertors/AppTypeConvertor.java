package com.fampay.automation.util.config.type_convertors;


import com.fampay.automation.util.config.constants.DriverType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;
import java.util.Map;

public class AppTypeConvertor implements Converter<String> {

    public String convert(Method method, String s) {
        if(s.toLowerCase().equals("prod")){
            return "prod";
        }
        return "debug";
    }
}
