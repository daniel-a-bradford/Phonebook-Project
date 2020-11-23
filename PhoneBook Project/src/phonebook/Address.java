package phonebook;

public class Address {
	private String street1 = "";
	private String street2 = "";
	private String city = "";
	private String state = "";
	private int zip = 0;
	private int zipPlus4 = 0;
	
	/** Constructor to build an Address
	 */
	public Address () {
	}
	
	/** Constructor to build an address with all information
	 * @param street1
	 * @param street2
	 * @param city
	 * @param state
	 * @param zip
	 * @param zipPlus4
	 */
	public Address(String street1, String street2, String city, String state, int zip, int zipPlus4) {
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.zipPlus4 = zipPlus4;
	}

	public String getAddressStreet1() {
		return street1;
	}

	public boolean setAddressStreet1(String street1) {
		city = city.trim();
		if (street1.length() > 3) {
			this.street1 = street1;
			return true;
		}
		System.out.println("setAddressStreet1 - Not enough letters street number and name.");
		return false;
	}

	public String getAddressStreet2() {
		return street2;
	}

	public void setAddressStreet2(String street2) {
		this.street2 = street2;
	}

	public String getAddressCity() {
		return city;
	}

	public boolean setAddressCity(String city) {
		city = city.trim();
		if (city.length() > 1) {
			this.city = city;
			return true;
		}
		System.out.println("setAddressCity - Not enough letters for a city.");
		return false;
	}

	public String getAddressState() {
		return state;
	}

	public boolean setAddressState(String state) {
		state = state.trim();
		if (isStateAbbreviation(state)) {
			this.state = state;
			return true;
		}
		System.out.println("setAddressState - Invalid 2 letter state abbreviation.");
		return false;
	}

	public int getAddressZip() {
		return zip;
	}

	public boolean setAddressZip(int zip) {
		if (Integer.toString(zip).length() == 5) {
			this.zip = zip;
			return true;
		}
		System.out.println("setAddressZip - Invalid 5 digit zip code.");
		return false;
	}

	public int getAddressZipPlus4() {
		return zipPlus4;
	}

	public boolean setAddressZipPlus4(int zipPlus4) {
		if (Integer.toString(zipPlus4).length() == 4) {
			this.zipPlus4 = zipPlus4;
			return true;
		}
		System.out.println("setAddressZipPlus4 - Invalid 4 digit zip+4.");
		return false;
	}

	/** Method promptUsertoUpdateAddressInfo uses dialog boxes to prompt the user for the street number and name,
	 * 	 additional street info (if needed), city, state, zip code, zip code+4 (if known).
	 * @param Address newAddress - new address object shell with addressID assigned
	 */
	public boolean promptUsertoUpdateAddressInfo() {
//		boolean BadInput = true;
//		Skip the function if the newAddress.addressID is not assigned.
//		TODO After prompting display address information and re-prompt if the information is incorrect
//			userCancelled:
//			do {
				String streetName1 = ConsoleInput.getInputString("Please enter the address's street number and street name: ");
				streetName1 = streetName1.trim();
				this.street1 = streetName1;
				
				String streetName2 = ConsoleInput.getInputString("Please enter the address's additional street information "
						+ "(e.g. Apt#, Building#, Care of, etc.) (Enter - if none): ");
				streetName2 = streetName2.trim();
				if (streetName2.contains("-")) {
					this.street2 = "";
				} else {
					this.street2 = streetName2;
				}
				
				String city = ConsoleInput.getInputString("Please enter the address's city: ");
				city = city.trim();
				this.city = city;
				
				String state = ConsoleInput.getInputState("Please enter the two characters of a US State, Commonwealth, or Territory \n"
						+ "(e.g. IL for Illinois): ");
				this.state = state;
				
				Integer zip = ConsoleInput.getInputInt("Please enter the address's 5 digit zip code: ", 5);
				this.zip = zip;
				
				Integer zipPlus4 = ConsoleInput.getInputInt("Please enter the address's 4 digit zip code+4:\n"
								+ "Enter 0 if not known", 5);
				this.zipPlus4 = zipPlus4;
				
				return true;

//			} while (BadInput);	
//		System.out.println("promptUsertoUpdateAddressInfo - address information invalid");
//		return false;
	}
	
