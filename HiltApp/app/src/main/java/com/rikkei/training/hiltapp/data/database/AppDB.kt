package com.rikkei.training.hiltapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rikkei.training.hiltapp.data.database.Question.QuestionDao
import com.rikkei.training.hiltapp.data.database.Question.QuestionEntity

@Database(entities = [QuestionEntity::class],version = 1,exportSchema = false)
abstract class AppDB : RoomDatabase(){

    abstract fun questionDao(): QuestionDao

}