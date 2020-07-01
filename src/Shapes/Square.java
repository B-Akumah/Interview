package Shapes;

/**
 * Created by brianschool on 7/1/20.
 */
public class Square extends Shape {

    public Square(int heightIn, int rowIn, String textIn) {
        super(heightIn, rowIn, textIn);
    }

    public void print() {
 /*Get the row we should put the text on (can not be more than the height which is also the width of the shape)*/
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
        }    }

}
