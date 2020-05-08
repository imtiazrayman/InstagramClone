package com.example.instant

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_camera.*

import java.io.FileNotFoundException
import java.io.InputStream


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
        if(requestCode == 0){
            if (resultCode === Activity.RESULT_OK) {
                try {
                    val imageUri = data!!.data
                    val imageStream: InputStream? = contentResolver.openInputStream(imageUri!!)
                    val selectedImage = BitmapFactory.decodeStream(imageStream)
                    imageView.setImageBitmap(selectedImage)
                    

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



    fun goGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)
        }
    }
    fun takePhoto() {
        val intent1 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent1.resolveActivity(packageManager) != null) {
            startActivityForResult(intent1, REQUEST_TAKE_PHOTO)
        }
    }
    companion object {
        private val REQUEST_TAKE_PHOTO = 4
        private val REQUEST_SELECT_IMAGE_IN_ALBUM = 0
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
