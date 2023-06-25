

package com.fampay.automation.util.mobile;

import com.fampay.automation.util.mobile.android.AndroidOps;
import com.fampay.automation.util.test.driver.DriverManager;
import com.fampay.automation.util.test.driver.IDriver;
import com.fampay.automation.util.logging.LogUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class AppiumUtil implements IDriver, IWaits {
    private final AppiumDriver driver;
    public AppiumUtil() {
        this.driver = DriverManager.getDriver();
    }
    @Override
    public AppiumDriver getDriver() {
        return this.driver;
    }


    public boolean clickElement(MobileElement ele) {
        LogUtil.getConsoleLogger().info(String.format("clicking %s ui element",ele.toString()));
        try {
            if(this.waitUntilElementPresent(ele) && this.waitUntilElementClickable(ele)) {
                ele.click();
                return true;
            }else {
                throw  new Exception("unable to click element");
            }
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
            return false;
        }
    }

    public boolean sendTextToElement(MobileElement ele, String text) {
        LogUtil.getConsoleLogger().info(String.format("send %s text to %s ui element",text,ele.toString()));
        try {
            waitUntilElementEnabled(ele);
            ele.sendKeys(text);
            return true;
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
            return false;
        }
    }

    public boolean submit(MobileElement ele) {
        LogUtil.getConsoleLogger().info(String.format("submitting %s ui element",ele.toString()));
        try {
            waitUntilElementEnabled(ele);
            ele.submit();
            return true;
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
            return false;
        }
    }

    public boolean checkElement(MobileElement e1, MobileElement e2) {
        return e1.equals(e2);
    }

    public boolean clearElementText(MobileElement ele) {
        LogUtil.getConsoleLogger().info(String.format("clear the text inside %s ui element",ele.toString()));
        try {
            this.waitUntilElementPresent(ele);
            ele.clear();
            return true;
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
            return false;
        }
    }

    public boolean getElementAttribute(MobileElement ele, String attName) {
        LogUtil.getConsoleLogger().info(String.format("getting value of %s ui element %s attribute",ele.toString(),attName));
        try {
            this.waitUntilElementPresent(ele);
            return Boolean.parseBoolean(ele.getAttribute(attName));
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
        }
        return false;
    }

    public String getElementText(MobileElement ele) {
        LogUtil.getConsoleLogger().info(String.format("fetching text from %s ui element",ele.toString()));
        try {
            this.waitUntilElementPresent(ele);
            return ele.getText();
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
        }
        return null;
    }

    public String getElementTagName(MobileElement ele) {
        LogUtil.getConsoleLogger().info("fetching %s ui element tagname",ele.toString());
        try {
            waitUntilElementEnabled(ele);
            return ele.getTagName();
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
        }
        return null;
    }

    public boolean isElementSelected(MobileElement ele) {
        LogUtil.getConsoleLogger().info("checking if ui element is selected");
        try {
            waitUntilElementEnabled(ele);
            return ele.isSelected();
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
        }
        return false;
    }

    public boolean isElementEnabled(MobileElement ele) {
        LogUtil.getConsoleLogger().info("checking if ui element is enabled");
        try {
            waitUntilElementEnabled(ele);
            return ele.isEnabled();
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
        }
        return false;
    }

    public boolean isElementDisplayed(MobileElement ele) {
        LogUtil.getConsoleLogger().info("checking if ui element is displayed");
        try {
            waitUntilElementEnabled(ele);
            return ele.isDisplayed();
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
        }
        return false;
    }

    public Point getElementLocation(MobileElement ele) {
        LogUtil.getConsoleLogger().info("fetching location of the element on the screen");
        try {
            waitUntilElementEnabled(ele);
            return ele.getLocation();
        } catch (Exception e) {
            LogUtil.getConsoleLogger().error(e.getMessage());
        }
        return null;
    }
    public boolean checkElementIsAvailable(MobileElement ele) {
        LogUtil.getConsoleLogger().info(String.format("checking whether %s ui element is rendered on screen",ele.toString()));
        waitUntilElementPresent(ele);
        return ele.isDisplayed() && ele.isEnabled();
    }

    public byte[] takeScreenShot() {
        LogUtil.getConsoleLogger().info("!!!!! capturing screenshot !!!!!");
        return ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES);
    }


    public void pageLoad(int time) {
        this.driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
    }

    public void screenLoadWait(int time) {
        LogUtil.getConsoleLogger().info("!!!!!! waiting for screen to load !!!!!!");
        this.driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public boolean waitUntilElementPresent(WebElement ele) {
        LogUtil.getConsoleLogger().info(String.format("waiting until %s ui element is rendered on screen",ele.toString()));
        return defaultTimeWaits.apply(driver, ExpectedConditions.visibilityOf(ele));
    }

    public boolean waitUntilElementWithText(WebElement ele, String txt) {
        LogUtil.getConsoleLogger().info(String.format("waiting until %s ui element with %s text to render on screen",
                ele.toString(),txt));
        return defaultTimeWaits.apply(driver, ExpectedConditions.textToBePresentInElement(ele, txt));
    }
    public boolean waitUntilElementWithText(int second,WebElement ele, String txt) {
        LogUtil.getConsoleLogger().info(String.format("waiting until %s ui element with %s text to render on screen",
                ele.toString(),txt));
        return customTimeWaits.apply(new WebDriverWait(driver,second), ExpectedConditions.textToBePresentInElement(ele, txt));
    }

    public boolean waitUntilElementPresent(long time, WebElement ele) {
        LogUtil.getConsoleLogger().info(String.format("waiting until %s ui element to render on screen before %d seconds",
                ele.toString(),time));
        return customTimeWaits.apply(new WebDriverWait(driver, time), ExpectedConditions.visibilityOf(ele));

    }

    public boolean waitUntilElementAttribute(WebElement ele, String attribute, String excepted) {
        LogUtil.getConsoleLogger().info(String.format("waiting until %s ui element with %s attribute have %s value",
                ele.toString(),attribute,excepted));
        return defaultTimeWaits.apply(driver, ExpectedConditions.attributeContains(ele, attribute, excepted));

    }

    public boolean waitUntilElementAttribute(long time, WebElement ele, String attribute, String excepted) {
        LogUtil.getConsoleLogger().info(String.format("waiting until %s ui element with %s attribute have %s value until %d seconds",
                ele.toString(),attribute,excepted,time));
        return customTimeWaits.apply(new WebDriverWait(driver, time), ExpectedConditions.attributeContains(ele, attribute, excepted));

    }

    public boolean waitUntilElementClickable(WebElement ele) {
        LogUtil.getConsoleLogger().info(String.format("waiting until %s ui element is clickable",
                ele.toString()));
        return defaultTimeWaits.apply(driver, ExpectedConditions.elementToBeClickable(ele));

    }

    public boolean waitUntilElementClickable(long time, WebElement ele) {
        LogUtil.getConsoleLogger().info(String.format("waiting until %d before %s ui element is clickable",
                time,ele.toString()));
        return customTimeWaits.apply(new WebDriverWait(driver, time), ExpectedConditions.elementToBeClickable(ele));

    }

    public boolean waitUntilElementEnabled(WebElement ele) {
        LogUtil.getConsoleLogger().info(String.format("waiting until %s ui element is enabled",ele.toString()));
        return defaultTimeWaits.apply(driver, ExpectedConditions.attributeToBe(ele, "enabled", "true"));

    }

    public boolean waitUntilElementEnabled(long time, WebElement ele) {
        LogUtil.getConsoleLogger().info(String.format("waiting %d seconds until  %s ui element is enabled",time,ele.toString()));
        return customTimeWaits.apply(new WebDriverWait(driver, time), ExpectedConditions.attributeToBe(ele, "enabled", "true"));

    }

    public MobileElement getElementById(String id){
        LogUtil.getConsoleLogger().info(String.format("fetching ui element with id %s",id));
        IUiElementLocate loc = this.driver::findElementById;
        return loc.locateElement.apply(loc,id);
    }
    public List<MobileElement> getElementsById(String id){
        LogUtil.getConsoleLogger().info(String.format("fetching list of ui element with id %s",id));
        IUiElementsLocate loc =this.driver::findElementsById;
        return loc.locateElements.apply(loc,id);
    }

    public MobileElement getElementByAccessibilityID(String id){
        LogUtil.getConsoleLogger().info(String.format("fetching ui element with accessibility id %s",id));
        IUiElementLocate loc = this.driver::findElementByAccessibilityId;
        return loc.locateElement.apply(loc,id);
    }
    public List<MobileElement> getElementsByAccessibilityID(String id){
        LogUtil.getConsoleLogger().info(String.format("fetching list of ui element with id %s",id));
        IUiElementsLocate loc =this.driver::findElementsByAccessibilityId;
        return loc.locateElements.apply(loc,id);
    }

    public MobileElement getElementByClassName(String className){
        LogUtil.getConsoleLogger().info(String.format("fetching ui element with classname %s",className));
        IUiElementLocate loc = this.driver::findElementByClassName;
        return loc.locateElement.apply(loc,className);
    }
    public List<MobileElement> getElementsByClassName(String className){
        LogUtil.getConsoleLogger().info(String.format("fetching list of ui element with classname %s",className));
        IUiElementsLocate loc =this.driver::findElementsByClassName;
        return loc.locateElements.apply(loc,className);
    }

    public MobileElement getElementByName(String name){
        LogUtil.getConsoleLogger().info(String.format("fetching ui element with name %s",name));
        IUiElementLocate loc = this.driver::findElementByName;
        return loc.locateElement.apply(loc,name);
    }
    public List<MobileElement> getElementsByName(String name){
        LogUtil.getConsoleLogger().info(String.format("fetching list of ui element with name %s",name));
        IUiElementsLocate loc =this.driver::findElementsByName;
        return loc.locateElements.apply(loc,name);
    }


    public MobileElement getElementByXpath(String xpath){
        LogUtil.getConsoleLogger().info(String.format("fetching ui element with xpath %s",xpath));
        IUiElementLocate loc = this.driver::findElementByXPath;
        return loc.locateElement.apply(loc,xpath);
    }
    public List<MobileElement> getElementsByXpath(String xpath){
        LogUtil.getConsoleLogger().info(String.format("fetching list of ui element with xpath %s",xpath));
        IUiElementsLocate loc =this.driver::findElementsByXPath;
        return loc.locateElements.apply(loc,xpath);
    }

    public MobileElement getElementByImage(String imgPath){
        LogUtil.getConsoleLogger().info("fetching ui element by image ");
        IUiElementLocate loc = this.driver::findElementByImage;
        return loc.locateElement.apply(loc,imgPath);
    }
    public List<MobileElement> getElementsByImage(String imgPath){
        LogUtil.getConsoleLogger().info("fetching list of ui element by image");
        IUiElementsLocate loc =this.driver::findElementsByImage;
        return loc.locateElements.apply(loc,imgPath);
    }

    public MobileElement getElementByTagName(String tagName){
        LogUtil.getConsoleLogger().info(String.format("fetching ui element with tagname %s",tagName));
        IUiElementLocate loc = this.driver::findElementByTagName;
        return loc.locateElement.apply(loc,tagName);
    }
    public List<MobileElement> getElementsByTagName(String tagName){
        LogUtil.getConsoleLogger().info(String.format("fetching list of ui element with tagname %s",tagName));
        IUiElementsLocate loc =this.driver::findElementsByTagName;
        return loc.locateElements.apply(loc,tagName);
    }

    public boolean scrollUntilElementIsVisible(String locatorType,String ele,int scrollLength,int noOfScroll){
        MobileElement element = null;
        for (int i=0;i<noOfScroll;i++) {
            try {
                switch (locatorType) {
                    case "xpath":
                        element = getElementByXpath(ele);
                        return true;
                    case "id":
                        element = getElementById(ele);
                        return true;
                    case "accessibility":
                        element = getElementByAccessibilityID(ele);
                        return true;
                    case "classname":
                        element = getElementByClassName(ele);
                        return true;
                    default:
                        LogUtil.getConsoleLogger().error("invalid option");
                        return false;
                }
            } catch (Exception e) {
                LogUtil.getConsoleLogger().error(String.format(" %s error while fetching ui element",element));
                int ssize = AndroidOps.getObject().getDeviceScreenSize().getHeight() / 2;
                UiActions.getObject().scroll(ssize, ssize + scrollLength);
            }
        }
        LogUtil.getConsoleLogger().error(String.format("unable to find element %s",ele));
        return false;
    }
    public boolean scrollUntilElementIsVisible(MobileElement ele,int scrollLength,int noOfScroll){
        for (int i=0;i<noOfScroll;i++) {
            try {
                if(ele.isDisplayed())
                    return true;

            } catch (Exception e) {
                LogUtil.getConsoleLogger().error(String.format(" %s error while fetching ui element",ele));
                int ssize = AndroidOps.getObject().getDeviceScreenSize().getHeight() / 2;
                UiActions.getObject().scroll(ssize, ssize + scrollLength);
            }
        }
        LogUtil.getConsoleLogger().error(String.format("unable to find element %s",ele));
        return false;
    }

    public String generatePhoneNumber() {
        int length = 9;
        String chars = "0123456789";
        String str = "9" + new Random().ints(length, 0, chars.length())
                .mapToObj(i -> "" + chars.charAt(i))
                .collect(Collectors.joining());
        LogUtil.getConsoleLogger().info(String.format("generated phone number %s",str));
        return str;
    }

    public String generatePanNumber() {
        String number = "0123456789";
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String str = "S" + new Random().ints(4, 1, alphabets.length())
                .mapToObj(i -> "" + alphabets.charAt(i))
                .collect(Collectors.joining()) + new Random().ints(4, 0, number.length())
                .mapToObj(i -> "" + number.charAt(i))
                .collect(Collectors.joining()) + alphabets.charAt(new Random().nextInt(alphabets.length()));
        LogUtil.getConsoleLogger().info(String.format("generated pan number %s",str));
        return str;
    }




}



