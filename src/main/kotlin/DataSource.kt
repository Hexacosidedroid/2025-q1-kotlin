package ru.cib

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

class DataSource {
    // Hikari connection pool implementation
    fun getHikariConnection() = HikariDataSource(HikariConfig().apply {
            driverClassName = "org.postgresql.Driver"
            jdbcUrl = "jdbc:postgresql://localhost:5432/postgres"
            username = "postgres"
            password = "test"
            maximumPoolSize = 30
        }).connection
}