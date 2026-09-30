package com.example.listycity

import com.google.firebase.firestore.DocumentId
import java.util.UUID
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class City
    @OptIn(ExperimentalUuidApi::class)
    constructor(
        val name: String = "",
        val province: String = "",
        val id: String = Uuid.random().toString(),
    )
