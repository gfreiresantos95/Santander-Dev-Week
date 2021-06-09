package br.com.santanderdevweek.data.local

import br.com.santanderdevweek.data.Cartao
import br.com.santanderdevweek.data.Cliente
import br.com.santanderdevweek.data.Conta

class Mockup {

    fun getConta(): Conta = Conta(
        getCliente(),
        "0123",
        "01234567–8",
        "1.234,56",
        "123,45",
        getCartao()
    )

    private fun getCliente(): Cliente = Cliente("Gabriel")

    private fun getCartao(): Cartao = Cartao("0123 4567 8901 2345", "08/2027", "08/2017")
}