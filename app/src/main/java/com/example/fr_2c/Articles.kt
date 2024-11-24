package com.example.example

import com.google.gson.annotations.SerializedName


data class Articles (

  //@SerializedName("source"      ) var source      : Source? = Source(),
  @SerializedName("title"       ) var title         : String? = null,
  @SerializedName("source_name"      ) var author   : String? = null,
  //@SerializedName("url"         ) var url         : String? = null,
  //@SerializedName("urlToImage"  ) var urlToImage  : String? = null,
  @SerializedName("pubDate" ) var publishedAt : String? = null,
  @SerializedName("description" ) var description : String? = null
  //@SerializedName("content"     ) var content     : String? = null

)