package com.example.easydictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.easydictionary.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchBtn.setOnClickListener{
            val word = binding.searchInput.text.toString()
            getMeaning(word)

        }


    }
    private fun getMeaning(word : String){
        setInProgress(true)
        GlobalScope.launch {
            val response = RetrofitInstance.dictionaryApi.getMeaning(word)
             runOnUiThread {
                 setInProgress(false)
             }
        }

    }

    private fun setInProgress(inProgress : Boolean){
        if(inProgress){
            binding.searchBtn.visibility= View.INVISIBLE
            binding.progressBar.visibility= View.VISIBLE
        }else{
            binding.searchBtn.visibility = View.INVISIBLE
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}