package com.fk.photogallery.core.utils.net

import android.util.Log
import com.alibaba.fastjson.JSON
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

object HttpUtil {
    private const val TAG: String = "HttpUtil"
    private var okHttpClient: OkHttpClient? = null
    private var onDataSuccessCallback: OnDataSuccessCallback? = null

    fun setCallback(callback: OnDataSuccessCallback?) {
        this.onDataSuccessCallback = callback
    }

    init {
        okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true).build()
    }

    fun <T> get(clazz: Class<T>, url: String, callBack: RequestDataCallBack<T>) {
        val request = Request.Builder()
            .get()
            .url(url)
            .build()
        Log.d(TAG, url)
        okHttpClient?.newCall(request)?.enqueue(OkhttpCallBack(clazz, callBack))
    }

    class OkhttpCallBack<T>(private val clazz: Class<T>, var callback: RequestDataCallBack<T>?) : Callback {

        override fun onFailure(call: Call, e: IOException) {
            Log.d(TAG, e.toString())
        }

        override fun onResponse(call: Call, response: Response) {
            val body = response.body?.string()
            body?.let {
                Log.d(TAG, JSONObject(it).toString(4))
                onDataSuccessCallback?.onDataSuccess()
                callback?.dataCallBack(response.code, JSON.parseObject(it, clazz))
            }
        }
    }


}