package hag.com.hardartgram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class AdaptadorPrincipal  extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private ActividadPrincipal mainActivity= new ActividadPrincipal();
   private List<Authores> authores;
   List<String> fotos = new ArrayList<>();
   private ArrayList<Integer> records = new ArrayList<>();

    public AdaptadorPrincipal (Activity activity, List<Authores> authores) {
        this.activity = activity;
        this.authores = authores;

        for(int x=0; x<6; x++){
            ArrayList<String> tmp = authores.get(x).getImagen();
            for(int i=0; i<3; i++){
                fotos.add(tmp.get(i));
            }
        }


    }
    @Override
    public int getCount() {
        return fotos.size()-1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.feeditem, null);


        ImageView foto = (ImageView) convertView.findViewById(R.id.foto);

        TextView nombre = (TextView) convertView.findViewById(R.id.nombre);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        TextView fecha = (TextView) convertView.findViewById(R.id.fecha);

        nombre.setText(authores.get(position).getNombre());
        description.setText(authores.get(position).getDescription());
        fecha.setText(authores.get(position).getFecha());

        //for (int x=0; x<authores.get(position).getImagen().size(); x++){
            System.out.println("Looping.......................");
           // Url_Image_LoaderTask LoadTask ;
           // if(position<3) {
                Url_Image_LoaderTask LoadTask = new Url_Image_LoaderTask(fotos.get(position));
           // }
            if (foto.getDrawable() == null) {
            try {
                System.out.println("BEFORE EXECUTING LOADTASK FOR FEEDPIC--->>>>>");
                Bitmap map = LoadTask.execute().get();
                foto.setImageBitmap(map);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
  //  }


        convertView.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
         //RetrieveProviders np= mainActivity.getProvider(position);
         System.out.println("CLICK CLICK WORKING ==================================");

        Intent intent = new Intent(v.getContext(),PerfilArtista.class);
        intent.putExtra("nombre", authores.get(position).getNombre());
        intent.putExtra("ciudad", authores.get(position).getCiudad());
        intent.putExtra("edad",authores.get(position).getEdad());
        intent.putExtra("bio",authores.get(position).getDescription());
        intent.putExtra("foto",authores.get(position).getFotodeperfil());




                 v.getContext().startActivity(intent);
         }
         });






        return convertView;
    }

    public boolean keeptrack(int position){
        if(records.contains(position)){
            return true;
        }else{
            records.add(position);
            return false;
        }
    }
}
