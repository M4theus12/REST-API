package com.example.bankaccount

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ArticlesRepository : MongoRepository <Articles, String>{
    fun findByDocument(document:String): Optional<Articles>
}