package com.example.multitouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

/**
 * Created by usuario on 02/07/2015.
 */
// Clase de tipo Vista que permite escalar una imagen
public class ZoomImageView extends View {
    // Imagen que aparece en la pantalla
    private Drawable imagen;
    // Factor de escalado
    private float factorEscalado = 1.0f;
    // Clase usada para detectar cambios de escala
    private ScaleGestureDetector scaleGestureDetector;
    // Constructor de la clase
    public ZoomImageView(Context context) {

super(context);
// Obtenemos la imagen que es el icono de la aplicaci�n
        imagen = context.getResources().getDrawable(
        R.drawable.logo);
// Indicamos que esta Vista puede obtener el foco de la
// aplicaci�n
        setFocusable(true);
// Establecemos los bordes de la imagen
        imagen.setBounds(0, 0, imagen.getIntrinsicWidth(),
        imagen.getIntrinsicHeight());
// Definimos el detector de cambios de escala con su
// listener correspondiente
        scaleGestureDetector = new ScaleGestureDetector(context, new
        ScaleListener());
        } // end constructor
// Evento que se lanza cuando es necesario dibujar la Vista
@Override
protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
// Guarda el estado del lienzo. As� evitamos que parpadee
        canvas.save();
// Escalamos el lienzo en Ancho y Alto
        canvas.scale(factorEscalado, factorEscalado);

// Dibujamos la imagen
        imagen.draw(canvas);
// Restauramos el lienzo
        canvas.restore();
        } // end onDraw()
// Evento que salta cuando el usuario toca la pantalla
@Override
public boolean onTouchEvent(MotionEvent event) {
// Pasamos el evento al detector de escala
        scaleGestureDetector.onTouchEvent(event);
// Redibujamos la Vista
        invalidate();
        return true;
        }
// Listener del ScaleGestureDetector
private class ScaleListener extends
        ScaleGestureDetector.SimpleOnScaleGestureListener {
    // Si se lanza el evento onScale...
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
// El nuevo factor de escalado es el anterior
// multiplicado por el valor de escala del evento
        factorEscalado *= detector.getScaleFactor();
// No dejamos que el objeto sea muy peque�o o muy grande.
        factorEscalado = Math.max(0.2f, Math.min(factorEscalado,
                5.0f));
// Indicamos que se debe redibujar la Vista
        invalidate();
        return true;
    } // end onScale()
} // end ScaleListener
} // end clase