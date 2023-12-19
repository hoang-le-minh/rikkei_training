package com.rikkei.training.hiltapp.data.entities

import com.rikkei.training.hiltapp.data.database.question.QuestionEntity

fun QuestionEntity.toQuestion(): Question{
    return Question(this.questionId, this.title)
}

fun Question.toQuestionEntity(): QuestionEntity {
    return QuestionEntity(this.questionId, this.title)
}

fun List<QuestionEntity>.toListQuestion(): List<Question> {
    return this.map { it.toQuestion() }
}

fun List<Question>.toListQuestionEntity(): List<QuestionEntity> {
    return this.map { it.toQuestionEntity() }
}