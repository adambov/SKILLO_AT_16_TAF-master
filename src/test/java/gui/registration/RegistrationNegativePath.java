package gui.registration;

import com.AD.POM.RegistrationPage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.regData.RegistrationDataGenerator;


public class RegistrationNegativePath extends BaseTest {
    RegistrationPage registrationPage = new RegistrationPage(super.driver,log);

    String USERNAME = RegistrationDataGenerator.createUser();
    String EMAIL = RegistrationDataGenerator.createEmail();
    String DATE = RegistrationDataGenerator.createDate();
    String PASSWORD = RegistrationDataGenerator.createPassword(6);
    String PUBLIC_INFO = RegistrationDataGenerator.generateRandomPublicInfo(50);

    String INVALID_USERNAME = RegistrationDataGenerator.createInvalidUsername();
    String INVALID_EMAIL = RegistrationDataGenerator.createInvalidEmail();
    String INVALID_DATE = RegistrationDataGenerator.createInvalidDate();
    String INVALID_PASSWORD = RegistrationDataGenerator.createInvalidPassword(3);

    @Test
    public void testInvalidUsername() throws InterruptedException {

        log.info("Registration page is loaded");
        registrationPage.navigateToRegPage();
        log.info("Verify registration form title");
        registrationPage.getRegFormTitle();
        log.info("Verify if sign in button is enabled");
        registrationPage.signUnButtonState();
        log.info("Provide invalid username");
        registrationPage.provideUserName(INVALID_USERNAME);
        log.info("Provide valid mail");
        registrationPage.provideEmail(EMAIL);
        log.info("Provide valid date");
        registrationPage.provideBirthDate(DATE);
        log.info("Provide Valid password");
        registrationPage.providePassword(PASSWORD);
        log.info("Provide valid confirm password");
        registrationPage.providePassword(PASSWORD);
        log.info("Provide valid public info");
        registrationPage.providePublicInfoText(PUBLIC_INFO);
        log.info("Verify if Sing in button is disabled");
        Assert.assertFalse(registrationPage.signUnButtonState(), "Button is enabled and it has to be disabled");
    }

    @Test
    public void testInvalidEmail() throws InterruptedException {

        log.info("Registration page is loaded");
        registrationPage.navigateToRegPage();
        log.info("Verify registration form title");
        registrationPage.getRegFormTitle();
        log.info("Verify if sign in button is enabled");
        registrationPage.signUnButtonState();
        log.info("Provide valid username");
        registrationPage.provideUserName(USERNAME);
        log.info("Provide invalid mail");
        registrationPage.provideEmail(INVALID_EMAIL);
        log.info("Provide valid date");
        registrationPage.provideBirthDate(DATE);
        log.info("Provide Valid password");
        registrationPage.providePassword(PASSWORD);
        log.info("Provide valid confirm password");
        registrationPage.providePassword(PASSWORD);
        log.info("Provide valid public info");
        registrationPage.providePublicInfoText(PUBLIC_INFO);
        log.info("Verify if Sing in button is disabled");
        Assert.assertFalse(registrationPage.signUnButtonState(), "Button is enabled and it has to be disabled");
    }

    @Test
    public void testInvalidDate() throws InterruptedException {

        log.info("Registration page is loaded");
        registrationPage.navigateToRegPage();
        log.info("Verify registration form title");
        registrationPage.getRegFormTitle();
        log.info("Verify if sign in button is enabled");
        registrationPage.signUnButtonState();
        log.info("Provide valid username");
        registrationPage.provideUserName(USERNAME);
        log.info("Provide valid mail");
        registrationPage.provideEmail(EMAIL);
        log.info("Provide invalid date");
        registrationPage.provideBirthDate(INVALID_DATE);
        log.info("Provide Valid password");
        registrationPage.providePassword(PASSWORD);
        log.info("Provide valid confirm password");
        registrationPage.providePassword(PASSWORD);
        log.info("Provide valid public info");
        registrationPage.providePublicInfoText(PUBLIC_INFO);
        log.info("Verify if Sing in button is disabled");
        Assert.assertFalse(registrationPage.signUnButtonState(), "Button is enabled and it has to be disabled");
    }

    @Test
    public void testInvalidPassword() throws InterruptedException {

        log.info("Registration page is loaded");
        registrationPage.navigateToRegPage();
        log.info("Verify registration form title");
        registrationPage.getRegFormTitle();
        log.info("Verify if sign in button is enabled");
        registrationPage.signUnButtonState();
        log.info("Provide validusername");
        registrationPage.provideUserName(USERNAME);
        log.info("Provide valid mail");
        registrationPage.provideEmail(EMAIL);
        log.info("Provide valid date");
        registrationPage.provideBirthDate(DATE);
        log.info("Provide invalid password");
        registrationPage.providePassword(INVALID_PASSWORD);
        log.info("Provide invalid confirm password");
        registrationPage.providePassword(INVALID_PASSWORD);
        log.info("Provide valid public info");
        registrationPage.providePublicInfoText(PUBLIC_INFO);
        log.info("Verify if Sing in button is disabled");
        Assert.assertFalse(registrationPage.signUnButtonState(), "Button is enabled and it has to be disabled");
    }

    @Test
    public void testEmptyPublicInfo() throws InterruptedException {

        log.info("Registration page is loaded");
        registrationPage.navigateToRegPage();
        log.info("Verify registration form title");
        registrationPage.getRegFormTitle();
        log.info("Verify if sign in button is enabled");
        registrationPage.signUnButtonState();
        log.info("Provide validusername");
        registrationPage.provideUserName(USERNAME);
        log.info("Provide valid mail");
        registrationPage.provideEmail(EMAIL);
        log.info("Provide valid date");
        registrationPage.provideBirthDate(DATE);
        log.info("Provide Valid password");
        registrationPage.providePassword(PASSWORD);
        log.info("Provide valid confirm password");
        registrationPage.providePassword(PASSWORD);
        log.info("Public info is left empty");
        log.info("Verify if Sing in button is disabled");
        Assert.assertFalse(registrationPage.signUnButtonState(), "Button is enabled and it has to be disabled");
    }

}
