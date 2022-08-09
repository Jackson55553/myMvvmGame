package model.repo

import data.Question

interface Repository {
    fun getQuestion(): Question
    fun getQuestionsFromLocal(): List<Question>
    fun getQuestionsFromRemote(): List<Question>
}