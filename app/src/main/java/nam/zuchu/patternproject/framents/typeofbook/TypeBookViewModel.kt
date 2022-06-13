package nam.zuchu.patternproject.framents.typeofbook

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nam.zuchu.patternproject.database.AppDatabase
import nam.zuchu.patternproject.database.entities.Member
import nam.zuchu.patternproject.database.entities.ThreeTablesBBM
import nam.zuchu.patternproject.database.entities.TypeOfBook
import nam.zuchu.patternproject.repositories.AppRepository

class TypeBookViewModel(application: Application) : AndroidViewModel(application) {
    var appRepository: AppRepository
    private var getAllTypes: LiveData<List<TypeOfBook>>

    init {
        val useDAO = AppDatabase.useDatabase(application).useUserDAO()
        appRepository = AppRepository(useDAO = useDAO)
        getAllTypes = appRepository.getTypes()
    }

    fun insertNewType(type: TypeOfBook) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.insertNewType(typeOfBook = type)
        }
    }

    fun deleteTypes() {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.deleteTypes()
        }
    }

    fun deleteTypeByID(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.deleteTypeByID(id = id)
        }
    }

    fun updateType(type: TypeOfBook) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.updateType(typeOfBook = type)
        }
    }


    val getTypes: LiveData<List<TypeOfBook>> = appRepository.getTypes()


}
