package com.example.visuallithuanian.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [FlashcardPair::class], version = 3, exportSchema = true)
abstract class FlashcardDatabase : RoomDatabase() {

    abstract fun cardPairDao():FlashCardDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var wordDao = database.cardPairDao()

                    wordDao.getAlphabetizedWords()



                }
            }
        }
    }
    companion object {
        @Volatile
        private var INSTANCE:FlashcardDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ):FlashcardDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FlashcardDatabase::class.java,
                    "word_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
