package nam.zuchu.patternproject.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import nam.zuchu.patternproject.R
import nam.zuchu.patternproject.database.entities.Book
import nam.zuchu.patternproject.database.entities.Member
import nam.zuchu.patternproject.framents.books.BookAdapter
import nam.zuchu.patternproject.framents.members.MemberAdapter

//class BindingAdapters {
//    companion object{
//        @BindingAdapter("listMember")
//        @JvmStatic
//        fun RecyclerView.setListDataMember(members: List<Member>?){
//            members.let {
//                (adapter as MemberAdapter).submitList(members)
//            }
//        }


//@BindingAdapter("listMember")
//        @JvmStatic
//fun  bindItemViewModels(recyclerView: RecyclerView, members: List<Member>?) {
//    val adapter = getOrCreateAdapter(recyclerView)
//    adapter.submitList(members)
//}


//}
//        @BindingAdapter("listBook")
//        @JvmStatic
//        fun RecyclerView.setListDataBook(books:List<Book>?){
//            books?.let {
//                (adapter as BookAdapter).submitList(books)
//            }
//        }
//        @BindingAdapter("imageRecourse")
//        @JvmStatic
//        fun CircleImageView.setImage(imgURL:String?){
//            imgURL?.let {
//                val imgURL = imgURL.toUri().buildUpon().scheme("https").build()
//                Glide.with(this.context).load(imgURL).apply {
//                    RequestOptions.placeholderOf(R.drawable.girl)
//                        .error(R.drawable.add)
//                }.into(this)
//            }
//        }
//    }
//
//
//
//}