package nam.zuchu.patternproject.framents.typeofbook

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nam.zuchu.patternproject.database.entities.TypeOfBook
import nam.zuchu.patternproject.databinding.ItemListTypeBookBinding

class TypeBookAdapter(private val onClickListener: ClickItem) : ListAdapter<TypeOfBook,TypeBookAdapter.TypeBookViewHolder>(TypeDiffCallBack()) {
    class TypeBookViewHolder(val itemListTypeBookBinding: ItemListTypeBookBinding) :RecyclerView.ViewHolder(itemListTypeBookBinding.root) {
        fun bind(typeOfBook: TypeOfBook){
            itemListTypeBookBinding.apply {
                itemListTypeBookBinding.type = typeOfBook
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeBookViewHolder {
        val itemListTypeBookBinding = ItemListTypeBookBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  TypeBookViewHolder(itemListTypeBookBinding =  itemListTypeBookBinding)
    }

    override fun onBindViewHolder(holder: TypeBookViewHolder, position: Int) {
        val typeOfBook = getItem(position)
        holder.bind(typeOfBook)
        holder.itemListTypeBookBinding.root.setOnClickListener {
            onClickListener.onClick(typeOfBook)
        }
    }
}

class TypeDiffCallBack : DiffUtil.ItemCallback<TypeOfBook>() {
    override fun areItemsTheSame(oldItem: TypeOfBook, newItem: TypeOfBook): Boolean {
        return oldItem.idTypeOfBook == newItem.idTypeOfBook
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: TypeOfBook, newItem: TypeOfBook): Boolean {
        return oldItem == newItem
    }
}

class  ClickItem(private val clickItem: (TypeOfBook:TypeOfBook)->Unit){
    fun onClick(typeOfBook: TypeOfBook) = clickItem(typeOfBook)
}
