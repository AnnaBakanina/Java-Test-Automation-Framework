package com.framework;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class OverviewTabTests extends BaseTestClass {

    String user = "AnnaBakanina";

    @BeforeEach
    void overviewTabSetup() {
        driver.get(BASE_URL + user);
    }

    @Test
    void userNameIsCorrectOnOverviewTab() {
        String actualUsername = driver.findElement(By.className("p-nickname")).getText();
        assertEquals(user, actualUsername);
    }

    @Test
    void repoLinkGoesToCorrectRepo() {
        String repo = "/typescript-s";
        WebElement actualRepoLink = driver.findElement(By.linkText(repo));
        actualRepoLink.click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(BASE_URL + user + repo, actualURL);
    }
}
