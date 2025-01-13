package gui.login;

import com.AD.POM.HomePage;
import com.AD.POM.LoginPage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginHappyPathsTest extends BaseTest {

    private static final String LOGIN_FORM_TITLE = "Sign in";
    public static final String LOGIN_SUCCESSFUL_MSG = "Successful login!";

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {"Nasko10", "Password123"},
        };
    }

    @Test(dataProvider = "invalidCredentials")
    public void verifyLoginWithValidCredentials(String username, String password) {

        HomePage homePage = new HomePage(super.driver, log);

        homePage.openHomePage();
        log.info("STEP 1: Not logged in user has opened the Skillo HomePage.");

        boolean isHomePageLoaded = homePage.isURLLoaded(HomePage.HOME_PAGE_URL);
        Assert.assertTrue(isHomePageLoaded, "User is not on the correct home page.");
        log.info("STEP 2: Verified the user is on the home page ");

        boolean isShownNavBarLoginLink = homePage.isNavLoginShown();
        Assert.assertTrue(isShownNavBarLoginLink);
        log.info("STEP 3: Verified that the login link is presented ");

        homePage.clickOnNavBarLogin();
        log.info("STEP 4: The user has navigated to the login page via click on navigation bar login link");

        LoginPage loginPage = new LoginPage(super.driver,log);
        boolean isLoginPageLoaded = homePage.isURLLoaded(loginPage.LOGIN_PAGE);
        Assert.assertTrue(isLoginPageLoaded, "User is not on the login page.");
        log.info("STEP 5: The user is successfully on the login page");

        String actualLoginFormTitle = loginPage.getLoginPageFormTitle();
        Assert.assertEquals(actualLoginFormTitle,LOGIN_FORM_TITLE);
        log.info("STEP 6: Verified the login title");

        loginPage.loginWithUSerAndPassword(username, password);
        log.info("STEP 7: User has logged");

        Assert.assertFalse(loginPage.isRememberMeCheckboxSelected(), "Checkbox is selected by default!");
        loginPage.clickOnRememberMeCheckbox();
        Assert.assertTrue(loginPage.isRememberMeCheckboxSelected(), "Remember Me checkbox is not selected!");
        log.info("STEP 8: Clicked on checkbox");

        String actualLoginActionMSG = loginPage.getLoginActionMessage();
        Assert.assertEquals(actualLoginActionMSG,LOGIN_SUCCESSFUL_MSG);
        log.info("STEP 9: Verified success message after successful login");

        boolean isLogoutButtonVisible = homePage.isNavLogoutShown();
        Assert.assertTrue(isLogoutButtonVisible, "Logout button is visible on the page!");
        log.info("STEP 10: Verified that the logout button is visible and present on the page.");

        boolean isProfileButtonVisible = homePage.isNavProfileShown();
        Assert.assertTrue(isProfileButtonVisible, "Profile button is visible on the page!");
        log.info("STEP 11: Verified that the Profile button is visible and present on the page.");
    }
}
