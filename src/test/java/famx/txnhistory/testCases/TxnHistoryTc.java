package famx.txnhistory.testCases;

import com.fampay.automation.pageObject.famX.transactionhistory.TxnHistoryEvents;
import com.fampay.automation.util.helper.TestDataUtil;
import com.fampay.automation.util.logging.LogUtil;
import com.fampay.automation.util.test.AbstractTestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TxnHistoryTc extends AbstractTestBase {



    TxnHistoryEvents txn;
    @BeforeTest
    public void initObject(){
        txn = new TxnHistoryEvents("src/main/java/com/fampay/automation/pageObject/famX/transactionhistory/TxnHistoryLocators.yaml");
    }

    public boolean checkingPaidAmt(String amt){
        String Amt = txn.getPaidAmt();
        return Float.parseFloat(Amt!=null ? Amt.split("₹")[1]: "-1") == Float.parseFloat(amt);
    }

    public boolean checkingPaidUserName(String name){
        return txn.getPaidToName().split("(to|from)")[1].strip().equals(name);
    }

    public boolean checkingTxnDate(String txnDateAndTime,String date) {
       if(date==null){
           return txnDateAndTime.split("at")[0].strip().equals(LocalDate.now()
                   .format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
       }
       return  LocalDate.parse(date+" "+LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")),
                DateTimeFormatter.ofPattern("d MMM yyyy"))
               .compareTo(LocalDate.parse(txnDateAndTime.split("at")[0].strip(),
                       DateTimeFormatter.ofPattern("d MMM yyyy")))==0;
    }

    public boolean checkingTxnTime(String txnDateAndTime,String time){
        try{
            SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
            return parseFormat.parse(txnDateAndTime.split("at")[1]).equals(parseFormat.parse(time));
        }catch (Exception e){
            return false;
        }
    }

    public boolean checkingPaymentStatus(String paidStatus){
        boolean flag = txn.getPaymentStatus().equals(paidStatus);
        if(!paidStatus.equals("Expired") && !paidStatus.equals("Declined")) {
            return flag && txn.tapOnViewDetailCta() && txn.isTxnDetailVisible();
        }
            return flag;
    }
    public  boolean verifyingTxnDetails(){
;        boolean flag = false;
        try {
            flag = txn.isNoTxnHisDisplayed();
        }catch (Exception e){

        }
        try {
            if (!flag) {
                flag = true;
                String usrName = txn.getPaidUsrName();
                String amt[] = txn.getPaidAmt() != null ? txn.getPaidAmt().split("₹") : null;
                String paidDate[] = txn.getPaidDate().split(",");
                String paidStatus = txn.getPaymentStatus();
                if (paidStatus.equals("Paid") || paidStatus.equals("Sent")) {
                    softAssert.assertEquals(amt[0].strip(), "-");
                }
                if (paidStatus.equals("Waiting for payer")) {
                    flag &= txn.tapOnTxnHisData();
                    String expireTxt = txn.getExpireInfo();
                    if (expireTxt != null) {
                        Matcher m = Pattern.compile("\\d{2} \\w{3} \\d{4}").matcher(expireTxt);
                        flag &= m.find();
                        flag&=LocalDate.parse(m.group(), DateTimeFormatter.ofPattern("dd MMM yyyy")).minusDays(1)
                                .format(DateTimeFormatter.ofPattern("dd MMM yyyy")).equals(txn.getPaidDate().split("at")[0].strip());
                    }
                    flag &= txn.tapOnCloseExpiredRequestScreen();

                } else {
                    flag &= txn.tapOnTxnHisData();
                    flag &= checkingPaidAmt(amt.length == 2 ? amt[1] : amt[0]);
                    flag &= checkingPaidUserName(usrName);
                    String txnDateAndTime = txn.getPaidDate();
                    flag &= checkingTxnDate(txnDateAndTime, paidDate.length == 2 ? paidDate[0] : null);
                    flag &= checkingTxnTime(txnDateAndTime, paidDate.length == 2 ? paidDate[1] : paidDate[0]);
                    flag &= checkingPaymentStatus(paidStatus);
                    flag &= txn.tapOnCloseCta();
                }
            } else {
                LogUtil.getConsoleLogger().info("No Txn Detail were Found with Selected Filter");
            }
        }catch (Exception e){
            LogUtil.getConsoleLogger().error(e.getMessage());
            return false;
        }
        return flag;
    }
    @Test(priority = 0)
    public void checkingBackCta(){
        Assert.assertTrue(txn.isBackAvailable());
    }

    @Test(priority = 1)
    public void checkingHelpCta(){
        Assert.assertTrue(txn.isHelpCtaAvailable());
    }

    @Test(priority = 2)
    public void checkIfTimeFilerCtaAvailable(){
     Assert.assertTrue(txn.isTimeFilterAvailable());
    }

    @Test(priority = 3)
    public void checkIfPayViaFilerCtaAvailable(){
        Assert.assertTrue(txn.isPaidFilterAvailable());
    }

    @Test(priority = 4)
    public void checkIfStatusFilerCtaAvailable(){
        Assert.assertTrue(txn.isStatusFilterAvailable());
    }

    @Test(priority = 5)
    public void verifyingTimeFilter(){
        Assert.assertTrue(txn.tapOnTimeFilterCta());
        softAssert.assertEquals(txn.getFilterBottomSheetTittle(), TestDataUtil.getUserData("timeFilterBottomSheetTittle"));
        softAssert.assertFalse(txn.isApplyCtaEnabled());
        Assert.assertTrue(txn.tapOnStartTimeFilterField());
        Assert.assertTrue(txn.monthPicker(LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MMM"))));
        Assert.assertTrue(txn.yearPicker(Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY")))));
//        Assert.assertTrue(txn.tapOnEndTimeFilterField());
//        Assert.assertTrue(txn.monthPicker(LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MMM"))));
//        Assert.assertTrue(txn.yearPicker(Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY")))));
        Assert.assertTrue(txn.tapOnApplyFilterCta());
    }

    @Test(priority = 6)
    public void verifyingTimeFilterResult(){
        Assert.assertTrue(this.verifyingTxnDetails());
        Assert.assertTrue(txn.tapOnTimeFilterCta());
        Assert.assertTrue(txn.tapOnClearFilterCta());
    }

    @Test(priority = 7)
    public void verifyingPaymentFilter(){
        Assert.assertTrue(txn.tapOnPayViaFilterCta());
        Assert.assertEquals(txn.getFilterBottomSheetTittle(),TestDataUtil.getUserData("paymentFilterBottomSheetTittle"));
        Assert.assertFalse(txn.isApplyCtaEnabled());
    }

    @Test(priority = 8)
    public  void verifyingFamCardTxnFilterOpt(){
        Assert.assertTrue(txn.tapOnFamCardPaymentOpt());
        Assert.assertTrue(txn.tapOnApplyFilterCta());
        Assert.assertTrue(this.verifyingTxnDetails());
        Assert.assertTrue(txn.tapOnPayViaFilterCta());
        Assert.assertTrue(txn.tapOnClearFilterCta());
    }

    @Test(priority = 8)
    public void verifyingUpiTxnFilterOpt(){
        Assert.assertTrue(txn.tapOnPayViaFilterCta());
        Assert.assertTrue(txn.tapOnUPIPaymentOpt());
        Assert.assertTrue(txn.tapOnApplyFilterCta());
        Assert.assertTrue(this.verifyingTxnDetails());
        Assert.assertTrue(txn.tapOnPayViaFilterCta());
        Assert.assertTrue(txn.tapOnClearFilterCta());
    }

    @Test(priority = 10)
    public void verifyingAppTxnFilterOpt(){
        Assert.assertTrue(txn.tapOnPayViaFilterCta());
        Assert.assertTrue(txn.tapOnInAppTxnOpt());
        Assert.assertTrue(txn.tapOnApplyFilterCta());
        Assert.assertTrue(this.verifyingTxnDetails());
        Assert.assertTrue(txn.tapOnPayViaFilterCta());
        Assert.assertTrue(txn.tapOnClearFilterCta());
    }

    @Test(priority = 11)
    public void verifyingStatusFilter(){
        Assert.assertTrue(txn.tapOnPaymentStatusCta());
        Assert.assertEquals(txn.getFilterBottomSheetTittle(),TestDataUtil.getUserData("paymentStatusBottomSheetTittle"));
        Assert.assertFalse(txn.isApplyCtaEnabled());
    }

    @Test(priority = 12)
    public  void verifyingSuccessFulFilterOpt(){
        Assert.assertTrue(txn.tapOnSuccessFulOpt());
        Assert.assertTrue(txn.tapOnApplyFilterCta());
        Assert.assertTrue(this.verifyingTxnDetails());
        Assert.assertTrue(txn.tapOnPaymentStatusCta());
        Assert.assertTrue(txn.tapOnClearFilterCta());
    }

    @Test(priority = 13)
    public void verifyingPendingFilterOpt(){
        Assert.assertTrue(txn.tapOnPaymentStatusCta());
        Assert.assertTrue(txn.tapOnPendingOpt());
        Assert.assertTrue(txn.tapOnApplyFilterCta());
        Assert.assertTrue(this.verifyingTxnDetails());
        Assert.assertTrue(txn.tapOnPaymentStatusCta());
        Assert.assertTrue(txn.tapOnClearFilterCta());
    }

    @Test(priority = 14)
    public void verifyingFailedFilterOpt(){
        Assert.assertTrue(txn.tapOnPaymentStatusCta());
        Assert.assertTrue(txn.tapOnFailedOpt());
        Assert.assertTrue(txn.tapOnApplyFilterCta());
        Assert.assertTrue(this.verifyingTxnDetails());
        Assert.assertTrue(txn.tapOnPaymentStatusCta());
        Assert.assertTrue(txn.tapOnClearFilterCta());
    }
}
