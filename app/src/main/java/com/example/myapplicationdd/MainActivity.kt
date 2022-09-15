package com.example.myapplicationdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    lateinit var recy: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val usersList: ArrayList<Mydata> = ArrayList()
        recy=findViewById<RecyclerView>(R.id.rvUsersList)
        try {
            // As we have JSON object, so we are getting the object
            //Here we are calling a Method which is returning the JSON object
            val obj = JSONObject(getJSONFromAssets()!!)
            // fetch JSONArray named users by using getJSONArray
            val usersArray = obj.getJSONArray("records")
            // Get the users data using for loop i.e. id, name, email and so on

            for (i in 0 until usersArray.length()) {
                // Create a JSONObject for fetching single User's Data
                val user = usersArray.getJSONObject(i)
                // Fetch id store it in variable
                val id = user.getInt("School_Id")
                val name = user.getString("Org_Name")



                val mobile = user.getString("Telephone")
                val email = user.getString("Email")


                // Now add all the variables to the data model class and the data model class to the array list.
                val userDetails =
                    Mydata(id, name,  mobile,email)

                // add the details in the list
                usersList.add(userDetails)
            }
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }

        // Set the LayoutManager that this RecyclerView will use.
        recy.layoutManager = LinearLayoutManager(this)
        // Adapter class is initialized and list is passed in the param.
        val itemAdapter = Recycler_View( usersList)
        // adapter instance is set to the recyclerview to inflate the items.
        recy.adapter = itemAdapter

    }


    /**
     * Method to load the JSON from the Assets file and return the object
     */
    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = assets.open("User.json")
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}