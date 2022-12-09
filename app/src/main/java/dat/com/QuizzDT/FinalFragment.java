package dat.com.QuizzDT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FinalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FinalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AppCompatButton btnComplete;
    private AppCompatButton btnShare;
    RelativeLayout relativeLayout;
    TextView textFinal;

    public FinalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FinalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FinalFragment newInstance(String param1, String param2) {
        FinalFragment fragment = new FinalFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_final, container, false);

        btnComplete = view.findViewById(R.id.btnComplete);
        btnShare = view.findViewById(R.id.btnShare);
        relativeLayout = view.findViewById(R.id.relativeLayout);
        TextView textFinal = view.findViewById(R.id.textFinal);

        textFinal.setText("Bạn đã hoàn thành " + History.getCorrectCount() +"/5 câu");

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.body, new PlayFragment())
                        .addToBackStack(null)
                        .commit();

                ((MainActivity) getActivity()).getSupportActionBar().setTitle("QuizzDT");

                //addHistory
                History.addHistory();

                //reset so cau tra loi dung khi bam nut hoan thanh
                History.resetCorrectCount();
            }

        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
        return view;
    }

    private void share() {
        Bitmap bitmap = getBitmapFromView(relativeLayout);
        try {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Tôi đã trả lời đúng " + History.getCorrectCount() + " câu hỏi cấp độ " + History.getLevel() + " ở chủ đề " + History.getTopic());
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("ResourceAsColor")
    private Bitmap getBitmapFromView(View view){
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if(bgDrawable != null){
            bgDrawable.draw(canvas);
        }else {
            canvas.drawColor(android.R.color.white);
        }
        return returnedBitmap;
    }
}