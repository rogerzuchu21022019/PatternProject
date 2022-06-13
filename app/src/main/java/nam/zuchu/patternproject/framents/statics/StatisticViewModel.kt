package nam.zuchu.patternproject.framents.statics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nam.zuchu.patternproject.database.AppDatabase
import nam.zuchu.patternproject.database.entities.BorrowCard
import nam.zuchu.patternproject.database.entities.Member
import nam.zuchu.patternproject.database.entities.ThreeTablesBBM
import nam.zuchu.patternproject.repositories.AppRepository

class StatisticViewModel(application: Application) : AndroidViewModel(application) {
    var appRepository: AppRepository
    private var getInners: LiveData<List<BorrowCard>>


    init {
        val useDAO = AppDatabase.useDatabase(application).useUserDAO()
        appRepository = AppRepository(useDAO = useDAO)
        getInners = appRepository.getCards()
    }
    fun getByTotalPaid():LiveData<List<BorrowCard>>{
        return appRepository.getByTotalPaid()
    }
    fun getString(status:String):LiveData<List<BorrowCard>>{
        return appRepository.getString(status = status)
    }
    fun staticByName(name:String):LiveData<List<BorrowCard>>{
        return appRepository.staticByName(name = name)
    }
    fun getTop10():LiveData<List<BorrowCard>> {
      return appRepository.getTop10()
    }







}
