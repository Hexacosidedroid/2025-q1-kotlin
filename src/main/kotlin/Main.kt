package ru.cib

import java.io.File
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller

fun main() {
//    val readFile = File("/Users/slava_ivanov_saikyo/2025-q1-kotlin/src/main/resources/students.xml")
    val context = JAXBContext.newInstance(Students::class.java)
//    val unmarshaller = context.createUnmarshaller()
//    val studentList = unmarshaller.unmarshal(readFile) as Students
//    studentList.student?.forEach {
//        it.name = "TEST"
//    }

    val studentList = listOf<Student>(
        Student("test", "test", "01.01.1900"),
        Student("test1", "test1", "01.01.1900"),
        Student("test2", "test2", "01.01.1900"),
        Student("test3", "test3", "01.01.1900"),
        Student("test4", "test4", "01.01.1900"),
        Student("test5", "test5", "01.01.1900"),
        Student("test6", "test6", "01.01.1900"),
    )
    val studentsWrapper = Students(studentList)

    val writeFile = File("/Users/slava_ivanov_saikyo/2025-q1-kotlin/src/main/resources/students-new.xml")

    val marshaller = context.createMarshaller()
//    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
    marshaller.marshal(studentsWrapper, writeFile)
}
