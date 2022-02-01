package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import com.example.geoquiz.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var listKey: MutableList<Int> = mutableListOf()
    var num:Int = 0
    var res: Int = 0
    var count: Int = 1
    var list: HashMap<Int, Pair<String, String>> = hashMapOf(
        1 to Pair("Is london in England", "true"),
        2 to Pair("Is berlin in Germany", "true"),
        3 to Pair("Is paris in spain", "false"),
        4 to Pair("Is alaska in canada", "false"),
        5 to Pair("Is texas in USA", "true"),
        6 to Pair("Is Rio in Brazil", "true"),
        7 to Pair("Is Montevideo A in czech", "false"),
        8 to Pair("Is sydney in australia", "true"),
        9 to Pair("Is prague in austria", "false"),
        10 to Pair("Is Buenos Aires in Argentine", "true"),
        11 to Pair("null", "true"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvQuestions.text = list[count]?.first

        disBtn(listKey,list)

        binding.btnNext.setOnClickListener {
            count++
            binding.btnPerv.isEnabled = true
            binding.tvQuestions.text = list[count]?.first.toString()
            if (count >= list.size-1) {
                binding.btnNext.isEnabled = false
            }
            disBtn(listKey,list)
        }
        binding.btnPerv.setOnClickListener {
            var num1:Int = 0
            count--
            binding.tvQuestions.text = list[count]?.first.toString()
            if (count < 2) {
                binding.btnPerv.isEnabled = false
            } else {
                binding.btnNext.isEnabled = true
            }
            disBtn(listKey,list)
        }

        binding.btnFalse.setOnClickListener {
            var flag: Boolean = false
            for (i in 0 until list.size) {
                if (binding.tvQuestions.text.toString() == list[i]?.first && list[i]?.second == "false") {
                    flag = true
                } else {
                    flag
                }
            }
            if (flag) {
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "InCorrect", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnTrue.setOnClickListener {
            var flag: Boolean = false
            for (i in 0 until list.size) {
                if (binding.tvQuestions.text.toString() == list[i]?.first && list[i]?.second == "true") {
                    flag = true
                } else {
                    flag
                }
            }
            if (flag) {
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "InCorrect", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnCheat.setOnClickListener {
            listKey.add(count)
            var I = Intent(Intent(this, CheatActivity::class.java))
            I.putExtra("DATA", binding.tvQuestions.text.toString())
            startActivityForResult(I,2000)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2000 && resultCode == RESULT_OK){
            res = (data?.getIntExtra("Result",-1)?:"Null") as Int
            num = (res)
            Log.d(res.toString(),"result")
            listKey.add(res)

        }
    }
    private fun disBtn(list: MutableList<Int> , listKey:HashMap<Int,Pair<String,String>>){
        for (i in list.indices){
            if (list.isNotEmpty()){
                if (listKey[list[i]]?.first == binding.tvQuestions.text){
                    binding.btnFalse.isEnabled = false
                    binding.btnTrue.isEnabled = false
                }else{
                    binding.btnFalse.isEnabled = true
                    binding.btnTrue.isEnabled = true
                }
            }
        }
        if (list.isEmpty()){
            binding.btnFalse.isEnabled = true
            binding.btnTrue.isEnabled = true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count" , count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        count = savedInstanceState.get("count") as Int
        binding.tvQuestions.text = list[count]?.first
//        binding.btnPerv.isEnabled = true
    }

}
