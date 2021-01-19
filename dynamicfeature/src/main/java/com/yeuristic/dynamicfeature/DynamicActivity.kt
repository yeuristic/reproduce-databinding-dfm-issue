package com.yeuristic.dynamicfeature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.yeuristic.dynamicfeature.databinding.ActivityDynamicBinding

class DynamicActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var activityDynamicBinding: ActivityDynamicBinding
    lateinit var viewModel: DynamicActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDynamicBinding = DataBindingUtil.setContentView(this, R.layout.activity_dynamic)
        viewModel = DynamicActivityViewModel()
        activityDynamicBinding.viewModel = viewModel

        initListener()
    }

    private fun initListener() {
        activityDynamicBinding.buttonLoginLogout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            activityDynamicBinding.buttonLoginLogout -> viewModel.isLogin = viewModel.isLogin.not()
        }
    }
}