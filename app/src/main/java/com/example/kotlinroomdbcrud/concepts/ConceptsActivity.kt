package com.example.kotlinroomdbcrud.concepts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinroomdbcrud.R

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
//        lazyEg()

        /*val add = add()
        add.doSum(2,3)
        add.doSum(2,3, object: helpToSum {
            override fun doMySum(sum: Int) {
                Log.e("add", "doMySum:- $sum")
            }
        })*/

        /*val human = Human()
        human.age = 50
        Log.e("human", "age:- ${human.age}")*/

        /*val student = Student()
        student.addHobby("Game")
        student.addHobby("TV")
        Log.e("student", "hobby:- ${student.hobby}")*/

        /*var successMsg =  ApiResult.success("data got")
        var failMsg =  ApiResult.fail("null")
        var loadingMsg =  ApiResult.loading("isLoading")
        sealedClassEg(loadingMsg)*/
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

    fun sealedClassEg(result: ApiResult) {

        when (result) {
            is ApiResult.success -> Log.e("sealedClassEg","${result.data}")
            is ApiResult.fail -> Log.e("sealedClassEg","${result.message}")
            is ApiResult.loading -> Log.e("sealedClassEg","${result.message}")
            else -> Log.e("sealedClassEg","else")
        }
    }
}

class add {

    fun doSum(num1: Int, num2: Int) {

        val sum = num1 + num2
        Log.e("add", "sum:- $sum")
    }

    fun doSum(num1: Int, num2: Int, helpToSum: helpToSum) {

        val sum = num1 + num2
        helpToSum.doMySum(sum)
    }
}

interface helpToSum {
    fun doMySum(sum: Int)
}

class Person(a: String)

class MySelf {
//    var name: String = "Rahil"
    var name: String = ""
//    var age: Int = 28
    var age: Int = 0
}

//todo eg#1 of Backing property/field
class Human {
    private var _age: Int = 0
    var age: Int
        get() {
            return _age
        }
        set(value) {
            _age = value
        }

}

//todo eg#2 of Backing property/field
class Student {
    private var _hobby = mutableListOf<String>()
    val hobby: List<String>
    get() = _hobby

    fun addHobby(text: String) {
        _hobby.add(text)
    }
}

sealed class ApiResult() {
    class loading(var message: String): ApiResult()
    class success(var data: String): ApiResult()
    class fail(var message: String): ApiResult()
}
