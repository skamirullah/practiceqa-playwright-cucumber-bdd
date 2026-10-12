Feature: Comparing plans and add-ons
  The customer picks one of three plans. Comprehensive and zero-depreciation
  plans cover the customer's own car and can take add-ons.

  Background:
    Given a customer has asked for plans for their Maruti Swift

  Scenario: A plan must be chosen before buying
    Then no plan can be bought yet

  Scenario: Third-party cover has no own-damage premium
    When they choose the third-party plan
    Then the premium has no own-damage cover

  Scenario: Comprehensive cover includes own damage
    When they choose the comprehensive plan
    Then the premium includes own-damage cover

  Scenario: Better cover costs more
    Then the comprehensive plan costs more than the third-party plan
    And the zero-dep plan costs more than the comprehensive plan

  @smoke
  Scenario: An add-on is added to the premium
    When they choose the comprehensive plan
    And they add roadside cover
    Then the add-ons come to "199.00"
    And the total premium is "14,442.00"
