package com.evaluation.testproject.listeners

interface RepositoryListener {
    fun <T : Any> onSuccessResponse(key: String, result: T)
    fun onFailureResponse(key: String, error: String)
}