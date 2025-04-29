package com.example.fr_2c.DataClasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Article")
data class Articles (
  @PrimaryKey(autoGenerate = true) val id: Int?,
  //@SerializedName("source"      ) var source      : Source? = Source(),
  @SerializedName("title")
  @ColumnInfo(name = "title") var title: String? = null,

  @SerializedName("source_name")
  @ColumnInfo(name = "source_name") var author: String? = null,
  //@SerializedName("url"         ) var url         : String? = null,
  //@SerializedName("urlToImage"  ) var urlToImage  : String? = null,
  @SerializedName("pubDate" )
  @ColumnInfo(name = "pubDate") var publishedAt: String? = null,

  @SerializedName("description" )
  @ColumnInfo(name = "description") var description :String? = null
  //@SerializedName("content"     ) var content     : String? = null

)