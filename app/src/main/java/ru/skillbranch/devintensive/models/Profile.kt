package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils

data class Profile(

    val firstName:String,
    val lastName:String,
    val repository:String,
    val about:String,
    val rating:Int = 0,
    val respect:Int = 0
) {
    val rank:String = "Типа разработчик"
    private val nickName:String = Utils.transliteration(("$firstName $lastName"), "_")
    fun toMap():Map<String,Any> = mapOf(
        "nickName" to nickName,
        "rank" to rank,
        "firstName" to firstName,
        "lastName" to lastName,
        "repository" to repository,
        "about" to about,
        "rating" to rating,
        "respect" to respect
    )
}