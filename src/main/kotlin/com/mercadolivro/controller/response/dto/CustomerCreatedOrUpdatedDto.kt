package com.mercadolivro.controller.response.dto

import java.util.Date

data class CustomerCreatedOrUpdatedDtoDto(
    var generatedId: Int,
    var createdAt: Date,
    var createdBy: String
)
