package phonebook;

import java.util.Scanner;

/** This class consists of methods to request input of different types from the user, and validates the input.
 * If the input is invalid, the methods prompt the user for the input again.
 * If the user presses cancel, the methods will return null.
 * If multiple inputs of the same class are needed, use userInput[type]Array.
 * Parameters in curly brackets {} are optional
 * Methods contained -
 * 	Boolean getInputBoolean(String prompt) - prompt is a Yes or No question.
 * 	Integer getInputInt({String userPrompt})
 * 	Integer getInputInt(String userPrompt, int numDigits) - numDigits specifies the required number of digits for the user input
 * 	int[] getInputIntArray(int numberOfInputs, {String userPrompt}) 
 * 	Long getInputLong({String userPrompt})
 * 	Long getInputLong(String userPrompt, int numDigits) - numDigits specifies the required number of digits for the user input
 * 	long[] getInputLongArray(int numberOfInputs, {String userPrompt}) 
 *  double getInputDouble({String userPrompt})
 * 	double[] getInputDoubleArray(int numberOfInputs, {String userPrompt}) 
 *  Character getInputChar({String userPrompt})
 *  Character getInputLetter({String userPrompt})
 *  String getInputString({String userPrompt})
 *  String[] getInputStringArray(int numberOfInputs, {String userPrompt})
 *  String getInputState(String userPrompt)
 */
public class ConsoleInput {
	
