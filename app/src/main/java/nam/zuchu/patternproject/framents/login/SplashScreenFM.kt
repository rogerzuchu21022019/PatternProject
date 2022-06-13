package nam.zuchu.patternproject.framents.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import nam.zuchu.patternproject.R
import nam.zuchu.patternproject.databinding.FragmentSplashScreenBinding

class SplashScreenFM : Fragment() {
   lateinit var splashBinding:FragmentSplashScreenBinding
   lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        splashBinding = FragmentSplashScreenBinding.inflate(layoutInflater)
        navController = findNavController()
        changeScreen()
        return splashBinding.root
    }
    private fun changeScreen() {
        var handler: Handler = Handler(Looper.myLooper()!!)
        handler.postDelayed(Runnable {
            findNavController().navigate(R.id.action_splashScreenFM_to_signInFM)
        }, 3000)
    }

}