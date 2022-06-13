package nam.zuchu.patternproject.database.entities

import android.os.Parcelable
import androidx.room.*
import androidx.room.ForeignKey.Companion.NO_ACTION
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "Book",
    foreignKeys = [ForeignKey(
        entity = TypeOfBook::class,
        parentColumns = arrayOf("STTType"),
        childColumns = arrayOf("STTType"),
        onDelete = NO_ACTION, deferred = false
    )]
)
@Parcelize
data class Book constructor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "STTBook")
    val idBook: Int,
    @ColumnInfo(name = "STTType")
    var idType: Int,
    @ColumnInfo(name = "Name")
    var name: String,
    @ColumnInfo(name = "Author")
    var author: String,
    @ColumnInfo(name = "Company")
    var publishCompany: String,
    @ColumnInfo(name = "Price To Borrow")
    var priceToBorrow: Double,
    @ColumnInfo(name = "Year of manufacture")
    var year: Int
) : Parcelable


