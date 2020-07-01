package Shapes;

/**
 * Created by brianschool on 7/1/20.
 */
public class Rectangle extends Shape{

    public Rectangle(int heightIn, int rowIn, String textIn) {
        super(heightIn, rowIn, textIn);
    }

    public void print() {
//Get the row we should put the text
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
        }    }

}
