package famx.account.testCases;

import com.fampay.automation.pageObject.famX.account.AccountEvents;
import com.fampay.automation.util.helper.TestDataUtil;
import com.fampay.automation.util.mobile.android.AndroidOps;
import com.fampay.automation.util.test.AbstractTestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AccountTc extends AbstractTestBase {

    AccountEvents acc;
    @BeforeTest
    public void initObject(){
        acc = new AccountEvents("src/main/java/com/fampay/automation/PageObject/famX/account/AccountLocators.yaml");
    }

    @Test(priority = 0)
    public void checkingUserprofileIcon(){
        Assert.assertTrue(acc.isProfileIconPresent());
    }

    @Test(priority = 1)
    public void checkingNotificationIcon(){
        Assert.assertTrue(acc.isNotificationIconPresent());
    }

    @Test(priority = 2)
    public void verifyingUserBal(){
        Assert.assertEquals(acc.getUserWalletBal(), TestDataUtil.getUserData("userBal"));
    }

    @Test(priority = 3)
    public void verifyAddMoneyCta(){
        Assert.assertTrue(acc.isAddMoneyCtaPresent());
    }

    @Test(priority = 4)
    public void verifyingUserUpiHandle(){
     Assert.assertEquals(acc.getUserUpiHandle(),TestDataUtil.getUserData("upiHandle"));
    }

    @Test(priority = 5)
    public void verifyingQrCode(){
        Assert.assertTrue(acc.openQrCode());
        Assert.assertTrue(acc.isQrCodeDisplayed());
    }

//    @Test(priority = 6)
//    public void verifyingRestoreUpi(){
//        String txt = acc.getQrCodeActionCtaText();
//        Assert.assertNotNull(txt);
//        if(txt.equals("Reactivate UPI ID")){
//            softAssert.assertTrue(acc.isUpiRestoreBackCtaDisplayed());
//            softAssert.assertTrue(acc.isUpiRestoreInfoCtaDisplayed());
//            Assert.assertTrue(acc.tapOnQrCodeActionCta());
//            Assert.assertEquals(acc.getRestoringUpiHandle(),TestDataUtil.getUserData("upiHandle"));
//            Assert.assertTrue(acc.isRestoreUpiIdLottieDisplayed());
//            softAssert.assertEquals(acc.getVerifyUpiIdCtaText(),"Verify via SMS");
//            Assert.assertTrue(acc.tapOnVerifyUpiIdCta());
//            Assert.assertTrue(acc.waitForRestoreTittle("UPI ID\n"+"reactivated!"));
//            softAssert.assertEquals(acc.getVerifyUpiIdCtaText(),"Let's go");
//            Assert.assertTrue(acc.tapOnVerifyUpiIdCta());
//        }
//    }
//
//    @Test(priority = 7)
//    public void verifyingCopyUpiHandleCta(){
//        Assert.assertTrue(acc.tapOnCopyUpiIdCta(TestDataUtil.getUserData("upiHandle")));
//        String toastTxt = AndroidOps.getObject().getToastText();
//        Assert.assertTrue(toastTxt!=null ? toastTxt.equalsIgnoreCase("UPI ID copied!"):false);
//        Assert.assertEquals(AndroidOps.getObject().getClickBoardText(),TestDataUtil.getUserData("upiHandle"));
//    }
//
//    @Test(priority = 8)
//    public void verifyingEnterAmtToReceive(){
//        softAssert.assertEquals(acc.getQrCodeActionCtaText(),"Enter amount to receive");
//        Assert.assertTrue(acc.tapOnQrCodeActionCta());
//        softAssert.assertTrue(acc.receiveAmtSheetHeader());
//        softAssert.assertEquals(acc.getReceiveAmtSheetTittle(),"Enter amount to receive via QR code");
//        softAssert.assertFalse(acc.isConfirmCtaEnabled());
//        Assert.assertTrue(acc.enterAmtToReceive("30"));
//        softAssert.assertTrue(acc.tapOnClearAmtField());
//        Assert.assertTrue(acc.enterAmtToReceive("10"));
//        softAssert.assertTrue(acc.isConfirmCtaEnabled());
//        Assert.assertTrue(acc.tapOnConfirmCta());
//        Assert.assertEquals(acc.getQrCodeActionCtaText(),"Amount set: ₹10");
//        Assert.assertTrue(acc.tapOnClearQrActionCtaText());
//    }

    @Test(priority = 9)
    public void verifyingQrCodeTheme(){
        Assert.assertTrue(acc.tapOnEditQrCodeThemeCta());
        softAssert.assertEquals(acc.getQrCodeThemeScreenTittle(),"Pick QR Code Theme");
        softAssert.assertTrue(acc.tapOnQRThemeShareCta());
        boolean flag = acc.isSharePanelOpened();
        softAssert.assertTrue(flag);
        if(flag)
        AndroidOps.getObject().navigateBack();
        softAssert.assertEquals(acc.getUserUpiHandle(),TestDataUtil.getUserData("upiHandle"));
        Assert.assertTrue(acc.tapOnQrThemeBackCta());
    }


//    @Test(priority = 10)
//    public void verifyingCustomiseUpiId(){
//        Assert.assertTrue(acc.tapOnEditUpiIdCta());
//        softAssert.assertEquals(acc.getEditUpiIdScreenTittle(),"Customise UPI ID");
//        softAssert.assertEquals(acc.getEditUpiIdScreenSubTittle(),"Buy and show off your custom UPI ID!");
//    }
//
//    @DataProvider
//    public Object[][] upiEditData(){
//        return new Object[][] {
//                {"65","UPI ID can only be 4-20 characters long."} ,
//                {"655678","You can only use your own phone number."},
//                {"random","UPI ID must contain your first/last name."},
//        };
//
//    }
//
//    @Test(priority = 11,dataProvider = "upiEditData")
//    public void verifyingInvalidEditUpiIdField(String upiHandle,String validationTxt){
//        Assert.assertTrue(acc.enterCustomUpiId(upiHandle));
//        Assert.assertTrue(acc.getInvalidUpiErrorText().equalsIgnoreCase(validationTxt));
//        Assert.assertFalse(acc.isSaveCtaEnabled());
//    }

//    @Test(priority = 12)
//    public void verifyingValidEditUpiHandle(){
//        Assert.assertTrue(acc.enterCustomUpiId(TestDataUtil.getUserData("newValidUpiHandle")));
//        Assert.assertTrue(acc.getValidUpiText().equalsIgnoreCase("Woohoo! This UPI ID is available."));
//        softAssert.assertTrue(acc.isSaveCtaEnabled());
////        Assert.assertTrue(acc.tapOnSaveCta());
//        Assert.assertTrue(acc.tapOnCloseQrCodeCta());
//    }

    @Test(priority = 13)
    public void verifyingCloseCtaInQrCodeScreen(){
        Assert.assertTrue(acc.tapOnCloseQrCodeCta());
    }

    @Test(priority = 14)
    public void verifyingFamCardHc(){
        Assert.assertTrue(acc.isFamCardHcDisplayed());
    }

    @Test(priority = 15)
    public void verifyingTransactionSection(){
        softAssert.assertTrue(acc.isMonthlyAddedMoneyIconPresent());
        softAssert.assertTrue(acc.isMonthlySentMoneyIconPresent());
        softAssert.assertTrue(acc.isMonthlySavedMoneyIconPresent());
        softAssert.assertEquals(acc.getRecentTxnDetailContainer(),2);
        Assert.assertTrue(acc.tapOnViewAllTxnCta());
        softAssert.assertTrue(acc.isTxnScreenTittleDisplayed());
        Assert.assertTrue(acc.tapOnTxnBackCta());
    }

    @Test(priority = 16)
    public void verifyingSpendingLimit(){
        acc.getToNeedHelpSection();
        Assert.assertTrue(acc.tapOnSpendingLimitHc());
        Assert.assertTrue(acc.isSpendingLmtTittleDisplayed());
        String lmt=acc.getMonthlySpendLmt();
        Assert.assertTrue(lmt!=null?lmt.equals("₹"+TestDataUtil.getUserData("monthlyLmt")+"/mo"):false);
        lmt = acc.getYearlySpendLmt();
        Assert.assertTrue(lmt!=null?lmt.equals("₹"+TestDataUtil.getUserData("yearlyLmt")+"/yr"):false);
    }

    private  void navBack(){
        AndroidOps.getObject().navigateBack();
        AndroidOps.getObject().navigateBack();
    }
    @Test(priority = 17)
    public void verifyingP2PSpendingLimit(){
        Assert.assertTrue(acc.tapOnEditP2PLimitCta());
        Assert.assertEquals(acc.getEditBottomSheetTittle(),"Spending limits for\n"+"paying a friend");
        Assert.assertTrue(acc.getEditBottomSheetSubTittle()
                    .equals("Your max limit is currently ₹"+TestDataUtil.getUserData("p2pMonthlyLmt")));
        String lmtText = acc.getLimitText();
        softAssert.assertTrue(acc.enterLimitAmt("200"));
        Assert.assertTrue(acc.isSaveLmtEnabled());
        this.navBack();
        Assert.assertEquals(acc.getEditP2PLimitSubText(),"₹"+lmtText+" per transaction");
    }

    @Test(priority = 18)
    public void verifyingP2MSpendingLimit(){
        Assert.assertTrue(acc.tapOnEditP2MLimitCta());
        Assert.assertEquals(acc.getEditBottomSheetTittle(),"Spending limits for\n"+"paying UPI merchants");
        Assert.assertTrue(acc.getEditBottomSheetSubTittle()
                .equals("Your max limit is currently ₹"+TestDataUtil.getUserData("p2mMonthlyLmt")));
        String lmtText = acc.getLimitText();
        softAssert.assertTrue(acc.enterLimitAmt("200"));
        Assert.assertTrue(acc.isSaveLmtEnabled());
        this.navBack();
        Assert.assertEquals(acc.getEditP2MLimitSubText(),"₹"+lmtText+" per transaction");
    }

    @Test(priority = 19)
    public void verifyingP2MCardSpendingLimit(){
        Assert.assertTrue(acc.tapOnEditP2MCardLimitCta());
        Assert.assertEquals(acc.getEditBottomSheetTittle(),"Spending limits for\n"+"FamX card");
        Assert.assertTrue(acc.getEditBottomSheetSubTittle()
                .equals("Your max limit is currently ₹"+TestDataUtil.getUserData("p2mFamCardMonthlyLmt")));
        String lmtText = acc.getLimitText();
        softAssert.assertTrue(acc.enterLimitAmt("200"));
        Assert.assertTrue(acc.isSaveLmtEnabled());
        this.navBack();
        Assert.assertEquals(acc.getEditP2MCardLimitSubText(),"₹"+lmtText+" per transaction");
//        Assert.assertTrue(acc.tapOnSpendingLmtHelpCta());
        Assert.assertTrue(acc.tapOnSpendingLmtBackCta());
    }

    @Test(priority = 20)
    public void verifyingSavedPaymentMode(){
        Assert.assertTrue(acc.tapOnSavedPaymentModeHc());
        Assert.assertEquals(acc.getSavedPaymentModesTittle(),"Saved payment\n" +"modes");
        if(!acc.isNoSavePaymentModeDisplayed()){
            String saveMethodName = acc.getSavedMethodName();
            Assert.assertTrue(acc.tapOnDeleteSavedPaymentMode());
            Assert.assertTrue(acc.getBlockedUpiIdBottomSheetTittle().equals("Delete UPI ID\n" +saveMethodName));
            Assert.assertTrue(acc.tapOnCancelUnblockingUpiId());
            Assert.assertTrue(acc.tapOnDeleteSavedPaymentMode());
            Assert.assertTrue(acc.tapOnUnblockUpiIdCta());
        }
        Assert.assertTrue(acc.tapOnCloseQrCodeCta());
    }

    @Test(priority = 21)
    public void verifyingBackupUpiId(){
        Assert.assertTrue(acc.tapOnAddBackupUpiIdHc());
        Assert.assertEquals(acc.getReceiveAmtSheetTittle(),"Edit backup UPI ID");
        softAssert.assertEquals(acc.getEditBackupUpiIdSubTittle(),"Your money will be transferred to this UPI ID in case of any account issues.");
        softAssert.assertFalse(acc.isConfirmCtaEnabled());
        softAssert.assertEquals(acc.getBackUpUpiId(),TestDataUtil.getUserData("backupId"));
        Assert.assertFalse(acc.isConfirmCtaEnabled());
        this.navBack();
    }

    @Test(priority = 22)
    public void verifyingBlockedUpiId(){
        Assert.assertTrue(acc.tapOnBlockedUpiIdHc());
        Assert.assertEquals(acc.getBlockedUpiIdTittle(),"Blocked UPI IDs");
    }

    @Test(priority = 23)
    public void verifyingUnblockUpiId() {
        if (!acc.isNoUpiIdBlocked()){
        softAssert.assertEquals(acc.getBlockedUpiIdSubTittle(),"These UPI IDs won’t be able to send or receive money from you.");
            String blockedVpa = acc.getBlockedVpa();
            Assert.assertTrue(acc.tapOnUnblockCta());
            softAssert.assertEquals(acc.getBlockedUpiIdBottomSheetTittle(), "Ublock UPI ID\n" + blockedVpa);
            softAssert.assertTrue(acc.tapOnCancelUnblockingUpiId());
            Assert.assertTrue(acc.tapOnUnblockCta());
            Assert.assertTrue(acc.tapOnUnblockUpiIdCta());
    }
        Assert.assertTrue(acc.tapOnCloseQrCodeCta());
    }

    @Test(priority = 24)
    public void verifyingContactSupport(){
        acc.scrollTillFooterImage();
      Assert.assertTrue(acc.tapOnContactSupportHc());
      Assert.assertTrue(acc.contactSupportBottomSheet());
      AndroidOps.getObject().navigateBack();
    }

    @Test(priority = 25)
    public void verifyingDoAndDont(){
        Assert.assertTrue(acc.tapOnDoAndDontHc());
        Assert.assertEquals(acc.getDoAndDontTittle(),"Guide for security");
        Assert.assertTrue(acc.tapOnCloseQrCodeCta());
    }

}
