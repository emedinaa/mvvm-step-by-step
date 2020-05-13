package com.emedinaa.kotlinmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.emedinaa.kotlinmvvm.R
import com.emedinaa.kotlinmvvm.di.Injection
import com.emedinaa.kotlinmvvm.model.Museum
import com.emedinaa.kotlinmvvm.viewmodel.MuseumViewModel
import com.emedinaa.kotlinmvvm.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_museum.*

class MuseumActivity : AppCompatActivity() {

    private lateinit var viewModel: MuseumViewModel
    private lateinit var adapter: MuseumAdapter

    companion object {
        const val TAG= "CONSOLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum)

        setupViewModel()
        setupUI()

        viewModel.getMuseums().observe(this,renderMuseums)
    }

    //ui
    private fun setupUI(){
        adapter= MuseumAdapter( emptyList())
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter= adapter
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this,ViewModelFactory(Injection.providerRepository())).get(MuseumViewModel::class.java)
    }

    //observers
    private val renderMuseums= Observer<List<Museum>> {
        Log.v(TAG, "data updated $it")
        layoutError.visibility=View.GONE
        layoutEmpty.visibility=View.GONE
        adapter.update(it)
    }

}
