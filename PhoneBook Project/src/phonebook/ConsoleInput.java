package phonebook;

import java.util.Scanner;

import javax.swing.JOptionPane;

/** This class consists of methods to request input of different types from the user, and validates the input.
 * If the input is invalid, the methods prompt the user for the input again.
 * If the user presses cancel, the methods will return null.
 * If multiple inputs of the same class are needed, use userInput[type]Array.
 * Parameters in curly brackets {} are optional
 * Methods contained -
 * 	Boolean getInputBoolean(String prompt) - prompt is a Yes or No question.
 * 	Integer getInputChoice(String numberedListOfOptions, int numberOfOptions)
 * 	Integer getInputInt({String userPrompt})
 * 	Integer getInputInt({String userPrompt}, int numDigits) - numDigits specifies the required number of digits for the user input
 * 	int[] getInputInts(int numberOfInputs, {String userPrompt}) 
 * 	boolean isValidInt(String, {int fromThis}, {int toThis}, {boolean silent}) 
 * 	Long getInputLong({String userPrompt})
 * 	Long getInputLong(String userPrompt, int numDigits) - numDigits specifies the required number of digits for the user input
 * 	long[] getInputLongs(int numberOfInputs, {String userPrompt}) 
 *  boolean isValidLong(String, {int fromThis}, {int toThis}, {boolean silent})
 *  double getInputDouble({String userPrompt})
 * 	double[] getInputDoubles(int numberOfInputs, {String userPrompt}) 
 * 	boolean isValidDouble(String, {int fromThis}, {int toThis}, {boolean silent})
 *  Character getInputChar({String userPrompt}, {fromThis}, {toThis}
 *  Character getInputLetter({String userPrompt})
 *  boolean isValidChar(String, {int fromThis}, {int toThis}, {boolean silent})
 *  String getInputString({String userPrompt})
 *  String getInputString(String userPrompt, int numChars) - numDigits specifies the required number of digits for the user input
 *  String[] getInputStrings(int numberOfInputs)
 *  String[] getInputStrings(String[] userPrompt)
 *  String getInputState({String userPrompt})
 *  boolean isValidString(String, {boolean silent})
 */

public class ConsoleInput {
	
	static StringChecker check = new StringChecker();
	
	/** Method getTrimmedUserString provides a class-wide standard way to prompt the user for and get input
	 *  from the user as a string. Methods in the class will parse the return string to get the input they
	 *  require or call this again to prompt the user. userCanCancel - true add the standard user cancel 
	 *  option to the userPrompt, false do not add the prompt. If the user cancels, return null as long as
	 *  userCanCancel is true, otherwise return the input.
	 * @param userPrompt
	 * @param userCanCancel
	 * @return String
	 */
	private static String getTrimmedUserString(String userPrompt, boolean userCanCancel) {
		Scanner input = new Scanner(System.in);
		if (userCanCancel) {
			userPrompt = addCancelOption(userPrompt);
		}
		System.out.println(userPrompt);
		// To avoid a NoSuchElement exception, if there is no NextLine return null before trying to fetching it.
		if (!input.hasNextLine()) {
			return null;
		}
		String userInput = input.nextLine().trim();
		// If the trimmed string contains the cancel string and the user can cancel, return null.
		if (userCancelled(userInput) && userCanCancel) {
			return null;
		}
		//input.close; //Appears to close the system.in as well as the scanner, messing up future scanners
		return userInput;
	}
	
	/** Method displayError is the class-wide standard way to display an error message specified by 
	 * 	errorString. If errorString is blank, display the standard error message.
	 * @param errorString
	 */
	private static void displayError(String errorString) {
		if (errorString.isBlank() || errorString.isEmpty()) {
			errorString = "You did not provide a valid input.";
		}
		System.out.println(errorString);
	}
	
