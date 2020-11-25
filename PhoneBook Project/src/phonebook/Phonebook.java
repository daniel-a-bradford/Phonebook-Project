package phonebook;

public class Phonebook {
	
	private String phonebookName;
	private Person[] phonebookPersonArray = new Person[0];
	private long nextPersonID = 1; 
	
	/** Constructor to build Phonebook with just the name. Initializes nextPersonID to 1.
	 * @param phonebookName
	 */
	public Phonebook(String phonebookName) {
		this.phonebookName = phonebookName;
	}
	
	/** Constructor to build Phonebook with a name and an array People. 
	 * 	It sorts the People by personID, initializes nextPersonID to the highest personID in the array + 1.
	 *  Then it sorts the People by PersonName.
	 * @param phonebookName
	 * @param phonebookPersonArray
	 */
	public Phonebook(String phonebookName, Person[] phonebookPersonArray) {
		super();
		this.phonebookName = phonebookName;
		// Ensure the People added are sorted by PersonID respectively
		this.phonebookPersonArray = bubbleSortByPersonID(phonebookPersonArray, true);
		// Assign the nextPersonID to the last sorted PersonID + 1
		this.nextPersonID = phonebookPersonArray[phonebookPersonArray.length - 1].getPersonID() + 1; 
		this.phonebookPersonArray = bubbleSortByPersonName(this.phonebookPersonArray, true);
	}

	public String getPhonebookName() {
		return phonebookName;
	}

	public void setPhonebookName(String phonebookName) {
		this.phonebookName = phonebookName;
	}

	public Person[] getPhonebookPersonArray() {
		return phonebookPersonArray;
	}
	
	/** Method addPerson prompts the user to provide all the necessary information to create a new person
	 *  or get the necessary information from a pre-formatted string of person data. It then assigns the next 
	 *  PersonNumber, and updates the PhonebookPersonArray with the new person included as the last element. 
	 *  fromUserPrompts - true - prompt the user for person data. false - get pre-formatted input string.
	 *  The expected string format is:
	 *  Full Name, Street Number and Name, City, 2 Letter State Abbreviation, 5-Digit Zip Code, 10-Digit Phone Number without separators
	 *  @param fromUserPrompts - true - prompt the user for person data. false - get pre-formatted input string. 
	 *  @return boolean - true if the person is added, false if the person was not added successfully.
	 */
	public boolean addPerson(boolean fromUserPrompts) {
		Person newPerson = new Person(nextPersonID);
		boolean wasSuccessful = false;
		if (fromUserPrompts) {
			wasSuccessful = newPerson.promptUsertoUpdatePersonInfo();
		} else {
			wasSuccessful = newPerson.parsePersonInputString();
		}
		// If wasSuccessful is true, then the new person information was valid,
		//  so add the new person and update this.phonebookPersonArray with the new person list.
		if (wasSuccessful) {
			// Set the personID and increment nextPersonID
			newPerson.setPersonID(this.nextPersonID);
			this.nextPersonID++;
			// Create updatedPeople with one more element at the end and add the new Person
			Person[] updatedPeople = resizeArray(this.phonebookPersonArray, this.phonebookPersonArray.length + 1);
			updatedPeople[updatedPeople.length - 1] = newPerson;
			this.phonebookPersonArray = updatedPeople;
		} 
		// Ensure the people are sorted by Name in ascending order
		this.phonebookPersonArray = bubbleSortByPersonName(phonebookPersonArray, true);
		// If wasSuccessful is false, then the new person information was not valid,
		//  so leave this.phonebookPersonArray array unchanged.
		return wasSuccessful;
	}
	
	/** Method addPerson takes a Person object, validates the information is correct usable, then assigns the 
	 * 	next PersonNumber, and updates PhonebookPersonArray
	 *   with the new person included as the last element.
	 *  @param Person - the Person object to be added to the phonebook.
	 *  @return boolean - true if the person is added, false if the person was not added successfully.
	 */
	public boolean addPerson(Person newPerson) {
		boolean wasSuccessful = newPerson.isValidPerson(true);
		// If wasSuccessful is true, then the new person information was valid,
		//  so add the new person and update this.phonebookPersonArray with the new person list.
		if (wasSuccessful) {
			// Set the personID and increment nextPersonID
			newPerson.setPersonID(this.nextPersonID);
			this.nextPersonID++;
			// Create updatedPeople with one more element at the end and add the new Person
			Person[] updatedPersonArray = resizeArray(this.phonebookPersonArray, this.phonebookPersonArray.length + 1);
			updatedPersonArray[updatedPersonArray.length - 1] = newPerson;
			this.phonebookPersonArray = updatedPersonArray;
		} 
		// Ensure the people are sorted by PersonName in ascending order
		this.phonebookPersonArray = bubbleSortByPersonName(phonebookPersonArray, true);
		// If wasSuccessful is false, then the new person information was not valid,
		//  so leave this.phonebookPersonArray array unchanged.
		return wasSuccessful;
	}
	
