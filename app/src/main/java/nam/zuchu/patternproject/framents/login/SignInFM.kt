package nam.zuchu.patternproject.framents.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nam.zuchu.patternproject.R
import nam.zuchu.patternproject.activities.DrawerLayoutActivity
import nam.zuchu.patternproject.activities.MainActivity
import nam.zuchu.patternproject.database.AppDatabase
import nam.zuchu.patternproject.database.entities.Book
import nam.zuchu.patternproject.database.entities.LibraryManager
import nam.zuchu.patternproject.database.entities.Member
import nam.zuchu.patternproject.database.entities.TypeOfBook
import nam.zuchu.patternproject.databinding.FragmentSignInBinding
import nam.zuchu.patternproject.framents.books.BookViewModel
import nam.zuchu.patternproject.framents.librarymanager.LibraryViewModel
import nam.zuchu.patternproject.framents.members.MemberViewModel
import nam.zuchu.patternproject.framents.typeofbook.TypeBookViewModel


class SignInFM : Fragment() {
    lateinit var signInBinding: FragmentSignInBinding
    lateinit var libraryManager: LibraryManager
    lateinit var viewModel: LibraryViewModel
    lateinit var viewModelBook: BookViewModel

    lateinit var viewModelMember: MemberViewModel
    lateinit var viewModelType: TypeBookViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        signInBinding = FragmentSignInBinding.inflate(layoutInflater)
        clickButton()
        return signInBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initDataLibraryManager()
    }


    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[LibraryViewModel::class.java]
        viewModelMember = ViewModelProvider(this)[MemberViewModel::class.java]
        viewModelType = ViewModelProvider(this)[TypeBookViewModel::class.java]
        viewModelBook = ViewModelProvider(this)[BookViewModel::class.java]

    }

    private fun initDataLibraryManager() {
        libraryManager = LibraryManager("admin", "Vu Thanh Nam", "admin", "Admin")
        viewModel.insertNewManager(libraryManager)
        libraryManager = LibraryManager("manager", "Vu Thanh Nam", "vuthanhnam", "Library Manager")
        viewModel.insertNewManager(libraryManager)

        var handler1: Handler = Handler(Looper.myLooper()!!)
        handler1.postDelayed(Runnable {
            viewModelMember.insertNewMember(Member(0,"1996","V?? Th??nh Nam","0879175310"))
            viewModelMember.insertNewMember(Member(0,"1996","Kh???ng Minh Ph??c","0879175310"))
            viewModelMember.insertNewMember(Member(0,"1996","V?? ?????i Hi???u K???","0879175310"))
            viewModelMember.insertNewMember(Member(0,"1996","Nguy???n Thu??? Nh?? Anh","0879175310"))
            viewModelMember.insertNewMember(Member(0,"1996","L?? H???u Nh???t","0879175310"))
            viewModelMember.insertNewMember(Member(0,"1996","Tr???n V??n An","0879175310"))
            viewModelMember.insertNewMember(Member(0,"1996","L?? Xu??n Ho??ng","0879175310"))
        }, 1000)
        var handler2: Handler = Handler(Looper.myLooper()!!)
        var type1 = TypeOfBook(0,"Kinh T???")
        var type2 = TypeOfBook(0,"Music")
        var type3 = TypeOfBook(0,"???m th???c")
        var type4 = TypeOfBook(0,"Toptop")
        var type5 = TypeOfBook(0,"Ch??nh tr???")
        var type6 = TypeOfBook(0,"X?? h???i")
        var type7 = TypeOfBook(0,"Khoa H???c")
        var type8 = TypeOfBook(0,"C??ng Ngh???")
        var type9 = TypeOfBook(0,"L???ch S???")
        handler2.postDelayed(Runnable {
            viewModelType.insertNewType(type1)
            viewModelType.insertNewType(type2)
            viewModelType.insertNewType(type3)
            viewModelType.insertNewType(type4)
            viewModelType.insertNewType(type5)
            viewModelType.insertNewType(type6)
            viewModelType.insertNewType(type7)
            viewModelType.insertNewType(type8)
            viewModelType.insertNewType(type9)
        }, 1000)





//        var handler: Handler = Handler(Looper.myLooper()!!)
//        handler.postDelayed(Runnable {
//
//        }, 3000)

    }

    private fun clickButton() {
        var flag: Boolean? = null
        signInBinding.btnSignIn.apply {
            setOnClickListener {
                lifecycleScope.launch {
                    var username = signInBinding.tieUsername.text.toString().trim()
                    var password = signInBinding.tiePassword.text.toString().trim()
                    if (username.isEmpty() || password.isEmpty()) {
                        Toast.makeText(requireContext(), "Fill in all Fields", Toast.LENGTH_LONG)
                            .show()
                        return@launch
                    } else {
//                        libraryManager = viewModel.getManagerByLogin(username, password)
//                        if (username == libraryManager.username && password != libraryManager.password) {
//
//                            Toast.makeText(
//                                requireContext(), "Login Fail", Toast.LENGTH_SHORT
//                            ).show()
//
//                        } else{
//                            val intent = Intent(requireActivity(), DrawerLayoutActivity::class.java)
//                            startActivity(intent)
//                            requireActivity().finish()
//                            Toast.makeText(
//                                requireContext(), "Login Successfully", Toast.LENGTH_SHORT
//                            ).show()
//                        }

                        viewModel.getManager.observe(viewLifecycleOwner, Observer {
                            for (i in it.indices) {
                                if (username == it[i].username && password == it[i].password) {
                                    flag = true
                                    val intent =
                                        Intent(requireActivity(), DrawerLayoutActivity::class.java)
                                    startActivity(intent)
                                    requireActivity().finish()
                                    Toast.makeText(
                                        requireContext(),
                                        "Login Successfully",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                } else {
                                    flag = false
                                }
                            }
                        })
                    }
                    if (flag==false){
                        Toast.makeText(
                            requireContext(),
                            "Login Failed",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}


