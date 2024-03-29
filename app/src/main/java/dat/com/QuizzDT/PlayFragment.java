package dat.com.QuizzDT;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btnMusic;
    Button btnMovie;
    Button btnMath;
    Button btnArt;

    public PlayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayFragment newInstance(String param1, String param2) {
        PlayFragment fragment = new PlayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);

        btnArt = view.findViewById(R.id.btnArt);
        btnMusic = view.findViewById(R.id.btnMusic);
        btnMovie = view.findViewById(R.id.btnMovie);
        btnMath = view.findViewById(R.id.btnMath);

//        QuestionData data = new QuestionData(view.getContext());

        btnArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.body, new LevelFragment())
                        .addToBackStack(null)
                        .commit();
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Nghệ thuật");
            }

        });

        btnMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.body, new LevelFragment())
                        .addToBackStack(null)
                        .commit();
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Toán học");
                //set Topic
                History.setTopic("math");
            }

        });

        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.body, new LevelFragment())
                        .addToBackStack(null)
                        .commit();
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Âm nhạc");
                History.setTopic("Music");
            }

        });

        btnMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.body, new LevelFragment())
                        .addToBackStack(null)
                        .commit();
                ((MainActivity) getActivity()).getSupportActionBar().setTitle("Điện ảnh");
                History.setTopic("Movie");
            }

        });

        return view;
    }
}