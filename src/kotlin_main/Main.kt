package kotlin_main

fun main() {
    print("Inserisci la lunghezza del primo lato del triangolo: ")
    val triangle1 = readln().toFloat()
    print("Inserisci la lunghezza del secondo lato del triangolo: ")
    val triangle2 = readln().toFloat()
    print("Inserisci la lunghezza del terzo lato del triangolo: ")
    val triangle3 = readln().toFloat()
    println("Il perimetro del triangolo di lati %.2f, %.2f e %.2f è: %.2f".format(triangle1, triangle2, triangle3, Triangle(triangle1, triangle2, triangle3).p))
    print("Inserisci la lunghezza del primo lato del rettangolo: ")
    val rectangle1 = readln().toFloat()
    print("Inserisci la lunghezza del secondo lato del rettangolo: ")
    val rectangle2 = readln().toFloat()
    println("Il perimetro del rettangolo di lati %.2f e %.2f è: %.2f".format(rectangle1, rectangle2, Rectangle(rectangle1, rectangle2).p))
    print("Inserisci la lunghezza del lato del quadrato: ")
    val square = readln().toFloat()
    println("Il perimetro del quadrato di lato %.2f è: %.2f".format(square, Square(square).p))
    print("Inserisci la lunghezza del primo lato del trapezio: ")
    val trapezium1 = readln().toFloat()
    print("Inserisci la lunghezza del secondo lato del trapezio: ")
    val trapezium2 = readln().toFloat()
    print("Inserisci la lunghezza del terzo lato del trapezio: ")
    val trapezium3 = readln().toFloat()
    print("Inserisci la lunghezza del quarto lato del trapezio: ")
    val trapezium4 = readln().toFloat()
    println("Il perimetro del trapezio di lati %.2f, %.2f, %.2f e %.2f è: %.2f".format(trapezium1, trapezium2, trapezium3, trapezium4, Trapezium(trapezium1, trapezium2, trapezium3, trapezium4).p))
}

private abstract class Defaults private constructor() {
    companion object {
        const val DEFAULT_MSG = "I lati non possono essere negativi!"
    }
}

private abstract class PolygonError private constructor() {
    companion object {
        private var canError = false
        fun checkError(arr: FloatArray): Boolean {
            arr.forEach {
                if (!isValid(it)) {
                    canError = true
                    return@forEach
                }
            }
            return canError
        }
        fun throwError(msg: String) {
            if (canError) {
                throw Exception(msg)
            }
        }
        private fun isValid(e: Float) = e > 0f
    }
}

abstract class Polygon(private var msg: String) {
    protected fun perimeter(vararg arr: Float): Float {
        checkError(arr)
        return sum(arr)
    }
    protected open fun getErrorMessage() = msg
    protected open fun setErrorMessage(m: String) {
        msg = m
    }
    private fun checkError(arr: FloatArray) {
        val err = PolygonError.checkError(arr)
        if (err) {
            PolygonError.throwError(msg)
        }
    }
    private fun sum(arr: FloatArray): Float {
        var s = 0f
        arr.forEach { s += it }
        return s
    }
}

class Triangle(private val l1: Float, private val l2: Float, private val l3: Float, msg: String = Defaults.DEFAULT_MSG): Polygon(msg) {
    val p = perimeter()
    private fun perimeter() = super.perimeter(l1, l2, l3)
    override fun toString() = "Triangle(%.2f, %.2f, %.2f)".format(l1, l2, l3)
    override fun getErrorMessage() = "$this: ${super.getErrorMessage()}"
    override fun setErrorMessage(m: String) = super.setErrorMessage("$this: $m")
}

class Rectangle(private val l1: Float, private val l2: Float, msg: String = Defaults.DEFAULT_MSG): Polygon(msg) {
    val p = perimeter()
    private fun perimeter() = super.perimeter(l1 * 2, l2 * 2)
    override fun toString() = "Rectangle(%.2f, %.2f)".format(l1, l2)
    override fun getErrorMessage() = "$this: ${super.getErrorMessage()}"
    override fun setErrorMessage(m: String) = super.setErrorMessage("$this: $m")
}

class Square(private val l: Float, msg: String = Defaults.DEFAULT_MSG): Polygon(msg) {
    val p = perimeter()
    private fun perimeter() = super.perimeter(l * 4)
    override fun toString() = "Square(%.2f)".format(l)
    override fun getErrorMessage() = "$this: ${super.getErrorMessage()}"
    override fun setErrorMessage(m: String) = super.setErrorMessage("$this: $m")
}

class Trapezium(private val l1: Float, private val l2: Float, private val l3: Float, private val l4: Float, msg: String = Defaults.DEFAULT_MSG): Polygon(msg) {
    val p = perimeter()
    private fun perimeter() = super.perimeter(l1, l2, l3, l4)
    override fun toString() = "Trapezium(%.2f, %.2f, %2.f, %.2f)".format(l1, l2, l3, l4)
    override fun getErrorMessage() = "$this: ${super.getErrorMessage()}"
    override fun setErrorMessage(m: String) = super.setErrorMessage("$this: $m")
}
