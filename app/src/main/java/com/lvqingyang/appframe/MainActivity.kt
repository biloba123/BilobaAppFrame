package com.lvqingyang.appframe

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_msg.setOnClickListener {
            AlertDialog.Builder(this)
                    .setTitle("标题")
                    .setMessage("确定要发送？")
                    .setPositiveButton(android.R.string.ok, null)
                    .setNegativeButton(android.R.string.cancel, null)
                    .create()
                    .show()
        }

        btn_menu.setOnClickListener {
            AlertDialog.Builder(this)
                    .setTitle("标题")
                    .setItems(Array(4, {i -> "选项${i+1}" }), {dialog, which ->
                        Toast.makeText(this, "点击了$which", Toast.LENGTH_SHORT).show()
                    })
                    .create()
                    .show()
        }
    }
}
