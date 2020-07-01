package Shapes;

public class Rectangle extends Shape {


    public Rectangle(String shapeType) {
        //Calls Inherited constructor
        super(shapeType);

        //Get the row we should put the text
        setRow(getNextValidTextRowWithMax(text, height));

        //Offset that allows text to be centered
        setTextOffset((height * 2 - text.length()) / 2);
    }



    public void print() {
        for (int y = 0; y < height; y++) {
            //double the width to make a rectangle
            for (int x = 0; x < height * 2; x++) {
                //Prints character of text instead of "X"
                if (y == row - 1 && (x >= textOffset && x < text.length() + textOffset)) {
                    System.out.print(text.charAt(x - textOffset) + " ");

                } else {
                    System.out.print("X ");         //prints "X" to draw the rectangle
                }
            }
            System.out.println();                   //starts next row
        }
    }
}
