package sudgoyal.gitusers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder>
{
    View view;
    private int lastPosition = -1;
    ArrayList<repoData> arrayListRepo;
    Context context;

    public ReposAdapter(ArrayList<repoData> arrayListRepo, Context context) {
        this.arrayListRepo = arrayListRepo;
        this.context = context;
    }

    @NonNull
    @Override
    public ReposAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.reporow,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReposAdapter.ViewHolder holder, int position) {
        final repoData temp =  arrayListRepo.get(position);
        
        holder.repo.setText(temp.getRepoName());

        holder.repo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(context.getApplicationContext(), temp.getGitUrl(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,Activity_Issues.class);
                intent.putExtra("repourl",temp.getGitUrl());
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

        Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        view.startAnimation(animation);
        lastPosition = position;

    }

    @Override
    public int getItemCount() {
        return arrayListRepo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView repo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            repo = itemView.findViewById(R.id.repoName);

        }
    }
}
