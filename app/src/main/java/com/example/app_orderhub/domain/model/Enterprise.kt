package com.example.app_orderhub.domain.model

data class Enterprise(
    val idEmpresa : Int,
    val nomeEmpresa: String,
    val endereco: Address,
    val imagens: List<String>,
    val servicos: List<Service>,
    val categoria: String,
    val proficionais: List<Professional>
) {
    constructor() : this(
        idEmpresa = 0,
        nomeEmpresa = "",
        endereco = Address(),
        imagens = listOf(),
        servicos = listOf(),
        categoria = "",
        proficionais = listOf()
    )

    constructor(
        idEmpresa: Int,
        nomeEmpresa: String,
        endereco: Address,
        servicos: List<Service>,
        proficionais: List<Professional>
    ) : this(
        idEmpresa = idEmpresa,
        nomeEmpresa = nomeEmpresa,
        endereco = endereco,
        imagens = listOf(),
        servicos = servicos,
        categoria = "",
        proficionais = proficionais
    )

    constructor(
        idEmpresa: Int,
        nomeEmpresa: String,
        endereco: Address,
        imagens: List<String>,
        servicos: List<Service>,
        categoria: String
    ) : this(
        idEmpresa = idEmpresa,
        nomeEmpresa = nomeEmpresa,
        endereco = endereco,
        imagens = imagens,
        servicos = servicos,
        categoria = categoria,
        proficionais = listOf()
    )

    constructor(
        nomeEmpresa: String,
        endereco: Address,
        imagens: List<String>,
        servicos: List<Service>,
        proficionais : List<Professional>
    ) : this(
        idEmpresa = 0,
        nomeEmpresa = nomeEmpresa,
        endereco = endereco,
        imagens = imagens,
        servicos = servicos,
        categoria = "",
        proficionais = proficionais
    )
}

data class Imagems(
    val urlImagem: String
)

