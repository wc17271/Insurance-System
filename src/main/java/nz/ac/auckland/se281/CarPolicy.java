package nz.ac.auckland.se281;

public class CarPolicy extends InsurancePolicy {

  private String model;
  private String licencePlate;
  private boolean mechanical;
  private int age;

  public CarPolicy(int sumInsured, String model, String licencePlate, boolean mechanical, int age) {
    super(sumInsured);
    this.model = model;
    this.licencePlate = licencePlate;
    this.mechanical = mechanical;
    this.age = age;
  }

  @Override
  public int getBasePremium() {

    int basePremium;

    // Finding the base premium:
    if (age < 25) {
      basePremium = (int) (0.15 * sumInsured);
    } else {
      basePremium = (int) (0.1 * sumInsured);
    }

    if (mechanical) {
      return basePremium += 80;
    }

    return basePremium;
  }

  public String getModel() {
    return model;
  }

  public String getLicence() {
    return licencePlate;
  }
}
