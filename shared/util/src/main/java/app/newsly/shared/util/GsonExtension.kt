package app.newsly.shared.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun <T> T.toJson(gson: Gson = Gson()) = gson.toJson(this)

inline fun <reified T> String.fromJson(): T? = try {
    Gson().fromJson<T?>(this, object : TypeToken<T?>() {}.type)
} catch (ignored: Exception) {
    null
}