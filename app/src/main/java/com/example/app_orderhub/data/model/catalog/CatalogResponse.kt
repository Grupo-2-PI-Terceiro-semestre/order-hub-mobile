package com.example.app_orderhub.data.model.catalog

import com.example.app_orderhub.domain.model.Address
import com.example.app_orderhub.domain.model.Enterprise
import com.example.app_orderhub.domain.model.Imagems
import com.example.app_orderhub.domain.model.Professional
import com.example.app_orderhub.domain.model.Service

data class CatalogResponse(
    val nomeEmpresa: String,
    val endereco: Address,
    val imagems: List<Imagems>,
    val servicos: List<Service>,
    val usuario: List<Professional>
)

fun CatalogResponse.toEnterprise(idEnterprise: Int): Enterprise {
    return Enterprise(
        idEmpresa = idEnterprise,
        nomeEmpresa,
        endereco,
        imagems.map { it.urlImagem },
        servicos.map { it.toDomainModel() },
        usuario.map { it.toDomainModel() }
    )
}
