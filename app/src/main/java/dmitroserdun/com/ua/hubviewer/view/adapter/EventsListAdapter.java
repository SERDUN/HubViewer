package dmitroserdun.com.ua.hubviewer.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.data.model.events.Event;
import dmitroserdun.com.ua.hubviewer.data.model.repository.Repository;
import dmitroserdun.com.ua.hubviewer.view.listener.RecyclerViewListener;

/**
 * Created by User on 20.08.2017.
 */

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.PlaceHolder> {
    private List<Event> events;
    private RecyclerViewListener recyclerViewListener;


    public EventsListAdapter(RecyclerViewListener<Repository> recyclerViewListener, List<Event> events) {
        this.recyclerViewListener = recyclerViewListener;
        this.events = events;
    }


    @Override
    public EventsListAdapter.PlaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_events_card_view, parent, false);
        return new EventsListAdapter.PlaceHolder(view);
    }

    @Override
    public void onBindViewHolder(EventsListAdapter.PlaceHolder holder, int position) {
        holder.bindView(events.get(position));
//        holder.select.setChecked(position == lastCheckedPosition);


    }

    public void swapList(ArrayList<Event> repositories) {
        this.events = repositories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (events == null) ? 0 : events.size();
    }

    class PlaceHolder extends RecyclerView.ViewHolder {
        public final String EVENT_WORD = "Event";
        TextView tvEventName;
        TextView tvActor;
        TextView tvRepo;

        public PlaceHolder(View itemView) {
            super(itemView);
            tvEventName = (TextView) itemView.findViewById(R.id.tv_type_event);
            tvActor = (TextView) itemView.findViewById(R.id.tv_event_actor);
            tvRepo = (TextView) itemView.findViewById(R.id.tv_event_repo_name);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    recyclerViewListener.onClick(events.get(getAdapterPosition()));
//                }
//            });
//
//            itemView.setOnClickListener(v -> {
//                lastCheckedPosition = getAdapterPosition();
//                notifyItemRangeChanged(0, predictions.size());
//                notifyItemChanged(lastCheckedPosition);
//                recyclerViewListener.onClick(predictions.get(getAdapterPosition()));
//            });
        }


        public void bindView(Event event) {
            tvEventName.setText(event.getType().substring(0, event.getType().indexOf(EVENT_WORD)));
            tvActor.setText(event.getActor().getLogin());
            tvRepo.setText(event.getRepo().getName());


        }
    }
}
