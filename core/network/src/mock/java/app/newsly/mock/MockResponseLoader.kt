package app.newsly.mock

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*


object MockResponseLoader {

    fun getResponseString(context: Context, method: String, endPoints: List<String>): String {
        try {
            var currentPatch = "mockApi"
            var mockList =
                HashSet<String>(context.assets.list(currentPatch)?.toList() ?: emptyList())
            for (endpoint in endPoints) {
                if (mockList.contains(endpoint)) {
                    currentPatch = "$currentPatch/$endpoint"
                    mockList =
                        HashSet(context.assets.list(currentPatch)?.toList() ?: emptyList())
                }
            }
            var finalPath = ""
            for (path in mockList) {
                if (path.contains(method.lowercase(Locale.getDefault()))) {
                    finalPath = "$currentPatch/$path"
                    break
                }
            }
            if (finalPath.isNotEmpty())
                return getResponseString(context, finalPath)
        } catch (e: IOException) {
            //Timber.e(e, "error in reading response string")
        }
        return ""
    }

    private fun getResponseString(context: Context, path: String): String {
        val stringBuilder = StringBuilder()
        try {
            val pathInputStream = context.assets.open(path)
            val bufferedReader = BufferedReader(InputStreamReader(pathInputStream))
            var line = ""
            while (bufferedReader.readLine()?.let { line = it } != null) {
                stringBuilder.append(line)
            }
        } catch (e: Exception) {
            //Timber.e(e, "error reading mock response file")
        }
        return stringBuilder.toString()
    }

}
