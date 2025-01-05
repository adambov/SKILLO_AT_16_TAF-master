package gui.post;

import com.AD.POM.*;
import gui.base.BaseTest;
import net.bytebuddy.build.Plugin;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class PostTests extends BaseTest {
    public static final String testUser = "Nasko10";
    public static final String testPassword = "Password123";
    public static final String caption = "Testing the create post caption";
    File postPicture = new File("src/test/resources/upload/n3Test.jpg");

    @Test(priority = 0)
    public void verifyUserCanCreatePost() throws InterruptedException{
        HomePage homePage = new HomePage(super.driver, log);

        log.info("Step 1: homepage is opened");
        homePage.openHomePage();

        log.info("Step 2: Click on Login");
        homePage.clickOnNavBarLogin();

        log.info("Step 3: User logs in");
        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithUSerAndPassword(testUser,testPassword);

        log.info("Step 4: User clicks on New Post in the navigation bar");
        homePage.clickOnNavBarNewPost();

        log.info("Step 5: User uploads picture");
        PostPage postPage = new PostPage(super.driver, log);
        postPage.uploadPicture(postPicture);

        log.info("Step 6: User provides post text/caption");
        postPage.providePostCaption(caption);

        log.info("Step 7: User sets post as private");
        postPage.clickPublicPrivateToggle();

        log.info("Step 8: User clicks create post button");
        postPage.clickCreatePostButton();

        log.info("Steps 9: Click on All post filter");
        ProfilePage profilePage = new ProfilePage(super.driver, log);
        profilePage.clickOnAllPostFilterBtn();

        log.info("Step 10: Verify if post are than before");
        boolean isMorePostShown = profilePage.getPostCount() > 0;
        Assert.assertTrue(isMorePostShown);
        homePage.clickPost(0);

        log.info("Step 11: Open the latest post");
        profilePage.openLastPost();

        log.info("Steps 12: Verify if post is private");
        PostModal postModal = new PostModal(super.driver, log);
        postModal.isPostPrivate();

        log.info("Step 13: Verify if post is visible");
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");

        log.info("Step 14: Verify if the user who created the post is the same with the username of the account");
        String postUserTxt = postModal.getPostUser();
        Assert.assertEquals(postUserTxt, testUser);

    }

    @Test(priority = 1, expectedExceptions = IndexOutOfBoundsException.class)
    public void verifyUserCanDeletePost() throws IndexOutOfBoundsException, InterruptedException {
        HomePage homePage = new HomePage(super.driver, log);
        LoginPage loginPage = new LoginPage(super.driver, log);
        ProfilePage profilePage = new ProfilePage(super.driver, log);
        int initialPostCount = profilePage.getPostCount();

        log.info("The user has navigated to the Login page.");
        loginPage.navigateToLoginPage();

        log.info("The user has logged in with username and password.");
        loginPage.loginWithUSerAndPassword(testUser, testPassword);

        log.info("The user has navigated to the Profile page.");
        homePage.clickOnNavBarProfile();

        log.info("Click on All post filter");
        profilePage.clickOnAllPostFilterBtn();
        int finalPostCount = profilePage.getPostCount();


        if (finalPostCount > 0) {
            profilePage.openLastPost();
            log.info("The user has clicked on the last post.");
        } else {
            log.error("No posts are available to click.");
            Assert.fail("Test failed: No posts available to delete.");
        }

        profilePage.ClickOnDeleteButton();
        log.info("The user has clicked on the Delete post button.");

        profilePage.ClickOnYesButton();
        log.info("The user has confirmed the deletion.");

        log.info("Verify deleted post message");
        profilePage.isDeletedMessageVisible();

        log.info("Confirm whether the number of posts is one fewer.");
            profilePage.clickOnAllPostFilterBtn();
            log.info("Final number of posts: " + finalPostCount);
            Assert.assertEquals(initialPostCount, finalPostCount, "The number of posts did not decrease by 1.");
    }
}
