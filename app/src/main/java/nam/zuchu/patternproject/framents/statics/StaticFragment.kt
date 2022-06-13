package nam.zuchu.patternproject.framents.statics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import nam.zuchu.patternproject.database.entities.BorrowCard
import nam.zuchu.patternproject.databinding.FragmentStaticBinding
import nam.zuchu.patternproject.framents.borrowcards.CardViewModel
import nam.zuchu.patternproject.framents.members.MemberViewModel

class StaticFragment : Fragment() {
    lateinit var staticBinding: FragmentStaticBinding
    lateinit var viewModel: StatisticViewModel
    lateinit var viewModelCards: CardViewModel
    lateinit var viewModelMember: MemberViewModel
    lateinit var adapter: StaticAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        staticBinding = FragmentStaticBinding.inflate(layoutInflater)

        return staticBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
        statics()
    }

    private fun statics() {
        var arrString = listOf("Not Pay","Paid","Total Paid Desc","Fullname","Top10")

        for (i in arrString.indices) {
            for (j in arrString.indices) {
                val arrayAdapter =
                    ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_dropdown_item_1line,
                        arrString
                    )
                staticBinding.tieStatic.setAdapter(arrayAdapter)
                staticBinding.tieStatic.setOnItemClickListener { adapterView, view, i, l ->
                    if (i==0 || i ==1 ){

                        staticByStatus(arrString[i])
                        initRecyclerView()
                        staticBinding.tilStaticByName.visibility = View.INVISIBLE
                        staticBinding.tieStaticByName.visibility = View.INVISIBLE
                    }else if (i==2){
                        staticByTotalPaid()
                        initRecyclerView()
                        staticBinding.tilStaticByName.visibility = View.INVISIBLE
                        staticBinding.tieStaticByName.visibility = View.INVISIBLE
                    }else if (i==3){
                        staticByName(arrString[i])
                        initRecyclerView()
                        staticBinding.tilStaticByName.visibility = View.VISIBLE
                        staticBinding.tieStaticByName.visibility = View.VISIBLE
                      loadFullname()
                    }else{
                        staticTop10()
                        initRecyclerView()
                        staticBinding.tilStaticByName.visibility = View.INVISIBLE
                        staticBinding.tieStaticByName.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    private fun loadFullname() {
        viewModelMember.getMembers.observe(viewLifecycleOwner, Observer {
            var memberArr = it
            if (memberArr == null) {
                return@Observer
            } else {
                for (i1 in memberArr.indices) {
                    val data = arrayOfNulls<String>(memberArr.size)
                    for (j in memberArr.indices) {
                        data[j] = memberArr[j].fullname
                        val arrayAdapter =
                            ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                data
                            )
                        staticBinding.tieStaticByName.setAdapter(arrayAdapter)
                        staticBinding.tieStaticByName.setOnItemClickListener { adapterView, view, i, l ->
                            staticByName(memberArr[i].fullname)
                        }
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
        adapter = StaticAdapter(ClickItem {
            navDirection(it)
        })
        staticBinding.rvCards.adapter = adapter
        staticBinding.rvCards.apply {
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun navDirection(it: BorrowCard) {
        val action = StaticFragmentDirections.actionStaticFragmentToDetailBorrowingCardFM(it)
        findNavController().navigate(action)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[StatisticViewModel::class.java]
        viewModelCards = ViewModelProvider(this)[CardViewModel::class.java]
        viewModelMember = ViewModelProvider(this)[MemberViewModel::class.java]
        viewModelCards.getCards.observe(viewLifecycleOwner,Observer{
            if(it.isEmpty()){
                Toast.makeText(
                    requireContext(),"Please Insert Data of List BorrowCard Before Using",Toast.LENGTH_SHORT
                ).show()
            }else{
                adapter.submitList(it)
            }

        })

    }

    private fun staticByStatus(status: String) {
        viewModel.getString(status).observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if(it.isEmpty()){
                Toast.makeText(requireContext(),"Please Insert Status for BorrowCard and comeback to use our service",Toast.LENGTH_SHORT).show()
            }else{
                adapter.submitList(it)
            }
        })
    }

    private fun staticByName(name: String) {
        viewModel.staticByName(name).observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if(it.isEmpty()){
                Toast.makeText(
                    requireContext(),"Please Insert Data of List BorrowCard Before Using",Toast.LENGTH_SHORT
                ).show()
            }else{
                adapter.submitList(it)
            }
        })
    }
    private fun staticByTotalPaid(){
        viewModel.getByTotalPaid().observe(viewLifecycleOwner,Observer{
            if(it.isEmpty()){
                Toast.makeText(
                    requireContext(),"Please Insert Data of List BorrowCard Before Using",Toast.LENGTH_SHORT
                ).show()
            }else{
                adapter.submitList(it)
            }
        })
    }
    private fun staticTop10(){
        viewModel.getTop10().observe(viewLifecycleOwner,Observer{
            if(it.isEmpty()){
                Toast.makeText(
                    requireContext(),"Please Insert Data of List BorrowCard Before Using",Toast.LENGTH_SHORT
                ).show()
            }else{
                adapter.submitList(it)
            }
        })
    }





}