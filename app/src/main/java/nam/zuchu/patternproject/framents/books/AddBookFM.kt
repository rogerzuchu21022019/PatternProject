package nam.zuchu.patternproject.framents.books

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import nam.zuchu.patternproject.R
import nam.zuchu.patternproject.database.entities.Book
import nam.zuchu.patternproject.database.entities.TypeOfBook
import nam.zuchu.patternproject.databinding.FragmentAddBookBinding
import nam.zuchu.patternproject.framents.members.AddMemberFMDirections
import nam.zuchu.patternproject.framents.typeofbook.TypeBookViewModel

class AddBookFM : Fragment() {
    lateinit var addBinding: FragmentAddBookBinding
    lateinit var viewModel: BookViewModel
    lateinit var viewModelType: TypeBookViewModel
    lateinit var book: Book
    var idType: Int = 0
    var name:String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addBinding = FragmentAddBookBinding.inflate(layoutInflater)
        return addBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        loadDataToSpinner()
        clickButton()
    }

    private fun loadDataToSpinner() {
        viewModelType.getTypes.observe(viewLifecycleOwner, Observer {
            var typeArr = it
            if (typeArr==null){
                return@Observer
            }else{
                for (i1 in typeArr.indices) {
                    val data = arrayOfNulls<String>(typeArr.size)
                    for (j in typeArr.indices) {
                        data[j] = typeArr[j].nameType
                        val arrayAdapter =
                            ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                data
                            )
                        addBinding.autoComplete.setAdapter(arrayAdapter)
                        addBinding.autoComplete.setOnItemClickListener { adapterView, view, i, l ->
                            idType = typeArr[i].idTypeOfBook
                            addBinding.tvIdType.text = idType.toString()
                            name = typeArr[i].nameType
                        }
                    }
                }
            }
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[BookViewModel::class.java]
        viewModelType = ViewModelProvider(this)[TypeBookViewModel::class.java]
    }

    private fun clickButton() {
        addBinding.fabAdd.setOnClickListener {
            val name = addBinding.tieName.text.toString()
            val author = addBinding.tieAuthor.text.toString()
            val company = addBinding.tiePublishCompany.text.toString()
            val year = addBinding.tieYear.text.toString()
            val price = addBinding.tiePrice.text.toString()
            if (idType.toString()!=""){
                Toast.makeText(requireContext(),"Fill in all fields",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                idType = addBinding.tvIdType.text.toString().toInt()
            }

            if (name.isEmpty() || author.isEmpty() || company.isEmpty() || year.isEmpty() || price.isEmpty()){
                Toast.makeText(requireContext(),"Fill in all fields",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            book = Book(
                0,
                idType = idType,
                name = name,
                author = author,
                publishCompany = company,
                year = year.toInt(),
                priceToBorrow = price.toDouble()
            )
            viewModel.insertNewBookInRecyclerView(book = book)
            val action = AddBookFMDirections.actionAddBookFMToBookManageFM(book)
            findNavController().navigate(action)
        }
    }


}