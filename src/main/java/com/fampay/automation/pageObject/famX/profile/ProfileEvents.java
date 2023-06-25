package com.fampay.automation.pageObject.famX.profile;

import com.fampay.automation.util.helper.LocatorsUtil;
import com.fampay.automation.util.mobile.AbstractBasePage;
import com.fampay.automation.util.mobile.android.AndroidOps;

public class ProfileEvents extends AbstractBasePage {
    static int len;
    LocatorsUtil loc ;
    public ProfileEvents(String locPath){
        loc = new LocatorsUtil();
        loc.loadYaml(locPath);
    }
   static{
       len = AndroidOps.getObject().getDeviceScreenSize().getHeight()/2;
       len = (int)(len * 0.4) * -1;
   }
    public String getUserName(){
        return getElementText(loc.getUiElement("userName"));
    }

    public String getUserBio(){
        return getElementText(loc.getUiElement("userBio"));
    }

    public boolean isUserDobAndSchoolScrollable(){
        return getElementAttribute(loc.getUiElement("userDobAndSchoolSection"),"scrollable");
    }

   public String getUserDob(){
        return getElementText(loc.getUiElement("userDob"));
   }

   public boolean isUserSchoolNameHcClickable(){
       return getElementAttribute(loc.getUiElement("userSchoolNameHc"),"clickable");
   }
   public String getUserSchoolName(){
        return getElementText(loc.getUiElement("userSchoolName"));
   }

   public boolean tapOnAddSchoolHc(){
        return clickElement(loc.getUiElement("userSchoolName"));
   }
   public boolean isProfileScrollable(){
        return getElementAttribute(loc.getUiElement("usrProfileScreen"),"scrollable");
   }

   public boolean tapOnEditUsrProfileCta(){
        return clickElement(loc.getUiElement("editUserProfileCta"));
   }

   public boolean tapOnEditUsrProfilePic(){
        return clickElement(loc.getUiElement("editUsrProfilePic"));
   }

   public String getChangeProfilePicTittle(){
        return getElementText(loc.getUiElement("changeUsrProfilePicTxt"));
   }

   public boolean uploadFromGalleryOpt(){
        return getElementAttribute(loc.getUiElement("uploadFromGallery"),"clickable");
   }

    public boolean uploadFromCameraOpt(){
        return getElementAttribute(loc.getUiElement("takePhotoFromCamera"),"clickable");
    }

    public boolean removeUsrProfilePic(){
       return getElementAttribute(loc.getUiElement("removeUsrPic"),"clickable");
    }

    public String getUserProfileName(){
        return getElementText(loc.getUiElement("userProfileName"));
    }

    public String getEditedUserProfileName(){
        return getElementText(loc.getUiElement("editUsrProfileName"));
    }
    public boolean tapOnEditUserProfileName(){
        return clickElement(loc.getUiElement("editUsrProfileName"));
    }


    public String getUserNameBottomSheetTittle(){
        return getElementText(loc.getUiElement("editUserNameBottomSheetTittle"));
    }

    public boolean editUserName(String usrName){
        return sendTextToElement(loc.getUiElement("editUsernameInput"),usrName);
    }

    public boolean tapOnClearUserNameCta(){return clickElement(loc.getUiElement("clearCta"));}
    public String getInvalidUserNameMessg(){
        return getElementText(loc.getUiElement("invalidUsernameErrMessg"));
    }

    public boolean tapOnConfirmCta(){
        return clickElement(loc.getUiElement("confirmCta"));
    }

    public boolean isEditUsrNameDisabled(){
        return !getElementAttribute(loc.getUiElement("disabledEditUserNameField"),"clickable");
    }

    public String getUserNameInProfileEdit(){
        return getElementText(loc.getUiElement("disabledEditUserNameField"));
    }

    public boolean isEditPhoneNoDisabled(){
        return !getElementAttribute(loc.getUiElement("disabledPhoneNoField"),"clickable");
    }

    public String getUserPhoneNo(){
        String phoNo = getElementText(loc.getUiElement("disabledPhoneNoField"));
        return phoNo!=null? phoNo.split("-")[1] : null;
    }

    public boolean enterUserBio(String bio){
        if(scrollUntilElementIsVisible("id",loc.getUiSelector("userBioInputField","id"),len,4))
        return sendTextToElement(loc.getUiElement("userBioInputField"),bio);
        return false;
    }

    public  String getInvalidUserBioErrorText(){
        if(scrollUntilElementIsVisible("id",loc.getUiSelector("invalidUsrBioErrMesg","id"),len,4))
        return getElementText(loc.getUiElement("invalidUsrBioErrMesg"));
        return null;
    }
    public boolean enterUserEmail(String email){ return sendTextToElement(loc.getUiElement("userEmailInputField"),email);}

