import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.bookly.OpenLibraryApi


object RetrofitInstance {
    val api: OpenLibraryApi = Retrofit.Builder()
        .baseUrl("https://openlibrary.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OpenLibraryApi::class.java)

}
