package com.example.instant

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File

import java.io.FileNotFoundException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*

private const val PICK_PHOTO_CODE = 1

class CameraActivity : AppCompatActivity() {
    var myFile:Uri?=null
    val db = DB()
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

        if (requestCode == PICK_PHOTO_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                myFile = data?.data
                imageView.setImageURI(myFile)
            } else {
                Toast.makeText(this, "Image picker action canceled", Toast.LENGTH_SHORT).show()
            }
        }
        if(requestCode==2)
        {
            imageView.setImageURI(myFile)
            db.storeImage("Uri", myFile.toString())
        }

    }

    fun FullSizedPictureIntent(view: View) {

        val myIntent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val photoFile= File.createTempFile("JPEG_$timeStamp",".jpg",
            getExternalFilesDir(Environment.DIRECTORY_PICTURES))
        val photoUri= FileProvider.getUriForFile(this,"com.example.instant.fileprovider",photoFile)
        myFile=Uri.fromFile(photoFile)
        myIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        startActivityForResult(myIntent,2)
    }

    /*var myFile:Uri?= null
    val db = DB()


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
        if(requestCode == 0){
            if (resultCode === Activity.RESULT_OK) {
                try {

                    val imageUri = data!!.data
                    val imageStream: InputStream? = contentResolver.openInputStream(imageUri!!)
                    val selectedImage = BitmapFactory.decodeStream(imageStream)
                    imageView.setImageBitmap(selectedImage)
                    db.storeImage("imageUri", "${imageUri}")

                    

                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                   // Toast.makeText(this@PostImage, "Something went wrong", Toast.LENGTH_LONG).show()
                }
            } else {
               // Toast.makeText(this@PostImage, "You haven't picked Image", Toast.LENGTH_LONG).show()
            }
        }
        if (requestCode == 1) {
            //imageView.setImageURI(Uri.fromFile(myFile))

            val imageBitmap = data?.extras?.get("data") as Bitmap

            imageView.setImageBitmap(imageBitmap)
            db.storeImage("bitmap", "${imageBitmap}") // this works to the bit map of the image
        }
        if(requestCode==2)
        {
            imageView.setImageURI(myFile)
            db.storeImage("imageURI", "${myFile}")
        }
    }

    // was trying to get the path from the uri using the path below but for some reason it kept breaking so instead went with the bit map and that apparently astarted to work.

    fun FullSizedPictureIntent(view: View) {
        val myIntent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
       val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val photoFile= File.createTempFile("JPEG_$timeStamp",".jpg",
            getExternalFilesDir(Environment.DIRECTORY_PICTURES))
        val photoUri= FileProvider.getUriForFile(this,"com.example.android.fileprovider",photoFile)
        myFile=Uri.fromFile(photoFile)
        myIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        startActivityForResult(myIntent,2)


    }*/



    fun goGallery() {
            val imagePickerIntent = Intent(Intent.ACTION_GET_CONTENT)
            imagePickerIntent.type = "image/*"
            if (imagePickerIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(imagePickerIntent, PICK_PHOTO_CODE)
            }
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
