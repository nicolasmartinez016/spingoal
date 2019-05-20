package personal.nmartinez.fr.spingoal.utils

import android.content.Context

class FileUtils {
    companion object {
        fun readJsonFile(context: Context, resourceId: Int) : String {
            return context.resources.openRawResource(resourceId)
                    .bufferedReader().use { it.readText() }
        }
    }
}