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

public class movie extends AppCompatActivity
            implements DialogInterface.OnClickListener {  //監聽dialogInterface

    EditText input;          //輸入EditText id : input
    Button help,confirm,next,goback,giveup;
    TextView clue,score,title,answer,end;
    String[] correct={"小美人魚","美女與野獸","花木蘭","海洋奇緣","冰雪奇緣"};   //正確答案
    String[] wordclue={"小","美","花","海","冰"};                             //給予答案的第一個字提示
    String[] sentence={"四個字","五個字","三個字","四個字","四個字"};           //給予成語意思提示

    int counter=1;           //紀錄第幾題
  int point=0;               //紀錄目前得分


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        input=(EditText)findViewById(R.id.input);
        help=(Button)findViewById(R.id.help);
        confirm=(Button)findViewById(R.id.confirm);
        next=(Button)findViewById(R.id.next);
        goback=(Button)findViewById(R.id.goback);
        giveup=(Button)findViewById(R.id.giveup);
        clue=(TextView)findViewById(R.id.clue);
        score=(TextView)findViewById(R.id.score);
        title=(TextView)findViewById(R.id.title);
        answer=(TextView)findViewById(R.id.answer);
        end=(TextView)findViewById(R.id.end);

        title.setText("迪士尼電影題:"+counter+"/5");    //設定title

        score.setText("目前得分:"+point+"分");         //設定目前得分樣式

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE){        //選擇用意思提示
            clue.setText("提示:"+sentence[counter-1]);
            point=point-10;                              //得分扣10分
            score.setText("目前得分:"+point+"分");
        }else if (which == DialogInterface.BUTTON_NEUTRAL){    //選擇不需要提示
            clue.setText("");
        }else if (which == DialogInterface.BUTTON_NEGATIVE){    //選擇給予答案的第一個字為提示
            clue.setText("提示:"+wordclue[counter-1]);
            point=point-5;                              //得分扣5分
            score.setText("目前得分:"+point+"分");
        }
    }
    public void clue(View view ){                      //按下提示按鈕後顯示交談窗的介面
        new AlertDialog.Builder(this)
                .setMessage("選擇提示第一個字分數將扣5分\n選擇字數提示將扣10分")    //設定顯示訊息
                .setCancelable(false)                      //禁用返回鍵關閉交談窗
                .setIcon(android.R.drawable.ic_menu_edit)      //採用內建的圖示
                .setTitle("需要提示嗎?")                         //設定交談窗的標題
                .setPositiveButton("字數提示",this)        //加入肯定按鈕並監聽事件
                .setNeutralButton("不需要",this)           //加入否定按鈕並監聽事件
                .setNegativeButton("給第一個字提示",this)    //不監聽中性按鈕
                .show();       //顯示交談
    }
    public void next(View view){
        counter++;                                    //按下放棄或前進下一題按鈕題數即會加一
        title.setText("迪士尼電影題:"+counter+"/5");
        next.setVisibility(View.GONE);
        help.setVisibility(View.VISIBLE);
        input.setText("");                  //editText會清空
        answer.setText("");
        if(counter==2){                    //第二題
            findViewById(R.id.picmovie1).setVisibility(View.GONE);
            findViewById(R.id.picmovie2).setVisibility(View.VISIBLE);
        }
        else if(counter==3){               //第三題
            findViewById(R.id.picmovie2).setVisibility(View.GONE);
            findViewById(R.id.picmovie3).setVisibility(View.VISIBLE);
        }else if(counter==4){             //第四題
            findViewById(R.id.picmovie3).setVisibility(View.GONE);
            findViewById(R.id.picmovie4).setVisibility(View.VISIBLE);
        }else if(counter==5){             //第五題
            findViewById(R.id.picmovie4).setVisibility(View.GONE);
            findViewById(R.id.picmovie5).setVisibility(View.VISIBLE);
            next.setText("分數總結");

        }else {                              //完成題目後分數總結
            end.setVisibility(View.VISIBLE);
            findViewById(R.id.picmovie5).setVisibility(View.GONE);
            next.setVisibility(View.GONE);
            input.setVisibility(View.GONE);
            confirm.setVisibility(View.GONE);
            clue.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            help.setVisibility(View.GONE);
            goback.setVisibility(View.GONE);
            giveup.setVisibility(View.GONE);
            if (point < 80) {                  //若得分低於80分即闖關失敗
                end.setText("闖關失敗");
            } else {
                end.setText("恭喜過關");       //若得分超過80分級闖關成功

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
    public void tryagain(View view){
        Intent it= new Intent(this,MainActivity.class);
        startActivity(it);
    }
}
