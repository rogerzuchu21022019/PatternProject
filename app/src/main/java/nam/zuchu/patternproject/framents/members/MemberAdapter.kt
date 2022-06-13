package nam.zuchu.patternproject.framents.members

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nam.zuchu.patternproject.database.entities.Book
import nam.zuchu.patternproject.database.entities.Member
import nam.zuchu.patternproject.databinding.ItemListBookBinding
import nam.zuchu.patternproject.databinding.ItemListMemberBinding

class MemberAdapter(private val clickItem: ClickItem) : ListAdapter<Member,MemberAdapter.MemberViewHolder>(MemberDiffCallBack()) {
    class MemberViewHolder(val itemListMemberBinding: ItemListMemberBinding) :RecyclerView.ViewHolder(itemListMemberBinding.root) {
        fun bind(member: Member){
            itemListMemberBinding.apply {
                itemListMemberBinding.member = member
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val itemListMemberBinding = ItemListMemberBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  MemberViewHolder(itemListMemberBinding =  itemListMemberBinding)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val member = getItem(position)
        holder.bind(member)
        holder.itemListMemberBinding.root.setOnClickListener {
            clickItem.onClick(member)
        }
    }
}

class MemberDiffCallBack : DiffUtil.ItemCallback<Member>() {
    override fun areItemsTheSame(oldItem: Member, newItem: Member): Boolean {
        return oldItem.idMember == newItem.idMember
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Member, newItem: Member): Boolean {
        return oldItem == newItem
    }
}

class  ClickItem(private val clickItem: (Member:Member)->Unit){
    fun onClick(member: Member) = clickItem(member)
}
