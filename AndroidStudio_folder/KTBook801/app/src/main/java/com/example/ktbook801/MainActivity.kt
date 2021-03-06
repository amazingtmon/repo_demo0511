package com.example.ktbook801

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //래트로핏 구현체 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("http://book.interpark.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val bookService = retrofit.create(BookService::class.java)

        bookService.getBestSellerBooks("A0C5D742FAE5C3A0D18BD4DFE8E00E336F4873DCF0A18D39C2C5A29920C4C91D")
            .enqueue(object: Callback<BestSellerDTO> {
                override fun onResponse(
                    call: Call<BestSellerDTO>,
                    response: Response<BestSellerDTO>
                ) {
                    if(response.isSuccessful.not()){
                        Log.e(TAG, "NOT SUCCESS")
                        return
                    }
                    response.body()?.let{
                        Log.d(TAG, "body에서 꺼내오기"+it.toString())
                        it.books.forEach{ book ->
                            Log.d(TAG, book.toString())
                        }
                    }
                }

                override fun onFailure(call: Call<BestSellerDTO>, t: Throwable) {
                    //실패처리
                    Log.e(TAG, t.toString())
                }
            })
    }
    companion object {
        private const val TAG = "MainActivity"
    }
}