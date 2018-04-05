package com.example.kartheekkadaru.mygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameisActive=true;
    int activeplayer=0;
    int gameState[] = {2,2,2,2,2,2,2,2,2};
    int [][]winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    public void dropin(View view){


        ImageView image5 = (ImageView)view;
        System.out.println(image5.getTag().toString());
        int tappedimage = Integer.parseInt(image5.getTag().toString())  ;

        if(gameState[tappedimage]==2 && gameisActive) {
            gameState[tappedimage] = activeplayer;


            if (activeplayer == 0) {
                image5.setTranslationY(-1000f);
                image5.setImageResource(R.drawable.yellow);


                image5.animate().translationYBy(1000f).setDuration(300);
                activeplayer = 1;

            } else if (activeplayer == 1) {
                image5.setTranslationY(1000f);
                image5.setImageResource(R.drawable.red);
                image5.animate().translationYBy(-1000f).setDuration(300);
                activeplayer = 0;
            }
            for(int[] winningPosition : winningPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]]&&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]]&&
                        gameState[winningPosition[0]]!=2)
                {
                    String winner = "Red";

                    if(gameState[winningPosition[0]]==0)    {
                        winner= "Yellow";
                    }


                     TextView a = (TextView)findViewById( R.id.winnermessage);
                     a.setText(winner +"has won");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.Playagainlayout);

                    layout.setVisibility(View.VISIBLE);

                } else{
                    boolean gameisOver = true;
                    for (int imageState : gameState){
                        if (imageState == 2) gameisOver=false;
                    }
                    if (gameisOver){
                        TextView a = (TextView)findViewById( R.id.winnermessage);

                        a.setText("It's a draw");

                        LinearLayout layout = (LinearLayout)findViewById(R.id.Playagainlayout);

                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }


        }

    }
public void playAgain(View view){
        gameisActive=true;
    LinearLayout layout = (LinearLayout)findViewById(R.id.Playagainlayout);
    layout.setVisibility(View.VISIBLE);
    activeplayer=0;
        for (int i=0;i<gameState.length;i++){
            gameState[i] = 2;

        }
    GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
    for (int i=0;i<gridLayout.getChildCount();i++)    {
        ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);

    }


}




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
