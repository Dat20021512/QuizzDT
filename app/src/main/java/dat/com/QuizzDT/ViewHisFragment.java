package dat.com.QuizzDT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import dat.com.QuizzDT.databinding.FragmentViewHisBinding;

public class ViewHisFragment extends Fragment {

    FragmentViewHisBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentViewHisBinding.inflate(inflater, container, false);
        binding.historyResultRec.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.historyResultRec.setAdapter(new HisAdapter(getContext(),History.history_list));


        return binding.getRoot();
    }

}