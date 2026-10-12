package com.practiceqa.securelife.data;

/** The car being insured. All data is fictional. */
public record Vehicle(String registration, String make, String model, String fuel, String year, String noClaimBonus) {

  /** A 2021 petrol Maruti Swift with no previous claims and a 20% no-claim bonus. */
  public static Vehicle swift() {
    return new Vehicle("MH12AB1234", "maruti", "swift", "petrol", "2021", "20");
  }
}
