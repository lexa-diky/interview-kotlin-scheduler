package org.example

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

object Cats : Table("cats") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 120)

    override val primaryKey: PrimaryKey? = PrimaryKey(id)
}

fun main() {
    val db = Database.connect("jdbc:sqlite:scheduler.db")
    transaction(db) {
        SchemaUtils.create(Cats)
        Cats.insert {
            it[name] = "Tom"
        }

        Cats.insert {
            it[name] = "jerry"
        }
    }

    transaction {
        Cats.selectAll().forEach { println(it[Cats.name]) }
    }
}