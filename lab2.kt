package com.example.lab2

fun calcularPromedio(numeros: List<Int>): Int {
    return numeros.reduce { acc, numero -> acc + numero } / numeros.size
}

fun filtrarNumerosImpares(numeros: List<Int>): List<Int> {
    return numeros.filter { it % 2 != 0 }
}

fun isPalindrome(cadena: String): Boolean {
    val limpiada = cadena.replace(Regex("[^A-Za-z0-9]"), "").toLowerCase()
    return limpiada == limpiada.reversed()
}

fun agregarSaludo(nombres: List<String>): List<String> {
    return nombres.map { "¡Hola, $it!" }
}

fun performOperation(a: Int, b: Int, operacion: (Int, Int) -> Int): Int {
    return operacion(a, b)
}

data class Person(val name: String, val age: Int, val gender: String)
data class Student(val name: String, val age: Int, val gender: String, val studentId: String)

// Función de mapeo que toma un objeto Person y devuelve un objeto Student correspondiente
fun mapPersonToStudent(person: Person): Student {
    return Student(person.name, person.age, person.gender, "")
}

fun main() {
    val numeros = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val nombres = listOf("Alicia", "Juan", "Pedro", "María")

    println("Promedio: ${calcularPromedio(numeros)}")
    println("Números impares: ${filtrarNumerosImpares(numeros)}")
    println("Es Palíndromo: ${isPalindrome("anita lava la tina")}")
    println(agregarSaludo(nombres))
    println("Resultado de la suma: ${performOperation(5, 3) { a, b -> a + b }}")
    println("Resultado de la resta: ${performOperation(8, 3) { a, b -> a - b }}")

    // Crear una lista de objetos Person
    val personList = listOf(
        Person("Alice", 20, "Female"),
        Person("Bob", 22, "Male"),
        Person("Charlie", 19, "Male")
    )

    // Mapear la lista de Person a una lista de Student
    val studentList = personList.map { mapPersonToStudent(it) }

    // Imprimir el resultado
    for (student in studentList) {
        println("El Estudiante ${student.name} tiene ${student.age} años.")
    }
}
