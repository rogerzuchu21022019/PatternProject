package nam.zuchu.patternproject.framents.statics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nam.zuchu.patternproject.R
import nam.zuchu.patternproject.databinding.FragmentDetailStaticBinding
import nam.zuchu.patternproject.databinding.FragmentStaticBinding

class DetailStaticFM : Fragment() {
    lateinit var detailsStaticBinding: FragmentDetailStaticBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsStaticBinding = FragmentDetailStaticBinding.inflate(layoutInflater)
        return detailsStaticBinding.root
    }
}