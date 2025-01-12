package gui.usersTest;

import com.AD.POM.BasePage;
import com.AD.POM.HomePage;
import com.AD.POM.LoginPage;
import com.AD.POM.ProfilePage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FollowAnotherUser extends BaseTest {
    public static final String testUser = "Nasko10";
    public static final String testPassword = "Password123";

    @Test
    public void verifyUserCanFollowAnotherUser () throws InterruptedException {
        BasePage basePage = new BasePage(super.driver, log);
        HomePage homePage = new HomePage(super.driver, log);

        homePage.openHomePage();
        log.info("STEP 1: User opened home page");

        homePage.clickOnNavBarLogin();
        log.info("STEP 2: Clicked on Login button");

        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithUSerAndPassword(testUser,testPassword);
        log.info("STEP 3: User is logged with a valid account");

        Thread.sleep(11111);
        homePage.clickOnSearchBar();
        log.info("STEP 4: User clicked on search field");

        homePage.enterValidUsernameinSearchBar();
        log.info("STEP 4: Entered valid username in search bar");

        Thread.sleep(11111);
        homePage.verifySearchResultIsShown();
        log.info("STEP 5: Search result is entered and shown as expected");


        homePage.clickOnSearchResult();
        log.info("STEP 6: User clicked on the correct search result");

        Thread.sleep(11111);
        String expectedURL = "http://training.skillo-bg.com:4300/users/8868";
        basePage.waitPageTobeFullyLoaded();
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "EXPECTED PAGE NOT FOUND");
        log.info("STEP 7: User is on the correct Profile page");

        ProfilePage profilePage = new ProfilePage(super.driver,log);
        profilePage.verifySearchedUserIsNotFollowed();
        log.info("STEP 8: Verified if the searched user is not already followed and the follow button has follow text");

        Thread.sleep(22222);
        profilePage.verifyIfAllBtnisDisabled();
        log.info("STEP 9: Verified if All posts button is inactive");

        profilePage.verifyIfPrivateBtnIsDisabled();
        log.info("STEP 9: Verified if Private posts button is inactive");

        profilePage.clickFollowBtn();
        log.info("STEP 10: User clicked on follow button");

        profilePage.verifySuccessfullFollow();
        log.info("STEP 11: User verified successful toast message");

        Thread.sleep(22222);
        profilePage.verifyUnfollowTextBtn();
        log.info("STEP 12: Verified if follow button changed to unfollow");

        profilePage.verifyIfAllBtnisEnabled();
        log.info("STEP 13: Verified if All posts button is active");

        profilePage.clickOnAllPostFilterBtn();
        log.info("STEP 14: User clicked on All posts button");

        profilePage.verifyIfPrivateBtnisEnabled();
        log.info("STEP 14: Verified if Private posts button is active");
    }
}
