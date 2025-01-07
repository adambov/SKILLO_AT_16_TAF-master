package gui.registration;

import com.AD.POM.RegistrationPage;
import gui.base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class RegistrationNegativePath extends BaseTest {

    @Test
    public void testEmptyFields() throws InterruptedException {
        RegistrationPage registrationPage = new RegistrationPage(super.driver,log);
        log.info("Registration page is loaded");
        registrationPage.navigateToRegPage();
        log.info("Verify registration form title");
        registrationPage.getRegFormTitle();
        log.info("Verify if sign in button is enabled");
        registrationPage.signUnButtonState();
    }

}
