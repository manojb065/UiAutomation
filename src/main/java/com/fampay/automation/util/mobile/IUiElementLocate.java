package com.fampay.automation.util.mobile;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import java.util.function.BiFunction;

public interface IUiElementLocate {
    WebElement element(String id);
    BiFunction<IUiElementLocate,String, MobileElement> locateElement = (exe, str)->{return (MobileElement) exe.element(str);};

}
