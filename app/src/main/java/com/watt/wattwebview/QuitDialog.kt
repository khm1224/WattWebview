package com.watt.wattwebview

import android.content.Context
import androidx.appcompat.app.AppCompatDialog
import com.watt.wattwebview.databinding.DialogQuitBinding

class QuitDialog(context: Context?) :
        AppCompatDialog(context, 0) {


    private val binding = DialogQuitBinding.inflate(layoutInflater)

    init {
        setContentView(binding.root)

        binding.tvCancel.setOnClickListener {
            dismiss()
        }
    }

    fun showDialog(onClickQuit:()->Unit){
        if(isShowing)
            dismiss()

        binding.tvOk.setOnClickListener {
            onClickQuit()
            dismiss()
        }

        show()
    }



}