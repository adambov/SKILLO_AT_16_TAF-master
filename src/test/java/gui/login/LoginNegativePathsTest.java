package gui.login;

import com.AD.POM.HomePage;
import com.AD.POM.LoginPage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginNegativePathsTest extends BaseTest {

    private static final String LOGIN_FORM_TITLE = "Sign in";
    public static final String LOGIN_NOT_SUCCESSFUL_MSG = "Wrong username or password!";

    @Test
    public void verifyUserCannotLoginWithWrongUserName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(super.driver,log);

        log.info("STEP 1: Already registered user is landing Iskilo loinpage ");
        loginPage.navigateToLoginPage();

        log.info("Step 2: Verification that the user is on login page ");
        boolean isLoginPageLoaded = loginPage.isURLLoaded(loginPage.LOGIN_PAGE);
        Assert.assertTrue(isLoginPageLoaded, "User is not on the login page.");

        log.info("STEP 3: Verify the login title");
        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle,LOGIN_FORM_TITLE);

        log.info("STEP 7: Provide invalid username");
        loginPage.provideUserName("HarryPotter");

        log.info("STEP 8: Provide invalid password");
        loginPage.providePassword("Dumbeldor123");

        log.info("STEP 9: Click on checkbox");
        Assert.assertFalse(loginPage.isRememberMeCheckboxSelected(), "Checkbox is selected by default!");
        loginPage.clickOnRememberMeCheckbox();
        Assert.assertTrue(loginPage.isRememberMeCheckboxSelected(), "Remember Me checkbox is not selected!");


        log.info("STEP 10: Click on loginButton");
        loginPage.clickOnLoginButton();
        Thread.sleep(5555);

        log.info("STEP 11: Verify error message after failed login");
        String actualLoginActionMSG = loginPage.getLoginActionMessage();
        Assert.assertEquals(actualLoginActionMSG, LOGIN_NOT_SUCCESSFUL_MSG);
        //Assertion
        //  Login action message shows = ERROR text
        //If user cannot login - login button states the some =  Form visible
        // Login button from login Form visible
        // page ULR = some
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
