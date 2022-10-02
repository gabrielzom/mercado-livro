package com.mercadolivro.controller

import com.mercadolivro.controller.request.dto.CustomerCreateOrUpdateDto
import com.mercadolivro.controller.response.dto.CustomerCreatedOrUpdatedDtoDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import kotlin.collections.List
import java.util.Date

@RequestMapping("/customer")
@RestController
class CustomerController {

    @Autowired
    val customerService: CustomerService = CustomerService()

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    fun get(@RequestParam fullName: String, @RequestParam email: String): List<CustomerModel> {
        return customerService.get(fullName, email);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): CustomerModel? {
        return customerService.get(id)
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun create(@RequestBody customerCreateOrUpdateDto: CustomerCreateOrUpdateDto): CustomerCreatedOrUpdatedDtoDto {
        return customerService.create(customerCreateOrUpdateDto)
    }

    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Int,
        @RequestBody customerCreateOrUpdateDto: CustomerCreateOrUpdateDto
    ): CustomerModel? {
        return customerService.update(id, customerCreateOrUpdateDto)
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): CustomerModel? {
        return customerService.delete(id)
    }
}