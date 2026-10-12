package com.practiceqa.securelife.data;

/** The policy owner and their nominee. All data is fictional. */
public record Proposer(String name, String dateOfBirth, String email, String phone, String address,
                       String nomineeName, String nomineeRelation, String nomineeAge) {

  public static Proposer valid() {
    return new Proposer("Amir Khan", "15/08/1990", "amir@example.com", "9876543210",
        "12 Palm Street, Pune 411001", "Sara Khan", "spouse", "32");
  }

  public Proposer bornOn(String dateOfBirth) {
    return new Proposer(name, dateOfBirth, email, phone, address, nomineeName, nomineeRelation, nomineeAge);
  }
}
