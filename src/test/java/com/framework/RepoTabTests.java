package com.framework;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepoTabTests extends BaseTestClass {

    @Test
    void repositoryCountIsCorrect() {
        driver.get("https://github.com/AnnaBakanina?tab=repositories");
        List<WebElement> repoList = driver.findElements(By.xpath("//div[@id='user-repositories-list']//li"));
        assertEquals(6, repoList.size());
    }
}
