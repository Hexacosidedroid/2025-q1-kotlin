package ru.cib

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "students")
data class Students(
    @XmlElement(name = "student")
    var student: List<Student> = listOf(),
)
