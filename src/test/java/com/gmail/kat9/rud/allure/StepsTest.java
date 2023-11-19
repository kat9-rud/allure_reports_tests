package com.gmail.kat9.rud.allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String ISSUE_NAME = "Issue_created_to_test_allure_reports";

    @Test
    @DisplayName("Lambda steps approach")
    public void testLambdaStep() { //lambda steps approach
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () -> {
            open("https://github.com/");
        });

        step("Search for a repository " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });

        step("Click the repository link " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Open Issues tab", () -> {
            $("#issues-tab").click();
        });

        step("Check that an issue with the name " + ISSUE_NAME + " exists",
                () -> {
            $(withText(ISSUE_NAME)).should(exist);
        });
    }
    @Test
    @DisplayName("@Step annotation approach")
    public void testAnnotatedStep() { //steps approach if you need to reuse these steps in other tests
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithName(ISSUE_NAME);
    }
}
