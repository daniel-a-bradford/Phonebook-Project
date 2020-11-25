package phonebook;

import java.util.Scanner;

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
 *  Character getInputChar({String userPrompt})
 *  Character getInputChar({String userPrompt}, {fromThis}, {toThis}
 *  Character getInputLetter({String userPrompt})
 *  boolean isValidChar(String, {int fromThis}, {int toThis}, {boolean silent})
 *  String getInputString({String userPrompt})
 *  String getInputString(String userPrompt, int numChars) - numDigits specifies the required number of digits for the user input
 *  String[] getInputStrings(int numberOfInputs, {String userPrompt})
 *  String getInputState(String userPrompt)
 *  boolean isValidString(String, {boolean silent})
 */

public class ConsoleInput {
	
	/** method addCancelOption provides a class-wide standard option to add to the user prompt
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
	 * If the user presses the Cancel button, return null. 
	 * @param userPrompt
	 */
	public static Boolean getInputBoolean(String userPrompt) {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		int userInt = 0;
		// Build the user prompt string with yes, no, and cancel options.
		userPrompt += "\nEnter 1 for yes or 2 for no: " + addCancelOption("");
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
		// Continue the loop if the string is a valid integer in the range from 1 to 2.
		// isValidInt will display applicable error messages.
		} while (!isValidInt(inputString, 1, 2));
		// User inputString has been validated so parse the string.
		userInt = Integer.parseInt(inputString);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		if (userInt == 1) {
			return true;
		} 
		return false;
	}
	
	/** getInputChoice prompts the user to make a choice based on a numbered list of options from 1 
	 * 	to numberOfOptions. If the user cancels, return null. 
	 * @param numberedListOfOptions
	 * @param numberOfOptions
	 * @return
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
					System.out.println("You did not select a valid option from 1 to " + 
							numberOfOptions + ". \nPlease try again.");
				} 
		} 
		return userSelected;
	}

//Integer	

	/** Prompt the user for a single integer input in the console. Use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static Integer getInputInt() {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		int userInt = 0;
		// Build the user prompt string with cancel options.
		String userPrompt = "Please enter an integer number: ";
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
		// Continue the loop if the string is a valid integer.
		// isValidInt will display applicable error messages.
		} while (!isValidInt(inputString));
		// User inputString has been validated so parse the string.
		userInt = Integer.parseInt(inputString);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userInt;
	}
	
	/** Prompt the user for a single integer input in the console. 
	 * If userPrompt is blank or empty use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 * @param userPrompt
	 */
	public static Integer getInputInt(String userPrompt) {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		int userInt = 0;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!isValidString(userPrompt)) {
			userPrompt = "Please enter an integer number:";
		}	
		// Build the user prompt string with cancel options.
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
		// Continue the loop if the string is a valid integer.
		// isValidInt will display applicable error messages.
		} while (!isValidInt(inputString));
		// User inputString has been validated so parse the string.
		userInt = Integer.parseInt(inputString);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userInt;
	}
	
	/** Prompt the user for a single integer input with a specified number of digits using the console. 
	 * Use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 * @param numDigits
	 */
	public static Integer getInputInt(int numDigits) {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		int userInt = 0;
		String userPrompt = "Please enter an integer with " + numDigits + "digits:";
		// Build the user prompt string with cancel options.
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
			if (inputString.length() != numDigits) {
				System.out.println("Your input did not have " + numDigits + "digits.\n");
			}
		// Continue the loop if the string is a valid integer.
		// isValidInt will display applicable error messages.
		} while (!isValidInt(inputString) && inputString.length() != numDigits);
		// User inputString has been validated so parse the string.
		userInt = Integer.parseInt(inputString);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userInt;
	}
	
	/** Prompt the user for a single integer input with a specified number of digits using the console. 
	 * If userPrompt is blank or empty use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 * @param userPrompt
	 * @param numDigits
	 */
	public static Integer getInputInt(String userPrompt, int numDigits) {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		int userInt = 0;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!isValidString(userPrompt)) {
			userPrompt = "Please enter an integer with " + numDigits + "digits:";
		}	
		// Build the user prompt string with cancel options.
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
			if (inputString.length() != numDigits) {
				System.out.println("Your input did not have " + numDigits + "digits.\n");
			}
		// Continue the loop if the string is a valid integer.
		// isValidInt will display applicable error messages.
		} while (!isValidInt(inputString) && inputString.length() != numDigits);
		// User inputString has been validated so parse the string.
		userInt = Integer.parseInt(inputString);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userInt;
	}
	
	/** Method numberOfDigits takes an integer and returns the number of digits the integer has.
	 * @param inputInt
	 * @return
	 */
	private static int numberOfDigits(int inputInt) {
		int digitCount = 0;
		while (inputInt != 0) {
			inputInt /= 10;
			digitCount++;
		}
		return digitCount;
	}
	
	/** Prompt the user for a set of integers input in the console. Use the default user prompt for each input.
	 * numberOfInputs parameter specifies how many inputs of this type are required.
	 * Validate the input and ask again if the input is not valid.
	 * @param numOfInputs
	 */
	public static int[] getInputInts(int numberOfInputs) {
		int[] userInputInts = new int[numberOfInputs];
		int arrayIndex = 0;
		Scanner input = new Scanner(System.in);
		String inputString = "";
		boolean validInput = true;
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			String userPrompt = "Please enter integer #" + (arrayIndex+1) + " of " + numberOfInputs;
			// Build the user prompt string with cancel options.
			userPrompt = addCancelOption(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
			// Check if the inputString is a valid integer.
			// isValidInt will display applicable error messages.
			validInput = !isValidInt(inputString);
			if (validInput) {
				// User inputString has been validated so parse the string.
				userInputInts[arrayIndex] = Integer.parseInt(inputString);
				// Increment the index to the next element in the array.
				arrayIndex++;
			}
		// Continue the loop if the string is a valid integer.
		} while (!validInput || (arrayIndex < userInputInts.length));
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userInputInts;
	}
	
	/** Method isValidInt tests to see if the inputString can be parsed as an integer.
	 * If so, it returns true, otherwise false.
	 * @param inputString
	 * @return
	 */
	public static boolean isValidInt(String inputString) {
		int inputInt = 0;
		// Try to interpret the user input string as an integer and assign it to userInt
		try {
			// If parseInt does not throw an exception, the user's input is an integer.
			inputInt = Integer.parseInt(inputString);
		} 
		// Integer.parseInt will throw a NumberFormatException if the input is not cannot be converted to an integer.
		catch (Exception NumberFormatException) {
			System.out.println("Your input was not an integer. \n"
					+ "Please enter a number without a decimal.\n");
			return false;
		}
		return true;
	}
	
	/** Method isValidInt tests to see if the inputString can be parsed as an integer.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message on the console.
	 * @param inputString
	 * @param silent
	 * @return
	 */
	public static boolean isValidInt(String inputString, boolean silent) {
		int inputInt = 0;
		// Try to interpret the user input string as an integer and assign it to userInt
		try {
			// If parseInt does not throw an exception, the user's input is an integer.
			inputInt = Integer.parseInt(inputString);
		} 
		// Integer.parseInt will throw a NumberFormatException if the input is not cannot be converted to an integer.
		catch (Exception NumberFormatException) {
			if (!silent) {
				System.out.println("Your input was not an integer. \n"
					+ "Please enter a number without a decimal.\n");
			}
			return false;
		}
		return true;
	}
	
	/** Method isValidInt tests to see if the inputString can be parsed as an integer.
	 * If it can, then it checks if the integer is between fromThis and toThis inclusively.
	 * If so, it returns true, otherwise false.
	 * @param inputString
	 * @param fromThis
	 * @param toThis
	 * @return
	 */
	public static boolean isValidInt(String inputString, int fromThis, int toThis) {
		int inputInt = 0;
		if (isValidInt(inputString)) {
			inputInt = Integer.parseInt(inputString);
		}
		if (inputInt >= fromThis && inputInt <= toThis) {
			return true;
		}
		System.out.println("Your input was not between " + fromThis + " and " + toThis + ".\n");
		return false;
	}
	
	/** Method isValidInt tests to see if the inputString can be parsed as an integer.
	 * If it can, then it checks if the integer is between fromThis and toThis inclusively.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message on the console.
	 * @param inputString
	 * @param fromThis
	 * @param toThis
	 * @param silent
	 * @return
	 */
	public static boolean isValidInt(String inputString, int fromThis, int toThis, boolean silent) {
		int inputInt = 0;
		if (isValidInt(inputString, silent)) {
			inputInt = Integer.parseInt(inputString);
		}
		if (inputInt >= fromThis && inputInt <= toThis) {
			return true;
		}
		if (!silent) {
			System.out.println("Your input was not between " + fromThis + " and " + toThis + ".");
		}
		return false;
	}
	
