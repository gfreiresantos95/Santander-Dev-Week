package br.com.santanderdevweek.view

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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import br.com.santanderdevweek.R
import br.com.santanderdevweek.databinding.ActivityMainBinding
import br.com.santanderdevweek.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mMainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
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
            mBinding.tvUserHeader.text = getString(R.string.ola_usuario, conta.cliente.nomeCliente)
            mBinding.tvAgencyHeader.text = getString(R.string.agencia, conta.agencia)
            mBinding.tvAccountHeader.text = getString(R.string.conta_corrente, conta.numeroConta)
            mBinding.tvBalance.text = getString(R.string.saldo, conta.saldo)
            mBinding.tvBalanceLimit.text = getString(R.string.saldo_limite, conta.limite)
            mBinding.tvFinalCard.text = getString(R.string.cartao, conta.cartao.numero)
            mBinding.tvCardDueDate.text =
                getString(R.string.validade_cartao, conta.cartao.dataValidade)
            mBinding.tvCardShippingDate.text =
                getString(R.string.cliente_desde, conta.cartao.dataExpedicao)
        })
    }

    private fun setListeners() {
        mBinding.clBalanceFixedContainer.setOnClickListener {
            if (mBinding.clBalanceExpandableContainer.visibility == View.VISIBLE) {
                beginTransition(mBinding.clBalanceExpandableContainer)
                mBinding.clBalanceExpandableContainer.visibility = View.GONE
                mBinding.ivExpandBalanceIcon.setImageResource(R.drawable.ic_expand_more)
            } else {
                beginTransition(mBinding.clBalanceExpandableContainer)
                mBinding.clBalanceExpandableContainer.visibility = View.VISIBLE
                mBinding.ivExpandBalanceIcon.setImageResource(R.drawable.ic_expand_less)
            }
        }

        mBinding.clFinalCardFixedContainer.setOnClickListener {
            if (mBinding.clFinalCardExpandableContainer.visibility == View.VISIBLE) {
                beginTransition(mBinding.clFinalCardExpandableContainer)
                mBinding.clFinalCardExpandableContainer.visibility = View.GONE
                mBinding.ivExpandFinalCardIcon.setImageResource(R.drawable.ic_expand_more)
            } else {
                beginTransition(mBinding.clFinalCardExpandableContainer)
                mBinding.clFinalCardExpandableContainer.visibility = View.VISIBLE
                mBinding.ivExpandFinalCardIcon.setImageResource(R.drawable.ic_expand_less)
            }
        }
    }

    private fun beginTransition(view: ConstraintLayout) {
        TransitionManager.beginDelayedTransition(
            view, AutoTransition()
        )
    }
}