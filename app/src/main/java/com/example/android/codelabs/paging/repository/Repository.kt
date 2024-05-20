package com.example.android.codelabs.paging.repository

import com.example.android.codelabs.paging.model.Repo

interface Repository {
    suspend fun getRepos(): Repo
}