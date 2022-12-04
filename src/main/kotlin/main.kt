import java.math.BigDecimal
import java.math.RoundingMode


fun main() {
    println("Я вас приветствую в консольном приложении КАЛЬКУЛЯТОР!")
    println("Когда захотите выйте введите: EXIT")
    //var x = true              //deleted
    // можно просто while (true)
    while (true) {              //fixed
        println("Введите необходимые числа и операцию, я могу: +, -, *, /")
        // можно удалить пробелы и пользователь сможет их ставить тип 2   + 3
        // readlnOrNull()?.replace(" ", "")
        val forCalculate = readlnOrNull()?.replace(" ", "") // fixed
        if (forCalculate?.uppercase() == "EXIT") { //trim_deleted // fixed
            println("До новых встреч!")
            // лучше закончить словом break
            break      //last_return // fixed
        }
        if (check(forCalculate)) calculate(forCalculate!!)
    }
}

// пока у тебя один файл, но уже набивай руку делать методы не вызываемые извне private
private fun calculate(str: String) {  //private_added //fixed
    val toNum = str.split("/", "+", "*", "-")
    // замени тут на BigDecimal, типа такого BigDecimal.valueOf(toNum[0].trim().toDouble())
    // и нормас будет печататься
    // (!) работает не очень корректно :\
    val n1 = BigDecimal.valueOf(toNum[0].toDouble()) // fixed
    val n2 = BigDecimal.valueOf(toNum[1].toDouble()) // fixed
    print("РЕЗУЛЬТАТ: ${toNum[0]} ")
    // var result = BigDecimal.ZERO
    var result = BigDecimal.ZERO    // fixed
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
    println(" ${toNum[1]} = $result") //(!) не помогло - .setScale(5, RoundingMode.HALF_UP)}")
}

private fun check(str: String?): Boolean { //private_added //fixed
    // мне кажется эту переменную можно удалить и где тебе надо писать return false и return true
    //var checkSTR = false //deleted
    //step #1
    if (str == null) {
        println("(!) [к сожалению я обнаружил null]")
        return false
    }

    //step #2 - проверим что значение не пустое
    // str.isEmpty()
    if (str == "") {
        println("(!) [пустая строка] Пожалуйста введите операцию по маске: число-оператор-число (Enter)")
        return false
    }

    //step #3 - проверим что все символы допустимые
    val operand1 = "0123456 789+-/*.".toCharArray().toTypedArray()
    val strToChar1 = str.toCharArray().toTypedArray()
    for (i in strToChar1) {
        if (!operand1.contains(i)) {
            println("(!) [я обнаружил НЕДОПУСТИМЫЕ символы]")
            return false
        }
    }
    //step #4 - проверим что операнда 2 штуки
    var quantityCheck = str.split("/", "+", "*", "-", " ").toMutableList()

    if (quantityCheck.contains("")) {
        quantityCheck.remove("")
    }
    if (quantityCheck.size == 1) {
        println("(!) [кажется вы ввели слишком мало чисел или забыли поставить оператор]")
        return false
    } else if (quantityCheck.size > 2) {
        println("(!) [кажется вы ввели слишком много чисел]")
        return false
    }


    //step #5 - проверим что есть оператор
    val operators = arrayOf('/', '+', '*', '-')
    val strToChar = str.toCharArray().toTypedArray()
    var operatorQ = 0
    for (i in strToChar) {
        for (iCh in operators) {
            if (i == iCh) {
                operatorQ++
            }
        }
    }
    /*    if (operatorQ == 0) {
            println("(!) [я не обнаружил оператора: +, -, *, /]")
            return false
        } else*/
    if (operatorQ > 1) {
        println("(!) [вы ввели слишком много операторов: +, -, *, /]")
        return false
    }
    return true
}

