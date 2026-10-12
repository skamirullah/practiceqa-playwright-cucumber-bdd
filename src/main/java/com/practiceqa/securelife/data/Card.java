package com.practiceqa.securelife.data;

/** A payment card. This is a test number that only the practice gateway accepts. */
public record Card(String number, String expiry, String cvv) {

  public static Card valid() {
    return new Card("4111111111111111", "1230", "123");
  }

  public String lastFour() {
    return number.substring(number.length() - 4);
  }
}
