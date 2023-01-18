package com.example.taskapp.model
import java.io.Serializable

data class Task(
    var title : String? = null,
    var description : String? = null,
): Serializable
