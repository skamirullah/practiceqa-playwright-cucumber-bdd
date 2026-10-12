package com.practiceqa.securelife.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class QuotePage extends BasePage {

  public QuotePage(Page page) {
    super(page);
  }

  public Locator vehicleSummary() {
    return testId("quote-vehicle");
  }

  /** @param plan third-party, comprehensive or zero-dep */
  public QuotePage choosePlan(String plan) {
    testId("plan-" + plan).click();
    return this;
  }

  public QuotePage addAddon(String addon) {
    testId("addon-" + addon).click();
    return this;
  }

  /** The price shown on a plan's card, as a number. */
  public double planPrice(String plan) {
    return amount(testId("premium-" + plan).textContent());
  }

  public Locator ownDamage() {
    return testId("od-premium");
  }

  public Locator addonPremium() {
    return testId("addon-premium");
  }

  public Locator totalPremium() {
    return testId("total-premium");
  }

  public Locator buyButton() {
    return testId("buy-plan");
  }

  public void buy() {
    buyButton().click();
  }

  private static double amount(String money) {
    return Double.parseDouble(money.replaceAll("[^0-9.]", ""));
  }
}
