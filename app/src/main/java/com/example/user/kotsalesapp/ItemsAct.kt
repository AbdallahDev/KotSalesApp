package com.example.user.kotsalesapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cat.*
import kotlinx.android.synthetic.main.activity_items.*

class ItemsAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        var url="http://pioneersacademyproject.com/get_items.php?category="+
                    intent.getStringExtra("cat")

        var list=ArrayList<String>()
        var list_id=ArrayList<String>()

        var rq= Volley.newRequestQueue(this)
        var jar= JsonArrayRequest(Request.Method.GET,url,null,
                Response.Listener { response ->

                    for(x in 0..response.length()-1){
                        list.add(response.getJSONObject(x).getString("name"))
                        list_id.add(response.getJSONObject(x).getString("id"))}

                    var adp= ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
                    items_lv.adapter=adp

                }, Response.ErrorListener { error ->

        })

        rq.add(jar)

        items_lv.setOnItemClickListener { adapterView, view, i, l ->

            var obj= Intent(this,ItemDetAct::class.java)
            obj.putExtra("itemid",list_id[i])
            startActivity(obj)
        }

    }
}
