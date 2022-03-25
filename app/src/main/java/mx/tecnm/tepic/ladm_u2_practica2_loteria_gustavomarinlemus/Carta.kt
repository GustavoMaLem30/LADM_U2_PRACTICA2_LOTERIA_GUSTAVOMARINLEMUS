package mx.tecnm.tepic.ladm_u2_practica2_loteria_gustavomarinlemus

import android.media.MediaPlayer
import android.widget.ImageView

class Carta(nombreCarta : String,img : Int, audio : Int ) {
    var nombreCarta = nombreCarta
    var img = img
    var audio = audio
    var mediaPlayer: MediaPlayer? = null


    fun barajar(cartas : Array<Carta>) {
        cartas.shuffle()
    }
    fun cambioDeCarta(img1 : ImageView,img2 : ImageView,img3 : ImageView,
                      img4 : ImageView,img5 : ImageView,contador : Int,cartas : Array<Carta> ){
        when(contador){
            0 -> img1.setImageResource(img)
            1 -> {img1.setImageResource(img);img2.setImageResource(cartas[contador-1].img)}
            2 -> {img1.setImageResource(img);img2.setImageResource(cartas[contador-1].img);img3.setImageResource(cartas[contador-2].img)}
            3 -> {img1.setImageResource(img);img2.setImageResource(cartas[contador-1].img);img3.setImageResource(cartas[contador-2].img)
                img4.setImageResource(cartas[contador-3].img)}
            else -> {img1.setImageResource(img);img2.setImageResource(cartas[contador-1].img);img3.setImageResource(cartas[contador-2].img)
                img4.setImageResource(cartas[contador-3].img); img5.setImageResource(cartas[contador-4].img)}
        }
    }
    fun reproduceAudio(ActividadPrincipal : MainActivity){
        mediaPlayer = MediaPlayer.create(ActividadPrincipal,audio)
        mediaPlayer!!.start()
    }
}


