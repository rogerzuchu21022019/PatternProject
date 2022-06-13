package nam.zuchu.patternproject.framents.statics

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nam.zuchu.patternproject.database.entities.BorrowCard
import nam.zuchu.patternproject.database.entities.Member
import nam.zuchu.patternproject.databinding.ItemListMemberBinding
import nam.zuchu.patternproject.databinding.ItemListStaticBinding

class StaticAdapter(private val clickItem: ClickItem) : ListAdapter<BorrowCard,StaticAdapter.MemberViewHolder>(BorrowCardDiffCallBack()) {
    class MemberViewHolder(val itemListStaticBinding: ItemListStaticBinding) :RecyclerView.ViewHolder(itemListStaticBinding.root) {
        fun bind(card: BorrowCard){
            itemListStaticBinding.apply {
                itemListStaticBinding.card = card
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val itemListStaticBinding = ItemListStaticBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  MemberViewHolder(itemListStaticBinding =  itemListStaticBinding)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val card = getItem(position)
        holder.bind(card)
        holder.itemListStaticBinding.root.setOnClickListener {
            clickItem.onClick(card)
        }
    }
}

class BorrowCardDiffCallBack : DiffUtil.ItemCallback<BorrowCard>() {
    override fun areItemsTheSame(oldItem: BorrowCard, newItem: BorrowCard): Boolean {
        return oldItem.idBorrowCard == newItem.idBorrowCard
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: BorrowCard, newItem: BorrowCard): Boolean {
        return oldItem == newItem
    }
}

class  ClickItem(private val clickItem: (BorrowCard:BorrowCard)->Unit){
    fun onClick(card: BorrowCard) = clickItem(card)
}
