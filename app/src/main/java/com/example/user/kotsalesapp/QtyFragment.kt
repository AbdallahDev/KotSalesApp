package com.example.user.kotsalesapp


import android.os.Bundle
import android.app.DialogFragment
import android.content.Intent
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


class QtyFragment : DialogFragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var v= inflater!!.inflate(R.layout.fragment_qty, container, false)

        var et=v.findViewById<EditText>(R.id.qty_et)
        var btn=v.findViewById<Button>(R.id.qty_btn)

        btn.setOnClickListener {

            UserInfo.qty=et.text.toString().toInt()
            var i=Intent(activity,TotalAct::class.java)
            startActivity(i)

        }

        return v
    }

}// Required empty public constructor
