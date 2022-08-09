package model.repo

import data.Question
import data.getQuestionsList

class RepositoryImpl: Repository {
    override fun getQuestion(): Question {
        return Question(" каталась(лся) на коньках.")
    }

    override fun getQuestionsFromLocal(): List<Question> {
        return getQuestionsList()
    }

    override fun getQuestionsFromRemote(): List<Question> {
        return getQuestionsList()
    }

}