	/** Prompt the user to answer 1 for yes or 2 for no in response to the prompt message.
	 * If the user presses the Cancel button, return null. */
	public static Boolean getInputBoolean(String prompt) {
		// Assume the input is invalid until after the method validates it.
		boolean badInput = true;
		// Prompt the user for an input in the console and capture the user input as a string
		System.out.println(prompt + "\nEnter 1 for yes and 2 for no: ");
		Scanner input = new Scanner(System.in);
		int userInt = 0;
		while (badInput) {
			// Try to interpret the user input string as an integer and assign it to userInt
			try {
				// If parseInt does not throw an exception, the user's input is an integer.
				userInt = input.nextInt();
				if (userInt == 1 || userInt == 2) {
					badInput = false;
				} else {
					System.out.println("Your input was not a valid choice please try again.");
					badInput = true;
				}
			} 
			// Integer.parseInt will throw a NumberFormatException if the input is not cannot be converted to an integer.
			catch (Exception NumberFormatException) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("Your input was not an integer. \n"
						+ "Please enter a number without a decimal.");
				badInput = true;
			}
			input.nextLine();
		}
//		//input.close();
		if (userInt == 1) {
			return true;
		} 
		return false;
	}
	
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
		// Assume the input is invalid until after the method validates it.
		boolean badInput = true;
		int userInt = 0;
		Scanner input = new Scanner(System.in);
		// Loop to re-prompt the user for a valid input as needed.
		while (badInput) {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println("Please enter an integer:");
			// Try to interpret the user input string as an integer and assign it to userInt
			try {
				// If parseInt does not throw an exception, the user's input is an integer.
				userInt = input.nextInt();
				badInput = false;
			} 
			// Integer.parseInt will throw a NumberFormatException if the input is not cannot be converted to an integer.
			catch (Exception NumberFormatException) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("Your input was not an integer. \n"
						+ "Please enter a number without a decimal.");
				badInput = true;
			}
			input.nextLine();
		}
		//input.close();
		return userInt;
	}
	
	/** Prompt the user for a single integer input in the console. 
	 * If userPrompt is blank or empty use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static Integer getInputInt(String userPrompt) {
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (userPrompt.isBlank() || userPrompt.isEmpty()) {
			userPrompt = "Please enter an integer number:";
		}	
		// Assume the input is invalid until after the method validates it.
		boolean badInput = true;
		int userInt = 0;
		Scanner input = new Scanner(System.in);
		// Loop to re-prompt the user for a valid input as needed.
		while (badInput) {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt); 
			// Try to interpret the user input string as an integer and assign it to userInt
			try {
				// If parseInt does not throw an exception, the user's input is an integer.
				userInt = input.nextInt();
				badInput = false;
			} 
			// Integer.parseInt will throw a NumberFormatException if the input is not cannot be converted to an integer.
			catch (Exception NumberFormatException) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("Your input was not an integer. \n"
						+ "Please enter a number without a decimal.");
				badInput = true;
			}
			input.nextLine();
		}
		//input.close();
		return userInt;
	}
	
	/** Prompt the user for a single integer input with a specified number of digits using the console. 
	 * If userPrompt is blank or empty use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static Integer getInputInt(String userPrompt, int numDigits) {
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (userPrompt.isBlank() || userPrompt.isEmpty()) {
			userPrompt = "Please enter an integer with " + numDigits + "digits:";
		}	
		// Assume the input is invalid until after the method validates it.
		boolean badInput = true;
		int userInt = 0;
		Scanner input = new Scanner(System.in);
		// Loop to re-prompt the user for a valid input as needed.
		while (badInput) {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			// Try to interpret the user input string as an integer and assign it to userInt
			try {
				// If parseInt does not throw an exception, the user's input is an integer.
				userInt = input.nextInt();
				if (numberOfDigits(userInt) == numDigits) {
					badInput = false;
				} else {
					// Advise the user the input is invalid and continue the loop
					System.out.println("Your input did not have the correct number of digits. \n"
							+ "Please enter a number with " + numDigits + " digits.");
				}
			} 
			// Integer.parseInt will throw a NumberFormatException if the input is not cannot be converted to an integer.
			catch (Exception NumberFormatException) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("Your input was not an integer. \n"
						+ "Please enter a number without a decimal.");
				badInput = true;
			}
			input.nextLine();
		}
		//input.close();
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
	 */
	public static int[] getInputInt(int numberOfInputs) {
		int[] userInputInts = new int[numberOfInputs];
		int arrayIndex = 0;
		boolean badInput = true;
		Scanner input = new Scanner(System.in);
		// Display a prompt for the user to enter data. 
		// Check input and if bad, advise the user and loop to prompt again until they provide proper input.
		// Continue loop until the array is full of good inputs (i.e. no bad input or an arrayIndex less than its length.)
		while (badInput || (arrayIndex < userInputInts.length)) {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println("Please enter integer #" + (arrayIndex+1) + " of " + numberOfInputs);
			// Try to interpret the user input string as an integer and assign it to userInt
			try {
				userInputInts[arrayIndex] = input.nextInt();
				// Increment the index to the next element in the array.
				arrayIndex++;
				// If the code above did not throw an exception, the input is valid. Exit the loop if all is complete.
				badInput = false;
			} 
			// Integer.parseInt will throw a NumberFormatException if the input is not cannot be converted to an integer.
			catch (Exception NumberFormatException) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("Your input was not an integer. \n"
						+ "Please enter a number without a decimal.");
				badInput = true;
			}
			input.nextLine();
		}
		//input.close();
		return userInputInts;
	}
	
