package nam.zuchu.patternproject.repositories

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import nam.zuchu.patternproject.database.dao.AppDAO
import nam.zuchu.patternproject.database.entities.*

class AppRepository(var useDAO: AppDAO) {

    suspend fun insertNewBook(book: Book){
        useDAO.insertNewBook(book=book)
    }

    suspend fun deleteBooks(){
        useDAO.deleteBooks()
    }

    suspend fun deleteBookByID(id:Int){
        useDAO.deleteBookByID(id = id)
    }

    suspend fun updateBook(book: Book){
        useDAO.updateBook(book=book)
    }
    fun getBooks(): LiveData<List<Book>> = useDAO.getBooks()

//    fun getInner():LiveData<Book>{
//        return useDAO.getInner()
//    }


    suspend fun insertNewMember(member: Member){
        useDAO.insertNewMember(member = member)
    }
    suspend fun deleteMembers(){
        useDAO.deleteMembers()
    }
    suspend fun deleteMemberByID(id:Int){
        useDAO.deleteMemberByID(id = id)
    }
    suspend fun updateMember(member: Member){
        useDAO.updateMember(member=member)
    }
    fun getMembers():LiveData<List<Member>> {
        return useDAO.getMembers()
    }

    suspend fun insertNewType(typeOfBook: TypeOfBook){
        useDAO.insertNewType(typeOfBook = typeOfBook)
    }
    suspend fun deleteTypes(){
        useDAO.deleteTypes()
    }
    suspend fun deleteTypeByID(id:Int){
        useDAO.deleteTypeByID(id = id)
    }
    suspend fun updateType(typeOfBook: TypeOfBook){
        useDAO.updateType(typeOfBook = typeOfBook)
    }
    fun getTypes():LiveData<List<TypeOfBook>> {
        return useDAO.getTypes()
    }



    // TODO: Library Manager

    suspend fun insertNewManager(libraryManager: LibraryManager){
        useDAO.insertNewManager(libraryManager)
    }

    suspend fun updateManager(libraryManager: LibraryManager){
        useDAO.updateManager(libraryManager)
    }

      fun getManagers():LiveData<List<LibraryManager>>{
         return useDAO.getManagers()
     }

    fun getManagerByUsername(username:String,password:String):LibraryManager{
        return useDAO.getManagerByLogin(username = username, password = password)
    }


    // TODO: Borrow Card
    suspend fun insertNewCard(borrowCard: BorrowCard){
        useDAO.insertNewCard(borrowCard = borrowCard)
    }
    suspend fun updateCard(borrowCard: BorrowCard){
        useDAO.updateCard(borrowCard = borrowCard)
    }
    suspend fun deleteCardByID(idCard: Int){
        useDAO.deleteCardByID(idCard = idCard)
    }
    fun getCards():LiveData<List<BorrowCard>> {
        return useDAO.getCards()
    }

    // TODO: Inner
    fun getString(status:String):LiveData<List<BorrowCard>>{
        return useDAO.getString(status=status)
    }
    fun getByTotalPaid():LiveData<List<BorrowCard>>{
        return useDAO.getByTotalPaid()
    }
    fun staticByName(name:String):LiveData<List<BorrowCard>>{
        return useDAO.staticByName(name=name)
    }
    fun getTop10():LiveData<List<BorrowCard>> = useDAO.getTop10()


}