package Shapes;

import java.util.Scanner;

/**
 * Created by brianschool on 7/1/20.
 */

public class Shape {
    protected int height, row;
    protected String text;


    public Shape(int heightIn, int rowIn, String textIn) {
        setHeight(heightIn);
        setRow(rowIn);
        setText(textIn);
    }

    public int getHeight() {
        return height;
    }
    public int getRow() {
        return row;
    }
    public String getText() {
        return text;
    }

    private void setHeight(int height) {
        this.height = height;
    }
    private void setRow(int row) {
        this.row = row;
    }
    private void setText(String text) {
        this.text = text;
    }

    public void print() {
        System.out.println("Shape");
    }

    /**
     * returns the user input int after being validated
     * makes sure integer is positive and not a string
     * will continue to prompt user until a valid integer is entered.
     **/
    private int getNextValidInt(String messagePrompt) {
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
     * returns the user input int after being validated
     * determines whether empty input which will default to "LU".
     */
    protected int getNextValidTextRowWithMax(String shapeText, int maxNum) {
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
    protected int getNextValidTextRowWithRange(String shapeText, int minNum, int maxNum) {
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

}
