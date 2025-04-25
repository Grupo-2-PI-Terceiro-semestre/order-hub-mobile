package com.example.app_orderhub.domain.model

data class Address(
    val idEndereco: Int,
    val logradouro: String,
    val cidade: String,
    val bairro: String,
    val uf: String,
    val cep: String,
    val numero: String,
    val complemento: String,
    val lat : String,
    val lng : String
) {
    constructor() : this(
        idEndereco = 0,
        logradouro = "",
        cidade = "",
        bairro = "",
        uf = "",
        cep = "",
        numero = "",
        complemento = "",
        lat = "",
        lng = ""
    )

    constructor(
        logradouro: String,
        cidade: String,
        uf: String,
        cep: String,
        numero: String
    ) : this(
        idEndereco = 0,
        logradouro = logradouro,
        cidade = cidade,
        bairro = "",
        uf = uf,
        cep = cep,
        numero = numero,
        complemento = "",
        lat = "",
        lng = ""
    )

    constructor(
        logradouro: String,
        cidade: String,
        bairro: String,
        uf: String,
        cep: String,
        numero: String
    ) : this(
        idEndereco = 0,
        logradouro = logradouro,
        cidade = cidade,
        bairro = bairro,
        uf = uf,
        cep = cep,
        numero = numero,
        complemento = "",
        lat = "",
        lng = ""
    )

    constructor(
        bairro: String,
        cidade: String,
        uf: String
    ) : this(
        idEndereco = 0,
        logradouro = "",
        cidade = cidade,
        bairro = bairro,
        uf = uf,
        cep = "",
        numero = "",
        complemento = "",
        lat = "",
        lng = ""
    )
}
