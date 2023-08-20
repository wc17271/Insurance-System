package nz.ac.auckland.se281;

public class LifePolicy extends InsurancePolicy {

  private int age;

  public LifePolicy(int sumInsured, int age) {
    super(sumInsured);
    this.age = age;
  }

  @Override
  public int getBasePremium() {

    double sum = ((1 + (age / (double) 100)) / 100) * sumInsured;

    return (int) sum;
  }
}
