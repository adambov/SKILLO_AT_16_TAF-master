package gui.HomeTest;

import com.AD.POM.HomePage;
import com.AD.POM.LoginPage;
import com.AD.POM.ProfilePage;
import gui.base.BaseTest;
import org.testng.annotations.Test;

public class FollowAnotherUsers extends BaseTest {
    public static final String testUser = "Nasko10";
    public static final String testPassword = "Password123";




    @Test
    public void verifyUserCanFollowAnotherUser() throws InterruptedException {
        HomePage homePage = new HomePage(super.driver, log);
        log.info("User opens home page");
        homePage.openHomePage();

        log.info("Step 2: Click on Login");
        homePage.clickOnNavBarLogin();
        log.info("Login with valid user account");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithUSerAndPassword(testUser,testPassword);
        Thread.sleep(11111);

        log.info("User clicks on search field");
        homePage.clickOnSearchBar();

        log.info("Enter valid username in search bar");
        homePage.enterValidUsernameinSearchBar();
        Thread.sleep(11111);
        log.info("Search result is entered and shown and the same as expected");
        homePage.verifySearchResultIsShown();

        log.info("Click on username from search result");
        homePage.clickOnSearchResult();
        Thread.sleep(11111);


//        log.info("Verify correct search result is found");
//        log.info("User clicks on the correct search result");
//
//        log.info("Verify if All posts button is inactive");

//        log.info("Verify if Private posts button is inactive");
//        log.info("User clicks on follow button");
//        log.info("Verify in follow button changed to unfollow");
//        log.info("Verify if All posts button is active");
//        log.info("User clicks on All posts button");
//        log.info("Verify if All posts are displayed");
//        log.info("Verify if Private posts button is active");
//        log.info("User clicks on Private posts button");
//        log.info("Verify if Private posts are displayed");
    }
}