	/** Method updatePerson takes the existing Person object, prompts the user for new data or takes a preformatted 
	 * 	string of values, validates the information is correct and usable, then updates PhonebookPersonArray
	 *   with the new person information.
	 *   fromUserPrompts - true - prompt the user for person data. false - get pre-formatted input string.
	 *   	 *  The expected string format is:
	 *  Full Name, Street Number and Name, City, 2 Letter State Abbreviation, 5-Digit Zip Code, 10-Digit Phone Number without separators
	 *  @param Person - the Person object to be updated in the phonebook.
	 *  @param fromUserPrompts - true - prompt the user for updates. false - get pre-formatted input string.
	 *  @return boolean - true if the person is added, false if the person was not added successfully.
	 */
	public boolean updatePerson(Person existingPerson, boolean fromUserPrompts) {
		boolean wasSuccessful = existingPerson.isValidPerson(false);
		if (!wasSuccessful) {
			System.out.println("updatePerson - existingPerson is not valid.");
			return false;
		}
		Person updatedPerson = new Person(existingPerson.getPersonID());
		if (fromUserPrompts) {
			wasSuccessful = updatedPerson.promptUsertoUpdatePersonInfo(existingPerson);
		} else {
			wasSuccessful = updatedPerson.parsePersonInputString();
		}
		// If wasSuccessful is true, then the updated person information is valid, so update the person.
		if (wasSuccessful) {
			// Find the index of the existing person in the phonebookPersonArray
			Integer indexOfExistingPerson = findPersonIndexByPersonID(existingPerson.getPersonID());
			if (indexOfExistingPerson == null) {
				System.out.println("updatePerson - Person not found.");
				return false;
			}
			// Replace the existingPerson in the array with the updatedPerson
			this.phonebookPersonArray[indexOfExistingPerson] = updatedPerson;
		} 
		// Ensure the people are sorted by PersonID in ascending order
		this.phonebookPersonArray = bubbleSortByPersonName(phonebookPersonArray, true);
		// If wasSuccessful is false, then the new person information was not valid,
		//  so leave this.phonebookPersonArray array unchanged.
		return wasSuccessful;
	}
	
	/** Method deletePerson takes the existing Person object, finds the user in the phonebookPersonArray, 
	 * 	displays the customer information, and prompts the user to confirm they want to delete the person.
	 * 	Returns true if the delete is successful. Returns false if the person was not found or the user cancels.
	 *  @param Person - the Person object to be deleted from the phonebook.
	 *  @return boolean - true if the person is added, false if the person was not added successfully.
	 */
	public boolean deletePerson(Person existingPerson) {
		if (!isPersonInPhonebook(existingPerson)) {
			System.out.println("deletePerson - PersonID was not found.");
			return false;
		} else {
			// Find the index of the existing person in the phonebookPersonArray
			Integer indexOfExistingPerson = findPersonIndexByPersonID(existingPerson.getPersonID());
			if (indexOfExistingPerson == null) {
				System.out.println("deletePerson - PersonID not found.");
				return false;
			}
			Boolean confirmDelete = ConsoleInput.getInputBoolean("Do you want to delete the following person from the phonebook?\n" +
					existingPerson.toString());
			if (confirmDelete == null) {
				System.out.println("User cancelled");
				return false;
			}
			if (confirmDelete) {
				// Replace the Person in the array with null, then clean all nulls out of the phonebookPersonArray
				this.phonebookPersonArray[indexOfExistingPerson] = null;
				this.phonebookPersonArray = cleanArray(this.phonebookPersonArray);
				return true;
			}

		} 
		return false;
	}
	
