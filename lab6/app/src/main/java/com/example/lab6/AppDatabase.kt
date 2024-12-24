package com.example.lab6

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/*val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Добавляем новое поле imageResId в таблицу Violation
        db.execSQL("ALTER TABLE Violation ADD COLUMN imageResId INTEGER DEFAULT 0 NOT NULL")
    }
}*/


@Database(entities = [Violation::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun violationDao(): ViolationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "violation_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}