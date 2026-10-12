package com.practiceqa.securelife.steps;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import com.practiceqa.securelife.context.World;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class QuoteSteps {
  private final World world;

  public QuoteSteps(World world) {
    this.world = world;
  }

  @When("they choose the {word} plan")
  public void theyChooseThePlan(String plan) {
    world.quote().choosePlan(plan);
  }

  @When("they add {word} cover")
  public void theyAddCover(String addon) {
    world.quote().addAddon(addon);
  }

  @When("they buy the plan")
  public void theyBuyThePlan() {
    world.quotedPremium = world.quote().totalPremium().textContent();
    world.quote().buy();
  }

  @Then("no plan can be bought yet")
  public void noPlanCanBeBoughtYet() {
    assertThat(world.quote().buyButton()).isDisabled();
  }

  @Then("the total premium is {string}")
  public void theTotalPremiumIs(String premium) {
    assertThat(world.quote().totalPremium()).containsText(premium);
  }

  @Then("the premium includes own-damage cover")
  public void thePremiumIncludesOwnDamageCover() {
    assertThat(world.quote().ownDamage()).isVisible();
  }

  @Then("the premium has no own-damage cover")
  public void thePremiumHasNoOwnDamageCover() {
    assertThat(world.quote().totalPremium()).isVisible();
    assertThat(world.quote().ownDamage()).hasCount(0);
  }

  @Then("the add-ons come to {string}")
  public void theAddOnsComeTo(String amount) {
    assertThat(world.quote().addonPremium()).containsText(amount);
  }

  @Then("the {word} plan costs more than the {word} plan")
  public void onePlanCostsMoreThanAnother(String dearer, String cheaper) {
    assertThat(world.quote().planPrice(dearer)).isGreaterThan(world.quote().planPrice(cheaper));
  }
}
