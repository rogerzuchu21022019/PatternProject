package nam.zuchu.patternproject.framents.books

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.Dispatchers
import nam.zuchu.patternproject.R
import nam.zuchu.patternproject.database.entities.Book
import nam.zuchu.patternproject.database.entities.BorrowCard
import nam.zuchu.patternproject.database.entities.Member
import nam.zuchu.patternproject.database.entities.TypeOfBook
import nam.zuchu.patternproject.databinding.FragmentBookManageBinding
import nam.zuchu.patternproject.framents.borrowcards.CardViewModel
import nam.zuchu.patternproject.framents.members.MemberManageFMDirections
import nam.zuchu.patternproject.framents.members.MemberViewModel
import nam.zuchu.patternproject.framents.typeofbook.TypeBookViewModel

class BookManageFM : Fragment() {
    lateinit var bookBinding: FragmentBookManageBinding
    lateinit var viewModel: BookViewModel
    lateinit var viewModelMember: MemberViewModel
    lateinit var viewModelCard: CardViewModel
    lateinit var viewModelType: TypeBookViewModel
    lateinit var adapter: BookAdapter
    val flag = true
    var count = 0
    val navArgs: BookManageFMArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookBinding = FragmentBookManageBinding.inflate(layoutInflater)
        initRecyclerView()
        initViewModel()
        clickButton()
        for (i in 0..1){
            count++
            if (count==1){
                loadTypes(true)
                break
            }else{
                loadTypes(false)
                break
            }
        }

        return bookBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    private fun loadTypes(isLoad:Boolean) {
        Dispatchers.Main.apply {
            var handler: Handler = Handler(Looper.myLooper()!!)
            handler.postDelayed(Runnable {
                if (isLoad){
                    viewModelType.getTypes.observe(viewLifecycleOwner, Observer {
                        for (item in 0..1) {
                            var book1 = Book(0,it[item].idTypeOfBook,"Day Con L??m Gi??u","Robert T. Kiyosaki v?? Sharon L. Lechter","NXB Tr???", 25000.0, 1997)
                            var book2 = Book(0,it[item].idTypeOfBook,"Vi???t Nam L?????c S???","Tr???n Tr???ng Kim", "NXB Tr???", 300000.0, 1920)
                            var book3 = Book(0,it[item].idTypeOfBook,"CHINH PH???C 1 TRI???U FOLLOW TIKTOK","Thi???u V??n Anh","NXB Tr???",95000.0,2021)
                            var book4 = Book(0,it[item].idTypeOfBook,"Tiktok Marketing - Content ?????t C?? B???t ???????c Trend","Ryan Waman","NXB Tr???",125000.0,2020)
                            var book5 = Book(0, it[item].idTypeOfBook,"L???ch s??? ??m nh???c Vi???t Nam","L?? M???nh Ph??t","NXB Tr???",225000.0,2002)
                            var book6 = Book(0, it[item].idTypeOfBook,"Ph????ng Ph??p ?????u T?? Warren Buffett","Warren Buffett","The New York Times ",320000.0,1997)
                            var book7 = Book(0,it[item].idTypeOfBook,"Tr??n ?????nh ph??? Wall","Peter Lynch","NXB Lao ?????ng - H?? N???i",500000.0,1997)
                            viewModel.insertNewBookInRecyclerView(book1)
                            viewModel.insertNewBookInRecyclerView(book2)
                            viewModel.insertNewBookInRecyclerView(book3)
                            viewModel.insertNewBookInRecyclerView(book4)
                            viewModel.insertNewBookInRecyclerView(book5)
                            viewModel.insertNewBookInRecyclerView(book6)
                            viewModel.insertNewBookInRecyclerView(book7)



                            break
                        }
                    })

                }
            }, 500)

        }

    }


    private fun initRecyclerView() {
        adapter = BookAdapter(ClickItemListener {
            navDirection(it)
        })
        bookBinding.rvBook.adapter = adapter
        bookBinding.rvBook.apply {
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }


    private fun navDirection(book: Book) {
        val action = BookManageFMDirections.actionBookManageFMToDetailBookFM(book = book)
        findNavController().navigate(action)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[BookViewModel::class.java]
        viewModelMember = ViewModelProvider(this)[MemberViewModel::class.java]
        viewModelType = ViewModelProvider(this)[TypeBookViewModel::class.java]
        viewModelCard = ViewModelProvider(this)[CardViewModel::class.java]
        viewModel.getBooks.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun clickButton() {

        bookBinding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_bookManageFM_to_addBookFM)
        }
    }


}