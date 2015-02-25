package com.example.kdiaziglesias.ejemploprogresobar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import  java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    int progreso;//Guardará un numero entero que será el numero de segundos que ha pasado desde que hemos inicializado la aplicación.
    ProgressBar barraProgreso;//Declaración de la barra de progreso.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barraProgreso = (ProgressBar)findViewById(R.id.progressBar);//Relacionamos el la barra de progreso del layout con el de java

        new TareaSegundoPlano().execute();//Iniciamos la tarea en segundo plano, en este caso no necesitamos pasarle nada.
    }

    //Clase interna que extiende de AsyncTask
    public class TareaSegundoPlano extends AsyncTask<Void, Void, Void>{

        //Método que se ejecutará antes de la tarea en segundo plano, normalmente utilizado para iniciar el entorno gráfico
        protected void onPreExecute(){
            barraProgreso.setProgress(0);//Ponemos la barra de progreso a 0
            barraProgreso.setMax(60);//El máximo de la barra de progreso son 60, de los 60 segundo que va a durar la tarea en segundo plano.
        }

        //Este método se ejecutará después y será el que ejecute el código en segundo plano
        @Override
        protected Void doInBackground(Void... params) {
            for(progreso=1;progreso<=60;progreso++){//Creamos un for de 1 a 60 que irá contando los segundos.

                try {
                    Thread.sleep(1000);//Esto lo que hace es ralentizar este proceso un segundo (el tiempo que se pone entre paréntesis es en milisegundos) tiene que ir entre try y catch
                } catch (InterruptedException e) {}

                publishProgress();//Actualizamos el progreso, es decir al llamar a este proceso en realidad estamos llamamos al método onProgresssUpdate()
            }

            return null;//Al llegar aquí, no devolvemos nada y acaba la tarea en segundo plano y se llama al método onPostExecute().
        }

        protected void onProgressUpdate(Void... values) {
            barraProgreso.setProgress(progreso);//Actualizamos la barra de progreso con los segundos que vayan.
        }

        protected void onPostExecute(Void result){//A este método se le llama cada vez que termine la tarea en segundo plano.
            Toast.makeText(MainActivity.this, "Tarea Finalizada", Toast.LENGTH_LONG).show();//Nos muestra una notificación informando de que la tarea en segundo plano ha finalizado
        }
    }
}






   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}*/
