//Evan Quinn
//Four In A Row
//2/25/24
package com.hfad.connect4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        val startButton = view.findViewById<Button>(R.id.button)

        startButton.setOnClickListener {
            val textView = view.findViewById(R.id.name_text) as TextView
            val name = textView.text.toString()
            view.findNavController()
                .navigate(SplashFragmentDirections.actionSplashToGameBoard(name))
        }
        return view
    }
}