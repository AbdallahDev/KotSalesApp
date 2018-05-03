package com.example.user.kotsalesapp

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_reg.*

class RegAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        reg_btn.setOnClickListener {

            var url="http://pioneersacademyproject.com/reg.php?mobile="+
                    reg_mobile.text.toString()+"&password="+
                    reg_password.text.toString()+"&name="+reg_name.text.toString()+
                    "&address="+reg_address.text.toString()

            var pd= ProgressDialog(this)
            pd.setMessage("Please Wait...")
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            pd.show()

            var rq= Volley.newRequestQueue(this)
            var sr= StringRequest(Request.Method.GET,url,
                    Response.Listener { response ->
                        pd.hide()
                        UserInfo.mobile=reg_mobile.text.toString()
                        var i= Intent(this,CatAct::class.java)
                        startActivity(i)
                    },
                    Response.ErrorListener { error ->
                        pd.hide()
                        Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
                    })

            rq.add(sr)

        }


    }
}