	/** Method addCancelOption provides a class-wide standard option to add to the user prompt
	 *  to allow the user to cancel the operation rather than entering an input.
	 *  Currently the character to enter is ~
	 * @param prompt
	 * @return
	 */
	private static String addCancelOption(String prompt) {
		return prompt + "\nEnter ~ to exit";
	}
	
	/** method userCancelled checks to see if the user entered class-wide standard cancel character.
	 *  If so, it returns true, otherwise false.
	 * @param input
	 * @return
	 */
	private static boolean userCancelled(String input) {
		if (input.trim().contentEquals("~")) {
			return true;
		}
		return false;
	}
	
	/** Prompt the user to answer 1 for yes or 2 for no in response to the prompt message.
	 * If the user cancels, return null. 
	 * @param userPrompt
	 * @return Boolean
	 */
	public static Boolean getInputBoolean(String userPrompt) {
		// Prompt the user for an input in an Input Dialog box and capture the user input as a string
		int userInput = JOptionPane.showConfirmDialog(null, userPrompt);
		// showConfirmDialog returns 0 for clicking Yes, 1 for clicking No, and 2 for clicking Cancel. 
		if (userInput == 2) {
			// Set a flag to skip the remainder of the method and return null as a signal to the calling method.
			return (Boolean)null;
		} else if (userInput == 1) {
			return false;
		} 
		return true;
	}
	
	/** getInputChoice prompts the user to make a choice based on a numbered list of options from 1 
	 * 	to numberOfOptions. If the user cancels, return null. 
	 * @param numberedListOfOptions
	 * @param numberOfOptions
	 * @return Integer
	 */
	public static Integer getInputChoice(String numberedListOfOptions, int numberOfOptions) {
		boolean badInput = true;
		Integer userSelected = null;
		while (badInput) {
			userSelected = getInputInt(numberedListOfOptions);
			if (userSelected == null) {
				// If branchSelected is null, the user pressed cancel and wants to exit, so exit the loop.
				return (Integer)null;
			}
			if (userSelected >= 1 && userSelected <= numberOfOptions) {
					badInput = false;
				} else {
					// Advise the user the input is invalid and continue the loop
					displayError("You did not select a valid option from 1 to " + 
							numberOfOptions + ". \nPlease try again.");
				} 
		} 
		return userSelected;
	}

//Integer	

	/** Prompt the user for a single integer input. Use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 * If the user cancels, return null.
	 * @return Integer
	 */
	public static Integer getInputInt() {
		return getInputInt("");
	}
	
