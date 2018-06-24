package hag.com.hardartgram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class PerfilArtista  extends AppCompatActivity {

    ImageView perfilfoto;
    TextView nombre;
    TextView edad;
    TextView ciudad;
    TextView bio;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        Intent intent = getIntent();
         //  UserProfile = (RetrieveUser) intent.getSerializableExtra("UserProfile");
         Authores  artista = (Authores) intent.getSerializableExtra("Perfil");


         perfilfoto = (ImageView) findViewById(R.id.imagenperfil);
         nombre = (TextView) findViewById(R.id.nombreartista);
         edad = (TextView) findViewById(R.id.edadartista);
         ciudad = (TextView) findViewById(R.id.ciudad);
         bio = (TextView)  findViewById(R.id.descriptionartista);


         Url_Image_LoaderTask LoadTask = new Url_Image_LoaderTask((String) intent.getSerializableExtra("foto"));
         try {
                Bitmap map = LoadTask.execute().get();
                perfilfoto.setImageBitmap(map);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            nombre.setText((CharSequence) intent.getSerializableExtra("nombre"));
            edad.setText((CharSequence) intent.getSerializableExtra("edad"));
            ciudad.setText((CharSequence) intent.getSerializableExtra("ciudad"));
            bio.setText((CharSequence) intent.getSerializableExtra("bio"));







    }
}
