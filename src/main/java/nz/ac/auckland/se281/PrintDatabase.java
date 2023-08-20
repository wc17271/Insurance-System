package nz.ac.auckland.se281;

public class PrintDatabase {

  // Print database header - 0 policies:
  public static void printHeaderZero(
      boolean load,
      String name,
      String loadedProfile,
      String printSize,
      String age,
      String numPolicies,
      int size) {

    // 0 Policies:
    if (size == 0) {
      if ((load) && (name.equals(loadedProfile))) {
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
            "*** ", printSize, name, age, numPolicies, "ies", "0");

        return;
      } else {
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
            "", printSize, name, age, numPolicies, "ies", "0");

        return;
      }
    }
  }

  // Print database header - 1 or more policies:
  public static void printHeader(
      boolean load,
      String name,
      String loadedProfile,
      String printSize,
      String age,
      String numPolicies,
      int size,
      String total) {

    switch (size) {
      case 1: // Policy "y"
        if ((load) && (name.equals(loadedProfile))) {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "*** ", printSize, name, age, numPolicies, "y", total);

          return;
        } else {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "", printSize, name, age, numPolicies, "y", total);
        }

        break;
      default: // Policies "ies"
        if ((load) && (name.equals(loadedProfile))) {

          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "*** ", printSize, name, age, numPolicies, "ies", total);

          return;
        } else {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "", printSize, name, age, numPolicies, "ies", total);
        }
    }
  }

  // Print database - Home Policy:
  public static void printHome(InsurancePolicy currentPolicy, int size) {
    if (currentPolicy instanceof HomePolicy) {
      HomePolicy homePolicy = (HomePolicy) currentPolicy;

      // Getting variables:
      String address = homePolicy.getAddress();
      String sumInsured = Integer.toString(homePolicy.getSumInsured());

      String discountSum = Integer.toString(Helper.getHome(homePolicy, size));
      String baseSum = Integer.toString(homePolicy.getBasePremium());

      // Printing message to user:
      MessageCli.PRINT_DB_HOME_POLICY.printMessage(address, sumInsured, baseSum, discountSum);
    }
  }

  // Print database - Car Policy:
  public static void printCar(InsurancePolicy currentPolicy, int size) {
    if (currentPolicy instanceof CarPolicy) {
      CarPolicy carPolicy = (CarPolicy) currentPolicy;

      // Getting variables:
      String model = carPolicy.getModel();
      String sumInsured = Integer.toString(carPolicy.getSumInsured());

      String discountSum = Integer.toString(Helper.getCar(carPolicy, size));
      String baseSum = Integer.toString(carPolicy.getBasePremium());

      // Printing message to user:
      MessageCli.PRINT_DB_CAR_POLICY.printMessage(model, sumInsured, baseSum, discountSum);
    }
  }

  // Print database - Life Policy:
  public static void printLife(InsurancePolicy currentPolicy, int size) {
    if (currentPolicy instanceof LifePolicy) {
      LifePolicy lifePolicy = (LifePolicy) currentPolicy;

      // Getting variables:
      String sumInsured = Integer.toString(lifePolicy.getSumInsured());

      String discountSum = Integer.toString(Helper.getLife(lifePolicy, size));
      String baseSum = Integer.toString(lifePolicy.getBasePremium());

      // Printing message to user:
      MessageCli.PRINT_DB_LIFE_POLICY.printMessage(sumInsured, baseSum, discountSum);
    }
  }
}
