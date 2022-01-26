package com.example.loginpagebyfragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.*
import com.example.info_page.SaveData
import com.example.loginpagebyfragments.databinding.FragmentLoginPageBinding

class LoginPageFragment : Fragment(R.layout.fragment_login_page) {

    lateinit var binding: FragmentLoginPageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginPageBinding.bind(view)
        var saveData = this.activity?.let { SaveData(it.baseContext) }
        var gender:String = ""

        binding.radioGroupGender.setOnCheckedChangeListener { radioGroup,_ ->
            gender = if (radioGroup.checkedRadioButtonId == R.id.rbtnMale){
                "Male"
            } else {
                "Female"
            }
        }
        fun getInputData(){
            saveData?.saveUserInfo(binding.etFullName.text.toString()
                ,binding.etUserName.text.toString(),binding.etEmail.text.toString()
                ,binding.etPassword.text.toString(),gender)
        }
        fun checkInput(){
            if (binding.etPassword.text.toString() != binding.etRetypePassword.text.toString()){
                Toast.makeText(activity?.baseContext, "Not Equal", Toast.LENGTH_LONG).show()
            }else if (binding.etPassword.text.toString() == binding.etRetypePassword.text.toString() &&
                binding.etFullName.text.isNotEmpty() && binding.etUserName.text.isNotEmpty() && binding.etEmail.text.isNotEmpty()){
                getInputData()
                parentFragmentManager.commit {
                    replace(R.id.fragment_container, SavePageFragment())
                }
                Toast.makeText(activity?.baseContext, "Is Done", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(activity?.baseContext, "Fill Info", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnRegister?.setOnClickListener {
            checkInput()
        }
    }

}