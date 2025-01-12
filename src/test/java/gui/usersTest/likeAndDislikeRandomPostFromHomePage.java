package gui.usersTest;

import com.AD.POM.BasePage;
import com.AD.POM.HomePage;
import com.AD.POM.LoginPage;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class likeAndDislikeRandomPostFromHomePage extends BaseTest{
    public static final String testUser = "Nasko10";
    public static final String testPassword = "Password123";

    @Test
    public void verifyUserCanLikeAndDislaikeRandomPostFromHomePageAfterLogin() throws InterruptedException {
        BasePage basePage = new BasePage(super.driver, log);
        HomePage homePage = new HomePage(super.driver, log);

        log.info("User opens home page");
        homePage.openHomePage();

        log.info("Step 2: Click on Login");
        homePage.clickOnNavBarLogin();
        log.info("Login with valid user account");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithUSerAndPassword(testUser,testPassword);
        log.info("User clicks on last post from home page");
        Thread.sleep(1111);
        homePage.clickOnLastPostOnHomePageAfterLogin();
        log.info("Verify if post is not already liked");
        homePage.verifyIfPostIsNotLiked();
        log.info("User clicks like button on the Post modal");
        homePage.clickLikeOnLastPostonHomePageAfterPostModalIsLoaded();
        log.info("Verify if taost message after like says Post liked");
        String actualLikeToastMSG = homePage.getToatMSGTextAfterLikeOrDislike();
        String expctedLikeToastMSG = "Post liked";
        Assert.assertEquals(actualLikeToastMSG, expctedLikeToastMSG);
        Thread.sleep(11111);
        log.info("User clicks again on like button to dislike the post");
        homePage.clickLikeOnLastPostonHomePageAfterPostModalIsLoaded();
        log.info("Verify if taost message after dislike says Post disliked");
        String actualDisikeToastMSG = homePage.getToatMSGTextAfterLikeOrDislike();
        String expctedDislikeToastMSG = "Post disliked";
        Assert.assertEquals(actualDisikeToastMSG, expctedDislikeToastMSG);
    }
}
