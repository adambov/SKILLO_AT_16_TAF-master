package gui.registration;

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

         String USERNAME = RegistrationDataGenerator.createUser() ;
         String EMAIL = RegistrationDataGenerator.createEmail();
         String DATE = RegistrationDataGenerator.createDate();
         String PASSWORD = RegistrationDataGenerator.createPassword(6);
         String PUBLIC_INFO = RegistrationDataGenerator.generateRandomPublicInfo(50);


        log.info("User is opening registration page");
        registrationPage.navigateToRegPage();

        log.info("Registration form title is correct - Sign up");
        registrationPage.getRegFormTitle();
        Assert.assertEquals(registrationPage.getRegFormTitle(), SIGN_UP_TITLE);

        log.info("Verify if Sign up button is inactive");
        registrationPage.signUnButtonState();

        log.info("Username is provided");
        registrationPage.provideUserName(USERNAME);

        log.info("Email is provided");
        registrationPage.provideEmail(EMAIL);

        log.info("BirthDate is provided);");
        registrationPage.provideBirthDate(DATE);

        log.info("Password is provided");
        registrationPage.providePassword(PASSWORD);

        log.info("Confirm Password is provided");
        registrationPage.provideConfirmPassword(PASSWORD);

        log.info("Public Info is provided");
        registrationPage.providePublicInfoText(PUBLIC_INFO);

        log.info("Sign up button is clicked");
        registrationPage.clickSignUpButton();


        log.info("Successful toast message is verified");
        String actualRegistrationMSG = registrationPage.getRegistrationActionMessage();
        Assert.assertEquals(actualRegistrationMSG,REGISTRATION_SUCCESSFUL_MSG);

        Thread.sleep(5555);
    }
}