	/** Method isPersonInPhonebook searches the phonebook person array for the currentPerson and returns true if it is found.
	 * @param Person currentPerson
	 * @return boolean - true if the currentPerson is found, otherwise false.
	 */
	public boolean isPersonInPhonebook(Person currentPerson) {
		for (Person existingPerson:phonebookPersonArray) {
			if (currentPerson.getPersonID() == existingPerson.getPersonID()) {
				return true;
			}	
		}
		return false;
	}
	
	/** Method findPeople returns an array of People based on the integer search criteria:
	 * 		1. First Name
	 * 		2. Last Name
	 * 		3. Full Name
	 * 		2. Street number and/or street name
	 * 		3. City
	 * 		4. State
	 * @param String searchString
	 * @param int searchType - specify what type of property searchString is looking for
	 * 		
	 * @return Person[]
	 */
	public Person[] findPeople(String searchString, int searchType) {
		Person[] personArray = new Person[0];
		Integer[] indices = new Integer[0];
		switch (searchType) {
		case 1:
			indices = findPersonIndicesByFirstName(searchString);
			break;
		case 2:
			indices = findPersonIndicesByLastName(searchString);
			break;
		case 3:
			indices = findPersonIndicesByName(searchString);
			break;
		case 4:
			indices = findPersonIndicesByStreet1(searchString);
			break;
		case 5:
			indices = findPersonIndicesByCity(searchString);
			break;
		case 6:
			indices = findPersonIndicesByState(searchString);
			break;
		default: 
			System.out.println("findPerson - not a valid search type");
		}
		if (indices != null) {
			personArray = resizeArray(personArray, indices.length);
			for (int index = 0; index < indices.length; index++) {
				personArray[index] = phonebookPersonArray[indices[index]];
			}
			return personArray;
		}
		return null;
	}
	
	/** Method findPeople returns an array of People based on their phone number stored as a long integer.
	 * @param long searchPhone
	 * return Person[]
	 */
	public Person[] findPeople(long searchPhone) {
		Person[] personArray = new Person[0];
		Integer[] indices = new Integer[0];
		indices = findPersonIndicesByPhone(searchPhone);
		if (indices != null) {
			personArray = resizeArray(personArray, indices.length);
			for (int index = 0; index < indices.length; index++) {
				personArray[index] = phonebookPersonArray[indices[index]];
			}
			return personArray;
		}
		return null;
	}
	
	/** Method findPeople returns an array of People based on their zip code stored as an integer.
	 * @param int searchZip
	 * return Person[]
	 */
	public Person[] findPeople(int searchZip) {
		Person[] personArray = new Person[0];
		Integer[] indices = new Integer[0];
		indices = findPersonIndicesByZip(searchZip);
		if (indices != null) {
			personArray = resizeArray(personArray, indices.length);
			for (int index = 0; index < indices.length; index++) {
				personArray[index] = phonebookPersonArray[indices[index]];
			}
			return personArray;
		}
		return null;
	}
	
