package mx.tecnm.tepic.ladm_u2_practica2_loteria_gustavomarinlemus

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.*
import mx.tecnm.tepic.ladm_u2_practica2_loteria_gustavomarinlemus.databinding.ActivityMainBinding
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var hilo : Hilo
    var mediaPlayer: MediaPlayer? = null
    var contadorGlobal = 0
    var currentProgress = 0
    var pausar = false
    lateinit var  corrutina : CoroutineContext
    lateinit var scope : CoroutineScope
    var color = Color.rgb(128,128,128)
    val cartas = arrayOf(Carta("El Gallo",R.drawable.carta1,R.raw.elgallo),Carta("El diablito",R.drawable.carta2,R.raw.eldiablito),
        Carta("La dama",R.drawable.carta3,R.raw.ladama),Carta("El catrin",R.drawable.carta4,R.raw.elcatrin),Carta("El paraguas",R.drawable.carta5,R.raw.elparaguas),
        Carta("La sirena",R.drawable.carta6,R.raw.lasirena),Carta("La escalera",R.drawable.carta7,R.raw.laescalera),Carta("La botella",R.drawable.carta8,R.raw.labotella),
        Carta("El barril",R.drawable.carta9,R.raw.elbarril),Carta("El arbol",R.drawable.carta10,R.raw.elarbol),Carta("El melon",R.drawable.carta11,R.raw.elmelon),
        Carta("El valiente",R.drawable.carta12,R.raw.elvaliente),Carta("El gorrito",R.drawable.carta13,R.raw.elgorrito),Carta("La muerte",R.drawable.carta14,R.raw.lamuerte),
        Carta("La pera",R.drawable.carta15,R.raw.lapera),Carta("La bandera",R.drawable.carta16,R.raw.labandera),Carta("El bandolon",R.drawable.carta17,R.raw.elbandolon),
        Carta("El violoncello",R.drawable.carta18,R.raw.elvioloncello),Carta("La garza",R.drawable.carta19,R.raw.lagarza),Carta("El pajaro",R.drawable.carta20,R.raw.elpajaro),
        Carta("La mano",R.drawable.carta21,R.raw.lamano),Carta("La bota",R.drawable.carta22,R.raw.labota),Carta("La luna",R.drawable.carta23,R.raw.laluna),
        Carta("El cotorro", R.drawable.carta24,R.raw.elcotorro),Carta("El borracho",R.drawable.carta25,R.raw.elborracho),Carta("El negrito",R.drawable.carta26,R.raw.elnegrito),
        Carta("El corazon",R.drawable.carta27,R.raw.elcorazon),Carta("La sandia",R.drawable.carta28,R.raw.lasandia),Carta("El tambor",R.drawable.carta29,R.raw.eltambor),
        Carta("El camaron",R.drawable.carta30,R.raw.elcamaron),Carta("Las jaras",R.drawable.carta31,R.raw.lasjaras),Carta("El musico",R.drawable.carta32,R.raw.elmusico),
        Carta("La araña",R.drawable.carta33,R.raw.laarana),Carta("El soldado",R.drawable.carta34,R.raw.elsoldado),Carta("La estrella",R.drawable.carta35,R.raw.laestrella),
        Carta("El cazo",R.drawable.carta36,R.raw.elcaso),Carta("El mundo",R.drawable.carta37,R.raw.elmundo),Carta("El apache",R.drawable.carta38,R.raw.elapache),
        Carta("El nopal",R.drawable.carta39,R.raw.elnopal),Carta("El alacran",R.drawable.carta40,R.raw.elalacran),Carta("La rosa",R.drawable.carta41,R.raw.larosa),
        Carta("La calavera",R.drawable.carta42,R.raw.lacalavera),Carta("La campana",R.drawable.carta43,R.raw.lacampana),Carta("El cantarito",R.drawable.carta44,R.raw.elcantarito),
        Carta("El venado",R.drawable.carta45,R.raw.elvenado),Carta("El sol",R.drawable.carta46,R.raw.elsol),Carta("La corona",R.drawable.carta47,R.raw.lacorona),
        Carta("La chalupa",R.drawable.carta48,R.raw.lachalupa),Carta("El pino",R.drawable.carta49,R.raw.elpino),Carta("El pescado",R.drawable.carta50,R.raw.elpescado),
        Carta("La palma",R.drawable.carta51,R.raw.lapalma),Carta("La maceta",R.drawable.carta52,R.raw.lamaceta),Carta("El arpa",R.drawable.carta53,R.raw.elarpa),
        Carta("La rana",R.drawable.carta54,R.raw.larana))

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //CREAMOS HILO
        hilo = Hilo(this,binding.textoContador)

        binding.btnIniciar.setOnClickListener {
                //SEVAYSECORRECON
                mediaPlayer = MediaPlayer.create(this, R.raw.sevaysecorrecon)
                mediaPlayer!!.start()
                //BARAJAMOS LAS CARTAS
                cartas[0].barajar(cartas)
                binding.ImgCartaMostrada.setImageResource(R.drawable.blanco)
                binding.carta1.setImageResource(R.drawable.blanco)
                binding.carta2.setImageResource(R.drawable.blanco)
                binding.carta3.setImageResource(R.drawable.blanco)
                binding.carta4.setImageResource(R.drawable.blanco)
                contadorGlobal = 0
            ObjectAnimator.ofInt(binding.progressbar,"Progress",currentProgress)
                .setDuration(0L)
                .start()
            //EMPEZAMOS LA CORRUTINA


            scope = CoroutineScope(Job() + Dispatchers.Main)
            corrutina = scope.launch(EmptyCoroutineContext, CoroutineStart.LAZY) {
                delay(2500L)
                binding.progressbar.max = 1000
                while (contadorGlobal < 54) {
                    if(!pausar) {
                            pausar = false
                            hilo.despausar()
                            runOnUiThread() {
                                var currentProgress = 1000
                                ObjectAnimator.ofInt(
                                    binding.progressbar,
                                    "Progress",
                                    currentProgress
                                )
                                    .setDuration(2000L)
                                    .start()
                                cartas[contadorGlobal].cambioDeCarta(
                                    binding.ImgCartaMostrada,
                                    binding.carta4,
                                    binding.carta3,
                                    binding.carta2,
                                    binding.carta1,
                                    contadorGlobal,
                                    cartas
                                )
                                cartas[contadorGlobal].reproduceAudio(this@MainActivity)
                            }
                            delay(2000L)
                            cartas[contadorGlobal].mediaPlayer = null
                            contadorGlobal++
                            currentProgress = 0
                            ObjectAnimator.ofInt(binding.progressbar, "Progress", currentProgress)
                                .setDuration(0L)
                                .start()
                    }
                }
            }
            (corrutina as Job).start()

            if(pausar){
                hilo.despausar()
                pausar = false
            }else {
                hilo.start()
            }
        }
        binding.btnLoteria.setOnClickListener {
            pausar = true
            hilo.pausarHilo()
            hilo.i = 0
            corrutina.cancel()
           mediaPlayer = MediaPlayer.create(this,R.raw.loteria)
            mediaPlayer!!.start()
        }
        binding.btnVerificar.setOnClickListener{
            var cadena = ""
            for (i in contadorGlobal+1..53){
                cadena += cartas[i].nombreCarta
                cadena += "\n"
            }
            AlertDialog.Builder(this)
                .setTitle("CARTAS RESTANTES")
                .setMessage(cadena)
                .setPositiveButton("¡FELICIDADES!",{d,i->d.dismiss()})
                .show()
        }
    }
}



