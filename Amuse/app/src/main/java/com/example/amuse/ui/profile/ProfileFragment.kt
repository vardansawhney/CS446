package com.example.amuse.ui.profile

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.amuse.R
import com.google.android.material.tabs.TabLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.amuse.FirebaseUtils
import com.example.amuse.MainActivity.Companion.myProfileImage
import com.example.amuse.MainActivity.Companion.myUser
import com.example.amuse.databinding.FragmentProfileBinding
import com.google.android.material.tabs.TabLayoutMediator
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.ByteArrayOutputStream
import java.io.File


class ProfileFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var vpAdapter: VPAdapter
    private lateinit var username: TextView
    private lateinit var editButton: Button
    private lateinit var profilePic: CircleImageView
    private var pickedPhoto: Uri? = null
    private var pickedBitMap: Bitmap? = null

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        tabLayout = binding.constraintLayout.findViewById(R.id.tabLayout)
        viewPager2 = binding.constraintLayout.findViewById(R.id.viewPager2)
        vpAdapter = VPAdapter(childFragmentManager, lifecycle)
        username = binding.constraintLayout.findViewById(R.id.username)
        editButton = binding.constraintLayout.findViewById(R.id.edit)
        profilePic = binding.constraintLayout.findViewById(R.id.profile_image)

        // Profile Picture
        profilePic.setImageBitmap(myProfileImage)

        editButton.setOnClickListener(){
        Log.d("Photo", "pick a photo")
        if (ContextCompat.checkSelfPermission(this.requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            Log.d("Photo", "in if")
            ActivityCompat.requestPermissions(this.requireActivity(),arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                1)
        } else {
            Log.d("Photo", "in else")
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent,2)

        }
    }

        // Username
        username.text = myUser.name

        // Create Tabs
        viewPager2.adapter = vpAdapter
        TabLayoutMediator(tabLayout, viewPager2){tab,position->
            when(position){
                0->{
                    tab.text = "Friends"
                }
                1->{
                    tab.text = "Pending"
                }
                2->{
                    tab.text = "Settings"
                }
            }
        }.attach()
        return root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.size > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val galleryIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent,2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            pickedPhoto = data.data
            if (pickedPhoto != null) {
                if (Build.VERSION.SDK_INT >= 28) {
                    val source = ImageDecoder.createSource(this.requireActivity().contentResolver,pickedPhoto!!)
                    pickedBitMap = ImageDecoder.decodeBitmap(source)
                    profilePic.setImageBitmap(pickedBitMap)
                    myProfileImage = pickedBitMap
                }
                else {
                    pickedBitMap = MediaStore.Images.Media.getBitmap(this.requireActivity().contentResolver,pickedPhoto)
                    profilePic.setImageBitmap(pickedBitMap)
                    myProfileImage = pickedBitMap
                }
                GlobalScope.launch(Dispatchers.IO) {
                    val storageRef = FirebaseUtils().fireStoreStorage.reference
                    val baos = ByteArrayOutputStream()
                    pickedBitMap!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                    val data = baos.toByteArray()
                    val profileImage = storageRef.child("${myUser.email}.jpg")
                    profileImage.putBytes(data)
                }


            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}