package com.example.demo.controller

import com.example.demo.dto.SaveMyModelRequest
import com.example.demo.dto.UpdateMyModelRequest
import com.example.demo.model.MyModel
import com.example.demo.service.MyModelService
import org.aspectj.weaver.ast.Not
import org.hibernate.annotations.NotFound
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.web.bind.annotation.*

@RestController
class Mycontroller(
        val myModelService: MyModelService,
) {
  @GetMapping("/hello")
  fun hello(): String {
    return "Hello World!"
  }

  @PostMapping("/my-model")
  fun saveMyModel(
          @RequestBody
          request: SaveMyModelRequest,
  ): MyModel {
    return myModelService.save(
            MyModel(
                    name = request.name
            )
    )
  }

  @GetMapping("/my-models")
  fun listMyModels(): List<MyModel> = myModelService.findAll()

  @PatchMapping("/my-model/{id}")
  fun updateMyModles(
          @PathVariable id: Long,
          @RequestBody request: UpdateMyModelRequest
  ): MyModel {
    val myModel: MyModel = myModelService.findById(id)
            ?: throw NotFoundException()

    myModel.name = request.name

    return myModelService.save(myModel)
  }
}