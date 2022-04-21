package com.watt.wattwebview

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Process
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by khm on 2022-01-06.
 */

class SplashActivity :AppCompatActivity() {
    
    private var badAccessDialog:BadAccessDialog?=null

    private val prefDirName = "saved_url"
    private val prefKey = "url"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        badAccessDialog = BadAccessDialog(this)



        
        if(intent.action == Intent.ACTION_VIEW){
            Log.d("ActionView","${intent.action}")
            val uri = intent.data
            if(uri != null){
                val url = uri.getQueryParameter("meetingUrl") ?: ""
                if(url.isNotEmpty())
                    saveUrl(url)
                startActivity(url)
            }
        }else{
            val savedUrl = PreferenceFunction.getStringPreference(this, prefDirName,prefKey)
            if(savedUrl.isEmpty()){
                //for test
//                startActivity("https://dev.watttalk.kr")
//                return

                badAccessDialog?.showDialog {
                    finishAffinity()
                    System.exit(0)
                }
            }else{
                startActivity(savedUrl)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        badAccessDialog?.dismiss()
    }

    private fun startActivity(url:String){
        badAccessDialog?.let{
            if(it.isShowing)
                it.dismiss()
        }
        Log.d("url","$url")

        val startIntent = Intent(this, MainActivity::class.java).apply {
            action = Intent.ACTION_MAIN
            addCategory(Intent.CATEGORY_LAUNCHER)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("url", url)
        }

        startActivity(startIntent)
    }


    private fun saveUrl(url:String){
        Log.d("saveUrl",url)
        var saveUrl = ""

        //????
        val firstIndex = url.lastIndexOf("/?reservId")
        if(firstIndex != -1){
            val tempUrl = url.substring(0, firstIndex)
            val index = tempUrl.lastIndexOf("kr:")
            if(index != -1){
                saveUrl = tempUrl.substring(0, index+2)
                Log.d("lastFirstSaveUrl",saveUrl)
            }
        }else{  //?????
            val secondIndex = url.lastIndexOf("/meetingRoom")
            if(secondIndex != -1){
                val tempUrl = url.substring(0, secondIndex)
                val index = tempUrl.lastIndexOf("kr:")
                if(index != -1){
                    saveUrl = tempUrl.substring(0, index+2)
                    Log.d("lastSecondSaveUrl",saveUrl)
                }
            }
        }

        if(saveUrl.isNotEmpty()){
            Log.d("subStringUrl",saveUrl)
            PreferenceFunction.setStringPreference(this, prefDirName, prefKey, saveUrl)
        }
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        
    }
}