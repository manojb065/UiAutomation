package com.fampay.automation.util.config.type_convertors;

import com.fampay.automation.util.config.constants.DriverType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;
import java.util.Map;

public class drivertypetc implements Converter<DriverType> {
    @Override
    public DriverType convert(Method method, String s) {
        Map<String, DriverType> map = Map.of("android", DriverType.ANDROID, "ios", DriverType.IOS);
        return map.getOrDefault(s.toLowerCase(), DriverType.ANDROID);
    }
}