package mx.tecnm.tepic.ladm_u2_practica2_loteria_gustavomarinlemus

import android.animation.ObjectAnimator
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView

class Hilo(este: MainActivity,este2 : TextView) : Thread() {
     var pausar = false
     var ejecutar = true
     var este = este
     var este2 = este2
     var i = 0

    override fun run() {
        super.run()
        sleep(2500L)
        while(ejecutar){
            if(!pausar){
                este.runOnUiThread() {
                    este2.text= "Numero cartas: ${i++}/54"
                    pausarHilo()
                }
            }
            sleep(300L)
        }
    }
    fun terminarHilo(){
        ejecutar = false
    }
    fun pausarHilo(){
        pausar = true
    }
    fun despausar (){
        pausar = false
    }
    fun estaPausado() : Boolean{
        return pausar
    }
}