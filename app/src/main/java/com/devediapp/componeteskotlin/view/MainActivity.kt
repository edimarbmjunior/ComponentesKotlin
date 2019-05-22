package com.devediapp.componeteskotlin.view

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.*
import com.devediapp.componeteskotlin.R
import com.devediapp.componeteskotlin.util.MockData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
        loadSpinner()
    }

    private fun setListeners(){
        buttonToastMe.setOnClickListener(this)
        buttonSnackMe.setOnClickListener(this)
        buttonGetSpinner.setOnClickListener(this)
        buttonSetSpinner.setOnClickListener(this)
        buttonProgress.setOnClickListener(this)
        buttonGetSeek.setOnClickListener(this)
        buttonSetSeek.setOnClickListener(this)
        buttonList.setOnClickListener(this)
        buttonDate.setOnClickListener(this)

        spinnerDynamic.onItemSelectedListener = this

        seekValue.setOnSeekBarChangeListener(this)
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
        }else if(id == R.id.buttonGetSpinner){
            //val value = spinnerDynamic.selectedItem.toString()
            val value = spinnerDynamic.selectedItemPosition.toString()
            Toast.makeText(applicationContext, "Button GetSpinner! Posicao="+value, Snackbar.LENGTH_LONG).show()
        }else if(id == R.id.buttonSetSpinner){
            spinnerDynamic.setSelection(1)
            Toast.makeText(applicationContext, "Button SetSpinner!"+spinnerDynamic.setSelection(3).toString(), Snackbar.LENGTH_LONG).show()
        }else if(id == R.id.buttonProgress){

            val progress = ProgressDialog(this)
            progress.setTitle("Titulo")
            progress.setMessage("Mensagem")
            progress.show()

            //progress.hide()
            //progress.dismiss()

        }else if(id == R.id.buttonGetSeek){
            val progress = seekValue.progress.toString()
            Toast.makeText(applicationContext, "SeekBar! Posicao="+progress, Snackbar.LENGTH_LONG).show()
        }else if(id == R.id.buttonSetSeek){
            seekValue.progress = 10
        }else if(id == R.id.buttonList){
            startNewActivity(Intent(this, ListViewActivity::class.java))
        }else if(id == R.id.buttonDate){
            startNewActivity(Intent(this, DateActivity::class.java))
        }
    }

    private fun loadSpinner(){
        val list = MockData.getListCompanionObject()

        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, list)

        spinnerDynamic.adapter = adapter
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val id = view.id
        if(id == R.id.spinnerDynamic){
            val value : String = parent.getItemAtPosition(position).toString()
            Toast.makeText(applicationContext, "Valor selecionado="+value, Toast.LENGTH_LONG).show()
        }
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        val id = seekBar.id
        if(id == R.id.seekValue){
            textSeekValue.text = progress.toString()
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {

    }

    private fun startNewActivity(intent: Intent) {
        startActivity(intent)
    }
}
