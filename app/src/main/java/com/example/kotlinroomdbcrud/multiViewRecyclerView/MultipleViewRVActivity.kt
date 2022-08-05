package com.example.kotlinroomdbcrud.multiViewRecyclerView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinroomdbcrud.R

class MultipleViewRVActivity : AppCompatActivity() {

    private lateinit var gfgRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_view_rvactivity)

        val dataList = ArrayList<DataModel>()
        dataList.add(DataModel(My_Adapter.VIEW_TYPE_ONE, "1. Geeks View 1"))
        dataList.add(DataModel(My_Adapter.VIEW_TYPE_TWO, "2. Geeks View 2"))
        dataList.add(DataModel(My_Adapter.VIEW_TYPE_ONE, "3. Geeks View 3"))
        dataList.add(DataModel(My_Adapter.VIEW_TYPE_TWO, "4. Geeks View 4"))
        dataList.add(DataModel(My_Adapter.VIEW_TYPE_ONE, "5. Geeks View 5"))
        dataList.add(DataModel(My_Adapter.VIEW_TYPE_TWO, "6. Geeks View 6"))
        dataList.add(DataModel(My_Adapter.VIEW_TYPE_ONE, "7. Geeks View 7"))
        dataList.add(DataModel(My_Adapter.VIEW_TYPE_TWO, "8. Geeks View 8"))
        dataList.add(DataModel(My_Adapter.VIEW_TYPE_ONE, "9. Geeks View 9"))
        dataList.add(DataModel(My_Adapter.VIEW_TYPE_TWO, "10. Geeks View 10"))
        dataList.add(DataModel(My_Adapter.VIEW_TYPE_ONE, "11. Geeks View 11"))
        dataList.add(DataModel(My_Adapter.VIEW_TYPE_TWO, "12. Geeks View 12"))


        val adapter = My_Adapter(this, dataList)
        gfgRecycler = findViewById(R.id.gfgRecycler)
        gfgRecycler.layoutManager = LinearLayoutManager(this)
        gfgRecycler.adapter = adapter
    }
}