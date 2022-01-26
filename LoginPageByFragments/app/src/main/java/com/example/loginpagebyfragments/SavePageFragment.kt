package com.example.loginpagebyfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.commit
import com.example.info_page.SaveData
import com.example.loginpagebyfragments.databinding.FragmentLoginPageBinding
import com.example.loginpagebyfragments.databinding.FragmentSavePageBinding

class SavePageFragment : Fragment(R.layout.fragment_save_page) {

    lateinit var binding: FragmentSavePageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var saveData = this.activity?.let { SaveData(it.baseContext) }
        binding = FragmentSavePageBinding.bind(view)

        binding.tvFullName.text = saveData?.getFullName()
        binding.tvUserName.text = saveData?.getUserName()
        binding.tvEmail.text = saveData?.getEmail()
        binding.tvPassword.text = saveData?.getPassword()
        binding.tvGender.text = saveData?.getGender()

        binding.btnSaveInfo?.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, LoginPageFragment())
            }
        }
    }
}