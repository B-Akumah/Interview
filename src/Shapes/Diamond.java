package Shapes;

public class Diamond extends Shape {
    private int halfHeight;
    private int firstHalfTextOffset;
    private int secondHalfTextOffset;

    public Diamond(String shapeType) {
        //Calls Inherited constructor
        super(shapeType);

         /*
        * Diamonds can only have an Odd number of rows
        * if an even number is selected it will go to the next odd number
        */
        if (height % 2 == 0) {
            height++;
        }

       /* A diamond is two triangles one upright and the other upside down
        * we get the half height which is the point where we start printing the inverted triangle*/
        setHalfHeight((height / 2) + 1);

       /*
        * Get the row we should put the text on
        * the row for the text must be able to fit the entire text
        * top half is the same as triangle
        * bottom half is the complement of the top half
        */
        setRow(getNextValidTextRowWithRange(text, text.length(), height - text.length() + 1));


        /*
         * The off set numbers to make the text in the middle
         * the offset numbers are different for the top and bottom half due to the bottom half counting downwards
         */
        setFirstHalfTextOffset((row - text.length()) / 2);
        setSecondHalfTextOffset(((height + 1 - row - text.length()) / 2) + ((height + 1 - row - text.length()) % 2));
    }

    public int getFirstHalfTextOffset() {
        return firstHalfTextOffset;
    }
    public int getSecondHalfTextOffset() {
        return secondHalfTextOffset;
    }
    public int getHalfHeight() {
        return halfHeight;
    }

    private void setFirstHalfTextOffset(int firstHalfTextOffset) {
        this.firstHalfTextOffset = firstHalfTextOffset;
    }
    private void setSecondHalfTextOffset(int secondHalfTextOffset) {
        this.secondHalfTextOffset = secondHalfTextOffset;
    }
    private void setHalfHeight(int halfHeight) {
        this.halfHeight = halfHeight;
    }
    public void print() {
        //Top Triangle
        for (int y = 0; y < halfHeight; y++) {

            for (int space = halfHeight - y; space > 0; space--) {
                System.out.print("  ");                 //Amount of spaces to initiate each row so that diamond will be symmetrical
            }
            for (int x = 1; x <= y; x++) {
                // print the text if we are on the correct row and on the top half
                if (y == row && x > firstHalfTextOffset && x <= (firstHalfTextOffset + text.length())) {
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
                if ((height + 1) - y == row && x > secondHalfTextOffset && x <= (secondHalfTextOffset + text.length())) {
                    System.out.print(text.charAt(x - secondHalfTextOffset - 1) + "   ");
                } else {
                    System.out.print("X   ");           // prints "X" to draw the bottom triangle
                }
            }
            System.out.println();                       //starts next row
        }
    }

}
