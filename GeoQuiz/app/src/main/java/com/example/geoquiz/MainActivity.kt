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
import com.example.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var listKey: MutableList<Int> = mutableListOf()
    var num:Int = 0
    var res: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var list: HashMap<Int, Pair<String, String>> = hashMapOf()
        list[1] = Pair("Is london in England", "true")
        list[2] = Pair("Is berlin in Germany", "true")
        list[3] = Pair("Is paris in spain", "false")
        list[4] = Pair("Is alaska in canada", "false")
        list[5] = Pair("Is texas in USA", "true")
        list[6] = Pair("Is texas in USA", "true")
        list[7] = Pair("Is texas in USA", "true")
        list[8] = Pair("Is texas in USA", "true")
        list[9] = Pair("Is texas in USA", "true")
        list[10] = Pair("Is texas in USA", "true")
        list[11] = Pair("f", "d")
        var count: Int = 1

        binding.tvQuestions.text = list[count]?.first

        binding.btnNext.setOnClickListener {
            count++
            binding.btnPerv.isEnabled = true
            binding.tvQuestions.text = list[count]?.first.toString()
            if (count >= list.size-1) {
                binding.btnNext.isEnabled = false
            }
        }
        binding.btnPerv.setOnClickListener {
            var num1:Int = 0
            count--
            binding.tvQuestions.text = list[count]?.first.toString()
            if (count == 1) {
                binding.btnPerv.isEnabled = false
            } else {
                binding.btnNext.isEnabled = true
            }
            if (listKey.isNotEmpty()){
                for (i in 0 until listKey.size){
                    if (list.keys.equals(listKey[i])){
                        list[listKey[i]]
                        binding.btnTrue.isEnabled = false
                        binding.btnFalse.isEnabled = false
                        Toast.makeText(this,"Cheating is Wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                binding.btnTrue.isEnabled = true
                binding.btnFalse.isEnabled = true
            }
//            if (num1 > 0){
//                binding.btnTrue.isEnabled = false
//                binding.btnFalse.isEnabled = false
//                Toast.makeText(this,"Cheating is Wrong", Toast.LENGTH_SHORT).show()
//            }
            Log.d(num1.toString(),"num1")
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


//        val contract = object :ActivityResultContract<String,String>() {
//            override fun createIntent(context: Context, input: String?): Intent {
//                return Intent(this@MainActivity, CheatActivity::class.java).apply {
//                    putExtra("DATA", binding.tvQuestions.text.toString())
//                }
//            }
//
//            override fun parseResult(resultCode: Int, intent: Intent?): String {
//                var result: String = intent?.getStringExtra("Result") ?: "Null"
//                return result.toString()
//            }
//        }
//
//        val activityResultLauncher =  registerForActivityResult(contract ,object :ActivityResultCallback<String>{
//            override fun onActivityResult(result: String?) {
//                result.toString()
//            }
//
//        })
//       val resultContract = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result:ActivityResult? ->
//           if (result?.resultCode == Activity.RESULT_OK){
//               binding.tvQuestions.text.toString()
//           }
//       }
        binding.btnCheat.setOnClickListener {
            var I = Intent(Intent(this, CheatActivity::class.java))
            I.putExtra("DATA", binding.tvQuestions.text.toString())
            startActivityForResult(I,2000)
//            startActivity(I)
//            activityResultLauncher.launch("DATA")
//            resultContract.launch(I)
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

}