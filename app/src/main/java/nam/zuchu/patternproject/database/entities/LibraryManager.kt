package nam.zuchu.patternproject.database.entities

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Library")
@Parcelize
data class LibraryManager (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="Username")
    @NotNull
    val username:String,
    @ColumnInfo(name="Name")
    var name:String,
    @ColumnInfo(name="Password")
    var password:String,
    @ColumnInfo(name = "Role")
    var role: String?
) : Parcelable
