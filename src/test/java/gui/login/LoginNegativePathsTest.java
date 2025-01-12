package gui.login;

import com.AD.POM.LoginPage;
import gui.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.regData.RegistrationDataGenerator;

public class LoginNegativePathsTest extends BaseTest {

    private static final String LOGIN_FORM_TITLE = "Sign in";
    public static final String LOGIN_NOT_SUCCESSFUL_MSG = "Wrong username or password!";

    String USERNAME = RegistrationDataGenerator.createUser();
    String PASSWORD = RegistrationDataGenerator.createPassword(6);

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {USERNAME, PASSWORD},
                {" ", "Password123"},
                {"Nasko197", "Password123"},
                {"Nasko10", "Password9090"},
                {"Nasko10", " "},
                {" ", " "}
        };
    }

    @Test(dataProvider = "invalidCredentials")
    public void verifyUserCannotLoginWithWrongUserNameOrPasswordOrInvalidEntries(String username, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(super.driver, log);

        loginPage.navigateToLoginPage();
        log.info("STEP 1: Landed on Iskillo login page.");

        boolean isLoginPageLoaded = loginPage.isURLLoaded(loginPage.LOGIN_PAGE);
        Assert.assertTrue(isLoginPageLoaded, "User is not on the login page.");
        log.info("STEP 2: Verified login page is loaded.");

        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle, LOGIN_FORM_TITLE, "Login form title mismatch!");
        log.info("STEP 3: Verified the login page title.");

        loginPage.loginWithUSerAndPassword(username, password);
        log.info("STEP 4: User is logged");

        Assert.assertFalse(loginPage.isRememberMeCheckboxSelected(), "Checkbox is selected by default!");
        log.info("STEP 5: 'Remember Me' checkbox is not checked.");
        loginPage.clickOnRememberMeCheckbox();
        Assert.assertTrue(loginPage.isRememberMeCheckboxSelected(), "Remember Me checkbox is not selected!");
        log.info("STEP 6: 'Remember Me' checkbox is checked.");

        String actualLoginActionMSG = loginPage.getLoginActionMessage();
        Assert.assertEquals(actualLoginActionMSG, LOGIN_NOT_SUCCESSFUL_MSG, "Error message mismatch!");
        log.info("STEP 7: Verified error message after failed login.");

        WebElement loginFormSubmitButton = loginPage.getLoginFormSubmitButton();
        Assert.assertTrue(loginFormSubmitButton.isDisplayed(), "Login form submit button is not visible!");
        log.info("STEP 8: Verified login submit button is still visible.");
    }
}
