package com.example.helge.bowlgamer;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@OptionsMenu(R.menu.result_menu)
@EActivity(R.layout.activity_result)
public class ResultActivity extends AppCompatActivity {


    @ViewById
    TextView player1_summary, player2_summary, player3_summary, player4_summary, player5_summary, player6_summary;
    @ViewById
    TextView teampins_game1, teampins_game2, teampins_game3, teampins_game4, teampins_game5;
    @ViewById
    TextView teampins_game6, teampins_game7, teampins_game8, teampins_game9;
    @ViewById
    EditText player_1_game_1, player_1_game_2, player_1_game_3, player_1_game_4, player_1_game_5;
    @ViewById
    EditText player_1_game_6, player_1_game_7, player_1_game_8, player_1_game_9;
    @ViewById
    EditText player_2_game_1, player_2_game_2, player_2_game_3, player_2_game_4, player_2_game_5;
    @ViewById
    EditText player_2_game_6, player_2_game_7, player_2_game_8, player_2_game_9;
    @ViewById
    EditText player_3_game_1, player_3_game_2, player_3_game_3, player_3_game_4, player_3_game_5;
    @ViewById
    EditText player_3_game_6, player_3_game_7, player_3_game_8, player_3_game_9;
    @ViewById
    EditText player_4_game_1, player_4_game_2, player_4_game_3, player_4_game_4, player_4_game_5;
    @ViewById
    EditText player_4_game_6, player_4_game_7, player_4_game_8, player_4_game_9;
    @ViewById
    EditText player_5_game_1, player_5_game_2, player_5_game_3, player_5_game_4, player_5_game_5;
    @ViewById
    EditText player_5_game_6, player_5_game_7, player_5_game_8, player_5_game_9;
    @ViewById
    EditText player_6_game_1, player_6_game_2, player_6_game_3, player_6_game_4, player_6_game_5;
    @ViewById
    EditText player_6_game_6, player_6_game_7, player_6_game_8, player_6_game_9;


