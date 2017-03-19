package ve.com.a4reign.www.prueba;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.Observable;


public class MainActivity extends AppCompatActivity {

    private TextView summit;
    private EditText name;
    private EditText last;
    private EditText phone;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = (EditText)findViewById(R.id.Name);

        last = (EditText)findViewById(R.id.Last);

        phone = (EditText)findViewById(R.id.Phone);

        email = (EditText)findViewById(R.id.Email);

        summit = (TextView)findViewById(R.id.summit);


        summit.setEnabled(false);


        phone.setOnKeyListener((v, keyCode, event) -> {
            validatePhone(event,keyCode);
            return false;
        });

        last.setOnKeyListener((v, keyCode, event) -> {
            summit.setEnabled(validateField());
            summit.setClickable(validateField());
            return false;
        });

        email.setOnKeyListener((v, keyCode, event) -> {
            if (validateEmail(email.getText().toString()) == false){
                email.setError("Not Valid Email");
            }
            return false;
        });

        summit.setOnClickListener(v -> {
            Toast.makeText(this, "Clicked Next", Toast.LENGTH_SHORT).show();
        });

    }

    //Validar Campos Vacios
    public boolean validateField() {
        final boolean[] t = {false};
        Observable.just(name.getText().toString(), last.getText().toString(), phone.getText().toString(), email.getText().toString())
                .all(field -> !field.isEmpty())
                .filter(v -> v)
                .subscribe(result -> t[0] = true);
        if (t[0] == true) summit.setTextColor(Color.parseColor("#55CCEA"));
        else summit.setTextColor(Color.LTGRAY);
        return t[0];
    }

    //Validar Telefono
    public void validatePhone(KeyEvent event, int KeyCode){
        boolean t = false;
        if (phone.getText().length() > 1) {
            switch (phone.getText().toString().substring(0,2)) {
                case "+1":
                    if ((phone.getText().length() == 2 || phone.getText().length() == 6 || phone.getText().length() == 10) && (event.getKeyCode() != KeyEvent.KEYCODE_DEL)) {
                        phone.setText(phone.getText() + " ");
                        phone.setSelection(phone.getText().length());
                    }else if (phone.getText().length() == 16){
                        phone.setText(phone.getText().toString().substring(0,phone.getText().length()-1));
                        phone.setSelection(phone.getText().length());
                    }
                    t = true;
                    break;

                case "+5":
                    if ((phone.getText().length() == 3 || phone.getText().length() == 7 || phone.getText().length() == 11) && (event.getKeyCode() != KeyEvent.KEYCODE_DEL)) {
                        phone.setText(phone.getText() + " ");
                        phone.setSelection(phone.getText().length());
                    }
                    t = true;
                    break;
            }

            if (t == false){
                phone.setError("Not Valid Phone Number, Begin +58 or +1");
            }
        }
    }

    //Validar Email
    private boolean validateEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
