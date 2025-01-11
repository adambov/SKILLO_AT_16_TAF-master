package gui.registration;

import com.AD.POM.RegistrationPage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.regData.RegistrationDataGenerator;

import static utils.regData.RegistrationDataGenerator.*;


public class RegistrationNegativePath extends BaseTest {


    String USERNAME = RegistrationDataGenerator.createUser();
    String EMAIL = createEmail();
    String DATE = createDate();
    String PASSWORD = RegistrationDataGenerator.createPassword(6);
    String PUBLIC_INFO = RegistrationDataGenerator.generateRandomPublicInfo(50);

    String INVALID_USERNAME = createInvalidUsername();
    String INVALID_EMAIL = RegistrationDataGenerator.createInvalidEmail();
    String INVALID_DATE = RegistrationDataGenerator.createInvalidDate();
    String INVALID_PASSWORD = RegistrationDataGenerator.createInvalidPassword(3);

    @DataProvider(name = "invalidDataProvidedForEachFieldSeparately")
    public Object[][] provideInvalidData() {
        return new Object[][] {
                {INVALID_USERNAME, EMAIL, DATE, PASSWORD, PASSWORD, PUBLIC_INFO},
                {USERNAME, INVALID_EMAIL, DATE, PASSWORD, PASSWORD, PUBLIC_INFO},
                {USERNAME, EMAIL, INVALID_DATE, PASSWORD, PASSWORD, PUBLIC_INFO},
                {USERNAME, EMAIL, DATE, INVALID_PASSWORD, INVALID_PASSWORD, PUBLIC_INFO},
                {USERNAME, EMAIL, DATE, PASSWORD, PASSWORD, ""}
        };
    }

    @Test(dataProvider = "invalidDataProvidedForEachFieldSeparately")
    public void validateUserCannotRegisterWithEvenOneInvalidField(String username, String email, String date, String password, String confirmPassword, String publicInfo) throws InterruptedException {
        RegistrationPage registrationPage = new RegistrationPage(super.driver, log);
        log.info("Registration page is loaded");
        registrationPage.navigateToRegPage();
        log.info("Verify registration form title");
        registrationPage.getRegFormTitle();
        log.info("Verify if sign in button is disabled");
        registrationPage.signUnButtonState();
        Assert.assertFalse(registrationPage.signUnButtonState(), "Button is enabled and it has to be disabled");

        log.info("Provide username");
        registrationPage.provideUserName(username);

        log.info("Provide email");
        registrationPage.provideEmail(email);

        log.info("Provide birth date");
        registrationPage.provideBirthDate(date);

        log.info("Provide password");
        registrationPage.providePassword(password);

        log.info("Provide confirm password");
        registrationPage.providePassword(confirmPassword);

        log.info("Provide public info");
        registrationPage.providePublicInfoText(publicInfo);

        log.info("Verify if sign in button is disabled");
        Assert.assertFalse(registrationPage.signUnButtonState(), "Button is enabled and it has to be disabled");
    }

}
