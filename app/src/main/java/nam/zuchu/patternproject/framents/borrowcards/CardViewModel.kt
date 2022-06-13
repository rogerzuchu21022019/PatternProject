package nam.zuchu.patternproject.framents.borrowcards

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
import nam.zuchu.patternproject.database.entities.BorrowCard
import nam.zuchu.patternproject.database.entities.Member
import nam.zuchu.patternproject.database.entities.ThreeTablesBBM
import nam.zuchu.patternproject.repositories.AppRepository

class CardViewModel(application: Application) : AndroidViewModel(application) {
    var appRepository: AppRepository

    private val getCard : LiveData<List<BorrowCard>>

    init {
        val useDAO: AppDAO = AppDatabase.useDatabase(application).useUserDAO()
        appRepository = AppRepository(useDAO = useDAO)
        getCard = appRepository.getCards()
    }

     fun insertNewCard(borrowCard: BorrowCard){
         viewModelScope.launch(Dispatchers.IO) {
             appRepository.insertNewCard(borrowCard)
         }

    }
     fun deleteCardByID(id:Int){
         viewModelScope.launch(Dispatchers.IO){
            appRepository.deleteCardByID(idCard = id)
        }
    }

     fun updateCard(borrowCard: BorrowCard){
        viewModelScope.launch(Dispatchers.IO){
            appRepository.updateCard(borrowCard)
        }
    }

    val getCards: LiveData<List<BorrowCard>> = appRepository.getCards()
    fun getString(status:String):LiveData<List<BorrowCard>> {
        return appRepository.getString(status=status)
    }
}