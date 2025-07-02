package com.example.bookly

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path


interface OpenLibraryApi {
    @GET("search.json")
    suspend fun searchBooksBySubject(
        @Query("subject") subjects: List<String>,
        @Query("limit")   limit:   Int
    ): Response<BookResponse>

    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("limit") limit:Int
    ): Response<BookResponse>

    @GET("works/{workId}.json")
    suspend fun getWorkDetails(
        @Path("workId") workId: String
    ): Response<WorkDetailResponse>

    @GET("works/{workId}/ratings.json")
    suspend fun getWorkRating(
        @Path("workId") workId: String
    ): Response<WorkRatingResponse>

    @GET("search.json")
    suspend fun advancedSearch(
        @Query("q")     query: String,
        @Query("limit") limit:   Int
    ): Response<BookResponse>

}
