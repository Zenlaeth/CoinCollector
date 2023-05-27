package com.zenlaeth.tpsup.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zenlaeth.tpsup.R
import com.zenlaeth.tpsup.activity.HomeActivity
import com.zenlaeth.tpsup.activity.WeaponActivity
import com.zenlaeth.tpsup.adapter.ListWeaponAdapter

class WeaponFragment(private val context: HomeActivity) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater?.inflate(R.layout.fragment_weapon, container, false)

        // recuperer recycler view
        val weaponsRecyclerView = view.findViewById<RecyclerView>(R.id.weaponsRV)

        weaponsRecyclerView.adapter = ListWeaponAdapter(object : ListWeaponAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                if (position == 0) runActivity("great-sword")
                if (position == 1) runActivity("long-sword")
                if (position == 2) runActivity("sword-and-shield")
                if (position == 3) runActivity("dual-blades")
                if (position == 4) runActivity("hammer")
                if (position == 5) runActivity("hunting-horn")
                if (position == 6) runActivity("lance")
                if (position == 7) runActivity("gunlance")
                if (position == 8) runActivity("switch-axe")
                if (position == 9) runActivity("charge-blade")
                if (position == 10) runActivity("insect-glaive")
                if (position == 11) runActivity("light-bowgun")
                if (position == 12) runActivity("heavy-bowgun")
                if (position == 13) runActivity("bow")

            }
        })
        weaponsRecyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    fun runActivity(typeName: String) {
        requireActivity().run{
            val intent = Intent(this, WeaponActivity::class.java)
            intent.putExtra("Type", typeName)

            startActivity(intent)
        }
    }
}