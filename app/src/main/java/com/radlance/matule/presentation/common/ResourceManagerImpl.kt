package com.radlance.matule.presentation.common

import android.content.Context
import javax.inject.Inject

class ResourceManagerImpl @Inject constructor(
    private val context: Context
) : ResourceManager {
    override fun getString(id: Int): String = context.getString(id)
}