package com.example.amuse.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.example.amuse.Event
import com.example.amuse.MainActivity
import com.example.amuse.OpenCardActivity
import com.example.amuse.R
import com.example.amuse.Review
import com.example.amuse.databinding.FragmentHomeBinding
import com.example.amuse.queryEvents
import com.example.amuse.ui.CardAdapter
import com.yuyakaido.android.cardstackview.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.amuse.uploadData


//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.core.view.updateLayoutParams
//import androidx.customview.widget.ViewDragHelper
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import com.example.amuse.MainActivity
//import com.example.amuse.OpenCardActivity
//import com.example.amuse.R
//import com.example.amuse.databinding.FragmentHomeBinding
//import com.google.android.material.card.MaterialCardView

const val TAG = "FIRESTORE"

class HomeFragment : Fragment() {
    private lateinit var cardList: ArrayList<Card>
    private lateinit var cardAdapter: CardAdapter
    private lateinit var cardStackView: CardStackView
    private lateinit var cardManager: CardStackLayoutManager
//    private lateinit var binding: FragmentHomeBinding

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        cardStackView = binding.root.findViewById(R.id.card_stack)

        cardManager = CardStackLayoutManager(this.context, object : CardStackListener{
            override fun onCardDragging(direction: Direction?, ratio: Float) {
                Log.d("Tag", "Dragged")
            }

            override fun onCardSwiped(direction: Direction) {
                if (direction === Direction.Right) {
//                    Toast.makeText(root.context, "Direction Right", Toast.LENGTH_SHORT).show()
                    val intent = Intent(activity, AvailableGroupsActivity::class.java)
                    startActivity(intent)
                }
                if (direction === Direction.Top) {
//                    Toast.makeText(root.context, "Direction Top", Toast.LENGTH_SHORT).show()
                    Log.d("TAG", cardManager.topPosition.toString())
                    startActivity(Intent(root.context, OpenCardActivity::class.java).putExtra("cardID", "card" + cardManager.topPosition.toString()))
                }
                if (direction === Direction.Left) {
//                    Toast.makeText(root.context, "Direction Left", Toast.LENGTH_SHORT).show()

                    // An example event to upload to Firestore
                    val event = Event("e_duhsuhf981398", "source_id: String", "source: String", "name: String", "description: String",
                       "earliest_time: String", "latest_time: String", "address: String", "Waterloo", 1, 5.0,
                        listOf("type1", "type2"), Review("Adam", 3.4, "This is a review", "Monday: 9:00 AM – 5:00 PM")
                    )

                    // Test code for uploading data into Firestore
                    CoroutineScope(Dispatchers.IO).launch {
                        uploadData(event)
                    }

                    // Test code for quering data from Firestore
                    CoroutineScope(Dispatchers.IO).launch {
                        queryEvents(3, listOf("restaurant")).collect( { data->
                            for (document in data) {
                                Log.d(TAG, "${document.id} => price_level: ${document.data.get("price_level")}, types: ${document.data.get("types")}")
                            }
                        })
                    }


                }
                if (direction === Direction.Bottom) {
//                    Toast.makeText(
//                        root.context,
//                        "Direction Bottom",
//                        Toast.LENGTH_SHORT
//                    ).show()
                }

                // Paginating
//                if (cardManager!!.topPosition == cardAdapter.itemCount - 5) {
//                    paginate()
//                }
            }

            override fun onCardRewound() {
                Log.d("Tag", "Rewound")
            }

            override fun onCardCanceled() {
                Log.d("Tag", "Canceled")
            }

            override fun onCardAppeared(view: View?, position: Int) {
                Log.d("Tag", "Appeared")
            }

            override fun onCardDisappeared(view: View?, position: Int) {
                Log.d("Tag", "Disappeared")
            }

        })
        cardManager.setDirections(Direction.FREEDOM)


        cardList = ArrayList()

        // Card 1
        cardList.add(Card("Hiking", "Laurel Trail (2.1 km)", "★★★☆☆", "$", "2 groups", "100 interested", R.drawable.card1_media))

        // Card 2
        cardList.add(Card("Fancy Dinner", "Shinwa (1.8 km)", "★★★★★", "$$", "1 groups", "2 interested", R.drawable.card2_media))

        // Card 3
        cardList.add(Card("Clubbing", "Allure (2 km)", "★☆☆☆☆", "$$$$", "0 groups", "0 interested", R.drawable.card3_media))

        cardAdapter = CardAdapter(cardList)
        cardStackView.layoutManager = cardManager
        cardStackView.adapter = cardAdapter

        cardAdapter.setOnClickListener(object: CardAdapter.OnClickListener {
            override fun onClick(position: Int, model: Card) {
                Log.d("TAG", "hello")
                startActivity(Intent(root.context, OpenCardActivity::class.java).putExtra("cardID", "card$position"))
            }
        })

        return root
    }
//
//    private lateinit var homeViewModel: HomeViewModel
//    private var _binding: FragmentHomeBinding? = null
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
//
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        /*
//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
//        */
//
//        binding.card1.setOnClickListener {
//            requireActivity().run {
//                startActivity(Intent(this, OpenCardActivity::class.java).putExtra("cardID", "card2"))
//                finish()
//            }
//        }
//
//        binding.card2.setOnClickListener {
//            requireActivity().run {
//                startActivity(Intent(this, OpenCardActivity::class.java).putExtra("cardID", "card2"))
//                finish()
//            }
//        }
//
//        binding.card3.setOnClickListener {
//            requireActivity().run {
//                startActivity(Intent(this, OpenCardActivity::class.java).putExtra("cardID", "card3"))
//                finish()
//            }
//        }
//
//        return root
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}