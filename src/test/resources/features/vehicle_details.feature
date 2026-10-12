Feature: Describing the car to insure
  A quote starts with the car: its registration number, make, model, fuel,
  year and claim history.

  Background:
    Given a customer wants to insure their car

  @smoke
  Scenario: A fully described car gets plans
    When they describe their Maruti Swift and ask for plans
    Then they see plans for a "Maruti Suzuki Swift"

  Scenario: Every missing detail is pointed out
    When they ask for plans without describing the car
    Then they are told to complete the highlighted fields
    And these vehicle fields show an error:
      | reg-number    | Enter a registration like MH12AB1234 |
      | vehicle-make  | Choose the make                      |
      | vehicle-model | Choose the model                     |
      | fuel          | Choose the fuel type                 |
      | vehicle-year  | Choose the registration year         |
      | claimed       | Tell us about previous claims        |

  Scenario Outline: A registration number must look like MH12AB1234: <case>
    When they enter the registration number "<registration>" and ask for plans
    Then the registration number is rejected

    Examples:
      | case             | registration |
      | too short        | MH12         |
      | digits only      | 1234567890   |
      | no state letters | 12AB1234     |
