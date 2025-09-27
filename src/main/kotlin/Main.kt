package ru.cib

import java.io.File
import java.sql.DriverManager
import javax.xml.bind.JAXBContext

fun main() {

    //Read XML with Jaxb
    val readFile = File("/Users/slava_ivanov_saikyo/2025-q1-kotlin/src/main/resources/students.xml")
    val context = JAXBContext.newInstance(Students::class.java)
    val unmarshaller = context.createUnmarshaller()
    val students = unmarshaller.unmarshal(readFile) as Students

    //Database connection and execute
    val connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "test")
    students.student.forEach { student ->
        connection.prepareStatement("Insert into test (name, surname, birthday) values ('${student.name}', '${student.surname}', '${student.birthday}')").use {
            it.execute()
        }
    }
    connection.close()
}