//Long
//TODO Fix this to use the console
	/** Prompt the user for a single long integer input in the console. Use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static Long getInputLong() {
		// Assume the input is invalid until after the method validates it.
		boolean badInput = true;
		long userLong = 0;
		Scanner input = new Scanner(System.in);
		// Loop to re-prompt the user for a valid input as needed.
		while (badInput) {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println("Please enter an integer number:");
			// Try to interpret the user input string as a long integer and assign it to userLong
			try {
				// If Long.parseLong does not throw an exception, the user's input is a long integer.
				userLong = input.nextLong();
				badInput = false;
			} 
			// Integer.parseInt will throw a NumberFormatException if the input is not cannot be converted to a long integer.
			catch (Exception NumberFormatException) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("Your input was not an integer. \n"
						+ "Please enter a number without a decimal.");
				badInput = true;
			}
			input.nextLine();
		}
		//input.close();
		return userLong;
	}
	
	/** Prompt the user for a single long integer input in the console. 
	 * If userPrompt is blank or empty use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static Long getInputLong(String userPrompt) {
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (userPrompt.isBlank() || userPrompt.isEmpty()) {
			userPrompt = "Please enter an integer number:";
		}	
		// Assume the input is invalid until after the method validates it.
		boolean badInput = true;
		long userLong = 0;
		Scanner input = new Scanner(System.in);
		// Loop to re-prompt the user for a valid input as needed.
		while (badInput) {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			// Try to interpret the user input string as a long integer and assign it to userLong
			try {
				// If Long.parseLong does not throw an exception, the user's input is a long integer.
				userLong = input.nextLong();
				badInput = false;
			} 
			// Integer.parseInt will throw a NumberFormatException if the input is not cannot be converted to a long integer.
			catch (Exception NumberFormatException) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("Your input was not an integer. \n"
						+ "Please enter a number without a decimal.");
				badInput = true;
			}
			input.nextLine();
		}
		//input.close();
		return userLong;
	}
	/** Prompt the user for a single long integer input with a specified number of digits using the console. 
	 * If userPrompt is blank or empty use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static Long getInputLong(String userPrompt, int numDigits) {
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (userPrompt.isBlank() || userPrompt.isEmpty()) {
			userPrompt = "Please enter an integer with " + numDigits + "digits:";
		}	
		// Assume the input is invalid until after the method validates it.
		boolean badInput = true;
		long userLong = 0;
		Scanner input = new Scanner(System.in);
		// Loop to re-prompt the user for a valid input as needed.
		while (badInput) {
			// Prompt the user for an input in the console
			System.out.println(userPrompt);
			// Try to interpret the user input string as a long integer and assign it to userLong
			try {
				// If Long.parseLong does not throw an exception, the user's input is a long integer.
				userLong = input.nextLong();
				if (numberOfDigits(userLong) == numDigits) {
					badInput = false;
				} else {
					// Advise the user the input is invalid and continue the loop
					System.out.println("Your input did not have the correct number of digits. \n"
							+ "Please enter a number with " + numDigits + " digits.");
				}
			} 
			// Integer.parseInt will throw a NumberFormatException if the input is not cannot be converted to a long integer.
			catch (Exception NumberFormatException) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("Your input was not an integer. \n"
						+ "Please enter a number without a decimal.");
				badInput = true;
			}
			input.nextLine();
		}
		//input.close();
		return userLong;
	}
	
	/** Prompt the user for a set of long integers input in the console. Use the default user prompt for each input.
	 * numberOfInputs parameter specifies how many inputs of this type are required.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static long[] getInputLong(int numberOfInputs) {
		long[] userInputLongs = new long[numberOfInputs];
		int arrayIndex = 0;
		boolean badInput = true;
		Scanner input = new Scanner(System.in);
		// Display a prompt for the user to enter data. 
		// Check input and if bad, advise the user and loop to prompt again until they provide proper input.
		// Continue loop until the array is full of good inputs (i.e. no bad input or an arrayIndex less than its length.)
		while (badInput || (arrayIndex < userInputLongs.length)) {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println("Please enter integer #" + (arrayIndex+1) + " of " + numberOfInputs);
			// Try to interpret the user input string as a long integer and assign it to userInputLongs
			try {
				userInputLongs[arrayIndex] = input.nextLong();
				// Increment the index to the next element in the array.
				arrayIndex++;
				// If the code above did not throw an exception, the input is valid. Exit the loop if all is complete.
				badInput = false;
			} 
			// Long.parseLong will throw a NumberFormatException if the input is not cannot be converted to an integer.
			catch (Exception NumberFormatException) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("Your input was not an integer. \n"
						+ "Please enter a number without a decimal.");
				badInput = true;
			}
			input.nextLine();
		}
		//input.close();
		return userInputLongs;
	}
	
	/** Prompt the user for a set of long integers input in the console. 
	 * If the current userPromptArray is blank or empty use the default user prompt.
	 * numberOfInputs parameter specifies how many inputs of this type are required.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static long[] getInputLong(int numberOfInputs, String[] userPromptArray) {
		long[] userInputLongs = new long[numberOfInputs];
		int arrayIndex = 0;
		boolean badInput = true;
		Scanner input = new Scanner(System.in);
		// Display a prompt for the user to enter data. 
		// Check input and if bad, advise the user and loop to prompt again until they provide proper input.
		// Continue loop until the array is full of good inputs (i.e. no bad input or an arrayIndex less than its length.)
		while (badInput || (arrayIndex < userInputLongs.length)) {
			// Check if the user prompt text is empty or blank and use the default prompt if it is.
			if (userPromptArray[arrayIndex].isBlank() || userPromptArray[arrayIndex].isEmpty()) {
				userPromptArray[arrayIndex] = "Please enter integer #" + (arrayIndex+1) + " of " + numberOfInputs;
			}
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPromptArray[arrayIndex]);
			// Try to interpret the user input string as a long integer and assign it to userInputLongs current element.
			try {
				userInputLongs[arrayIndex] = input.nextLong();
				// Increment the index to the next element in the array.
				arrayIndex++;
				// If the code above did not throw an exception, the input is valid. Exit the loop if all is complete.
				badInput = false;
			} 
			// Long.parseLong will throw a NumberFormatException if the input is not cannot be converted to a long integer.
			catch (Exception NumberFormatException) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("Your input was not an integer. \n"
						+ "Please enter a number without a decimal.");
				badInput = true;
			}
			input.nextLine();
		}
		//input.close();
		return userInputLongs;
	}
	
	/** Method numberOfDigits takes a long integer and returns the number of digits the long integer has.
	 * @param inputLong
	 * @return
	 */
	private static long numberOfDigits(long inputLong) {
		int digitCount = 0;
		while (inputLong != 0) {
			inputLong /= 10;
			digitCount++;
		}
		return digitCount;
	}
	
