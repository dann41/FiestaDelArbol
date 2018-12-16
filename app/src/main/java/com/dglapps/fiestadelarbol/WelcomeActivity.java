package com.dglapps.fiestadelarbol;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import com.dglapps.fiestadelarbol.services.QuestionService;
import io.reactivex.functions.Consumer;

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

        findViewById(R.id.sync).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadQuestions();
            }
        });

        loadQuestions();

    }

    private void loadQuestions() {
        QuestionService questionService = ServiceLocator.getInstance().getQuestionService();

        questionService.loadQuestions(getApplicationContext())
            .subscribe(new Consumer<Integer>() {
                @Override
                public void accept(Integer count) {
                    Toast toast = Toast.makeText(getApplicationContext(), count + " preguntas importadas", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM | Gravity.LEFT, 0, 0);
                    toast.show();
                }
            });
    }

    @Override
    protected View getContentView() {
        return mContentView;
    }

}
