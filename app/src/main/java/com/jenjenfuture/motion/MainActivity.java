package com.jenjenfuture.motion;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText mEditTextInputUang;
    Button mButtonConvert;
    Button mButtonChange;
    TextView mTextViewHasilKonversi;
    Spinner mSpinnerFrom;
    Spinner mSpinnerTo;

    float[] mArrayOfConstant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mEditTextInputUang = findViewById(R.id.uang_input);
        mButtonConvert = findViewById(R.id.rpyen);
        mTextViewHasilKonversi = findViewById(R.id.hasil_konversi);


        mArrayOfConstant = new float[]{
                1, 14130, 15871, 126.51f, 2103.3f, 10433
        };

        String[] arraySpinner = new String[] {
                "Rupiah", "USD", "EURO", "Yen" ,"Yuan", "SGD"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerFrom = findViewById(R.id.from_spinner);
        mSpinnerFrom.setAdapter(adapter);

        mSpinnerTo = findViewById(R.id.to_spinner);
        mSpinnerTo.setAdapter(adapter);


        mButtonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cek())
                {
                    float from = mArrayOfConstant[mSpinnerFrom.getSelectedItemPosition()];
                    float to = mArrayOfConstant[mSpinnerTo.getSelectedItemPosition()];
                    float angka = Float.parseFloat(mEditTextInputUang.getText().toString());

                    float hasil = from/to * angka;
                    mTextViewHasilKonversi.setText(String.valueOf(hasil));
                }
            }
        });

        mButtonChange = findViewById(R.id.change);
        mButtonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idx1 = mSpinnerFrom.getSelectedItemPosition();
                int idx2 = mSpinnerTo.getSelectedItemPosition();
                mSpinnerFrom.setSelection(idx2);
                mSpinnerTo.setSelection(idx1);


            }
        });


    }

    public boolean cek(){
        if (mEditTextInputUang.getText().toString().isEmpty()){
            Toast.makeText(this, "Silahkan masukan jumlah uang", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}