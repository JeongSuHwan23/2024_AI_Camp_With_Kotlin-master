package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "my_Model")
data class MyModel(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        var name: String,
)