	/** Method findPersonIndexByPersonID searches the phonebookPersonArray for matching personID and returns 
	 *   the index of the matching personID. If nothing is found, return null.
	 * @param searchID - the long personID to search for stored as a long integer
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer findPersonIndexByPersonID(long searchID) {
		for (int index = 0; index < this.phonebookPersonArray.length; index++) {
			if (phonebookPersonArray[index].getPersonID() == searchID) {
				return index;
			}
		}
		return null;
	}
	
	/** Method findPersonIndicesByName searches the phonebookPersonArray for matching full name and returns 
	 * the indices of all matches. Match is not case sensitive.
	 * @param name - the full name to search for
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findPersonIndicesByName(String name) {
		name = name.trim().toLowerCase();
		Integer[] indexArray = new Integer[0];
		String tempString = "";
		for (int index = 0; index < this.phonebookPersonArray.length; index++) {
			tempString = phonebookPersonArray[index].getPersonName().toLowerCase();
			if (tempString.contains(name)) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findPersonIndicesByFirstName searches the phonebookPersonArray for matching first name and returns 
	 * the indices of all matches. Match is not case sensitive.
	 * @param first - the first name to search for
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findPersonIndicesByFirstName(String first) {
		first = first.trim().toLowerCase();
		Integer[] indexArray = new Integer[0];
		String tempString = "";
		for (int index = 0; index < this.phonebookPersonArray.length; index++) {
			tempString = phonebookPersonArray[index].getFirstName().toLowerCase();
			if (tempString.contains(first)) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findPersonIndicesByLastName searches the phonebookPersonArray for matching last name and returns 
	 * the indices of all matches. Match is not case sensitive.
	 * @param last - the last name to search for
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findPersonIndicesByLastName(String last) {
		last = last.trim().toLowerCase();
		Integer[] indexArray = new Integer[0];
		String tempString = "";
		for (int index = 0; index < this.phonebookPersonArray.length; index++) {
			tempString = phonebookPersonArray[index].getLastName().toLowerCase();
			if (tempString.contains(last)) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findPersonIndicesByStreet1 searches the phonebookPersonArray for matching full name and returns 
	 * the indices of all matches. Match is not case sensitive.
	 * @param street1 - the street number and street name to search for
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findPersonIndicesByStreet1(String street1) {
		street1 = street1.trim().toLowerCase();
		Integer[] indexArray = new Integer[0];
		String tempString = "";
		for (int index = 0; index < this.phonebookPersonArray.length; index++) {
			tempString = phonebookPersonArray[index].getAddressStreet1().toLowerCase();
			if (tempString.contains(street1)) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findPersonIndicesByCity searches the phonebookPersonArray for matching full name and returns 
	 * the indices of all matches. Match is not case sensitive.
	 * @param city - the city to search for
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findPersonIndicesByCity(String city) {
		city = city.trim().toLowerCase();
		Integer[] indexArray = new Integer[0];
		String tempString = "";
		for (int index = 0; index < this.phonebookPersonArray.length; index++) {
			tempString = phonebookPersonArray[index].getAddressCity().toLowerCase();
			if (tempString.contains(city)) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findPersonIndicesByState searches the phonebookPersonArray for matching full name and returns 
	 * the indices of all matches. Match is not case sensitive.
	 * @param state - the state to search for
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findPersonIndicesByState(String state) {
		state = state.trim().toLowerCase();
		Integer[] indexArray = new Integer[0];
		String tempString = "";
		for (int index = 0; index < this.phonebookPersonArray.length; index++) {
			tempString = phonebookPersonArray[index].getAddressState().toLowerCase();
			if (tempString.contains(state)) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findPersonIndicesByZip searches the phonebookPersonArray for matching zip code and returns 
	 * the indices of all matches.
	 * @param zip - the zip code integer to search for
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findPersonIndicesByZip(int zip) {
		Integer[] indexArray = new Integer[0];
		for (int index = 0; index < this.phonebookPersonArray.length; index++) {
			if (phonebookPersonArray[index].getAddressZip() == zip) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}
	
	/** Method findPersonIndicesByPhone searches the phonebookPersonArray for matching phone number and returns 
	 * the indices of all matches.
	 * @param searchPhone - the phone number to search for stored as a long integer
	 * @return Integer[] - an array of the indices of all matches
	 */
	private Integer[] findPersonIndicesByPhone(long searchPhone) {
		Integer[] indexArray = new Integer[0];
		for (int index = 0; index < this.phonebookPersonArray.length; index++) {
			if (phonebookPersonArray[index].getPersonPhone() == searchPhone) {
				indexArray = resizeArray(indexArray, indexArray.length + 1);
				indexArray[indexArray.length - 1] = index;
			}
		}
		return indexArray;
	}

	/** Method numberOfPeople searches the phonebookBookArray returns the total number of people
	 */
	public Integer numberOfPeople() {
		return phonebookPersonArray.length;
	}
	
	/** Method bubbleSort sorts an array of Person by PersonID depending on leastToGreatest.
	 * 	If leastToGreatest is true: Bubble sort from least to greatest.
	 * 	If leastToGreatest is false: Bubble sort from greatest to least.
	 */
	private Person[] bubbleSortByPersonID(Person[] inputArray, boolean leastToGreatest) {

		for (int out = 0; out < inputArray.length; out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputArray.length; in++) {
				// Depending on direction, check to see if a swap is necessary.
				if ( (leastToGreatest && inputArray[in-1].getPersonID() > inputArray[in].getPersonID()) || 
						(!leastToGreatest && inputArray[in-1].getPersonID() < inputArray[in].getPersonID())) {
					Person temp = inputArray[in-1];
					inputArray[in-1] = inputArray[in];
					inputArray[in] = temp;
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any swaps.
				break;
			}
		}
		return inputArray;
	}
	
