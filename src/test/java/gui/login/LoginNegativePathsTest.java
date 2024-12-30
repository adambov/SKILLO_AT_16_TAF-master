package gui.login;

import com.AD.POM.HomePage;
import com.AD.POM.LoginPage;
import gui.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginNegativePathsTest extends BaseTest {

    private static final String LOGIN_FORM_TITLE = "Sign in";
    public static final String LOGIN_NOT_SUCCESSFUL_MSG = "Wrong username or password!";

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {"HarryPotter", "Dumbeldor123"},
                {" ", "Password123"},
                {"Nasko10", "Password9090"},
                {"Nasko10", " "},
                {" ", " "}
        };
    }

    @Test(dataProvider = "invalidCredentials")
    public void verifyUserCannotLoginWithWrongUserName(String username, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 1: Landing on Iskilo login page.");
        loginPage.navigateToLoginPage();

        log.info("STEP 2: Verify login page is loaded.");
        boolean isLoginPageLoaded = loginPage.isURLLoaded(loginPage.LOGIN_PAGE);
        Assert.assertTrue(isLoginPageLoaded, "User is not on the login page.");

        log.info("STEP 3: Verify the login page title.");
        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle, LOGIN_FORM_TITLE, "Login form title mismatch!");

        log.info("STEP 4: Use loginWithUserAndPassword method to login");
        loginPage.loginWithUSerAndPassword(username, password);

        log.info("STEP 5: Interact with 'Remember Me' checkbox.");
        Assert.assertFalse(loginPage.isRememberMeCheckboxSelected(), "Checkbox is selected by default!");
        loginPage.clickOnRememberMeCheckbox();
        Assert.assertTrue(loginPage.isRememberMeCheckboxSelected(), "Remember Me checkbox is not selected!");

        log.info("STEP 6: Verify error message after failed login.");
        String actualLoginActionMSG = loginPage.getLoginActionMessage();
        Assert.assertEquals(actualLoginActionMSG, LOGIN_NOT_SUCCESSFUL_MSG, "Error message mismatch!");

        log.info("STEP 7: Verify login submit button is still visible.");
        WebElement loginFormSubmitButton = loginPage.getLoginFormSubmitButton();
        Assert.assertTrue(loginFormSubmitButton.isDisplayed(), "Login form submit button is not visible!");

        Thread.sleep(5555);
    }
}
