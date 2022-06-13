package nam.zuchu.patternproject.framents.borrowcards

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RestrictTo.Scope
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nam.zuchu.patternproject.R
import nam.zuchu.patternproject.database.entities.Book
import nam.zuchu.patternproject.database.entities.BorrowCard
import nam.zuchu.patternproject.database.entities.Member
import nam.zuchu.patternproject.databinding.FragmentAddBorrowCardBinding
import nam.zuchu.patternproject.framents.books.BookViewModel
import nam.zuchu.patternproject.framents.members.MemberViewModel
import nam.zuchu.patternproject.framents.typeofbook.TypeBookViewModel
import java.util.*


class AddBorrowCardFM : Fragment() {

    lateinit var addBorrowBinding: FragmentAddBorrowCardBinding
    lateinit var viewModel: CardViewModel
    lateinit var viewModelType: TypeBookViewModel
    lateinit var viewModelBook: BookViewModel
    lateinit var viewModelMember: MemberViewModel
    var calendar: Calendar = Calendar.getInstance()
    lateinit var member: Member
    var idMember = 0

    lateinit var borrowCard: BorrowCard
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addBorrowBinding = FragmentAddBorrowCardBinding.inflate(layoutInflater)
        initViewModel()
        getData()
        return addBorrowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickButton()
        initViewModel()
    }

    private fun loadData() {
        var arrInt = listOf("1", "2", "3")
        for (i in arrInt.indices) {
            for (j in arrInt.indices) {
                val arrayAdapter =
                    ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_dropdown_item_1line,
                        arrInt
                    )
                addBorrowBinding.tieQuantity.setAdapter(arrayAdapter)
                addBorrowBinding.tieQuantity.setOnItemClickListener { adapterView, view, i, l ->
                    var price = addBorrowBinding.tiePrice.text.toString()
                    addBorrowBinding.tieTotalPaid.setText((price.toDouble() * (i + 1)).toString())
                        .toString()


                }
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun getData() {
        if (addBorrowBinding.tieAutoNameType.text.isEmpty()) {
            addBorrowBinding.tilQuantity.isEnabled = false
            addBorrowBinding.tieQuantity.isEnabled = false
        }
        viewModelBook.getBooks.observe(viewLifecycleOwner, Observer {

            var bookArr = it
            if (bookArr.isEmpty()) {
                return@Observer
            } else {
                for (i in bookArr.indices) {
                    val data = arrayOfNulls<Int>(bookArr.size)
                    for (j in bookArr.indices) {
                        data[j] = bookArr[j].idBook
                        val arrayAdapter =
                            ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                data
                            )
                        addBorrowBinding.tieAutoNameType.setAdapter(arrayAdapter)
                        addBorrowBinding.tieAutoNameType.setOnItemClickListener { adapterView, view, i, l ->
                            addBorrowBinding.tilQuantity.isEnabled = true
                            addBorrowBinding.tieQuantity.isEnabled = true
                            addBorrowBinding.tieNameBook.setText(bookArr[i].name)
                            addBorrowBinding.tiePrice.setText(bookArr[i].priceToBorrow.toString())
                        }
                    }
                }
            }
        })

        viewModelMember.getMembers.observe(viewLifecycleOwner, Observer {
            var memberArr = it
            if (memberArr == null) {
                return@Observer
            } else {
                for (i1 in memberArr.indices) {
                    val data = arrayOfNulls<String>(memberArr.size)
                    idMember = memberArr[i1].idMember
                    for (j in memberArr.indices) {

                        data[j] = memberArr[j].fullname

                        val arrayAdapter =
                            ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                data
                            )
                        addBorrowBinding.tieAutoFullname.setAdapter(arrayAdapter)
                        addBorrowBinding.tieAutoFullname.setOnItemClickListener { adapterView, view, i, l ->
                            idMember = memberArr[i].idMember
                            addBorrowBinding.tieIdMember.setText(idMember.toString())
                        }
                    }
                }
            }
        })
        loadData()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[CardViewModel::class.java]
        viewModelType = ViewModelProvider(this)[TypeBookViewModel::class.java]
        viewModelBook = ViewModelProvider(this)[BookViewModel::class.java]
        viewModelMember = ViewModelProvider(this)[MemberViewModel::class.java]

    }

    private fun clickButton() {
        addBorrowBinding.acbShowDay.apply {
            setOnClickListener { initDataPicker(addBorrowBinding.acbShowDay) }
        }
        addBorrowBinding.acbShowEndDay.apply {
            setOnClickListener { initDataPicker(addBorrowBinding.acbShowEndDay) }
        }

        addBorrowBinding.fabAdd.apply {
            setOnClickListener {
                var fullname = addBorrowBinding.tieAutoFullname.text.toString()
                var quantity = addBorrowBinding.tieQuantity.text.toString()
                var idBook = addBorrowBinding.tieAutoNameType.text.toString()
                var endAt = addBorrowBinding.acbShowEndDay.text.toString()
                var createAt = addBorrowBinding.acbShowDay.text.toString()
                var nameBook = addBorrowBinding.tieNameBook.text.toString()
                var price = addBorrowBinding.tiePrice.text.toString()
                var status = addBorrowBinding.tieStatus.text.toString()
                var idMember = addBorrowBinding.tieIdMember.text.toString()
                var username = addBorrowBinding.tieAdmin.text.toString()
                var money = addBorrowBinding.tieTotalPaid.text.toString()

                if (fullname.isEmpty() || quantity.isEmpty() || idBook.isEmpty()
                    || endAt.isEmpty() || createAt.isEmpty() || nameBook.isEmpty()
                    || price.isEmpty() || status.isEmpty() || idMember.isEmpty()
                    || username.isEmpty() || money.isEmpty()
                ) {
                    Toast.makeText(requireContext(), "Fill in all fields", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                borrowCard = BorrowCard(
                    0,
                    idMember = idMember.toInt(),
                    idBook = idBook.toInt(),
                    username = username,
                    quantity = quantity.toInt(),
                    fullname = fullname,
                    createAt = createAt,
                    endDate = endAt,
                    status = status,
                    priceToBorrow = price.toDouble(),
                    namebook = nameBook,
                    totalPaid = money.toDouble()
                )

                viewModel.insertNewCard(borrowCard)

                val action =
                    AddBorrowCardFMDirections.actionAddBorrowCardFMToBorrowCardsManageFM(borrowCard)
                findNavController().navigate(action)
            }
        }

    }
//    fun initSpinner(){
//        var status = resources.getStringArray(R.array.status1_not)
//        var spinner =  addBorrowBinding.spinner
//
//        val adapter = ArrayAdapter(requireContext(),
//            android.R.layout.simple_spinner_item, status)
//        spinner.adapter = adapter
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                var text =spinner.selectedItem.toString()
//                Toast.makeText(requireContext(),"ok" + status,Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//        }
//
//    }

    @SuppressLint("SetTextI18n")
    private fun initDataPicker(button: AppCompatButton) {
        val style = DatePickerDialog.THEME_HOLO_LIGHT
        val pickerDialog = DatePickerDialog(
            requireActivity(), style,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                button.text =
                    "$year-" + (month + 1) + "-$dayOfMonth"
                if (TextUtils.isEmpty(button.text)) {
                    Toast.makeText(
                        requireContext(),
                        "Please pick date and click ok to finish",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        pickerDialog.show()
    }
}