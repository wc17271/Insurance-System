package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Helper {

  // Converts string to titlecase:
  public static String toTitleCase(String userName) {
    userName = userName.toLowerCase();
    userName = userName.substring(0, 1).toUpperCase() + userName.substring(1);

    return userName;
  }

  // Converts string to integer type:
  public static int stringToInt(String age) {
    return Integer.parseInt(age);
  }

  // Calculates total price:
  public static int getTotal(ArrayList<InsurancePolicy> policies, int size) {

    // Initialize Variables:
    int total = 0;

    // Initiaize for loop to iterate through the user's policies:
    for (int i = 0; i < size; i++) {
      InsurancePolicy currentPolicy = policies.get(i);

      // Sum of home policies:
      if (currentPolicy instanceof HomePolicy) {
        HomePolicy home = (HomePolicy) currentPolicy;
        total += home.getBasePremium();

        continue;
      }

      // Sum of car policies:
      if (currentPolicy instanceof CarPolicy) {
        CarPolicy car = (CarPolicy) currentPolicy;
        total += car.getBasePremium();

        continue;
      }

      // Sum of life policies:
      if (currentPolicy instanceof LifePolicy) {
        LifePolicy life = (LifePolicy) currentPolicy;
        total += life.getBasePremium();
      }
    }

    // Apply discounts:
    if (size == 2) {
      return (int) (total * 0.9);
    }

    if (size > 2) {
      return (int) (total * 0.8);
    }

    return total;
  }

  // Calculates discount - Home:
  public static int getHome(InsurancePolicy home, int size) {

    // Casting the object, allowing us to use its instance methods:
    HomePolicy homePolicy = (HomePolicy) home;
    int sum = homePolicy.getBasePremium();

    // Calculates discounts depending on the number of policies the user has:
    if (size == 2) {
      return (int) (sum * 0.9);
    }

    if (size > 2) {
      return (int) (sum * 0.8);
    }

    return sum;
  }

  // Calculates discount - Car:
  public static int getCar(InsurancePolicy car, int size) {

    // Casting the object, allowing us to use its instance methods:
    CarPolicy carPolicy = (CarPolicy) car;
    int sum = carPolicy.getBasePremium();

    // Calculates discounts depending on the number of policies the user has:
    if (size == 2) {
      return (int) (sum * 0.9);
    }

    if (size > 2) {
      return (int) (sum * 0.8);
    }

    return sum;
  }

  // Calculates discount - Life:
  public static int getLife(InsurancePolicy life, int size) {

    // Casting the object, allowing us to use its instance methods:
    LifePolicy lifePolicy = (LifePolicy) life;
    int sum = lifePolicy.getBasePremium();

    // Calculates discounts depending on the number of policies the user has:
    if (size == 2) {
      return (int) (sum * 0.9);
    }

    if (size > 2) {
      return (int) (sum * 0.8);
    }

    return sum;
  }
}
