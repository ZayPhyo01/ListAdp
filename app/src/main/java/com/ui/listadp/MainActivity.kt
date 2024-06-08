package com.ui.listadp

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val adapter = SampleAdapter({

    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rvList = findViewById<RecyclerView>(R.id.rvList)

        rvList.adapter = adapter
        var list = buildList<SampleModel> {
            repeat(100){
                add(SampleModel("ID_$it", "Name_$it"))
            }
        }
        adapter.submitList(list)

        val removeBtn = findViewById<Button>(R.id.btnRemoveFirsgt)
        removeBtn.setOnClickListener {
            val mutableList = list.toMutableList()
            mutableList.removeFirst()
            list = mutableList
            adapter.submitList(list)
        }


    }
}