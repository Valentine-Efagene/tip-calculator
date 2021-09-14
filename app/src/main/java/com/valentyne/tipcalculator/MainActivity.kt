package com.valentyne.tipcalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

@SuppressLint("UseSwitchCompatOrMaterialCode")
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var tipResult: TextView
    private lateinit var roundUp: Switch
    private lateinit var amazing: RadioButton
    private lateinit var good: RadioButton
    private lateinit var ok: RadioButton
    private lateinit var costOfService: EditText
    private lateinit var calculateTip: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModelFactory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        tipResult = findViewById(R.id.tip_result)
        roundUp = findViewById(R.id.round_up_switch)
        amazing = findViewById(R.id.option_twenty_percent)
        good = findViewById(R.id.option_eighteen_percent)
        ok = findViewById(R.id.option_fifteen_percent)
        costOfService = findViewById(R.id.cost_of_service)
        calculateTip = findViewById(R.id.calculate_button)

        amazing.setOnClickListener { onRadioButtonClicked(amazing) }
        good.setOnClickListener { onRadioButtonClicked(good) }
        ok.setOnClickListener { onRadioButtonClicked(ok) }

        calculateTip.setOnClickListener {
            viewModel.setCostOfService(costOfService.text.toString().toDouble())
            viewModel.computeTipAmount()
        }

        roundUp.setOnClickListener {
            viewModel.setRoundUp(!viewModel.roundUp)
        }

        val tipAmountObserver = Observer<Double> {
            value -> tipResult.text = value.toString()
        }

        viewModel.tipAmount.observe(this, tipAmountObserver)
    }

    private fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when(view.getId()) {
                R.id.option_twenty_percent ->
                    if (checked) {
                        viewModel.percentage = viewModel.percentageMap[Rating.AMAZING]
                    }
                R.id.option_eighteen_percent ->
                    if (checked) {
                        viewModel.percentage = viewModel.percentageMap[Rating.GOOD]
                    }
                R.id.option_fifteen_percent ->
                    if (checked) {
                        viewModel.percentage = viewModel.percentageMap[Rating.OK]
                    }
            }

            viewModel.computeTipAmount()
        }
    }
}