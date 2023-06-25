/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fampay.automation.util.mobile;

/**
 * @author manoj
 */

import com.fampay.automation.util.logging.LogUtil;
import com.fampay.automation.util.test.driver.DriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

public class UiActions implements IUiActions {
    private final TouchAction action;
    private static UiActions ui = new UiActions();

    public static UiActions getObject() {
        return ui;
    }

    private UiActions() {
        this.action = new TouchAction(DriverManager.getDriver());
    }

    public void pressElement(int x, int y) {
        LogUtil.getConsoleLogger().info(String.format("pressing ui element on co ordinates (%d x, %d y) ",x,y));
        press.accept(this.action,PointOption.point(x,y));
    }
    public void pressElement(MobileElement ele) {
        LogUtil.getConsoleLogger().info(String.format("pressing ui element %s on screen",ele.toString()));
        press.accept(this.action,ElementOption.element(ele));
    }

    public void longPressElement(int x, int y) {
        LogUtil.getConsoleLogger().info(String.format("long pressing ui element on co ordinates (%d x, %d y) ",x,y));
        longPressPoint.accept(this.action,PointOption.point(x, y));
    }
    public void longPressElement( MobileElement ele) {
        LogUtil.getConsoleLogger().info(String.format("long pressing ui element %s on screen",ele.toString()));
        longPress.accept(this.action,LongPressOptions.longPressOptions().withElement(ElementOption.element(ele)));
    }


    public void tapElement(MobileElement ele) {
//        this.action.tap(TapOptions.tapOptions().withElement(ElementOption.element(ele))).perform().release();

        LogUtil.getConsoleLogger().info(String.format("tapping ui element %s on screen",ele.toString()));
        tap.accept(this.action,TapOptions.tapOptions().withElement(ElementOption.element(ele)));
    }

    public void tapOnPoint(int x, int y) {
        LogUtil.getConsoleLogger().info(String.format("tapping ui element on co ordinates (%d x, %d y) ",x,y));
        PreformAction.accept(this.action.tap(PointOption.point(x, y)));
    }

    public void moveElement(int x, int y) {
        LogUtil.getConsoleLogger().info(String.format("moving ui element to co ordinates (%d x, %d y) ",x,y));
            move.accept(this.action,PointOption.point(x, y));
    }

    public void moveElement(MobileElement ele) {
        LogUtil.getConsoleLogger().info(String.format("moving ui element %s on screen",ele.toString()));
        PreformAction.accept(this.action.moveTo(ElementOption.element(ele)));
    }

    public void slideOrScroll(int startx, int starty, int endx, int endy) {
        PreformAction.accept(this.action.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endx, endy)));
    }

    public void slide(int startX,int endX){
        this.slideOrScroll(startX,0,endX,0);
    }

    public void scroll(int startY, int endY){
        slideOrScroll(0,startY,0,endY);
    }

    public void dragAndDrop(MobileElement source, MobileElement destination) {
        LogUtil.getConsoleLogger().info("dragging %s ui element  and dropping it into %s ui element",source.toString()
        ,destination.toString());
        PreformAction.accept(this.action.tap(TapOptions.tapOptions().withElement(ElementOption.element(source)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(ElementOption.element(destination)));
    }

    public void slideElement(MobileElement ele, int x) {
        LogUtil.getConsoleLogger().info(String.format("slide element to co ordinates (%d x,%d y) on screen",x,ele.getLocation().y));
        PreformAction.accept(this.action.press(ElementOption.element(ele)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(x,ele.getLocation().y)));
    }

    public void scrollElement(MobileElement ele,int y){
        LogUtil.getConsoleLogger().info(String.format("scroll element to co ordinates (%d x,%d y) on screen",ele.getLocation().x,y));
        PreformAction.accept(this.action.press(ElementOption.element(ele)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(ele.getLocation().x, y)));
    }
}


