package com.example.matdis.api.town

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import coil.api.load
import com.example.matdis.POD.PictureOfTheDayData
import com.example.matdis.R
import kotlinx.android.synthetic.main.fragment_town_second.constraint_container
import kotlinx.android.synthetic.main.fragment_town_second.my_town_pic


class MyTownFragment : Fragment() {

    private var isShown = false
    private val viewModel: MyTownViewModel by lazy {
        ViewModelProviders.of(this).get(MyTownViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_town_first, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getMyTownPic()
            .observe(this@MyTownFragment, Observer<PictureOfTheDayData> { renderData(it) })
        my_town_pic.setOnClickListener { if (isShown) makeSmaller() else makeLarger() }


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

    private fun makeLarger() {
        isShown = true

        val constraintSet = ConstraintSet()
        constraintSet.clone(context, R.layout.fragment_town_second)


        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(constraint_container, transition)
        constraintSet.applyTo(constraint_container)
    }

    private fun makeSmaller() {
        isShown = false

        val constraintSet = ConstraintSet()
        constraintSet.clone(context, R.layout.fragment_town_first)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(constraint_container, transition)
        constraintSet.applyTo(constraint_container)
    }

}