    @AfterViews
    protected void init() {
        setListeners();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_launcher);
        }
    }

    private void setListeners() {

        final View[][] listOfPlayersOfGames = {
                {player_1_game_1, player_1_game_2, player_1_game_3, player_1_game_4, player_1_game_5, player_1_game_6, player_1_game_7, player_1_game_8, player_1_game_9},
                {player_2_game_1, player_2_game_2, player_2_game_3, player_2_game_4, player_2_game_5, player_2_game_6, player_2_game_7, player_2_game_8, player_2_game_9},
                {player_3_game_1, player_3_game_2, player_3_game_3, player_3_game_4, player_3_game_5, player_3_game_6, player_3_game_7, player_3_game_8, player_3_game_9},
                {player_4_game_1, player_4_game_2, player_4_game_3, player_4_game_4, player_4_game_5, player_4_game_6, player_4_game_7, player_4_game_8, player_4_game_9},
                {player_5_game_1, player_5_game_2, player_5_game_3, player_5_game_4, player_5_game_5, player_5_game_6, player_5_game_7, player_5_game_8, player_5_game_9},
                {player_6_game_1, player_6_game_2, player_6_game_3, player_6_game_4, player_6_game_5, player_6_game_6, player_6_game_7, player_6_game_8, player_6_game_9},
        };

        final TextView[] playerSummeries = {
                player1_summary, player2_summary, player3_summary, player4_summary, player5_summary, player6_summary
        };

        final TextView[] gameSummeries = {
                teampins_game1, teampins_game2, teampins_game3, teampins_game4, teampins_game5
        };

        View.OnFocusChangeListener updateSumsListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // pay attention that you never got more then 9 player / games
                if (!hasFocus) {
                    // Update player(player)_summary and teampins_game(game) depending on which edittext lost focus
                    int tagCounter = Integer.parseInt((String) v.getTag());
                    int player = tagCounter / 10;
                    int game = tagCounter - (player * 10);

                    // always calculate the meaningfull fields
                    // calculate the playerscore iterates overer all(9) games of the same player
                    Integer playerScore = 0;
                    for (int i = 0; i < 9; i++) {
                        String field = ((EditText) listOfPlayersOfGames[player - 1][i]).getText().toString();
                        if (field != null && field.length() > 0) {
                            Integer content = Integer.parseInt(field.toString());
                            if (content > 300 || content < 0) {
                                ((EditText) v).setError(content.toString());
                            } else {
                                playerScore += content;
                            }
                        }
                    }
                    //set player(player)_summary
                    playerSummeries[player - 1].setText(playerScore.toString());

                    // calculate GameScore iterates over all (6) players of the same game
                    Integer gameScore = 0;
                    for (int i = 0; i < 6; i++) {
                        String field = ((EditText) listOfPlayersOfGames[i][game - 1]).getText().toString();
                        if (field != null && field.length() > 0) {
                            Integer content = Integer.parseInt(field.toString());
                            gameScore += content;
                        }
                    }
                    gameSummeries[game - 1].setText(gameScore.toString());
                }
            }
        };

        player_1_game_1.setOnFocusChangeListener(updateSumsListener);
        player_2_game_1.setOnFocusChangeListener(updateSumsListener);
        player_3_game_1.setOnFocusChangeListener(updateSumsListener);
        player_4_game_1.setOnFocusChangeListener(updateSumsListener);
        player_5_game_1.setOnFocusChangeListener(updateSumsListener);
        player_6_game_1.setOnFocusChangeListener(updateSumsListener);

        player_1_game_2.setOnFocusChangeListener(updateSumsListener);
        player_2_game_2.setOnFocusChangeListener(updateSumsListener);
        player_3_game_2.setOnFocusChangeListener(updateSumsListener);
        player_4_game_2.setOnFocusChangeListener(updateSumsListener);
        player_5_game_2.setOnFocusChangeListener(updateSumsListener);
        player_6_game_2.setOnFocusChangeListener(updateSumsListener);

        player_1_game_3.setOnFocusChangeListener(updateSumsListener);
        player_2_game_3.setOnFocusChangeListener(updateSumsListener);
        player_3_game_3.setOnFocusChangeListener(updateSumsListener);
        player_4_game_3.setOnFocusChangeListener(updateSumsListener);
        player_5_game_3.setOnFocusChangeListener(updateSumsListener);
        player_6_game_3.setOnFocusChangeListener(updateSumsListener);

        player_1_game_4.setOnFocusChangeListener(updateSumsListener);
        player_2_game_4.setOnFocusChangeListener(updateSumsListener);
        player_3_game_4.setOnFocusChangeListener(updateSumsListener);
        player_4_game_4.setOnFocusChangeListener(updateSumsListener);
        player_5_game_4.setOnFocusChangeListener(updateSumsListener);
        player_6_game_4.setOnFocusChangeListener(updateSumsListener);

        player_1_game_5.setOnFocusChangeListener(updateSumsListener);
        player_2_game_5.setOnFocusChangeListener(updateSumsListener);
        player_3_game_5.setOnFocusChangeListener(updateSumsListener);
        player_4_game_5.setOnFocusChangeListener(updateSumsListener);
        player_5_game_5.setOnFocusChangeListener(updateSumsListener);
        player_6_game_5.setOnFocusChangeListener(updateSumsListener);

        player_1_game_6.setOnFocusChangeListener(updateSumsListener);
        player_2_game_6.setOnFocusChangeListener(updateSumsListener);
        player_3_game_6.setOnFocusChangeListener(updateSumsListener);
        player_4_game_6.setOnFocusChangeListener(updateSumsListener);
        player_5_game_6.setOnFocusChangeListener(updateSumsListener);
        player_6_game_6.setOnFocusChangeListener(updateSumsListener);

        player_1_game_7.setOnFocusChangeListener(updateSumsListener);
        player_2_game_7.setOnFocusChangeListener(updateSumsListener);
        player_3_game_7.setOnFocusChangeListener(updateSumsListener);
        player_4_game_7.setOnFocusChangeListener(updateSumsListener);
        player_5_game_7.setOnFocusChangeListener(updateSumsListener);
        player_6_game_7.setOnFocusChangeListener(updateSumsListener);

        player_1_game_8.setOnFocusChangeListener(updateSumsListener);
        player_2_game_8.setOnFocusChangeListener(updateSumsListener);
        player_3_game_8.setOnFocusChangeListener(updateSumsListener);
        player_4_game_8.setOnFocusChangeListener(updateSumsListener);
        player_5_game_8.setOnFocusChangeListener(updateSumsListener);
        player_6_game_8.setOnFocusChangeListener(updateSumsListener);

        player_1_game_9.setOnFocusChangeListener(updateSumsListener);
        player_2_game_9.setOnFocusChangeListener(updateSumsListener);
        player_3_game_9.setOnFocusChangeListener(updateSumsListener);
        player_4_game_9.setOnFocusChangeListener(updateSumsListener);
        player_5_game_9.setOnFocusChangeListener(updateSumsListener);
        player_6_game_9.setOnFocusChangeListener(updateSumsListener);

    }

}
