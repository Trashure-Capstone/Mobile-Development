package com.example.trashure.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.trashure.R
import java.io.File

class ComposeFileProvider : FileProvider(
    R.xml.file_paths
) {
    companion object {
        fun getImageUri(context: Context): Uri {
            val directory = File(context.cacheDir, "images")
            directory.mkdirs()
            val file = File.createTempFile(
                "selected_image_",
                ".jpg",
                directory,
            )
            val authority = "com.example.trashure.ui.screen.scan.ScanScreenKt"
            return getUriForFile(
                context,
                authority,
                file,
            )
        }
    }
}