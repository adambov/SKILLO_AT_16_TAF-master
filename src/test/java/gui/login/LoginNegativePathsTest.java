package gui.login;

import com.AD.POM.HomePage;
import com.AD.POM.LoginPage;
import gui.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginNegativePathsTest extends BaseTest {

    private static final String LOGIN_FORM_TITLE = "Sign in";
    public static final String LOGIN_NOT_SUCCESSFUL_MSG = "Wrong username or password!";

    @Test
    public void verifyUserCannotLoginWithWrongUserName() {
        LoginPage loginPage = new LoginPage(super.driver, log);

        log.info("STEP 1: Already registered user is landing on Iskilo login page.");
        loginPage.navigateToLoginPage();

        log.info("STEP 2: Verify the user is on login page.");
        boolean isLoginPageLoaded = loginPage.isURLLoaded(loginPage.LOGIN_PAGE);
        Assert.assertTrue(isLoginPageLoaded, "User is not on the login page.");

        log.info("STEP 3: Verify the login page title.");
        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle, LOGIN_FORM_TITLE, "Login form title mismatch!");

        log.info("STEP 4: Provide invalid username.");
        loginPage.provideUserName("HarryPotter");

        log.info("STEP 5: Provide invalid password.");
        loginPage.providePassword("Dumbeldor123");

        log.info("STEP 6: Interact with 'Remember Me' checkbox.");
        Assert.assertFalse(loginPage.isRememberMeCheckboxSelected(), "Checkbox is selected by default!");
        loginPage.clickOnRememberMeCheckbox();
        Assert.assertTrue(loginPage.isRememberMeCheckboxSelected(), "Remember Me checkbox is not selected!");

        log.info("STEP 7: Click on login button.");
        loginPage.clickOnLoginButton();

        log.info("STEP 8: Verify error message after failed login.");
        String actualLoginActionMSG = loginPage.getLoginActionMessage();
        Assert.assertEquals(actualLoginActionMSG, LOGIN_NOT_SUCCESSFUL_MSG, "Error message mismatch!");

        log.info("STEP 9: Verify login submit button is still visible.");
        WebElement loginFormSubmitButton = loginPage.getLoginFormSubmitButton();
        Assert.assertTrue(loginFormSubmitButton.isDisplayed(), "Login form submit button is not visible!");
    }

    @Test
    public void verifyUserCannotLoginWithEmptyUserName(){
        LoginPage loginPage = new LoginPage(super.driver,log);

        log.info("STEP 1: Already registered user is landing Iskilo loinpage");
        loginPage.navigateToLoginPage();
        //Verification that the user is on login page
        //Verification login form is presented

        loginPage.provideUserName(" ");

        loginPage.providePassword("testing");


        loginPage.clickOnLoginButton();
        //Assertion
        //  Login action message shows = ERROR text
        //If user cannot login - login button states the some =  Form visible
        // Login button from login Form visible
        // page ULR = some
    }

    @Test
    public void verifyUserCannotLoginWithWrongPassword(){
        LoginPage loginPage = new LoginPage(super.driver,log);
        loginPage.navigateToLoginPage();
        loginPage.provideUserName("testingDemo");
        loginPage.providePassword("WrongPassword");

    }

    @Test
    public void verifyUserCannotLoginWithEmptyPassword(){
        LoginPage loginPage = new LoginPage(super.driver,log);
        loginPage.navigateToLoginPage();
        loginPage.provideUserName("testingDemo");
        loginPage.providePassword(" ");

    }

    @Test
    public void verifyUserCannotLoginWithEmptyCredentialsData(){
        LoginPage loginPage = new LoginPage(super.driver,log);
        loginPage.navigateToLoginPage();
    }

}
