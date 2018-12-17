package com.dglapps.fiestadelarbol.fragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
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
import com.dglapps.fiestadelarbol.services.CategoryService;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class CategorySelectionFragment extends Fragment {

    private GameActivity gameActivity;
    private LuckyWheel luckyWheel;

    private CategoryService categoryService;
    private Category selectedCategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.category_selection_layout, container, false);

        luckyWheel = v.findViewById(R.id.wheel);
        luckyWheel.setRotationTime(2000);
        luckyWheel.setRotations(5);
        //luckyWheel.setBackgroundColor(getResources().getColor(R.color.white, null));
        luckyWheel.addWheelItems(getWheelItems());
        luckyWheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCategory = categoryService.getRandomCategory();
                luckyWheel.rotateWheelTo(selectedCategory.getId());
            }
        });
        luckyWheel.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {
                displayFragmentForCategory(selectedCategory);
            }
        });

        categoryService = ServiceLocator.getInstance().getCategoryService();

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GameActivity) {
            this.gameActivity = (GameActivity) context;
        }
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

}
