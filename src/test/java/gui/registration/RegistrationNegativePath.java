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
    public void validateUserCannotRegisterWithEvenOneInvalidField(String username, String email, String date,
                                                                  String password, String confirmPassword, String publicInfo) throws InterruptedException {
        RegistrationPage registrationPage = new RegistrationPage(super.driver, log);

        registrationPage.navigateToRegPage();
        log.info("STEP 1: Registration page is loaded");

        registrationPage.getRegFormTitle();
        log.info("STEP 2: Verified registration form title");

        registrationPage.signUnButtonState();
        Assert.assertFalse(registrationPage.signUnButtonState(), "Button is enabled and it has to be disabled");
        log.info("STEP 3: Verifid if sign in button is disabled");

        registrationPage.provideUserName(username);
        log.info("STEP 4: Provided username");

        registrationPage.provideEmail(email);
        log.info("STEP 5: Provided email");

        registrationPage.provideBirthDate(date);
        log.info("STEP 6: Provided birth date");

        registrationPage.providePassword(password);
        log.info("STEP 7: Provided password");

        registrationPage.providePassword(confirmPassword);
        log.info("STEP 8: Provided confirm password");

        registrationPage.providePublicInfoText(publicInfo);
        log.info("STEP 9: Provided public info");

        Assert.assertFalse(registrationPage.signUnButtonState(), "Button is enabled and it has to be disabled");
        log.info("STEP 10: Verified if sign in button is disabled");
    }
}