//Long
	
	/** Prompt the user for a single long integer input in the console. Use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static Long getInputLong() {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		long userLong = 0L;
		// Build the user prompt string with cancel options.
		String userPrompt = "Please enter an integer number: ";
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
		// Continue the loop if the string is a valid long integer.
		// isValidLong will display applicable error messages.
		} while (!isValidLong(inputString));
		// User inputString has been validated so parse the string.
		userLong = Long.parseLong(inputString);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userLong;
	}
	
	/** Prompt the user for a single integer input in the console. 
	 * If userPrompt is blank or empty use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static Long getInputLong(String userPrompt) {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		long userLong = 0L;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!isValidString(userPrompt)) {
			userPrompt = "Please enter an integer number:";
		}	
		// Build the user prompt string with cancel options.
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
		// Continue the loop if the string is a valid integer.
		// isValidLong will display applicable error messages.
		} while (!isValidLong(inputString));
		// User inputString has been validated so parse the string.
		userLong = Long.parseLong(inputString);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userLong;
	}
	
	/** Prompt the user for a single long integer input with a specified number of digits using the console. 
	 * If userPrompt is blank or empty use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static Long getInputLong(String userPrompt, int numDigits) {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		long userLong = 0L;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!isValidString(userPrompt)) {
			userPrompt = "Please enter an integer with " + numDigits + "digits:";
		}	
		// Build the user prompt string with cancel options.
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
			if (inputString.length() != numDigits) {
				System.out.println("Your input did not have " + numDigits + "digits.\n");
			}
		// Continue the loop if the string is a valid long integer.
		// isValidLong will display applicable error messages.
		} while (!isValidLong(inputString) && inputString.length() != numDigits);
		// User inputString has been validated so parse the string.
		userLong = Long.parseLong(inputString);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userLong;
	}
	
	/** Method numberOfDigits takes an integer and returns the number of digits the integer has.
	 * @param inputLong
	 * @return
	 */
	private static int numberOfDigits(long inputLong) {
		int digitCount = 0;
		while (inputLong != 0) {
			inputLong /= 10L;
			digitCount++;
		}
		return digitCount;
	}
	
	/** Prompt the user for a set of long integers input in the console. 
	 * 	Use the default user prompt for each input.
	 * numberOfInputs parameter specifies how many inputs of this type are required.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static long[] getInputLongs(int numberOfInputs) {
		long[] userInputLongs = new long[numberOfInputs];
		int arrayIndex = 0;
		Scanner input = new Scanner(System.in);
		String inputString = "";
		boolean validInput = true;
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			String userPrompt = "Please enter integer #" + (arrayIndex+1) + " of " + numberOfInputs;
			// Build the user prompt string with cancel options.
			userPrompt = addCancelOption(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
			// Check if the inputString is a valid long integer.
			// isValidLong will display applicable error messages.
			validInput = !isValidLong(inputString);
			if (validInput) {
				// User inputString has been validated so parse the string.
				userInputLongs[arrayIndex] = Long.parseLong(inputString);
				// Increment the index to the next element in the array.
				arrayIndex++;
			}
		// Continue the loop if the string is a valid integer.
		} while (!validInput || (arrayIndex < userInputLongs.length));
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userInputLongs;
	}
	
	/** Method isValidLong tests to see if the inputString can be parsed as a long integer.
	 * If so, it returns true, otherwise false.
	 * @param inputString
	 * @return
	 */
	public static boolean isValidLong(String inputString) {
		long inputLong = 0L;
		// Try to interpret the user input string as an integer and assign it to userLong
		try {
			// If parseLong does not throw an exception, the user's input is a long integer.
			inputLong = Long.parseLong(inputString);
		} 
		// Long.parseLong will throw a NumberFormatException if the input is not cannot be converted to an integer.
		catch (Exception NumberFormatException) {
			System.out.println("Your input was not an integer. \n"
					+ "Please enter a number without a decimal.\n");
			return false;
		}
		return true;
	}
	
	/** Method isValidLong tests to see if the inputString can be parsed as an integer.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message on the console.
	 * @param inputString
	 * @param silent
	 * @return
	 */
	public static boolean isValidLong(String inputString, boolean silent) {
		long inputLong = 0L;
		// Try to interpret the user input string as an integer and assign it to userLong
		try {
			// If parseLong does not throw an exception, the user's input is a long integer.
			inputLong = Long.parseLong(inputString);
		} 
		// Long.parseLong will throw a NumberFormatException if the input is not cannot be converted to an integer.
		catch (Exception NumberFormatException) {
			if (!silent) {
				System.out.println("Your input was not an integer. \n"
					+ "Please enter a number without a decimal.\n");
			}
			return false;
		}
		return true;
	}
	
	/** Method isValidLong tests to see if the inputString can be parsed as a long integer.
	 * If it can, then it checks if the integer is between fromThis and toThis inclusively.
	 * If so, it returns true, otherwise false.
	 * @param inputString
	 * @param fromThis
	 * @param toThis
	 * @return
	 */
	public static boolean isValidLong(String inputString, int fromThis, int toThis) {
		long inputLong = 0L;
		if (isValidLong(inputString)) {
			inputLong = Long.parseLong(inputString);
		}
		if (inputLong >= fromThis && inputLong <= toThis) {
			return true;
		}
		System.out.println("Your input was not between " + fromThis + " and " + toThis + ".\n");
		return false;
	}
	
	/** Method isValidLong tests to see if the inputString can be parsed as an integer.
	 * If it can, then it checks if the integer is between fromThis and toThis inclusively.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message on the console.
	 * @param inputString
	 * @param fromThis
	 * @param toThis
	 * @param silent
	 * @return
	 */
	public static boolean isValidLong(String inputString, int fromThis, int toThis, boolean silent) {
		long inputLong = 0L;
		if (isValidLong(inputString, silent)) {
			inputLong = Long.parseLong(inputString);
		}
		if (inputLong >= fromThis && inputLong <= toThis) {
			return true;
		}
		if (!silent) {
			System.out.println("Your input was not between " + fromThis + " and " + toThis + ".");
		}
		return false;
	}

