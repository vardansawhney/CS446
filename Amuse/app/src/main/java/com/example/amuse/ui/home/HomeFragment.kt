package com.example.amuse.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.example.amuse.CardAction
import com.example.amuse.DownSwipeAction
import com.example.amuse.Event
import com.example.amuse.LeftSwipeAction
import com.example.amuse.LocalEventStore
import com.example.amuse.MainActivity
import com.example.amuse.OpenCardActivity
import com.example.amuse.R
import com.example.amuse.Review
import com.example.amuse.RightSwipeAction
import com.example.amuse.databinding.FragmentHomeBinding
import com.example.amuse.queryEvents
import com.example.amuse.ui.CardAdapter
import com.yuyakaido.android.cardstackview.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.amuse.uploadData
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputEditText
import kotlin.properties.Delegates


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
    private lateinit var pref_popup_card: MaterialCardView
    private lateinit var cardManager: CardStackLayoutManager
    private lateinit var SettingsButton: AppCompatButton
    private lateinit var SubmitButton: AppCompatButton
    private lateinit var startTime: EditText
    private lateinit var endTime: EditText
//    private lateinit var priceRange: Int?
//    private var distance: Float()
    private lateinit var thrilling_type: CheckBox
    private lateinit var no_alchol_type: CheckBox
    private lateinit var eating_type: CheckBox
    private lateinit var dancing_type: CheckBox
    private lateinit var alcohol_type: CheckBox
    private lateinit var animals_type: CheckBox
    private lateinit var movies_type: CheckBox
    private lateinit var outdoors_type: CheckBox
    private lateinit var malls_type: CheckBox

    private lateinit var price_slider: Slider
    private lateinit var distance_slider: Slider
