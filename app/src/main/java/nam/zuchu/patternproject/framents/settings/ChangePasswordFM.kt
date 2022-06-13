package nam.zuchu.patternproject.framents.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import nam.zuchu.patternproject.R
import nam.zuchu.patternproject.database.entities.LibraryManager
import nam.zuchu.patternproject.databinding.FragmentChangePasswordBinding
import nam.zuchu.patternproject.framents.librarymanager.LibraryViewModel

class ChangePasswordFM : Fragment() {
   lateinit var changeBinding:FragmentChangePasswordBinding
   lateinit var viewModel:LibraryViewModel
   lateinit var manager: LibraryManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        changeBinding = FragmentChangePasswordBinding.inflate(layoutInflater)
        initViewModel()
        clickButton()
        return changeBinding.root
    }

    private fun clickButton() {
        changeBinding.fabAdd.apply {
            setOnClickListener {
                var username = changeBinding.tieUsername.text.toString().trim()
                var oldPass = changeBinding.tieOld.text.toString().trim()
                var newPass  = changeBinding.tiePassword.text.toString().trim()
                var confirmPass = changeBinding.tieConfirm.text.toString().trim()
                if(username.isEmpty() || oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()){
                    Toast.makeText(requireContext(),"Fill in all fields", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (newPass != confirmPass){
                    Toast.makeText(requireContext(),"New Password and Confirm Password is wrong", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                viewModel.getManager.observe(viewLifecycleOwner, Observer {
                    for (item in it){
                        if (username == item.username && oldPass == item.password){
                            if (newPass == confirmPass){
                                item.password = confirmPass
                                viewModel.updateManager(item)
                                findNavController().navigate(R.id.action_changePasswordFM_to_bookManageFM)
                            }
                        }
                    }
                })
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[LibraryViewModel::class.java]
    }

}