//Doubles

	/** Prompt the user for a single double input in the console. Use the default user prompt.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static double getInputDouble() {
		// Assume the input is invalid until after the method validates it.
		boolean badInput = true;
		double userDouble = 0.0;
		Scanner input = new Scanner(System.in);
		// Loop to re-prompt the user for a valid input as needed.
		while (badInput) {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println("Please enter a decimal number: ");
			// Try to interpret the user input string as a double and assign it to userInputInts
			try {
				// If parseDouble does not throw an exception, the user's input is a double.
				userDouble = input.nextDouble();
				badInput = false;
			} 
			// Double.parseDouble will throw a NumberFormatException if the input is not cannot be converted to an integer.
			catch (Exception NumberFormatException) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("Your input was not a decimal number.");
				badInput = true;
			}
			input.nextLine();
		}
		//input.close();
		return userDouble;
	}
	
	/** Prompt the user for a single double input in the console. 
	 * Prompt the user with userPrompt unless it is empty or blank. Otherwise provide the default prompt.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static double getInputDouble(String userPrompt) {
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (userPrompt.isBlank() || userPrompt.isEmpty()) {
			userPrompt = "Please enter a decimal number:";
		}	
		// Assume the input is invalid until after the method validates it.
		boolean badInput = true;
		double userInt = 0.0;		
		Scanner input = new Scanner(System.in);
		// Loop to re-prompt the user for a valid input as needed.
		while (badInput) {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			// Try to interpret the user input string as a double and assign it to userInputInts
			try {
				// If parseDouble does not throw an exception, the user's input is a double.
				userInt = input.nextDouble();
				badInput = false;
			} 
			// Double.parseDouble will throw a NumberFormatException if the input is not cannot be converted to an integer.
			catch (Exception NumberFormatException) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("Your input was not a decimal number.");
				badInput = true;
			}
			input.nextLine();
		}
		//input.close();
		return userInt;
	}
	
	/** Prompt the user for a set of type double numbers input in the console. Use the default user prompt.
	 * numberOfInputs parameter specifies how many inputs of this type are required.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static double[] getInputDoubleArray(int numberOfInputs) {
		double[] userInputDouble = new double[numberOfInputs];
		int arrayIndex = 0;
		boolean badInput = true;
		Scanner input = new Scanner(System.in);
		// Display a prompt for the user to enter data. 
		// Check input and if bad, advise the user and loop to prompt again until they provide proper input.
		// Continue loop until the array is full of good inputs (i.e. no bad input or an arrayIndex less than its length.)
		while (badInput || (arrayIndex < userInputDouble.length)) {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println("Please enter decimal #" + (arrayIndex+1) + " of " + numberOfInputs);
			// Try to interpret the user input string as an integer and assign it to userInputInts
			try {
				userInputDouble[arrayIndex] = input.nextDouble();
				// Increment the index to the next element in the array.
				arrayIndex++;
				// If the code above did not throw an exception, the input is valid. Exit the loop if all is complete.
				badInput = false;
			} 
			// Integer.parseInt will throw a NumberFormatException if the input is not cannot be converted to an integer.
			catch (Exception NumberFormatException) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("Your input was not a decimal number.");
				badInput = true;
			}
			input.nextLine();
		}
		//input.close();
		return userInputDouble;
	}
	
	/** Prompt the user for a set of type double numbers input in the console. 
	 * numberOfInputs parameter specifies how many inputs of this type are required.
	 * Prompt the user with the default prompt if that element of userPromptArray is empty or blank.
	 * Validate the input and ask again if the input is not valid.
	 */
	public static double[] getInputDoubleArray(int numberOfInputs, String[] userPromptArray) {
		double[] userInputDouble = new double[numberOfInputs];
		int arrayIndex = 0;
		boolean badInput = true;
		Scanner input = new Scanner(System.in);
		// Display a prompt for the user to enter data. 
		// Check input and if bad, advise the user and loop to prompt again until they provide proper input.
		// Continue loop until the array is full of good inputs (i.e. no bad input or an arrayIndex less than its length.)
		while (badInput || (arrayIndex < userInputDouble.length)) {
			// Check if the user prompt text is empty or blank and use the default prompt if it is.
			if (userPromptArray[arrayIndex].isBlank() || userPromptArray[arrayIndex].isEmpty()) {
				userPromptArray[arrayIndex] = "Please enter decimal #" + (arrayIndex+1) + " of " + numberOfInputs;
			}
			// Prompt the user for an input in the console 
			System.out.println(userPromptArray[arrayIndex]);
			// Try to interpret the user input string as an double and assign it to userInputInts
			try {
				userInputDouble[arrayIndex] = input.nextDouble();
				// Increment the index to the next element in the array.
				arrayIndex++;
				// If the code above did not throw an exception, the input is valid. Exit the loop if all is complete.
				badInput = false;
			} 
			// Integer.parseInt will throw a NumberFormatException if the input is not cannot be converted to an integer.
			catch (Exception NumberFormatException) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("Your input was not a decimal number.");
				badInput = true;
			}
			input.nextLine();
		}
		//input.close();
		return userInputDouble;
	}
	
