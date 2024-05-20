package com.example.android.codelabs.paging.api

import com.example.android.codelabs.paging.model.Repo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndPoints {

    @GET(ApiDetails.ENDPOINT)
    suspend fun getRepos(): Repo

}