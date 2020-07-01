import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

import Shapes.*;

public class Main {

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
        String shapeType;

         /*Get the type of shape we should draw*/
        do {
            System.out.println("\nSHAPE CHOICES: Diamond, Square, Rectangle, Triangle");
            System.out.print("What SHAPE should I draw:  ");
            shapeType = shapeBuilderInput.nextLine();

            if (!shapeOptions.contains(shapeType)) {
                System.out.print("\n** Error: please Enter A VALID Shapes **");
            }

        } while (!shapeOptions.contains(shapeType));

        Shape newShape;

        //Determine what shape function to call and pass it needed parameters
        switch (shapeType) {
            case "Square":
            case "square":
                newShape = new Square(shapeType);
                newShape.print();
                break;
            case "Rectangle":
            case "rectangle":
                newShape = new Rectangle(shapeType);
                newShape.print();
                break;
            case "Diamond":
            case "diamond":
                newShape = new Diamond(shapeType);
                newShape.print();
                break;
            case "Triangle":
            case "triangle":
                newShape = new Triangle(shapeType);
                newShape.print();
                break;
            default:
                newShape = new Shape(shapeType);
                newShape.print();
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
        System.out.println("***Welcome to Shapes Builder***");


        //Continue to build new shapes while the user chooses to do so
        do {
            //starts shape builder
            shapeBuilder(shapeOptions);
            System.out.println("\n\n");
            buildAnotherShape = getYesOrNo("\nDo you want to build another shape?");

        } while (buildAnotherShape);
    }
}
