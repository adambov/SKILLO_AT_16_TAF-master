package gui.login;

import com.AD.POM.HomePage;
import com.AD.POM.LoginPage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginHappyPathsTest extends BaseTest {

    private static final String LOGIN_FORM_TITLE = "Sign in";
    public static final String LOGIN_SUCCESSFUL_MSG = "Successful login!";
    public static final String LOGIN_NOT_SUCCESSFUL_MSG = "Wrong username or password!";


    @Test
    public void verifyLoginWithValidCredentials() throws InterruptedException {

        HomePage homePage = new HomePage(super.driver, log);

        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");
        homePage.openHomePage();

        log.info("STEP 2.Verify the user is on the home page ");
        boolean isHomePageLoaded = homePage.isURLLoaded(HomePage.HOME_PAGE_URL);
        Assert.assertTrue(isHomePageLoaded, "User is not on the correct home page.");

        log.info("STEP 3. Verify that the login link is presented ");
        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);

        log.info("STEP 4: The user is navigating to the login page via click on navigation bar login link");
        homePage.clickOnNavBarLogin();

        log.info("STEP 5: The user is successfully on the login page");
        LoginPage loginPage = new LoginPage(super.driver,log);

        log.info("STEP 6: Verify the login title");
        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle,LOGIN_FORM_TITLE);

        log.info("STEP 7: Verify checkbox");
        loginPage.clickOnRememberMeCheckbox();

        log.info("STEP 8: Verify Rememeber me sing");

        log.info("STEP 9: Verify Not a member sing");

        log.info("STEP 10: Verify Register button");

        log.info("STEP 11: Register button");

        log.info("STEP 12: Provide valid username");
        loginPage.provideUserName("Nasko10");

        log.info("STEP 13: Provide valid password");
        loginPage.providePassword("Password123");

        log.info("STEP 14: Click on checkbox");

        log.info("STEP 15: Click on loginButton");
        loginPage.clickOnLoginButton();

        log.info("STEP 16: Verify success message after successful login");
        String actualLoginActionMSG = loginPage.getLoginActionMessage();
        Assert.assertEquals(actualLoginActionMSG,LOGIN_SUCCESSFUL_MSG);

        log.info("STEP 17: Verifying that the logout button is visible and present on the page.");
        boolean isLogoutButtonVisible = homePage.isNavLogoutShown();
        Assert.assertTrue(isLogoutButtonVisible, "Logout button is visible on the page!");

        log.info("STEP 18: Verifying that the Profile button is visible and present on the page.");
        boolean isProfileButtonVisible = homePage.isNavProfileShown();
        Assert.assertTrue(isProfileButtonVisible, "Profile button is visible on the page!");

        Thread.sleep(5555);
    }
}
