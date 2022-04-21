package com.watt.wattwebview

import android.content.Context
import androidx.appcompat.app.AppCompatDialog
import com.watt.wattwebview.databinding.DialogBadAccessBinding
import com.watt.wattwebview.databinding.DialogQuitBinding

/**
 * Created by khm on 2022-01-06.
 */

class BadAccessDialog(context: Context?) :
    AppCompatDialog(context, 0) {


    private val binding = DialogBadAccessBinding.inflate(layoutInflater)

    init {
        setContentView(binding.root)
    }

    fun showDialog(onClickQuit:()->Unit){
        if(isShowing)
            dismiss()

        binding.tvOk.setOnClickListener {
            dismiss()
            onClickQuit()
        }

        show()
    }



}