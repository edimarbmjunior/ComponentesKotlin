package com.devediapp.componeteskotlin

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
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
        buttonSnackMe.setOnClickListener(this)
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
        }else if(id == R.id.buttonSnackMe){
            //Elemento relativamente novo, por isso não está na biobliteca padrão do android
            //sendo que tem que adicionar a biblioteca que contem snack
            val snackbar = Snackbar.make(constraintLayout, "Snack bar notification!", Snackbar.LENGTH_LONG)

            snackbar.view.findViewById<TextView>(android.support.design.R.id.snackbar_text).setTextColor(Color.RED)
            snackbar.view.setBackgroundColor(Color.YELLOW)

            //Recuperar a cor de arquivo(colors.xml) de resources criado como suporte para armazenamento de cores em um unico lugar
            //Código abaixo está devasado, será retirado de uso a partir da API 27
            //resources.getColor(R.color.colorAccent)
            //Novo código suportado
            ContextCompat.getColor(this, R.color.colorAccent)

            //Código para defazer uma ação
            snackbar.setAction("Desfazer a ação que fez?", {
                Snackbar.make(constraintLayout, "Ação desfeita!", Snackbar.LENGTH_LONG).show()
            })

            snackbar.setActionTextColor(Color.MAGENTA)

            snackbar.show()
        }
    }
}
