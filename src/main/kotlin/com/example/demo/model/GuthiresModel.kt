package com.example.demo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class GuthiresModel(@Id val id:String,
                         val productsList: List<Products>)

data class GuthriesAddress(val id: String)

data class DeliveryAddress(val address:String)

data class OrderAddress(val id: String,val orderAdd:String)

@Document
data class Products(@Id var id  :String, val supplierCode : String,
                    val productName:String, val quantity: String, val price :String, val value: String  )
