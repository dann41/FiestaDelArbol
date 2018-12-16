package com.dglapps.fiestadelarbol.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dglapps.fiestadelarbol.GameActivity;
import com.dglapps.fiestadelarbol.R;
import com.dglapps.fiestadelarbol.ServiceLocator;
import com.dglapps.fiestadelarbol.domain.Category;
import com.dglapps.fiestadelarbol.domain.Player;
import com.dglapps.fiestadelarbol.services.GameService;

public class CategoryFragment extends Fragment {

    private View contentView;
    private TextView categoryView;
    private TextView questionView;
    private TextView answerView;
    private TextView answerButton;
    private TextView nextButton;

    private GameActivity gameActivity;
    private GameService gameService;

    private Category category;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_layout, container, false);

        contentView = view.findViewById(R.id.category_layout);
        categoryView = view.findViewById(R.id.category);
        questionView = view.findViewById(R.id.question);
        answerView = view.findViewById(R.id.answer);
        answerButton = view.findViewById(R.id.answer_button);
        nextButton = view.findViewById(R.id.next);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });

        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerView.setVisibility(View.VISIBLE);
                answerButton.setVisibility(View.INVISIBLE);
                nextButton.setEnabled(true);
            }
        });

        gameService = ServiceLocator.getInstance().getGameService();

        fillViews(gameService.nextPlayer());

        return contentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GameActivity) {
            gameActivity = (GameActivity) context;
        }
    }

    private void next() {
        if (category.isAllPlay() || gameService.isEndOfRound()) {
            gameService.nextRound();
            gameActivity.backToRoulette();
        } else {
            fillViews(gameService.nextPlayer());
        }
    }

    private void fillViews(Player player) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            int categoryId = bundle.getInt("categoryId");
            category = ServiceLocator.getInstance().getCategoryService().findById(categoryId);

            contentView.setBackgroundColor(
                    ResourcesCompat.getColor(
                        getResources(),
                        category.getColorId(),
                        null
                    ));
            categoryView.setText(category.getNameId());
            categoryView.setCompoundDrawablesWithIntrinsicBounds(category.getIconId(), 0, 0, 0);

            if (category.isAnswerRequired()) {
                nextButton.setEnabled(false);
                answerButton.setVisibility(View.VISIBLE);
            } else {
                nextButton.setEnabled(true);
                answerButton.setVisibility(View.INVISIBLE);
            }

            answerView.setVisibility(View.INVISIBLE);

            // TODO change this
            questionView.setText(player.getPlayerName());
            answerView.setText("Round " + gameService.getRound());
        }
    }
}
