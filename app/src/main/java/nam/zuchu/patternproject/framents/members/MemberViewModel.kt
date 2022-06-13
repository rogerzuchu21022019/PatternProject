package nam.zuchu.patternproject.framents.members

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nam.zuchu.patternproject.database.AppDatabase
import nam.zuchu.patternproject.database.entities.Member
import nam.zuchu.patternproject.repositories.AppRepository

class MemberViewModel(application: Application) : AndroidViewModel(application) {
    var appRepository: AppRepository
    private var getAllInformation: LiveData<List<Member>>


    init {
        val useDAO = AppDatabase.useDatabase(application).useUserDAO()
        appRepository = AppRepository(useDAO = useDAO)
        getAllInformation = appRepository.getMembers()
    }

     fun insertNewMember(member: Member) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.insertNewMember(member = member)
        }
    }

     fun deleteMembers() {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.deleteMembers()
        }
    }

     fun deleteMemberByID(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.deleteMemberByID(id = id)
        }
    }

     fun updateMember(member: Member) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.updateMember(member = member)
        }
    }


    val getMembers: LiveData<List<Member>> = appRepository.getMembers()

}
