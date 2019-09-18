package com.example.multitouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by usuario on 02/07/2015.
 */
// Clase de tipo Vista que simula una pizarra
public class UnToqueView extends View {
    // Pincel que usamos para pintar
    private Paint paint = new Paint();
    // Camino que guarda el trazo del dibujo
    private Path path = new Path();
    // Constructor de la clase
    public UnToqueView(Context context) {
        super(context);
// Definimos el pincel de dibujo con el
// borde suave, de ancho 6, color negro,
// trazo s�lido y uniones redondeadas
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    } // end constructor
    // Evento que se lanza cuando es necesario dibujar la Vista
    @Override
    protected void onDraw(Canvas canvas) {
// Dibujamos el camino
        canvas.drawPath(path, paint);
    }
    // Evento que salta cuando el usuario toca la pantalla
    @Override
    public boolean onTouchEvent(MotionEvent event) {
// Obtenemos la posici�n del toque
        float eventX = event.getX();
        float eventY = event.getY();

// En funci�n del tipo de toque
        switch (event.getAction()) {
// Si el dedo toca la pantalla
            case MotionEvent.ACTION_DOWN:
// Movemos el camino a la posici�n X, Y
                path.moveTo(eventX, eventY);
                return true;
// Si el dedo se mueve
            case MotionEvent.ACTION_MOVE:
// Creamos el camino hasta X, Y mediante una l�nea
                path.lineTo(eventX, eventY);
                break;
// Si se levanta el dedo
            case MotionEvent.ACTION_UP:
// No hacemos nada
                break;
            default:
                return false;
        } // end case
// Indicamos que se debe redibujar la Vista
        invalidate();
        return true;
    } // end onTouchEvent
} // end clase