package nam.zuchu.patternproject.framents.borrowcards

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.*
import nam.zuchu.patternproject.R
import nam.zuchu.patternproject.database.entities.Book
import nam.zuchu.patternproject.database.entities.BorrowCard
import nam.zuchu.patternproject.databinding.FragmentBorrowCardsManageBinding
import java.util.*

class BorrowCardsManageFM : Fragment() {
    lateinit var cardBinding: FragmentBorrowCardsManageBinding
    var calendar: Calendar = Calendar.getInstance()
    lateinit var viewModel: CardViewModel
    lateinit var adapter: CardAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cardBinding = FragmentBorrowCardsManageBinding.inflate(layoutInflater)
        return cardBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
        clickButton()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[CardViewModel::class.java]
        viewModel.getCards.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it.isEmpty()){
                Toast.makeText(requireContext(),"Please Insert Data Before Using",Toast.LENGTH_SHORT).show()
            }else{
                adapter.submitList(it)
            }
        })
    }

    private fun clickButton() {
//        cardBinding.acbShowDay.apply {
//            setOnClickListener {
//                initDataPicker()
//
//            }
//        }
        cardBinding.fabAdd.apply {
            setOnClickListener {
                findNavController().navigate(R.id.action_borrowCardsManageFM_to_addBorrowCardFM)

            }
        }
    }

    private fun initRecyclerView() {
        adapter = CardAdapter(ClickItemListener {
            navDirection(it)
        })
        cardBinding.rvCards.adapter = adapter
        cardBinding.rvCards.apply {
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    fun navDirection(card: BorrowCard) {
        val action =
            BorrowCardsManageFMDirections.actionBorrowCardsManageFMToDetailBorrowingCardFM(card)
        findNavController().navigate(action)
    }

//    @SuppressLint("SetTextI18n")
//    private fun initDataPicker() {
//        val style = DatePickerDialog.THEME_HOLO_LIGHT
//        val pickerDialog = DatePickerDialog(
//            requireActivity(), style,
//            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
//                cardBinding.acbShowDay.text =
//                    "$year-" + (month + 1) + "-$dayOfMonth"
//                if (TextUtils.isEmpty(cardBinding.acbShowDay.text)) {
//                    Toast.makeText(
//                        requireContext(),
//                        "Please pick date and click ok to finish",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            },
//            calendar.get(Calendar.YEAR),
//            calendar.get(Calendar.MONTH),
//            calendar.get(Calendar.DAY_OF_MONTH)
//        )
//        pickerDialog.show()
//    }

}