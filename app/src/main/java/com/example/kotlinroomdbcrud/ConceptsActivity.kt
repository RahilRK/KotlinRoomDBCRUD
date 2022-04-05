package com.example.kotlinroomdbcrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ConceptsActivity : AppCompatActivity() {

    val TAG = "ConceptsActivity"

    val name: String by lazy {
        "Rahil"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concepts)

//        equalToLogic()
//        scopeWith()
//        scopeApply()
//        scopeAlso()
        lazyEg()
    }

    fun equalToLogic() {
        val a = "How2"
        val b = "How"
        val c = a
//        Log.e(TAG, "equalToLogic1: ${a==c}")
//        Log.e(TAG, "equalToLogic2: ${a===c}")

        val p1 = Person("rahil")
        val p2 = Person("rahil")
        Log.e(TAG, "equalToLogic3 ${p1===p2}")
        Log.e(TAG, "equalToLogic4 ${p1==p2}")
    }

    //todo scopeFunctions
    fun scopeWith() {

        val mySelfModel = MySelf()
        Log.e(TAG, "scopeWith1:- ${mySelfModel.name}, ${mySelfModel.age}")

        with(mySelfModel){
            Log.e(TAG, "scopeWith2:- ${this.name}, ${this.age}")
        }

        with(mySelfModel){
            Log.e(TAG, "scopeWith3:- ${name}, $age")
        }

        val returnValue = with(mySelfModel){
            age+5
            name.reversed()
        }
        Log.e(TAG, "scopeWith4:- $returnValue")
    }

    fun scopeApply() {

        val mySelfModel = MySelf().apply {
            name = "Raj"
            age = 22
        }

        with(mySelfModel) {
            Log.e(TAG, "scopeApply:- ${name}, ${age}")
        }
    }

    fun scopeAlso() {

        val numberList: MutableList<Int> = mutableListOf(1,2,3)
//        numberList.add(4)
//        numberList.removeAt(1)
//        Log.e(TAG, "scopeAlso:- $numberList")

        numberList.also {
            it.add(4)
            it.removeAt(1)
            Log.e(TAG, "scopeAlso:- $it")
        }
    }

    fun lazyEg() {
        Log.e(TAG, "lazyEg:- $name")
    }
}

class Person(a: String)

class MySelf {
//    var name: String = "Rahil"
    var name: String = ""
//    var age: Int = 28
    var age: Int = 0
}