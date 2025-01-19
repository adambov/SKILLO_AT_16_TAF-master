package gui.usersTest;

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
    public void verifyUserCanFollowAnotherUser () {
        HomePage homePage = new HomePage(super.driver, log);

        homePage.openHomePage();
        log.info("STEP 1: User opened home page");

        homePage.clickOnNavBarLogin();
        log.info("STEP 2: Clicked on Login button");

        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithUSerAndPassword(testUser,testPassword);
        log.info("STEP 3: User is logged with a valid account");

        boolean isLogoutButtonVisible = homePage.isNavLogoutShown();
        Assert.assertTrue(isLogoutButtonVisible, "Logout button is visible on the page!");
        log.info("STEP 4: Verified that the logout button is visible and present on the page.");

        homePage.clickOnSearchBar();
        log.info("STEP 5: User clicked on search field");

        homePage.enterValidUsernameinSearchBar();
        log.info("STEP 6: Entered valid username in search bar");

        homePage.verifySearchResultIsShown();
        log.info("STEP 7: Search result is entered and shown as expected");

        homePage.clickOnSearchResult();
        log.info("STEP 8: User clicked on the correct search result");

        String expectedURL = "http://training.skillo-bg.com:4300/users/8868";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "EXPECTED PAGE NOT FOUND");
        log.info("STEP 9: User is on the correct Profile page");

        ProfilePage profilePage = new ProfilePage(super.driver,log);
        profilePage.verifySearchedUserIsNotFollowed();
        log.info("STEP 10: Verified if the searched user is not already followed and the follow button has follow text");

        profilePage.verifyIfAllBtnisDisabled();
        log.info("STEP 11: Verified if All posts button is inactive");

        profilePage.verifyIfPrivateBtnIsDisabled();
        log.info("STEP 12: Verified if Private posts button is inactive");

        profilePage.clickFollowBtn();
        log.info("STEP 13: User clicked on follow button");

        profilePage.verifySuccessfullFollow();
        log.info("STEP 14: User verified successful toast message");

        profilePage.verifyUnfollowTextBtn();
        log.info("STEP 15: Verified if follow button changed to unfollow");

        profilePage.verifyIfAllBtnisEnabled();
        log.info("STEP 16: Verified if All posts button is active");

        profilePage.clickOnAllPostFilterBtn();
        log.info("STEP 17: User clicked on All posts button");

        profilePage.verifyIfPrivateBtnisEnabled();
        log.info("STEP 18: Verified if Private posts button is active");
    }
}