	/** Method promptUsertoUpdateAddressInfo updates the user information by prompting the user for the updated address name, 
	 * 	street number and name, additional street info (if needed), city, state, zip code, zip code+4 (if known),
	 * 	address e-mail, and if the new address can check out books right away. 
	 * 
	 * @param Address newAddress - new address object shell with addressID assigned
	 */
	public boolean promptUsertoUpdateAddressInfo(Address oldAddress) {
//TODO After prompting display address information and re-prompt if the information is incorrect
//		userCancelled:
//		do {
			String streetName1 = ConsoleInput.getInputString("Current street number and name: " 
			+ oldAddress.getAddressStreet1() + "\nPlease enter the address's street number and street name: ");
			streetName1 = streetName1.trim();
			this.street1 = streetName1;
			
			String streetName2 = ConsoleInput.getInputString("Current additional street information: " 
					+ oldAddress.getAddressStreet2() + "\nPlease enter the address's additional street information "
					+ "(e.g. Apt#, Building#, Care of, etc.) (Enter - if none): ");
			streetName2 = streetName2.trim();
			this.street2 = streetName2;
			if (streetName2.contains("-")) {
				this.street2 = "";
			} else {
				this.street2 = streetName2;
			}
			
			String city = ConsoleInput.getInputString("Current city: " + oldAddress.getAddressCity() 
				+ "\nPlease enter the address's city:");
			city = city.trim();
			this.city = city;
			
			String state = ConsoleInput.getInputState("Current state abbreviation: " 
					+ oldAddress.getAddressState() + "\nPlease enter the two characters of a US State, Commonwealth, or Territory \n"
					+ "(e.g. IL for Illinois):");
//			// If ConsoleInput return is null, then the user pressed Cancel, so break out of the loop.
//			if (state == null) {
//				break userCancelled;
//			}
			this.state = state;
			
			Integer zip = ConsoleInput.getInputInt("Current zip code: " 
					+ oldAddress.getAddressZip() + "\nPlease enter the address's 5 digit zip code:", 5);
			this.zip = zip;
			
			Integer zipPlus4 = ConsoleInput.getInputInt("Current zip code+4: " 
					+ oldAddress.getAddressZip() + "\nPlease enter the address's 4 digit zip code+4:", 4);
			this.zipPlus4 = zipPlus4;
			return true;
//		} while (false);	
//		System.out.println("promptUsertoUpdateAddressInfo - address information invalid");
//		return false;
	}
	
	
	/** Method isValidAddress checks the current Address object to ensure all properties have non-blank or null values.
	 * 	It prints the address to the console and if invalid, the first reason why it was found to be invalid.
	 * @param isNewAddress - true if the Address is new so it does not check for a address ID.
	 * @return boolean - true if the address information is valid, otherwise false.
	 */
	public boolean isValidAddress(boolean isNewAddress) {
		if (this.street1.isBlank() || this.street1.isEmpty()) {
			System.out.println("isValidAddress - address has no street information.");
			return false;
		}
		if (this.city.isBlank() || this.city.isEmpty()) {
			System.out.println("isValidAddress - address has no city information.");
			return false;
		}
		if (this.state.isBlank() || this.state.isEmpty()) {
			System.out.println("isValidAddress - address has no state information.");
			return false;
		}
		if (this.state.isBlank() || this.state.isEmpty() || !isStateAbbreviation(this.state)) {
			System.out.println("isValidAddress - address has no state information.");
			return false;
		}
		if (this.zip == 0 || Long.toString(this.zip).length() != 5) {
			System.out.println("isValidAddress - address zip code information is invalid.");
			return false;
		}
		return true;
	}
	
	/** Method isStateAbbreviation returns true if the input String represents a two letter US state, 
	 * commonwealth, or territory. Otherwise it returns false.
	 * */
	protected static boolean isStateAbbreviation(String inputString) {
		String[] abbreviationArray = {"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL",
				"IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ",
				"NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA",
				"WV","WI","WY","AS","DC","FM","GU","MH","MP","PW","PR","VI"};
		// Remove all white space from the userInput
		inputString = inputString.replaceAll("\\s", "");
		// Convert the inputString to all upper case before comparing to the abbreviationArray Strings.
		inputString = inputString.toUpperCase();
		//System.out.println("isStateAbbreviation - searching for " + inputString);
		for (int index = 0; index < abbreviationArray.length; index++) {
			//System.out.println("isStateAbbreviation - comparing to " + abbreviationArray[index]);
			if (inputString.contentEquals(abbreviationArray[index])) {
				return true;
			}
		}
		return false;
	}
	
	/** Method toString overrides the default Object.toString to summarize the address.
	 * @return String with the salient details of the address
	 */
	@Override
	public String toString() {
			String outputString = this.street1 + ", ";
		if (!this.street2.isEmpty()) {
			outputString += " " + this.street2 + " ";
		}
		outputString += this.city + ", " + this.state + "  " + this.zip;
		if (this.zipPlus4 != 0) {
			outputString += "-" + this.zipPlus4 + " ";
		}
		return outputString;
	}

}
