package com.fk.photogallery.base.utils.viewbinding

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 *  # viewBinding() and FragmentBindingProperty
 * ### 为了减少使用ViewBinding时带来的模版代码，使用kotlin委托的方式减轻代码量
 * ## 使用方式：
 * ### val binding : FragmentXXXBinding by viewBinding()
 * ### viewBinding()利用反射调用ViewBinding的bind方法作为参数传入FragmentBindingProperty类中，并且利用其中的lifeCycle观察Fragment生命周期，释放内部viewbinding对象
 * ### 原文博主：https://juejin.cn/post/6960914424865488932#heading-9
 */
private const val TAG = "FragmentBindingProperty"
inline fun <reified VB : ViewBinding> viewBinding() = viewBinding(VB::class.java)

inline fun <reified T : ViewBinding> viewBinding(clazz: Class<T>): FragmentBindingProperty<Fragment, T> {
    val bindMethod = clazz.getDeclaredMethod("bind",View::class.java)
    return FragmentBindingProperty {
        bindMethod(null, it.requireView()) as T
    }
}

class FragmentBindingProperty<in F : Fragment, out VB : ViewBinding>(private val viewBinder: (F) -> VB) :
    ReadOnlyProperty<F, VB> {
    private var viewBinding: VB? = null

    override fun getValue(thisRef: F, property: KProperty<*>): VB {
        viewBinding?.let {
            return it
        }

        val viewBinding = viewBinder(thisRef)
        val lifecycle = thisRef.viewLifecycleOwner.lifecycle
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            Log.w(
                TAG, "Access to viewBinding after Lifecycle is destroyed or hasn't created yet. " +
                        "The instance of viewBinding will be not cached."
            )
        } else {
            lifecycle.addObserver(ReleaseBindingByLifeCycleObserver())
            this.viewBinding = viewBinding
        }
        return viewBinding
    }

    @MainThread
    fun clear() {
        Log.d("FragmentBindingProperty","clear")
        viewBinding = null
    }

    private inner class ReleaseBindingByLifeCycleObserver : DefaultLifecycleObserver {

        private val mainHandler = Handler(Looper.getMainLooper())

        override fun onDestroy(owner: LifecycleOwner) {
            owner.lifecycle.removeObserver(this)
            mainHandler.post {
                clear()
            }
        }
    }
}