import java.util.Scanner;
import java.util.Vector;


public class Shape {

    private Scanner shapeBuilderInput;
    private String type, text;
    private int height;



    Shape() {
        shapeBuilderInput = new Scanner(System.in);
        Vector<String> shapeOptions = new Vector<>();
        shapeOptions.add("Square");
        shapeOptions.add("Triangle");
        shapeOptions.add("Diamond");
        shapeOptions.add("Rectangle");
        shapeOptions.add("square");
        shapeOptions.add("triangle");
        shapeOptions.add("diamond");
        shapeOptions.add("rectangle");

         /*Get the type of shape we should draw*/
        do {
            System.out.print("\nWhat SHAPE should I draw:  ");
            type = shapeBuilderInput.nextLine();

            if (!shapeOptions.contains(type)) {
                System.out.print("\n** Error: please Enter A VALID Shape **");
            }

        } while (!shapeOptions.contains(type));
    }


    Shape(Vector<String> shapeOptionsIn) {
        shapeBuilderInput = new Scanner(System.in);
        type = shapeBuilderInput.nextLine();

    }
}
