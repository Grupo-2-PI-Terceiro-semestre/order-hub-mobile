package com.example.app_orderhub.data

data class Enterprise (
    val nomeEmpresa: String,
    val endereco: Address,
    val imagens : List<String>,
    val servicos : List<Service>,
    val proficionais : List<String>
){
    constructor() : this(
        nomeEmpresa = "",
        endereco = Address(),
        imagens = listOf(),
        servicos = listOf(),
        proficionais = listOf()
    )

    constructor(nomeEmpresa: String, endereco: Address, servicos : List<Service>, proficionais: List<String>) : this(
        nomeEmpresa = nomeEmpresa,
        endereco = endereco,
        imagens = listOf(),
        servicos = servicos,
        proficionais = proficionais
    )
}

