package nam.zuchu.patternproject.database.entities

import android.os.Parcelable
import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import kotlinx.parcelize.Parcelize
import nam.zuchu.patternproject.framents.members.MemberManageFM

@Entity(
    tableName = "BorrowCard",
    foreignKeys = [ForeignKey(
        entity = LibraryManager::class,
        parentColumns = arrayOf("Username"),
        childColumns = arrayOf("Username"),
        onUpdate = CASCADE,
        onDelete = CASCADE,
        deferred = false
    ),ForeignKey(
        entity = Member::class,
        parentColumns = arrayOf("STTMember"),
        childColumns = arrayOf("STTMember"),
        onUpdate = CASCADE,
        onDelete = CASCADE,
        deferred = false
    ), ForeignKey(
        entity = Book::class,
        parentColumns = arrayOf("STTBook"),
        childColumns = arrayOf("STTBook"),
        onUpdate = CASCADE,
        onDelete = CASCADE,
        deferred = false
    ),
    ]
)
@Parcelize
data class BorrowCard (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "STTBorrow")
    val idBorrowCard: Int,
    @ColumnInfo(name = "Username")
    var username: String,
    @ColumnInfo(name = "STTMember")
    var idMember: Int,
    @ColumnInfo(name = "STTBook")
    var idBook: Int,
    @ColumnInfo(name = "Fullname")
    var fullname: String,
    @ColumnInfo(name = "Name Book")
    var namebook: String,
    @ColumnInfo(name = "Create at")
    var createAt: String,
    @ColumnInfo(name = "End Date")
    var endDate: String,
    @ColumnInfo(name = "Price To Borrow")
    var priceToBorrow: Double,
    @ColumnInfo(name = "Status")
    var status: String,
    @ColumnInfo(name = "Quantity")
    var quantity: Int,
    @ColumnInfo(name = "Total Paid")
    var totalPaid :Double
) : Parcelable
