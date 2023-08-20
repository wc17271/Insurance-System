package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  private ArrayList<Person> userList = new ArrayList<Person>();
  boolean load;
  String loadedProfile;

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  public void printDatabase() {
    /* The printDatabase instance method prints the entire insurance database. */

    // Determine size of database:
    int databaseSize = userList.size();

    // Printing relevant statements based on the size of the database:
    String printSize = Integer.toString(databaseSize);

    // 0 Profiles:
    if (databaseSize == 0) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(printSize, "s", ".");

      return;
    }

    // 1 or more profiles (header):
    if (databaseSize == 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(printSize, "", ":"); // 1 Profile.
    } else {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(printSize, "s", ":"); // More than 1 profile.
    }

    for (int i = 0; i < databaseSize; i++) {

      // Initialize Rank:
      String rank = Integer.toString(i + 1);

      // Retrieving profile details:
      String name = GetProfile.getName(userList, i);
      String age = GetProfile.getAge(userList, i);
      Person user = userList.get(i);

      ArrayList<InsurancePolicy> policies = user.getPolicies();
      int size = policies.size();
      String numPolicies = Integer.toString(size);

      // 0 Policies:
      if (size == 0) {
        PrintDatabase.printHeaderZero(load, name, loadedProfile, rank, age, numPolicies, size);

        continue;
      }

      // 1 or more policies:
      // Get total price and print profile header:
      int tempTotal = Helper.getTotal(policies, size);
      String total = Integer.toString(tempTotal);

      PrintDatabase.printHeader(load, name, loadedProfile, rank, age, numPolicies, size, total);

      // Printing policies:
      for (int j = 0; j < size; j++) {
        InsurancePolicy currentPolicy = policies.get(j);

        PrintDatabase.printHome(currentPolicy, size);
        PrintDatabase.printCar(currentPolicy, size);
        PrintDatabase.printLife(currentPolicy, size);
      }
    }
  }

  public void createNewProfile(String userName, String age) {
    /* The createNewProfile instance method creates a new client profile. */

    if (load) {
      MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(loadedProfile);
      return;
    }

    // Converting username to title case:
    userName = Helper.toTitleCase(userName);

    // Checking if the usermame is at least three characters long:
    if (userName.length() < 3) {
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);
      return;
    }

    // Checking age is an integer:
    int intAge;

    try {
      intAge = Integer.parseInt(age);
    } catch (NumberFormatException exception) {
      // print message for error
      MessageCli.INVALID_AGE.printMessage(age, userName);
      return;
    }

    // Checking if age is a positive integer:
    if (intAge <= 0) {
      MessageCli.INVALID_AGE.printMessage(age, userName);
      return;
    }

    // Checking if the username is unique:
    int numberOfUsers = userList.size();

    if (numberOfUsers > 0) {
      for (int i = 0; i < numberOfUsers; i++) {

        String currentUser = GetProfile.getName(userList, i);

        if (currentUser.equals(userName)) {
          MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
          return;
        }
      }
    }

    // Creating a new Person instance and storing into the database:
    Person user = new Person(userName, age);
    userList.add(user);

    // Printing success message:
    MessageCli.PROFILE_CREATED.printMessage(userName, age);
  }

  public void loadProfile(String userName) {
    /* loadProfile instance method loads a profile. */

    // Convert to title case:
    String name = Helper.toTitleCase(userName);

    // Check if the user exists:
    int numberOfUsers = userList.size();

    if (numberOfUsers == 0) {
      MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(name);
      return;
    }

    for (int i = 0; i < numberOfUsers; i++) {

      String currentUser = GetProfile.getName(userList, i);

      if (currentUser.equals(name)) {
        MessageCli.PROFILE_LOADED.printMessage(name);

        // Set the loaded profile:
        load = true;
        loadedProfile = name;
        return;
      }
    }

    // If user does not exist:
    MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(name);
    return;
  }

  public void unloadProfile() {
    /* unloadProfile instance method unloads profiles. */

    // No loaded profiles:
    if (!load) {
      MessageCli.NO_PROFILE_LOADED.printMessage();
    } else {
      MessageCli.PROFILE_UNLOADED.printMessage(loadedProfile);
      load = false;
      loadedProfile = "";
    }
  }

  public void deleteProfile(String userName) {
    /* deleteProfile instance method deletes a profile. */

    // Convert to titlecase:
    String name = Helper.toTitleCase(userName);

    // Checking if current profile is loaded:
    if (load) {
      if (loadedProfile.equals(name)) {
        MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(name);
        return;
      }
    }

    // Deleting profile:
    int numberOfUsers = userList.size();

    for (int i = 0; i < numberOfUsers; i++) {

      String currentUser = GetProfile.getName(userList, i);

      if (currentUser.equals(name)) {
        userList.remove(i);
        MessageCli.PROFILE_DELETED.printMessage(name);
        return;
      }
    }

    // Profile does not exist:
    MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(name);

    return;
  }

  public void createPolicy(PolicyType type, String[] options) {
    /* createPolicy instance method creates policies. */

    // Checking if profile is loaded:
    if (!load) {
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();

      return;
    }

    // Creating Policies:
    // Get person instance and age:
    Person user = null;
    String ageTemp = null;
    String name = null;
    int age = 0;

    int numberOfUsers = userList.size();

    for (int i = 0; i < numberOfUsers; i++) {

      String currentUser = GetProfile.getName(userList, i);

      if (currentUser.equals(loadedProfile)) {
        user = userList.get(i);
        ageTemp = user.getAge();
        age = Helper.stringToInt(ageTemp);
        name = user.getName();
      }
    }

    switch (type) {
      case LIFE: // Life Policy:
        // Age Check:
        if (age > 100) {
          MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(name);

          return;
        }

        // Policy Check:
        int numPolicies = GetProfile.getNumLifePolicies(user);

        if (numPolicies >= 1) {
          MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(name);

          return;
        }

        // Create Life Policy:
        int sumInsured = Helper.stringToInt(options[0]);

        InsurancePolicy lifePolicy = new LifePolicy(sumInsured, age);
        user.getPolicies().add(lifePolicy);

        MessageCli.NEW_POLICY_CREATED.printMessage("life", name);

        break;
      case HOME: // Home Policy:
        // Extract Details:
        int sum3 = Helper.stringToInt(options[0]);
        String address = options[1];
        String rentTemp = options[2];
        String rent = rentTemp.toLowerCase();
        boolean rental = false;

        // If rental, set boolean to true:
        if (rent.contains("y")) {
          rental = true;
        }

        // Create Home Policy:
        InsurancePolicy homePolicy = new HomePolicy(sum3, address, rental);
        user.getPolicies().add(homePolicy);

        MessageCli.NEW_POLICY_CREATED.printMessage("home", name);

        break;
      case CAR: // Car Policy:
        // Extract Details:
        int sum4 = Helper.stringToInt(options[0]);
        String model = options[1];
        String licence = options[2];
        String mechTemp = options[3];
        String mech = mechTemp.toLowerCase();
        boolean mechanical = false;

        if (mech.contains("y")) {
          mechanical = true;
        }

        // Create Car Policy:
        InsurancePolicy carPolicy = new CarPolicy(sum4, model, licence, mechanical, age);
        user.getPolicies().add(carPolicy);

        MessageCli.NEW_POLICY_CREATED.printMessage("car", name);

        break;
      default:
        return;
    }
  }
}
