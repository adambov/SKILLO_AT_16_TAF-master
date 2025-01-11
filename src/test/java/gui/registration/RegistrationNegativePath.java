package gui.registration;

import com.AD.POM.RegistrationPage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.regData.RegistrationDataGenerator;


public class RegistrationNegativePath extends BaseTest {

    String USERNAME = RegistrationDataGenerator.createUser();
    String EMAIL = RegistrationDataGenerator.createEmail();
    String DATE = RegistrationDataGenerator.createDate();
    String PASSWORD = RegistrationDataGenerator.createPassword(6);
    String PUBLIC_INFO = RegistrationDataGenerator.generateRandomPublicInfo(50);

    String INVALID_USERNAME = RegistrationDataGenerator.createInvalidUsername();
    String INVALID_EMAIL = RegistrationDataGenerator.createInvalidEmail();
    String INVALID_DATE = RegistrationDataGenerator.createInvalidDate();

    @Test
    public void testInvalidUsername() throws InterruptedException {
        RegistrationPage registrationPage = new RegistrationPage(super.driver,log);
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
        log.info("Provide Valid password");
        registrationPage.providePassword(PASSWORD);
        log.info("Provide valid confirm password");
        registrationPage.providePassword(PASSWORD);
        log.info("Provide valid public info");
        registrationPage.providePublicInfoText(PUBLIC_INFO);
        log.info("Verify if Sing in button is disabled");
        Assert.assertFalse(registrationPage.signUnButtonState(), "Button is enabled and it has to be disabled");
    }

}
