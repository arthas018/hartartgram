package hag.com.hardartgram;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;



import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kendycolon on 7/10/17.
 */

  public class Url_Image_LoaderTask extends AsyncTask<Void, Void, Bitmap> {

    private String urls;
    private ImageView feed_profile_pic;
    ActividadPrincipal mainActivity= new ActividadPrincipal();




    public Url_Image_LoaderTask(String url) {
        this.urls = url;
     }

    @Override
    protected Bitmap doInBackground(Void... params) {
        System.out.println("<<<<<<<<<<<background task>>>>>>>>>>>>");
         try {

        Bitmap bitmap = null;
        InputStream iStream = null;
        URL url = new URL(urls);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(9000 /* milliseconds */);
        conn.setConnectTimeout(9000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        iStream = conn.getInputStream();
        bitmap = BitmapFactory.decodeStream(iStream);

        return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



        @Override
    protected void onPostExecute(Bitmap result) {
         super.onPostExecute(result);

             System.out.println("onPostExecute fee_profile_pic to be check if not null<<-------->>");
            if( feed_profile_pic != null) {
                 System.out.println("SETTTING NEW IMAG====================>!");
            }
    }

}








  
