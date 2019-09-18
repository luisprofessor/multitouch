package com.example.multitouch;

import android.os.Bundle;
;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTabHost;


public class MainActivity extends FragmentActivity {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
// Buscamos el contenedor de Tabs de la interfaz de usuario en
// el archivo activity_main.xml
            FragmentTabHost mTabHost = (FragmentTabHost)
                    findViewById(android.R.id.tabhost);
// Configuramos el frame que contendrá el contenido de la
// pestaña
            mTabHost.setup(this, getSupportFragmentManager(),android.R.id.tabcontent);
// Añadimos las pestañas que son fragmentos
            mTabHost.addTab(
                    mTabHost.newTabSpec("tab1").setIndicator("Pizarra",getResources().getDrawable(R.drawable.pizarra)),PizarraFragmentTab.class, null);
            mTabHost.addTab(
                    mTabHost.newTabSpec("tab2").setIndicator("Multitouch", getResources().getDrawable(R.drawable.touch)),MultiTouchFragmentTab.class, null);
            mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("Cambio Tamaño", getResources().getDrawable(R.drawable.multitouch)), ResizeFragmentTab.class, null);
        } // end onCreate()
    } // end clase


