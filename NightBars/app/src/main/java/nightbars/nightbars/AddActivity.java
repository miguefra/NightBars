package nightbars.nightbars;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nightbars.nightbars.app.ConexionDBAddPlace;
import nightbars.nightbars.helper.TaskCallback;

public class AddActivity extends AppCompatActivity implements TaskCallback {
    private static final String TAG = "AddActivity";
    private String IP = "sql8.freesqldatabase.com:3306";
    private String dataBase = "/sql8170846";

    @InjectView(R.id.input_name)
    EditText nameText;
    @InjectView(R.id.input_type)
    EditText typeText;
    @InjectView(R.id.input_price)
    EditText priceText;
    @InjectView(R.id.input_location)
    EditText locationText;
    @InjectView(R.id.input_score)
    EditText scoreText;
    @InjectView(R.id.btn_addPlace)
    Button addPlaceButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.inject(this);

        addPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlace();
            }
        });
    }

    public void addPlace() {
        Log.d(TAG, "AddPlace");

        if (!validate()) {
            onAddPlaceFailed();
            return;
        }
        addPlaceButton.setEnabled(false);

        // TODO: Añadir ubicación real.
        String name = nameText.getText().toString();
        String type = typeText.getText().toString();
        String price = priceText.getText().toString();
        String location = locationText.getText().toString();
        String score = scoreText.getText().toString();

        new ConexionDBAddPlace(AddActivity.this, this).execute(IP, dataBase, name, type, price, location, score);
    }

    public void onAddPlaceFailed() {
        Toast.makeText(getBaseContext(), "Ooops! Something failed, try again.", Toast.LENGTH_LONG).show();

        addPlaceButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String type = typeText.getText().toString();
        String price = priceText.getText().toString();
        String location = locationText.getText().toString();
        String score = scoreText.getText().toString();

        if (name.isEmpty()) {
            nameText.setError("Please, put the name of the place.");
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (type.isEmpty()) {
            typeText.setError("Eey, put the type, please.");
            valid = false;
        } else {
            typeText.setError(null);
        }

        if (price.isEmpty() || Integer.parseInt(price) < 1 || Integer.parseInt(price) > 5) {
            priceText.setError("Put a price or between 1 and 5");
            valid = false;
        } else {
            priceText.setError(null);
        }

        if (location.isEmpty()) {
            locationText.setError("Enter a location, please!");
            valid = false;
        } else {
            locationText.setError(null);
        }

        if (score.isEmpty() || Integer.parseInt(score) < 1 || Integer.parseInt(score) > 5) {
            scoreText.setError("Between 1 and 5, please!");
            valid = false;
        } else {
            scoreText.setError(null);
        }

        return valid;
    }

    public void done() {
        finish();
    }
}
