package com.anooplab.newsapp.main

import android.os.Bundle
import com.anooplab.newsapp.R
import com.anooplab.newsapp.base.BaseDaggerActivity
import com.anooplab.newsapp.factslist.FactsListFragment

class MainActivity : BaseDaggerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FactsListFragment.newInstance()).commit()
    }
}