	/** Method bubbleSortByPersonName sorts an array of Person last name, first name, and middle name
	 *  depending on leastToGreatest.
	 * 	If leastToGreatest is true: Bubble sort from least to greatest.
	 * 	If leastToGreatest is false: Bubble sort from greatest to least.
	 */
	private Person[] bubbleSortByPersonName(Person[] inputArray, boolean alphabeticalOrder) {

		for (int out = 0; out < inputArray.length; out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputArray.length; in++) {
				// Depending on direction, check to see if a swap is necessary.
				String lastPerson = inputArray[in-1].getLastName() 
						+ inputArray[in-1].getFirstName() + inputArray[in-1].getMiddleName();
				String currentPerson = inputArray[in].getLastName() 
						+ inputArray[in].getFirstName() + inputArray[in].getMiddleName();
				if ( (alphabeticalOrder && lastPerson.compareToIgnoreCase(currentPerson) > 0) || 
						(!alphabeticalOrder && lastPerson.compareToIgnoreCase(currentPerson) < 0) ) {
					Person temp = inputArray[in-1];
					inputArray[in-1] = inputArray[in];
					inputArray[in] = temp;
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any swaps.
				break;
			}
		}
		return inputArray;
	}
	
	/** Method resizeArray takes an array and re-sizes the array to the desiredLength.
	 * 	It discards any elements beyond the desired length.
	 * 
	 * @param Person[] inputArray, int desiredLength
	 * @return Person[]
	 */
	private static Person[] resizeArray(Person[] inputArray, int desiredLength) {
		// Create a new array of the desired length
		Person[] resizedArray = new Person[desiredLength];
		// Step through the input array and assign input values to the resized array.
		for (int index = 0; index < desiredLength; index++) {
			// If the desired length is longer than the input array, put nulls in the remaining elements of the resized array.
			if (index < inputArray.length) {
				resizedArray[index] = inputArray[index];
			} else {
				resizedArray[index] = null;
			}
		}
		return resizedArray;
	}
	
	/** Method cleanArray takes an array and searches for null elements while building a clean array, then resizes it.
	 * 	and populates it with the remaining non-null elements.
	 * 	It discards any elements beyond the desired length.
	 * 
	 * @param Person[] inputArray, int desiredLength
	 * @return Person[]
	 */
	private static Person[] cleanArray(Person[] inputArray) {
		int numberOfNulls = 0;
		Person[] outputArray = new Person[inputArray.length];
		for (int index = 0; index < inputArray.length; index++) {
			if (inputArray[index] != null) {
				outputArray[index - numberOfNulls] = inputArray[index];
			} else {
				numberOfNulls++;
			}
		}
		if (numberOfNulls == 0) {
			System.out.println("cleanArray - no null elements found");
			return inputArray;
		}
		outputArray = resizeArray(outputArray, inputArray.length - numberOfNulls);
		return outputArray;
	}
	
	/** Method resizeArray takes an array and re-sizes the array to the desiredLength.
	 * 	It discards any elements beyond the desired length.
	 * 
	 * @param int[] inputIntArray, int desiredLength
	 * @return Integer[]
	 */
	private static Integer[] resizeArray(Integer[] inputIntArray, int desiredLength) {
		// Create a new array of the desired length
		Integer[] resizedArray = new Integer[desiredLength];
		// Step through the input array and assign input values to the resized array.
		for (int index = 0; index < desiredLength; index++) {
			// If the desired length is longer than the input array, put nulls in the remaining elements of the resized array.
			if (index < inputIntArray.length) {
				resizedArray[index] = inputIntArray[index];
			} else {
				resizedArray[index] = (Integer)null;
			}
		}
		return resizedArray;
	}
	
	/** Method toString overrides the default Object.toString to summarize the phonebook.
	 * @return String with the salient details of the phonebook
	 */
	@Override
	public String toString() {
		String outputString = "";
		if (phonebookPersonArray.length == 1) {
			outputString = this.phonebookName + " has " + numberOfPeople() + " person:\n";
		} else {
			outputString = this.phonebookName + " has " + numberOfPeople() + " total people:\n";
		}
		for (int index = 0; index < phonebookPersonArray.length; index++) {
			outputString += phonebookPersonArray[index].toString() + "\n";
		}
		return outputString;
	}
}
