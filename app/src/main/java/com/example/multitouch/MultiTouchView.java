package com.example.multitouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by usuario on 02/07/2015.
 */
// Clase de tipo Vista que muestra circulos cuando
// el usuario toca la pantalla con varios dedos a la vez
public class MultiTouchView extends View {
    // Radio de los cjrculos
    private static final int RADIO = 50;
    // Matriz que guarda los puntos de la pantalla
    private SparseArray<PointF> puntosActivos;
    // Pincel para dibujar
    private Paint paint;
    // Matriz con colores para dibujar circulos


    private int[] colores = { Color.GREEN, Color.BLUE, Color.RED,
            Color.MAGENTA, Color.BLACK, Color.CYAN, Color.GRAY,
            Color.DKGRAY, Color.LTGRAY, Color.YELLOW };
    // Constructor de la clase
    public MultiTouchView(Context context) {
        super(context);
// Creamos la matriz de puntos
        puntosActivos = new SparseArray<PointF>();
// Inicializamos el pincel
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
// Indicamos que se debe rellenar todo lo que dibuja
// para que el c�rculo se pinte por dentro
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    } // end constructor
    // Evento que se lanza cuando es necesario dibujar la Vista
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
// Dibujamos todos los puntos en circulo con un bucle
        int size = puntosActivos.size();
        for (int i = 0; i < size; i++) {
// Obtenemos el punto i
            PointF punto = puntosActivos.valueAt(i);
// Si obtenemos el punto...
            if (punto != null)
// Elegimos un color
                paint.setColor(colores[i % 9]);
// Dibujamos el c�rculo
            canvas.drawCircle(punto.x, punto.y, RADIO, paint);
        } // end for i
    } // end onDraw()
    // Evento que salta cuando el usuario toca la pantalla
    @Override
    public boolean onTouchEvent(MotionEvent event) {
// Mostramos el contenido del evento
        Log.d("Debug", event.toString());
// Obtenemos el �ndice del punto del objeto del evento
        int pointerIndex = (event.getAction() &
                MotionEvent.ACTION_POINTER_INDEX_MASK) >>
                MotionEvent.ACTION_POINTER_INDEX_SHIFT;
// Obtenemos el ID del punto
        int pointerId = event.getPointerId(pointerIndex);
// Obtenemos la m�scara de la acci�n
        int mascaraAction = event.getAction() &
                MotionEvent.ACTION_MASK;
        switch (mascaraAction) {
// En el caso de que el usuario tenga el dedo en la pantalla

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
// Cuando aparece un nuevo punto entonces lo guardamos en
// la matriz
                PointF punto = new PointF();
                punto.x = event.getX(pointerIndex);
                punto.y = event.getY(pointerIndex);
                puntosActivos.put(pointerId, punto);
                break;
            }
// Si se mueve el dedo
            case MotionEvent.ACTION_MOVE: {
// Recorremos todos los puntos y actualizamos su nueva
// posici�n
                for (int size = event.getPointerCount(), i = 0; i < size;
                     i++) {
                    PointF punto = puntosActivos.get(event.getPointerId(i));
                    if (punto != null) {
                        punto.x = event.getX(i);
                        punto.y = event.getY(i);
                    }
                }
                break;
            }
// Si el dedo se levanta o se cancela
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL: {
// Quitamos el punto activo
                puntosActivos.remove(pointerId);
                break;
            }
        } // end switch
// Redibujamos la Vista
        invalidate();
// Indicamos que gestionamos el evento
        return true;
    } // end onTouchEvent
} // end clase