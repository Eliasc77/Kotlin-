package com.example.ainterdent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap


class AboutUsFragment : Fragment() {

    private lateinit var map:GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createFragment()
        return inflater.inflate(R.layout.fragment_about_us, container, false)
    }

    private fun createFragment() {
        val mapFragment: Fragment? = parentFragmentManager.findFragmentById(R.id.map)
    }


}