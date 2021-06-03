package br.com.santanderdevweek.data

data class Conta(
    val cliente: Cliente,
    val agencia: String,
    val numeroConta: String,
    val saldo: String,
    val limite: String,
    val cartao: Cartao
)
