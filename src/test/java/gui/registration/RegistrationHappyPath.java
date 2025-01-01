package gui.registration;

import com.AD.POM.HomePage;
import com.AD.POM.RegistrationPage;
import gui.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.regData.RegistrationDataGenerator;

public class RegistrationHappyPath extends BaseTest {

    private String SIGN_UP_TITLE = "Sign up";
    private String REGISTRATION_SUCCESSFUL_MSG = "Successful register!";

    @Test
    public void verifyUserCanRegisterWithValidData() throws InterruptedException {
        RegistrationPage registrationPage = new RegistrationPage(super.driver,log);
        HomePage homePage = new HomePage(super.driver,log);

         String USERNAME = RegistrationDataGenerator.createUser() ;
         String EMAIL = RegistrationDataGenerator.createEmail();
         String DATE = RegistrationDataGenerator.createDate();
         String PASSWORD = RegistrationDataGenerator.createPassword(6);
         String PUBLIC_INFO = RegistrationDataGenerator.generateRandomPublicInfo(50);


        log.info("STEP 1: User is opening registration page");
        registrationPage.navigateToRegPage();

        log.info("STEP 2: Registration form title is correct - Sign up");
        registrationPage.getRegFormTitle();
        Assert.assertEquals(registrationPage.getRegFormTitle(), SIGN_UP_TITLE);

        log.info("STEP 3: Verify if Sign up button is inactive");
        registrationPage.signUnButtonState();

        log.info("STEP 4: Username is provided");
        registrationPage.provideUserName(USERNAME);

        log.info("STEP 5: Email is provided");
        registrationPage.provideEmail(EMAIL);

        log.info("STEP 6: BirthDate is provided);");
        registrationPage.provideBirthDate(DATE);

        log.info("STEP 7: Password is provided");
        registrationPage.providePassword(PASSWORD);

        log.info("STEP 8: Confirm Password is provided");
        registrationPage.provideConfirmPassword(PASSWORD);

        log.info("STEP 9: Public Info is provided");
        registrationPage.providePublicInfoText(PUBLIC_INFO);

        log.info("STEP 10: Sign up button is clicked");
        registrationPage.clickSignUpButton();


        log.info("STEP 11: Successful toast message is verified");
        String actualRegistrationMSG = registrationPage.getRegistrationActionMessage();
        Assert.assertEquals(actualRegistrationMSG,REGISTRATION_SUCCESSFUL_MSG);

        log.info("STEP 12: Verifying that the Profile button is visible and present on the page.");
        boolean isProfileButtonVisible = homePage.isNavProfileShown();
        Assert.assertTrue(isProfileButtonVisible, "Profile button is visible on the page!");

        log.info("STEP 13: Verifying that the Logout button is visible and present on the page.");
        boolean isLogoutButtonVisible = homePage.isNavLogoutShown();
        Assert.assertTrue(isLogoutButtonVisible, "Profile button is visible on the page!");

        Thread.sleep(5555);
    }
}
