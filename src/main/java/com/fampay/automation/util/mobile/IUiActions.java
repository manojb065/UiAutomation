package com.fampay.automation.util.mobile;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


public interface IUiActions {
    public BiConsumer<TouchAction, PointOption> press = (action,opt)->{action.press(opt).release().perform();};
    public BiConsumer<TouchAction,PointOption> longPressPoint = (action,opt)->{action.longPress(opt).release().perform();};
    public BiConsumer<TouchAction, LongPressOptions> longPress = (action,opt)->{action.longPress(opt).release().perform();};
    public BiConsumer<TouchAction, PointOption> move = (action,opt)->{action.longPress(opt).release().perform();};
    public BiConsumer<TouchAction, TapOptions> tap = (action, opt)->{action.tap(opt).release().perform();};
    public Consumer<TouchAction> PreformAction = (action)->{action.release().perform();};
    public BiConsumer<TouchAction,Integer> waitUntilAction = (action,sec)->{action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(sec)));};
}