//Character
	
	/** Prompt the user for a single character input in the console. Use the default prompt text.
	 * Validate the input is not empty. Take only the first letter in the string.
	 */
	public static Character getInputChar() {
		// Assume the input is invalid until after the method validates it.
		boolean badInput = true;
		char userChar = 'a';
		Scanner input = new Scanner(System.in);
		// Loop to re-prompt the user for a valid input as needed.
		while (badInput) {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println("Please enter a character:\n (will only take the first character)");
			String userInput = input.nextLine();
			// Trim the leading and trailing white space from the string.
			userInput = userInput.trim();
			// Check if the user did not put anything in. 
			// If so prompt again, if not assign userChar to the first character in the string.
			if 	( userInput.isBlank() ) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("You did not provide an input. \nPlease try again.");
				badInput = true;
			} else {
				userChar = userInput.charAt(0);
				badInput = false;
			}
		}
		//input.close();
		return userChar;
	}
	
	/** Prompt the user for a single character input in the console. 
	 * Prompt the user with the string userPrompt.
	 * Validate the input is not empty. Take only the first letter in the string.
	 */
	public static Character getInputChar(String userPrompt) {
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (userPrompt.isBlank() || userPrompt.isEmpty()) {
			userPrompt = "Please enter a character:\n (will only take the first character)";
		}
		// Assume the input is invalid until after the method validates it.
		boolean badInput = true;
		char userChar = 'a';
		Scanner input = new Scanner(System.in);
		// Loop to re-prompt the user for a valid input as needed.
		while (badInput) {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			String userInput = input.nextLine();
			// Trim the leading and trailing white space from the string.
			userInput = userInput.trim();
			// Check if the user did not put anything in. 
			// If so prompt again, if not assign userChar to the first character in the string.
			if 	( userInput.isBlank() ) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("You did not provide an input. \nPlease try again.");
				badInput = true;
			} else {
				userChar = userInput.charAt(0);
				badInput = false;
			}
		}
		//input.close();
		return userChar;
	}
	
	/** Prompt the user for a single letter input in the console. Use the default prompt text.
	 * Validate the input as a letter A-Z or a-z and ask again if the input is not valid or is empty.
	 */
	public static Character getInputLetter() {
		// Assume the input is invalid until after the method validates it.
		boolean badInput = true;
		char userLetter = 'a';
		Scanner input = new Scanner(System.in);
		// Loop to re-prompt the user for a valid input as needed.
		while (badInput) {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println("Please enter a letter:\n (will only take the first character)");
			String userInput = input.nextLine();
			// Trim the leading and trailing white space from the string.
			userInput = userInput.trim();
			// Check if the user did not put anything in. 
			// If so prompt again, if not assign userChar to the first character in the string.
			if 	( userInput.isBlank() ) {
				// Advise the user the input is empty and continue the loop
				System.out.println("You did not provide an input. \nPlease try again.");
				badInput = true;
			// Check if the user did not put in a letter by checking ASCII values are outside the range of letters.
			// A is 65 to Z which is 90 and a is 97 to z which is 122
			} else if (!Character.isLetter(userInput.charAt(0))) {
				System.out.println("The first character of your input was not a letter. \n"
						+ "Please try again with an upper or lowercase letter.");
				badInput = true;
			} else {
				userLetter = userInput.charAt(0);
				// Input is good, so exit the loop.
				badInput = false;	
			} 
		}
		//input.close();
		return userLetter;
	}
	
	/** Prompt the user for a single letter input in the console. 
	 * Prompt the user with the string userPrompt.
	 * Validate the input as a letter A-Z or a-z and ask again if the input is not valid or is empty.
	 */
	public static Character getInputLetter(String userPrompt) {
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (userPrompt.isBlank() || userPrompt.isEmpty()) {
			userPrompt = "Please enter a letter:\n (will only take the first character)";
		}
		// Assume the input is invalid until after the method validates it.
		boolean badInput = true;
		char userLetter = 'a';
		Scanner input = new Scanner(System.in);
		// Loop to re-prompt the user for a valid input as needed.
		while (badInput) {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPrompt);
			String userInput = input.nextLine();
			// Trim the leading and trailing white space from the string.
			userInput = userInput.trim();
			// Check if the user did not put anything in. 
			// If so prompt again, if not assign userChar to the first character in the string.
			if 	( userInput.isBlank() ) {
				// Advise the user the input is empty and continue the loop
				System.out.println("You did not provide an input. \nPlease try again.");
				badInput = true;
			// Check if the user did not put in a letter by checking ASCII values are outside the range of letters.
			// A is 65 to Z which is 90 and a is 97 to z which is 122
			} else if (!Character.isLetter(userInput.charAt(0))) {
				System.out.println("The first character of your input was not a letter. \n"
						+ "Please try again with an upper or lowercase letter.");
				badInput = true;
			} else {
				userLetter = userInput.charAt(0);
				// Input is good, so exit the loop.
				badInput = false;	
			} 
		}
		//input.close();
		return userLetter;
	}
	
