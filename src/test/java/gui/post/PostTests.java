package gui.post;

import com.AD.POM.*;
import gui.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class PostTests extends BaseTest {

    public static final String testUser = "Nasko10";
    public static final String testPassword = "Password123";
    public static final String caption = "Testing the create post caption";
    File postPicture = new File("src/test/resources/upload/n3Test.jpg");

    @Test(priority = 0)
    public void verifyUserCanCreatePost() throws InterruptedException {
        HomePage homePage = new HomePage(super.driver, log);

        homePage.openHomePage();
        log.info("Step 1: homepage is opened");

        homePage.clickOnNavBarLogin();
        log.info("Step 2: Clicked on Login");

        LoginPage loginPage = new LoginPage(super.driver, log);
        loginPage.loginWithUSerAndPassword(testUser,testPassword);
        log.info("Step 3: User logged in");

        homePage.clickOnNavBarNewPost();
        log.info("Step 4: User clicked on New Post in the navigation bar");

        PostPage postPage = new PostPage(super.driver, log);
        postPage.uploadPicture(postPicture);
        log.info("Step 5: User uploaded picture");

        postPage.providePostCaption(caption);
        log.info("Step 6: User provided post text/caption");

        postPage.clickPublicPrivateToggle();
        log.info("Step 7: User has set post as private");

        postPage.clickCreatePostButton();
        log.info("Step 8: User clicked create post button");

        ProfilePage profilePage = new ProfilePage(super.driver, log);
        profilePage.clickOnAllPostFilterBtn();
        log.info("Steps 9: User clicked on All post filter");

        boolean isMorePostShown = profilePage.getPostCount() > 0;
        Assert.assertTrue(isMorePostShown);
        int lastPostIndex = profilePage.getLastPostIndex();
        profilePage.clickPost(lastPostIndex);
        log.info("Step 10: Verified if post are than before");

        PostModal postModal = new PostModal(super.driver, log);
        postModal.isPostPrivate();
        log.info("Steps 11: Verified if post is private");

        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        log.info("Step 12: Verified if post is visible");

        String postUserTxt = postModal.getPostUser();
        Assert.assertEquals(postUserTxt, testUser);
        log.info("Step 13: Verified if the user who created the post is the same with the username of the account");
    }

    @Test(priority = 1)
    public void verifyUserCanDeletePost() throws IndexOutOfBoundsException, InterruptedException  {
        HomePage homePage = new HomePage(super.driver, log);
        LoginPage loginPage = new LoginPage(super.driver, log);
        ProfilePage profilePage = new ProfilePage(super.driver, log);
        int initialPostCount = profilePage.getPostCount();

        loginPage.navigateToLoginPage();
        log.info("STEP 1: The user has navigated to the Login page.");

        loginPage.loginWithUSerAndPassword(testUser, testPassword);
        log.info("STEP 2: The user has logged with username and password.");

        homePage.clickOnNavBarProfile();
        log.info("STEP 3: The user has navigated to the Profile page.");

        profilePage.clickOnAllPostFilterBtn();
        int finalPostCount = profilePage.getPostCount();
        int lastPostIndex = profilePage.getLastPostIndex();
        log.info("STEP 4: Clicked on All post filter");

        try {
            profilePage.clickPost(lastPostIndex);
            log.info("STEP 5: The user has clicked on the last post.");
        } catch (IndexOutOfBoundsException e) {
            log.error("STEP 5: No posts are available to click.");
            throw new IndexOutOfBoundsException();
        }

        profilePage.ClickOnDeleteButton();
        log.info("STEP 6: The user has clicked on the Delete post button.");

        profilePage.ClickOnYesButton();
        log.info("STEP 7: The user has confirmed the deletion.");

        log.info("STEP 8: Verified deleted post message");
        profilePage.isDeletedMessageVisible();

        Assert.assertTrue(finalPostCount > initialPostCount, "The number of posts did not increase by 1.");
        log.info("STEP 9: Confirmed whether the number of posts is one fewer.");
        log.info("STEP 10: Initial number of posts: " + initialPostCount);
        log.info("STEP 11: Final number of posts: " + finalPostCount);
    }
}
