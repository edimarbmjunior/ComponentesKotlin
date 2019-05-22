package com.devediapp.componeteskotlin.view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.devediapp.componeteskotlin.R
import kotlinx.android.synthetic.main.activity_list_view.*
import java.util.*

class ListViewActivity : AppCompatActivity(), View.OnClickListener {

    private val mItens = arrayOf("São Gonçalo", "Niterói", "Itaborái")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        popularListView()
        setListeners()
    }

    private fun popularListView() {
        for (item in mItens) {
            println("Item: >${item}")
        }
        listView.adapter = NotesAdapter(this, mItens)
    }

    private fun voltar() {
        finish()
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonVoltar) {
            voltar()
        }
    }

    private fun setListeners() {
        buttonVoltar.setOnClickListener(this)
    }

    inner class NotesAdapter : BaseAdapter {

        private var mLista : Array<String>
        private var context: Context? = null

        constructor(context: Context, notesList: Array<String>) : super() {
            this.mLista = notesList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.listviewexample, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            vh.txtView1.text = mLista[position]

            return view
        }

        override fun getItem(position: Int): Any {
            return mLista[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return mLista.size
        }
    }

    private class ViewHolder(view: View?) {
        val txtView1: TextView

        init {
            this.txtView1 = view?.findViewById(R.id.txtView1) as TextView
        }
    }
}
