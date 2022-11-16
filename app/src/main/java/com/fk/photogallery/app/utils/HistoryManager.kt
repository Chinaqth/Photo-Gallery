package com.fk.photogallery.app.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.alibaba.fastjson.JSON
import com.fk.photogallery.base.model.RuntimeData
import java.util.ArrayList

object HistoryManager {
    private var MAXSIZE = 10
    private var sp: SharedPreferences? = null
    private var edit: SharedPreferences.Editor? = null

    init {
        sp = RuntimeData.getInstance().context.getSharedPreferences("history", Context.MODE_PRIVATE)
        edit = sp?.edit()
    }

    fun addHistory(history: String) {
        getHistories().let {
            if (it.size == MAXSIZE) {
                it.removeAt(0)
            }
            it.add(history)
            val jsonString = JSON.toJSONString(it)
            Log.d("HistoryManager",jsonString)
            edit?.let { sp ->
                sp.putString("history", jsonString)
                sp.commit()
            }
        }

    }

    fun getHistories(): ArrayList<String> {
        val jsonString = sp?.getString("history", "")
        Log.d("HistoryManager", jsonString.toString())
        return if (jsonString.isNullOrEmpty()) {
            ArrayList<String>()
        } else {
            JSON.parseObject(jsonString, ArrayList<String>()::class.java)
        }
    }


}