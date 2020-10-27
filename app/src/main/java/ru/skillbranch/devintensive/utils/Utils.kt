package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?): Pair<String?, String?>{
        val parts : List<String>? = fullName?.trim()?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        firstName = firstName?.trim()
        lastName = lastName?.trim()
        if ((firstName == "")&&(lastName == "")){
            firstName = null
            lastName = null
        }
        else if (firstName == ""){
            firstName = null
        }
        else if (lastName == ""){
            lastName = null
        }
        //return Pair (firstName, lastName)
        return firstName to lastName
    }
    fun transliteration(payload : String, divider : String = " ") : String {
        val parts: List<String>? = payload.split(" ")
        var newString = ""
        var newPart = ""
        var myListSize = 0
        val listSize = parts?.size
        if (parts != null) {
            for (part in parts) {
                myListSize++
                for (charIndex in part) {
                    var upperCase = false
                    var myChar = charIndex
                    if (charIndex.isUpperCase()) {
                        upperCase = true
                        myChar = charIndex.toLowerCase()
                    }
                    var myString = "" + myChar
                    myString = myCase(myString)
                    if (myString != "") {
                        if (upperCase) {
                            var myFirstString = myString.getOrNull(0)?.toUpperCase().toString()
                            var mySecondString = myString.getOrNull(1)
                            var myThirdString = myString.getOrNull(2)
                            myString = if ((mySecondString != null) && (myThirdString != null)) {
                                myFirstString + mySecondString + myThirdString
                            } else if (mySecondString != null) {
                                myFirstString + mySecondString
                            } else myFirstString
                        }
                        newPart += myString
                    }
                }
                if (myListSize != listSize) {
                    newPart += divider
                }
            }
            newString += newPart
        }
        return newString
    }

    private fun myCase(myString : String) : String {
        return when(myString) {
            "а" -> "a"
            "б" -> "b"
            "в" -> "v"
            "г" -> "g"
            "д" -> "d"
            "е" -> "e"
            "ё" -> "e"
            "ж" -> "zh"
            "з" -> "z"
            "и" -> "i"
            "й" -> "i"
            "к" -> "k"
            "л" -> "l"
            "м" -> "m"
            "н" -> "n"
            "о" -> "o"
            "п" -> "p"
            "р" -> "r"
            "с" -> "s"
            "т" -> "t"
            "у" -> "u"
            "ф" -> "f"
            "х" -> "h"
            "ц" -> "c"
            "ч" -> "ch"
            "ш" -> "sh"
            "щ" -> "sh'"
            "ъ" -> ""
            "ы" -> "i"
            "ь" -> ""
            "э" -> "e"
            "ю" -> "yu"
            "я" -> "ya"
            else -> myString
        }
    }
    fun toInitials(firstName: String?, lastName: String? ) : String? {
        var firstNameInitial = firstName?.trim()?.getOrNull(0)
        var lastNameInitial = lastName?.trim()?.getOrNull(0)
        var initials : String?
        if ((firstNameInitial == null)&&(lastNameInitial == null)) {
            initials = null
        }
        else if (lastNameInitial == null) {
            initials ="" + firstNameInitial
            initials = initials.toUpperCase()
        }
        else if (firstNameInitial == null) {
            initials ="" + lastNameInitial
            initials = initials.toUpperCase()
        }
        else {
            initials = "" + firstNameInitial + lastNameInitial
            initials = initials.toUpperCase()
        }
        return initials
    }
}
