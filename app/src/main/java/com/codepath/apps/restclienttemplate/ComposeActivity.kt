package com.codepath.apps.restclienttemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.bumptech.glide.load.model.Headers
import com.codepath.apps.restclienttemplate.models.Tweet
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler

class ComposeActivity : AppCompatActivity() {

    lateinit var editCompose:EditText
    lateinit var tweetBtn:Button

    lateinit var client: TwitterClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose)

        editCompose = findViewById(R.id.editTextTweetCompose)
        tweetBtn = findViewById(R.id.tweetButton)

        client = TwitterApplication.getRestClient(this)

        tweetBtn.setOnClickListener{
            //grab content of edittext

            val tweetContent = editCompose.text.toString()
            //make sure tweet isn't empty

            if (tweetContent.isEmpty()){
                Toast.makeText(this, "Empty tweets aren't allowed", Toast.LENGTH_SHORT).show()

            }
            //make sure character count is under character count

            if (tweetContent.length > 140){
                Toast.makeText(this, "Tweet is too long. Keep your message length less than or up to 140 characters", Toast.LENGTH_SHORT).show()
            }
            else
            {
                client.publishTweet(tweetContent, object:JsonHttpResponseHandler(){

                    override fun onFailure(
                        statusCode: Int,
                        headers: okhttp3.Headers?,
                        response: String?,
                        throwable: Throwable?
                    ) {
                        Log.e(TAG, "tweet publish failed", throwable)
                    }

                    override fun onSuccess(
                        statusCode: Int,
                        headers: okhttp3.Headers?,
                        json: JSON
                    ) {
                        Log.i(TAG, "tweet publish successful")
                        val tweet = Tweet.fromJson(json.jsonObject)
                        val intent = Intent()
                        intent.putExtra("tweet",tweet)
                        setResult(RESULT_OK, intent)
                        finish()



                    }
                })

            }
        //make api call to publish tweet
            //testing this works with a toast





        }
    }

    companion object{
        val TAG = "ComposeActivity"
    }
}