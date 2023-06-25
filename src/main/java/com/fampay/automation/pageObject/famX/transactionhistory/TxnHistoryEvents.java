package com.fampay.automation.pageObject.famX.transactionhistory;

import com.fampay.automation.util.helper.LocatorsUtil;
import com.fampay.automation.util.mobile.AbstractBasePage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Coordinates;

import javax.xml.stream.Location;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class TxnHistoryEvents extends AbstractBasePage {

    LocatorsUtil loc;
    public TxnHistoryEvents(String path){
        loc = new LocatorsUtil();
        loc.loadYaml(path);
    }
    public boolean isBackAvailable(){
        return checkElementIsAvailable(loc.getUiElement("backCta")) &&
                getElementAttribute(loc.getUiElement("backCta"),"clickable");
    }

    public boolean isHelpCtaAvailable(){
        return checkElementIsAvailable(loc.getUiElement("helpCta")) &&
                getElementAttribute(loc.getUiElement("helpCta"),"clickable");
    }

    public boolean isTimeFilterAvailable(){
        return checkElementIsAvailable(loc.getUiElement("timeFilterCta"));
    }

    public boolean isPaidFilterAvailable(){
        return checkElementIsAvailable(loc.getUiElement("paidViaFilterCta"));
    }

    public boolean isStatusFilterAvailable(){
        return checkElementIsAvailable(loc.getUiElement("txnStatusFilterCta"));
    }

    public boolean tapOnTimeFilterCta(){
        return clickElement(loc.getUiElement("timeFilterCta"));
    }

    public String getFilterBottomSheetTittle(){
        return getElementText(loc.getUiElement("filterTittle"));
    }

    public boolean isApplyCtaEnabled(){
        return isElementEnabled(loc.getUiElement("applyFilterCta"));
    }

    public boolean tapOnStartTimeFilterField(){
        return clickElement(loc.getUiElement("startTimeFilter"));
    }


    public boolean monthPicker(String month){
        int y = loc.getUiElements("timeInput").get(0).getLocation().y;
        int x=loc.getUiElements("timeInput").get(0).getLocation().x;
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MMM")
                .withLocale(Locale.ENGLISH);
        int givenMonth = dtFormatter.parse(month).get(ChronoField.MONTH_OF_YEAR);
        int currentMonth = dtFormatter.parse(loc.getElementText(loc.getUiElements("timeInput").get(0))).get(ChronoField.MONTH_OF_YEAR);
        int maxScroll = currentMonth - givenMonth >0? currentMonth - givenMonth +1 : (currentMonth - givenMonth +1)*-1;
        int len = currentMonth - givenMonth >0 ? 135 : -135;

       while (!month.equals(getElementText(loc.getUiElements("timeInput").get(0))) && maxScroll>0){
           actions.slideOrScroll(x,y,x,y+len);
         maxScroll--;
        }
        return maxScroll>0 ? true : false;
    }

    public boolean yearPicker(int year){
        int max = 4;
        int y = loc.getUiElements("timeInput").get(1).getLocation().y;
        int x=loc.getUiElements("timeInput").get(1).getLocation().x;
            while (year!=Integer.parseInt(getElementText(loc.getUiElements("timeInput").get(1)))&&max>0) {
                actions.slideOrScroll(x,y,x,y+135);
                max--;
            }
            return max>0 ? true : false;
    }

    public boolean tapOnEndTimeFilterField(){
        return clickElement(loc.getUiElement("endTimeFilter"));
    }

    public boolean tapOnApplyFilterCta(){
        return clickElement(loc.getUiElement("applyFilterCta"));
    }

    public boolean isDataBeingDisplayed(){
        return isElementDisplayed(loc.getUiElement("txnHisData"));
    }

    public boolean isNoTxnHisDisplayed(){
        return isElementDisplayed(loc.getUiElement("noTxnHisData"));
    }

    public boolean tapOnClearFilterCta(){
        return clickElement(loc.getUiElement("clearFilterCta"));
    }

    public boolean tapOnPayViaFilterCta(){
        return clickElement(loc.getUiElement("paidViaFilterCta"));
    }

    public boolean tapOnFamCardPaymentOpt(){
        return clickElement(loc.getUiElements("payType").get(0));
    }

    public boolean tapOnUPIPaymentOpt(){
        return clickElement(loc.getUiElements("payType").get(1));
    }

    public boolean tapOnInAppTxnOpt(){
        return clickElement(loc.getUiElements("payType").get(2));
    }

    public boolean tapOnTxnHisData(){
        return clickElement(loc.getUiElement("txnHisData"));
    }

    public String getPaidUsrName(){
        return getElementText(loc.getUiElement("paidUsrName"));
    }

    public String getPaidDate(){
        return getElementText(loc.getUiElement("paidDate"));
    }

    public String getPaidAmt(){
        return getElementText(loc.getUiElement("paidAmt"));
    }

    public String getPaymentStatus(){
        return getElementText(loc.getUiElement("paidStatus"));
    }

    public boolean tapOnCloseCta(){
        return clickElement(loc.getUiElement("closeCta"));
    }

    public  boolean tapOnShareCta(){
        return getElementAttribute(loc.getUiElement("shareCta"),"clickable");
    }

    public boolean tapOnViewDetailCta(){
        return clickElement(loc.getUiElement("viewDetailsCta"));
    }

    public boolean isTxnDetailVisible(){
        return isElementDisplayed(loc.getUiElement("viewTransDetail"));
    }

    public boolean tapOnSuccessFulOpt(){
        return clickElement(loc.getUiElements("statusOpt").get(0));
    }

    public boolean tapOnPendingOpt(){
        return clickElement(loc.getUiElements("statusOpt").get(1));
    }

    public boolean tapOnFailedOpt(){
        return clickElement(loc.getUiElements("statusOpt").get(2));
    }

    public boolean tapOnPaymentStatusCta(){
        return clickElement(loc.getUiElement("txnStatusFilterCta"));
    }

    public String getPaidToName(){
        return getElementText(loc.getUiElement("paidToName"));
    }

    public String getExpireInfo(){
        return getElementText(loc.getUiElement("expiresTxtInfo"));
    }

    public boolean tapOnCloseExpiredRequestScreen(){
        return clickElement(loc.getUiElement("expiredScreenCloseCta"));
    }
}
