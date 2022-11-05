package com.bmk.data.repository

import com.bmk.data.ApiService
import com.bmk.data.mapper.mapper
import com.bmk.domain.Repository
import com.bmk.domain.Response
import com.bmk.domain.Result
import javax.inject.Inject


class RepositoryImpl @Inject constructor(val api: ApiService) :
    Repository {
    override suspend fun getData(): Result<Response> {
        return api.getData().mapper()
    }
}