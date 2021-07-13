package com.example.ktbook802.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ktbook802.databinding.ItemBookBinding
import com.example.ktbook802.model.Book

class BookAdapter: ListAdapter<Book, BookAdapter.BookItemViewHolder>(diffUtil) {
    inner class BookItemViewHolder(private val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(bookModel: Book){
            binding.titleTextView.text = bookModel.title
            binding.descriptionTextView.text = bookModel.description
            //서버에서 이미지 정보를 가져와서 이미지 로딩 처리하기
            Glide
                .with(binding.coverImageView.context)
                .load(bookModel.coverSmallUrl)
                .into(binding.coverImageView);
        }
    }/////////////// end of inner class

    //미리 만들어진 뷰홀더가 없을 때 실행될 코드
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):BookItemViewHolder{
        return BookItemViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }
    //뷰홀더가 바인딩 되었을 때 실제 데이터를 그려주기
    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int){
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<Book>(){
            override fun areItemsTheSame(oldItem: Book,newItem: Book): Boolean{
                return oldItem == newItem
            }
            //내용이 같은지 체크
            override fun areContentsTheSame(oldItem: Book,newItem: Book): Boolean{
                return oldItem.id == newItem.id
            }
        }
    }
}