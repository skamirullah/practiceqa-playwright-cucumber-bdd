package com.practiceqa.securelife.context;

import com.practiceqa.securelife.browser.BrowserManager;
import com.practiceqa.securelife.pages.PolicyPage;
import com.practiceqa.securelife.pages.ProposalPage;
import com.practiceqa.securelife.pages.QuotePage;
import com.practiceqa.securelife.pages.VehiclePage;

/**
 * Everything one scenario shares between its step classes. PicoContainer creates
 * a new World per scenario, so scenarios stay independent. Pages are built on
 * first use, after the Before hook has opened the browser page.
 */
public class World {
  private VehiclePage vehicle;
  private QuotePage quote;
  private ProposalPage proposal;
  private PolicyPage policy;

  /** The premium the customer was quoted, remembered so later steps can compare against it. */
  public String quotedPremium;

  public VehiclePage vehicle() {
    if (vehicle == null) vehicle = new VehiclePage(BrowserManager.page());
    return vehicle;
  }

  public QuotePage quote() {
    if (quote == null) quote = new QuotePage(BrowserManager.page());
    return quote;
  }

  public ProposalPage proposal() {
    if (proposal == null) proposal = new ProposalPage(BrowserManager.page());
    return proposal;
  }

  public PolicyPage policy() {
    if (policy == null) policy = new PolicyPage(BrowserManager.page());
    return policy;
  }
}
