package Shapes;


public class Triangle extends Shape {

    public Triangle(String shapeType) {
        //Calls Inherited constructor
        super(shapeType);

        //Get the row we should put the text on
        //The number of available spots for the text is equal to the row number for a triangle
        setRow(getNextValidTextRowWithRange(text, text.length(), height));

        // amount of "X" before text should be printed to make sure text is in the middle
        setTextOffset((row - text.length()) / 2);
    }

    public void print() {
        for (int y = 0; y <= height; y++) {

            for (int space = height - y; space > 0; space--) {
                //Amount of spaces to initiate each row so that triangle is equilateral
                System.out.print(" ");
            }
            for (int x = 0; x < y; x++) {
                // print the text if we are on the correct row and our x value is less than the full length of the text so we don't overflow
                if (y == row && x - textOffset < text.length() && x >= textOffset && textOffset >= 0) {
                    System.out.print(text.charAt(x - textOffset) + " ");

                } else {
                    System.out.print("X ");                     // prints "X" to draw the shape

                }
            }
            System.out.println();                               //Start next row
        }
    }
}
