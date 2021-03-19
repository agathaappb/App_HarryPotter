package com.agathaappb.harrypotter

import android.annotation.SuppressLint
import android.os.AsyncTask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.loader.content.AsyncTaskLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        loading.setOnClickListener {
            lauchHarryTask()
        }
    }
    fun showData(list : List<Harry>){
        //result_textview.text = ""

        list?.forEach{bruxo ->
            result_textview.append("Bruxo: ${bruxo.name} - Casa: ${bruxo.house} \n\n")

        }
    }
    fun showLoadingBar(){
        progress.visibility = View.VISIBLE
    }
    fun hideLoadingBar(){
        progress.visibility = View.GONE
    }

    fun lauchHarryTask(){
        val task = TaskHarry()
        task.execute()
    }



    inner class TaskHarry() : AsyncTask<Void, Int, List<Harry>>() {
        private val repository = HarryRepository()
        //On Pre Execute -> nesta etapa ainda podemos fazer alterações em nossa UI
        override fun onPreExecute() {
            super.onPreExecute()
            showLoadingBar()
        }

        //Nesta etapa que vamos de fato fazer com que a tarefa seja executada em background
        //o loadData nos retorna uma lista de Harry (*.*)
        override fun doInBackground(vararg p0: Void?): List<Harry>? {
           return repository.loadData()
        }

        override fun onPostExecute(result: List<Harry>?) {
            super.onPostExecute(result)
            hideLoadingBar()

            if (result != null) {

                showData(result)
            }

        }

    }
}