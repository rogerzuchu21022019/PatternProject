package nam.zuchu.patternproject.framents.books

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nam.zuchu.patternproject.database.AppDatabase
import nam.zuchu.patternproject.database.dao.AppDAO
import nam.zuchu.patternproject.database.entities.Book
import nam.zuchu.patternproject.database.entities.Member
import nam.zuchu.patternproject.repositories.AppRepository

class BookViewModel(application: Application) : AndroidViewModel(application) {
    var appRepository: AppRepository

    private val getBook: LiveData<List<Book>>

    init {
        val useDAO: AppDAO = AppDatabase.useDatabase(application).useUserDAO()
        appRepository = AppRepository(useDAO = useDAO)
        getBook = appRepository.getBooks()
    }

    fun insertNewBookInRecyclerView(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.insertNewBook(book = book)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.deleteBooks()
        }
    }

    fun deleteItemInRecycleViewByID(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.deleteBookByID(id = id)
        }
    }
//
//    val getInner: LiveData<Book>
//        = appRepository.getInner()


    fun updateInformationBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.updateBook(book = book)
        }
    }

    val getBooks: LiveData<List<Book>> = appRepository.getBooks()
}