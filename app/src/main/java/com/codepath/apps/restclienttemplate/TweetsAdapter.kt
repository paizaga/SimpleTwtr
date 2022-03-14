package com.codepath.apps.restclienttemplate

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.apps.restclienttemplate.models.Tweet
import okhttp3.internal.notify

class TweetsAdapter (val tweets: ArrayList<Tweet>) : RecyclerView.Adapter<TweetsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetsAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.item_tweet,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TweetsAdapter.ViewHolder, position: Int) {
        //populates date into the item through the holder
        val tweet: Tweet = tweets.get(position)

        holder.tvUserName.text = tweet.user?.name

        holder.tvTweetBody.text = tweet.body

        Glide.with(holder.itemView).load(tweet.user?.publicImageUrl).into(holder.ivProfileImage)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }

    fun clear(){
        tweets.clear()
        notifyDataSetChanged()
    }

    fun addAll(tweetList: List <Tweet>){
        tweets.addAll(tweetList)
        notifyDataSetChanged()
    }





    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivProfileImage = itemView.findViewById<ImageView>(R.id.ivProfileImage)
        val tvUserName = itemView.findViewById<TextView>(R.id.tvUsername)
        val tvTweetBody = itemView.findViewById<TextView>(R.id.tvTweetBody)



    }
}