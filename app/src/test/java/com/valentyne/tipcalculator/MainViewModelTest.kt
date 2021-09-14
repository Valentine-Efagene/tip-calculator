package com.valentyne.tipcalculator

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {
    // If you ever need the application context
    // ApplicationProvider.getApplicationContext()

    private val viewModel = MainViewModel()

    @Test
    fun changeTipAmount(){
        val testValue = 18.0
        viewModel.percentage = viewModel.percentageMap[Rating.GOOD]
        val delta = 0.05
        viewModel.computeTipAmount()

        Assert.assertEquals(viewModel.tipAmount.value!!, testValue, delta)
    }


}