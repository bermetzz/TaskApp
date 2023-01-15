package com.example.taskapp
import java.io.Serializable

data class Task(
    var title : String? = null,
    var description : String? = null,
): Serializable
