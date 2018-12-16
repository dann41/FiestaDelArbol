package com.dglapps.fiestadelarbol.fragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;
import com.bluehomestudio.luckywheel.WheelView;
import com.dglapps.fiestadelarbol.GameActivity;
import com.dglapps.fiestadelarbol.R;
import com.dglapps.fiestadelarbol.ServiceLocator;
import com.dglapps.fiestadelarbol.domain.Category;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static com.dglapps.fiestadelarbol.services.CategoryService.*;

public class CategorySelectionFragment extends Fragment {

    private final int[] PATTERN = new int[] {
        TRUE_FALSE, MUSIC, QUICK, QUESTION, SKILL,
        MUSIC, TRUE_FALSE, QUESTION, SKILL,
        QUICK, QUESTION, MUSIC, TRUE_FALSE,
        TRUE_FALSE, SKILL, QUESTION, MUSIC,
        QUICK, QUESTION, MUSIC
    };

    private GameActivity gameActivity;
    private LuckyWheel luckyWheel;

    private int i = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.category_selection_layout, container, false);

        luckyWheel = v.findViewById(R.id.wheel);
        setWheelDuration(2000);
        luckyWheel.addWheelItems(getWheelItems());
        luckyWheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = (i + 1) % PATTERN.length;
                luckyWheel.rotateWheelTo(PATTERN[i]);
            }
        });
        luckyWheel.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {
                Log.i("TEST", "Selected category " + PATTERN[i]);
                displayFragmentForCategory(getCategory(i));
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GameActivity) {
            this.gameActivity = (GameActivity) context;
        }
    }

    private Category getCategory(int categoryId) {
        return ServiceLocator.getInstance().getCategoryService().findById(categoryId);
    }

    private void displayFragmentForCategory(final Category category) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gameActivity.displayCategory(category);
            }
        }, 1000);

    }



    private List<WheelItem> getWheelItems() {
        List<WheelItem> wheelItems = Arrays.asList(
                new WheelItem(
                        ResourcesCompat.getColor(getResources(), R.color.colorTrueFalse, null),
                        BitmapFactory.decodeResource(getResources(), R.drawable.true_false)
                ),
                new WheelItem(
                        ResourcesCompat.getColor(getResources(), R.color.colorSkill, null),
                        BitmapFactory.decodeResource(getResources(), R.drawable.skills)
                ),
                new WheelItem(
                        ResourcesCompat.getColor(getResources(), R.color.colorQuestion, null),
                        BitmapFactory.decodeResource(getResources(), R.drawable.question)
                ),
                new WheelItem(
                        ResourcesCompat.getColor(getResources(), R.color.colorMusic, null),
                        BitmapFactory.decodeResource(getResources(), R.drawable.music)
                ),
                new WheelItem(
                        ResourcesCompat.getColor(getResources(), R.color.colorQuick, null),
                        BitmapFactory.decodeResource(getResources(), R.drawable.quick)
                )
        );
        return wheelItems;
    }

    private void setWheelDuration(int durationInMs) {
        WheelView wheelView = null;
        try {
            Field field = luckyWheel.getClass().getDeclaredField("wheelView");
            field.setAccessible(true);
            wheelView = (WheelView) field.get(luckyWheel);
            Field durationField = wheelView.getClass().getDeclaredField("DEFAULT_ROTATION_TIME");
            durationField.setAccessible(true);

            /*Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(durationField, durationField.getModifiers() & ~Modifier.FINAL);*/

            durationField.set(wheelView, durationInMs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
