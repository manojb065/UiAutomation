package com.fampay.automation.util.config.type_convertors;

import com.fampay.automation.util.config.constants.MODE;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;
import java.util.Map;

public class modetc implements Converter<MODE> {
    @Override
    public MODE convert(Method method, String s) {
        Map<String, MODE> map = Map.of("local", MODE.LOCALDRIVER, "browserstack", MODE.BROWSERSTACK);
        return map.getOrDefault(s.toLowerCase(), MODE.LOCALDRIVER);
    }
}