//    private lateinit var binding: FragmentHomeBinding

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val localStore = LocalEventStore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        cardStackView = binding.root.findViewById(R.id.card_stack)



        price_slider = root.findViewById(R.id.layoutHorizontal1)
        distance_slider = root.findViewById(R.id.distance)
        // Preferences Panel Info
        pref_popup_card = root.findViewById<MaterialCardView>(R.id.pref_popup_card)
        SettingsButton = root.findViewById<AppCompatButton>(R.id.SettingsButton)
        SettingsButton.setOnClickListener(settings_button_press)
        SubmitButton = root.findViewById<AppCompatButton>(R.id.SubmitButton)
        SubmitButton.setOnClickListener(submit_button_press)

        startTime = root.findViewById<TextInputEditText>(R.id.startTime)
        endTime = root.findViewById(R.id.endTime)

        thrilling_type = root.findViewById<CheckBox>(R.id.type1)
        no_alchol_type = root.findViewById<CheckBox>(R.id.type2)
        eating_type = root.findViewById<CheckBox>(R.id.type3)
        dancing_type = root.findViewById<CheckBox>(R.id.type4)
        alcohol_type = root.findViewById<CheckBox>(R.id.type5)
        animals_type = root.findViewById<CheckBox>(R.id.type6)
        movies_type = root.findViewById<CheckBox>(R.id.type7)
        outdoors_type = root.findViewById<CheckBox>(R.id.type8)
        malls_type = root.findViewById<CheckBox>(R.id.type9)

        cardManager = CardStackLayoutManager(this.context, object : CardStackListener{
            override fun onCardDragging(direction: Direction?, ratio: Float) {
                Log.d("Tag", "Dragged")
            }

            override fun onCardSwiped(direction: Direction) {
                if (direction === Direction.Right) {
//                    Toast.makeText(root.context, "Direction Right", Toast.LENGTH_SHORT).show()
                    //Might not work. How to pass context?
                    val rightSwipe:CardAction = RightSwipeAction(container!!.context)
                    rightSwipe.swipeActivity(localStore.currentAvailableEventsStack.removeFirst())
//                    val intent = Intent(activity, AvailableGroupsActivity::class.java)
//                    startActivity(intent)
                }
                if (direction === Direction.Top) {
//                    Toast.makeText(root.context, "Direction Top", Toast.LENGTH_SHORT).show()
                    Log.d("TAG", cardManager.topPosition.toString())
                    startActivity(Intent(root.context, OpenCardActivity::class.java).putExtra("cardID", "card" + cardManager.topPosition.toString()))
                }
                if (direction === Direction.Left) {
                    val leftSwipe:CardAction = LeftSwipeAction(container!!.context)
                    leftSwipe.swipeActivity(localStore.currentAvailableEventsStack.removeFirst())
//
//                    Toast.makeText(root.context, "Direction Left", Toast.LENGTH_SHORT).show()

                    // An example event to upload to Firestore
//                    val event = Event("e_duhsuhf981398", "source_id: String", "source: String", "name: String", "description: String",
//                       "earliest_time: String", "latest_time: String", "address: String", "Waterloo", 1, 5.0,
//                        listOf("type1", "type2"), Review("Adam", 3.4, "This is a review", "Monday: 9:00 AM – 5:00 PM")
//                    )

                    // Test code for uploading data into Firestore
//                    CoroutineScope(Dispatchers.IO).launch {
//                        uploadData(event)
//                    }

                    // Test code for quering data from Firestore
                    /*CoroutineScope(Dispatchers.IO).launch {
                        queryEvents(3, listOf("restaurant")).collect( { data->
                            for (document in data) {
                                Log.d(TAG, "${document.id} => price_level: ${document.data.get("price_level")}, types: ${document.data.get("types")}")
                            }
                        })
                    }*/


                }
                if (direction === Direction.Bottom) {
                    val downSwipe:CardAction = DownSwipeAction(container!!.context)
                    downSwipe.swipeActivity(localStore.currentAvailableEventsStack.removeFirst())
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


//        cardList = ArrayList()
//
//        // Card 1
//        cardList.add(Card("Hiking", "Laurel Trail (2.1 km)", "★★★☆☆", "$", "2 groups", "100 interested", R.drawable.card1_media))
//
//        // Card 2
//        cardList.add(Card("Fancy Dinner", "Shinwa (1.8 km)", "★★★★★", "$$", "1 groups", "2 interested", R.drawable.card2_media))
//
//        // Card 3
//        cardList.add(Card("Clubbing", "Allure (2 km)", "★☆☆☆☆", "$$$$", "0 groups", "0 interested", R.drawable.card3_media))

        cardAdapter = CardAdapter(localStore.cardList)
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

    val settings_button_press = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.SettingsButton -> {
                pref_popup_card.visibility = View.VISIBLE;
            }
        }
    }

    val submit_button_press = View.OnClickListener { view ->
        when (view.getId()) {

            R.id.SubmitButton -> {
                val preferencesFormatted = ArrayList<String>()
                val priceSliderValue = price_slider.value
                val distanceSliderValue = distance_slider.value
                Log.d("tag", priceSliderValue.toString())
                Log.d("tag",distanceSliderValue.toString())
                if (thrilling_type.isChecked){
                    preferencesFormatted.add("amusement_park")
                    preferencesFormatted.add("tourist_attraction")
                    preferencesFormatted.add("art_gallery")
                    preferencesFormatted.add("stadium")
                    preferencesFormatted.add("museum")
                    preferencesFormatted.add("bowling_alley")
                    preferencesFormatted.add("casino")
                }
                if(eating_type.isChecked){
                    preferencesFormatted.add("restaurant")
                    preferencesFormatted.add("meal_takeaway")
                    preferencesFormatted.add("cafe")
                }
                if(alcohol_type.isChecked){
                    if(dancing_type.isChecked){
                        preferencesFormatted.add("night_club")
                    }
                }
                if(animals_type.isChecked){
                    preferencesFormatted.add("pet_store")
                    preferencesFormatted.add("zoo")
                    preferencesFormatted.add("aquarium")
                }
                if(movies_type.isChecked){
                    preferencesFormatted.add("movie_theater")
                }
                if(outdoors_type.isChecked){
                    preferencesFormatted.add("park")
                    preferencesFormatted.add("campground")
                }
                if(malls_type.isChecked){
                    preferencesFormatted.add("bicycle_store")
                    preferencesFormatted.add("book_store")
                    preferencesFormatted.add("clothing_store")
                    preferencesFormatted.add("convenience_store")
                    preferencesFormatted.add("electronics_store")
                    preferencesFormatted.add("shopping_mall")
                    preferencesFormatted.add("shoe_store")
                }

                val thingsToQuery = arrayListOf<Any>(startTime.toString(), endTime.toString(), preferencesFormatted)

                Log.d("tag",preferencesFormatted.size.toString())
                localStore.PullUntilFull(true)
                pref_popup_card.visibility = View.GONE;
            }
        }
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