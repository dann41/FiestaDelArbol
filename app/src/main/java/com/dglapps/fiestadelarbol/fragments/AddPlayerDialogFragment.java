package com.dglapps.fiestadelarbol.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.dglapps.fiestadelarbol.PlayerSelectionActivity;
import com.dglapps.fiestadelarbol.R;
import com.dglapps.fiestadelarbol.ServiceLocator;
import com.dglapps.fiestadelarbol.domain.Player;

public class AddPlayerDialogFragment extends DialogFragment {

    private EditText playerNameInput;
    private View[] avatarViews;
    private Button addPlayerButton;

    private Player player;
    private int selectedAvatarId;
    private PlayerSelectionActivity activity;

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.player_dialog, null, false);

        this.playerNameInput = view.findViewById(R.id.player_name);
        this.avatarViews = new View[] {
                view.findViewById(R.id.avatar_1),
                view.findViewById(R.id.avatar_2),
                view.findViewById(R.id.avatar_3),
                view.findViewById(R.id.avatar_4),
                view.findViewById(R.id.avatar_5),
                view.findViewById(R.id.avatar_6)
        };

        for (View v : avatarViews) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectAvatar(v.getId());
                }
            });
        }

        this.addPlayerButton = view.findViewById(R.id.add_player);

        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePlayer();
                dismiss();
            }
        });

        view.findViewById(R.id.remove_player).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.getPlayerName() != null && !player.getPlayerName().isEmpty()) {
                    ServiceLocator.getInstance().getPlayerService().removePlayer(player.getPlayerId());
                }
                dismiss();
            }
        });

        initPlayer();

        return new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Light_Dialog_MinWidth)
                .setView(view)
                .setCancelable(false)
                .create();
    }

    private void selectAvatar(int avatarId) {
        selectedAvatarId = avatarId;
        for (View view : avatarViews) {
            if (view.getId() == avatarId) {
                view.setBackgroundResource(R.drawable.selected_avatar);
            } else {
                view.setBackground(null);
            }
        }
    }

    private void initPlayer() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            player = new Player(
                    bundle.getInt("playerId"),
                    bundle.getString("playerName"),
                    bundle.getInt("avatarId")
            );
        } else {
            player = new Player(1, null, -1);
        }

        playerNameInput.setText(player.getPlayerName());
        selectAvatar(player.getAvatarId());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PlayerSelectionActivity) {
            activity = (PlayerSelectionActivity) context;
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (activity != null) {
            activity.refreshScreen();
        }
    }

    private Player getPlayerFromInput() {
        return new Player(player.getPlayerId(),
                playerNameInput.getText().toString(),
                selectedAvatarId);
    }

    private void savePlayer() {
        Player player = getPlayerFromInput();
        ServiceLocator.getInstance().getPlayerService().savePlayer(player);
    }

}
