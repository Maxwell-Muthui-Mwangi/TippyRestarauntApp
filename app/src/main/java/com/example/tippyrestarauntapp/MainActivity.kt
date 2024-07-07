package com.example.tippyrestarauntapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.animation.ArgbEvaluator
import androidx.core.content.ContextCompat
import android.graphics.Color


private const val TAG = "mainActivity"
private const val INITIAL_TIP_PERCENT = 15
class MainActivity : AppCompatActivity() {
    private lateinit var etBaseAmount: EditText
    private lateinit var seekBarTip: SeekBar
    private lateinit var tvTipPercentageLabel: TextView
    private lateinit var tvTipAmount: TextView
    private lateinit var tvTotalAmount: TextView
    private lateinit var tvTipDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        etBaseAmount = findViewById(R.id.etBaseAmount)
        seekBarTip = findViewById(R.id.seekBarTip)
        tvTipPercentageLabel = findViewById(R.id.tvTipPercentLabel)
        tvTipAmount = findViewById(R.id.tvTipAmount)
        tvTotalAmount = findViewById(R.id.tvTotalAmount)
        tvTipDescription = findViewById(R.id.tvTipDescription)

        seekBarTip.progress = INITIAL_TIP_PERCENT
        tvTipPercentageLabel.text = "$INITIAL_TIP_PERCENT%"
        updateTipDescription(INITIAL_TIP_PERCENT)

