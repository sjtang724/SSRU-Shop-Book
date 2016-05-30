package ssru.winitawilawan.ssrushopbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    //Explicit ประกาศตัวแปร
    private EditText nameEditText, surnamEditText, userEditText, passwordEditText;
    private String nameString, surnameString, userString,passwordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget การเรียกผูกตัวแปรที่ประกาศไว้
        nameEditText = (EditText) findViewById(R.id.editText);
        surnamEditText = (EditText) findViewById(R.id.editText2);
        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);


    } //Main Method

    public void clickSingUpSign(View view) {

        nameString = nameEditText.getText().toString().trim();
        surnameString = surnamEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space ตรวจสอบหาความว่างเปล่าของข้อมูลที่กรอก
        if (nameString.equals("") || surnameString.equals("") || userString.equals("") || passwordString.equals("")) {

            //Have Space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง", "กรุณากรอกทุกช่อง");
        } else {

            //NO Space
        }

    } //clickSign

} // Main Class
