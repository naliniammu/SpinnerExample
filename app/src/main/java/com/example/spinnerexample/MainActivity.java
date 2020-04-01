package com.example.spinnerexample;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListPopupWindow;
import android.widget.Spinner;
import java.lang.reflect.Field;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  {

    @BindView(R.id.country_spinner)
    Spinner mCountrySpinner;
    @BindView(R.id.country_spinner_withoutreflection)
    Spinner mCountrySpinnerWithoutreflection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // bind the view using butterknife
        ButterKnife.bind(this);
        // Spinner click listener
        addListenerOnSpinnerItemSelection();
        addListenerOnSpinnerItemSelectionWithoutReflection();


    }

    private void addListenerOnSpinnerItemSelectionWithoutReflection() {

    }


    public void addListenerOnSpinnerItemSelection() {
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);
            // Get private mPopup member variable and try cast to ListPopupWindow
            ListPopupWindow popupWindow = null;
            popupWindow = (ListPopupWindow) popup.get(mCountrySpinner);
            popupWindow.setHeight(500);
            mCountrySpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {

        }

    }



}
