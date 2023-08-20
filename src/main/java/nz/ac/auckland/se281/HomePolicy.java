package nz.ac.auckland.se281;

public class HomePolicy extends InsurancePolicy {

  private String address;
  private boolean rental;

  public HomePolicy(int sumInsured, String address, boolean rental) {
    super(sumInsured);
    this.address = address;
    this.rental = rental;
  }

  public String getAddress() {
    return address;
  }

  @Override
  public int getBasePremium() {
    if (rental) {
      return (int) (0.02 * sumInsured);
    } else {
      return (int) (0.01 * sumInsured);
    }
  }
}
