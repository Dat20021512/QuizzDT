package dat.com.QuizzDT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dat.com.QuizzDT.databinding.FragmentAnswerBinding;
public class AnswerFragment extends Fragment {

    FragmentAnswerBinding binding;

    List<Question> questionItems;
    int currentQuestionCount = 0;

    //final int question_progress = (int) Math.ceil(100/questionItems.size());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAnswerBinding.inflate(inflater,container,false);


        //get all questions
        try {
            loadQuestion(History.getTopic(), History.getLevel());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //shuffle (random) the questions
        Collections.shuffle(questionItems);

        //load first question
        DisplayQuestion(currentQuestionCount);

        //Chon dap an xong next cau hoi
        binding.btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check correct answer
                if(questionItems.get(currentQuestionCount).getAnswer1().equals(questionItems.get(currentQuestionCount).getQuestionAnswer())){
                    History.increaseCorrectCount();
                }

                //load next question neu co
                if(currentQuestionCount < 4){  // 4: chi chon 5 cau hoi
                    currentQuestionCount++;
                    DisplayQuestion(currentQuestionCount);
                }else{
                    //go to result Fragment
                    FinalFragment resultFragment = new FinalFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.body, resultFragment).addToBackStack(null).commit();


                }
            }
        });

        binding.Incorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check correct answer
                if(questionItems.get(currentQuestionCount).getAnswer2().equals(questionItems.get(currentQuestionCount).getQuestionAnswer())){
                    History.increaseCorrectCount();
                }

                //load next question neu co
                if(currentQuestionCount < 4){
                    currentQuestionCount++;
                    DisplayQuestion(currentQuestionCount);
                }else{
                    //go to result Fragment
                    FinalFragment resultFragment = new FinalFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.body, resultFragment).addToBackStack(null).commit();

                }
            }
        });


        return binding.getRoot();
    }

    //load Json file
    public  String loadJSONFromAsset()
            throws JSONException {
        String json = "";
        try{
            InputStream is = getActivity().getAssets().open("Question.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
        }
        return json;
    }

    //make list with all question
    public  void loadQuestion(String topic, String difficulty) throws JSONException {
        questionItems = new ArrayList<>();
        //load all question to string jsonStr
        String jsonStr = loadJSONFromAsset();

        //load all data into the list
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);

            JSONArray all = jsonObj.getJSONObject(topic).getJSONArray(difficulty);
            for(int i = 0; i < all.length(); i++){
                JSONObject question = all.getJSONObject(i);

                String questionString = question.getString("question");
                String answer1String = question.getString("answer1");
                String answer2String = question.getString("answer2");
                String correctString = question.getString("correct");

                questionItems.add(new Question(
                        questionString,
                        answer1String,
                        answer2String,
                        correctString
                ));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    //hien thi cau hoi
    private void DisplayQuestion(int number){
        binding.textAnswer.setText(questionItems.get(number).getQuestionText());
        updateQuesQuantity();
    }

    //PROGRESS BAR
    //update cau hoi hien tai/tong so cau hoi
    private void updateQuesQuantity(){
        binding.textProgess.setText(currentQuestionCount+1 + "/5");

        int progress = binding.progressBar.getProgress();

        binding.progressBar.setProgress(progress + 20);

    }

}