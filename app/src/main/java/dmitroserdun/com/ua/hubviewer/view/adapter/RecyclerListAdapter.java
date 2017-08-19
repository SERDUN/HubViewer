package dmitroserdun.com.ua.hubviewer.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.data.model.Repository;
import dmitroserdun.com.ua.hubviewer.view.listener.RecyclerViewListener;

/**
 * Created by User on 19.08.2017.
 */

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.PlaceHolder> {
    private ArrayList<Repository> repository;
    private RecyclerViewListener recyclerViewListener;


    public RecyclerListAdapter(RecyclerViewListener recyclerViewListener, ArrayList<Repository> repository) {
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
        return repository.size();
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