package tw.cgu.b0921211.afinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class word extends AppCompatActivity
            implements DialogInterface.OnClickListener {

    EditText input;
    Button help,confirm,next,goback,tryagain,giveup;
    TextView clue,score,title,answer,end;
    String[] correct={"守株待兔","文質彬彬","驕兵必敗","趾高氣揚","望眼欲穿"};
    String[] wordclue={"守","文","驕","趾","望"};
    String[] sentence={"比喻妄想不勞而獲或等著目標自己送上門來",
                        "形容人舉止文雅有禮",
                        "比喻自負強大而輕敵的軍隊必會打敗仗",
                        "形容驕傲自滿、得意忘形的樣子",
                        "形容盼望的極為殷切"};

    int counter=1;
    int point=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        input=(EditText)findViewById(R.id.input);
        help=(Button)findViewById(R.id.help);
        confirm=(Button)findViewById(R.id.confirm);
        next=(Button)findViewById(R.id.next);
        goback=(Button)findViewById(R.id.goback);
        tryagain=(Button)findViewById(R.id.tryagain);
        giveup=(Button)findViewById(R.id.giveup);
        clue=(TextView)findViewById(R.id.clue);
        score=(TextView)findViewById(R.id.score);
        title=(TextView)findViewById(R.id.title);
        answer=(TextView)findViewById(R.id.answer);
        end=(TextView)findViewById(R.id.end);

        title.setText("成語題:"+counter+"/5");

        score.setText("目前得分:"+point+"分");


    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        if (which == DialogInterface.BUTTON_POSITIVE){   //sentence clue
            clue.setText("提示:"+sentence[counter-1]);
            point=point-10;
            score.setText("目前得分:"+point+"分");
        }else if (which == DialogInterface.BUTTON_NEUTRAL){    //no need clue
            clue.setText("");
        }else if (which == DialogInterface.BUTTON_NEGATIVE){    //word clue
            clue.setText("提示:"+wordclue[counter-1]);
            point=point-5;
            score.setText("目前得分:"+point+"分");
        }

    }
    public void clue(View view ){
        new AlertDialog.Builder(this)
                .setMessage("選擇提示第一個字分數將扣5分\n選擇意思提示將扣10分")
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_menu_edit)
                .setTitle("需要提示嗎?")
                .setPositiveButton("用意思提示",this)
                .setNeutralButton("不需要",this)
                .setNegativeButton("給第一個字提示",this)
                .show();
    }
    public void next(View view){
        counter++;
        title.setText("成語題:"+counter+"/5");
        next.setVisibility(View.GONE);
        help.setVisibility(View.VISIBLE);
        input.setText("");
        answer.setText("");

        if(counter==2){
            findViewById(R.id.picword1).setVisibility(View.GONE);
            findViewById(R.id.picword2).setVisibility(View.VISIBLE);
        }
        else if(counter==3){
            findViewById(R.id.picword2).setVisibility(View.GONE);
            findViewById(R.id.picword3).setVisibility(View.VISIBLE);
        }else if(counter==4){
            findViewById(R.id.picword3).setVisibility(View.GONE);
            findViewById(R.id.picword4).setVisibility(View.VISIBLE);
        }else if(counter==5){
            findViewById(R.id.picword4).setVisibility(View.GONE);
            findViewById(R.id.picword5).setVisibility(View.VISIBLE);
            next.setText("分數總結");

        }else {
            end.setVisibility(View.VISIBLE);
            findViewById(R.id.picword5).setVisibility(View.GONE);
            next.setVisibility(View.GONE);
            input.setVisibility(View.GONE);
            confirm.setVisibility(View.GONE);
            clue.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            help.setVisibility(View.GONE);
            goback.setVisibility(View.GONE);
            giveup.setVisibility(View.GONE);
            if (point < 80) {
                end.setText("闖關失敗");
                tryagain.setVisibility(View.VISIBLE);
            } else {
                end.setText("恭喜過關");
            }
        }
    }

    public void goback(View view){
        Intent it=new Intent(this,MainActivity.class);
        startActivity(it);

    }
    public void confirm(View view){
        String input1 = input.getText().toString().trim();
        if (input1.length()==0){                              //未輸入
            answer.setText("請於上方輸入答案");
        }else{
            if(input1.equals(correct[counter-1])){     //答案正確
                answer.setText("答案正確!!");
                next.setVisibility(View.VISIBLE);
                help.setVisibility(View.GONE);
                clue.setText("");
                point=point+20;
                score.setText("目前得分:"+point+"分");
            }else{
                answer.setText("答案錯誤!!");     //答案錯誤
            }
        }

    }
}
