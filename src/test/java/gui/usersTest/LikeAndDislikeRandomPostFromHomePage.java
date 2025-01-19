package gui.usersTest;

import com.AD.POM.HomePage;
import com.AD.POM.LoginPage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LikeAndDislikeRandomPostFromHomePage extends BaseTest {
    public static final String testUser = "Nasko10";
    public static final String testPassword = "Password123";

    @Test
    public void verifyUserCanLikeAndDislikeRandomPostFromHomePageAfterLogin() throws InterruptedException {
        HomePage homePage = new HomePage(super.driver, log);

        homePage.openHomePage();
        log.info("STEP 1: User opened home page");

        homePage.clickOnNavBarLogin();
        log.info("Step 2: User clicked on the Login button");

        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithUSerAndPassword(testUser,testPassword);
        homePage.waitForToastMessageToDisappear();
        log.info("Step 3: User has logged with valid user account");


        homePage.clickOnRandomPostOnHomePageAfterLogin();
        log.info("STEP 4: User clicked on random post from home page");

        homePage.verifyIfPostIsNotLiked();
        log.info("STEP 5: Verified that post is not already liked");

        homePage.waitForToastMessageToDisappear();
        homePage.clickLikeOnPostonHomePageAfterPostModalIsLoaded();
        log.info("STEP 6: User clicked like button on the Post modal");

        String actualLikeToastMSG = homePage.getToastMSGTextAfterLikeOrDislike();
        String expctedLikeToastMSG = "Post liked";
        Assert.assertEquals(actualLikeToastMSG, expctedLikeToastMSG);
        log.info("STEP 7: Verified taost message says Post is liked after post is like");

        homePage.waitForToastMessageToDisappear();
        homePage.clickLikeOnPostonHomePageAfterPostModalIsLoaded();
        log.info("STEP 8: User clicked again on like button to dislike the post");

        String actualDisikeToastMSG = homePage.getToastMSGTextAfterLikeOrDislike();
        String expctedDislikeToastMSG = "Post disliked";
        Assert.assertEquals(actualDisikeToastMSG, expctedDislikeToastMSG);
        log.info("STEP 9: Verifie if taost message after dislike says Post disliked");
    }
}
