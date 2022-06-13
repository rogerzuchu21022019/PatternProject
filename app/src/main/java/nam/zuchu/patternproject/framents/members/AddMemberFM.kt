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
import nam.zuchu.patternproject.database.AppDatabase
import nam.zuchu.patternproject.database.dao.AppDAO
import nam.zuchu.patternproject.database.entities.Member
import nam.zuchu.patternproject.databinding.FragmentAddMemberBinding

class AddMemberFM : Fragment() {
    lateinit var addMemberBinding:FragmentAddMemberBinding
    lateinit var viewModel: MemberViewModel
    lateinit var navController: NavController
    lateinit var dataSouce: AppDAO
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       addMemberBinding = FragmentAddMemberBinding.inflate(layoutInflater)
        initViewModel()
        clickButton()

        return addMemberBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun clickButton() {
        addMemberBinding.fabAdd.setOnClickListener {


            var fullname = addMemberBinding.tieFullname.text.toString().trim()
            var phone = addMemberBinding.tiePhone.text.toString().trim()
            var year = addMemberBinding.tieYear.text.toString().trim()
            if (fullname.isEmpty() || phone.isEmpty() || year.isEmpty()){
                Toast.makeText(requireContext(),"Fill in all fields",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val member = Member(0,yearOfBirthDay = year, fullname = fullname, phoneNumber = phone)
            viewModel.insertNewMember(member)

            val action= AddMemberFMDirections.actionAddMemberFMToMemberManageFM(member=member)
//            val action= AddMemberFMDirections.actionAddMemberFMToMemberManageFM()
            findNavController().navigate(action)

        }
    }


    private fun initViewModel() {
        dataSouce = AppDatabase.useDatabase(requireActivity().application).useUserDAO()
        viewModel  = ViewModelProvider(this)[MemberViewModel::class.java]
        addMemberBinding.lifecycleOwner = this
    }

}