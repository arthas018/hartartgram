package hag.com.hardartgram;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ActividadPrincipal extends AppCompatActivity {

    ListView lista_contenido;
    ArrayList<Authores>  artistas;
    ArrayAdapter arrayAdapter;
    private StorageReference mStorageRef;
   
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        lista_contenido = (ListView) findViewById(R.id.list);
        Button btn = (Button) findViewById(R.id.button);
        setSupportActionBar(toolbar);


        btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, 1);
        }
    });




     /**  Authores author = new Authores("Andres Davinson","June 11 2018","Porque te gusta dibujar",
               a,"17 Años","Medellin Antoquia","Fotodeperfil");

        db
        .collection("arte").add(author).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                System.out.println("Terminando.....");
            }

        });*/

       FirebaseFirestore.getInstance();


       artistas = new ArrayList<>();
       db.collection("arte").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {


                   // documentsIDs.add(document.getId());
                     Authores auth = document.toObject(Authores.class);
                      artistas.add(auth);
                     System.out.println("####################: "+artistas.size());
                }
                actualizar();
            }
        }
    });

System.out.println("LAA.......");

    }

    public void actualizar(){
        System.out.println("Actualizando.....");
        lista_contenido.setAdapter(new AdaptadorPrincipal(this, artistas));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
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


    @SuppressLint("ResourceType")
    public void actualiza_lista(){


    }

   /** public ArrayList<String> obtenerFotos(){

       // StorageReference sf = mStorageRef.child("artefotos/art54").getDownloadUrl();



        return fotos;
    }*/


     private void uploadFile(Bitmap bitmap) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            Random rand = new Random();
            int randomNum = rand.nextInt((999 - 0) + 1) + 0;
            StorageReference mountainImagesRef = storageRef.child("arte/"+ rand+".jpg");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = mountainImagesRef.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();

                /**Firebase.setAndroidContext(User_Profile.this);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref2User = database.getReference("users");
                DatabaseReference userProfileref = ref2User.child(UserProfile.getId());

                userProfileref.child("profilePicture").setValue(downloadUrl.toString());*/

                }
            });

        }








}
