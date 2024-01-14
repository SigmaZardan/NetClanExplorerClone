package com.example.netclanexplorer.model

data class Person(
    val firstName: String,
    val middleName: String?,
    val lastName: String,
    val address: String,
    val locationRange: Int,
    val title: String,
    val description: String?
)
