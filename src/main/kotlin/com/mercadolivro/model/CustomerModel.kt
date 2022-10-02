package com.mercadolivro.model

import java.util.*

data class CustomerModel(
        var id: Int,
        var fullName: String,
        var email: String,
        var createdAt: Date,
        var createdBy: String
)