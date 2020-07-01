package Shapes;

/**
 * Created by brianschool on 7/1/20.
 */
public class Triangle extends Shape{

    public Triangle(int heightIn, int rowIn, String textIn) {
        super(heightIn, rowIn, textIn);
    }

    public void print() {
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
                // print the text if we are on the correct row and our x value is less than the full length of the text so we don't overflow
                if (y == textRow && x - textOffset < text.length() && x >= textOffset && textOffset >= 0) {
                    System.out.print(text.charAt(x - textOffset) + " ");

                } else {
                    System.out.print("X ");                     // prints "X" to draw the shape

                }
            }
            System.out.println();                               //Start next row
        }    }

}
