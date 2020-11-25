package phonebook;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Person extends Address {
	private long personID;
	private String firstName = "";
	private String middleName = "";
	private String lastName = "";
	private String personName = "";
	private long personPhone = 0;
	
	/** Constructor to build a Person with only a personID number
	 * @param personID
	 */
	public Person (long personID) {
		this.personID = personID;
	}
	
	/** Constructor to build a Person with all person data without personID and the personBooksBorrowed array.
	 * @param personName
	 * @param personStreet1
	 * @param personStreet2
	 * @param personCity
	 * @param personState
	 * @param personZip
	 * @param personZipPlus4
	 * @param personPhone
	 */
	public Person(String personName, String personStreet1, String personStreet2, String personCity, 
			String personState, int personZip, int personZipPlus4, long personPhone) {
		super(personStreet1, personStreet2, personCity, personState, personZip, personZipPlus4);
		this.personName = personName;
		extractNames(personName);
		this.personPhone = personPhone;
	}

	/** Constructor to build a Person with all person data
	 * @param personID
	 * @param personName
	 * @param personStreet1
	 * @param personStreet2
	 * @param personCity
	 * @param personState
	 * @param personZip
	 * @param personZipPlus4
	 * @param personPhone
	 */
	public Person(long personID, String personName, String personStreet1,
			String personStreet2, String personCity, String personState, int personZip, int personZipPlus4,
			long personPhone) {
		super(personStreet1, personStreet2, personCity, personState, personZip, personZipPlus4);
		this.personID = personID;
		this.personName = personName;
		extractNames(personName);
		this.personPhone = personPhone;
	}
	
	/** Constructor to build a Person with all person data
	 * @param personID
	 * @param personName
	 * @param personStreet1
	 * @param personStreet2
	 * @param personCity
	 * @param personState
	 * @param personZip
	 * @param personZipPlus4
	 * @param personPhone
	 */
	public Person(long personID, String personName, Address personAddress, long personPhone) {
		this.personID = personID;
		this.personName = personName;
		extractNames(personName);
		this.personPhone = personPhone;
	}

	public long getPersonID() {
		return personID;
	}

	public void setPersonID(long personID) {
		this.personID = personID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
		// Set the first, middle, and last name properties based on personName.
		extractNames(personName);
	}

	public long getPersonPhone() {
		return personPhone;
	}

	public boolean setPersonPhone(long personPhone) {
		if (Long.toString(personPhone).length() == 10) {
			this.personPhone = personPhone;
			return true;
		} else if (Long.toString(personPhone).length() < 10 && personPhone != 0) {
			this.personPhone = 	personPhone;
			return true;
		}
		System.out.println("setPersonPhone - Invalid phone number.");
		return false;
		
	}
	
	/** Method extractNames splits the input string into parts separated by spaces, assigning the first 
	 * 	part to the property firstName, the last part to lastName (unless there is only one part, where it
	 * 	assigns a blank string). Middle name remains blank unless there are more than 2 parts.
	 * @param personName
	 */
	private void extractNames(String personName) {
		this.middleName = "";
		if (personName != null) {
			personName = personName.trim();
			String[] nameArray = personName.split(" ");
			this.firstName = nameArray[0];
			
			for (int index = 1; index < nameArray.length - 1; index++) {
				this.middleName += nameArray[index] + " ";
			}
			if (nameArray.length > 1) {
				this.lastName = nameArray[nameArray.length - 1];
			} else {
				this.lastName = "";
			}
		} else {
			this.firstName = "";
			this.lastName = "";
		}
	}

	/** Method promptUsertoUpdatePersonInfo uses dialog boxes to prompt the user for the person name, 
	 * 	street number and name, additional street info (if needed), city, state, zip code, zip code+4 
	 * 	(if known).
	 */
	public boolean promptUsertoUpdatePersonInfo() {
		boolean BadInput = true;
		// Skip the function if the newPerson.personID is not assigned.
		if (this.getPersonID() != 0) {
			userCancelled:
			do {
				String firstName = ConsoleInput.getInputString("Please enter the person's first name: ");
				// Remove all white space from the string
				firstName = firstName.replaceAll("\\s", "");
				String middleName = ConsoleInput.getInputString("Please enter the person's middle name(s): ");
				// Remove white space from the beginning and end of the string.
				middleName = middleName.trim();
				String lastName = ConsoleInput.getInputString("Please enter the person's last name: ");
				// Remove all white space from the string
				lastName = lastName.replaceAll("\\s", "");
				this.personName = firstName + " " + middleName + " " + lastName;
				extractNames(this.personName);
				
				if (!super.promptUsertoUpdateAddressInfo()) {
					break userCancelled;
				}
			
				Long phone = ConsoleInput.getInputLong("Please enter the person's phone number (all digits no separators): ", 10);
				this.personPhone = phone;

				return true;
//TODO display person information and re-prompt if the information is incorrect
			} while (BadInput);	
		}
		System.out.println("promptUsertoUpdatePersonInfo - person information invalid");
		this.personID = 0;
		return false;
	}
	
	/** Method promptUsertoUpdatePersonInfo updates the user information by prompting the user for the updated person name, 
	 * 	street number and name, additional street info (if needed), city, state, zip code, zip code+4 
	 * (if known). newPerson is a new person object shell with personID assigned.
	 * @param newPerson
	 */
	public boolean promptUsertoUpdatePersonInfo(Person oldPerson) {
		// Skip the function if the newPerson.personID is not assigned.
		if (this.getPersonID() != 0) {
			userCancelled:
			do {
				String firstName = ConsoleInput.getInputString("Current first name: " + oldPerson.getFirstName()
						+ "\nPlease enter the person's new first name: ");
				if (firstName == null) {
					System.out.println("User cancelled");
					break userCancelled;
				}
				// Remove all white space from the string
				firstName = firstName.replaceAll("\\s", "");
				String middleName = ConsoleInput.getInputString("Current middle name(s): " + oldPerson.getMiddleName()
						+ "\nPlease enter the person's middle name(s): ");
				if (middleName == null) {
					System.out.println("User cancelled");
					break userCancelled;
				}
				// Remove white space from the beginning and end of the string.
				middleName = middleName.trim();
				String lastName = ConsoleInput.getInputString("Current last name: " + oldPerson.getLastName()
					+ "\nPlease enter the person's last name: ");
				if (lastName == null) {
					System.out.println("User cancelled");
					break userCancelled;
				}
				// Remove all white space from the string
				lastName = lastName.replaceAll("\\s", "");
				this.personName = firstName + " " + middleName + " " + lastName;
				extractNames(this.personName);
				
				Boolean gotAddress = super.promptUsertoUpdateAddressInfo();
				if (gotAddress == null || !gotAddress) {
					System.out.println("User cancelled");
					break userCancelled;
				}
				
				Long phone = ConsoleInput.getInputLong("Current phone number: " 
						+ oldPerson.getPersonPhone() + "\nPlease enter the person's phone number (all digits no separators):", 10);
				if (phone == null) {
					System.out.println("User cancelled");
					break userCancelled;
				}
				this.personPhone = phone;
				
				this.personID = oldPerson.getPersonID();
				return true;
			} while (false);	
		}
		System.out.println("promptUsertoUpdatePersonInfo - person information invalid");
		this.personID = 0;
		return false;
	}
	
	/** Method parsePersonInputString prompts the user for a pre-formatted string of user data, validates it,
	 * 	 and returns a new Person object with the information entered. If the string has invalid data, return null.
	 *  The expected format is:
	 *  Full Name, Street Number and Name, City, 2 Letter State Abbreviation, 5-Digit Zip Code, 10-Digit Phone Number without separators
	 * @return boolean
	 */
	public boolean parsePersonInputString() {
		boolean badInput = true;
		while (badInput) {
			String userInput = ConsoleInput.getInputString("Please enter the new person information using the following format.\n"
					+ "Full Name, Street Number and Name, City, 2 Letter State Abbreviation, 5-Digit Zip Code, 10-Digit Phone Number without separators:\n"
					+ "Enter - to cancel:");
			userInput = userInput.trim();
			if (userInput == "-") {
				System.out.println("parsePersonInputString - user cancelled input.");
				return false;
			}
			String[] inputArray = userInput.split(",");
			for (int index = 0; index < inputArray.length; index++) {
				// Trim whitespace before and after each input string to help validate data.
				inputArray[index] = inputArray[index].trim();
			}
			// If the
			if (inputArray.length == 6) {
				// Validate the data string input. Zip code length 5, phone length 10, and state abbreviation is a state.
				boolean areValidNumbers = false;
				try {
					Integer.parseInt(inputArray[4]);
					Long.parseLong(inputArray[5]);
					areValidNumbers = true;
				} 
				catch (NumberFormatException exception) {
					System.out.println("The numbers in your string are invalid. Please try again.");
					break;
				}
				if (inputArray[4].length() == 5 && inputArray[5].length() == 10 && 
						super.isStateAbbreviation(inputArray[3]) && areValidNumbers) {
					extractNames(inputArray[0]);
					super.setAddressStreet1(inputArray[1]);
					super.setAddressCity(inputArray[2]);
					super.setAddressState(inputArray[3]);
					int zip = Integer.parseInt(inputArray[4]);
					super.setAddressZip(zip);
					this.personPhone = Long.parseLong(inputArray[5]);
					badInput = false;
				} else {
					System.out.println("New person information provided was not formatted correctly. Please try again.");
					return false;
				}
			} else {
				System.out.println("New person information did not have 6 elements separated by commas. Please try again.");
				return false;
			}
		}
		return true;
	}
	
	/** Method isValidPerson checks the current Person object to ensure all properties have non-blank or null values.
	 * 	It prints the person to the console and if invalid, the first reason why it was found to be invalid.
	 * @param isNewPerson - true if the Person is new so it does not check for a person ID.
	 * @return boolean - true if the person information is valid, otherwise false.
	 */
	public boolean isValidPerson(boolean isNewPerson) {
		if (!isNewPerson) {
			if ((Long)this.personID == null || this.personID == 0) {
				System.out.println("isValidPerson - existing person has no personID.");
				return false;
			}
		}
		if (this.personName.isBlank() || this.personName.isEmpty()) {
			System.out.println("isValidPerson - person has no name.");
			return false;
		}
		
		if (!super.isValidAddress(isNewPerson)) {
			return false;
		}
		if (this.personPhone == 0L || Long.toString(this.personPhone).length() > 10) {
			System.out.println("isValidPerson - person phone number information is invalid.");
			return false;
		}
//		System.out.println("isValidPerson - " + this.personName + " is valid.");
		return true;
	}
	
	/** Method toString overrides the default Object.toString to summarize the person.
	 * @return String with the salient details of the person
	 */
	@Override
	public String toString() {
		String outputString = this.lastName + ", "  + this.firstName + " " + this.middleName + "  " + 
					super.toString() + " ";
		String phoneString = Long.toString(this.personPhone);
		if (phoneString.length() < 10) {
			DecimalFormat paddedPhone = new DecimalFormat("0000000000");
			phoneString = paddedPhone.format(this.personPhone);
		} 
		if (phoneString.length() == 10) {
			phoneString = "(" + phoneString.subSequence(0, 3) + ") " + phoneString.subSequence(3, 6) + "-" + phoneString.subSequence(6, 10);
		} 
		outputString += " Phone " + phoneString;
		return outputString;
	}

}
