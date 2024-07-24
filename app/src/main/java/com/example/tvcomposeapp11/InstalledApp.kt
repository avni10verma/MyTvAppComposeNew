package com.example.tvcomposeapp11

import android.content.Context

import android.content.pm.PackageManager
import android.graphics.drawable.Drawable

data class InstalledApp(val name: String, val packageName: String, val icon: Drawable)

fun getInstalledApps(context: Context): List<InstalledApp> {
    val packageManager = context.packageManager
    val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
    return apps.map { app ->
        InstalledApp(
            name = packageManager.getApplicationLabel(app).toString(),
            packageName = app.packageName,
            icon = packageManager.getApplicationIcon(app)
        )
    }
}