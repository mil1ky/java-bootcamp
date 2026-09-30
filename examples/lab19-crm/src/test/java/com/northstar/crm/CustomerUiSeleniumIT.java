package com.northstar.crm;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerUiSeleniumIT {

    @LocalServerPort
    int port;

    @Test
    void aminaRowVisible() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("http://localhost:" + port + "/");

            WebDriverWait wait = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-testid='customer-list']")));

            WebElement amina = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("[data-testid='customer-row-CUS-1001']")));

            assertTrue(amina.getText().contains("Amina"));

        } finally {
            driver.quit();
        }
    }
}