	/** Prompt the user for a single integer input. 
	 * If userPrompt is blank or empty use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 * If the user cancels, return null.
	 * @param userPrompt
	 * @return Integer
	 */
	public static Integer getInputInt(String userPrompt) {
		String inputString = "";
		int userInt = 0;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!check.isValidString(userPrompt)) {
			userPrompt = "Please enter an integer number:";
		}	
		do {
			// Prompt the user for an input and capture the user input as a string
			inputString = getTrimmedUserString(userPrompt, true);
			// Check if the user has cancelled and if so, return null.
			if (inputString == null) {
				return null;
			}
		// Continue the loop if the string is a valid integer.
		// check.isValidInt will display applicable error messages.
		} while (!check.isValidInt(inputString, false));
		// User inputString has been validated so parse the string.
		userInt = Integer.parseInt(inputString); 
		return userInt;
	}
	
	/** Prompt the user for a single integer input with a specified number of digits. 
	 * Use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 * If the user cancels, return null.
	 * @param numDigits
	 * @return Integer
	 */
	public static Integer getInputInt(int numDigits) {
		return getInputInt("", numDigits);
	}
	
	/** Prompt the user for a single integer input with a specified number of digits. 
	 * If userPrompt is blank or empty use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 * If the user cancels, return null.
	 * @param userPrompt
	 * @param numDigits
	 * @return Integer
	 */
	public static Integer getInputInt(String userPrompt, int numDigits) {
		String inputString = "";
		int userInt = 0;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!check.isValidString(userPrompt)) {
			userPrompt = "Please enter an integer with " + numDigits + " digits:";
		}	
		do {
			// Prompt the user for an input and capture the user input as a string
			inputString = getTrimmedUserString(userPrompt, true);
			// Check if the user has cancelled and if so, return null.
			if (inputString == null) {
				return null;
			}
		// Continue the loop if the string is a valid integer.
		// check.isValidInt will display applicable error messages.
		} while (!check.isValidInt(inputString, numDigits, false));
		// User inputString has been validated so parse the string.
		userInt = Integer.parseInt(inputString); 
		return userInt;
	}
	
	/** Prompt the user for an integer input. Use the default prompt text.
	 * Validate the input is not empty. 
	 * Prompt again if the number is not between fromThis and toThis (inclusive). 
	 * If the user cancels, return null.
	 * @param fromThis
	 * @param toThis
	 * @return Integer
	 */
	public static Integer getInputInt(int fromThis, int toThis) {
		return getInputInt("", fromThis, toThis);
	}
	
	/** Prompt the user for an integer input. If userPrompt is empty use the default prompt.
	 * Validate the input is not empty. 
	 * Prompt again if the number is not between fromThis and toThis (inclusive). 
	 * If the user cancels, return null.
	 * @param userPrompt
	 * @param fromThis
	 * @param toThis
	 * @return Integer
	 */
	public static Integer getInputInt(String userPrompt, int fromThis, int toThis) {
		String inputString = "";
		int userInt = 0;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!check.isValidString(userPrompt)) {
			userPrompt = "Please enter an integer between " + fromThis + " and " + toThis + ":";
		}
		do {
			// Prompt the user for an input and capture the user input as a string
			inputString = getTrimmedUserString(userPrompt, true);
			// Check if the user has cancelled and if so, return null.
			if (inputString == null) {
				return null;
			}
		// Continue the loop if the string is a valid integer.
		// check.isValidInt will display applicable error messages.
		} while (!check.isValidInt(inputString, fromThis, toThis, false));
		// User inputString has been validated so parse the string.
		userInt = inputString.charAt(0);
		return userInt;
	}
	
	/** Prompt the user for a set of integers input. Use the default user prompt for each input.
	 * numberOfInputs parameter specifies how many inputs of this type are required.
	 * Validate the input and ask again if the input is not valid.
	 * If the user cancels, return null.
	 * @param numOfInputs
	 * @return int[]
	 */
	public static int[] getInputInts(int numberOfInputs) {
		int[] userInputInts = new int[numberOfInputs];
		int arrayIndex = 0;
		Integer currentInt = 0;
		do {
			// Prompt the user for an input and capture the user input
			String userPrompt = "Please enter integer #" + (arrayIndex+1) + " of " + numberOfInputs;
			currentInt = getInputInt(userPrompt);
			if (currentInt != null) {
				// User has not cancelled, so store currentInt.
				userInputInts[arrayIndex] = currentInt;
				// Increment the index to the next element in the array.
				arrayIndex++;
			} else {
				return null;
			}
		// Continue the loop until the array is full
		} while (arrayIndex < userInputInts.length); 
		return userInputInts;
	}
		
