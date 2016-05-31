package ssru.winitawilawan.ssrushopbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    //Explicit ประกาศตัวแปร
    private EditText nameEditText, surnamEditText, userEditText, passwordEditText;
    private String nameString, surnameString, userString, passwordString;
    private static final String urlUpload = "http://swiftcodingthai.com/ssru/add_user_nam.php";

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
            uploadNewUser();
        }

    } //clickSign

    private void uploadNewUser() {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("Name" ,nameString)
                .add("Surname", surnameString)
                .add("User", userString)
                .add("Password", passwordString)
                .build();

        Request.Builder builder = new Request.Builder();
        Request request = builder.url("http://swiftcodingthai.com/ssru/add_user_nam.php").post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                finish();
            }
        });
    } // uploadNewUser

} // Main Class
