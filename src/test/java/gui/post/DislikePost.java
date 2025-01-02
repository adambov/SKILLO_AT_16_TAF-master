package gui.post;

import com.AD.POM.ProfilePage;
import gui.base.BaseTest;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.io.File;

public class DislikePost extends BaseTest {
    public static final String testUser = "testingDemos";
    public static final String testPassword = "testing";
    public static final String caption = "Testing the create post caption";
    File postPicture = new File("src/test/resources/upload/n3Test.jpg");
    Logger log;

    @Test
    public void verifyUserCanDislikePost() {
        ProfilePage profilePage = new ProfilePage(super.driver, log);
        profilePage.navigateTo("posts/all");
    }
}
