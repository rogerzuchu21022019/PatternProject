package nam.zuchu.patternproject.database.entities

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Entity(tableName = "TypeOfBook")
@Parcelize
 data class TypeOfBook (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="STTType")
    val idTypeOfBook:Int,
    @ColumnInfo(name="Name")
    var nameType:String
) : Parcelable
