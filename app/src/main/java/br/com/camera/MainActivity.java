package br.com.camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnFotografar,btLeitura;
    ImageView imgSuaImagem;
    EditText txtLeitura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retiraFoto();
        lerCodigos();
    }
    private  void lerCodigos(){
        btLeitura = (Button)findViewById(R.id.btLeitura);
        txtLeitura=(EditText)findViewById(R.id.edtLeitura);
        btLeitura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                startActivityForResult(intent,0);
            }
        });
    }
    public void retiraFoto(){
        btnFotografar = (Button)findViewById(R.id.btnTiraFoto);
        imgSuaImagem = (ImageView)findViewById(R.id.suaImagem);
        btnFotografar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(abreCamera,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if(requestCode==0) {
        txtLeitura.setText(data.getStringExtra("SCAN_RESULT"));
       }
        /*Bundle bundle = data.getExtras();
        if(data!=null){
            Bitmap bitmap = (Bitmap)bundle.get("data");
            imgSuaImagem.setImageBitmap(bitmap);
        }*/
    }

}