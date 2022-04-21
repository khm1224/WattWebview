package com.watt.wattwebview

import android.content.Context
import org.json.JSONException
import org.json.JSONObject

class PreferenceFunction {
    companion object{
        fun getStringPreference(context: Context, dirName: String, key: String):String{
            return context.getSharedPreferences(dirName, 0).getString(key, "")!!
        }

        fun setStringPreference(context: Context, dirName: String, key: String, content: String){
            context.getSharedPreferences(dirName, 0).edit().putString(key, content).apply()
        }

        fun clearSharedPreferences(context: Context, dirName: String){
            context.getSharedPreferences(dirName, 0).edit().clear().apply()
        }

        fun setHashMapPreference(context: Context, stringHashMap: HashMap<String, Object>){
            val json = JSONObject()

            try {
                for((key, value) in stringHashMap){
                    json.put(key, value)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }


        fun clearAllDataClassPreference(context:Context){
            context.getSharedPreferences(dataClassDirName, 0).edit().clear().apply()
        }

        fun deleteDataClassPreference(context:Context, key:String){
            context.getSharedPreferences(dataClassDirName, 0).edit().remove(key).apply()
        }

        private const val dataClassDirName = "data_class_dirname"





    }



}

