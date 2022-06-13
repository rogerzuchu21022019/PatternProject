package nam.zuchu.patternproject.database.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.lang.reflect.Constructor

@Entity(tableName = "Member")

@Parcelize
data class Member (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "STTMember")
    var idMember: Int,

    @ColumnInfo(name = "Year Of Birthday")
    var yearOfBirthDay: String,

    @ColumnInfo(name = "Fullname")
    var fullname: String,

    @ColumnInfo(name = "Phone Number")
    var phoneNumber: String

) : Parcelable



