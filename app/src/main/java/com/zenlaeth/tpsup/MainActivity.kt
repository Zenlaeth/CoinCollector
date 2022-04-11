package com.zenlaeth.tpsup

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.zenlaeth.tpsup.databinding.ActivityMainBinding
import android.app.TimePickerDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import java.util.*


class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var monsterB = findViewById<Button>(R.id.btnMonster)
        monsterB.setOnClickListener {
            // Handler code here.
            val intent = Intent(this, MonsterActivity::class.java)
            this.startActivity(intent);
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, 28, 0, "Monster")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.getItemId() == 28) {
            val intent = Intent(this, MonsterActivity::class.java)
            this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item)
    }
}