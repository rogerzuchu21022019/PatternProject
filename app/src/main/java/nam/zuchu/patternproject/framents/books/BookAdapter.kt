package nam.zuchu.patternproject.framents.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import nam.zuchu.patternproject.database.entities.Book
import nam.zuchu.patternproject.databinding.ItemListBookBinding

class BookAdapter(private val clickItem:ClickItemListener):ListAdapter<Book, BookAdapter.BookViewHolder>(BookDiffCallback()) {

    class BookViewHolder( val itemListBookBinding: ItemListBookBinding) : ViewHolder(itemListBookBinding.root) {
        fun bind(book: Book){
            itemListBookBinding.book = book
            itemListBookBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemListBookBinding = ItemListBookBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BookViewHolder(itemListBookBinding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
        holder.itemListBookBinding.root.setOnClickListener {
            clickItem.onClick(book=book)
        }

    }

}
class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.idBook == newItem.idBook
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return areItemsTheSame(oldItem,newItem)
    }

}

class ClickItemListener(private val clickListener:(book: Book)->Unit){
    fun onClick(book: Book) = clickListener(book)
}