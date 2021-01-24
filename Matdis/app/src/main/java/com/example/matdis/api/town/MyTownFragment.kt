package com.example.matdis.api.town

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.example.matdis.POD.PictureOfTheDayData
import com.example.matdis.R
import kotlinx.android.synthetic.main.fragment_town.*


class MyTownFragment: Fragment() {

    private val viewModel: MyTownViewModel by lazy {
        ViewModelProviders.of(this).get(MyTownViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_town, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getMyTownPic()
            .observe(this@MyTownFragment, Observer<PictureOfTheDayData> { renderData(it) })
    }

    private fun renderData(data: PictureOfTheDayData) {

        when (data) {
            is PictureOfTheDayData.Success -> {

                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url

                if (url.isNullOrEmpty()) {
                    Toast.makeText(context, "Неверный URL", Toast.LENGTH_SHORT).show()
                } else {

                    my_town_pic.load(url) {
                        lifecycle(this@MyTownFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_town)
                    }
                }
            }
            is PictureOfTheDayData.Loading -> {
            }
            is PictureOfTheDayData.Error -> {
                Toast.makeText(context, "Нет сети", Toast.LENGTH_SHORT).show()
                Log.d("!!!!!!!!!!!!!!!", data.toString())
            }
        }
    }
}