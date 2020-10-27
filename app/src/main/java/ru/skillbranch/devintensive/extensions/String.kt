package ru.skillbranch.devintensive.extensions

fun String.truncate(size : Int = 16) : String {
    var answer = this
    answer = answer.trim()
    if (answer.length > size) {
        answer = answer.substring(0,size)
        if (answer.last() == ' ') {
            answer = answer.dropLast(1)
        }
        answer = "$answer..."
    }
    return answer
}

fun String.stripHtml() : String {
    var answer = this
    val patternSpace : Regex ="\\s+".toRegex()
    val patternTag : Regex ="<[^>]*>".toRegex()
    val patternEnd : Regex ="&[^;]*;".toRegex()
    answer = answer.replace(patternTag, "").replace(patternEnd, "").replace(patternSpace, " ")
    return answer
}