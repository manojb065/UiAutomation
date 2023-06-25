package com.fampay.automation.util.mobile;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.BiFunction;

public interface IUiElementsLocate {
    List<WebElement> elements(String id);
    BiFunction<IUiElementsLocate,String, List<MobileElement>> locateElements = (exe, str)->{return (List) exe.elements(str);};

}