//Long
	
	/** Prompt the user for a single long integer input. Use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 * If the user cancels, return null.
	 * @return Long
	 */
	public static Long getInputLong() {
		return getInputLong("");
	}
	
	/** Prompt the user for a single long integer input. 
	 * If userPrompt is blank or empty use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 * If the user cancels, return null.
	 * @param userPrompt
	 * @return Long
	 */
	public static Long getInputLong(String userPrompt) {
		String inputString = "";
		long userLong = 0L;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!check.isValidString(userPrompt)) {
			userPrompt = "Please enter an integer number:";
		}
		do {
			// Prompt the user for an input and capture the user input as a string
			inputString = getTrimmedUserString(userPrompt, true);
			// Check if the user has cancelled and if so, return null.
			if (inputString == null) {
				return null;
			}
		// Continue the loop if the string is a valid integer.
		// check.isValidLong will display applicable error messages.
		} while (!check.isValidLong(inputString, false));
		// User inputString has been validated so parse the string.
		userLong = Long.parseLong(inputString);
		return userLong;
	}
	
	/** Prompt the user for a single long integer input with a specified number of digits. 
	 * If userPrompt is blank or empty use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 * If the user cancels, return null.
	 * @param userPrompt
	 * @param numDigits
	 * @return Long
	 */
	public static Long getInputLong(int numDigits) {
		return getInputLong("", numDigits);
	}
	
	/** Prompt the user for a single long integer input with a specified number of digits. 
	 * If userPrompt is blank or empty use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 * If the user cancels, return null.
	 * @param userPrompt
	 * @param numDigits
	 * @return Long
	 */
	public static Long getInputLong(String userPrompt, int numDigits) {
		String inputString = "";
		long userLong = 0L;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!check.isValidString(userPrompt)) {
			userPrompt = "Please enter an integer with " + numDigits + " digits:";
		}	
		do {
			// Prompt the user for an input and capture the user input as a string
			inputString = getTrimmedUserString(userPrompt, true);
			// Check if the user has cancelled and if so, return null.
			if (inputString == null) {
				return null;
			}
		// Continue the loop if the string is a valid long integer.
		// check.isValidLong will display applicable error messages.
		} while (!check.isValidLong(inputString, numDigits, false));
		// User inputString has been validated so parse the string.
		userLong = Long.parseLong(inputString);
		return userLong;
	}
	
	/** Prompt the user for a long integer input. Use the default prompt text.
	 * Validate the input is not empty. 
	 * Prompt again if the number is not between fromThis and toThis (inclusive). 
	 * If the user cancels, return null.
	 * @param fromThis
	 * @param toThis
	 * @return Long
	 */
	public static Long getInputLong(long fromThis, long toThis) {
		return getInputLong("", fromThis, toThis);
	}
	
	/** Prompt the user for a long integer input. If userPrompt is empty, use the default prompt.
	 * Validate the input is not empty. 
	 * Prompt again if the number is not between fromThis and toThis (inclusive). 
	 * If the user cancels, return null.
	 * @param userPrompt
	 * @param fromThis
	 * @param toThis
	 * @return Long
	 */
	public static Long getInputLong(String userPrompt, long fromThis, long toThis) {
		String inputString = "";
		long userLong = 0L;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!check.isValidString(userPrompt)) {
			userPrompt = "Please enter an integer between " + fromThis + " and " + toThis + ":";
		}
		do {
			// Prompt the user for an input and capture the user input as a string
			inputString = getTrimmedUserString(userPrompt, true);
			// Check if the user has cancelled and if so, return null.
			if (inputString == null) {
				return null;
			}
		// Continue the loop if the string is a valid integer.
		// check.isValidLong will display applicable error messages.
		} while (!check.isValidLong(inputString, fromThis, toThis, false));
		// User inputString has been validated so parse the string.
		userLong = Long.parseLong(inputString);
		return userLong;
	}
	
	/** Prompt the user for a set of long integers input. 
	 * 	Use the default user prompt for each input.
	 * numberOfInputs parameter specifies how many inputs of this type are required.
	 * Validate the input and ask again if the input is not valid.
	 * If the user cancels, return null.
	 * @param numberOfInputs
	 * @return long[]
	 */
	public static long[] getInputLongs(int numberOfInputs) {
		long[] userInputLongs = new long[numberOfInputs];
		int arrayIndex = 0;
		Long currentLong = 0L;
		do {
			// Prompt the user for an input and capture the user input
			String userPrompt = "Please enter integer #" + (arrayIndex+1) + " of " + numberOfInputs;
			currentLong = getInputLong(userPrompt);
			if (currentLong != null) {
				// User has not cancelled, so store currentInt.
				userInputLongs[arrayIndex] = currentLong;
				// Increment the index to the next element in the array.
				arrayIndex++;
			} else {
				return null;
			}
		// Continue the loop until the array is full
		} while (arrayIndex < userInputLongs.length);
		return userInputLongs;
	}
	
