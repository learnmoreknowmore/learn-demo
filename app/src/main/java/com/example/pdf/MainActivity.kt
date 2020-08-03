package com.example.pdf

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.pdf.adapter.ByPageAdapter
import com.example.pdf.adapter.GroupPageAdapter
import com.example.pdf.datasource.ByItemDataRepository
import com.example.pdf.model.ByPageViewModel
import com.example.pdf.model.GroupViewModel
import com.tbruyelle.rxpermissions3.RxPermissions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    //private lateinit var mByPageViewModel: ByPageViewModel
    private lateinit var viewModel:GroupViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        mByPageViewModel = ViewModelProviders.of(this).get(ByPageViewModel::class.java)
//        val adapter = ByPageAdapter()
//        rv.adapter = adapter
//        mByPageViewModel.stories.observe(this, Observer(adapter::submitList))
        viewModel = ViewModelProviders.of(this).get(GroupViewModel::class.java)
        val mAdapter = GroupPageAdapter()
        rv.adapter = mAdapter
        btnLoad.setOnClickListener {
            lifecycleScope.launch {
                viewModel.getList().collectLatest {
                    mAdapter.submitData(it)
                }
            }
        }


        //
    }


}