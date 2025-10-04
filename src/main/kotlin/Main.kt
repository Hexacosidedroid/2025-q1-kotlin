package ru.cib

import java.io.File
import java.sql.Date
import java.sql.Statement
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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
                    //Date from xml (string) to Date at Java
                    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                    val date = LocalDate.parse(student.birthday, formatter)
                    //Executing students insert to table
                    val insertStudent = connection.prepareStatement(
                        "Insert into students (name, surname, birthday) values (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                    ).apply {
                        setString(1, "${student.name}")
                        setString(2, "${student.surname}")
                        setDate(3, Date.valueOf(date))
                    }
                    insertStudent.executeUpdate()
                    //Return id of student in table
                    val rs = insertStudent.generatedKeys
                    if (rs.next()) {
                        val id = rs.getInt(1)
                        //Insert hobbies by student_id
                        student.hobbies?.hobby?.forEach { hobby ->
                            connection.prepareStatement("Insert into hobbies (student_id, hobby) values ('${id}', '${hobby}')")
                                .use {
                                    it.execute()
                                }
                        }
                    }
                }
                connection.close()
                // place file to archive
                file
                    .copyTo(File("/Users/slava_ivanov_saikyo/2025-q1-kotlin/src/main/resources/archive/${file.name}"))
                file.delete()
            } catch (e: Exception) {
                // logic for errors, place file to error
                println(e)
                file.copyTo(
                    File("/Users/slava_ivanov_saikyo/2025-q1-kotlin/src/main/resources/error/${file.name}")
                )
                file.delete()
            }
        }
    }
}