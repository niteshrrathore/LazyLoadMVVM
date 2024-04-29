package com.example.myapplication.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.RecyclerViewAdapter
import com.example.myapplication.core.utils.RecyclerItemClickListenr
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.model.Posts
import com.example.myapplication.presentation.viewModel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    val viewModel:PostViewModel by viewModels()
    private var rowsArrayList = ArrayList<Posts?>()
    private val recyclerViewAdapter = RecyclerViewAdapter(rowsArrayList)
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding!!.root
        setContentView(view)

        supportActionBar!!.title = "Posts"
        binding.recyclerView.adapter = recyclerViewAdapter
        initScrollListener()

        viewModel.postList.observe(this, {
            Log.d("MainActivity123" ,"Success-${it?.size}")
            rowsArrayList.addAll(it!!)
            recyclerViewAdapter.notifyDataSetChanged()
        })

        viewModel.errorMessage.observe(this, {
            Log.d("MainActivity123" ,"Error - $it")
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                Log.d("MainActivity123" ,"Show Loading")
                //binding.progressDialog.visibility = View.VISIBLE
            } else {
                Log.d("MainActivity123" ,"Hide Loading")
                //binding.progressDialog.visibility = View.GONE
            }
        })

        binding.recyclerView.addOnItemTouchListener(RecyclerItemClickListenr(this, binding.recyclerView, object :
            RecyclerItemClickListenr.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(applicationContext, ReceiverActivity::class.java)
                intent.putExtra("objPost", rowsArrayList[position])
                startActivity(intent)
            }
            override fun onItemLongClick(view: View?, position: Int) {
                TODO("do nothing")
            }
        }))

    }

    private fun initScrollListener() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager
                        .findLastCompletelyVisibleItemPosition() == rowsArrayList.size-1) {
                        //bottom of list!
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        rowsArrayList.add(null)
        recyclerViewAdapter.notifyItemInserted(rowsArrayList.size - 1)

        val handler = Handler()
        handler.postDelayed(Runnable {
            rowsArrayList.removeAt(rowsArrayList.size - 1)
            val scrollPosition: Int = rowsArrayList.size
            recyclerViewAdapter.notifyItemRemoved(scrollPosition)
            viewModel.getAllPosts()
            recyclerViewAdapter.notifyDataSetChanged()
            isLoading = false
        }, 1000)
    }
}