    public String getInvalidUserEmailErrorText(){return getElementText(loc.getUiElement("invalidUsrEmailErrMesg"));}
    public boolean enterUserSchoolName(String schoolName){
        if(scrollUntilElementIsVisible("id",loc.getUiSelector("userSchoolNameInput","id"),len,4))
       return sendTextToElement(loc.getUiElement("userSchoolNameInput"),schoolName);
        return false;
    }

    public String getPersonalSectionUserDob(){
       if(scrollUntilElementIsVisible("id",loc.getUiSelector("dob","id"),len,4))
        return getElementText(loc.getUiElement("dob"));
       return null;
    }

    public String getUserGender(){
        if(scrollUntilElementIsVisible("id",loc.getUiSelector("userGender","id"),len,4))
            return getElementText(loc.getUiElement("userGender"));
        return null;
    }

    public boolean tapOnGenderDropDown(){
        if(scrollUntilElementIsVisible("id",loc.getUiSelector("lineDivider","id"),len,4))
       return clickElement(loc.getUiElement("userGender"));
        return false;
    }

    public boolean isGenderOptionSelected(String opt){
       switch (opt){
           case "Male": return getElementAttribute(loc.getUiElements("genderOpt").get(0),"checked");
           case "Female": return getElementAttribute(loc.getUiElements("genderOpt").get(1),"checked");
           case "Other": return getElementAttribute(loc.getUiElements("genderOpt").get(2),"checked");
       }
      return false;
    }

    public boolean tapOnConfirmUserGender(){
       return clickElement(loc.getUiElement("confirm"));
    }
    public  boolean enterUsrInstagram(String instaHandle){
       if(scrollUntilElementIsVisible("id",loc.getUiSelector("instagramInputField","id"),len,4)){
           if(getElementText(loc.getUiElement("instagramInputField"))!=null)
           clearElementText(loc.getUiElement("instagramInputField"));
           return sendTextToElement(loc.getUiElement("instagramInputField"),instaHandle);
       }
       return false;
    }
    public  boolean enterUsrSnapchat(String snapHandle){
        if(scrollUntilElementIsVisible("id",loc.getUiSelector("snapchatInputField","id"),len,4)){
            if(getElementText(loc.getUiElement("snapchatInputField"))!=null)
            clearElementText(loc.getUiElement("snapchatInputField"));
            return sendTextToElement(loc.getUiElement("snapchatInputField"),snapHandle);
        }
        return false;
    }

    public boolean tapOnSaveCta(){
       if(scrollUntilElementIsVisible("id",loc.getUiSelector("save","id"),len*-1,6))
       return clickElement(loc.getUiElement("save"));
       return false;
    }

    public boolean tapOnBackCta(){
        if(scrollUntilElementIsVisible("id",loc.getUiSelector("backCta","id"),len*-1,6))
       return clickElement(loc.getUiElement("backCta"));
        return false;
    }

    public boolean waitForCustomerSupportToLoad(){
        return waitUntilElementEnabled(10,loc.getUiElement("faq"));
    }

    public boolean tapOnProfileScreenHc(String hcTittle){
       String hc = String.format(loc.getUiSelector("profileHC","xpath"),hcTittle);
       if(scrollUntilElementIsVisible("xpath",hc,len,4)){
           return clickElement(getElementByXpath(hc));
       }
       return false;
    }

    public void navBack(){
       AndroidOps.getObject().navigateBack();
    }

    public boolean tapOnLogoutCta(){
       if(scrollUntilElementIsVisible("xpath",loc.getUiSelector("logoutCta","xpath"),len,5))
       return clickElement(loc.getUiElement("logoutCta"));
       return false;
    }

    public boolean tapOnLogout(){
       return clickElement(loc.getUiElement("logout"));
    }

    public boolean tapOnCancelLogout(){
       return clickElement(loc.getUiElement("cancelLogOut"));
    }

    public boolean getFamLogo(){
       if(scrollUntilElementIsVisible("xpath",loc.getUiSelector("famLogo","xpath"),len,6))
       return checkElementIsAvailable(loc.getUiElement("famLogo"));
       return false;
    }

    public boolean getMahalLogo(){
        if(scrollUntilElementIsVisible("xpath",loc.getUiSelector("mahalImg","xpath"),len,6))
            return checkElementIsAvailable(loc.getUiElement("mahalImg"));
        return false;
    }

    public boolean getCraftedWithText(){
       return checkElementIsAvailable(loc.getUiElements("craftedText").get(0));
    }

    public boolean getSocialMediaIntegration(){
       return checkElementIsAvailable(loc.getUiElement("facebook")) &&
               checkElementIsAvailable(loc.getUiElement("instagram")) &&
               checkElementIsAvailable(loc.getUiElement("snapchat"));
    }
}


