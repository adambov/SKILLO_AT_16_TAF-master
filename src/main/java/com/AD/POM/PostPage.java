package com.AD.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.File;

public class PostPage extends BasePage {
    final String POST_PAGE_URL = "http://training.skillo-bg.com:4200/posts/create";
    @FindBy(css = "img.image-preview")
    private WebElement image;
    @FindBy(css = "input.input-lg")
    private WebElement imageTextElement;
    @FindBy(css = ".file[type='file']")
    private WebElement uploadField;
    @FindBy(name = "caption")
    private WebElement captionElement;
    @FindBy(id = "create-post")
    private WebElement createPostButton;
    @FindBy(xpath = "/html/body/app-root/div[2]/app-create-post/div/div/div/form/div[2]/div[3]/span/label[2]")
    private WebElement privatePublicToggle;

    public PostPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    public boolean isImageVisible() {
        boolean isVisible = false;
        try {
            isVisible = wait.until(ExpectedConditions.visibilityOf(image)).isDisplayed();
            log.info("CONFIRMATION # The file is visible.");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.error("ERROR : The file is not visible");
            isVisible = false;
        }
        return isVisible;
    }

    public String getImageName() {
        String imageName = imageTextElement.getAttribute("placeholder");
        log.info("CONFIRMATION # The image name is: " + imageName);
        return imageName;
    }

    public void uploadPicture(File file) {
        isPresented(uploadField);
        uploadField.sendKeys(file.getAbsolutePath());
        log.info("CONFIRMATION # The file was successfully uploaded.");
    }

    public void providePostCaption(String caption) {
        wait.until(ExpectedConditions.visibilityOf(captionElement));
        captionElement.sendKeys(caption);
        log.info("CONFIRMATION # The user has provided caption text: " + caption);
    }

    public void clickCreatePostButton() {
        wait.until(ExpectedConditions.visibilityOf(createPostButton));
        createPostButton.click();
        log.info("CONFIRMATION # The user has clicked on the submit post button.");
    }

    public void clickPublicPrivateToggle() {
        wait.until(ExpectedConditions.visibilityOf(privatePublicToggle));
        privatePublicToggle.click();
    }
}