package com.fk.photogallery.base.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.fk.photogallery.base.model.RuntimeData
import com.fk.photogallery.base.model.dao.IntentParma

object FunctionUtils {
    fun goTo(
        targetActivity: Class<out Activity>,
        intentParma: IntentParma?,
        finishCurrent: Boolean
    ) {
        RuntimeData.getInstance().currentActivity?.let {
            val intent = Intent()
            intent.apply {
                setClass(it, targetActivity)
                if (action.isNullOrEmpty()) {
                    action = "intent_put"
                }
                intentParma?.let { parma ->
                    putExtra(action, parma)
                }
            }
            it.startActivity(intent)

            if (finishCurrent) {
                it.finish()
            }
        }

    }

}