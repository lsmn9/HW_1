package com.geekbrains.tests

import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.ViewDetailsContract
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailsPresenterTest {

    private lateinit var presenter: DetailsPresenter

    @Mock
    private lateinit var viewContract: ViewDetailsContract

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)

        presenter = DetailsPresenter(viewContract)
    }


    @Test
    fun detaisOnIncrement_DefaultConter_Test() {

        presenter.onIncrement()
        presenter.onDecrement()
        presenter.onIncrement()

        Mockito.verify(viewContract, Mockito.times(2)).setCount(1)
        Mockito.verify(viewContract, Mockito.times(1)).setCount(0)
    }

    @Test
    fun detaisOnIncrement_NonDefaultConter_Test() {
        val count = 5

        presenter.setCounter(count)
        presenter.onIncrement()
        presenter.onDecrement()
        presenter.onIncrement()

        Mockito.verify(viewContract, Mockito.times(2)).setCount(1+count)
        Mockito.verify(viewContract, Mockito.times(1)).setCount(0+count)
    }

}