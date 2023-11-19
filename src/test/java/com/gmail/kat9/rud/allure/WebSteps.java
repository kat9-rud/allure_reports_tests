package com.gmail.kat9.rud.allure;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Open main page")
    public void openMainPage() {
        open("https://github.com/");
    }
    @Step("Search for a repository {repo}")
    public void searchForRepository(String repo) {
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").submit();
    }

    @Step("Click the repository link {repo}")
    public void clickRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Open Issues tab")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }
    @Step("Check that an issue with the name {name}")
    public void shouldSeeIssueWithName(String name) {
        $(withText(name)).should(exist);
    }
}

