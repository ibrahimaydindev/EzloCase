package com.ibrahimaydindev.ezlocase.database

import androidx.room.Query

interface DeviceDao {

    @Query("SELECT * FROM news")
    fun getAllArticles(): LiveData<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: News)

    @Delete
    suspend fun deleteArticle(article: News)
    @Query("SELECT * FROM news WHERE id = :articleId")
    suspend fun getArticle(articleId: Int): News

}