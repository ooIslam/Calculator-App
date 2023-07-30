package com.example.calculator20

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.ac
import kotlinx.android.synthetic.main.activity_main.ac1
import kotlinx.android.synthetic.main.activity_main.delete
import kotlinx.android.synthetic.main.activity_main.devide
import kotlinx.android.synthetic.main.activity_main.eight
import kotlinx.android.synthetic.main.activity_main.equal
import kotlinx.android.synthetic.main.activity_main.five
import kotlinx.android.synthetic.main.activity_main.four
import kotlinx.android.synthetic.main.activity_main.minus
import kotlinx.android.synthetic.main.activity_main.multiply
import kotlinx.android.synthetic.main.activity_main.nine
import kotlinx.android.synthetic.main.activity_main.one
import kotlinx.android.synthetic.main.activity_main.plus
import kotlinx.android.synthetic.main.activity_main.plusminus
import kotlinx.android.synthetic.main.activity_main.point
import kotlinx.android.synthetic.main.activity_main.result
import kotlinx.android.synthetic.main.activity_main.seven
import kotlinx.android.synthetic.main.activity_main.six
import kotlinx.android.synthetic.main.activity_main.three
import kotlinx.android.synthetic.main.activity_main.two
import kotlinx.android.synthetic.main.activity_main.zero

class MainActivity : AppCompatActivity() {
    var GLOBAL_RESULT = 0.0
    var CURRENT_OPERATION = ""
    var SECOND_OPERAND = 0.0
    var OPERATION_CLICKED = false
    var TYPING = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = 0xF0F0F0.toInt()
        initButtons()
    }
    fun initButtons () {
        one.setOnClickListener {
          DigitClicked("1")
        }
        two.setOnClickListener {
            DigitClicked("2")
        }
        three.setOnClickListener {
            DigitClicked("3")
        }
        four.setOnClickListener {
            DigitClicked("4")
        }
        five.setOnClickListener {
            DigitClicked("5")
        }
        six.setOnClickListener {
            DigitClicked("6")
        }
        seven.setOnClickListener {
            DigitClicked("7")
        }
        eight.setOnClickListener {
            DigitClicked("8")
        }
        nine.setOnClickListener {
            DigitClicked("9")
        }
        zero.setOnClickListener {
            DigitClicked("0")
        }
        ac.setOnClickListener {
            reset()
        }
        ac1.setOnClickListener {
            reset()
        }
        plus.setOnClickListener {
            OperationClicked("+")
        }
        minus.setOnClickListener {
            OperationClicked("-")
        }
        multiply.setOnClickListener {
            OperationClicked("*")
        }
        devide.setOnClickListener {
            OperationClicked("/")
        }
        equal.setOnClickListener {
            CalcResult()
        }
        delete.setOnClickListener {
            Delete()
        }
        point.setOnClickListener {
            AddPoint()
        }
        plusminus.setOnClickListener {
            ChangeMark()
        }
    }
    @SuppressLint("SetTextI18n")
    fun DigitClicked (NumberClicked : String) {
        if (!OPERATION_CLICKED && !TYPING) {
            result.text = NumberClicked
        } else if (result.text.toString() == "0") {
            result.text = NumberClicked
        } else if (OPERATION_CLICKED) {
            OPERATION_CLICKED = false
            result.text = NumberClicked
        } else if (result.text == "ERROR") {
            reset()
        } else {
            result.text = "${result.text}${NumberClicked}"
        }
        TYPING = true
    }
    fun OperationClicked (operation : String) {
        if (result.text != "ERROR") {
            if (CURRENT_OPERATION == "") {
                OPERATION_CLICKED = true
                CURRENT_OPERATION = operation
                if (GLOBAL_RESULT == 0.0) {
                    GLOBAL_RESULT = result.text.toString().toDouble()
                } else {
                    CalcResult()
                    CURRENT_OPERATION = ""
                }
                TYPING = false
            } else {
                OPERATION_CLICKED = true
                if (GLOBAL_RESULT == 0.0) {
                    GLOBAL_RESULT = result.text.toString().toDouble()
                } else {
                    CalcResult()
                    CURRENT_OPERATION = operation
                }
                TYPING = false
            }
        }
    }
    fun reset() {
        GLOBAL_RESULT = 0.0
        result.text = "0"
        CURRENT_OPERATION = ""
        OPERATION_CLICKED = false
        TYPING = false
    }
    @SuppressLint("SetTextI18n")
    fun CalcResult () {
        if (CURRENT_OPERATION == "+") {
            SECOND_OPERAND = result.text.toString().toDouble()
            if (TYPING) {
                GLOBAL_RESULT += SECOND_OPERAND
                result.text = "$GLOBAL_RESULT"
                TYPING = false
            }
        }
        if (CURRENT_OPERATION == "-") {
            SECOND_OPERAND = result.text.toString().toDouble()
            if (TYPING) {
                GLOBAL_RESULT -= SECOND_OPERAND
                result.text = "$GLOBAL_RESULT"
                TYPING = false
            }
        }
        if (CURRENT_OPERATION == "*") {
            SECOND_OPERAND = result.text.toString().toDouble()
            if (TYPING) {
                GLOBAL_RESULT *= SECOND_OPERAND
                result.text = "$GLOBAL_RESULT"
                TYPING = false
            }
        }
        if (CURRENT_OPERATION == "/") {
            SECOND_OPERAND = result.text.toString().toDouble()
            if (TYPING) {
                if (SECOND_OPERAND != 0.0) {
                    GLOBAL_RESULT /= SECOND_OPERAND
                    result.text = "$GLOBAL_RESULT"
                    TYPING = false
                } else {
                    result.text = "ERROR"
                    GLOBAL_RESULT = 0.0
                }
            }
        }
        OPERATION_CLICKED = false
    }
    fun Delete () {
        if (result.text != "ERROR") {
            if (TYPING) {
                if (result.text.length > 1) {
                    result.text = result.text.subSequence(0, result.text.length - 1)
                } else {
                    result.text = "0"
                }
            }
        }
    }
    fun AddPoint () {
        if (result.text != "ERROR") {
            if (!result.text.contains(".")) {
                result.text = "${result.text}."
            }
        }
    }
    fun ChangeMark () {
        if (result.text.toString().toDouble() !=0.0) {
            result.text = (result.text.toString().toDouble() * (-1)).toString()
            TYPING = false
        }
    }
}