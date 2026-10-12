package com.practiceqa.securelife.steps;

import com.microsoft.playwright.Page;
import com.practiceqa.securelife.browser.BrowserManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.nio.file.Path;

public class Hooks {

  /** Every scenario gets its own browser context, so nothing leaks between scenarios. */
  @Before
  public void openPage() {
    BrowserManager.newPage();
  }

  /** A failed scenario keeps a screenshot in the report and a Playwright trace under target/traces. */
  @After
  public void closePage(Scenario scenario) {
    Path trace = null;
    if (scenario.isFailed() && BrowserManager.page() != null) {
      byte[] screenshot = BrowserManager.page().screenshot(new Page.ScreenshotOptions().setFullPage(true));
      scenario.attach(screenshot, "image/png", "Screenshot at failure");
      trace = Path.of("target", "traces", scenario.getName().replaceAll("[^A-Za-z0-9]+", "-") + ".zip");
    }
    BrowserManager.closePage(trace);
  }
}
