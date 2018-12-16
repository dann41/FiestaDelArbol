package com.dglapps.fiestadelarbol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.dglapps.fiestadelarbol.domain.Player;
import com.dglapps.fiestadelarbol.fragments.AddPlayerDialogFragment;
import com.dglapps.fiestadelarbol.services.GameService;
import com.dglapps.fiestadelarbol.services.QuestionService;

import java.util.Arrays;
import java.util.List;

public class PlayerSelectionActivity extends FullScreenActivity {

    private View mContentView;

    private Player[] players = initPlayers();

    private Button startGame;

    private ImageView[] playerAvatars;
    private TextView[] playerNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_selection);
        mContentView = findViewById(R.id.player_selection);

        startGame = findViewById(R.id.start);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        playerNames = new TextView[] {
                findViewById(R.id.player_1_name),
                findViewById(R.id.player_2_name),
                findViewById(R.id.player_3_name),
                findViewById(R.id.player_4_name)
        };

        playerAvatars = new ImageView[] {
                findViewById(R.id.add_player1),
                findViewById(R.id.add_player2),
                findViewById(R.id.add_player3),
                findViewById(R.id.add_player4)
        };

        for (int i = 0; i < players.length; i++) {
            final int idx = i;
            playerAvatars[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayAddPlayerDialog(players[idx]);
                }
            });

            playerNames[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayAddPlayerDialog(players[idx]);
                }
            });
        }
    }

    @Override
    protected View getContentView() {
        return mContentView;
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshScreen();
    }

    private Player[] initPlayers() {
        return new Player[] {
                new Player(1, null, -1),
                new Player(2, null, -1),
                new Player(3, null, -1),
                new Player(4, null, -1)
        };
    }

    private void startGame() {
        GameService gameService = ServiceLocator.getInstance().getGameService();
        gameService.initGame(Arrays.asList(players));

        QuestionService questionService = ServiceLocator.getInstance().getQuestionService();
        questionService.reset();

        startActivity(new Intent(getApplicationContext(), GameActivity.class));
    }

    public void refreshScreen() {
        hideSystemBar();
        List<Player> storedPlayers = ServiceLocator.getInstance().getPlayerService().getPlayers();

        players = initPlayers();
        for (Player player : storedPlayers) {
            players[player.getPlayerId() - 1] = player;
        }
        updateViews();
    }

    private void updateViews() {
        for (int i = 0; i < players.length; i++) {
            String playerName = players[i].getPlayerName();
            if (playerName != null && !playerName.isEmpty()) {
                playerNames[i].setText(playerName);
            } else {
                playerNames[i].setText(R.string.add_player_hint);
            }

            int avatarId = players[i].getAvatarId();
            if (avatarId != -1) {
                int resourceId = ServiceLocator.getInstance().getAvatarService().getById(avatarId).getResourceId();
                playerAvatars[i].setImageResource(resourceId);
            } else {
                playerAvatars[i].setImageResource(R.drawable.add_contact);
            }
        }
        startGame.setEnabled(availablePlayers() >= 2);
    }

    private int availablePlayers() {
        int availablePlayers = 0;
        for (Player p : players) {
            if (p.getPlayerName() != null && !p.getPlayerName().isEmpty()) {
                availablePlayers++;
            }
        }
        return availablePlayers;
    }

    private void displayAddPlayerDialog(Player player) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("addPlayerDialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        DialogFragment dialogFragment = new AddPlayerDialogFragment();
        dialogFragment.setArguments(getPlayerBundle(player));
        dialogFragment.show(ft, "dialog");
    }

    private Bundle getPlayerBundle(Player player) {
        Bundle bundle = new Bundle();
        bundle.putInt("playerId", player.getPlayerId());
        bundle.putString("playerName", player.getPlayerName());
        bundle.putInt("avatarId", player.getAvatarId());
        return bundle;
    }
}
