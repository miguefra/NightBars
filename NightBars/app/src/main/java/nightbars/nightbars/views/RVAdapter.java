package nightbars.nightbars.views;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nightbars.nightbars.R;
import nightbars.nightbars.model.Place;
import nightbars.nightbars.presenters.RecyclerViewOnItemClickListener;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PlaceViewHolder> {

    private List<Place> places;
    private static RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public List<Place> getPlaces() {
        return places;
    }

    public RVAdapter(RecyclerViewOnItemClickListener recyclerViewOnItemClickListener) {
        places = new ArrayList<>();
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    public void setplaces(List<Place> places){
        this.places = new ArrayList<>(places);
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        private TextView placeName;
        private TextView placeType;
        private ImageView placeImage;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            placeName = (TextView)itemView.findViewById(R.id.p_name);
            placeType = (TextView)itemView.findViewById(R.id.p_type);
            placeImage = (ImageView)itemView.findViewById(R.id.place_photo);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewOnItemClickListener.onClick(view, getAdapterPosition());
        }
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_card_view, parent, false);
        PlaceViewHolder placeViewHolder = new PlaceViewHolder(view);

        return placeViewHolder;
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        holder.placeName.setText(places.get(position).getName());
        holder.placeType.setText(places.get(position).getType());
        holder.placeImage.setImageResource(R.drawable.espit_chupitos);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
