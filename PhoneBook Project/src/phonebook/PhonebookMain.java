package phonebook;

public class PhonebookMain {

	public static void main(String[] args) {
		/* Phonebook: Write a program to simulate the actions of a phone book. 
		 * Your program should be able to :
		 * •	Add new entries
		 * •	Search for an existing entry
		 * •	Search by first name
		 * •	Search by last name
		 * •	Search by full name 
		 * NOTE: Name is not unique therefore the result should be an array of Person Objects.
		 * •	Search by telephone number
		 * •	Search by city or state
		 * •	Delete a record for a given telephone number
		 * •	Update a record for a given telephone number
		 * •	Show all records in ascending order *note you will need to research sorting on an array.
		 * •	An option to exit the program 
		 */ 
				
		boolean userExit = false;
		String phonebookName = "Bradford Virtual Phonebook";
		// The user wants to access the phonebook, so create one
		Phonebook ourPhonebook = new Phonebook(phonebookName);
		ourPhonebook = setDefaultPeople(ourPhonebook);
		while (!userExit) {
			// Use getInputChoice to display the main menu and have the user choose an option.
			Integer userInputInt = ConsoleInput.getInputChoice("__________________________________________\n"
					+ "Welcome to the " + phonebookName + "! \n"
					+ "Please enter the number of what you would like to do: \n"
					+ "1. Add a new person.\n"
					+ "2. Search for a person and display their information.\n"
					+ "3. Search for a person and update their information.\n"
					+ "4. Search for a person and delete their information.\n"
					+ "5. Show all people in the phonebook.\n"
					+ "6. Exit the phonebook.", 6);
			
			// If null is returned, skip the remaining instructions since the user wants to exit.
			if (userInputInt != null) {
				// Determine the method to run
				switch (userInputInt) {
				case 1:
					ourPhonebook = personAddMenu(ourPhonebook);
					break;
				case 2:
					System.out.println("Person Search");
					Person[] peopleFound = personSearchMenu(ourPhonebook);
					displayPeopleFound(peopleFound);
					break;
				case 3:
					System.out.println("Update Person");
					ourPhonebook = personUpdateMenu(ourPhonebook);
					break;
				case 4:
					System.out.println("Delete Person");
					ourPhonebook = personDeleteMenu(ourPhonebook);
					break;	
				case 5:
					System.out.println(ourPhonebook.toString());
					break;	
				case 6:
					userExit = true;
					break;
				default:
					System.out.println("You selected " + userInputInt + " which is not a valid menu option.");
				}
			} else {
				userExit = true;
			}
		}
	}
	
	/** Method personAddMenu provides the user a menu to choose how they want to enter the new person information:
	 * 	Yes to be prompted for each piece of data, no to enter a single string of pre-formatted user data.
	 * @param currentPhonebook  - the Phonebook object to be updated
	 * @return Phonebook - if successful, the Phonebook object with the new person added
	 */
	private static Phonebook personAddMenu(Phonebook currentPhonebook) {
		// Call addPerson requesting user input.
		boolean promptOrFormatted = ConsoleInput.getInputBoolean("Do you want to enter the new person information "
				+ "by being prompted for each piece of data? \n"
				+ "If no, you will be prompted for pre-formatted data separated by commas.");
		if (currentPhonebook.addPerson(promptOrFormatted)) {
			System.out.println("Person added successfully.");
		} else {
			System.out.println("Person was not added.");
		};
		return currentPhonebook;
	}
	
