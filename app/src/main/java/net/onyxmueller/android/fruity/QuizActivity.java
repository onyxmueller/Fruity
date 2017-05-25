package net.onyxmueller.android.fruity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import net.onyxmueller.android.fruity.data.Fruit;
import net.onyxmueller.android.fruity.views.AnswerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity implements
        AnswerView.OnAnswerSelectedListener {
    private static final String TAG = QuizActivity.class.getSimpleName();

    //Number of quiz answers
    public static final int ANSWER_COUNT = 5;

    public static final String EXTRA_FRUITS = "fruitList";
    public static final String EXTRA_ANSWER = "selectedFruit";
    private static final String EXTRA_SELECTED_ANSWER_INDEX = "selectedAnswerIndex";

    private TextView questionText;
    private TextView correctText;
    private AnswerView answerSelect;

    public static Intent newIntent(Context context, Cursor cursor) {
        List<Fruit> currentFruitList = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            currentFruitList.add(new Fruit(cursor));
        }
        Collections.shuffle(currentFruitList);
        List<Fruit> randomFruitList = currentFruitList.subList(0, ANSWER_COUNT);
        Fruit randomlySelectedFruit = randomFruitList.get(new Random().nextInt(randomFruitList.size()));

        Intent intent = new Intent(context, QuizActivity.class);
        intent.putParcelableArrayListExtra(QuizActivity.EXTRA_FRUITS, new ArrayList<>(randomFruitList));
        intent.putExtra(QuizActivity.EXTRA_ANSWER, randomlySelectedFruit);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = (TextView) findViewById(R.id.text_question);
        correctText = (TextView) findViewById(R.id.text_correct);
        answerSelect = (AnswerView) findViewById(R.id.answer_select);

        answerSelect.setOnAnswerSelectedListener(this);

        List<Fruit> fruits = getIntent().getParcelableArrayListExtra(EXTRA_FRUITS);
        Fruit selected = getIntent().getParcelableExtra(EXTRA_ANSWER);
        buildQuestion(fruits, selected);

        if (savedInstanceState != null && savedInstanceState.containsKey(EXTRA_SELECTED_ANSWER_INDEX)) {
            answerSelect.setCheckedIndex(savedInstanceState.getInt(EXTRA_SELECTED_ANSWER_INDEX));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(EXTRA_SELECTED_ANSWER_INDEX, answerSelect.getCheckedIndex());
    }

    private void buildQuestion(List<Fruit> fruits, Fruit selected) {
        String question = getString(R.string.question_text, selected.commonName);
        questionText.setText(question);

        //Load answer strings
        ArrayList<String> options = new ArrayList<>();
        for (Fruit item : fruits) {
            options.add(item.scientificName);
        }

        answerSelect.loadAnswers(options, selected.scientificName);
    }

    /* Answer Selection Callbacks */

    @Override
    public void onCorrectAnswerSelected() {
        updateResultText();
    }

    @Override
    public void onWrongAnswerSelected() {
        updateResultText();
    }

    private void updateResultText() {
        correctText.setTextColor(answerSelect.isCorrectAnswerSelected() ?
                ContextCompat.getColor(this, R.color.colorCorrect) : ContextCompat.getColor(this, R.color.colorWrong)
        );
        correctText.setText(answerSelect.isCorrectAnswerSelected() ?
                R.string.answer_correct : R.string.answer_wrong
        );
    }
}
