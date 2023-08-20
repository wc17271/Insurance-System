package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Person {

  private ArrayList<InsurancePolicy> policies;

  private String name;
  private String age;

  public Person(String name, String age) {
    this.name = name;
    this.age = age;
    policies = new ArrayList<InsurancePolicy>();
  }

  public String getName() {
    return name;
  }

  public String getAge() {
    return age;
  }

  /* public void addPolicy(InsurancePolicy policy) {
    policies.add(policy);
  } */

  public ArrayList<InsurancePolicy> getPolicies() {
    return policies;
  }
}
