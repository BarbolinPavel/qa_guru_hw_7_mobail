package tests;

import config.MobileConfig;
import drivers.BrowserstackDriver;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchTests extends TestBase{

    @Tag("android")
    @Test
    @DisplayName("Successful search")
    void successfulSearchTest() {
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Honda");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_container"))
                        .shouldHave(sizeGreaterThan(0)));
    }
    @Tag("ios")
    @Test
    @DisplayName("Mobile wiki page search test by IOS device")
    void loginTest() {
        step("Click button", () ->{
            $(id("Text Button")).click();
        });
        step("Write email", () ->{
            $(id("Text Input")).click();
            $(id("Text Input")).sendKeys("mail@gmail.com");
            $(id("Text Input")).pressEnter();
        });
        step("Check result", () ->{
            assertEquals("mail@gmail.com", $(id("Text Output")).getText());
        });
    }
}

