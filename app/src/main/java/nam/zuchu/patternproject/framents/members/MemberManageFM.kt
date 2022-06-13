package nam.zuchu.patternproject.framents.members

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import nam.zuchu.patternproject.R
import nam.zuchu.patternproject.database.dao.AppDAO
import nam.zuchu.patternproject.database.entities.Member
import nam.zuchu.patternproject.databinding.FragmentMemberManageBinding
import nam.zuchu.patternproject.generated.callback.OnClickListener

class MemberManageFM : Fragment() {
    lateinit var memberBinding: FragmentMemberManageBinding
    lateinit var viewModel: MemberViewModel
    lateinit var adapter: MemberAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        memberBinding = FragmentMemberManageBinding.inflate(layoutInflater)
        return memberBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
        initViewModel()
        clickButton()
    }
    private fun initRecycleView() {
        adapter = MemberAdapter(ClickItem {
            navDirections(member = it)
            Log.d("SENT_MEMBER","navDicrection to detail member")
        })
        memberBinding.rvMember.adapter = adapter
        memberBinding.rvMember.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MemberViewModel::class.java]
        this.memberBinding.memberViewModel = viewModel
        viewModel.getMembers.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            Log.d("SUBMITLIST","submitlist successfully")
        })
    }

    private fun clickButton() {
        memberBinding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_memberManageFM_to_addMemberFM)
        }
//        memberBinding.fabDelete.setOnClickListener {
//            viewModel.deleteMemberByID(id = 2)
//        }
    }
    fun navDirections(member:Member){
        val action = MemberManageFMDirections.actionMemberManageFMToUpdateMemberFM(member)

        findNavController().navigate(action)
    }

}