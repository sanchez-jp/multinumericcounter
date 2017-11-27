package com.juanpedrosanchez.contadormultinumerico;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NumberPickerFragment.OnFragmentInteractionListener{


    private static final String APP = "Contador Multinumérico";
    private static final String NUMBER = "com.juanpedrosanchez.contadormultinumerico.number";
    private TextView tv_number;

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_number = (TextView) findViewById(R.id.tv_number);
        mp = MediaPlayer.create(this, R.raw.mario_bros_level_1_2);


        if(savedInstanceState != null) {
            tv_number.setText(savedInstanceState.getString(NUMBER));
        }

        loadStartFragment();
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(APP," Comienzo");
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new NumberPickerFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(APP," Aplicación en pausa");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(APP," Aplicación detenida");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(APP," Destrucción de la aplicación");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(NUMBER,tv_number.getText().toString());
    }

    /**
     * Suma al valor del TextView (numérico) el valor seleccionado en el DatePicker del fragment
     */
    public void sumar(Integer number){
        Integer num = Integer.parseInt(tv_number.getText().toString()) + number;
        tv_number.setText(num.toString());
        mp.start();
    }

    public void reset(){
        tv_number.setText("0");
    }

    /**
     * Carga el fragmento NumberPickerFragment con el DatePicker
     */
    private void loadStartFragment() {
        getFragmentManager().beginTransaction().add(R.id.fragment_container, new NumberPickerFragment())
                .commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