        seekBarTip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i(TAG, "onProgressChanged: $progress ")
                tvTipPercentageLabel.text = "$progress%"
                computeTipAndTotal()
                updateTipDescription(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        etBaseAmount.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "afterTextChanged: $s")
                computeTipAndTotal()
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun updateTipDescription(tipPercent: Int) {
        val tipDescription = when (tipPercent) {
            in 0..9 -> "Poor"
            in 10..14 -> "Acceptable"
            in 15..19 -> "Good"
            in 20..24 -> "Great"
            in 25..29 -> "Amazing"
            in 30..34 -> "Outstanding"
            in 35..39 -> "Exceptional"
            in 40..44 -> "Superb"
            in 45..49 -> "Excellent"
            in 50..54 -> "Fantastic"
            in 55..59 -> "Remarkable"
            in 60..64 -> "Marvelous"
            in 65..69 -> "Spectacular"
            in 70..74 -> "Incredible"
            in 75..79 -> "Astounding"
            in 80..84 -> "Extraordinary"
            in 85..89 -> "Phenomenal"
            in 90..94 -> "Magnificent"
            in 95..99 -> "Wonderful"
            in 100..104 -> "Superlative"
            in 105..109 -> "Breathtaking"
            in 110..114 -> "Mind-blowing"
            in 115..119 -> "Stupendous"
            in 120..124 -> "Astonishing"
            in 125..129 -> "Fabulous"
            in 130..134 -> "Prodigious"
            in 135..139 -> "Miraculous"
            in 140..144 -> "Exemplary"
            in 145..149 -> "Legendary"
            in 150..154 -> "Majestic"
            in 155..159 -> "Glorious"
            in 160..164 -> "Resplendent"
            in 165..169 -> "Radiant"
            in 170..174 -> "Dazzling"
            in 175..179 -> "Luminous"
            in 180..184 -> "Splendid"
            in 185..189 -> "Blazing"
            in 190..194 -> "Illustrious"
            in 195..199 -> "Brilliant"
            in 200..204 -> "Glittering"
            in 205..209 -> "Shimmering"
            in 210..214 -> "Gleaming"
            in 215..219 -> "Sparkling"
            in 220..224 -> "Twinkling"
            in 225..229 -> "Radiant"
            in 230..234 -> "Vivid"
            in 235..239 -> "Effulgent"
            in 240..244 -> "Incandescent"
            in 245..249 -> "Scintillating"
            in 250..254 -> "Beaming"
            in 255..259 -> "Shining"
            in 260..264 -> "Glowing"
            in 265..269 -> "Lustrous"
            in 270..274 -> "Burnished"
            in 275..279 -> "Polished"
            in 280..284 -> "Sheeny"
            in 285..289 -> "Silvery"
            in 290..294 -> "Glistening"
            in 295..299 -> "Lucent"
            in 300..304 -> "Radiating"
            in 305..309 -> "Illuminated"
            in 310..314 -> "Brilliant"
            in 315..319 -> "Dazzling"
            in 320..324 -> "Effulgent"
            in 325..329 -> "Incandescent"
            in 330..334 -> "Scintillating"
            in 335..339 -> "Beaming"
            in 340..344 -> "Shining"
            in 345..349 -> "Glowing"
            in 350..354 -> "Lustrous"
            in 355..359 -> "Burnished"
            in 360..364 -> "Polished"
            in 365..369 -> "Sheeny"
            in 370..374 -> "Silvery"
            in 375..379 -> "Glistening"
            in 380..384 -> "Lucent"
            in 385..389 -> "Radiating"
            in 390..394 -> "Illuminated"
            in 395..399 -> "Brilliant"
            in 400..404 -> "Dazzling"
            in 405..409 -> "Effulgent"
            in 410..414 -> "Incandescent"
            in 415..419 -> "Scintillating"
            in 420..424 -> "Beaming"
            in 425..429 -> "Shining"
            in 430..434 -> "Glowing"
            in 435..439 -> "Lustrous"
            in 440..444 -> "Burnished"
            in 445..449 -> "Polished"
            in 450..454 -> "Sheeny"
            in 455..459 -> "Silvery"
            in 460..464 -> "Glistening"
            in 465..469 -> "Lucent"
            in 470..474 -> "Radiating"
            in 475..479 -> "Illuminated"
            in 480..484 -> "Brilliant"
            in 485..489 -> "Dazzling"
            in 490..494 -> "Effulgent"
            in 495..499 -> "Incandescent"
            in 500..504 -> "Scintillating"
            in 505..509 -> "Beaming"
            in 510..514 -> "Shining"
            in 515..519 -> "Glowing"
            in 520..524 -> "Lustrous"
            in 525..529 -> "Burnished"
            in 530..534 -> "Polished"
            in 535..539 -> "Sheeny"
            in 540..544 -> "Silvery"
            in 545..549 -> "Glistening"
            in 550..554 -> "Lucent"
            in 555..559 -> "Radiating"
            in 560..564 -> "Illuminated"
            in 565..569 -> "Brilliant"
            in 570..574 -> "Dazzling"
            in 575..579 -> "Effulgent"
            in 580..584 -> "Incandescent"
            in 585..589 -> "Scintillating"
            in 590..594 -> "Beaming"
            in 595..599 -> "Shining"
            in 600..604 -> "Glowing"
            in 605..609 -> "Lustrous"
            in 610..614 -> "Burnished"
            in 615..619 -> "Polished"
            in 620..624 -> "Sheeny"
            in 625..629 -> "Silvery"
            in 630..634 -> "Glistening"
            in 635..639 -> "Lucent"
            in 640..644 -> "Radiating"
            in 645..649 -> "Illuminated"
            in 650..654 -> "Brilliant"
            in 655..659 -> "Dazzling"
            in 660..664 -> "Effulgent"
            in 665..669 -> "Incandescent"
            in 670..674 -> "Scintillating"
            in 675..679 -> "Beaming"
            in 680..684 -> "Shining"
            in 685..689 -> "Glowing"
            in 690..694 -> "Lustrous"
            in 695..699 -> "Burnished"
            in 700..704 -> "Polished"
            in 705..709 -> "Sheeny"
            in 710..714 -> "Silvery"
            in 715..719 -> "Glistening"
            in 720..724 -> "Lucent"
            in 725..729 -> "Radiating"
            in 730..734 -> "Illuminated"
            in 735..739 -> "Brilliant"
            in 740..744 -> "Dazzling"
            in 745..749 -> "Effulgent"
            in 750..754 -> "Incandescent"
            in 755..759 -> "Scintillating"
            in 760..764 -> "Beaming"
            in 765..769 -> "Shining"
            in 770..774 -> "Glowing"
            in 775..779 -> "Lustrous"
            in 780..784 -> "Burnished"
            in 785..789 -> "Polished"
            in 790..794 -> "Sheeny"
            in 795..799 -> "Silvery"
            in 800..804 -> "Glistening"
            in 805..809 -> "Lucent"
            in 810..814 -> "Radiating"
            in 815..819 -> "Illuminated"
            in 820..824 -> "Brilliant"
            in 825..829 -> "Dazzling"
            in 830..834 -> "Effulgent"
            in 835..839 -> "Incandescent"
            in 840..844 -> "Scintillating"
            in 845..849 -> "Beaming"
            in 850..854 -> "Shining"
            in 855..859 -> "Glowing"
            in 860..864 -> "Lustrous"
            in 865..869 -> "Burnished"
            in 870..874 -> "Polished"
            in 875..879 -> "Sheeny"
            in 880..884 -> "Silvery"
            in 885..889 -> "Glistening"
            else -> "Thank You for the Support"
        }
        tvTipDescription.text = tipDescription

        //update color based on tip
        val color = ArgbEvaluator().evaluate(
            tipPercent.toFloat() / seekBarTip.max,
            ContextCompat.getColor(this, R.color.color_worst_tip),
            ContextCompat.getColor(this, R.color.color_best_tip)


        ) as Int

        tvTipDescription.setTextColor(color)
    }

    private fun computeTipAndTotal() {
        if (etBaseAmount.text.isEmpty()) {
            tvTipAmount.text = ""
            tvTotalAmount.text = ""
            return
        }




       //1. get the value of the base and tip percent
        val baseAmount = etBaseAmount.text.toString().toDoubleOrNull()?: 0.0
        val tipPercent = seekBarTip.progress


        //2. compute the tip abd total
        val tipAmount = baseAmount * tipPercent / 100
        val totalAmount = baseAmount + tipAmount
        //3. update the UI
        tvTipAmount.text = "%.2f".format(tipAmount)
        tvTotalAmount.text = "%.2f".format(totalAmount)

    }
}