//Doubles

	/** Prompt the user for a single decimal input. Use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 * If the user cancels, return null.
	 * @return Double
	 */
	public static Double getInputDouble() {
		return getInputDouble("");
	}
	
	/** Prompt the user for a single decimal input. 
	 * If userPrompt is blank or empty use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 * If the user cancels, return null.
	 * @param userPrompt
	 * @return Double
	 */
	public static Double getInputDouble(String userPrompt) {
		String inputString = "";
		double userDouble = 0.0;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!check.isValidString(userPrompt)) {
			userPrompt = "Please enter an decimal number:";
		}	
		do {
			// Prompt the user for an input and capture the user input as a string
			inputString = getTrimmedUserString(userPrompt, true);
			// Check if the user has cancelled and if so, return null.
			if (inputString == null) {
				return null;
			}
		// Continue the loop if the string is a valid decimal.
		// check.isValidDouble will display applicable error messages.
		} while (!check.isValidDouble(inputString, false));
		// User inputString has been validated so parse the string.
		userDouble = Double.parseDouble(inputString);
		return userDouble;
	}
	
	/** Prompt the user for a single decimal input with a specified number of digits. 
	 *	Use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 * If the user cancels, return null.
	 * @param numDigits
	 * @return Double
	 */
	public static Double getInputDouble(int numDigits) {
		return getInputDouble("", numDigits);
	}
	
	/** Prompt the user for a single decimal input with a specified number of digits. 
	 * If userPrompt is blank or empty use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 * If the user cancels, return null.
	 * @param userPrompt
	 * @param numDigits
	 * @return Double
	 */
	public static Double getInputDouble(String userPrompt, int numDigits) {
		String inputString = "";
		double userDouble = 0.0;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!check.isValidString(userPrompt)) {
			userPrompt = "Please enter an decimal with " + numDigits + " digits (not counting the decimal point.):";
		}	
		do {
			// Prompt the user for an input and capture the user input as a string
			inputString = getTrimmedUserString(userPrompt, true);
			// Check if the user has cancelled and if so, return null.
			if (inputString == null) {
				return null;
			}
		// Continue the loop if the string is a valid decimal.
		// check.isValidDouble will display applicable error messages.
		} while (!check.isValidDouble(inputString, numDigits, false));
		// User inputString has been validated so parse the string.
		userDouble = Double.parseDouble(inputString);
		return userDouble;
	}
	
	/** Prompt the user for a decimal number input. Use the default prompt text.
	 * Validate the input is not empty. 
	 * Prompt again if the number is not between fromThis and toThis (inclusive). 
	 * If the user cancels, return null.
	 * @param fromThis
	 * @param toThis
	 * @return Double
	 */
	
	public static Double getInputDouble(double fromThis, double toThis) {
		return getInputDouble("", fromThis, toThis);
	}
	
	/** Prompt the user for a decimal number input. If userPrompt is empty, use the default prompt.
	 * Validate the input is not empty. 
	 * Prompt again if the number is not between fromThis and toThis (inclusive). 
	 * If the user cancels, return null.
	 * @param userPrompt
	 * @param fromThis
	 * @param toThis
	 * @return Double
	 */
	public static Double getInputDouble(String userPrompt, double fromThis, double toThis) {
		String inputString = "";
		double userDouble = 0.0;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!check.isValidString(userPrompt)) {
			userPrompt = "Please enter an decimal number between " + fromThis + " and " + toThis + ":";
		}
		do {
			// Prompt the user for an input and capture the user input as a string
			inputString = getTrimmedUserString(userPrompt, true);
			// Check if the user has cancelled and if so, return null.
			if (inputString == null) {
				return null;
			}
		// Continue the loop if the string is a valid integer.
		// check.isValidDouble will display applicable error messages.
		} while (!check.isValidDouble(inputString, fromThis, toThis, false));
		// User inputString has been validated so parse the string.
		userDouble = inputString.charAt(0);
		return userDouble;
	}
	
	/** Prompt the user for a set of decimals input. Use the default user prompt for each input.
	 * numberOfInputs parameter specifies how many inputs of this type are required.
	 * Validate the input and ask again if the input is not valid.
	 * If the user cancels, return null.
	 * @param numberOfInputs
	 * @return double[]
	 */
	public static double[] getInputDoubles(int numberOfInputs) {
		double[] userInputDoubles = new double[numberOfInputs];
		int arrayIndex = 0;
		Double currentLong = 0.0;
		do {
			// Prompt the user for an input and capture the user input
			String userPrompt = "Please enter decimal #" + (arrayIndex+1) + " of " + numberOfInputs;
			currentLong = getInputDouble(userPrompt);
			if (currentLong != null) {
				// User has not cancelled, so store currentInt.
				userInputDoubles[arrayIndex] = currentLong;
				// Increment the index to the next element in the array.
				arrayIndex++;
			} else {
				return null;
			}
		// Continue the loop until the array is full
		} while (arrayIndex < userInputDoubles.length);
		return userInputDoubles;
	}
		
