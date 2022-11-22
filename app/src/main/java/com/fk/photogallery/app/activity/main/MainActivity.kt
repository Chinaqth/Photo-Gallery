package com.fk.photogallery.app.activity.main

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentTransaction
import com.ansen.shape.AnsenImageView
import com.fk.photogallery.R
import com.fk.photogallery.app.activity.main.home.HomeFragment
import com.fk.photogallery.app.activity.main.my.MyFragment
import com.fk.photogallery.base.activity.BaseActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {
    private var homeFragment: HomeFragment? = null
    private var findFragment: MyFragment? = null
    private var mailFragment: MyFragment? = null
    private var myFragment: MyFragment? = null

    private lateinit var ivHome: AnsenImageView
    private lateinit var ivDiscover: AnsenImageView
    private lateinit var ivMail: AnsenImageView
    private lateinit var ivPerson: AnsenImageView
    private var oldId = -1


    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreateContent(savedInstanceState: Bundle?) {
        super.onCreateContent(savedInstanceState)
        ivHome = findViewById(R.id.iv_home)
        ivDiscover = findViewById(R.id.iv_discover)
        ivMail = findViewById(R.id.iv_mail)
        ivPerson = findViewById(R.id.iv_person)
        homeFragment = HomeFragment()
        changeFragment(R.id.rl_home)
        supportFragmentManager.beginTransaction().add(R.id.main_container, homeFragment!!).commit()

        val scope = MainScope()

        val launch = scope.launch {


        }


        scope.launch {
            launch.join()
        }



    }

    override fun onAfterCreated(savedInstanceState: Bundle?) {
        super.onAfterCreated(savedInstanceState)
        setViewOnClickListener(R.id.rl_home, onClickListener)
        setViewOnClickListener(R.id.rl_discover, onClickListener)
        setViewOnClickListener(R.id.rl_mail, onClickListener)
        setViewOnClickListener(R.id.rl_person, onClickListener)
    }

    private val onClickListener = View.OnClickListener { view: View -> changeFragment(view.id) }

    private fun changeFragment(@IdRes resId: Int) {
        if (resId != oldId) {
            changSelectFragment(resId)
            oldId = resId
        } else {
            doubleClickRefresh(resId)
        }
    }

    private fun changSelectFragment(@IdRes resId: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        if (oldId > 0) {
            changeIcon(oldId, false)
            hideFragment(oldId, transaction)
        }
        showFragment(resId, transaction)
        changeIcon(resId, true)
        transaction.commitAllowingStateLoss()
    }

    private fun hideFragment(@IdRes resId: Int, transaction: FragmentTransaction) {
        when (resId) {
            R.id.rl_home -> {
                homeFragment?.let {
                    transaction.hide(it)
                }
            }
            R.id.rl_discover -> {
                findFragment?.let {
                    transaction.hide(it)
                }
            }
            R.id.rl_mail -> {
                mailFragment?.let {
                    transaction.hide(it)
                }
            }
            R.id.rl_person -> {
                myFragment?.let {
                    transaction.hide(it)
                }
            }
        }
    }

    private fun showFragment(@IdRes resId: Int, transaction: FragmentTransaction) {
        when(resId) {
            R.id.rl_home -> {
                if (homeFragment == null) {
                    homeFragment = HomeFragment()
                    transaction.add(R.id.main_container, homeFragment!!)
                } else {
                    transaction.show(homeFragment!!)
                }
            }
            R.id.rl_discover -> {
                if (findFragment == null) {
                    findFragment = MyFragment()
                    transaction.add(R.id.main_container, findFragment!!)
                } else {
                    transaction.show(findFragment!!)
                }
            }
            R.id.rl_mail -> {
                if (mailFragment == null) {
                    mailFragment = MyFragment()
                    transaction.add(R.id.main_container, mailFragment!!)
                } else {
                    transaction.show(mailFragment!!)
                }
            }
            R.id.rl_person -> {
                if (myFragment == null) {
                    myFragment = MyFragment()
                    transaction.add(R.id.main_container, myFragment!!)
                } else {
                    transaction.show(myFragment!!)
                }
            }
        }
    }

    private fun doubleClickRefresh(resId: Int) {
        when(resId) {
            R.id.rl_home -> {
                homeFragment?.doubleClickRefresh()
            }
            R.id.rl_discover -> {

            }
            R.id.rl_mail -> {

            }
            R.id.rl_person -> {

            }
        }
    }

    private fun changeIcon(@IdRes resId: Int, isSelect: Boolean) {
        when(resId) {
            R.id.rl_home -> {
                changeIcon(ivHome, isSelect)
            }
            R.id.rl_discover -> {
                changeIcon(ivDiscover, isSelect)
            }
            R.id.rl_mail -> {
                changeIcon(ivMail, isSelect)
            }
            R.id.rl_person -> {
                changeIcon(ivPerson, isSelect)
            }
        }
    }

    private fun changeIcon(imageView: AnsenImageView?, isSelect: Boolean) {
        if (imageView != null) {
            imageView.isSelected = isSelect
            imageView.updateSrc()
        }
    }

}