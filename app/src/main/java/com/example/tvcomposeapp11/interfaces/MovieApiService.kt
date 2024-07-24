package com.example.tvcomposeapp11.interfaces




import com.example.mytvappcompose.model.MovieResponse
import com.example.mytvappcompose.model.NowPlayingResponse
import com.example.mytvappcompose.model.PopularResponse
import com.example.mytvappcompose.model.SearchResponse
import com.example.mytvappcompose.model.UpcomingResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApiService {
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYjU4MTc3M2Q0ZGUzOTc3MzJkNjBhM2UxZTgzNDY5NSIsInN1YiI6IjY1Yjc2ZDI0ODc0MWM0MDE2MzkxZDE2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.h8T8hpkrQewnzn0cRoKwOlIH_Vg8xrs9my1WVvjNhu0")
    @GET("trending/movie/week")
    suspend fun getTrendingMovies(): MovieResponse

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYjU4MTc3M2Q0ZGUzOTc3MzJkNjBhM2UxZTgzNDY5NSIsInN1YiI6IjY1Yjc2ZDI0ODc0MWM0MDE2MzkxZDE2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.h8T8hpkrQewnzn0cRoKwOlIH_Vg8xrs9my1WVvjNhu0")
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): PopularResponse

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYjU4MTc3M2Q0ZGUzOTc3MzJkNjBhM2UxZTgzNDY5NSIsInN1YiI6IjY1Yjc2ZDI0ODc0MWM0MDE2MzkxZDE2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.h8T8hpkrQewnzn0cRoKwOlIH_Vg8xrs9my1WVvjNhu0")
    @GET("movie/popular")
    suspend fun getPopularMovies(): PopularResponse

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYjU4MTc3M2Q0ZGUzOTc3MzJkNjBhM2UxZTgzNDY5NSIsInN1YiI6IjY1Yjc2ZDI0ODc0MWM0MDE2MzkxZDE2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.h8T8hpkrQewnzn0cRoKwOlIH_Vg8xrs9my1WVvjNhu0")
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): NowPlayingResponse

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYjU4MTc3M2Q0ZGUzOTc3MzJkNjBhM2UxZTgzNDY5NSIsInN1YiI6IjY1Yjc2ZDI0ODc0MWM0MDE2MzkxZDE2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.h8T8hpkrQewnzn0cRoKwOlIH_Vg8xrs9my1WVvjNhu0")
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): UpcomingResponse

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYjU4MTc3M2Q0ZGUzOTc3MzJkNjBhM2UxZTgzNDY5NSIsInN1YiI6IjY1Yjc2ZDI0ODc0MWM0MDE2MzkxZDE2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.h8T8hpkrQewnzn0cRoKwOlIH_Vg8xrs9my1WVvjNhu0")
    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String) : SearchResponse

//    companion object {
//        fun create(): MovieApiService {
//            return RetrofitInstance.api.create(MovieApiService::class.java)
//        }
//    }
}



