package nam.zuchu.patternproject.framents.books

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import nam.zuchu.patternproject.R
import nam.zuchu.patternproject.database.entities.Book
import nam.zuchu.patternproject.databinding.FragmentDetailsBinding


class DetailBookFM : Fragment() {
    lateinit var detailsBinding: FragmentDetailsBinding
    val navargs : DetailBookFMArgs by navArgs()
    lateinit var viewModel: BookViewModel
    lateinit var book:Book
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsBinding = FragmentDetailsBinding.inflate(layoutInflater)
        initViewModel()
        navDirection()
        return detailsBinding.root
    }

    private fun navDirection() {
        detailsBinding.fabSave.apply {
            setOnClickListener {
                book.priceToBorrow =  detailsBinding.tiePrice.text.toString().trim().toDouble()
                viewModel.updateInformationBook(book)
                val action = DetailBookFMDirections.actionDetailBookFMToBookManageFM(book)
                findNavController().navigate(action)
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[BookViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNav()
    }

    private fun getNav() {
        book = navargs.book
        detailsBinding.book = book
    }

}