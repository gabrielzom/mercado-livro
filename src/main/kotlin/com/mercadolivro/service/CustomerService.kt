package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerService {

    val customersModel = mutableListOf<CustomerModel>()
    val adminUser: String = "admin.sales@example.com"

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
}