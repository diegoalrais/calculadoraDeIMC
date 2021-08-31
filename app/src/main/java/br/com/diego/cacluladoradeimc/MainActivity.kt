package br.com.diego.cacluladoradeimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import br.com.diego.cacluladoradeimc.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btn_calcular = binding.btnCalcular
        val mensagem = binding.txtvMensagem

        btn_calcular.setOnClickListener {
            val edtPeso= binding.edtPeso.text.toString()
            val edtAltura = binding.edtAltura.text.toString()

            if(edtPeso .isEmpty()){
                mensagem.setText("Informe o seu Peso")
            } else if(edtAltura.isEmpty()){
                mensagem.setText("Informe a sua Altura")
            } else {
                calculoDeIMC()
            }
        }

    }

    private fun calculoDeIMC() {
        val pesoId = binding.edtPeso
        val alturaId = binding.edtAltura

        val peso = Integer.parseInt(pesoId.text.toString())
        val altura = java.lang.Float.parseFloat(alturaId.text.toString())
        val resultado = binding.txtvMensagem

        val imc = peso/ (altura * altura)

        val Mensagem = when{
            imc <= 18.5 -> "Peso Baixo"
            imc <= 24.9 -> "Peso Normal"
            imc <= 29.9-> "Sobrepeso"
            imc <= 34.9 -> "Obesidade (Grau 1)"
            imc <= 39.9 -> "Obesidade (Grau 2)"
            else -> "Obesidade Mórbida (Grau 3)"
        }

        //Para formatar o número de casas decimais
        val casasDecimais =  DecimalFormat ("0.00")

        imc.toString()
        resultado.setText("IMC: ${casasDecimais.format(imc)} \n $Mensagem")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.it_reset -> {
                val limparEditPeso = binding.edtPeso
                val limparEditAlturra = binding.edtAltura
                val limparMensagem = binding.txtvMensagem

                limparEditPeso.setText("")
                limparEditAlturra.setText("")
                limparMensagem.setText("")
            }
            R.id.it_close -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}