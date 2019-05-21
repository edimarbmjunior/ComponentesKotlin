package com.devediapp.componeteskotlin.util

class MockData private constructor(){

    companion object{
        fun getListCompanionObject() : List<String> = listOf("Gramas", "Kilo", "Arroba", "Tonelada")
    }

    object TESTE {
        fun getListObject() : List<String> = listOf("Gramas", "Kilo", "Arroba", "Tonelada")
    }
}