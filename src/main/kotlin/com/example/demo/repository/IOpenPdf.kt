package com.example.demo.repository

import com.example.demo.model.GuthiresModel
import org.springframework.data.mongodb.repository.MongoRepository

interface IOpenPdf : MongoRepository<GuthiresModel,String> {
}
