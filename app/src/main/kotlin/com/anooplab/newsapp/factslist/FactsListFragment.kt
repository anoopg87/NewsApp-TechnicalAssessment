package com.anooplab.newsapp.factslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.anooplab.newsapp.R
import com.anooplab.newsapp.base.BaseDaggerActivity
import com.anooplab.newsapp.factslist.view.FactsListAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.facts_list.*

class FactsListFragment : DaggerFragment() {

    private lateinit var viewModel: FactsListViewModel

    private lateinit var factsListAdapter: FactsListAdapter

    companion object {
        fun newInstance(): FactsListFragment {
            return FactsListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.facts_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        factsList.layoutManager = LinearLayoutManager(context)
        factsListAdapter = FactsListAdapter()
        factsList.adapter = factsListAdapter
        factsList.setHasFixedSize(true)
        swiperefresh.setOnRefreshListener {
            viewModel.reloadFacts()
        }
    }

    private fun setupViewModel() {
        viewModel = (activity as BaseDaggerActivity).getViewModel()
        viewModel.factsObserver.observe(viewLifecycleOwner, Observer {
            ((activity as AppCompatActivity)).supportActionBar?.title = it.title
            factsListAdapter.factsList = it.factsItemUiModels.toMutableList()
        })

        viewModel.loadingObserver.observe(viewLifecycleOwner, Observer {
            swiperefresh.isRefreshing = it
        })

        viewModel.errorObserver.observe(viewLifecycleOwner, Observer {
            Snackbar.make(rootView, it, Snackbar.LENGTH_SHORT).show()
        })
        viewModel.showFacts()
    }
}