package com.example.kotlin.ui.splash

import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.ui.MainActivity
import com.example.kotlin.ui.base.BaseActivity
import com.example.kotlin.R
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

private const val START_DELAY = 1000L

class SplashActivity : BaseActivity<Boolean?, SplashViewState>() {

    override val viewModel: SplashViewModel by viewModel()

    override val layoutRes: Int = R.layout.activity_splash

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({ viewModel.requestUser() }, START_DELAY)
    }

    override fun renderData(data: Boolean?) {
        data?.takeIf{ it }?.let {
            startMainActivity()
        }
    }


    private fun startMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
        finish()
    }
}