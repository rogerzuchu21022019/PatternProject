package nam.zuchu.patternproject.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import nam.zuchu.patternproject.database.entities.*

@Dao
interface AppDAO {

    // TODO: Book
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertNewBook(book: Book)
    @Query("delete from `Book`")
    suspend fun deleteBooks()
    @Query("delete from `Book` where STTBook =:id")
    suspend fun deleteBookByID(id:Int)
    @Update
    suspend fun updateBook(book: Book)
    @Query("select * from `Book`")
    fun getBooks():LiveData<List<Book>>


    // TODO: Member
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertNewMember(member: Member)
    @Query("delete from `Member`")
    suspend fun deleteMembers()
    @Query("delete from `Member` where STTMember =:id")
    suspend fun deleteMemberByID(id:Int)
    @Update
    suspend fun updateMember(member: Member)
    @Query("select * from `Member`")
    fun getMembers():LiveData<List<Member>>


    // TODO: TypeOFBook
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertNewType(typeOfBook: TypeOfBook)
    @Query("delete from `TypeOfBook`")
    suspend fun deleteTypes()
    @Query("delete from `TypeOfBook` where STTType =:id")
    suspend fun deleteTypeByID(id:Int)
    @Update
    suspend fun updateType(typeOfBook: TypeOfBook)
    @Query("select * from `TypeOfBook`")
    fun getTypes():LiveData<List<TypeOfBook>>


    // TODO: Library Manager
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewManager(libraryManager: LibraryManager)
    @Update
    suspend fun updateManager(libraryManager: LibraryManager)
    @Query("select * from `Library`")
     fun getManagers():LiveData<List<LibraryManager>>
    @Query("SELECT * from Library where Username=(:username) and Password=(:password)")
    fun getManagerByLogin(username:String,password:String):LibraryManager

    // TODO: BorrowCard
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewCard(borrowCard: BorrowCard)
    @Update
    suspend fun updateCard(borrowCard: BorrowCard)
    @Query("delete from `BorrowCard` where `STTBorrow` =:idCard")
    suspend fun deleteCardByID(idCard: Int)
    @Query("select * from `BorrowCard`")
    fun getCards():LiveData<List<BorrowCard>>

    // TODO: inner join
//    @Query("select br.`Total Paid`,b.STTBook,m.Fullname,br.Status from borrowcard br inner join book b on  br.STTBook = b.STTBook inner join member m on br.STTMember  = m.STTMember where br.Status like :status")
    @Query("select * from borrowcard br inner join book b on  br.STTBook = b.STTBook inner join member m on br.STTMember  = m.STTMember where br.Status like :status ")
    fun getString(status:String):LiveData<List<BorrowCard>>
    @Query("select *  from borrowcard br inner join book b on  br.STTBook = b.STTBook inner join member m on br.STTMember  = m.STTMember  where br.Fullname like :name")
    fun staticByName(name:String):LiveData<List<BorrowCard>>
    @Query("select *  from borrowcard br inner join book b on  br.STTBook = b.STTBook inner join member m on br.STTMember  = m.STTMember  order by   br.`Total Paid` desc ")
    fun getByTotalPaid():LiveData<List<BorrowCard>>
    @Query("select distinct * from BorrowCard  order by `Total Paid` desc limit 10 ")
    fun getTop10():LiveData<List<BorrowCard>>
}