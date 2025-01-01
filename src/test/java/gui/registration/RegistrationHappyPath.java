package gui.registration;

import com.AD.POM.RegistrationPage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.regData.RegistrationDataGenerator;

public class RegistrationHappyPath extends BaseTest {

    @Test
    public void verifyUserCanRegisterWithValidData() throws InterruptedException {
        RegistrationPage registrationPage = new RegistrationPage(super.driver,log);

         String USERNAME = RegistrationDataGenerator.createUser() ;
         String EMAIL = RegistrationDataGenerator.createEmail();
         String DATE = RegistrationDataGenerator.createDate();
         String PASSWORD = RegistrationDataGenerator.createPassword(6);
         String PUBLIC_INFO = RegistrationDataGenerator.generateRandomPublicInfo(50);

        registrationPage.navigateToRegPage();

        registrationPage.provideUserName(USERNAME);
        registrationPage.provideEmail(EMAIL);
        registrationPage.provideBirthDate(DATE);
        registrationPage.providePassword(PASSWORD);
        registrationPage.provideConfirmPassword(PASSWORD);
        registrationPage.providePublicInfoText(PUBLIC_INFO);
        registrationPage.clickSignInButton();

        Thread.sleep(5555);
    }
}
