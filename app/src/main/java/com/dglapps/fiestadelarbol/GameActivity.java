package com.dglapps.fiestadelarbol;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.dglapps.fiestadelarbol.domain.Category;
import com.dglapps.fiestadelarbol.fragments.CategoryFragment;
import com.dglapps.fiestadelarbol.fragments.CategorySelectionFragment;

public class GameActivity extends FullScreenActivity {

    private View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        contentView = findViewById(R.id.game_screen);

        displayRoulette();
    }

    @Override
    protected View getContentView() {
        return contentView;
    }

    public void displayRoulette() {
        Fragment fragment = new CategorySelectionFragment();
        displayFragment(fragment, "roulette");
    }

    public void backToRoulette() {
        getSupportFragmentManager().popBackStackImmediate();
    }

    public void displayCategory(Category category) {
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(getCategoryBundle(category));
        displayFragment(fragment, "category");
    }

    private void displayFragment(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, fragment, tag);
        ft.addToBackStack(null);
        ft.commit();
    }

    private Bundle getCategoryBundle(Category category) {
        Bundle bundle = new Bundle();
        bundle.putInt("categoryId", category.getId());
        return bundle;
    }

}
