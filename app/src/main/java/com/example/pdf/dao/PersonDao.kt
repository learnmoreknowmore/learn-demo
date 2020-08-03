package com.example.pdf.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pdf.bean.Person

@Dao
interface PersonDao {
    @Insert
    fun insertPerson(person: Person)

    @Insert
    fun insertPersons(persons: List<Person>)

    @Delete
    fun deletePerson(person: Person)

    @Query("SELECT * FROM Person ORDER BY name COLLATE NOCASE ASC")
    fun getAllPersons(): DataSource.Factory<Int, Person>
}













