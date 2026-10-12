package com.practiceqa.securelife.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.practiceqa.securelife.data.Vehicle;

public class VehiclePage extends BasePage {

  public VehiclePage(Page page) {
    super(page);
  }

  public VehiclePage open() {
    page.navigate("/apps/insurance");
    return this;
  }

  public VehiclePage enterRegistration(String registration) {
    testId("reg-number").fill(registration);
    return this;
  }

  /** Fills the whole form for a car with no previous claims. */
  public VehiclePage describe(Vehicle vehicle) {
    enterRegistration(vehicle.registration());
    testId("vehicle-make").selectOption(vehicle.make());
    testId("vehicle-model").selectOption(vehicle.model());
    testId("fuel-" + vehicle.fuel()).click();
    testId("vehicle-year").selectOption(vehicle.year());
    testId("claim-no").click();
    testId("ncb").selectOption(vehicle.noClaimBonus());
    return this;
  }

  public void viewPlans() {
    testId("get-quote").click();
  }

  public Locator errorSummary() {
    return testId("vehicle-errors");
  }
}
