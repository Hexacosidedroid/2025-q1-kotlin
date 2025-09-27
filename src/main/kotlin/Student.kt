package ru.cib

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "student")
data class Student(
    @XmlElement(name = "name")
    var name: String? = null,
    @XmlElement(name = "surname")
    var surname: String? = null,
    @XmlElement(name = "birthday")
    var birthday: String? = null
)
