package com.practiceqa.securelife.runner;

import com.practiceqa.securelife.browser.BrowserManager;
import org.testng.annotations.AfterSuite;

/** TestNG reuses its worker threads, so each thread's browser is closed once, when the run ends. */
public class CloseBrowsers {

  @AfterSuite(alwaysRun = true)
  public void closeBrowser() {
    BrowserManager.quit();
  }
}
