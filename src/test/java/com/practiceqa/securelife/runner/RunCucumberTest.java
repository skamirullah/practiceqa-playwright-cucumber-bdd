package com.practiceqa.securelife.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * Entry point for `mvn test`. Scenarios run in parallel, each in its own browser context.
 * Pick scenarios by tag with `mvn test -Dcucumber.filter.tags="@smoke"`.
 */
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.practiceqa.securelife.steps",
    plugin = {
        "pretty",
        "html:target/cucumber-report.html",
        "json:target/cucumber-report.json",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    })
public class RunCucumberTest extends AbstractTestNGCucumberTests {

  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}
