package com.example.android.codelabs.paging.repository

import com.example.android.codelabs.paging.api.ApiDetails
import com.example.android.codelabs.paging.api.ApiEndPoints
import com.example.android.codelabs.paging.data.GithubRepository
import com.example.android.codelabs.paging.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val apiDetails: ApiEndPoints) : Repository {

        override suspend fun getRepos(): Repo {
            return apiDetails.getRepos()
    }
}