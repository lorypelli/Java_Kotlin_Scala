package java_main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        final Scanner s = new Scanner(System.in);
        System.out.print("Inserisci la lunghezza del primo lato del triangolo: ");
        final float triangle1 = s.nextFloat();
        System.out.print("Inserisci la lunghezza del secondo lato del triangolo: ");
        final float triangle2 = s.nextFloat();
        System.out.print("Inserisci la lunghezza del terzo lato del triangolo: ");
        final float triangle3 = s.nextFloat();
        System.out.printf("Il perimetro del triangolo di lati %.2f, %.2f e %.2f è: %.2f\n", triangle1, triangle2, triangle3, new Triangle(triangle1, triangle2, triangle3).p);
        System.out.print("Inserisci la lunghezza del primo lato del rettangolo: ");
        final float rectangle1 = s.nextFloat();
        System.out.print("Inserisci la lunghezza del secondo lato del rettangolo: ");
        final float rectangle2 = s.nextFloat();
        System.out.printf("Il perimetro del rettangolo di lati %.2f e %.2f è: %.2f\n", rectangle1, rectangle2, new Rectangle(rectangle1, rectangle2).p);
        System.out.print("Inserisci la lunghezza del lato del quadrato: ");
        final float square = s.nextFloat();
        System.out.printf("Il perimetro del quadrato di lato %.2f è %.2f\n", square, new Square(square).p);
        System.out.print("Inserisci la lunghezza del primo lato del trapezio: ");
        final float trapezium1 = s.nextFloat();
        System.out.print("Inserisci la lunghezza del secondo lato del trapezio: ");
        final float trapezium2 = s.nextFloat();
        System.out.print("Inserisci la lunghezza del terzo lato del trapezio: ");
        final float trapezium3 = s.nextFloat();
        System.out.print("Inserisci la lunghezza del quarto lato del trapezio: ");
        final float trapezium4 = s.nextFloat();
        System.out.printf("Il perimetro del trapezio di lati %.2f, %.2f, %.2f e %.2f è: %.2f\n", trapezium1, trapezium2, trapezium3, trapezium4, new Trapezium(trapezium1, trapezium2, trapezium3, trapezium4).p);
        s.close();
    }
}

abstract class Defaults {
    static final String DEFAULT_MSG = "I lati non possono essere negativi!";
}

abstract class PolygonError {
    private static boolean canError = false;
    static boolean checkError(float[] arr) {
        for (float it : arr) {
            if (!isValid(it)) {
                canError = true;
                break;
            }
        }
        return canError;
    }
    static void throwError(String msg) throws Exception {
        if (canError) {
            throw new Exception(msg);
        }
    }
    private static boolean isValid(float e) {
        return e > 0f;
    }
}

abstract class Polygon {
    protected String msg;
    Polygon(String msg) {
        this.msg = msg;
    }
    protected float perimeter(float... arr) throws Exception {
        checkError(arr);
        return sum(arr);
    }
    protected String getErrorMessage() {
        return msg;
    }
    protected void setErrorMessage(String m) {
        msg = m;
    }
    private void checkError(float[] arr) throws Exception {
        final boolean err = PolygonError.checkError(arr);
        if (err) {
            PolygonError.throwError(msg);
        }
    }
    private float sum(float[] arr) {
        float s = 0f;
        for (float it : arr) {
            s += it;
        }
        return s;
    }
}

final class Triangle extends Polygon {
    private final float l1;
    private final float l2;
    private final float l3;
    final float p;
    Triangle(float l1, float l2, float l3, String msg) throws Exception {
        super(msg);
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
        this.p = perimeter();
    }
    Triangle(float l1, float l2, float l3) throws Exception {
        this(l1, l2, l3, Defaults.DEFAULT_MSG);
    }
    private float perimeter() throws Exception {
        return super.perimeter(l1, l2, l3);
    }
    public String toString() {
        return String.format("Triangle(%.2f, %.2f, %.2f, %s)", l1, l2, l3, msg);
    }
    protected String getErrorMessage() {
        return this + super.getErrorMessage();
    }
    protected void setErrorMessage(String m) {
        super.setErrorMessage(this + m);
    }
}

final class Rectangle extends Polygon {
    private final float l1;
    private final float l2;
    final float p;
    Rectangle(float l1, float l2, String msg) throws Exception {
        super(msg);
        this.l1 = l1;
        this.l2 = l2;
        this.p = perimeter();
    }
    Rectangle(float l1, float l2) throws Exception {
        this(l1, l2, Defaults.DEFAULT_MSG);
    }
    private float perimeter() throws Exception {
        return super.perimeter(l1 * 2, l2 * 2);
    }
    public String toString() {
        return String.format("Rectangle(%.2f, %.2f, %s)", l1, l2, msg);
    }
    protected String getErrorMessage() {
        return this + super.getErrorMessage();
    }
    protected void setErrorMessage(String m) {
        super.setErrorMessage(this + m);
    }
}

final class Square extends Polygon {
    private final float l;
    final float p;
    Square(float l, String msg) throws Exception {
        super(msg);
        this.l = l;
        this.p = perimeter();
    }
    Square(float l) throws Exception {
        this(l, Defaults.DEFAULT_MSG);
    }
    private float perimeter() throws Exception {
        return super.perimeter(l * 4);
    }
    public String toString() {
        return String.format("Square(%.2f, %s)", l, msg);
    }
    protected String getErrorMessage() {
        return this + super.getErrorMessage();
    }
    protected void setErrorMessage(String m) {
        super.setErrorMessage(this + m);
    }
}

final class Trapezium extends Polygon {
    private final float l1;
    private final float l2;
    private final float l3;
    private final float l4;
    final float p;
    Trapezium(float l1, float l2, float l3, float l4, String msg) throws Exception {
        super(msg);
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
        this.l4 = l4;
        this.p = perimeter();
    }
    Trapezium(float l1, float l2, float l3, float l4) throws Exception {
        this(l1, l2, l3, l4, Defaults.DEFAULT_MSG);
    }
    private float perimeter() throws Exception {
        return super.perimeter(l1, l2, l3, l4);
    }
    public String toString() {
        return String.format("Trapezium(%.2f, %.2f, %.2f, %.2f, %s)", l1, l2, l3, l4, msg);
    }
    protected String getErrorMessage() {
        return this + super.getErrorMessage();
    }
    protected void setErrorMessage(String m) {
        super.setErrorMessage(this + m);
    }
}