//String
	
	/** Prompt the user for a string input in the console. Use the default prompt text.
	 * Validate the input is not empty.
	 */
	public static String getInputString() {
		String userInput = null;
		// Assume the input is invalid until after the method validates it.
		boolean badInput = true;
		Scanner input = new Scanner(System.in);
		// Loop to re-prompt the user for a valid input as needed.
		while (badInput) {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println("Please enter an string of characters:"); 
			userInput = input.nextLine();
			// Trim the leading and trailing white space from the string.
			userInput = userInput.trim();
			// Check if the user did not put anything in. 
			// If so prompt again, if not assign userChar to the first character in the string.
			if 	( !userInput.isBlank() ) {
				badInput = false;
			} else {
				// Advise the user the input is invalid and continue the loop
				System.out.println("You did not provide an input. \nPlease try again.");
				badInput = true;
			}
		}
		//input.close();
		return userInput;
	}
	
	/** Prompt the user for a string input in the console. 
	 * If the input string is not empty, prompt the user with userPrompt. Validate the input is not empty.
	 */
	public static String getInputString(String userPrompt) {
		// Check if the user prompt text is empty or blank and use the default prompt if it is.
		if (userPrompt.isBlank() || userPrompt.isEmpty()) {
			userPrompt = "Please enter an string of characters:";
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
			// Trim the leading and trailing white space from the string.
			userInput = userInput.trim();
			// Check if the user did not put anything in. 
			// If so prompt again, if not assign userChar to the first character in the string.
			if 	( !userInput.isBlank() ) {
				badInput = false;
			} else {
				// Advise the user the input is invalid and continue the loop
				System.out.println("You did not provide an input. \nPlease try again.");
				badInput = true;
			}
		}
		//input.close();
		return userInput;
	}
	
	/** Prompt the user for a multiple string inputs in the console. Use the default prompt text for each input.
	 * Validate the input is not empty.
	 */
	public static String[] getInputString(int numberOfInputs) {
		String[] userInputArray = new String[numberOfInputs];
		int arrayIndex = 0;
		// Assume the input is invalid until after the method validates it.
		boolean badInput = true;
		Scanner input = new Scanner(System.in);
		// Loop to re-prompt the user for a valid input as needed.
		while (badInput || (arrayIndex < userInputArray.length)) {
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println("Please enter an string of characters:");
			userInputArray[arrayIndex] = input.nextLine();
			// Trim the leading and trailing white space from the string.
			userInputArray[arrayIndex] = userInputArray[arrayIndex].trim();
			// Check if the user did not put anything in. 
			// If so prompt again, if not, the input is good and we can exit the loop or get another input if needed.
			if 	( userInputArray[arrayIndex].isBlank() ) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("You did not provide an input. \nPlease try again.");
				badInput = true;
			} else {
				badInput = false;
				arrayIndex ++;
			}
		}
		//input.close();
		return userInputArray;
	}
	
	/** Prompt the user for a multiple string inputs in the console. 
	 * Use the array of user prompts to ask for each input. Validate the input is not empty.
	 */
	public static String[] getInputString(int numberOfInputs, String[] userPromptArray) {
		String[] userInputArray = new String[numberOfInputs];
		int arrayIndex = 0;
		// Assume the input is invalid until after the method validates it.
		boolean badInput = true;
		Scanner input = new Scanner(System.in);
		// Loop to re-prompt the user for a valid input as needed.
		while (badInput || (arrayIndex < userInputArray.length)) {
			// Check if the user prompt text is empty or blank and use the default prompt if it is.
			if (userPromptArray[arrayIndex].isBlank() || userPromptArray[arrayIndex].isEmpty()) {
				userPromptArray[arrayIndex] = "Please enter an string of characters:";
			}
			// Prompt the user for an input in the console and capture the user input as a string
			System.out.println(userPromptArray[arrayIndex]);
			userInputArray[arrayIndex] = input.nextLine();
			// Trim the leading and trailing white space from the string.
			userInputArray[arrayIndex] = userInputArray[arrayIndex].trim();
			// Check if the user did not put anything in. 
			// If so prompt again, if not, the input is good and we can exit the loop or get another input if needed.
			if 	( userInputArray[arrayIndex].isBlank() ) {
				// Advise the user the input is invalid and continue the loop
				System.out.println("You did not provide an input. \nPlease try again.");
				badInput = true;
			} else {
				badInput = false;
				arrayIndex ++;
			}
		}
		//input.close();
		return userInputArray;
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
		if (userPrompt.isBlank() || userPrompt.isEmpty()) {
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
