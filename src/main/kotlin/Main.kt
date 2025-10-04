package ru.cib

import java.io.File
import java.sql.DriverManager
import javax.xml.bind.JAXBContext

fun main() {
    //Read directory to find files with xml
    val newDir = File("/Users/slava_ivanov_saikyo/2025-q1-kotlin/src/main/resources/new")
    newDir.walk().filter { /* filter to xml */ it.extension == "xml" }.forEach { file ->
        if (file.isFile) { // check for file or not
            try { // catch errors bloc
                //Read XML with Jaxb
                val context = JAXBContext.newInstance(Students::class.java)
                val unmarshaller = context.createUnmarshaller()
                val students = unmarshaller.unmarshal(file) as Students

                //Database connection and execute
                val connection = DataSource().getHikariConnection()
                students.student.forEach { student ->
                    connection.prepareStatement("Insert into test (name, surname, birthday) values ('${student.name}', '${student.surname}', '${student.birthday}')")
                        .use {
                            it.execute()
                        }
                }
                connection.close()

                // place file to archive
                file
                    .copyTo(File("/Users/slava_ivanov_saikyo/2025-q1-kotlin/src/main/resources/archive/${file.name}"))
                file.delete()
            } catch (e: Exception) {
                // logic for errors, place file to error
                println(e.message)
                file.copyTo(
                    File("/Users/slava_ivanov_saikyo/2025-q1-kotlin/src/main/resources/error/${file.name}")
                )
                file.delete()
            }
        }
    }
}