package nam.zuchu.patternproject.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import org.jetbrains.annotations.NotNull

@Entity
data class ThreeTablesBBM(
    @ColumnInfo(name = "STTBook")
    val idBook: Int =0,
    @ColumnInfo(name = "STTType")
    var idType: Int =0,
    @ColumnInfo(name = "Name")
    var name: String,
    @ColumnInfo(name = "Author")
    var author: String,
    @ColumnInfo(name = "Company")
    var publishCompany: String,
    @ColumnInfo(name = "Year of manufacture")
    var year: Int =0,
    @ColumnInfo(name = "STTMember")
    var idMember: Int =0,
    @ColumnInfo(name = "Year Of Birthday")
    var yearOfBirthDay: String,
    @ColumnInfo(name = "Fullname")
    var fullname: String,
    @ColumnInfo(name = "Phone Number")
    var phoneNumber: String,
    @ColumnInfo(name = "STTBorrow")
    val idBorrowCard: Int =0,
    @ColumnInfo(name = "Username")
    var username: String ="",
    @ColumnInfo(name = "Date Borrow")
    var dateBorrow: String ="",
    @ColumnInfo(name = "Date Give")
    var dateGive: String ="",
    @ColumnInfo(name = "Price To Borrow")
    var priceToBorrow: Double,
    @ColumnInfo(name = "Status")
    var status: String ="",
    @ColumnInfo(name = "Quantity")
    var quantity: Int
)
