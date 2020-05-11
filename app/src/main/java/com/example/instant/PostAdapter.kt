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


/*class PostAdapter(val context: Context, val posts: ArrayList<Any>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>()
{
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val nameTextView = itemView.findViewById<TextView>(R.id.tvUsername)
        val time = itemView.findViewById<TextView>(R.id.tvRelativeTime)
       // val image = itemView.ivPost.setImageURI("d")
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.item_post, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }



    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val db = DB()
        val Post: Posts = posts[position] as Posts
        // Set item views based on your views and data model
        //val textView = viewHolder.nameTextView
        val textView = holder.nameTextView
       // textView.setText(db.retrieveCurrentUser())
    }
}*/


class PostAdapter(val context: Context, val posts: ArrayList<Any>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    val db = DB()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position] as Posts)
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

    }
}