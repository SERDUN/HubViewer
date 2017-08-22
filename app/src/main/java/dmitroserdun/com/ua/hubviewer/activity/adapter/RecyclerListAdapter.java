package dmitroserdun.com.ua.hubviewer.activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.data.model.repository.Repository;
import dmitroserdun.com.ua.hubviewer.activity.listener.RecyclerViewListener;

/**
 * Created by User on 19.08.2017.
 */

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.PlaceHolder> {
    private List<Repository> repository;
    private RecyclerViewListener recyclerViewListener;


    public RecyclerListAdapter(RecyclerViewListener<Repository> recyclerViewListener, List<Repository> repository) {
        this.recyclerViewListener = recyclerViewListener;
        this.repository = repository;
    }


    @Override
    public PlaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.repository_card_view, parent, false);
        return new PlaceHolder(view);
    }

    @Override
    public void onBindViewHolder(PlaceHolder holder, int position) {
        holder.bindView(repository.get(position));
//        holder.select.setChecked(position == lastCheckedPosition);


    }

    public void swapList(ArrayList<Repository> repositories) {
        this.repository = repositories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (repository == null) ? 0 : repository.size();
    }

    class PlaceHolder extends RecyclerView.ViewHolder {
        TextView tvRepoName;
        TextView tvRepoDescription;
        TextView tvRepoLanguage;

        public PlaceHolder(View itemView) {
            super(itemView);
            tvRepoName = (TextView) itemView.findViewById(R.id.tv_repo_name);
            tvRepoDescription = (TextView) itemView.findViewById(R.id.tv_repo_description);
            tvRepoLanguage = (TextView) itemView.findViewById(R.id.tv_repo_language);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewListener.onClick(repository.get(getAdapterPosition()));
                }
            });
//
//            itemView.setOnClickListener(v -> {
//                lastCheckedPosition = getAdapterPosition();
//                notifyItemRangeChanged(0, predictions.size());
//                notifyItemChanged(lastCheckedPosition);
//                recyclerViewListener.onClick(predictions.get(getAdapterPosition()));
//            });
        }


        public void bindView(Repository repo) {
            tvRepoName.setText(repo.getName());
            tvRepoDescription.setText(repo.getDescription());
            tvRepoLanguage.setText(repo.getLanguage());


        }
    }


}