package com.dglapps.fiestadelarbol.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dglapps.fiestadelarbol.R;
import com.dglapps.fiestadelarbol.ServiceLocator;
import com.dglapps.fiestadelarbol.domain.Category;

public class CategoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_layout, container, false);

        fillViews();

        return view;
    }

    private void fillViews() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            int categoryId = bundle.getInt("categoryId");
            Category category = ServiceLocator.getInstance().getCategoryService().findById(categoryId);
            getView().setBackgroundColor(category.getColorId());
        }
    }
}
