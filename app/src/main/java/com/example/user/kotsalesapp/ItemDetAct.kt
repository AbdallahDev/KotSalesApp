package com.example.user.kotsalesapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_det.*

class ItemDetAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_det)

        var url="http://pioneersacademyproject.com/find_item.php?id="+
                intent.getStringExtra("itemid")

        var rq=Volley.newRequestQueue(this)
        var jor=JsonObjectRequest(Request.Method.GET,url,null,
                Response.Listener { response ->
                    item_name.text=response.getString("name")
                    item_price.text=response.getString("price")

                    UserInfo.price=item_price.text.toString().toDouble()

                    var photo="http://pioneersacademyproject.com/images/" + response.getString("photo")
                    photo=photo.replace(" ","%20")

                    Picasso.with(this).load(photo).into(item_photo)
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()  })

        rq.add(jor)

        item_btn.setOnClickListener {

            var obj=QtyFragment()
            obj.show(fragmentManager,"Qty")

        }


    }
}
