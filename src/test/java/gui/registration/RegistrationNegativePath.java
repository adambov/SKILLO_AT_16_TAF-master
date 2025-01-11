package gui.registration;

import com.AD.POM.RegistrationPage;
import com.github.javafaker.Faker;
import gui.base.BaseTest;
import org.testng.annotations.Test;
import utils.regData.RegistrationDataGenerator;


public class RegistrationNegativePath extends BaseTest {

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


    }

}
