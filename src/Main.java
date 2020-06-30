import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    /**
     * Prints out a square
     **/
    private static void printSquare(int height, String text) {
        /*Get the row we should put the text on (can not be more than the height of the shape)*/
        int textRow = getNextValidTextRowWithMax(text, height);

        // The amount of "X" before the text to make sure it's in the middle of the space
        int textOffset = (height - text.length()) / 2;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < height; x++) {
                // print the text if we are on the correct row and our x value is less than the full length of the text
                if (y == textRow - 1 && (x >= textOffset && x < text.length() + textOffset)) {
                    System.out.print(text.charAt(x - textOffset) + " ");
                } else {
                    System.out.print("X ");                     // prints "X" to draw the shape
                }
            }
            System.out.println();                               //Start next row
        }
    }

    /**
     * Prints out a triangle
     **/
    private static void printTriangle(int height, String text) {

        //Get the row we should put the text on
        //The number of available spots for the text is equal to the row number for a triangle
        int textRow = getNextValidTextRowWithRange(text, text.length(), height);

        // amount of "X" before text should be printed to make sure text is in the middle
        int textOffset = (textRow - text.length()) / 2;

        for (int y = 0; y <= height; y++) {

            for (int space = height - y; space > 0; space--) {
                //Amount of spaces to initiate each row so that triangle is equilateral
                System.out.print(" ");
            }
            for (int x = 0; x < y; x++) {
                // print the text if we are on the correct row and our x value is less than the full length of the text
                if (y == textRow && x - textOffset < text.length() && x >= textOffset && textOffset >= 0) {
                    System.out.print(text.charAt(x - textOffset) + " ");

                } else {
                    System.out.print("X ");                     // prints "X" to draw the shape

                }
            }
            System.out.println();                               //Start next row
        }
    }

    /**
     * Prints out a diamond
     **/
    private static void printDiamond(int height, String text) {

        /*
        * Diamonds can only have an Odd number of rows
        * if an even number is selected it will go to the next odd numbe
        * r*/
        if (height % 2 == 0) {
            height++;
        }

        /*A diamond is two triangles one upright and the other upside down
        * we get the half height which is the point where we start printing the inverted triangle*/
        int halfHeight = (height / 2) + 1;

        /*
        *Get the row we should put the text on
        * the row for the text must be able to fit the entire text
        * top half is the same as triangle
        * bottom half is the complement of the top half
        * */
        int textRow = getNextValidTextRowWithRange(text, text.length(), height - text.length() + 1);

        /*
         * The off set numbers to make the text in the middle
         * the offset numbers are different for the top and bottom half due to the bottom half counting downwards
         */
        int firstHalfTextOffset = (textRow - text.length()) / 2;
        int secondHalfTextOffset = ((height + 1 - textRow - text.length()) / 2) + ((height + 1 - textRow - text.length()) % 2);

        //Top Triangle
        for (int y = 0; y < halfHeight; y++) {

            for (int space = halfHeight - y; space > 0; space--) {
                System.out.print("  ");                 //Amount of spaces to initiate each row so that diamond will be symmetrical
            }
            for (int x = 1; x <= y; x++) {
                // print the text if we are on the correct row and on the top half
                if (y == textRow && x > firstHalfTextOffset && x <= (firstHalfTextOffset + text.length())) {
                    System.out.print(text.charAt(x - firstHalfTextOffset - 1) + "   ");
                } else {
                    System.out.print("X   ");           // prints "X" to draw the top triangle
                }
            }
            System.out.println();                       //starts next row
        }
        //Bottom triangle
        for (int y = halfHeight; y > 0; y--) {

            for (int space = halfHeight - y; space > 0; space--) {
                System.out.print("  ");
            }
            for (int x = 1; x <= y; x++) {
                // print the text if we are on the correct row and on the bottom half
                if ((height + 1) - y == textRow && x > secondHalfTextOffset && x <= (secondHalfTextOffset + text.length())) {
                    System.out.print(text.charAt(x - secondHalfTextOffset - 1) + "   ");
                } else {
                    System.out.print("X   ");           // prints "X" to draw the bottom triangle
                }
            }
            System.out.println();                       //starts next row
        }
    }

    /**
     * Prints out a rectangle
     **/
    private static void printRectangle(int height, String text) {
        //Get the row we should put the text on
        int textRow = getNextValidTextRowWithMax(text, height);

        //Offset that allows text to be centered
        int textOffset = (height * 2 - text.length()) / 2;

        for (int y = 0; y < height; y++) {
            //double the width to make a rectangle
            for (int x = 0; x < height * 2; x++) {
                //Prints character of text instead of "X"
                if (y == textRow - 1 && (x >= textOffset && x < text.length() + textOffset)) {
                    System.out.print(text.charAt(x - textOffset) + " ");

                } else {
                    System.out.print("X ");         //prints "X" to draw the rectangle
                }
            }
            System.out.println();                   //starts next row
        }
    }

    /**
     * returns the user input int after being validated
     * makes sure integer is positive and not a string
     * will continue to prompt user until a valid integer is entered.
     **/
    private static int getNextValidInt(String messagePrompt) {
        boolean validInput;
        Scanner validNumScanner = new Scanner(System.in);
        int validNum;

        //continue prompting user to enter a value that is an int
        do {
            System.out.print(messagePrompt);
            validInput = validNumScanner.hasNextInt();              // Checks if user input is an int
            if (!validInput) {
                System.out.print("\n** Error: Please Enter A VALID Number **");
                validNumScanner.next();
            }

        } while (!validInput);

        // continue prompting user to enter a value that is non negative
        do {
            validNum = validNumScanner.nextInt();

            //checks if user input is positive
            if (validNum <= 0) {
                System.out.print("\n** Error: Please Enter A POSITIVE Number **");
                System.out.print(messagePrompt);
            }

        } while (validNum <= 0);

        validNumScanner.nextLine();   //To move cursor to next line for next user input .nextInt used earlier does not move cursor
        return validNum;
    }

    /**
     * returns the user input String after being validated
     * determines whether empty input which will default to "LU".
     **/
    private static String getNextValidShapeText(String shapeType) {
        String validShapeText;
        Scanner validShapeTextScanner = new Scanner(System.in);

        System.out.print("\nWhat label should I print on this " + shapeType + " (Leave Blank for \"LU\")?");
        validShapeText = validShapeTextScanner.nextLine();

        //If the user does not enter any text defaults to "LU"
        if (Objects.equals(validShapeText, "")) {
            validShapeText = "LU";
        }
        return validShapeText;
    }

    /**
     * returns the user input int after being validated
     * determines whether empty input which will default to "LU".
     */
    private static int getNextValidTextRowWithMax(String shapeText, int maxNum) {
        int validShapeTextRow;

        //we continue to ask the user for a number less than the maxNum
        do {
            //get a valid row input
            validShapeTextRow = getNextValidInt("\nWhat row should I print \"" + shapeText + "\": ");

            if (validShapeTextRow > maxNum) {
                System.out.print("\n** Error: please Enter A number LESS THAN " + (maxNum + 1) + "! **");
            }
        } while (validShapeTextRow > maxNum);
        return validShapeTextRow;
    }

    /**
     * returns the user input if it is within the range of minNum and maxNum
     */
    private static int getNextValidTextRowWithRange(String shapeText, int minNum, int maxNum) {
        int validShapeTextRow;

        //we continue to ask the user for a number more than the minNum and less than the max num
        do {
            //get a valid row input
            validShapeTextRow = getNextValidInt("\nWhat row should I print " + shapeText + "\": ");

            if (validShapeTextRow < minNum) {
                System.out.print("\n** Error: please Enter A number MORE THAN " + (minNum - 1) + "! **");
            } else if (validShapeTextRow > maxNum) {
                System.out.print("\n** Error: please Enter A number LESS THAN " + (maxNum + 1) + "! **");
            }

        } while (validShapeTextRow < minNum || validShapeTextRow > maxNum);
        return validShapeTextRow;
    }

    /**
     * asks the user a yes/no questions until a variation of "YES" or "NO" is inputed
     */
    private static boolean getYesOrNo(String prompt) {
        Scanner boolScanner = new Scanner(System.in);
        boolean validBool = false, toRepeat;
        String response;

        do {
            System.out.print(prompt);
            response = boolScanner.nextLine();
            //different possibilities of YES returns true
            if (Objects.equals(response, "y") ||
                    Objects.equals(response, "Y") ||
                    Objects.equals(response, "yes") ||
                    Objects.equals(response, "Yes") ||
                    Objects.equals(response, "YES")) {
                validBool = true;
                toRepeat = false;
            }
            //different possibilities of NO returns false
            else if (Objects.equals(response, "n") ||
                    Objects.equals(response, "N") ||
                    Objects.equals(response, "no") ||
                    Objects.equals(response, "No") ||
                    Objects.equals(response, "NO")) {
                validBool = false;
                toRepeat = false;
            }
            //user did not enter yes or no variation and re ask for correct input
            else {
                System.out.print("\n\n**Error: Please enter Yes or No** ");
                toRepeat = true;
            }
        } while (toRepeat);

        return validBool;       //return true(YES) or false(NO)
    }

    /**
     * driver function that calls all other function and sequentially creates shape
     */
    private static void shapeBuilder(Vector<String> shapeOptions) {
        Scanner shapeBuilderInput = new Scanner(System.in);
        String shapeType, shapeText;
        int shapeHeight;

         /*Get the type of shape we should draw*/
        do {
            System.out.print("\nWhat SHAPE should I draw:  ");
            shapeType = shapeBuilderInput.nextLine();

            if (!shapeOptions.contains(shapeType)) {
                System.out.print("\n** Error: please Enter A VALID Shape **");
            }

        } while (!shapeOptions.contains(shapeType));

        /*Get the Height of the shape we should draw*/
        shapeHeight = getNextValidInt("\nEnter the HEIGHT of the Shape: ");

        /*Get the text label we should print on the shape*/
        shapeText = getNextValidShapeText(shapeType);


        //Determine what shape function to call and pass it needed parameters
        switch (shapeType) {
            case "Square":
            case "square":
                printSquare(shapeHeight, shapeText);
                break;
            case "Rectangle":
            case "rectangle":
                printRectangle(shapeHeight, shapeText);
                break;
            case "Diamond":
            case "diamond":
                printDiamond(shapeHeight, shapeText);
                break;
            case "Triangle":
            case "triangle":
                printTriangle(shapeHeight, shapeText);
                break;
            default:
                printSquare(shapeHeight, shapeText);
        }
    }

    /**
     * main function
     */
    public static void main(String[] args) {

        boolean buildAnotherShape;
        //Initialize the types of shapes we can draw and has Caps variations
        Vector<String> shapeOptions = new Vector<>();
        shapeOptions.add("Square");
        shapeOptions.add("Triangle");
        shapeOptions.add("Diamond");
        shapeOptions.add("Rectangle");
        shapeOptions.add("square");
        shapeOptions.add("triangle");
        shapeOptions.add("diamond");
        shapeOptions.add("rectangle");

        //Welcome Message
        System.out.println("***Welcome to Shape Builder***");
        System.out.println("\n * * * * SHAPE CHOICES: Diamond, Square, Rectangle, Triangle * * * *");

        //Continue to build new shapes while the user chooses to do so
        do {
            //starts shape builder
            shapeBuilder(shapeOptions);
            System.out.println("\n\n");
            buildAnotherShape = getYesOrNo("\nDo you want to build another shape?");

        } while (buildAnotherShape);
    }
}
