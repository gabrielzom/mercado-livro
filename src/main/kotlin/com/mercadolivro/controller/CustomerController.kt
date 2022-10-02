package com.mercadolivro.controller

import com.mercadolivro.controller.request.dto.CustomerCreateOrUpdateDto
import com.mercadolivro.controller.response.dto.CustomerCreatedDto
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

    val customersModel = mutableListOf<CustomerModel>()
    val adminUser: String = "admin.sales@example.com"

    @Autowired
    val customerService: CustomerService = CustomerService()

    fun customerExist(id: Int): Boolean {
        if (this.customersModel.isEmpty() || id > this.customersModel.size) {
            return false
        }
        return true
    }

    @GetMapping
    fun get(
        @RequestParam fullName: String,
        @RequestParam email: String
    ): List<CustomerModel> {
        return customerService.get(fullName, email);
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): CustomerModel? {
        if (this.customerExist(id)) {
            return customersModel.first { customerModel -> customerModel.id == id }
        }
        return null;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    fun create(@RequestBody customerCreateOrUpdateDto: CustomerCreateOrUpdateDto): CustomerCreatedDto {
        val customerModel = CustomerModel(
            this.customersModel.size + 1,
            customerCreateOrUpdateDto.fullName,
            customerCreateOrUpdateDto.email,
            Date(),
            this.adminUser
        )

        customersModel.add(customerModel)

        return CustomerCreatedDto(this.customersModel.size, customerModel.createdAt, this.adminUser)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Int,
        @RequestBody customerCreateOrUpdateDto: CustomerCreateOrUpdateDto
    ): CustomerModel? {
        if (this.customerExist(id)) {
            customersModel.first{ it.id == id }.let {
                it.fullName = customerCreateOrUpdateDto.fullName
                it.email = customerCreateOrUpdateDto.email
            }
            return customersModel.first{ it.id == id };
        }
        return null;
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): CustomerModel? {
        if (this.customerExist(id)) {
            val customerModel = customersModel.first{ it.id == id }
            customersModel.remove(customerModel)
            return customerModel
        }
        return null;
    }
}