package com.example.webserviceques1

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL =" https://jsonplaceholder.typicode.com"

class MainActivity :AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.edittext_id) as EditText

        findViewById<View>(R.id.getDetailsBtn).setOnClickListener {
            val getIdToFetch = editText.text
            Log.i(TAG,"$getIdToFetch")

            if (getIdToFetch.isNullOrEmpty() || getIdToFetch.toString().toInt() > 10 || getIdToFetch.toString().toInt() < 1
            ) {
                editText.error = "Enter digit between 1 to 10"
            } else {
                getMyData(getIdToFetch.toString().toInt())
            }


        }
    }

    private fun getMyData(id: Int) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl( BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData=retrofitBuilder.getAId(id)
        retrofitData.enqueue(object : Callback<List<DataModelItem>?> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<List<DataModelItem>?>,
                response: Response<List<DataModelItem>?>
            ) {
                val responseBody = response.body()!!

                for (myData in responseBody) {
                    findViewById<TextView>(R.id.textview_id).text = "Post Id: ${myData.id}"
                    findViewById<TextView>(R.id.textview_title).text = "Title: ${myData.title}"
                    findViewById<TextView>(R.id.textview_body).text = "Body: ${myData.body}"

                }
            }
            override fun onFailure(call: Call<List<DataModelItem>?>, t: Throwable) {

                Log.d("MainActivity" , "onFailure:" +t.message)
            }
        })


    }
}