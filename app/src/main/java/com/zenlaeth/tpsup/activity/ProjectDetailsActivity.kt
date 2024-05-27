package com.zenlaeth.tpsup.activity

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.zenlaeth.tpsup.adapter.ProjectAdapter
import com.zenlaeth.tpsup.api.FirebaseManager.Companion.getProject
import com.zenlaeth.tpsup.databinding.SetDetailsBinding
import kotlinx.coroutines.launch

class ProjectDetailsActivity: AppCompatActivity(){
    private lateinit var binding: SetDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SetDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val setId = intent.getStringExtra(ProjectAdapter.ViewHolder.setIdKey)

        lifecycleScope.launch {
            val set = getProject(setId!!)
            // utiliser glide pour recuperer l'image à partir de son lien -> composant
            if(set.image != null) {
                Glide.with(this@ProjectDetailsActivity).load(Uri.parse(set.image))
                    .into(binding.detailImage)
                Picasso.get().load(set.image).into(binding.detailImage)
            }
            binding.detailTitle.text = set.title
            binding.detailDescription.text = set.description.replace("\\n", "\n");
            binding.detailLocation.text = set.location
            binding.detailPrice.text = set.price.toString() + " €"
        }
    }
}