Bilkul! Yahan Roman Urdu mein 10 lines mein simple explain kar deta hoon ke yeh SSH keys wali commands mein kya ho raha hai 👇

1️⃣ GitBash ke andar ssh-agent ko start karte hain taake keys memory mein load ho saken.
2️⃣ Hum nayi SSH key generate karte hain — yeh GitHub pe login/operations ke liye hoti hai.
3️⃣ Ek separate SSH key commit signing ke liye banti hai taa ke GitHub verify kar sake ke commit sach mein aapka hai.
4️⃣ Dono keys ke alag naam rakhe jaate hain (auth & signing).
5️⃣ Keys generate karne ke waqt email add hoti hai taa ke GitHub pe identity link ho.
6️⃣ ssh-add command keys ko ssh-agent ke andar load kar deti hai.
7️⃣ Phir Git push karte waqt password poochne ki zaroorat nahi rehti.
8️⃣ Signing key ka kaam hai commit par verified badge lagwana.
9️⃣ Commands chalane ke baad GitHub settings mein Public Key copy/paste karni hoti hai.
🔟 ssh-add -l se check hota hai ke agent ke andar kaunsi keys loaded hain.
sssssshhhhhhhhh keys above

![Image 1](clean.PNG)


main ab tamam flow ko bilkul aasaan Roman-Urdu mei step-by-step samjha deta hoon, aur saath hi insert / delete / update / getAll sab functions ka full flow bhi clear karaonga.

Neeche poora Room Architecture aapke code ke hisaab se explain kiya gaya hai, WITH citations from your files.

⭐ COMPLETE ROOM FLOW — Roman Urdu Explanation (with citations)
1️⃣ Entity (Database Table)

File: TodoEntity.kt 

TodoEntity

@Entity(tableName = "arslantbl")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String
)

✔ Roman Urdu explanation

Yeh Room table hoti hai.

@Entity ka matlab Database mei yeh ek table banayega.

tableName = "arslantbl" matlab table ka naam yeh hoga.

id auto increment primary key hai.

title text column hai.

2️⃣ DAO (Data Access Object)

File: TodoDao.kt 

TodoDao

@Dao
interface TodoDao {
    @Query("SELECT * FROM arslantbl")
    fun getAll(): Flow<List<TodoEntity>>

    @Insert
    suspend fun insert(todo: TodoEntity)
}

✔ Roman Urdu explanation

DAO wo jagah hoti hai jahan database se interaction hota hai.

Yahan do functions hain:

1) getAll()

Room se continuous flow me list deta hai.

Flow<List<TodoEntity>> ka matlab realtime updates miltay rahain.

2) insert()

@Insert se data table mei add hota hai.

suspend ka matlab yeh function coroutine mei chalega.

⚡ Missing functions you want (Delete, Update):

Yeh bhi DAO mei add honay chahiye:

@Delete
suspend fun delete(todo: TodoEntity)

@Update
suspend fun update(todo: TodoEntity)


Agar chaho main yeh aapke project ke hisaab se add karke de doon.

3️⃣ Database Class

File: AppDatabase.kt 

AppDatabase

@Database(entities = [TodoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}

✔ Roman Urdu explanation

Yeh Room database ka main setup hai.

entities = [TodoEntity] matlab is database mei filhaal aik hi table hai.

todoDao() DAO provide karta hai.

4️⃣ Repository Layer

File: TodoRepository.kt 

TodoRepository

class TodoRepository(private val dao: TodoDao) {
    fun getAll() = dao.getAll()

    suspend fun insert(todo: TodoEntity) {
        dao.insert(todo)
    }
}

✔ Roman Urdu explanation

Repository middle layer hoti hai:

App (UI) ←→ Repository ←→ DAO (database)

Iska kaam hai:
✔ Database se data lana
✔ ViewModel ko provide karna
✔ Business logic handle karna

Agar delete/update chahiye:

suspend fun delete(todo: TodoEntity) = dao.delete(todo)
suspend fun update(todo: TodoEntity) = dao.update(todo)

5️⃣ ViewModel

File: TodoViewModel.kt 

TodoViewModel

class TodoViewModel(private val repo: TodoRepository) : ViewModel() {

    val data = repo.getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun add(title: String) {
        viewModelScope.launch {
            repo.insert(TodoEntity(title = title))
        }
    }
}

✔ Roman Urdu explanation

ViewModel UI ko data provide karta hai.

data ek StateFlow hai → realtime list milti rehti hai.

add() function insert call karta hai.

Agar delete/update chahiye:

fun delete(todo: TodoEntity) {
    viewModelScope.launch {
        repo.delete(todo)
    }
}

fun update(todo: TodoEntity) {
    viewModelScope.launch {
        repo.update(todo)
    }
}

6️⃣ ViewModel Factory

File: TodoVMFactory.kt 

TodoVMFactory

Yeh repository ko ViewModel mei inject karta hai.

7️⃣ MainActivity Flow

File: MainActivity.kt 

MainActivity

vm.add("Hello Room")

✔ Roman Urdu explanation

Activity Room database banati hai.

DAO → Repository → ViewModel chain create hoti hai.

vm.add() ka matlab new Todo insert karo.

Data listen karne ka flow:

lifecycleScope.launch {
    vm.data.collectLatest {
        Log.d("DATA", it.toString())
    }
}

⭐ FULL FLOW SUMMARY (Roman Urdu)

Activity database banati hai → DAO result ViewModel mei jata hai

ViewModel UI ke liye data hold karta hai

Repository database se baat karta hai

DAO queries chalata hai

Entity Room table hoti hai

Flow ke zariye hamesha fresh data milta rehta hai

🔥 Agar chaho to main:

✅ Poora updated code tumhare project mei add karke de doon
➡ insert, delete, update, getById, getAll
➡ UI example (RecyclerView)
➡ Click to delete / edit complete flow

Bas bolo: “full code with delete + update bhi bana do”
