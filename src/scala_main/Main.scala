package scala_main

import scala.io.StdIn
import scala.util.control.Breaks.{breakable, break}

object Main {
    def main(args: Array[String]): Unit = {
        val triangle1 = StdIn.readLine("Inserisci la lunghezza del primo lato del triangolo: ").toFloat
        val triangle2 = StdIn.readLine("Inserisci la lunghezza del secondo lato del triangolo: ").toFloat
        val triangle3 = StdIn.readLine("Inserisci la lunghezza del terzo lato del triangolo: ").toFloat
        printf("Il perimetro del triangolo di lati %.2f, %.2f e %.2f è: %.2f\n", triangle1, triangle2, triangle3, new Triangle(triangle1, triangle2, triangle3).p)
        val rectangle1 = StdIn.readLine("Inserisci la lunghezza del primo lato del rettangolo: ").toFloat
        val rectangle2 = StdIn.readLine("Inserisci la lunghezza del secondo lato del rettangolo: ").toFloat
        printf("Il perimetro del rettangolo di lati %.2f e %.2f è: %.2f\n", rectangle1, rectangle2, new Rectangle(rectangle1, rectangle2).p)
        val square = StdIn.readLine("Inserisci la lunghezza del lato del quadrato: ").toFloat
        printf("Il perimetro del quadrato di lato %.2f è: %.2f\n", square, new Square(square).p)
        val trapezium1 = StdIn.readLine("Inserisci la lunghezza del primo lato del trapezio: ").toFloat
        val trapezium2 = StdIn.readLine("Inserisci la lunghezza del secondo lato del trapezio: ").toFloat
        val trapezium3 = StdIn.readLine("Inserisci la lunghezza del terzo lato del trapezio: ").toFloat
        val trapezium4 = StdIn.readLine("Inserisci la lunghezza del quarto lato del trapezio: ").toFloat
        printf("Il perimetro del trapezio di lati %.2f, %.2f, %.2f e %.2f è: %.2f\n", trapezium1, trapezium2, trapezium3, trapezium4, new Trapezium(trapezium1, trapezium2, trapezium3, trapezium4).p)
        
    }
}

private object Defaults {
    val DEFAULT_MSG = "I lati non possono essere negativi!"
}

private object PolygonError {
    private var canError = false
    def checkError(arr: Seq[Float]): Boolean = {
        breakable {
            arr.foreach { it =>
                if (!isValid(it)) {
                    canError = true
                    break
                }
            }
        }
        canError
    }
    def throwError(msg: String): Unit = {
        if (canError) {
            throw new Exception(msg)
        }
    }
    private def isValid(e: Float) = e > 0f
}

abstract class Polygon(private var msg: String) {
    protected def perimeter(arr: Float*): Float = {
        checkError(arr)
        sum(arr)
    }
    protected def getErrorMessage: String = msg
    protected def setErrorMessage(m: String): Unit = msg = m
    private def checkError(arr: Seq[Float]): Unit = {
        val err = PolygonError.checkError(arr)
        if (err) {
            PolygonError.throwError(msg)
        }
    }
    private def sum(arr: Seq[Float]) = {
        var s = 0f
        arr.foreach { it => s += it }
        s
    }
}

final class Triangle(l1: Float, l2: Float, l3: Float, msg: String = Defaults.DEFAULT_MSG) extends Polygon(msg) {
    val p: Float = perimeter()
    private def perimeter() = super.perimeter(l1, l2, l3)
    override def toString: String = "Triangle(%.2f, %.2f, %.2f)".format(l1, l2, l3)
    override def getErrorMessage: String = s"$this: ${super.getErrorMessage}"
    override def setErrorMessage(m: String): Unit = super.setErrorMessage(s"$this: $msg")
}

final class Rectangle(l1: Float, l2: Float, msg: String = Defaults.DEFAULT_MSG) extends Polygon(msg) {
    val p: Float = perimeter()
    private def perimeter() = super.perimeter(l1, l2)
    override def toString: String = "Rectangle(%.2f, %.2f)".format(l1, l2)
    override def getErrorMessage: String = s"$this: ${super.getErrorMessage}"
    override def setErrorMessage(m: String): Unit = super.setErrorMessage(s"$this: $msg")
}

final class Square(l: Float, msg: String = Defaults.DEFAULT_MSG) extends Polygon(msg) {
    val p: Float = perimeter()
    private def perimeter() = super.perimeter(l)
    override def toString: String = "Square(%.2f)".format(l)
    override def getErrorMessage: String = s"$this: ${super.getErrorMessage}"
    override def setErrorMessage(m: String): Unit = super.setErrorMessage(s"$this: $msg")
}

final class Trapezium(l1: Float, l2: Float, l3: Float, l4: Float, msg: String = Defaults.DEFAULT_MSG) extends Polygon(msg) {
    val p: Float = perimeter()
    private def perimeter() = super.perimeter(l1, l2, l3, l4)
    override def toString: String = "Trapezium(%.2f, %.2f, %.2f, %.2f)".format(l1, l2, l3, l4)
    override def getErrorMessage: String = s"$this: ${super.getErrorMessage}"
    override def setErrorMessage(m: String): Unit = super.setErrorMessage(s"$this: $msg")
}