//Doubles

	/** Prompt the user for a single decimal input in the console. Use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static Double getInputDouble() {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		double userDouble = 0.0;
		// Build the user prompt string with cancel options.
		String userPrompt = "Please enter an decimal number: ";
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
		// Continue the loop if the string is a valid decimal.
		// isValidDouble will display applicable error messages.
		} while (!isValidDouble(inputString));
		// User inputString has been validated so parse the string.
		userDouble = Double.parseDouble(inputString);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userDouble;
	}
	
	/** Prompt the user for a single decimal input in the console. 
	 * If userPrompt is blank or empty use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static Double getInputDouble(String userPrompt) {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		double userDouble = 0.0;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!isValidString(userPrompt)) {
			userPrompt = "Please enter an decimal number:";
		}	
		// Build the user prompt string with cancel options.
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
		// Continue the loop if the string is a valid decimal.
		// isValidDouble will display applicable error messages.
		} while (!isValidDouble(inputString));
		// User inputString has been validated so parse the string.
		userDouble = Double.parseDouble(inputString);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userDouble;
	}
	
	/** Prompt the user for a single decimal input with a specified number of digits using the console. 
	 * If userPrompt is blank or empty use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static Double getInputDouble(String userPrompt, int numDigits) {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		double userDouble = 0.0;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!isValidString(userPrompt)) {
			userPrompt = "Please enter an decimal with " + numDigits + "digits (not counting the .):";
		}	
		// Build the user prompt string with cancel options.
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
			// .length-1 will exclude the decimal point
			if (inputString.length() != numDigits - 1) {
				System.out.println("Your input did not have " + numDigits + "digits.\n");
			}
		// Continue the loop if the string is a valid decimal.
		// isValidDouble will display applicable error messages.
		} while (!isValidDouble(inputString) && inputString.length() != numDigits);
		// User inputString has been validated so parse the string.
		userDouble = Double.parseDouble(inputString);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userDouble;
	}
	
	/** Prompt the user for a set of decimals input in the console. Use the default user prompt for each input.
	 * numberOfInputs parameter specifies how many inputs of this type are required.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static double[] getInputDoubles(int numberOfInputs) {
		double[] userInputDoubles = new double[numberOfInputs];
		int arrayIndex = 0;
		Scanner input = new Scanner(System.in);
		String inputString = "";
		boolean validInput = true;
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			String userPrompt = "Please enter decimal #" + (arrayIndex+1) + " of " + numberOfInputs;
			// Build the user prompt string with cancel options.
			userPrompt = addCancelOption(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
			// Check if the inputString is a valid decimal.
			// isValidDouble will display applicable error messages.
			validInput = !isValidDouble(inputString);
			if (validInput) {
				// User inputString has been validated so parse the string.
				userInputDoubles[arrayIndex] = Double.parseDouble(inputString);
				// Increment the index to the next element in the array.
				arrayIndex++;
			}
		// Continue the loop if the string is a valid decimal.
		} while (!validInput || (arrayIndex < userInputDoubles.length));
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userInputDoubles;
	}
	
	/** Method isValidDouble tests to see if the inputString can be parsed as an decimal.
	 * If so, it returns true, otherwise false.
	 * @param inputString
	 * @return
	 */
	public static boolean isValidDouble(String inputString) {
		double inputDouble = 0.0;
		// Try to interpret the user input string as an decimal and assign it to userDouble
		try {
			// If parseDouble does not throw an exception, the user's input is an decimal.
			inputDouble = Double.parseDouble(inputString);
		} 
		// Double.parseDouble will throw a NumberFormatException if the input is not cannot be converted to an decimal.
		catch (Exception NumberFormatException) {
			System.out.println("Your input was not an decimal number. \n");
			return false;
		}
		return true;
	}
	
	/** Method isValidDouble tests to see if the inputString can be parsed as an decimal.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message on the console.
	 * @param inputString
	 * @param silent
	 * @return
	 */
	public static boolean isValidDouble(String inputString, boolean silent) {
		double inputDouble = 0.0;
		// Try to interpret the user input string as an decimal and assign it to userDouble
		try {
			// If parseDouble does not throw an exception, the user's input is an decimal.
			inputDouble = Double.parseDouble(inputString);
		} 
		// Double.parseDouble will throw a NumberFormatException if the input is not cannot be converted to an decimal.
		catch (Exception NumberFormatException) {
			if (!silent) {
				System.out.println("Your input was not an decimal. \n"
					+ "Please enter a number without a decimal.\n");
			}
			return false;
		}
		return true;
	}
	
	/** Method isValidDouble tests to see if the inputString can be parsed as an decimal.
	 * If it can, then it checks if the decimal is between fromThis and toThis inclusively.
	 * If so, it returns true, otherwise false.
	 * @param inputString
	 * @param fromThis
	 * @param toThis
	 * @return
	 */
	public static boolean isValidDouble(String inputString, double fromThis, double toThis) {
		double inputDouble = 0.0;
		if (isValidDouble(inputString)) {
			inputDouble = Double.parseDouble(inputString);
		}
		if (inputDouble >= fromThis && inputDouble <= toThis) {
			return true;
		}
		System.out.println("Your input was not between " + fromThis + " and " + toThis + ".\n");
		return false;
	}
	
	/** Method isValidDouble tests to see if the inputString can be parsed as an decimal.
	 * If it can, then it checks if the decimal is between fromThis and toThis inclusively.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message on the console.
	 * @param inputString
	 * @param fromThis
	 * @param toThis
	 * @param silent
	 * @return
	 */
	public static boolean isValidDouble(String inputString, double fromThis, double toThis, 
			boolean silent) {
		double inputDouble = 0.0;
		if (isValidDouble(inputString, silent)) {
			inputDouble = Double.parseDouble(inputString);
		}
		if (inputDouble >= fromThis && inputDouble <= toThis) {
			return true;
		}
		if (!silent) {
			System.out.println("Your input was not between " + fromThis + " and " + toThis + ".");
		}
		return false;
	}
	
