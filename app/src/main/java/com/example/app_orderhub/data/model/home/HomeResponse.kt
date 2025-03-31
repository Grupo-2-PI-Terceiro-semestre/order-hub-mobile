package com.example.app_orderhub.data.model.home

import com.example.app_orderhub.domain.model.Address
import com.example.app_orderhub.domain.model.Enterprise
import com.example.app_orderhub.domain.model.Service

data class EnterpriseResponse(
    val idEmpresa: Int,
    val nomeEmpresa: String,
    val bairro: String,
    val cidade: String,
    val uf: String,
    val urlLogo: String,
    val servicos: List<String>,
    val categoria: String
)

fun List<EnterpriseResponse>.toEnterprises(): List<Enterprise> {
    return this.map { it.toEnterprise() }
}

fun EnterpriseResponse.toEnterprise(): Enterprise {
    return Enterprise(
        idEmpresa,
        nomeEmpresa,
        Address(
            bairro,
            cidade,
            uf
        ),
        listOf(urlLogo),
        servicos.map { Service(it) },
        categoria
    )
}