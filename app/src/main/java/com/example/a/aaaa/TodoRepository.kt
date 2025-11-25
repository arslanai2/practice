package com.example.a.aaaa
class TodoRepository(private val dao: TodoDao) {
    fun getAll() = dao.getAll()


    suspend fun insert(todo: TodoEntity) {
        dao.insert(todo)
    }
}
