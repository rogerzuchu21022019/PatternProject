package nam.zuchu.patternproject.framents.typeofbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import nam.zuchu.patternproject.R
import nam.zuchu.patternproject.database.entities.TypeOfBook
import nam.zuchu.patternproject.databinding.FragmentAddTypeBinding


class AddTypeFM : Fragment() {
    lateinit var addTypeBinding:FragmentAddTypeBinding
    lateinit var viewModel:TypeBookViewModel
    lateinit var type:TypeOfBook
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addTypeBinding = FragmentAddTypeBinding.inflate(layoutInflater)
        initViewModel()
        onClickButton()
        return addTypeBinding.root
    }

    private fun onClickButton() {
        addTypeBinding.fabAdd.setOnClickListener {
             var name = addTypeBinding.tieName.text.toString().trim()
            type = TypeOfBook(0, nameType = name)
            if (name.isEmpty()){
                Toast.makeText(requireContext(),"Fill in all fields",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.insertNewType(type)
            findNavController().navigate(R.id.action_addTypeFM_to_typeOfBookFM)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[TypeBookViewModel::class.java]
    }

}