//Character
	
	/** Prompt the user for a single character input. Use the default prompt text.
	 * Validate the input is not empty. Takes only the first letter in the string.
	 * If the user cancels, return null.
	 * @return Character
	 */
	public static Character getInputChar() {
		return getInputChar("");
	}
	
	/** Prompt the user for a single character input. 
	 * Prompt the user with the string userPrompt.
	 * Validate the input is not empty. Takes only the first character in the string.
	 * If the user cancels, return null.
	 * @param userPrompt
	 * @return Character
	 */
	public static Character getInputChar(String userPrompt) {
		String inputString = "";
		char userChar = 'a';
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!check.isValidString(userPrompt)) {
			userPrompt = "Please enter a character:\n (will only take the first character)";
		}
		do {
			// Prompt the user for an input and capture the user input as a string
			inputString = getTrimmedUserString(userPrompt, true);
			// Check if the user has cancelled and if so, return null.
			if (inputString == null) {
				return null;
			}
		// Continue the loop if the string is a valid integer.
		// check.isValidChar will display applicable error messages.
		} while (!check.isValidChar(inputString, false));
		// User inputString has been validated so parse the string.
		userChar = inputString.charAt(0);
		return userChar;
	}

	/** Prompt the user for a single character input. Use the default prompt text.
	 * Validate the input is not empty. Takes only the first character in the string.
	 * Prompt again if the number is not between fromThis and toThis (inclusive). 
	 * Compares using ASCII values. If the user cancels, return null.
	 * @param fromThis
	 * @param toThis
	 * @return Character
	 */
	public static Character getInputChar(char fromThis, char toThis) {
		return getInputChar("", fromThis, toThis);
	}
	
	/** Prompt the user for a single character input. 
	 * Prompt the user with the string userPrompt.
	 * Validate the input is not empty. Takes only the first character in the string.
	 * Prompt again if the number is not between fromThis and toThis (inclusive). 
	 * Compares using ASCII values. If the user cancels, return null.
	 * @param fromThis
	 * @param toThis
	 * @return Character
	 */
	public static Character getInputChar(String userPrompt, char fromThis, char toThis) {
		String inputString = "";
		char userChar = 'a';
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!check.isValidString(userPrompt)) {
			userPrompt = "Please enter a character between " + fromThis + " and " + toThis + ":"
					+ "\n (will only take the first character)";
		}
		do {
			// Prompt the user for an input and capture the user input as a string
			inputString = getTrimmedUserString(userPrompt, true);
			// Check if the user has cancelled and if so, return null.
			if (inputString == null) {
				return null;
			}
		// Continue the loop if the string is a valid integer.
		// check.isValidChar will display applicable error messages.
		} while (!check.isValidChar(inputString, fromThis, toThis, false));
		// User inputString has been validated so parse the string.
		userChar = inputString.charAt(0);
		return userChar;
	}
	
	/** Prompt the user for a single letter input. Use the default prompt text.
	 * Validate the input as a letter A-Z or a-z and ask again if the input is not valid or is empty.
	 * If the user cancels, return null.
	 * @return Character
	 */
	public static Character getInputLetter() {
		return getInputLetter("");
	}
	
	/** Prompt the user for a single letter input. 
	 * Prompt the user with the string userPrompt.
	 * Validate the input as a letter A-Z or a-z and ask again if the input is not valid or is empty.
	 * If the user cancels, return null.
	 * @param userPrompt
	 * @return Character
	 */
	public static Character getInputLetter(String userPrompt) {
		String inputString = "";
		char userChar = 'a';
		boolean validChar = false;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!check.isValidString(userPrompt)) {
			userPrompt = "Please enter a letter (will only take the first character):";
		}
		do {
			// Prompt the user for an input and capture the user input as a string
			inputString = getTrimmedUserString(userPrompt, true);
			// Check if the user has cancelled and if so, return null.
			if (inputString == null) {
				return null;
			}
			validChar = check.isValidChar(inputString, false);
			if (validChar) {
				 if (!Character.isLetter(inputString.charAt(0))) {
					displayError(inputString.charAt(0) + " is not a letter. \n"
								+ "Please try again with an upper or lowercase letter.");
					validChar = false;
				 }
			}
		// Continue the loop if the string is a valid integer.
		// check.isValidChar will display applicable error messages.
		} while (!validChar);
		// User inputString has been validated so parse the string.
		userChar = inputString.charAt(0);
		return userChar;
	}
	
