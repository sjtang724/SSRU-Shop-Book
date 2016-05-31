package ssru.winitawilawan.ssrushopbook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage myManage;
    private static final String urlJSON = "http://swiftcodingthai.com/ssru/get_user_nam.php";
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    private String[] loginString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        userEditText = (EditText) findViewById(R.id.editText5);
        passwordEditText = (EditText) findViewById(R.id.editText6);

        //Request SQLite

        myManage = new MyManage(MainActivity.this);

        //Test Add Value to SQLite
        //myManage.addNewUser("Name", "sur", "user", "pass", "money");

        //Delete All userTABLE
        deleteAlluserTABLE();

        synJSONtoSQLite();

    } // Main Method

    @Override
    protected void onRestart() {
        super.onRestart();

        deleteAlluserTABLE();
        synJSONtoSQLite();
    }

    public void clickSignIn(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") || passwordString.equals("")) {

            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง", "กรุณากรอกทุกช่อง");
        } else {
            checkUserAnPassword();
        }

    }//clickSignIn

    private void checkUserAnPassword() {

        try {

            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                    MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE WHERE User = " + "'" + userString + "'", null);
            cursor.moveToFirst();

            loginString = new String[cursor.getColumnCount()];

            for (int i = 0; i < cursor.getColumnCount(); i++) {

                loginString[i] = cursor.getString(i);

            }//for

            cursor.close();

            //Check Password

            if (passwordString.equals(loginString[4])) {
                //Password True
                Toast.makeText(this, "ยินดีต้อนรับ " + loginString[1] + " " + loginString[2],
                        Toast.LENGTH_SHORT).show();
            } else {
                //Password False
                MyAlert myAlert = new MyAlert();
                myAlert.myDialog(this, "Password False", "Please Try Again Password False");
            }

        } catch (Exception e) {

            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ไม่มี User นี้", "ไม่มี" + userString + "ในฐานข้อมูลของเรา");

        }
    }//check


    private void synJSONtoSQLite() {
        ConnectedUserTABLE connectedUserTABLE = new ConnectedUserTABLE();
        connectedUserTABLE.execute();

    }

    public class ConnectedUserTABLE extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(urlJSON).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                Log.d("31May", "my Error ==> " + e.toString());
                return null;
            }
        } //doInback

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                Log.d("31May", "JSON ==> " + s);

                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strName = jsonObject.getString(MyManage.column_name);
                    String strSurname = jsonObject.getString(MyManage.column_surname);
                    String strUser = jsonObject.getString(MyManage.column_user);
                    String strPassword = jsonObject.getString(MyManage.column_password);
                    String strmoney = jsonObject.getString(MyManage.column_money);

                    myManage.addNewUser(strName, strSurname, strUser, strPassword, strmoney);

                } //for
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//connect


    private void deleteAlluserTABLE() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.user_table, null, null);
    } //delete

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class)); //การคลิกเปลี่ยนหน้า
    }


} //Main Class
