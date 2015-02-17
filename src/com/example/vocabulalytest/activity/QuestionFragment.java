package com.example.vocabulalytest.activity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.vocabulalytest.database.*;
import com.example.vocabulatytest.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragment extends Fragment {

	private View mView;
	private TextView selectionA;
	private TextView selectionB;
	private TextView selectionC;
	private TextView selectionD;
	private TextView answerA;
	private TextView answerB;
	private TextView answerC;
	private TextView answerD;
	private TextView exampleText;
	private TextView questionText;
	private TextView questionNumber;
	private RelativeLayout buttonA;
	private RelativeLayout buttonB;
	private RelativeLayout buttonC;
	private RelativeLayout buttonD;
	private Questions mQuestion;
    private String ansString;
	private int ansNumber;
	private boolean flgAnswered;

	public QuestionFragment() {

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onActivityCreated(savedInstanceState);
		int mode;
		if (getArguments() != null && getArguments().containsKey("num")) {
			questionNumber.setText("Question : "
					+ getArguments().getString("num"));
		}else{
			questionNumber.setText("Question : ?");
		}
		
		if(getArguments() != null && getArguments().containsKey("mode")){
			mode = getArguments().getInt("mode");
		}else{
			mode = 0;
		}

        //modeを引っ張ってきてアクセスするデータベースをセット
		requireQuestion(mode);

		setQuestionStatus(mode);


	}

	@Override
	public void onAttach(Activity activity) {
		// TODO 自動生成されたメソッド・スタブ
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		mView = inflater
				.inflate(R.layout.layout_answer_panel, container, false);
		selectionA = (TextView) mView.findViewById(R.id.selection_textA);
		selectionB = (TextView) mView.findViewById(R.id.selection_textB);
		selectionC = (TextView) mView.findViewById(R.id.selection_textC);
		selectionD = (TextView) mView.findViewById(R.id.selection_textD);
		answerA = (TextView) mView.findViewById(R.id.answer_textA);
		answerB = (TextView) mView.findViewById(R.id.answer_textB);
		answerC = (TextView) mView.findViewById(R.id.answer_textC);
		answerD = (TextView) mView.findViewById(R.id.answer_textD);
		exampleText = (TextView) mView.findViewById(R.id.question_example);
		questionText = (TextView) mView.findViewById(R.id.question_word);
		questionNumber = (TextView) mView.findViewById(R.id.question_number);
		OnAnswerClick lis = new OnAnswerClick();

		buttonA = (RelativeLayout) mView.findViewById(R.id.answerA);
		buttonA.setOnClickListener(lis);
		buttonB = (RelativeLayout) mView.findViewById(R.id.answerB);
		buttonB.setOnClickListener(lis);
		buttonC = (RelativeLayout) mView.findViewById(R.id.answerC);
		buttonC.setOnClickListener(lis);
		buttonD = (RelativeLayout) mView.findViewById(R.id.answerD);
		buttonD.setOnClickListener(lis);
		flgAnswered = false;

		// DataBase questionData = new DataBase(getActivity());
		//requireQuestion(mode);

		//setQuestionStatus();

		return mView;
	}

	@Override
	public void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroyView();
	}

	@Override
	public void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
	}

	public void setQuestionNumber(int num) {
		questionNumber.setText("Question : " + String.valueOf(num));
	}

	private void requireNextQuestion(boolean ans) {
		QuestionPagerFragment activity = (QuestionPagerFragment) getFragmentManager()
				.findFragmentByTag("pager");

		if (activity instanceof QuestionFragmentInterface) {
			QuestionFragmentInterface listener = (QuestionFragmentInterface) activity;
			listener.nextQuestion(ans);
		}
	}

	private boolean checkAnswer(int num) {
		selectAnsBackground(num);
		colorSetBackground(ansNumber);
		answerA.setVisibility(View.VISIBLE);
		answerB.setVisibility(View.VISIBLE);
		answerC.setVisibility(View.VISIBLE);
		answerD.setVisibility(View.VISIBLE);

		if (num == ansNumber) {
            sendAnswerStatus(true);
			return true;
		} else {
           sendAnswerStatus(false);
			return false;
		}
	}
    private void requireMenu(){
        Activity activity = getActivity();
        if(activity instanceof OnClickStartListener){
            OnClickStartListener listener = (OnClickStartListener)activity;
            listener.startMenu(this);
        }
    }

	private void setQuestionStatus(int mode) {
		ArrayList<DBWordEntity> entity = mQuestion.getQuestion();

        if(entity == null || entity.size() <4){
            if(mode == -1) {
                Toast.makeText(getActivity(), "あなたには苦手単語がありません", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getActivity(), "要求されたセクションはオープンできませんでした", Toast.LENGTH_LONG).show();
            }
            requireMenu();
        }else {

            ansNumber = mQuestion.getCorrect();


            ArrayList<String> wordList = new ArrayList<String>();
            ArrayList<String> meanList = new ArrayList<String>();

            for (DBWordEntity en : entity) {
                wordList.add(en.getWord());
                meanList.add(en.getMean());

            }
            exampleText.setText(entity.get(ansNumber).getExam());
            ansString = wordList.get(ansNumber);
            questionText.setText(wordList.get(ansNumber));
            answerA.setText(wordList.get(0));
            answerB.setText(wordList.get(1));
            answerC.setText(wordList.get(2));
            answerD.setText(wordList.get(3));
            selectionA.setText(meanList.get(0));
            selectionB.setText(meanList.get(1));
            selectionC.setText(meanList.get(2));
            selectionD.setText(meanList.get(3));
        }
	}

	private void selectAnsBackground(int num) {
		switch (num) {
		case 0:
			buttonA.setBackgroundColor(Color.parseColor("#AAAAAA"));
			break;
		case 1:
			buttonB.setBackgroundColor(Color.parseColor("#AAAAAA"));
			break;
		case 2:
			buttonC.setBackgroundColor(Color.parseColor("#AAAAAA"));
			break;
		case 3:
			buttonD.setBackgroundColor(Color.parseColor("#AAAAAA"));
			break;

		}
	}

	private void colorSetBackground(int num) {
		switch (num) {
		case 0:
			buttonA.setBackgroundColor(Color.parseColor("#FFDDDD"));
			break;
		case 1:
			buttonB.setBackgroundColor(Color.parseColor("#DDDDFF"));
			break;
		case 2:
			buttonC.setBackgroundColor(Color.parseColor("#FFFFDD"));
			break;
		case 3:
			buttonD.setBackgroundColor(Color.parseColor("#DDFFDD"));
			break;

		}

	}

    /**
        データベースに問題を要求する
     */
	private void requireQuestion(int mode) {
		DBHelper helper = new DBHelper(getActivity(), null, 1);
		SQLiteDatabase db = helper.getReadableDatabase();
		DBDAO dao = new DBDAO(db);
		Select s = new Select(dao);
		if(mode == 0){
			mQuestion = s.getAllQuestions();
		}else if(mode > 0){
			mQuestion = s.getSectionQuestions(mode);
		}else{
			mQuestion = s.getErrorQuestions(0.5);
		}
	}

    /**
     * データベースに回答結果を出力する
     */
    private void sendAnswerStatus(boolean ans){
        DBHelper helper = new DBHelper(getActivity(),null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        DBDAO dao = new DBDAO(db);

        DBWordEntity dbent = dao.findAnyWord(ansString).get(0);
        int toans = dbent.getNumberOfTimes()+1;
        dao.updateNumberOfTimes(ansString,toans);
        int tocor = dbent.getNumberOfCorrect();
        if(ans){
            tocor++;

            dao.updateNumberOfCorrect(ansString,tocor);
        }
        double neerr = (double)tocor/(double)toans;
        dao.updateError(ansString,neerr);
        helper.close();
    }

	class OnAnswerClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO 自動生成されたメソッド・スタブ
			if (!flgAnswered) {
				switch (v.getId()) {
				case R.id.answerA:
					// answerCheck and requireNextQuestion
					requireNextQuestion(checkAnswer(0));
					break;
				case R.id.answerB:
					requireNextQuestion(checkAnswer(1));
					break;
				case R.id.answerC:
					requireNextQuestion(checkAnswer(2));
					break;
				case R.id.answerD:
					requireNextQuestion(checkAnswer(3));
					break;

				}
				flgAnswered = true;
			}
		}

	}

}
