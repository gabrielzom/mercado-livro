package com.mercadolivro.model

import java.util.*

data class CustomerModel(
        var uuid: String,
        var fullName: String,
        var email: String,
        var createdAt: Date,
        var createdBy: String
)