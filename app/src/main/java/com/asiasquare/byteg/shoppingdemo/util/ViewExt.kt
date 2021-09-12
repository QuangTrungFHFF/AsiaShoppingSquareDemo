package com.asiasquare.byteg.shoppingdemo.util

import android.util.Log
import androidx.appcompat.widget.SearchView
import java.text.Normalizer

inline fun SearchView.onQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
//            listener(removeAccents(query).orEmpty())
            listener(query.orEmpty())
            clearFocus()
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            //listener(removeAccents(newText).orEmpty())
            return true
        }
    })

}


//fun stripAccents(s: String?): String? {
//    var s = s
//    s = Normalizer.normalize(s, Normalizer.Form.NFD)
//    s = s.replace("[\\p{M}]".toRegex(), "") //remove all accents
//    s = s.replace("a|e|i|o".toRegex(), "*") //replace a, e, i ,o with wildcard
//    return s
//}

//fun addTildeOptions(searchText: String?): String {
//    return searchText?.toLowerCase()
//        ?.replace("\\.*[aáàäâã]\\.*".toRegex(), "[aáàäâã]")!!
//
//}
//

//private val REGEX_UNACCENT = "\\p{Mn}+".toRegex()
//fun CharSequence.unaccent(): String {
//    val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
//    return REGEX_UNACCENT.replace(temp, "")
//}




//work
private var MAP_NORM: MutableMap<Char, Char>? = null
fun removeAccents(value: String?): String? {
    if (MAP_NORM == null || MAP_NORM!!.isNotEmpty()) {
        MAP_NORM = HashMap()
        MAP_NORM!!['a'] = 'á'
        MAP_NORM!!['A'] = 'Á'
 //       MAP_NORM!!['a'] = 'à'

    }
    if (value == null) {
        return ""
    }
    val sb = StringBuilder(value)
    for (i in 0 until value.length) {
        val c = MAP_NORM!![sb[i]]
        if (c != null) {
            sb.setCharAt(i, c.toChar())
        }
    }
    return sb.toString()
}

