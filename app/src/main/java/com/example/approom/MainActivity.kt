package com.example.approom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.approom.adapter.NotasAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var app:NotaApp?=null
    var recyclerNotas:RecyclerView?=null
    var notas:ArrayList<Nota>?=null
    var adapter:NotasAdapter?=null

    var fadAdd: FloatingActionButton?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = applicationContext as NotaApp

        fadAdd = findViewById(R.id.fatAddNota)
        recyclerNotas = findViewById(R.id.recyclerNotas)

        notas = ArrayList<Nota>()

        lifecycleScope.launch {
            val notasDb = app!!.baseDeDatos.notaDao().getAll()
            notas!!.addAll(notasDb)
            adapter!!.notifyDataSetChanged()
        }

        adapter = NotasAdapter(notas!!,this)

        fadAdd!!.setOnClickListener {
            val i = Intent(this, AppNotaActivity::class.java)
            startActivity(i)
        }

        recyclerNotas!!.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        recyclerNotas!!.setHasFixedSize(true)
        recyclerNotas!!.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val notasDb = app!!.baseDeDatos.notaDao().getAll()
            notas!!.addAll(notasDb)
            adapter!!.notifyDataSetChanged()
        }
    }
}