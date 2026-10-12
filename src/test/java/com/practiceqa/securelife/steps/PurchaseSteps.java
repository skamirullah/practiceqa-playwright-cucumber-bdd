package com.practiceqa.securelife.steps;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.practiceqa.securelife.context.World;
import com.practiceqa.securelife.data.Card;
import com.practiceqa.securelife.data.Proposer;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PurchaseSteps {
  private final World world;
  private final Card card = Card.valid();

  public PurchaseSteps(World world) {
    this.world = world;
  }

  @When("they complete the proposal")
  public void theyCompleteTheProposal() {
    world.proposal().fill(Proposer.valid()).acceptDeclaration().proceedToPay();
  }

  @When("they complete the proposal for an owner born on {string}")
  public void theyCompleteTheProposalForAnOwnerBornOn(String dateOfBirth) {
    world.proposal().fill(Proposer.valid().bornOn(dateOfBirth)).acceptDeclaration().proceedToPay();
  }

  @When("they complete the proposal without accepting the declaration")
  public void theyCompleteTheProposalWithoutTheDeclaration() {
    world.proposal().fill(Proposer.valid()).proceedToPay();
  }

  @When("they pay by card in the payment gateway")
  public void theyPayByCard() {
    world.proposal().payWith(card);
  }

  @When("they cancel in the payment gateway")
  public void theyCancelInThePaymentGateway() {
    world.proposal().cancelPayment();
  }

  @Then("the amount to pay is the premium they were quoted")
  public void theAmountToPayIsTheQuotedPremium() {
    assertThat(world.proposal().payablePremium()).hasText(world.quotedPremium);
  }

  @Then("a policy is issued with a number starting {string}")
  public void aPolicyIsIssued(String prefix) {
    assertThat(world.policy().issued()).isVisible();
    assertThat(world.policy().number()).containsText(prefix);
  }

  @Then("the policy shows the premium paid and the card used")
  public void thePolicyShowsThePremiumAndCard() {
    assertThat(world.policy().premium()).hasText(world.quotedPremium);
    assertThat(world.policy().cardLastFour()).hasText(card.lastFour());
  }

  @Then("the {word} field says {string}")
  public void theFieldSays(String field, String message) {
    assertThat(world.proposal().fieldError(field)).hasText(message);
  }

  @Then("the payment gateway does not open")
  public void thePaymentGatewayDoesNotOpen() {
    assertThat(world.proposal().gatewayModal()).hasCount(0);
  }

  @Then("they are told the payment was cancelled and can try again")
  public void theyAreToldThePaymentWasCancelled() {
    assertThat(world.proposal().paymentCancelled()).hasText("Payment was cancelled. You can try again.");
    assertThat(world.proposal().gatewayModal()).hasCount(0);
  }
}
