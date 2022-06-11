package tw.cgu.b0921211.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView explain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        explain=(TextView)findViewById(R.id.explain);

        explain.setText("遊戲方法 :\n依題目上的表情符號，猜出答案\n" +
                        "總分超過80分即為過關\n請先選出想挑戰的題目類型");
    }
    public void cartoon(View view){
        Intent it=new Intent(this,cartoon.class);
        startActivity(it);
    }
    public void movie(View view){
        Intent it=new Intent(this,movie.class);
        startActivity(it);

    }
    public void word(View view){
        Intent it=new Intent(this,word.class);
        startActivity(it);
    }
}
