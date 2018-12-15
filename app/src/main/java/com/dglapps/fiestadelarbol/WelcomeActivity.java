package com.dglapps.fiestadelarbol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends FullScreenActivity {

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        mContentView = findViewById(R.id.welcome_title);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PlayerSelectionActivity.class));
            }
        });
    }

    @Override
    protected View getContentView() {
        return mContentView;
    }

}
