package demo.com.progressbardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean can = true;
    private RoundProgressBar progressbar;
    private TextView text;
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressbar = (RoundProgressBar) findViewById(R.id.progressbar);
        text = (TextView) findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = 0;
                progressbar.setProgress(0);
                text.setVisibility(View.INVISIBLE);
                can = true;
                new Thread(){
                    @Override
                    public void run() {
                        while (can){
                            if(progress==10000){
                                can = false;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        text.setVisibility(View.VISIBLE);
                                        text.setText("体检完成");

                                    }
                                });

                            }
                            progressbar.setProgress(progress);
                            progress++;
                            try {
                                sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
            }
        });


    }
}
