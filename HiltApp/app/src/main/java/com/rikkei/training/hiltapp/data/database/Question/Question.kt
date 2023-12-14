package com.rikkei.training.hiltapp.data.database.Question

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
data class QuestionEntity(
    @PrimaryKey val questionId: Int,
    @ColumnInfo val title: String? = null
)