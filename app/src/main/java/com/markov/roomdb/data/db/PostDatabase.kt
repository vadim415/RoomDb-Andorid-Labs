package com.markov.roomdb.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.markov.roomdb.data.dao.PostDao
import com.markov.roomdb.data.model.Post

@Database(
    entities = [Post::class],
    version = 1
)
abstract class PostDatabase : RoomDatabase() {
    abstract fun dao(): PostDao

    companion object {
        @Volatile
        private var INSTANCE: PostDatabase? = null

        fun getDatabase(context: Context): PostDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        PostDatabase::class.java,
                        "posts_db"
                    )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}