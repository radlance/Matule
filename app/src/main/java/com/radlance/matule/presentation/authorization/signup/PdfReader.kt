package com.radlance.matule.presentation.authorization.signup

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.radlance.matule.R

class PdfReader(private val context: Context) {
    fun readPdf(downloadUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(Uri.parse(downloadUrl), "application/pdf")
            addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        }

        with(context) {
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, getString(R.string.no_pdf_reader), Toast.LENGTH_SHORT).show()
            }
        }

    }
}