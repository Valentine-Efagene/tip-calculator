package com.valentyne.tipcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.round

class MainViewModel : ViewModel() {
    val percentageMap = mapOf(Rating.AMAZING to 0.2, Rating.GOOD to 0.18, Rating.OK to 0.15)
    var percentage = percentageMap[Rating.AMAZING]

    private var costOfService = 0.0

    private val _tipAmount = MutableLiveData(0.0)
    val tipAmount: LiveData<Double>
        get() = _tipAmount

    private var _roundUp: Boolean = false
    val roundUp: Boolean
        get() = _roundUp

    fun setRoundUp(value: Boolean) {
        _roundUp = value
        computeTipAmount()
    }

    fun computeTipAmount() {
        if (roundUp) {
            _tipAmount.value = round(costOfService * percentage!!)
        } else {
            _tipAmount.value = costOfService * percentage!!
        }
    }

    fun setCostOfService(value: Double) {
        costOfService = value
    }
}

enum class Rating {
    AMAZING,
    GOOD,
    OK
}