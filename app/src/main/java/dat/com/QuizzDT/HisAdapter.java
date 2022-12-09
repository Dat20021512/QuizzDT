package dat.com.QuizzDT;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HisAdapter extends RecyclerView.Adapter<HisAdapter.HisViewHolder> {

    Context context;
    ArrayList<History> list;
    public HisAdapter(Context context, ArrayList<History> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_his, null);
        return new HisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HisViewHolder holder, int position) {
        History item = list.get(position);



        holder.result.setText(item.correctCount + "/5");
        holder.category.setText(item.topic);
        holder.level.setText(item.level);
        holder.date.setText(item.initTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HisViewHolder extends RecyclerView.ViewHolder{
        TextView result;
        TextView category;
        TextView level;
        TextView date;

        public HisViewHolder(@NonNull View itemView) {
            super(itemView);
            result = itemView.findViewById(R.id.result);
            category = itemView.findViewById(R.id.topic);
            level = itemView.findViewById(R.id.level);
            date = itemView.findViewById(R.id.date);
        }

    }
}
