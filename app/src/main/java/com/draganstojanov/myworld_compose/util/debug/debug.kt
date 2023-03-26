package biz.simula.infinity.util.debug

import android.util.Log

fun debugLog(tag: Any? = "", msg: Any?) {
    Log.d("MCC.DBG-$tag", msg.toString())
}