//String
	
	/** Prompt the user for a string input. Use the default prompt text.
	 * Validate the input is not empty.
	 * If the user cancels, return null.
	 * @return String
	 */
	public static String getInputString() {
		return getInputString("");
	}
	
	/** Prompt the user for a string input. 
	 * If the input string is not empty, prompt the user with userPrompt. Validate the input is not empty.
	 * If the user cancels, return null.
	 * @param userPrompt
	 * @return String
	 */
	public static String getInputString(String userPrompt) {
		String inputString = "";
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!check.isValidString(userPrompt)) {
			userPrompt = "Please enter an string of characters:";
		}
		do {
			// Prompt the user for an input and capture the user input as a string
			inputString = getTrimmedUserString(userPrompt, true);
			// Check if the user has cancelled and if so, return null.
			if (inputString == null) {
				return null;
			}
		// Continue the loop if the string is a valid integer.
		// check.isValidString will display applicable error messages.
		} while (!check.isValidString(inputString, false));
		return inputString;
	}
	
	/** Prompt the user for a string input with numChars characters in the string. 
	 * If the input string is not empty, prompt the user with userPrompt. Validate the input is not empty.
	 * If the user cancels, return null.
	 * @param numChars
	 * @return String
	 */
	public static String getInputString(int numChars) {
		return getInputString("", numChars);
	}
	
	/** Prompt the user for a string input with numChars characters in the string. 
	 * If the input string is not empty, prompt the user with userPrompt. Validate the input is not empty.
	 * If the user cancels, return null.
	 * @param userPrompt
	 * @param numChars
	 * @return String
	 */
	public static String getInputString(String userPrompt, int numChars) {
		String inputString = "";
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!check.isValidString(userPrompt)) {
			userPrompt = "Please enter an string of " + numChars + " characters:";
		}
		do {
			// Prompt the user for an input and capture the user input as a string
			inputString = getTrimmedUserString(userPrompt, true);
			// Check if the user has cancelled and if so, return null.
			if (inputString == null) {
				return null;
			}
		// Continue the loop if the string is a valid string.
		// check.isValidString will display applicable error messages.
		} while (!check.isValidString(inputString, numChars, false));
		return inputString;
	}
	
	/** Prompt the user for a multiple string inputs. Use the default prompt text for each input.
	 * Validate the input is not empty. If the user cancels, return null.
	 */
	public static String[] getInputStrings(int numberOfInputs) {
		String[] blankPrompts = new String[numberOfInputs];
		for (int index = 0; index < numberOfInputs; index++) {
			blankPrompts[index] = "";
		}
		return getInputStrings(blankPrompts);
	}
	
	/** Prompt the user for a multiple string inputs. 
	 * Use the array of user prompts to ask for each input. Validate the input is not empty.
	 * If the user cancels, return null.
	 * @param userPromptArray
	 * @return String[]
	 */
	public static String[] getInputStrings(String[] userPromptArray) {
		String[] userInputArray = new String[userPromptArray.length];
		int arrayIndex = 0;
		String currentString = "";
		String userPrompt = "";
		do {
			// Prompt the user for an input and capture the user input
			if (userPromptArray[arrayIndex].isBlank() || userPromptArray[arrayIndex].isEmpty()) {
				userPrompt = "Please enter integer #" + (arrayIndex+1) + " of " + userPromptArray.length;
			} else {
				userPrompt = userPromptArray[arrayIndex];
			}
			currentString = getInputString(userPrompt);
			if (currentString != null) {
				// User has not cancelled, so store currentInt.
				userInputArray[arrayIndex] = currentString;
				// Increment the index to the next element in the array.
				arrayIndex++;
			} else {
				return null;
			}
		// Continue the loop until the array is full
		} while (arrayIndex < userInputArray.length);
		return userInputArray;
	}
	
	/** Prompt the user for a string input representing a two letter US state, commonwealth, or territory 
	 * abbreviation. Use the default prompt string.
	 * Validate the input is not empty, advise and re-prompt the user.
	 * Validate the input is a state, territory or commonwealth two letter abbreviation, otherwise advise 
	 * and re-prompt the user.
	 * If the user cancels, return null.
	 */
	public static String getInputState() {
		return getInputState("");
	}
	
	/** Prompt the user for a string input representing a two letter US state, commonwealth, or territory 
	 * abbreviation. 
	 * If the input string is not empty, prompt the user with userPrompt.
	 * Validate the input is not empty, advise and re-prompt the user.
	 * Validate the input is a state, territory or commonwealth two letter abbreviation, otherwise advise 
	 * and re-prompt the user.
	 * If the user cancels, return null.
	 */
	public static String getInputState(String userPrompt) {
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!check.isValidString(userPrompt)) {
			userPrompt = "Please enter the two characters of a US State, Commonwealth, or Territory \n"
					+ "(e.g. IL for Illinois):";
		}
		String inputString = "";
		// Assume the input is invalid until after the method validates it.
		boolean notAState = true;
		// Loop to re-prompt the user for a valid input as needed.
		do {
			// Prompt the user for an input and capture the user input as a string
			inputString = getTrimmedUserString(userPrompt, true);
			if (inputString == null) {
				return null;
			}
			// Convert the userInput to upper case in case it is a valid state abbreviation.
			inputString = inputString.toUpperCase();
			// Check if the user did not put anything in. 
			// If so prompt again, if not assign userChar to the first character in the string.
			if 	(check.isValidString(inputString, false)) {
				// Check if the user input is a valid US state, commonwealth or territory abbreviation.
				if (check.isStateAbbreviation(inputString)) {
					notAState = false;
				} else {
					// Advise the user the input is not a state and continue the loop
					displayError("Your input was not a two letter abbreviation for a US state, "
							+ "commonwealth, or territory. \nPlease try again.");
					notAState = true;
				}
			} else {
				// Advise the user the input is invalid and continue the loop
				displayError("You did not provide an input. \nPlease try again.");
			}
		} while (!check.isValidString(inputString, false) || notAState);
		//input.close();
		return inputString;
	}
	
}
