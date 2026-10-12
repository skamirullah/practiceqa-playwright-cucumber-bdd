package com.practiceqa.securelife.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.practiceqa.securelife.data.Card;
import com.practiceqa.securelife.data.Proposer;

public class ProposalPage extends BasePage {

  public ProposalPage(Page page) {
    super(page);
  }

  public ProposalPage fill(Proposer proposer) {
    testId("owner-name").fill(proposer.name());
    testId("owner-dob").fill(proposer.dateOfBirth());
    testId("owner-email").fill(proposer.email());
    testId("owner-phone").fill(proposer.phone());
    testId("owner-address").fill(proposer.address());
    testId("nominee-name").fill(proposer.nomineeName());
    testId("nominee-relation").selectOption(proposer.nomineeRelation());
    testId("nominee-age").fill(proposer.nomineeAge());
    return this;
  }

  public ProposalPage acceptDeclaration() {
    testId("declaration").check();
    return this;
  }

  /** Submits the proposal; when it is valid this opens the payment gateway. */
  public ProposalPage proceedToPay() {
    testId("pay-premium").click();
    return this;
  }

  /** The payment form lives in an iframe, so its fields are reached through a FrameLocator. */
  private FrameLocator gateway() {
    return page.frameLocator("[data-testid='payment-gateway']");
  }

  public void payWith(Card card) {
    gateway().getByTestId("gw-card").fill(card.number());
    gateway().getByTestId("gw-expiry").fill(card.expiry());
    gateway().getByTestId("gw-cvv").fill(card.cvv());
    gateway().getByTestId("gw-pay").click();
  }

  public void cancelPayment() {
    gateway().getByTestId("gw-cancel").click();
  }

  public Locator gatewayModal() {
    return testId("gateway-modal");
  }

  public Locator paymentCancelled() {
    return testId("payment-cancelled");
  }

  public Locator payablePremium() {
    return testId("payable-premium");
  }
}
