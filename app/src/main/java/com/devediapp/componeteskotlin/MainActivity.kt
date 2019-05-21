package com.devediapp.componeteskotlin

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    private fun setListeners(){
        buttonToastMe.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        if(id == R.id.buttonToastMe){
            val toast = Toast.makeText(applicationContext, "Toast notification! Tempo de visualização = " + Toast.LENGTH_LONG.toString(), Toast.LENGTH_LONG)

            //layout padrão alterado
            //toast.view.findViewById<TextView>(android.R.id.message).setTextColor(Color.GREEN)

            //Layout de perzonalizado do toast
            //val inflater : LayoutInflater = layoutInflater
            val toastLayout = layoutInflater.inflate(R.layout.toast_custom, null)
            toast.view = toastLayout

            val textView = toast.view.findViewById<TextView>(R.id.textMessageToast)
            textView.setTextColor(Color.BLUE)
            textView.text = "Toast notification!"

            toast.show()
        }
    }
}
