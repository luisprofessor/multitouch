package com.example.multitouch;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * Created by usuario on 02/07/2015.
 */
public class PizarraFragmentTab extends Fragment {
    // Como es un fragmento debemos buscar las Vistas en este evento
//y no en onCreate()
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {
// Creamos la Vista interna a partir de la clase
// correspondiente
        View v = new UnToqueView(getActivity());
        return v;
    }    // end onCreateView
}