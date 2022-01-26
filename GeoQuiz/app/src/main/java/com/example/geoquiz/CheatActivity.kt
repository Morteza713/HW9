package com.example.geoquiz

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.ActivityMainBinding
import com.example.geoquiz.databinding.CheatActivityBinding

class CheatActivity : AppCompatActivity() {

    private lateinit var binding: CheatActivityBinding
    private var key:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cheat_activity)

        binding = CheatActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var list: HashMap<Int, Pair<String, String>> = hashMapOf()
        list[1] = Pair("Is london in England", "true")
        list[2] = Pair("Is berlin in Germany", "true")
        list[3] = Pair("Is paris in spain", "false")
        list[4] = Pair("Is alaska in canada", "false")
        list[5] = Pair("Is texas in USA", "true")
        list[6] = Pair("f", "d")

        var getData = intent.getStringExtra("DATA")

        binding.btnShowAns.setOnClickListener {
            binding.tvSeeAns.text = checkData(getData!! , list)
            binding.tvSeeAns.visibility = View.VISIBLE
            val intent = Intent()
            intent.putExtra("Result" , key)
            setResult(RESULT_OK , intent)
            Log.d(key.toString(),"key")
//            this.finish()
        }
        key = getData?.let { checkDataKey(it, list) }!!
    }
    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this,"Cheating is Wrong", Toast.LENGTH_SHORT).show()
    }

    private fun checkData(data:String , list:HashMap<Int,Pair<String,String>>):String{
        var ans:String = ""
        for (i in 0 until list.size){
            if (list[i]?.first == data){
                ans = list[i]?.second.toString()

            }
        }
        return ans
    }
    private fun checkDataKey(data:String , list:HashMap<Int,Pair<String,String>>):Int{
        var key:Int = 0
        for (i in 0 until list.size){
            if (list[i]?.first == data){
                key = i
            }
        }
        return key
    }

}