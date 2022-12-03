fun main() {
    println("Я вас приветствую в консольном приложении КАЛЬКУЛЯТОР!")
    println("Когда захотите выйте введите: EXIT")
    var x = true
    while (x) {
        println("Введите необходимые числа и операцию, я могу: +, -, *, /")
        val forCalculate = readlnOrNull()
        if (forCalculate?.trim()?.uppercase()=="EXIT") {
            println("До новых встреч!")
            return
        }
        if (check(forCalculate)) calculate(forCalculate!!)
    }
}

fun calculate(str: String) {
    val toNum = str.split("/", "+", "*", "-")
    val n1 = toNum[0].trim().toDouble()
    val n2 = toNum[1].trim().toDouble()
    print("РЕЗУЛЬТАТ: $n1 ")
    var result = 0.0
    when (str.contains("")) {
        str.contains("+") -> {
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
    var checkSTR = false
    //step #1
    if (str == null) {
        println("(!) [к сожалению я обнаружил null]")
        return checkSTR
    }

    //step #2 - проверим что значение не пустое
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

