package com.example.instant

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instant.models.Posts

import kotlinx.android.synthetic.main.item_post.view.*



class PostsAdapter(val context: Context, val posts: ArrayList<Posts>) :
    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    val db = DB()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.bind(posts[position])
      //  holder.name.setText(posts.get(position).username)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var name: TextView
        lateinit var imageUri: ImageView
        lateinit var time: TextView

        fun bind(post: Posts) {
            name =  itemView.findViewById(R.id.tvUsername)
            //imageUri = itemView.findViewById(R.id.ivPost)
            time = itemView.findViewById(R.id.tvRelativeTime)

           itemView.tvUsername.text = db.retrieveCurrentUser().toString()   // this should be the username from the user, should call the method from db which gets the user name.
            itemView.ivPost.setImageURI(Uri.parse(post.imageurl))
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