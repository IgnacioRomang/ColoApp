package romang.ignacio.coloapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

import romang.ignacio.coloapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Dialog dialog;
    private MaterialDatePicker datePicker;
    private MaterialTimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        FloatingActionButton addbutton = binding.navigationRail.getHeaderView().findViewById(R.id.addButton);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { ;
                dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.add_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                addLiseners(dialog);
                dialog.show();
                Toast.makeText(getBaseContext(),"Llego",Toast.LENGTH_LONG).show();
            }
        });

        setContentView(binding.getRoot());
    }
    private void addLiseners(Dialog dialog){
        MaterialButton aceptar = dialog.findViewById(R.id.aceptarb);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 4/3/2022  hacer aceptar button
                dialog.dismiss();
            }
        });
        MaterialButton cancelar = dialog.findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 4/3/2022 Limpiar variables
                dialog.cancel();
            }
        });
        MaterialButton calentar = dialog.findViewById(R.id.calendarButton);
        MaterialButton tiempo = dialog.findViewById(R.id.timeButton);
        // TODO: 4/3/2022 Lisseners de aceptar y cancelar y cambiar la fecha
        datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(getString(R.string.datePicker))
                .setSelection(Calendar.getInstance().getTimeInMillis())
                .build();

        timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
                .setMinute(Calendar.getInstance().get(Calendar.MINUTE))
                .build();

        calentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show(getSupportFragmentManager(),"datepiker");
            }
        });
        tiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.show(getSupportFragmentManager(),"tiempopiker");
            }
        });

    }
}