package ru.cib

import jakarta.xml.bind.annotation.XmlElement
import jakarta.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "student")
data class Student(
    @XmlElement(name = "name")
    var name: String? = null,
    @XmlElement(name = "surname")
    var surname: String? = null,
    @XmlElement(name = "birthday")
    var birthday: String? = null,
    @XmlElement(name = "hobbies")
    var hobbies: Hobbies? = null
)

@XmlRootElement(name = "hobbies")
data class Hobbies(
    @XmlElement(name = "hobby")
    var hobby: List<String> = mutableListOf()
)
