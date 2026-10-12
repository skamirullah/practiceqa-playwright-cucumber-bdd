Feature: Buying a policy
  After choosing a plan the customer fills in a proposal and pays in a payment
  gateway that opens inside the page. A successful payment issues the policy.

  Background:
    Given a customer has asked for plans for their Maruti Swift
    And they choose the comprehensive plan
    And they add roadside cover
    And they buy the plan

  @smoke
  Scenario: Paying by card issues the policy
    When they complete the proposal
    Then the amount to pay is the premium they were quoted
    When they pay by card in the payment gateway
    Then a policy is issued with a number starting "SL/2026/"
    And the policy shows the premium paid and the card used

  Scenario: Cancelling the payment keeps the proposal open
    When they complete the proposal
    And they cancel in the payment gateway
    Then they are told the payment was cancelled and can try again

  Scenario: The owner must be an adult
    When they complete the proposal for an owner born on "15/08/2015"
    Then the owner-dob field says "Owner must be 18 or older"
    And the payment gateway does not open

  Scenario: The declaration must be accepted
    When they complete the proposal without accepting the declaration
    Then the declaration field says "Please accept the declaration"
    And the payment gateway does not open
