package com.example.user.kotsalesapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cat.*

class CatAct : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat)

        var url="http://pioneersacademyproject.com/get_cat.php"
        var list=ArrayList<String>()

        var rq=Volley.newRequestQueue(this)
        var jar=JsonArrayRequest(Request.Method.GET,url,null,
                Response.Listener { response ->

                    for(x in 0..response.length()-1)
                        list.add(response.getJSONObject(x).getString("category"))

                    var adp=ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
                    cat_lv.adapter=adp

                },Response.ErrorListener { error ->

        })

        rq.add(jar)

        cat_lv.setOnItemClickListener { adapterView, view, i, l ->

            var obj=Intent(this,ItemsAct::class.java)
            obj.putExtra("cat",list[i])
            startActivity(obj)
        }



    }
}
