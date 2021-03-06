package com.example.ktbook802

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.ktbook802.adapter.BookAdapter
import com.example.ktbook802.adapter.HistoryAdapter
import com.example.ktbook802.databinding.ActivityMainBinding
import com.example.ktbook802.model.BestSellerDTO
import com.example.ktbook802.model.History
import com.example.ktbook802.model.SearchBookDTO
import com.example.ktbook802.util.AppDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BookAdapter
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var bookService: BookService
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = BookAdapter()
        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter

        db = Room.databaseBuilder((applicaionContext)
                                    ,AppDatabase::class.java
                                    ,"BookSearchDB").build()
        //setContentView(R.layout.activity_main)

        //래트로핏 구현체 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("http://book.interpark.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        bookService.getBestSellerBooks(getString(R.string.bookApiKey))
            .enqueue(object: Callback<BestSellerDTO> {
                override fun onResponse(
                    call: Call<BestSellerDTO>,
                    response: Response<BestSellerDTO>
                ) {
                    if(response.isSuccessful.not()){
                        Log.e(TAG, "NOT SUCCESS")
                        return
                    }
                    adapter.submitList(response.body()?.books.orEmpty())
                }

                override fun onFailure(call: Call<BestSellerDTO>, t: Throwable) {
                    //실패처리
                    Log.e(TAG, t.toString())
                }
            })
        binding.searchEditText.setOnKeyListener{v, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.action == MotionEvent.ACTION_DOWN){
            search(binding.searchEditText.text.toString())
            }
        }
    }////////////////// end of Create

    //검색기능 구현
    private fun search(keyword: String){
        bookService.getBooksByName(getString(R.string.bookApiKey), keyword)
            .enqueue(object: Callback<SearchBookDTO> {
                override fun onResponse(
                    call: Call<SearchBookDTO>,
                    response: Response<SearchBookDTO>
                ) {
                    if(response.isSuccessful.not()){
                        Log.e(TAG, "NOT SUCCESS")
                        return
                    }
                    adapter.submitList(response.body()?.books.orEmpty())
                }

                override fun onFailure(call: Call<SearchBookDTO>, t: Throwable) {
                    //실패처리
                    Log.e(TAG, t.toString())
                }
            })
    }

    private fun initHistoryRecyclerView(){
        historyAdapter = HistoryAdapter()
        binding.historyRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.historyRecyclerView.adapter = historyAdapter
    }

    //삭제 후 뷰 갱신처리하기
    private fun showHistoryView(){
        Thread{
            val keywords = db.historyDao().getAll().reversed()
            //아래 코드가 없으면 저장은 되더라도 화면에 보이지 않는다.
            runOnUiThread{
                binding.historyRecyclerView.isVisible = true
                //키워드에 null이 올 수 있으므로
                historyAdapter.submitList(keywords.orEmpty())
            }
        }.start()
    }
    //검색이 실패했을 때도 지워준다.
    private fun hideHistofyView(){
        binding.historyRecyclerView.isVisible = false
    }

    //검색 키워드 등록처리
    private fun saveSearchKey(keyword: String){
        Thread{
            db.historyDao().insertHistory(History(null, keyword))

        }.start()
    }

    //검색 이력 삭제
    private fun deleteSearchKeyword(keyword: String){
        Thread{
            db.historyDao().delete(keyword)
            //삭제하고나서 뷰 갱신처리하기

        }.start()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}