//Character
	
	/** Prompt the user for a single character input in the console. Use the default prompt text.
	 * Validate the input is not empty. Takes only the first letter in the string.
	 * Return null if the user cancelled.
	 */
	public static Character getInputChar() {
			Scanner input = new Scanner(System.in);
			String inputString = "";
			char userChar = 'a';
			// Build the user prompt string with cancel options.
			String userPrompt = "Please enter a character:\n (will only take the first character)";
			userPrompt = addCancelOption(userPrompt);
			do {
				// Prompt the user for an input in the console and capture the user input as a string
				System.out.println(userPrompt);
				inputString = input.nextLine();
				inputString = inputString.trim();
				// Check if the user has cancelled and if so, return null.
				if (userCancelled(inputString)) {
					return null;
				}
			// Continue the loop if the string is a valid integer.
			// isValidInt will display applicable error messages.
			} while (!isValidChar(inputString));
			// User inputString has been validated so parse the string.
			userChar = inputString.charAt(0);
//			Appears to close the system.in as well as the scanner, messing up future scanners
//			input.close(); 
		return userChar;
	}
	
	/** Prompt the user for a single character input in the console. 
	 * Prompt the user with the string userPrompt.
	 * Validate the input is not empty. Takes only the first letter in the string.
	 * Return null if the user cancelled.
	 */
	public static Character getInputChar(String userPrompt) {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		char userChar = 'a';
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!isValidString(userPrompt)) {
			userPrompt = "Please enter a character:\n (will only take the first character)";
		}
		// Build the user prompt string with cancel options.
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
		// Continue the loop if the string is a valid integer.
		// isValidInt will display applicable error messages.
		} while (!isValidChar(inputString));
		// User inputString has been validated so parse the string.
		userChar = inputString.charAt(0);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userChar;
	}

	/** Prompt the user for a single character input in the console. Use the default prompt text.
	 * Validate the input is not empty. Takes only the first letter in the string.
	 * Prompt again if the number is not between fromThis and toThis (inclusive). 
	 * Compares using ASCII values.
	 * Return null if the user cancelled.
	 * @param fromThis
	 * @param toThis
	 */
	public static Character getInputChar(char fromThis, char toThis) {
			Scanner input = new Scanner(System.in);
			String inputString = "";
			char userChar = 'a';
			// Build the user prompt string with cancel options.
			String userPrompt = "Please enter a character between " + fromThis + " and " + toThis + ":"
					+ "\n (will only take the first character)";
			userPrompt = addCancelOption(userPrompt);
			do {
				// Prompt the user for an input in the console and capture the user input as a string
				System.out.println(userPrompt);
				inputString = input.nextLine();
				inputString = inputString.trim();
				// Check if the user has cancelled and if so, return null.
				if (userCancelled(inputString)) {
					return null;
				}
			// Continue the loop if the string is a valid integer.
			// isValidInt will display applicable error messages.
			} while (!isValidChar(inputString, fromThis, toThis));
			// User inputString has been validated so parse the string.
			userChar = inputString.charAt(0);
//			Appears to close the system.in as well as the scanner, messing up future scanners
//			input.close(); 
		return userChar;
	}
	
	/** Prompt the user for a single character input in the console. 
	 * Prompt the user with the string userPrompt.
	 * Validate the input is not empty. Takes only the first letter in the string.
	 * Prompt again if the number is not between fromThis and toThis (inclusive). 
	 * Compares using ASCII values.
	 * Return null if the user cancelled.
	 * @param fromThis
	 * @param toThis
	 */
	public static Character getInputChar(String userPrompt, char fromThis, char toThis) {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		char userChar = 'a';
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!isValidString(userPrompt)) {
			userPrompt = "Please enter a character between " + fromThis + " and " + toThis + ":"
					+ "\n (will only take the first character)";
		}
		// Build the user prompt string with cancel options.
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
		// Continue the loop if the string is a valid integer.
		// isValidInt will display applicable error messages.
		} while (!isValidChar(inputString));
		// User inputString has been validated so parse the string.
		userChar = inputString.charAt(0);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userChar;
	}
	
	/** Prompt the user for a single letter input in the console. Use the default prompt text.
	 * Validate the input as a letter A-Z or a-z and ask again if the input is not valid or is empty.
	 */
	public static Character getInputLetter() {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		char userChar = 'a';
		boolean validChar = false;
		String userPrompt = "Please enter a character:\n (will only take the first character)";
		// Build the user prompt string with cancel options.
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
			validChar = isValidChar(inputString);
			if (validChar) {
				 if (!Character.isLetter(inputString.charAt(0))) {
					System.out.println("The first character of your input was not a letter. \n"
								+ "Please try again with an upper or lowercase letter.");
				 }
			}
		// Continue the loop if the string is a valid integer.
		// isValidInt will display applicable error messages.
		} while (!validChar);
		// User inputString has been validated so parse the string.
		userChar = inputString.charAt(0);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userChar;
	}
	
	/** Prompt the user for a single letter input in the console. 
	 * Prompt the user with the string userPrompt.
	 * Validate the input as a letter A-Z or a-z and ask again if the input is not valid or is empty.
	 */
	public static Character getInputLetter(String userPrompt) {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		char userChar = 'a';
		boolean validChar = false;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!isValidString(userPrompt)) {
			userPrompt = "Please enter a character:\n (will only take the first character)";
		}
		// Build the user prompt string with cancel options.
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
			validChar = isValidChar(inputString);
			if (validChar) {
				 if (!Character.isLetter(inputString.charAt(0))) {
					System.out.println("The first character of your input was not a letter. \n"
								+ "Please try again with an upper or lowercase letter.");
				 }
			}
		// Continue the loop if the string is a valid integer.
		// isValidInt will display applicable error messages.
		} while (!validChar);
		// User inputString has been validated so parse the string.
		userChar = inputString.charAt(0);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userChar;
	}
	
	/** Method isValidChar tests to see if the inputString not is empty or blank.
	 * If so, it returns true, otherwise false.
	 * @param inputString
	 * @return
	 */
	public static boolean isValidChar(String inputString) {
		// If the input string is blank or empty advise the user and return false.
		if (inputString.isBlank() || inputString.isEmpty()) {
			System.out.println("You did not provide an input.\n");
			return false;
		}
		return true;
	}
	
	/** Method isValidChar tests to see if the inputString is not empty or blank.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message on the console.
	 * @param inputString
	 * @param silent
	 * @return
	 */
	public static boolean isValidChar(String inputString, boolean silent) {
		// If the input string is blank or empty advise the user (if not silent) and return false.
		if (inputString.isBlank() || inputString.isEmpty()) {
			if (!silent) {
				System.out.println("You did not provide an input.\n");
			}
			return false;
		}
		return true;
	}
	
	/** Method isValidChar tests to see if the inputString is not empty or blank.
	 * If it can, then it checks if the character is between fromThis and toThis inclusively
	 * using the ASCII values.
	 * If so, it returns true, otherwise false.
	 * @param inputString
	 * @param fromThis
	 * @param toThis
	 * @return
	 */
	public static boolean isValidChar(String inputString, char fromThis, char toThis) {
		char inputChar = 'a';
		if (isValidChar(inputString)) {
			inputChar = inputString.charAt(0);
		}
		if (inputChar >= fromThis && inputChar <= toThis) {
			return true;
		}
		System.out.println("Your input was not between " + fromThis + " and " + toThis + ".\n");
		return false;
	}
	
	/** Method isValidChar tests to see if the inputString can be parsed as an decimal.
	 * If it can, then it checks if the decimal is between fromThis and toThis inclusively.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message on the console.
	 * @param inputString
	 * @param fromThis
	 * @param toThis
	 * @param silent
	 * @return
	 */
	public static boolean isValidChar(String inputString, char fromThis, char toThis, boolean silent) {
		char inputChar = 'a';
		if (isValidChar(inputString)) {
			inputChar = inputString.charAt(0);
		}
		if (inputChar >= fromThis && inputChar <= toThis) {
			return true;
		}
		if (!silent) {
			System.out.println("Your input was not between " + fromThis + " and " + toThis + ".");
		}
		return false;
	}
	
