package groovy_main

print("Inserisci la lunghezza del primo lato del triangolo: ")
final float triangle1 = System.in.newReader().readLine().toFloat()
print("Inserisci la lunghezza del secondo lato del triangolo: ")
final float triangle2 = System.in.newReader().readLine().toFloat()
print("Inserisci la lunghezza del terzo lato del triangolo: ")
final float triangle3 = System.in.newReader().readLine().toFloat()
printf("Il perimetro del triangolo di lati %.2f, %.2f e %.2f è: %.2f\n", triangle1, triangle2, triangle3, new Triangle(triangle1, triangle2, triangle3).p)
print("Inserisci la lunghezza del primo lato del rettangolo: ")
final float rectangle1 = System.in.newReader().readLine().toFloat()
print("Inserisci la lunghezza del secondo lato del triangolo: ")
final float rectangle2 = System.in.newReader().readLine().toFloat()
printf("Il perimetro del rettangolo di lati %.2f e %.2f è: %.2f\n", rectangle1, rectangle2, new Rectangle(rectangle1, rectangle2).p)
print("Inserisci la lunghezza del lato del quadrato: ")
final float square = System.in.newReader().readLine().toFloat()
printf("Il perimetro del quadrato di lato %.2f è: %.2f\n", square, new Square(square).p)
print("Inserisci la lunghezza del primo lato del trapezio: ")
final float trapezium1 = System.in.newReader().readLine().toFloat()
print("Inserisci la lunghezza del secondo lato del trapezio: ")
final float trapezium2 = System.in.newReader().readLine().toFloat()
print("Inserisci la lunghezza del terzo lato del trapezio: ")
final float trapezium3 = System.in.newReader().readLine().toFloat()
print("Inserisci la lunghezza del quarto lato del trapezio: ")
final float trapezium4 = System.in.newReader().readLine().toFloat()
printf("Il perimetro del trapezio di lati %.2f, %.2f, %.2f e %.2f è: %.2f\n", trapezium1, trapezium2, trapezium3, trapezium4, new Trapezium(trapezium1, trapezium2, trapezium3, trapezium4).p)

abstract class Defaults {
    static final String DEFAULT_MSG = "I lati non possono essere negativi!"

    private Defaults() {}
}

abstract class PolygonError {
    private static boolean canError = false

    private PolygonError() {}

    static boolean checkError(float ... arr) {
        arr.each { it ->
            if (!isValid(it)) {
                canError = true
                return
            }
        }
        return canError
    }

    static void throwError(String msg) {
        if (canError) {
            throw new Exception(msg)
        }
    }

    private static boolean isValid(float e) {
        return e > 0f
    }
}

abstract class Polygon {
    private String msg

    Polygon(String msg) {
        this.msg = msg
    }

    protected float perimeter(float ... arr) {
        checkError(arr)
        return sum(arr)
    }

    protected String getErrorMessage() {
        return msg
    }

    protected void setErrorMessage(String m) {
        msg = m
    }

    private void checkError(float[] arr) {
        final boolean err = PolygonError.checkError(arr)
        if (err) {
            PolygonError.throwError(msg)
        }
    }

    private static float sum(float[] arr) {
        float s = 0f
        arr.each { it ->
            s += it
        }
        return s
    }
}

final class Triangle extends Polygon {
    private final float l1
    private final float l2
    private final float l3
    final float p

    Triangle(float l1, float l2, float l3, String msg = Defaults.DEFAULT_MSG) {
        super(msg)
        this.l1 = l1
        this.l2 = l2
        this.l3 = l3
        p = perimeter()
    }

    private float perimeter() {
        return super.perimeter(l1, l2, l3)
    }

    String toString() {
        return sprintf("Triangle(%.2f, %.2f, %.2f)", l1, l2, l3)
    }

    protected String getErrorMessage() {
        return "$this: ${super.getErrorMessage()}"
    }

    protected void setErrorMessage(String m) {
        super.setErrorMessage("$this: $m")
    }
}

final class Rectangle extends Polygon {
    private final float l1
    private final float l2
    final float p

    Rectangle(float l1, float l2, String msg = Defaults.DEFAULT_MSG) {
        super(msg)
        this.l1 = l1
        this.l2 = l2
        p = perimeter()
    }

    private float perimeter() {
        return super.perimeter(l1 * 2 as float, l2 * 2 as float)
    }

    String toString() {
        return sprintf("Rectangle(%.2f, %.2f)", l1, l2)
    }

    protected String getErrorMessage() {
        return "$this: ${super.getErrorMessage()}"
    }

    protected void setErrorMessage(String m) {
        super.setErrorMessage("$this: $m")
    }
}

final class Square extends Polygon {
    private final float l
    final float p

    Square(float l, String msg = Defaults.DEFAULT_MSG) {
        super(msg)
        this.l = l
        p = perimeter()
    }

    private float perimeter() {
        return super.perimeter(l * 4 as float)
    }

    String toString() {
        return sprintf("Square(%.2f)", l)
    }

    protected String getErrorMessage() {
        return "$this: ${super.getErrorMessage()}"
    }

    protected void setErrorMessage(String m) {
        super.setErrorMessage("$this: $m")
    }
}

final class Trapezium extends Polygon {
    private final float l1
    private final float l2
    private final float l3
    private final float l4
    final float p

    Trapezium(float l1, float l2, float l3, float l4, String msg = Defaults.DEFAULT_MSG) {
        super(msg)
        this.l1 = l1
        this.l2 = l2
        this.l3 = l3
        this.l4 = l4
        p = perimeter()
    }

    private float perimeter() {
        return super.perimeter(l1, l2, l3, l4)
    }

    String toString() {
        return sprintf("Trapezium(%.2f, %.2f, %.2f, %.2f)", l1, l2, l3, l4)
    }

    protected String getErrorMessage() {
        return "$this: ${super.getErrorMessage()}"
    }

    protected void setErrorMessage(String m) {
        super.setErrorMessage("$this: $m")
    }
}