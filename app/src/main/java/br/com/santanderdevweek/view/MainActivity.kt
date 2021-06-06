package br.com.santanderdevweek.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.santanderdevweek.R
import br.com.santanderdevweek.viewmodel.MainViewModel
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    private lateinit var mMainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.main_toolbar))

        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        getData()
        setListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_notificacoes -> {
                Toast.makeText(this, "${item.title} foi clicado", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun getData() {
        mMainViewModel.buscarConta().observe(this, { conta ->
            findViewById<TextView>(R.id.tv_user_header).text =
                getString(R.string.ola_usuario, conta.cliente.nomeCliente)

            findViewById<TextView>(R.id.tv_agency_header).text =
                getString(R.string.agencia, conta.agencia)

            findViewById<TextView>(R.id.tv_account_header).text =
                getString(R.string.conta_corrente, conta.numeroConta)

            findViewById<TextView>(R.id.tv_balance).text =
                getString(R.string.saldo, conta.saldo)

            findViewById<TextView>(R.id.tv_balance_limit).text =
                getString(R.string.saldo_limite, conta.limite)

            findViewById<TextView>(R.id.tv_final_card).text =
                getString(R.string.cartao_final, conta.cartao.numero)

            findViewById<TextView>(R.id.tv_card_due_date).text =
                getString(R.string.validade_cartao, conta.cartao.dataValidade)

            findViewById<TextView>(R.id.tv_card_shipping_date).text =
                getString(R.string.cliente_desde, conta.cartao.dataExpedicao)
        })
    }

    private fun setListeners() {
        findViewById<ConstraintLayout>(R.id.cl_final_card_fixed_container).setOnClickListener {
            if (findViewById<ConstraintLayout>(R.id.cl_final_card_expandable_container).visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(
                    findViewById<ConstraintLayout>(R.id.cl_final_card_expandable_container),
                    AutoTransition()
                )

                findViewById<ConstraintLayout>(R.id.cl_final_card_expandable_container).visibility =
                    View.GONE

                findViewById<ImageView>(R.id.iv_expand_final_card_icon).setImageResource(R.drawable.ic_expand_more)
            } else {
                TransitionManager.beginDelayedTransition(
                    findViewById<ConstraintLayout>(R.id.cl_final_card_expandable_container),
                    AutoTransition()
                )

                findViewById<ConstraintLayout>(R.id.cl_final_card_expandable_container).visibility =
                    View.VISIBLE

                findViewById<ImageView>(R.id.iv_expand_final_card_icon).setImageResource(R.drawable.ic_expand_less)
            }
        }
    }
}