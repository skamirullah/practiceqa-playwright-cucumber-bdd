package com.practiceqa.securelife.steps;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.practiceqa.securelife.context.World;
import com.practiceqa.securelife.data.Vehicle;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.regex.Pattern;

public class VehicleSteps {
  private final World world;

  public VehicleSteps(World world) {
    this.world = world;
  }

  @Given("a customer wants to insure their car")
  public void aCustomerWantsToInsureTheirCar() {
    world.vehicle().open();
  }

  @Given("a customer has asked for plans for their Maruti Swift")
  public void aCustomerHasAskedForPlans() {
    world.vehicle().open().describe(Vehicle.swift()).viewPlans();
    assertThat(world.quote().vehicleSummary()).containsText("Swift");
  }

  @When("they ask for plans without describing the car")
  public void theyAskForPlansWithoutDescribingTheCar() {
    world.vehicle().viewPlans();
  }

  @When("they enter the registration number {string} and ask for plans")
  public void theyEnterTheRegistrationNumber(String registration) {
    world.vehicle().enterRegistration(registration).viewPlans();
  }

  @When("they describe their Maruti Swift and ask for plans")
  public void theyDescribeTheirCar() {
    world.vehicle().describe(Vehicle.swift()).viewPlans();
  }

  @Then("they are told to complete the highlighted fields")
  public void theyAreToldToCompleteTheHighlightedFields() {
    assertThat(world.vehicle().errorSummary()).hasText("Please complete the highlighted fields.");
  }

  @Then("these vehicle fields show an error:")
  public void theseVehicleFieldsShowAnError(DataTable table) {
    table.asMap().forEach((field, message) -> assertThat(world.vehicle().fieldError(field)).hasText(message));
  }

  @Then("the registration number is rejected")
  public void theRegistrationNumberIsRejected() {
    assertThat(world.vehicle().fieldError("reg-number")).hasText("Enter a registration like MH12AB1234");
  }

  @Then("they see plans for a {string}")
  public void theySeePlansFor(String car) {
    assertThat(world.quote().vehicleSummary()).containsText(car);
    assertThat(world.quote().vehicleSummary().page()).hasURL(Pattern.compile("/apps/insurance/quote"));
  }
}
