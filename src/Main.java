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

        int textOffset = (height - text.length()) / 2;          // The amount of "X" before the text to make sure it's in the middle of the space

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

        int textOffset = (textRow - text.length()) / 2;
        for (int y = 0; y <= height; y++) {

            for (int space = height - y; space > 0; space--) {
                System.out.print(" ");
            }
            for (int x = 0; x < y; x++) {
                if (y == textRow && x - textOffset < text.length() && x >= textOffset && textOffset >= 0) {
                    System.out.print(text.charAt(x - textOffset) + " ");

                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Prints out a diamond
     **/
    private static void printDiamond(int height, String text) {

        if (height % 2 == 0) {
            height++;
        }
        int halfHeight = (height / 2) + 1;

        /*Get the row we should put the text on
        * the row for the text must be able to fit the entire text
        * top half is the same as triangle
        * bottom half is the complement of the top half*/
        int textRow = getNextValidTextRowWithRange(text, text.length(), height-text.length() + 1);

        int firstHalfTextOffset = (textRow - text.length()) / 2;
        int secondHalfTextOffset = (height + 1 - textRow - text.length())/2;


        for (int y = 0; y < halfHeight; y++) {

            for (int space = halfHeight - y; space > 0; space--) {
                System.out.print("  ");
            }
            for (int x = 1; x <= y; x++) {
                if (y == textRow && x > firstHalfTextOffset && x <= (firstHalfTextOffset + text.length())) {
                    System.out.print(text.charAt(x - firstHalfTextOffset - 1) + "   ");
                } else {
                    System.out.print("X   ");
                }
            }
            System.out.println();
        }
        for (int y = halfHeight; y > 0; y--) {

            for (int space = halfHeight - y; space > 0; space--) {
                System.out.print("  ");
            }
            for (int x = 1; x <= y; x++) {
                if ((height + 1) - y == textRow && x > secondHalfTextOffset && x < (secondHalfTextOffset + text.length())) {
                    System.out.print(x - secondHalfTextOffset - 1 + "   ");
                } else {
                    System.out.print("X   ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Prints out a square
     **/
    private static void printRectangle(int height, String text) {
              /*Get the row we should put the text on (can not be more than the height of the shape)*/
        int textRow = getNextValidTextRowWithMax(text, height);

        int textOffset = (height * 2 - text.length()) / 2;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < height * 2; x++) {
                if (y == textRow - 1 && (x >= textOffset && x < text.length() + textOffset)) {
                    System.out.print(text.charAt(x - textOffset) + " ");

                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
        System.out.println("\n\n\n");
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
        do {
            System.out.print(messagePrompt);
            validInput = validNumScanner.hasNextInt();
            if (!validInput) {
                System.out.print("\n** Error: Please Enter A VALID Number **");
                validNumScanner.next();
            }

        } while (!validInput);

        do {
            validNum = validNumScanner.nextInt();

            if (validNum <= 0) {
                System.out.print("\n** Error: Please Enter A POSITIVE Number **");
                System.out.print(messagePrompt);
            }

        } while (validNum <= 0);

        validNumScanner.nextLine();   //To move cursor to next line .nextInt does not move cursor
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
            validShapeTextRow = getNextValidInt("\nWhat row should I print \"" + shapeText + "\": ");
            if (maxNum < shapeText.length()) {
                System.out.println("HEY HEY HEY");
            }
            if (validShapeTextRow > maxNum) {
                System.out.print("\n** Error: please Enter A number LESS THAN " + (maxNum + 1) + "! **");
            }
        } while (validShapeTextRow > maxNum);
        return validShapeTextRow;
    }

    private static int getNextValidTextRowWithRange(String shapeText, int minNum, int maxNum) {
        int validShapeTextRow;

        //we continue to ask the user for a number more than the minNum
        do {
            validShapeTextRow = getNextValidInt("\nWhat row should I print " + shapeText + "\": ");
            if (minNum > maxNum) {
                System.out.println("HEY HEY HEY HEY");
            }
            if (validShapeTextRow < minNum) {
                System.out.print("\n** Error: please Enter A number MORE THAN " + (minNum - 1) + "! **");
            } else if (validShapeTextRow > maxNum) {
                System.out.print("\n** Error: please Enter A number LESS THAN " + (maxNum + 1) + "! **");
            }

        } while (validShapeTextRow < minNum || validShapeTextRow > maxNum);
        return validShapeTextRow;
    }

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


    public static void main(String[] args) {

        boolean buildAnotherShape;
        String nextShapeResponse;
        Scanner nextShapeScanner = new Scanner(System.in);
        Vector<String> shapeOptions = new Vector<>();
        shapeOptions.add("Square");
        shapeOptions.add("Triangle");
        shapeOptions.add("Diamond");
        shapeOptions.add("Rectangle");
        shapeOptions.add("square");
        shapeOptions.add("triangle");
        shapeOptions.add("diamond");
        shapeOptions.add("rectangle");


        System.out.println("***Welcome to Shape Builder***");
        System.out.println("\n * * SHAPE CHOICES: Diamond, Square, Rectangle, Triangle * * ");

        do {
            shapeBuilder(shapeOptions);

            System.out.print("\n\nDo you want to build another shape?");
            nextShapeResponse = nextShapeScanner.nextLine();
            ;
            buildAnotherShape = (Objects.equals(nextShapeResponse, "Yes") || Objects.equals(nextShapeResponse, "yes") || Objects.equals(nextShapeResponse, "y") || Objects.equals(nextShapeResponse, "Y"));

        } while (buildAnotherShape);
    }
}