//String
	
	/** Prompt the user for a string input in the console. Use the default prompt text.
	 * Validate the input is not empty.
	 */
	public static String getInputString() {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		String userPrompt = "Please enter an string of characters:";
		// Build the user prompt string with cancel options.
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
		// Continue the loop if the string is a valid integer.
		// isValidInt will display applicable error messages.
		} while (!isValidString(inputString));
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return inputString;
	}
	
	/** Prompt the user for a string input in the console. 
	 * If the input string is not empty, prompt the user with userPrompt. Validate the input is not empty.
	 */
	public static String getInputString(String userPrompt) {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!isValidString(userPrompt)) {
			userPrompt = "Please enter an string of characters:";
		}
		// Build the user prompt string with cancel options.
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
		// Continue the loop if the string is a valid integer.
		// isValidInt will display applicable error messages.
		} while (!isValidString(inputString));
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return inputString;
	}
	
	/** Prompt the user for a string input with numChars characters in the string using the console. 
	 * If the input string is not empty, prompt the user with userPrompt. Validate the input is not empty.
	 */
	public static String getInputString(int numChars) {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		boolean validString = false;
		String userPrompt = "Please enter an string of " + numChars + " characters:";
		// Build the user prompt string with cancel options.
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
			validString = !isValidString(inputString);
			// Update validString if the length of the input string have the required numChars.
			if (validString) {
				validString = inputString.length() == numChars;
			}
		// Continue the loop if the string is a valid string.
		// isValidInt will display applicable error messages.
		} while (!validString);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return inputString;
	}
	
	/** Prompt the user for a string input with numChars characters in the string using the console. 
	 * If the input string is not empty, prompt the user with userPrompt. Validate the input is not empty.
	 */
	public static String getInputString(String userPrompt, int numChars) {
		Scanner input = new Scanner(System.in);
		String inputString = "";
		boolean validString = false;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!isValidString(userPrompt)) {
			userPrompt = "Please enter an string of " + numChars + " characters:";
		}
		// Build the user prompt string with cancel options.
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
			validString = !isValidString(inputString);
			// Update validString if the length of the input string have the required numChars.
			if (validString) {
				validString = inputString.length() == numChars;
			}
		// Continue the loop if the string is a valid string.
		// isValidInt will display applicable error messages.
		} while (!validString);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return inputString;
	}
	
	/** Prompt the user for a multiple string inputs in the console. Use the default prompt text for each input.
	 * Validate the input is not empty.
	 */
	public static String[] getInputStrings(int numberOfInputs) {
		Scanner input = new Scanner(System.in);
		String[] userInputArray = new String[numberOfInputs];
		int arrayIndex = 0;
		String inputString = "";
		boolean validString = false;
		String userPrompt = "Please enter an string of characters:";
		// Build the user prompt string with cancel options.
		userPrompt = addCancelOption(userPrompt);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
			validString = isValidString(inputString);
			if (validString) {
				userInputArray[arrayIndex] = inputString;
				arrayIndex++;
			}
		// Continue the loop if the string is a valid integer.
		// isValidInt will display applicable error messages.
		} while (!isValidString(inputString) || arrayIndex < numberOfInputs);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userInputArray;
	}
	
	/** Prompt the user for a multiple string inputs in the console. 
	 * Use the array of user prompts to ask for each input. Validate the input is not empty.
	 */
	public static String[] getInputStrings(String[] userPromptArray) {
		Scanner input = new Scanner(System.in);
		String[] userInputArray = new String[userPromptArray.length];
		int arrayIndex = 0;
		String inputString = "";
		boolean validString = false;
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!isValidString(userPromptArray[arrayIndex])) {
			userPromptArray[arrayIndex] = "Please enter an string of characters:";
		}
		// Build the user prompt string with cancel options.
		userPromptArray[arrayIndex] = addCancelOption(userPromptArray[arrayIndex]);
		do {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPromptArray[arrayIndex]);
			inputString = input.nextLine();
			inputString = inputString.trim();
			// Check if the user has cancelled and if so, return null.
			if (userCancelled(inputString)) {
				return null;
			}
			validString = isValidString(inputString);
			if (validString) {
				userInputArray[arrayIndex] = inputString;
				arrayIndex++;
			}
		// Continue the loop if the string is a valid integer.
		// isValidInt will display applicable error messages.
		} while (!isValidString(inputString) || arrayIndex < userPromptArray.length);
