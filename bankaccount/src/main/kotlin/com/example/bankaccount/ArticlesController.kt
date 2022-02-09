package com.example.bankaccount

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sun.jvm.hotspot.oops.CellTypeState.value

@RestController
@RequestMapping

class ArticlesController ( val repository : ArticlesRepository) {

    @PostMapping
    fun create(@RequestBody articles: Articles): ResponseEntity<Articles> = ResponseEntity.ok(repository.save(articles))

       @GetMapping("/")
       fun read() = ResponseEntity.ok(repository.findAll())

        @PutMapping("{document}")
        fun update(@PathVariable document: String , @RequestBody articles: Articles): ResponseEntity<Articles> {
            val articlesDBOpitional = repository.findByDocument(document)
            val ToSave = articlesDBOpitional
                .orElseThrow { RuntimeException("Account document: $document not found") }
            .copy(id = articles.id, title = articles.title)
            return ResponseEntity.ok(ToSave)

        }

    @DeleteMapping("{document}")
    fun delete (@PathVariable document : String ) :Unit = repository
        .findByDocument(document)
        .ifPresent{repository.delete(it)}

}