package com.zenlaeth.tpsup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.zenlaeth.tpsup.R
import com.zenlaeth.tpsup.activity.HomeActivity
import com.zenlaeth.tpsup.adapter.ProjectAdapter
import com.zenlaeth.tpsup.api.FirebaseManager
import kotlinx.coroutines.launch

class HomeFragment(private val context: HomeActivity) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater?.inflate(R.layout.fragment_home, container, false)

        // charger api
//        val hRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        val vRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)

        vRecyclerView.apply {
            lifecycleScope.launch {
                val sets = FirebaseManager.getProjects()

                vRecyclerView.adapter = ProjectAdapter(context as HomeActivity, sets, R.layout.item_vertical)
            }
        }

        return view
    }
}