package com.example.amuse.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import com.example.amuse.R
import com.example.amuse.ui.groups.GroupPageFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputLayout
import android.content.Intent
import kotlin.math.max
import android.graphics.Color
import android.util.Log

import android.widget.AutoCompleteTextView

import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.amuse.MainActivity
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text


class AvailableGroupsActivity : AppCompatActivity() {
    lateinit var create_group_button : MaterialButton
    lateinit var create_group_popup_card : MaterialCardView
    lateinit var checkbox_solo_Adventure : CheckBox
    lateinit var min_ppl : TextInputLayout
    lateinit var max_ppl : TextInputLayout
    lateinit var invited_friends : TextInputLayout
    lateinit var event_title : TextInputLayout

    lateinit var confirm_group_creation : MaterialButton

    lateinit var event_title_text : TextInputEditText
    lateinit var min_ppl_text : TextInputEditText
    lateinit var max_ppl_text : TextInputEditText

    lateinit var friends_text : AutoCompleteTextView
    lateinit var no_groups_text : TextView
    var fruits = arrayOf("Lance","Liam","Lilo","Saksham", "Talha", "Vardhan", "Denis", "Yinuo", "Alan", "Alex", "Alejandro", "Yilin", "Ying", "Varun", "Victor", "Tommy", "Tammy", "D_enis")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_groups)

        create_group_button = findViewById<MaterialButton>(R.id.create_group_button)
        create_group_button.setOnClickListener(create_button_listener)

        create_group_popup_card = findViewById<MaterialCardView>(R.id.create_group_popup_card)

        checkbox_solo_Adventure = findViewById<CheckBox>(R.id.solo_checkbox)
        checkbox_solo_Adventure.setOnClickListener(solo_checkbox_listener)

        min_ppl = findViewById<TextInputLayout>(R.id.min_ppl)
        min_ppl_text = findViewById<TextInputEditText>(R.id.min_ppl_text)

        max_ppl = findViewById<TextInputLayout>(R.id.max_ppl)
        max_ppl_text = findViewById<TextInputEditText>(R.id.max_ppl_text)

        event_title = findViewById<TextInputLayout>(R.id.event_title)
        event_title_text = findViewById<TextInputEditText>(R.id.event_title_text)

        invited_friends = findViewById<TextInputLayout>(R.id.invite_friends)
        friends_text = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

        no_groups_text = findViewById<TextView>(R.id.no_groups_text)

        confirm_group_creation = findViewById<MaterialButton>(R.id.confirm_new_group_settings)
        confirm_group_creation.setOnClickListener(confirm_group_listener)


        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.select_dialog_item, fruits)
        val actv = findViewById<View>(R.id.autoCompleteTextView) as AutoCompleteTextView
        actv.threshold = 1
        actv.setAdapter(adapter)
    }

    private val create_button_listener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.create_group_button -> {
                create_group_popup_card.visibility = View.VISIBLE;
            }
        }
    }

    private val solo_checkbox_listener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.solo_checkbox -> {
                if(checkbox_solo_Adventure.isChecked){
                    min_ppl.isEnabled = false;
                    max_ppl.isEnabled = false;
                    invited_friends.isEnabled = false;
                }else{
                    min_ppl.isEnabled = true;
                    max_ppl.isEnabled = true;
                    invited_friends.isEnabled = true;
                }
            }
        }
    }

    private val confirm_group_listener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.confirm_new_group_settings -> {
                val group_to_add = arrayOf<String>(event_title_text.text.toString(), min_ppl_text.text.toString(), max_ppl_text.text.toString(), checkbox_solo_Adventure.isChecked.toString() , friends_text.text.toString())
                val intent = Intent(this, MainActivity::class.java);
//                setContentView(R.layout.fragment_group_page)
                startActivity(intent.putExtra("groupInfo", group_to_add));
                create_group_popup_card.visibility = View.GONE;
                no_groups_text.visibility = View.GONE;
            }
        }
    }




}