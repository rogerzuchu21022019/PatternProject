package nam.zuchu.patternproject.framents.librarymanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nam.zuchu.patternproject.database.AppDatabase
import nam.zuchu.patternproject.database.dao.AppDAO
import nam.zuchu.patternproject.database.entities.Book
import nam.zuchu.patternproject.database.entities.LibraryManager
import nam.zuchu.patternproject.repositories.AppRepository

class LibraryViewModel(application: Application) : AndroidViewModel(application) {
    var appRepository: AppRepository

    private val getManagers: LiveData<List<LibraryManager>>

    init {
        val useDAO: AppDAO = AppDatabase.useDatabase(application).useUserDAO()
        appRepository = AppRepository(useDAO = useDAO)
        getManagers = appRepository.getManagers()
    }

    fun insertNewManager(manager: LibraryManager) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.insertNewManager(manager)
        }
    }

    fun updateManager(manager: LibraryManager) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.updateManager(manager)
        }
    }

    fun getManagerByLogin(username: String, password: String): LibraryManager {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.getManagerByUsername(username = username, password = password)
        }
        return appRepository.getManagerByUsername(username = username, password = password)

    }

    val getManager: LiveData<List<LibraryManager>> = appRepository.getManagers()


}