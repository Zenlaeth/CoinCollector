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
import com.zenlaeth.tpsup.activity.ArmorActivity
import com.zenlaeth.tpsup.activity.HomeActivity
import com.zenlaeth.tpsup.adapter.ListArmorsAdapter

class ArmorFragment(private val context: HomeActivity) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater?.inflate(R.layout.fragment_armor, container, false)

        // recuperer recycler view
        val armorsRecyclerView = view.findViewById<RecyclerView>(R.id.armorsRV)

        armorsRecyclerView.adapter = ListArmorsAdapter(object : ListArmorsAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                if (position == 0) {
                    requireActivity().run{
                        val intent = Intent(this, ArmorActivity::class.java)
                        intent.putExtra("Type", "head")

                        startActivity(intent)
                    }
                }
                if (position == 1) {
                    requireActivity().run{
                        val intent = Intent(this, ArmorActivity::class.java)
                        intent.putExtra("Type", "chest")

                        startActivity(intent)
                    }
                }
                if (position == 2) {
                    requireActivity().run{
                        val intent = Intent(this, ArmorActivity::class.java)
                        intent.putExtra("Type", "gloves")

                        startActivity(intent)
                    }
                }
                if (position == 3) {
                    requireActivity().run{
                        val intent = Intent(this, ArmorActivity::class.java)
                        intent.putExtra("Type", "waist")

                        startActivity(intent)
                    }
                }
                if (position == 4) {
                    requireActivity().run{
                        val intent = Intent(this, ArmorActivity::class.java)
                        intent.putExtra("Type", "legs")

                        startActivity(intent)
                    }
                }
            }
        })
        armorsRecyclerView.layoutManager = LinearLayoutManager(context)

//        var armorB = view.findViewById<Button>(R.id.btnArmorHead)
//        armorB.setOnClickListener {
//            // Handler code here.
//            val intent = Intent(this, ArmorHeadActivity::class.java)
//            this.startActivity(intent);
//        }


//        // charger api
//        val vRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
//        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
//        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
//        val call = serviceGenerator.getArmors()
//
//        call.enqueue(object : Callback<MutableList<ArmorBean>> {
//            override fun onResponse(call: Call<MutableList<ArmorBean>>, response: Response<MutableList<ArmorBean>>) {
//                if(response.isSuccessful){
//                    vRecyclerView.apply {
//                        progressBar.isVisible = true
//                        val body = response.body()?.filter {it.type == "head"}
//
//                        vRecyclerView.adapter = ArmorAdapter(context as HomeActivity, body!!, R.layout.item_vertical)
//                        progressBar.isVisible = false
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<MutableList<ArmorBean>>, t: Throwable) {
//                t.printStackTrace()
//                Log.e("error", t.message.toString())
//            }
//
//        })

        return view
    }
}