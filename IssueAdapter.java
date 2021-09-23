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

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.ViewHolder> {
    View view;
    private int lastPosition = -1;
    ArrayList<IssueContent> arrayListIssue;
    Context context;

    public IssueAdapter(ArrayList<IssueContent> arrayListIssue, Context context) {
        this.arrayListIssue = arrayListIssue;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.issuesrow,parent,false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final IssueContent temp =  arrayListIssue.get(position);

        holder.Issue.setText(temp.getIssue());

        holder.Issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(context.getApplicationContext(), temp.getDetail(), Toast.LENGTH_SHORT).show();
                Intent intentBeds = new Intent(context,IssueList.class);
                intentBeds.putExtra("IssueDetail",temp.getDetail());
                intentBeds.putExtra("HTMLURL",temp.getHtmlUrl());

                intentBeds.setFlags(intentBeds.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentBeds);
            }
        });

        Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        view.startAnimation(animation);
        lastPosition = position;
    }

    @Override
    public int getItemCount() {
        return arrayListIssue.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView Issue;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Issue = itemView.findViewById(R.id.IssueName);

        }
    }
}