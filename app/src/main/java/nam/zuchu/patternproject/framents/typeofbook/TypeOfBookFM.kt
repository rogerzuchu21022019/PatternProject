package nam.zuchu.patternproject.framents.typeofbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import nam.zuchu.patternproject.R
import nam.zuchu.patternproject.database.entities.Member
import nam.zuchu.patternproject.database.entities.TypeOfBook
import nam.zuchu.patternproject.databinding.FragmentTypeOfBookBinding
import nam.zuchu.patternproject.framents.members.MemberManageFMDirections

class TypeOfBookFM : Fragment() {
    lateinit var typeBinding:FragmentTypeOfBookBinding
    lateinit var adapter: TypeBookAdapter
    lateinit var viewModel: TypeBookViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        typeBinding = FragmentTypeOfBookBinding.inflate(layoutInflater)

        return typeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
//        initialData()
        initRecycleView()
        clickButton()
    }

    private fun initialData() {

    }

    private fun clickButton() {
        typeBinding.fabAdd.apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_typeOfBookFM_to_addTypeFM)
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[TypeBookViewModel::class.java]
        viewModel.getTypes.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun initRecycleView() {
        adapter = TypeBookAdapter(ClickItem{
            navDirections(type = it)
        })
        typeBinding.rvType.adapter = adapter
        typeBinding.rvType.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        }
    }
    private fun navDirections(type:TypeOfBook){
        val action = TypeOfBookFMDirections.actionTypeOfBookFMToUpdateTypeFM(type = type)
        findNavController().navigate(action)
    }

}