//		Appears to close the system.in as well as the scanner, messing up future scanners
//		input.close(); 
		return userInputArray;
	}
	
	/** Method isValidString tests to see if the inputString not is empty or blank.
	 * If so, it returns true, otherwise false.
	 * @param inputString
	 * @return
	 */
	public static boolean isValidString(String inputString) {
		// If the input string is blank or empty advise the user and return false.
		if (inputString.isBlank() || inputString.isEmpty()) {
			System.out.println("You did not provide an input.\n");
			return false;
		}
		return true;
	}
	
	/** Method isValidString tests to see if the inputString is not empty or blank.
	 * If so, it returns true, otherwise false.
	 * If silent is true, then do not display an error message on the console.
	 * @param inputString
	 * @param silent
	 * @return
	 */
	public static boolean isValidString(String inputString, boolean silent) {
		// If the input string is blank or empty advise the user (if not silent) and return false.
		if (inputString.isBlank() || inputString.isEmpty()) {
			if (!silent) {
				System.out.println("You did not provide an input.\n");
			}
			return false;
		}
		return true;
	}
	
	/** Prompt the user for a string input representing a two letter US state, commonwealth, or territory 
	 * abbreviation in the console. 
	 * If the input string is not empty, prompt the user with userPrompt.
	 * Validate the input is not empty, advise and re-prompt the user.
	 * Validate the input is a state, territory or commonwealth two letter abbreviation, otherwise advise 
	 * and re-prompt the user.
	 */
	public static String getInputState(String userPrompt) {
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (!isValidString(userPrompt)) {
			userPrompt = "Please enter the two characters of a US State, Commonwealth, or Territory \n"
					+ "(e.g. IL for Illinois):";
		}
		String userInput = null;
		// Assume the input is invalid until after the method validates it.
		boolean badInput = true;
		Scanner input = new Scanner(System.in);
		// Loop to re-prompt the user for a valid input as needed.
		while (badInput) {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			userInput = input.nextLine();
			// Remove leading or trailing white space from the string.
			userInput = userInput.trim();
			// Convert the userInput to upper case in case it is a valid state abbreviation.
			userInput = userInput.toUpperCase();
			// Check if the user did not put anything in. 
			// If so prompt again, if not assign userChar to the first character in the string.
			if 	( !userInput.isBlank() ) {
				// Check if the user input is a valid US state, commonwealth or territory abbreviation.
				if (isStateAbbreviation(userInput)) {
					badInput = false;
				} else {
					// Advise the user the input is not a state and continue the loop
					System.out.println("Your input was not a two letter abbreviation for a US state, "
							+ "commonwealth, or territory. \nPlease try again.");
					badInput = true;
				}
			} else {
				// Advise the user the input is invalid and continue the loop
				System.out.println("You did not provide an input. \nPlease try again.");
				badInput = true;
			}
		}
		//input.close();
		return userInput;
	}
	
	/** Method isStateAbbreviation returns true if the input String represents a two letter US state, 
	 * commonwealth, or territory. Otherwise it returns false.
	 * */
	private static boolean isStateAbbreviation(String inputString) {
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
}
