import java.math.BigDecimal

fun main() {
    println("Я вас приветствую в консольном приложении КАЛЬКУЛЯТОР!")
    println("Когда захотите выйте введите: EXIT")
    var x = true
    // можно просто while (true)
    while (x) {
        println("Введите необходимые числа и операцию, я могу: +, -, *, /")
        // можно удалить пробелы и пользователь сможет их ставить тип 2   + 3
        // readlnOrNull()?.replace(" ", "")
        val forCalculate = readlnOrNull()
        if (forCalculate?.trim()?.uppercase()=="EXIT") {
            println("До новых встреч!")
            // лучше закончить словом break
            return
        }
        if (check(forCalculate)) calculate(forCalculate!!)
    }
}
// пока у тебя один файл, но уже набивай руку делать методы не вызываемые извне private
fun calculate(str: String) {
    val toNum = str.split("/", "+", "*", "-")
    // замени тут на BigDecimal, типа такого BigDecimal.valueOf(toNum[0].trim().toDouble())
    // и нормас будет печататься
    val n1 = toNum[0].trim().toDouble()
    val n2 = toNum[1].trim().toDouble()
    print("РЕЗУЛЬТАТ: $n1 ")
    // var result = BigDecimal.ZERO
    var result = 0.0
    // можно просто when {
    when (str.contains("")) {
        str.contains("+") -> {
            // пиши всегда на новых строчках
            print("+"); result = n1 + n2
        }

        str.contains("-") -> {
            print("-"); result = n1 - n2
        }

        str.contains("*") -> {
            print("*"); result = n1 * n2
        }

        str.contains("/") -> {
            print("/"); result = n1 / n2
        }

        else -> {
            println("EXIT"); return
        }
    }
    println(" $n2 = $result")
}

fun check(str: String?): Boolean {
    // мне кажется эту переменную можно удалить и где тебе надо писать return false и return true
    var checkSTR = false
    //step #1
    if (str == null) {
        println("(!) [к сожалению я обнаружил null]")
        return checkSTR
    }

    //step #2 - проверим что значение не пустое
    // str.isEmpty()
    if (str == "") {
        println("(!) [пустая строка] Пожалуйста введите операцию по маске: число-оператор-число (Enter)")
        return checkSTR
    }

    //step #3 - проверим что все символы допустимые
    val operand1 = "0123456 789+-/*.".toCharArray().toTypedArray()
    val strToChar1 = str.toCharArray().toTypedArray()
    for (i in strToChar1) {
        if (!operand1.contains(i)) {
            println("(!) [я обнаружил НЕДОПУСТИМЫЕ символы]")
            return checkSTR
        }
    }
    //step #4 - проверим что операнда 2 штуки
    var quantityCheck = str.split("/", "+", "*", "-", " ").toMutableList()

    if(quantityCheck.contains("")){
        quantityCheck.remove("")
    }
    if (quantityCheck.size!==2){
        println("(!) [кажется вы ввели слишком много или мало чисел]")
        return checkSTR
    }


    //step #5 - проверим что есть оператор
    val operand = arrayOf('/', '+', '*', '-')
    val strToChar = str.toCharArray().toTypedArray()
    for (i in strToChar) {
        for (iCh in operand) {
            if (i == iCh) checkSTR = true
        }
    }
    if (!checkSTR) {
        println("(!) [я не обнаружил оператора: +, -, *, /]")
        return checkSTR
    }
    return checkSTR
}

