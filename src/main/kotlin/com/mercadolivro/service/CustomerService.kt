package com.mercadolivro.service

import com.mercadolivro.controller.request.dto.CustomerCreateOrUpdateDto
import com.mercadolivro.controller.response.dto.CustomerCreatedOrUpdatedDtoDto
import com.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService {

    val customersModel = mutableListOf<CustomerModel>()
    val adminUser: String = "admin.sales@example.com"

    fun customerExist(id: Int): Boolean {
        if (this.customersModel.isEmpty() || id > this.customersModel.size) {
            return false
        }
        return true
    }

    fun get(fullName: String, email: String): List<CustomerModel> {
        if (fullName != "" && email != "") {
            fullName.let {
                return this.customersModel.filter {
                    it.fullName.contains(fullName, true) && it.email.contains(email, true)
                }
            }
        }

        if (fullName != "" ) {
            fullName.let {
                return this.customersModel.filter { it.fullName.contains(fullName, true) }
            }
        }

        if (email != "" ) {
            email.let {
                return this.customersModel.filter { it.fullName.contains(email, true) }
            }
        }

        return this.customersModel
    }

    fun get(id: Int): CustomerModel? {
        if (this.customerExist(id)) {
            return customersModel.first { customerModel -> customerModel.id == id }
        }
        return null
    }

    fun create(customerCreateOrUpdateDto: CustomerCreateOrUpdateDto): CustomerCreatedOrUpdatedDtoDto {
        val customerModel = CustomerModel(
            this.customersModel.size + 1,
            customerCreateOrUpdateDto.fullName,
            customerCreateOrUpdateDto.email,
            Date(),
            this.adminUser
        )

        customersModel.add(customerModel)

        return CustomerCreatedOrUpdatedDtoDto(this.customersModel.size, customerModel.createdAt, this.adminUser)
    }

    fun update(id: Int, customerCreateOrUpdateDto: CustomerCreateOrUpdateDto): CustomerModel? {
        if (this.customerExist(id)) {
            customersModel.first{ it.id == id }.let {
                it.fullName = customerCreateOrUpdateDto.fullName
                it.email = customerCreateOrUpdateDto.email
            }
            return customersModel.first{ it.id == id }
        }
        return null
    }

    fun delete(id: Int): CustomerModel? {
        if (this.customerExist(id)) {
            val customerModel = customersModel.first{ it.id == id }
            customersModel.remove(customerModel)
            return customerModel
        }
        return null
    }
}