package gui.post;

import com.AD.POM.*;
import gui.base.BaseTest;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class LikePost extends BaseTest {
    public static final String testUser = "Nasko10";
    public static final String testPassword = "Password123";
    public static final String caption = "Testing the create post caption";
    File postPicture = new File("src/test/resources/upload/n3Test.jpg");

    @Test(priority = 1)
    public void verifyUserCanLikePost() {


        HomePage homePage = new HomePage(super.driver, log);
        LoginPage loginPage = new LoginPage(super.driver, log);
        ProfilePage profilePage = new ProfilePage(super.driver, log);

        log.info("The user has navigated to the Login page.");
        loginPage.navigateToLoginPage();

        log.info("The user has logged in with username and password.");
        loginPage.loginWithUSerAndPassword(testUser, testPassword);


    }
}
