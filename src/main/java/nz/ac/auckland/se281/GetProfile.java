package nz.ac.auckland.se281;

import java.util.ArrayList;

public class GetProfile {

  // Retrieve name from person instance:
  public static String getName(ArrayList<Person> userList, int i) {
    Person currentUser = userList.get(i);
    String name = currentUser.getName();

    return name;
  }

  // Retrieve age from person instance:
  public static String getAge(ArrayList<Person> userList, int i) {
    Person currentUser = userList.get(i);
    String age = currentUser.getAge();

    return age;
  }

  // Retrieve number of life policies from person instance:
  public static int getNumLifePolicies(Person user) {

    // Extract user's policies:
    ArrayList<InsurancePolicy> policies = user.getPolicies();

    // Initialize Variables:
    int size = policies.size();
    int count = 0;

    // Find the number of life polcies:
    for (int i = 0; i < size; i++) {
      InsurancePolicy currentPolicy = policies.get(i);

      if (currentPolicy instanceof LifePolicy) {
        count++;
      }
    }

    return count;
  }
}
