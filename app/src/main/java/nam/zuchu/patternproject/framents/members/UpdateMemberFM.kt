package nam.zuchu.patternproject.framents.members

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import nam.zuchu.patternproject.database.AppDatabase
import nam.zuchu.patternproject.database.dao.AppDAO
import nam.zuchu.patternproject.database.entities.Member
import nam.zuchu.patternproject.databinding.FragmentUpdateMemberBinding

class UpdateMemberFM : Fragment() {
    lateinit var updateBinding: FragmentUpdateMemberBinding
    lateinit var viewModel: MemberViewModel
    lateinit var navController: NavController
    lateinit var dataSouce: AppDAO
    lateinit var member: Member
    private val args: UpdateMemberFMArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updateBinding = FragmentUpdateMemberBinding.inflate(layoutInflater)
        initViewModel()
        clickButton()
        getNavArgs()
        return updateBinding.root
    }

    private fun getNavArgs() {

        member = args.member
        updateBinding.tieFullname.setText(member.fullname).toString()
        updateBinding.tiePhone.setText(member.phoneNumber).toString()
        updateBinding.tieYear.setText(member.yearOfBirthDay).toString()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun clickButton() {

        updateBinding.fabDone.setOnClickListener {
            member.phoneNumber = updateBinding.tiePhone.text.toString()
            member.fullname = updateBinding.tieFullname.text.toString()
            member.yearOfBirthDay = updateBinding.tieYear.text.toString()
            viewModel.updateMember(member)
            Toast.makeText(requireContext(), "member : " + member.fullname, Toast.LENGTH_SHORT)
                .show()
            val action= UpdateMemberFMDirections.actionUpdateMemberFMToMemberManageFM(member=member)
            findNavController().navigate(action)
        }
        updateBinding.fabDelete.setOnClickListener {
            viewModel.deleteMemberByID(member.idMember)
            val action= UpdateMemberFMDirections.actionUpdateMemberFMToMemberManageFM(member=member)
            findNavController().navigate(action)
        }
    }

    private fun initViewModel() {
        dataSouce = AppDatabase.useDatabase(requireActivity().application).useUserDAO()
        viewModel = ViewModelProvider(this)[MemberViewModel::class.java]
        updateBinding.lifecycleOwner = this
    }

}