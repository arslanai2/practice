package com.example.a

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.a.aaaa.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(this, AppDatabase::class.java, "mydb").build()
        val repo = TodoRepository(db.todoDao())

        val vm: TodoViewModel by viewModels {
            TodoVMFactory(repo)
        }

        vm.add("Hello Room")

        lifecycleScope.launch {
            vm.data.collectLatest {
                Log.d("DATA", it.toString())
            }
        }
    }
}
