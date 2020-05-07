package com.example.instant

import android.content.Intent

import android.content.Intent.ACTION_DIAL
import android.content.Intent.ACTION_VIEW

import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CameraActivity : AppCompatActivity() {
    var myFile:Uri?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        var savedUri:Uri?=savedInstanceState.getParcelable("imageUri")
        myFile=savedUri
        imageView.setImageURI(savedUri)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("imageUri",myFile)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            //imageView.setImageURI(Uri.fromFile(myFile))
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
        if(requestCode==2)
        {
            imageView.setImageURI(myFile)
        }
    }

    // was trying to get the path from the uri using the path below but for some reason it kept breaking so instead went with the bit map and that apparently astarted to work.

    fun FullSizedPictureIntent(view: View) {
        val myIntent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
       /* val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val photoFile= File.createTempFile("JPEG_$timeStamp",".jpg",
            getExternalFilesDir(Environment.DIRECTORY_PICTURES))
        val photoUri= FileProvider.getUriForFile(this,"com.example.android.fileprovider",photoFile)
        myFile=Uri.fromFile(photoFile)
        myIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)*/
        startActivityForResult(myIntent,1)
    }






    // these are the Navigational Methods
    fun goHome(view: View) {
        val myIntent= Intent(this, HomeActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); // slide back to the main activity

    }



    fun goSearch(view: View) {
        val myIntent= Intent(this, SearchActivity::class.java)
        startActivity(myIntent)
        // this is a slide back
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    fun goLikes(view: View) {
        val myIntent= Intent(this, LikesActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // slide out to next activity animation
    }

    fun goProfile(view: View) {
        val myIntent= Intent(this, ProfileActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // slide out to next activity animation
    }

}
