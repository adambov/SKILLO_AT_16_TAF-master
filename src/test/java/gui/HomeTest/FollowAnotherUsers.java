package gui.HomeTest;

import com.AD.POM.BasePage;
import com.AD.POM.HomePage;
import com.AD.POM.LoginPage;
import com.AD.POM.ProfilePage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FollowAnotherUsers extends BaseTest {
    public static final String testUser = "Nasko10";
    public static final String testPassword = "Password123";




    @Test
    public void verifyUserCanFollowAnotherUser () throws InterruptedException {
        BasePage basePage = new BasePage(super.driver, log);
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
        log.info("Search result is entered and shown as expected");
        homePage.verifySearchResultIsShown();

        log.info("User clicks on the correct search result");
        homePage.clickOnSearchResult();
        Thread.sleep(11111);

        log.info("Verify if Profile page is opened");
        String expectedURL = "http://training.skillo-bg.com:4300/users/8868";
        basePage.waitPageTobeFullyLoaded();
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "EXPECTED PAGE NOT FOUND");
        log.info("User is on the correct Profile page");

        log.info("Verify if the searched user is not already followed and the follow button has follow text");
        ProfilePage profilePage = new ProfilePage(super.driver,log);
        profilePage.verifySearchedUserIsNotFollowed();
        Thread.sleep(22222);


        log.info("Verify if All posts button is inactive");
        profilePage.verifyIfAllBtnisDisabled();

        log.info("Verify if Private posts button is inactive");
        profilePage.verifyIfPrivateBtnIsDisabled();

        log.info("User clicks on follow button");
        profilePage.clickFollowBtn();

        log.info("User verifies successful toast message");
        profilePage.verifySuccessfullFollow();
        Thread.sleep(22222);

        log.info("Verify in follow button changed to unfollow");
        profilePage.verifyUnfollowTextBtn();

        log.info("Verify if All posts button is active");
        profilePage.verifyIfAllBtnisEnabled();

        log.info("User clicks on All posts button");
        profilePage.clickOnAllPostFilterBtn();

        log.info("Verify if Private posts button is active");
        profilePage.verifyIfPrivateBtnisEnabled();

    }
}
