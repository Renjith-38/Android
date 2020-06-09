package com.example.knect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        //O: green 1:red 2:empty
        int player=0;
        int[] state = {2,2,2,2,2,2,2,2,2};
        int[][] winpos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        boolean game = true;

        public void drop(View view) {
            ImageView counter = (ImageView) view;

            int tap = Integer.parseInt(counter.getTag().toString());
            // boolean game= true;
            if (state[tap] == 2 && game) {
                TextView textView1 = (TextView) findViewById(R.id.textView5);
                textView1.setVisibility(View.INVISIBLE);
                state[tap] = player;
                counter.setTranslationY(-1500);
                if (player == 0) {
                    counter.setImageResource(R.drawable.green1);
                    counter.animate().translationYBy(1500).setDuration(400);
                    player = 1;
                } else {
                    counter.setImageResource(R.drawable.red1);
                    counter.animate().translationYBy(1500).setDuration(400);
                    player = 0;
                }
                for (int[] winpos : winpos) {
                    if (state[winpos[0]] == state[winpos[1]] && state[winpos[1]] == state[winpos[2]] && state[winpos[0]] != 2) {
                        game = false;
                        String msg = "";
                        if (player == 1)
                            msg = "Green";
                        else
                            msg = "Red";
                        TextView textView = (TextView) findViewById(R.id.textView2);
                        Button button = (Button) findViewById(R.id.button);
                        button.setVisibility(View.VISIBLE);
                        textView.setText(msg + " Has Won");
                        textView.setVisibility(View.VISIBLE);


                        // Toast.makeText(this, msg+" Has Won", Toast.LENGTH_SHORT).show();
                    } else {
                        int c = 0;
                        for (int j = 0; j < state.length; j++) {
                            if (state[j] != 2)
                                c++;
                        }
                        if (c == state.length)
                            Toast.makeText(this, "Game is a tie", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        }

       public void playagain (View view) {
            TextView textView = (TextView) findViewById(R.id.textView2);
            Button button = (Button) findViewById(R.id.button);
            button.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);

          GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

          for(int i=0; i<gridLayout.getChildCount(); i++) {

              ImageView counter = (ImageView) gridLayout.getChildAt(i);

              counter.setImageDrawable(null);



          }
           /*for (int i=0; i<state.length; i++) {

               state[i] = 2;

           }*/
            state = new int[]{2,2,2,2,2,2,2,2,2};
            game = true;
            player = 0;

        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