	/** Method personSearchMenu provides a common menu to be used when the user wants to search for person data for
	 * 	the purposes of displaying people, or selecting a person to update or delete from the currentPhonebook.
	 * 	It presents a menu of search options, prompts for the search criteria, and returns the results as an array
	 * 	of Person objects.  
	 * @param currentPhonebook
	 * @return
	 */
	private static Person[] personSearchMenu(Phonebook currentPhonebook) {
		Integer searchType = ConsoleInput.getInputChoice("\n"
				+ "How would you like to search for a person?\n"
				+ "1. First Name\n"
				+ "2. Last Name\n"
				+ "3. Full Name\n"
				+ "4. Street Address\n"
				+ "5. City\n"
				+ "6. State\n"
				+ "7. Zip Code\n"
				+ "8. Phone Number\n"
				+ "9. Exit to the main menu\n"
				+ "Please enter the number of your selection:", 9);
		String searchString = "";
		Person[] peopleFound = new Person[0];
		boolean userExit = false;
			switch (searchType) {
			case 1:
				searchString = ConsoleInput.getInputString("Please enter part or all of the person's first name.");
				if (searchString == null) {
					System.out.println("Person search cancelled by the user.");
					break;
				}
				peopleFound = currentPhonebook.findPeople(searchString, searchType);
				break;			
			case 2:
				searchString = ConsoleInput.getInputString("Please enter part or all of the person's last name.");
				if (searchString == null) {
					System.out.println("Person search cancelled by the user.");
					break;
				}
				peopleFound = currentPhonebook.findPeople(searchString, searchType);
				break;
			case 3:
				searchString = ConsoleInput.getInputString("Please enter part or all of the person's full name.");
				if (searchString == null) {
					System.out.println("Person search cancelled by the user.");
					break;
				}
				peopleFound = currentPhonebook.findPeople(searchString, searchType);
				break;
			case 4:
				searchString = ConsoleInput.getInputString("Please enter part or all of the person's street address.");
				if (searchString == null) {
					System.out.println("Person search cancelled by the user.");
					break;
				}
				peopleFound = currentPhonebook.findPeople(searchString, searchType);
				break;
			case 5:
				searchString = ConsoleInput.getInputString("Please enter part or all of the person's city.");
				if (searchString == null) {
					System.out.println("Person search cancelled by the user.");
					break;
				}
				peopleFound = currentPhonebook.findPeople(searchString, searchType);
				break;
			case 6:
				searchString = ConsoleInput.getInputState("Please enter the person's 2 letter state abbreviation.");
				if (searchString == null) {
					System.out.println("Person search cancelled by the user.");
					break;
				}
				peopleFound = currentPhonebook.findPeople(searchString, searchType);
				break;
			case 7:
				Integer searchZip = ConsoleInput.getInputInt("Please enter the person's 5 digit zip code as a number.", 5);
				System.out.println("personSearch - looking for zip code " + searchZip);
				if (searchZip == null) {
					System.out.println("Person search cancelled by the user.");
					break;
				}
				peopleFound = currentPhonebook.findPeople(searchZip);
				break;
			case 8:
				Long searchPhone = ConsoleInput.getInputLong("Please enter the person's phone number as a number without separating characters.", 10);
				if (searchPhone == null) {
					System.out.println("Person search cancelled by the user.");
					break;
				}
				peopleFound = currentPhonebook.findPeople(searchPhone);
				break;
			case 9:
				System.out.println("Person search cancelled by the user.");
				userExit = true;
				break;
			default: 
				System.out.println("Your selection was invalid.");
			}
		if (!userExit) {
			return peopleFound;
		}
		return null;
	}
	
	/** Method displayPeopleFound takes an array of Person and displays them as a numbered list so the user can
	 * 	select a specific person by number (if required).
	 * @param peopleFound
	 */
	private static void displayPeopleFound(Person[] peopleFound) {
		String searchResults = "";
		if (peopleFound != null) {
			searchResults = "Your search returned " + peopleFound.length + " results:\n";
			for (int index = 0; index < peopleFound.length; index++) {
				searchResults += (index + 1) + "- " + peopleFound[index].toString() + "\n";
			}
			System.out.println(searchResults);
		} else {
			System.out.println("Your search did not find any people.");
		}
	}
	
