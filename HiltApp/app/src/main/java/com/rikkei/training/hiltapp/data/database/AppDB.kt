package com.rikkei.training.hiltapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rikkei.training.hiltapp.data.database.question.QuestionDao
import com.rikkei.training.hiltapp.data.database.question.QuestionEntity

@Database(entities = [QuestionEntity::class],version = 1,exportSchema = false)
abstract class AppDB : RoomDatabase(){

    abstract fun questionDao(): QuestionDao

}