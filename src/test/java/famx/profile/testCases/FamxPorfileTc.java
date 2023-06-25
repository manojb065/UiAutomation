package famx.profile.testCases;

import com.fampay.automation.pageObject.famX.profile.ProfileEvents;
import com.fampay.automation.util.test.AbstractTestBase;
import com.fampay.automation.util.helper.TestDataUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class FamxPorfileTc extends AbstractTestBase {

    ProfileEvents profile ;
    @BeforeTest
    public void initObject(){
        profile = new ProfileEvents("src/main/java/com/fampay/automation/pageobject/famX/profile/ProfileLocator.yaml");
    }

    @Test(priority = 0)
    public void checkProfileScreenScrollable(){
        Assert.assertTrue(profile.isProfileScrollable());
    }
    @Test(priority = 1)
    public void checkingUserName(){
        Assert.assertEquals(profile.getUserName(), TestDataUtil.getUserData("name"));
    }

    @Test(priority = 2,testName = "check if Dob and School name scrollable")
    public void checkingUserDobAndSchoolNameSection(){
        Assert.assertTrue(profile.isUserDobAndSchoolScrollable());
    }

    @Test(priority = 3)
    public void checkingUserDob(){
        Assert.assertEquals(profile.getUserDob(),TestDataUtil.getUserData("usrDobInProfile"));
    }

    @Test(priority = 4)
    public void checkingIfUserSchoolNameHcIsClickable(){
        Assert.assertTrue(profile.isUserSchoolNameHcClickable());
        String schoolName = profile.getUserSchoolName();
        if(!schoolName.equals("Add your school"))
            Assert.assertEquals(profile.getUserSchoolName(),TestDataUtil.getUserData("usrSchoolName"));
        else{
            Assert.assertTrue(profile.tapOnAddSchoolHc());
            Assert.assertTrue(profile.tapOnBackCta());
        }
    }

    @Test(priority = 5)
    public void clickingOnEditProfileCta(){
        Assert.assertTrue(profile.tapOnEditUsrProfileCta());
    }

    @Test(priority = 6)
    public void editingUsrProfilePic(){
      Assert.assertTrue(profile.tapOnEditUsrProfilePic());
      String tittle = profile.getChangeProfilePicTittle();
      if(tittle.equals("Change profile picture")){
       Assert.assertTrue(profile.uploadFromCameraOpt(),"upload from camera option is not available");
       Assert.assertTrue(profile.uploadFromGalleryOpt(),"upload from gallery option is not available");
       Assert.assertTrue(profile.removeUsrProfilePic(),"remove user pic not available");
      }else{
          Assert.assertTrue(profile.uploadFromCameraOpt(),"upload from camera option is not available");
          Assert.assertTrue(profile.uploadFromGalleryOpt(),"upload from gallery option is not available");
      }
        profile.navBack();
    }

    @Test(priority = 7)
    public void verifyingUserProfileName(){
        Assert.assertEquals(profile.getUserProfileName(),TestDataUtil.getUserData("name"));
    }

    @Test(priority = 8)
    public void verifyingEditUserProfileNameBottomSheet(){
    Assert.assertTrue(profile.tapOnEditUserProfileName());
    Assert.assertEquals(profile.getUserNameBottomSheetTittle(),"Enter your username");
    }

    @Test(priority = 9)
    public void verifyingInvalidUserName(){
        Assert.assertTrue(profile.editUserName("leoki"));
        Assert.assertTrue(profile.tapOnConfirmCta());
        Assert.assertEquals(profile.getInvalidUserNameMessg(),"Username length must be between 6 and 40 characters");
        softAssert.assertTrue(profile.tapOnClearUserNameCta());
        Assert.assertTrue(profile.editUserName("@randomString"));
        Assert.assertTrue(profile.tapOnConfirmCta());
        softAssert.assertEquals(profile.getInvalidUserNameMessg(),"Username can only contain only letters, numbers, and . - or _ characters");
    }

    @Test(priority = 10)
    public void verifyingValidUsername(){
        softAssert.assertTrue(profile.tapOnClearUserNameCta());
        Assert.assertTrue(profile.editUserName(TestDataUtil.getUserData("userName")));
        Assert.assertTrue(profile.tapOnConfirmCta());
        Assert.assertEquals(profile.getEditedUserProfileName(),TestDataUtil.getUserData("userName"));
    }

    @Test(priority = 11)
    public void verifyingEditingUsrName(){
        Assert.assertTrue(profile.isEditUsrNameDisabled());
        Assert.assertEquals(profile.getUserNameInProfileEdit(),TestDataUtil.getUserData("name"));
    }

    @Test(priority = 12)
    public void verifyingEditingUsrPhoneNo(){
        Assert.assertTrue(profile.isEditPhoneNoDisabled());
        Assert.assertEquals(profile.getUserPhoneNo(),TestDataUtil.getUserData("userPhoneNo"));
    }

    @Test(priority = 13)
    public void  verifyingUserEmailField(){
        Assert.assertTrue(profile.enterUserEmail("hi"));
        softAssert.assertEquals(profile.getInvalidUserEmailErrorText(),"Enter a valid email");
        System.out.println();
        Assert.assertTrue(profile.enterUserEmail(TestDataUtil.getUserData("email")));
    }

    @Test(priority = 14)
    public void verifyingEditUserBioField(){
        Assert.assertTrue(profile.enterUserBio("hi"));
        softAssert.assertEquals(profile.getInvalidUserBioErrorText(),"Bio should be 6 - 140 characters long");
        Assert.assertTrue(profile.enterUserBio(TestDataUtil.getUserData("newBio")));
    }

    @Test(priority = 15)
    public void verifyingUsrSchoolNameField(){
        Assert.assertTrue(profile.enterUserSchoolName(TestDataUtil.getUserData("newSchoolName")));
    }

    @Test(priority = 16)
    public void verifyingPersonalSectionUsrDob(){
        Assert.assertEquals(profile.getPersonalSectionUserDob(),TestDataUtil.getUserData("usrDobInPersonal"));
    }

    @Test(priority = 17)
    public void verifyingUserGender(){
        String usrGender = profile.getUserGender();
        softAssert.assertEquals(usrGender,TestDataUtil.getUserData("userGender"));
        Assert.assertTrue(profile.tapOnGenderDropDown());
        softAssert.assertTrue(profile.isGenderOptionSelected(usrGender));
        Assert.assertTrue(profile.tapOnConfirmUserGender());
    }

    @Test(priority = 18)
    public void verifyingInstaInputField(){
        Assert.assertTrue(profile.enterUsrInstagram(TestDataUtil.getUserData("instaId")));
    }

    @Test(priority = 19)
    public void verifyingSnapChatInputField(){
        Assert.assertTrue(profile.enterUsrSnapchat(TestDataUtil.getUserData("snapId")));
    }

    @Test(priority = 20)
    public void verifyingSaveCta(){
       Assert.assertTrue(profile.tapOnSaveCta());
    }
    @Test(priority = 21)
    public void checkingUserBio(){
        Assert.assertEquals(profile.getUserBio(),TestDataUtil.getUserData("newBio"));
    }

    @Test(priority = 22)
    public void checkingUserSchoolName(){
        Assert.assertEquals(profile.getUserSchoolName(),TestDataUtil.getUserData("newSchoolName"));
    }
    @DataProvider(name = "hcData")
    private Iterator<Object[]> data(){
        ArrayList<Object [] > dat = new ArrayList<>();
        dat.add(new String[]{"FAQs and Support"});
        dat.add(new String[]{"DO’S and DON’TS"});
        dat.add(new String[]{"Privacy settings"});
        dat.add(new String[]{"T&Cs and policies"});
        dat.add(new String[]{"Alert settings"});
        dat.add(new String[]{"Link social media accounts"});

        return dat.iterator();
    }
    @Test(priority = 23,dataProvider = "hcData")
    public void checkingHcInProfileScreen(String hcTittle){
        Assert.assertTrue(profile.tapOnProfileScreenHc(hcTittle));
        if(hcTittle.equals("FAQs and Support")){

        }
        profile.navBack();
    }


  @Test(priority = 24)
    public  void checkBottomScreen(){
      softAssert.assertTrue(profile.getMahalLogo());
        softAssert.assertTrue(profile.getFamLogo());
        softAssert.assertTrue(profile.getSocialMediaIntegration());
        softAssert.assertTrue(profile.getCraftedWithText());
  }

    @Test(priority = 25)
    public void checkCancelLogout(){
        Assert.assertTrue(profile.tapOnLogoutCta());
        Assert.assertTrue(profile.tapOnCancelLogout());
    }

    @Test(priority = 26)
    public void checkLogout(){
        Assert.assertTrue(profile.tapOnLogoutCta());
        Assert.assertTrue(profile.tapOnLogout());
    }
}
