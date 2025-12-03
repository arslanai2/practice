package com.translateall.language.free.translator.dictionary.speechtext.learnenglish
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
@Entity(tableName = "ars")
data class MyDataClass(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String
)
@Dao
interface MyDataClassDAO{
    @Insert
    fun insertdatatodb(data1 : MyDataClass)
}
@Database(entities = [MyDataClass::class], version = 1)
abstract class MyDataClassDatabase : RoomDatabase()
{abstract fun daousage() : MyDataClassDAO}