	/** Method personUpdateMenu allows a user to update the information for a person in the current Phonebook object.
	 * 	It displays the search menu and then prompts the user to select a person to update. Then it prompts the user
	 * 	how they want to update the user data, either by prompts for each part of the person data with the current 
	 * 	data displayed for reference, or a single pre-formatted string of updated user data. If successful, it returns
	 * 	the Phonebook object with the updated Person included.
	 * @param ourPhonebook - the current Phonebook object
	 * @return Phonebook
	 */
	private static Phonebook personUpdateMenu(Phonebook ourPhonebook) {
		Person[] peopleFound = personSearchMenu(ourPhonebook);
		displayPeopleFound(peopleFound);
		if (peopleFound.length == 0) {
			System.out.println("No results to update");
			return ourPhonebook;
		}
		int userChoice = ConsoleInput.getInputChoice(
				"Please type the number of the search result you want to update:", peopleFound.length);
		boolean userWantsPrompts = ConsoleInput.getInputBoolean("Would you like to be prompted to update each attribute of "
				+ peopleFound[userChoice - 1].getPersonName() + " individually?");		
		if (ourPhonebook.updatePerson(peopleFound[userChoice - 1], userWantsPrompts)) {
			System.out.println("Person update successful.");
		} else {
			System.out.println("Person update failed.");
		}
		return ourPhonebook;
	}
	
	/** Method personDeleteMenu allows a user to a person from the current Phonebook object.
	 * 	It displays the search menu and then prompts the user to select a person to delete. After confirming, 
	 * 	the person is deleted from the Phonebook object. If successful, it returns the Phonebook object with 
	 * 	the Person removed.
	 * @param ourPhonebook - the current Phonebook object
	 * @return Phonebook
	 */
	private static Phonebook personDeleteMenu(Phonebook ourPhonebook) {
		Person[] peopleFound = personSearchMenu(ourPhonebook);
		displayPeopleFound(peopleFound);
		if (peopleFound.length == 0) {
			System.out.println("No results to delete.");
			return ourPhonebook;
		}
		int userChoice = ConsoleInput.getInputChoice(
				"Please type the number of the search result you want to delete:", peopleFound.length);
		if (ourPhonebook.deletePerson(peopleFound[userChoice - 1])) {
			System.out.println("Person delete successful.");
		} else {
			System.out.println("Person delete failed.");
		}
		return ourPhonebook;
	}

	/** Method setDefaultPeople assigns a set of default People 
	 * 	to test the functions of the phonebook.
	 * @param ourPhonebook
	 * @return Phonebook - updated version of the phonebook with default People
	 */
	private static Phonebook setDefaultPeople(Phonebook ourPhonebook) {
		Person[] defaultPersonArray = new Person[6];
//		Person(String personName, String personStreet1, String personStreet2, String personCity, 
//				String personState, int personZip, int personZipPlus4, long personPhone)
		defaultPersonArray[0] = new Person("Dan Bradford", "120 Civic Plaza", "", "O'Fallon", "IL", 62269, 0, 6186323783L);
		defaultPersonArray[1] = new Person("Miri Belle", "101 Dalmatian St.", "", "Yorkville", "IL", 60560, 0, 6301234567L);
		defaultPersonArray[2] = new Person("Noah June", "910 2nd St.", "", "Maryville", "IL", 62062, 0, 6187654321L);
		defaultPersonArray[3] = new Person("Brandon Owners", "406 W US Highway 50", "", "O'Fallon", "IL", 62269, 0, 6181234321L);
		defaultPersonArray[4] = new Person("Noah Noodles", "951 S Green Mount Rd", "", "Belleville", "IL", 62220, 4814, 6182330513L);
		defaultPersonArray[5] = new Person("Micah Mook", "5050 Oakland Ave", "", "St. Louis", "MO", 63110, 1460, 3142894400L);
		for (Person tempPerson:defaultPersonArray) {
			if (ourPhonebook.addPerson(tempPerson)) {
//				System.out.println("Added default Person " + tempPerson.getPersonName());
			} else {
				System.out.println("Failed to add default Person " + tempPerson.getPersonName());
			}
		}
		return ourPhonebook;
	}
}
