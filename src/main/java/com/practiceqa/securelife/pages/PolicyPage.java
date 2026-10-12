package com.practiceqa.securelife.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PolicyPage extends BasePage {

  public PolicyPage(Page page) {
    super(page);
  }

  public Locator issued() {
    return testId("policy-issued");
  }

  public Locator number() {
    return testId("policy-number");
  }

  public Locator premium() {
    return testId("policy-premium");
  }

  public Locator cardLastFour() {
    return testId("policy-card-last4");
  }
}
