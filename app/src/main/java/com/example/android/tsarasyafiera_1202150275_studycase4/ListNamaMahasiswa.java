package com.example.android.tsarasyafiera_1202150275_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class ListNamaMahasiswa extends AppCompatActivity {

    //Deklrasi private variable
    private ListView mListView;
    private ProgressBar mProgressBar;
    private Button mStartAsyncTask;

    //Menyimpan string daftar nama mahasiswa kedalam variable array daftarMahasiswa
    private String [] daftarMahasiswa= {"ANGGA",
            "ANGGI",
            "RENO",
            "RENA",
            "RANI",
            "RINA",
            "RIRI",
            "RARA",
            "RANGGA",
            "BIMA",
            "BIMO",
            "FANI",
            "FINA",
            "FINO",
            "FANDI"};

    //Deklarasi private variable untuk menambahkan item ke listview
    private AddItemToListView mAddItemToListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama_mahasiswa);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mListView = (ListView) findViewById(R.id.listView);
        mStartAsyncTask = (Button) findViewById(R.id.button_startAsyncTask);

        //Membuat progressbar visible ketika aplikasi berjalan
        mListView.setVisibility(View.GONE);

        //Setup adapter
        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new ArrayList<String>()));

        //Memulai async task ketika button diklik
        mStartAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Adapter proses dengan async task
                mAddItemToListView = new AddItemToListView();  //Melakukan eksekusi ketika Button diklik
                mAddItemToListView.execute();
            }
        });
    }

    //Didalam class untuk proses async task
    public class AddItemToListView  extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> mAdapter;
        private int counter=1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListNamaMahasiswa.this);


        @Override   //Method ini digunakan untuk melakukan eksekusi progress dialog sebelum method onPostExecute dijalankan
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter(); //casting suggestion

            //Untuk progress dialog
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setProgress(0);
            //memperbarui kemajuan dialog dengan beberapa nilai
            //menampilkan progress dialog

            //proses tombol cancel pada dialog
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mAddItemToListView.cancel(true);
                    //Menampilkan (Visible) progress bar pada layar dialog setelah diklik cancel
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            mProgressDialog.show();
        }



        @Override  //Method ini digunakan untuk melakukan aktivitas dibackground menggunakan AsyncTask
        protected Void doInBackground(Void... params) {
            //membuat perulangan untuk memunculkan nama mahasiswa
            for (String item : daftarMahasiswa){
                publishProgress(item);
                try {
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(isCancelled()){
                    mAddItemToListView.cancel(true);
                }
            }
            return null;  //mengembalikan nilai
        }

        @Override //Method ini digunakan untuk menghitung presentase progress dialog
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]); //adapter array memulai dari array 0

            Integer current_status = (int)((counter/(float)daftarMahasiswa.length)*100);
            mProgressBar.setProgress(current_status);

            //Set tampilan progress pada dialog progress
            mProgressDialog.setProgress(current_status);

            //Set message berupa persentase progress pada dialog progress
            mProgressDialog.setMessage(String.valueOf(current_status+"%"));
            counter++; //mengeset hitungan di dalam progress dialog
        }


        @Override //Method ini digunakan untuk melakukan eksekusi setImageBitmap setelah method doInBackground dijalankan
        protected void onPostExecute(Void Void) {
            //Menyembunyikan progressbar
            mProgressBar.setVisibility(View.GONE);

            //setelah loading progress sudah penuh maka otomatis akan hilang progress dialog tersebut
            mProgressDialog.dismiss();
            mListView.setVisibility(View.VISIBLE);
        }
    }
}
