package nam.zuchu.patternproject.framents.borrowcards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import nam.zuchu.patternproject.database.entities.Book
import nam.zuchu.patternproject.database.entities.BorrowCard
import nam.zuchu.patternproject.databinding.ItemListBookBinding
import nam.zuchu.patternproject.databinding.ItemListBorrowCardBinding

class CardAdapter(private val clickItem:ClickItemListener):ListAdapter<BorrowCard, CardAdapter.CardViewHolder>(BookDiffCallback()) {

    class CardViewHolder( val itemListBorrowCardBinding: ItemListBorrowCardBinding) : ViewHolder(itemListBorrowCardBinding.root) {
        fun bind(borrowCard: BorrowCard){
            itemListBorrowCardBinding.card = borrowCard
            itemListBorrowCardBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemListBorrowCardBinding = ItemListBorrowCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CardViewHolder(itemListBorrowCardBinding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val borrowCard = getItem(position)
        holder.bind(borrowCard)
        holder.itemListBorrowCardBinding.root.setOnClickListener {
            clickItem.onClick(borrowCard = borrowCard)
        }
    }


}
class BookDiffCallback : DiffUtil.ItemCallback<BorrowCard>() {
    override fun areItemsTheSame(oldItem: BorrowCard, newItem: BorrowCard): Boolean {
        return oldItem.idBorrowCard == newItem.idBorrowCard
    }

    override fun areContentsTheSame(oldItem: BorrowCard, newItem: BorrowCard): Boolean {
        return areItemsTheSame(oldItem,newItem)
    }
}

class ClickItemListener(private val clickListener:(borrowCard: BorrowCard)->Unit){
    fun onClick(borrowCard: BorrowCard) = clickListener(borrowCard)
}