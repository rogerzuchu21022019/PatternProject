package nam.zuchu.patternproject.framents.typeofbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import nam.zuchu.patternproject.R
import nam.zuchu.patternproject.database.entities.Book
import nam.zuchu.patternproject.database.entities.TypeOfBook
import nam.zuchu.patternproject.databinding.FragmentUpdateTypeBinding
import nam.zuchu.patternproject.framents.books.BookViewModel
import nam.zuchu.patternproject.framents.members.UpdateMemberFMDirections


class UpdateTypeFM : Fragment() {
    lateinit var updateBinding:FragmentUpdateTypeBinding
    lateinit var viewModel:TypeBookViewModel
    lateinit var type:TypeOfBook
    val args :  UpdateTypeFMArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updateBinding = FragmentUpdateTypeBinding.inflate(layoutInflater)
        initViewModel()
        onClickButton()
        receiveArguments()
        return updateBinding.root
    }

    private fun receiveArguments() {
        type = args.type
        updateBinding.tieName.setText(type.nameType).toString()
    }

    private fun onClickButton() {
        updateBinding.fabAdd.apply {
            setOnClickListener {
                type.nameType = updateBinding.tieName.text.toString().trim()
                viewModel.updateType(type)
                val action= UpdateTypeFMDirections.actionUpdateTypeFMToTypeOfBookFM(type=type)
                findNavController().navigate(action)
            }
        }
        updateBinding.fabDelete.apply {
            setOnClickListener {
                Toast.makeText(requireContext(),"Database is important, You don't have permission to delete ",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[TypeBookViewModel::class.java]
    }

}