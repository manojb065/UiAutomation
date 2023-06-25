package com.fampay.automation.pageObject.famX.account;

import com.fampay.automation.util.helper.LocatorsUtil;
import com.fampay.automation.util.mobile.AbstractBasePage;
import com.fampay.automation.util.mobile.android.AndroidOps;
import org.openqa.selenium.interactions.Locatable;

public class AccountEvents extends AbstractBasePage {

    static LocatorsUtil loc = new LocatorsUtil();

    int len;
    public AccountEvents(String locatorPath){
        loc.loadYaml(locatorPath);
        len = (int)(AndroidOps.getObject().getDeviceScreenSize().height/2*0.3);
    }
    public boolean isProfileIconPresent(){
        return isElementDisplayed(loc.getUiElement("profileIcon"));
    }

    public boolean isNotificationIconPresent(){
        return isElementDisplayed(loc.getUiElement("notificationIcon"));
    }

    public String getUserWalletBal(){
        return getElementText(loc.getUiElement("walletBal"));
    }

    public boolean isAddMoneyCtaPresent(){
        return isElementDisplayed(loc.getUiElement("addMoneyCta"));
    }

    public String getUserUpiHandle(){
        return getElementText(loc.getUiElement("upiHandle"));
    }

    //Todo need to add open logic
    public boolean openQrCode(){
        try {
            actions.scroll(len, len * 10);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean isQrCodeDisplayed(){
        return isElementDisplayed(loc.getUiElement("qrCode"));
    }

    public String getQrCodeActionCtaText(){
        return getElementText(loc.getUiElement("qrCodeActionCta"));
    }

    public boolean tapOnQrCodeActionCta(){
        return clickElement(loc.getUiElement("qrCodeActionCta"));
    }

    public boolean waitForRestoreTittle(String tittle){
        return waitUntilElementWithText(60,loc.getUiElement("upiRestoreTittle"),tittle);
    }

    public String getRestoringUpiHandle(){
        return getElementText(loc.getUiElement("restoringUpiId"));
    }

    public boolean isRestoreUpiIdLottieDisplayed(){
        return isElementDisplayed(loc.getUiElement("restoreLottie"));
    }

    public String getVerifyUpiIdCtaText(){
        return getElementText(loc.getUiElement("verifyCta"));
    }
    public boolean tapOnVerifyUpiIdCta(){
        return clickElement(loc.getUiElement("verifyCta"));
    }

    public boolean isUpiRestoreBackCtaDisplayed(){
        return isElementDisplayed(loc.getUiElement("upiRestoreBackCta"))
                && getElementAttribute(loc.getUiElement("upiRestoreBackCta"),"clickable");
    }

    public boolean isUpiRestoreInfoCtaDisplayed(){
        return isElementDisplayed(loc.getUiElement("upiRestoreInfoCta"))
                && getElementAttribute(loc.getUiElement("upiRestoreInfoCta"),"clickable");
    }

    public boolean tapOnCopyUpiIdCta(String upiHandle){
        return clickElement(getElementByXpath(String.format(loc.getUiSelector("copyCta", "xpath"), upiHandle)));
    }

    public boolean receiveAmtSheetHeader(){
        return isElementDisplayed(loc.getUiElement("receiveAmtSheetHeader"));
    }

    public String getReceiveAmtSheetTittle(){
        return getElementText(loc.getUiElement("receiveAmtTittle"));
    }

    public boolean enterAmtToReceive(String amt){
        return sendTextToElement(loc.getUiElement("inputAmtField"),amt);
    }

    public boolean tapOnClearAmtField(){
        return clickElement(loc.getUiElement("clearAmtField"));
    }

    public boolean isConfirmCtaEnabled(){
        return isElementEnabled(loc.getUiElement("confirmCta"));
    }

    public boolean tapOnConfirmCta(){
        return clickElement(loc.getUiElement("confirmCta"));
    }

    public boolean tapOnClearQrActionCtaText(){
       return   clickElement(loc.getUiElement("clearQrActionCta"));
    }

    public boolean tapOnEditQrCodeThemeCta(){
        return clickElement(loc.getUiElement("qrCodeThemeCta"));
    }

    public String getQrCodeThemeScreenTittle(){
        return getElementText(loc.getUiElement("themeScreenTittle"));
    }

    public boolean tapOnQRThemeShareCta(){
        return clickElement(loc.getUiElement("themeShareCta"));
    }

    public boolean isSharePanelOpened(){
        return isElementDisplayed(loc.getUiElement("sharePanel"));
    }

    public boolean tapOnQrThemeBackCta(){
        return clickElement(loc.getUiElement("qrThemeBackCta"));
    }

    public boolean tapOnEditUpiIdCta(){
        return clickElement(loc.getUiElement("editUpiCta"));
    }

    public String getEditUpiIdScreenTittle(){
        return getElementText(loc.getUiElement("customUpiIdTittle"));
    }

    public String getEditUpiIdScreenSubTittle(){
        return getElementText(loc.getUiElement("customUpiIdSubTittle"));
    }

    public boolean enterCustomUpiId(String upiId){
        return sendTextToElement(loc.getUiElement("customUpiIdEnterField"),upiId);
    }

    public String getInvalidUpiErrorText(){
        return getElementText(loc.getUiElement("invalidUpiErrorText"));
    }

    public String getValidUpiText(){
        return getElementText(loc.getUiElement("validUpiText"));
    }

    public boolean isSaveCtaEnabled(){
        return isElementEnabled(loc.getUiElement("saveCta"));
    }

    public boolean tapOnSaveCta(){
        return clickElement(loc.getUiElement("saveCta"));
    }
    public boolean tapOnCloseQrCodeCta(){
        return clickElement(loc.getUiElement("closeQrCodeCta"));
    }

    public boolean isFamCardHcDisplayed(){
        return isElementDisplayed(loc.getUiElement("famCardHc"));

    }

    public boolean isMonthlyAddedMoneyIconPresent(){
        return isElementDisplayed(loc.getUiElement("monthlyAddMoneyIcon"));
    }

    public boolean isMonthlySentMoneyIconPresent(){
        return isElementDisplayed(loc.getUiElement("monthlySentMoneyIcon"));
    }

    public boolean isMonthlySavedMoneyIconPresent(){
        return isElementDisplayed(loc.getUiElement("monthlySavedMoneyIcon"));
    }

    public int getRecentTxnDetailContainer(){
        return loc.getUiElements("twoRecentTxnDetail").size();
    }

    public boolean tapOnViewAllTxnCta(){
        return clickElement(loc.getUiElement("viewAllTxnCta"));
    }

    public boolean isTxnScreenTittleDisplayed(){
        return isElementDisplayed(loc.getUiElement("txnTittle"));

    }

    public boolean tapOnTxnBackCta(){
        return clickElement(loc.getUiElement("txnBackCta"));
    }

    public boolean tapOnSpendingLimitHc(){
        if(scrollUntilElementIsVisible("id",loc.getUiSelector("spendingLimitHc","id"),len*-1,8))
        return clickElement(loc.getUiElement("spendingLimitHc"));
        return false;
    }

    public boolean tapOnSpendingLmtBackCta(){
        return clickElement(loc.getUiElement("spendingLmtBackCta"));
    }

    public boolean tapOnSpendingLmtHelpCta(){
        return clickElement(loc.getUiElement("spendingLmtHelpCta"));
    }

    public boolean isSpendingLmtTittleDisplayed(){
        return isElementDisplayed(loc.getUiElement("spendingLimitTittle"));
    }

    public String getMonthlySpendLmt(){
        return getElementText(loc.getUiElement("spendingMonthlyLmt"));
    }

    public String getYearlySpendLmt(){
        return getElementText(loc.getUiElement("spendingYearlyLmt"));
    }

    public boolean tapOnEditP2PLimitCta(){
        return clickElement(loc.getUiElement("editP2PLimitCta"));
    }



    public String getEditBottomSheetTittle(){
        return getElementText(loc.getUiElement("editBottomSheetTittle"));
    }

    public  String getEditBottomSheetSubTittle(){
        return getElementText(loc.getUiElement("editBottomSheetSubTittle"));
    }

    public boolean enterLimitAmt(String amt){
        return sendTextToElement(loc.getUiElement("editLimitAmtInput"),amt);
    }

    public String getLimitText(){
        return getElementText(loc.getUiElement("editLimitAmtInput"));
    }

    public boolean isSaveLmtEnabled(){
        return isElementEnabled(loc.getUiElement("saveEditedLmtCta"));
    }

    public String getEditP2PLimitSubText(){
        return getElementText(loc.getUiElement("editP2PLimitSubText"));
    }

    public boolean tapOnEditP2MLimitCta(){
        return clickElement(loc.getUiElement("editP2MLimitCta"));
    }

    public String getEditP2MLimitSubText(){
        return getElementText(loc.getUiElement("editP2MLimitSubText"));
    }

    public boolean tapOnEditP2MCardLimitCta(){
        return clickElement(loc.getUiElement("editP2MCardLimitCta"));
    }

    public String getEditP2MCardLimitSubText(){
        return getElementText(loc.getUiElement("editP2MCardLimitSubText"));
    }

    public boolean tapOnSavedPaymentModeHc(){
        if(scrollUntilElementIsVisible("id",loc.getUiSelector("savedPaymentModeHc","id"),len*-1,8))
            return clickElement(loc.getUiElement("savedPaymentModeHc"));
        return false;
    }

    public String getSavedPaymentModesTittle(){
        return getElementText(loc.getUiElement("savedPaymentModeTittle"));
    }

    public boolean isNoSavePaymentModeDisplayed(){
        return isElementDisplayed(loc.getUiElement("noSavedPaymentMode"));
    }

    public boolean tapOnDeleteSavedPaymentMode(){
        return  clickElement(loc.getUiElement("deleteSavedCard"));
    }

    public String getSavedMethodName(){
        return getElementText(loc.getUiElement("savedMethodName"));
    }

    public boolean tapOnAddBackupUpiIdHc(){
        if(scrollUntilElementIsVisible("id",loc.getUiSelector("addBackupUpiIdHc","id"),len*-1,8))
            return clickElement(loc.getUiElement("addBackupUpiIdHc"));
        return false;
    }

    public String getEditBackupUpiIdSubTittle(){
        return getElementText(loc.getUiElement("addBackupUpiIdSubTittle"));
    }

    public  String getBackUpUpiId()
    {
       return getElementText(loc.getUiElement("inputAmtField"));
    }

    public boolean tapOnBlockedUpiIdHc(){
        if(scrollUntilElementIsVisible("xpath",loc.getUiSelector("blockedUpiIdHc","xpath"),len*-1,8))
            return clickElement(loc.getUiElement("blockedUpiIdHc"));
        return false;
    }

    public String getBlockedUpiIdTittle(){
        return getElementText(loc.getUiElement("blockedUpiIdTittle"));
    }

    public String getBlockedUpiIdSubTittle(){
        return getElementText(loc.getUiElement("blockedUpiIdSubTittle"));
    }

    public boolean isNoUpiIdBlocked(){
        return isElementDisplayed(loc.getUiElement("noBlockedUpiId"));
    }

    public boolean tapOnUnblockCta(){
        return clickElement(loc.getUiElement("unblockCta"));
    }

    public String getBlockedVpa(){
        return getElementText(loc.getUiElement("blockedVpa"));
    }


    public String getBlockedUpiIdBottomSheetTittle(){
        return getElementText(loc.getUiElement("blockedUpiIdBottomSheetTittle"));
    }

    public boolean tapOnUnblockUpiIdCta(){
        return clickElement(loc.getUiElement("unblock"));
    }

    public boolean tapOnCancelUnblockingUpiId(){
        return clickElement(loc.getUiElement("dontUnblock"));
    }

    public boolean tapOnContactSupportHc(){
        if(scrollUntilElementIsVisible("id",loc.getUiSelector("contactSupportHc","id"),len*-1,8))
            return clickElement(loc.getUiElement("contactSupportHc"));
        return false;
    }

    public boolean contactSupportBottomSheet(){
        return isElementDisplayed(loc.getUiElement("contactSupportBottomSheet"));
    }

    public boolean tapOnDoAndDontHc(){
        if(scrollUntilElementIsVisible("id",loc.getUiSelector("doAndDontHc","id"),len*-1,8))
            return clickElement(loc.getUiElement("doAndDontHc"));
        return false;
    }

    public String getDoAndDontTittle(){
        return getElementText(loc.getUiElement("doAndDontPageTittle"));
    }

    public void scrollTillFooterImage(){
        scrollUntilElementIsVisible("id",loc.getUiSelector("footerImg","id"),len*-1,8);
    }

    public void getToNeedHelpSection(){
        scrollUntilElementIsVisible("xpath",loc.getUiSelector("needHelpSection","xpath"),len*-1,8);
    }
}