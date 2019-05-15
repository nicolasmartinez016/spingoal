package personal.nmartinez.fr.virtualfootballpicker

import android.content.Context

class FileManager {
    companion object {
        fun readJsonFile(context: Context, resourceId: Int) : String {
            return context.resources.openRawResource(resourceId)
                    .bufferedReader().use { it.readText() }
        }
    }
}