package com.agathaappb.harrypotter

import android.os.AsyncTask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.random
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        imageButton.setOnClickListener{
            houseHat()
        }

        loading.setOnClickListener {
            yourhouse.visibility = View.GONE
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

    fun houseHat() {
        val valorHouse = listOf(1, 2, 3, 4)
        val house = valorHouse.random()
        progress.visibility = View.VISIBLE

        when(house){
            1 -> {
                progress.visibility = View.GONE
                yourhouse.setImageResource(R.drawable.house_gryffindor_1)
                yourhouse.visibility = View.VISIBLE

            }
            2 -> {
                progress.visibility = View.GONE
                yourhouse.setImageResource(R.drawable.house_hufflepuff_1)
                yourhouse.visibility = View.VISIBLE

            }
            3 -> {
                progress.visibility = View.GONE
                yourhouse.setImageResource(R.drawable.house_ravenclaw_1)
                yourhouse.visibility = View.VISIBLE

            }
            4 -> {
                progress.visibility = View.GONE
                yourhouse.setImageResource(R.drawable.house_slytherin_1)
                yourhouse.visibility = View.VISIBLE


            }
        }
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


