package com.example.amuse.ui.profile

import android.R.id.input
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.amuse.FirebaseUtils
import com.example.amuse.MainActivity.Companion.myUser
import com.example.amuse.R
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await


class AddFriendFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_friend, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val cancelButton = view.findViewById<Button>(R.id.cancel)
        val addButton = view.findViewById<Button>(R.id.add)
        val emailText = view.findViewById<EditText>(R.id.add_friend_email)

        cancelButton.setOnClickListener(){
            Toast.makeText(context, "cancel button pressed", Toast.LENGTH_LONG).show()
            dismiss()
        }
        addButton.setOnClickListener(){
            val email = emailText.text.toString()
            if(email.isEmpty()){
                emailText.error = "Enter email"
                emailText.requestFocusFromTouch()
            } else {
                val userDocument = FirebaseUtils().fireStoreDatabase.collection("Users").document(email)
                val myDocument = FirebaseUtils().fireStoreDatabase.collection("Users").document(myUser.email)
                runBlocking {
                    val user = userDocument.get().await()
                    if (user.exists()) {
                        runBlocking {
                            Log.d("Add", " new friend $email")
                            userDocument.update("friends", FieldValue.arrayUnion(myUser.email))
                            myDocument.update("friends", FieldValue.arrayUnion(email))
//                            val fragment: FriendsFragment =
//                                requireActivity().fragmentManager.findFragmentByTag("FriendsFragment") as FriendsFragment
//                            // Add the new friend to the view
//                            runBlocking {
//                                val friendUserDocument =
//                                    FirebaseUtils().fireStoreDatabase.collection("Users")
//                                        .document(email)
//                                val friendUser = friendUserDocument.get().await()
//                                Log.d(
//                                    "Hi",
//                                    "Added document with ID ${
//                                        friendUser.data?.get("email").toString()
//                                    }"
//                                )
//                                fragment.friendList.add(
//                                    Friend(
//                                        friendUser.data?.get("name").toString(),
//                                        friendUser.data?.get("email").toString(),
//                                        friendUser.data?.get("picture") as Int
//                                    )
//                                )
//                            }
                            dismiss()
                        }
//                        friends = user.data?.get("friends") as List<String>
                    }
                    else{
                        emailText.error = "User not found"
                        emailText.requestFocusFromTouch()
                    }
                }
            }
        }
    }
//
//    override fun onDismiss(dialog: DialogInterface) {
//        super.getView()
//        super.onDismiss(dialog)
//    }
//    interface MyInterface {
//        fun onChoose()
//    }
//
//    private var mListener: MyInterface? = null
//
//    override fun onAttach(activity: Activity) {
//        mListener = activity as MyInterface
//        super.onAttach(activity)
//    }
//
//    override fun onDetach() {
//        mListener = null
//        super.onDetach()
//    }
}