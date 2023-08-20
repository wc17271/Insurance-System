package nz.ac.auckland.se281;

public abstract class InsurancePolicy {

  protected int sumInsured;

  public InsurancePolicy(int sumInsured) {
    this.sumInsured = sumInsured;
  }

  public abstract int getBasePremium();

  public int getSumInsured() {
    return sumInsured;
  }
}
