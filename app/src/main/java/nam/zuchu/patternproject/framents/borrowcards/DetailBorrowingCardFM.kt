package nam.zuchu.patternproject.framents.borrowcards

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import nam.zuchu.patternproject.R
import nam.zuchu.patternproject.database.entities.BorrowCard
import nam.zuchu.patternproject.databinding.FragmentDetailBorrowingBinding
import java.util.*

class DetailBorrowingCardFM : Fragment() {

    private val args: DetailBorrowingCardFMArgs by navArgs()
    lateinit var detailsBinding: FragmentDetailBorrowingBinding
    lateinit var card: BorrowCard
    lateinit var viewModel: CardViewModel
    var calendar: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsBinding = FragmentDetailBorrowingBinding.inflate(layoutInflater)

        return detailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgs()
        getData()
        initViewModel()
        clickButton()
    }

    private fun getData() {
        var listStatus = listOf("Not Pay", "Paid")
        val arrAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_expandable_list_item_1,
            listStatus
        )
        detailsBinding.tieStatus.setAdapter(arrAdapter)
        detailsBinding.tieStatus.apply {
            setOnItemClickListener { adapterView, view, i, l ->
                card.status = detailsBinding.tieStatus.text.toString()
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[CardViewModel::class.java]
    }

    private fun clickButton() {
        detailsBinding.acbShowEndDay.apply {
            setOnClickListener {
                initDataPicker(this)
            }
        }
        detailsBinding.fabSave.apply {
            setOnClickListener {
                card.endDate = detailsBinding.acbShowEndDay.text.toString()
                card.status = detailsBinding.tieStatus.text.toString()
                viewModel.updateCard(card)
                findNavController().navigate(R.id.action_detailBorrowingCardFM_to_borrowCardsManageFM)
            }
        }
    }

    private fun getArgs() {
        card = args.card
        detailsBinding.tieAutoNameType.setText(card.idBook.toString()).toString()
        detailsBinding.tieQuantity.setText(card.quantity.toString()).toString()
        detailsBinding.acbShowDay.setText(card.createAt).toString()
        detailsBinding.acbShowEndDay.setText(card.endDate).toString()
        detailsBinding.tieFullname.setText(card.fullname).toString()
        detailsBinding.tieAdmin.setText(card.username).toString()
        detailsBinding.tiePrice.setText(card.priceToBorrow.toString()).toString()
        detailsBinding.tieIdMember.setText(card.idMember.toString()).toString()
        detailsBinding.tieNameBook.setText(card.namebook).toString()
        if (card.status == "Paid") {
            detailsBinding.tieStatus.setText(card.status).toString()
            detailsBinding.acbShowDay.isEnabled = false
            detailsBinding.acbShowEndDay.isEnabled = false
            detailsBinding.tilStatus.isEnabled = false
        }

        detailsBinding.tieTotalPaid.setText(card.totalPaid.toString()).toString()
    }

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