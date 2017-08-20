package dmitroserdun.com.ua.hubviewer.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.data.model.directory.Directory;
import dmitroserdun.com.ua.hubviewer.view.listener.RecyclerViewListener;

/**
 * Created by User on 20.08.2017.
 */

public class ContentListAdapter extends RecyclerView.Adapter<ContentListAdapter.PlaceHolder> {
    private List<Directory> directory;
    private RecyclerViewListener recyclerViewListener;


    public ContentListAdapter(RecyclerViewListener<Directory> recyclerViewListener, List<Directory> directory) {
        this.recyclerViewListener = recyclerViewListener;
        this.directory = directory;
    }


    @Override
    public ContentListAdapter.PlaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_file_for_directory, parent, false);
        return new ContentListAdapter.PlaceHolder(view);
    }

    @Override
    public void onBindViewHolder(ContentListAdapter.PlaceHolder holder, int position) {
        holder.bindView(directory.get(position));
//        holder.select.setChecked(position == lastCheckedPosition);


    }

    public void swapList(ArrayList<Directory> directory) {
        this.directory = directory;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (directory == null) ? 0 : directory.size();
    }

    class PlaceHolder extends RecyclerView.ViewHolder {
        public final String TYPE_FILE = "file";
        public final String TYPE_DIR = "dir";
        ImageView ivTypeElement;
        TextView tvName;
        TextView tvCommet;
        TextView tvLastTime;

        public PlaceHolder(View itemView) {
            super(itemView);
            ivTypeElement = (ImageView) itemView.findViewById(R.id.iv_type_item);
            tvName = (TextView) itemView.findViewById(R.id.tv_item_name);

            itemView.setOnClickListener(v -> {
                recyclerViewListener.onClick(directory.get(getAdapterPosition()));

            });
//                @Override
//                public void onClick(View v) {
//                    recyclerViewListener.onClick(directory.get(getAdapterPosition()));
//                }
//            });
//

        }


        public void bindView(Directory repo) {
            if (repo.getType().equals(TYPE_DIR))
                ivTypeElement.setImageResource(R.drawable.ic_folder_black_24dp);
            tvName.setText(repo.getName());
//            tvRepoName.setText(repo.getName());
//            tvRepoDescription.setText(repo.getDescription());
//            tvRepoLanguage.setText(repo.getLanguage());


        }
    }
}


