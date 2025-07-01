import com.example.bookly.BookResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.bookly.OpenLibraryApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


object RetrofitInstance {
    val api: OpenLibraryApi = Retrofit.Builder()
        .baseUrl("https://openlibrary.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OpenLibraryApi::class.java)

}

interface OpenLibraryApi {
    @GET("search.json")
    suspend fun searchBooksBySubject(
        @Query("subject") subject: String, //  “genre” filter
        @Query("limit")   limit:   Int
    ): Response<BookResponse>
}