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


class MainActivity : AppCompatActivity(), View.OnClickListener, TimePickerDialog.OnTimeSetListener,
    DatePickerDialog.OnDateSetListener {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var name:String = "Entrer du texte..."
        binding.edtName.setText(name)

        binding.btnValider.setOnClickListener(this)

        binding.btnAnnuler.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        if(binding.btnValider === v){
            if(binding.checkLike.isChecked() && !binding.checkDislike.isChecked()){
                binding.edtName.setText(R.string.checkLike)
            }
            else if(binding.checkDislike.isChecked() && !binding.checkLike.isChecked()){
                binding.edtName.setText(R.string.checkDislike)
            } else {
                binding.edtName.setText("")
            }
            binding.iv.setImageResource(R.drawable.ic_baseline_flag_24)
        } else if(binding.btnAnnuler === v) {
            binding.edtName.setText("")
            binding.iv.setImageResource(R.drawable.ic_baseline_delete_forever_24)
        }
    }
    // Callback TimePicker
    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
//        Toast.makeText(this, "$hourOfDay:$minute", Toast.LENGTH_LONG).show()
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
//        Toast.makeText(this, "$hoursOfDay/$minute/", Toast.LENGTH_LONG).show()
    }
}