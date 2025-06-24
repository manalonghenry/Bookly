package com.example.bookly

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path


interface OpenLibraryApi {
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String
    ): Response<BookResponse>

    @GET("works/{workId}.json")
    suspend fun getWorkDetails(
        @Path("workId") workId: String
    ): Response<WorkDetailResponse>

    @GET("works/{workId}/ratings.json")
    suspend fun getWorkRating(
        @Path("workId") workId: String
    ): Response<WorkRatingResponse>

}
