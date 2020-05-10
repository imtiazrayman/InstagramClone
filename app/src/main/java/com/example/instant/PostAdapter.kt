package com.example.instant

import android.content.Context
import android.net.Uri
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instant.models.Posts

import kotlinx.android.synthetic.main.item_post.view.*
import java.math.BigInteger
import java.security.MessageDigest

class PostsAdapter (val context: Context, val posts: List<Posts>) :
    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    val db = DB()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(posts[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(post: Posts) {
            itemView.tvUsername.text = db.retrieveCurrentUser().toString()   // this should be the username from the user, should call the method from db which gets the user name.
            itemView.ivPost.setImageURI(Uri.parse(post.imageurl))
            //itemView.tvDescription.text = post.description
            //Glide.with(context).load(post.imageurl).into(itemView.ivPost)
           // Glide.with(context).load(getProfileImageUrl(username)).into(itemView.ivProfileImage)
            itemView.tvRelativeTime.text = post.time
        }

        /*private fun getProfileImageUrl(username: String): String {
            //val digest = MessageDigest.getInstance("MD5")
            //val hash = digest.digest(username.toByteArray())
            //val bigInt = BigInteger(hash)
            //val hex = bigInt.abs().toString(16)

           // return "https://www.gravatar.com/avatar/$hex?d=identicon"
        }*/
    }
}