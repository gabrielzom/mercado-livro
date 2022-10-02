package com.mercadolivro.controller.response.dto

import java.util.Date

data class CustomerCreatedDto(
    var generatedId: Int,
    var createdAt: Date,
    var createdBy: String
)
