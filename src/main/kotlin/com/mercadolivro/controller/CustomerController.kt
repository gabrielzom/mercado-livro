package com.mercadolivro.controller

import com.mercadolivro.controller.request.dto.CustomerCreateDto
import com.mercadolivro.controller.response.dto.CustomerCreatedDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.mercadolivro.model.CustomerModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.Date

@RequestMapping("/customers")
@RestController
class CustomerController {
    @GetMapping
    fun get(): CustomerModel {
        return CustomerModel(
            "e1592ae0-4210-11ed-b878-0242ac120002",
            "Gabriel Nascimento",
            "gabriel.nascimento@example.com",
            Date(),
            "admin.sales@example.com"

        )
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun create(@RequestBody customer: CustomerCreateDto): CustomerCreatedDto {
        println(customer)
        return CustomerCreatedDto(
            "e1592ae0-4210-11ed-b878-0242ac120002",
            Date(),
            "admin.sales@example.com"
        )
    }
}