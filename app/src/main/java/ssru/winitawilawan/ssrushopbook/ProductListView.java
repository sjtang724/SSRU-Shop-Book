package ssru.winitawilawan.ssrushopbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ProductListView extends AppCompatActivity {

    //Explicit

    private TextView nameTextView, surnameTextView, moneyTextView;
    private ListView listView;
    private String[] loginStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_view);

        //Bind Widget
        nameTextView = (TextView) findViewById(R.id.textView7);
        surnameTextView = (TextView) findViewById(R.id.textView8);
        moneyTextView = (TextView) findViewById(R.id.textView9);
        listView = (ListView) findViewById(R.id.listView);

        //Receive Value From Intent
        loginStrings = getIntent().getStringArrayExtra("Login");

        //show view
        nameTextView.setText(loginStrings[1]);
        surnameTextView.setText(loginStrings[2]);
        moneyTextView.setText(loginStrings[5] + "THB.");


    } //main method
}//main class
