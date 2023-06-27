package com.example.amuse.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import androidx.customview.widget.ViewDragHelper
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.amuse.MainActivity
import com.example.amuse.OpenCardActivity
import com.example.amuse.R
import com.example.amuse.databinding.FragmentHomeBinding
import com.google.android.material.card.MaterialCardView

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        */

        binding.card1.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, OpenCardActivity::class.java).putExtra("cardID", "card1"))
                finish()
            }
        }

        binding.card2.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, OpenCardActivity::class.java).putExtra("cardID", "card2"))
                finish()
            }
        }

        binding.card3.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, OpenCardActivity::class.java).putExtra("cardID", "card3"))
                finish()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}