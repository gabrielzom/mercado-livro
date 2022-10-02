package com.mercadolivro.controller.response.dto

import java.util.Date

data class CustomerCreatedDto(
    var generatedUuid: String,
    var createdAt: Date,
    var createdBy: String
)
