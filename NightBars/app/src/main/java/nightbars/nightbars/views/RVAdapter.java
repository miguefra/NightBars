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
        private ImageView star1Image;
        private ImageView star2Image;
        private ImageView star3Image;
        private ImageView star4Image;
        private ImageView star5Image;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            placeName = (TextView)itemView.findViewById(R.id.p_name);
            placeType = (TextView)itemView.findViewById(R.id.p_type);
            placeImage = (ImageView)itemView.findViewById(R.id.place_photo);
            star1Image = (ImageView)itemView.findViewById(R.id.star1);
            star2Image = (ImageView)itemView.findViewById(R.id.star2);
            star3Image = (ImageView)itemView.findViewById(R.id.star3);
            star4Image = (ImageView)itemView.findViewById(R.id.star4);
            star5Image = (ImageView)itemView.findViewById(R.id.star5);

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
        holder.placeImage.setImageResource(R.drawable.app_logo);

        if (places.get(position).getScore() == 5) {
            holder.star1Image.setVisibility(View.VISIBLE);
            holder.star2Image.setVisibility(View.VISIBLE);
            holder.star3Image.setVisibility(View.VISIBLE);
            holder.star4Image.setVisibility(View.VISIBLE);
            holder.star5Image.setVisibility(View.VISIBLE);
        } else if (places.get(position).getScore() == 4) {
            holder.star1Image.setVisibility(View.VISIBLE);
            holder.star2Image.setVisibility(View.VISIBLE);
            holder.star3Image.setVisibility(View.VISIBLE);
            holder.star4Image.setVisibility(View.VISIBLE);
            holder.star5Image.setVisibility(View.INVISIBLE);
        } else if (places.get(position).getScore() == 3) {
            holder.star1Image.setVisibility(View.VISIBLE);
            holder.star2Image.setVisibility(View.VISIBLE);
            holder.star3Image.setVisibility(View.VISIBLE);
            holder.star4Image.setVisibility(View.INVISIBLE);
            holder.star5Image.setVisibility(View.INVISIBLE);
        } else if (places.get(position).getScore() == 2) {
            holder.star1Image.setVisibility(View.VISIBLE);
            holder.star2Image.setVisibility(View.VISIBLE);
            holder.star3Image.setVisibility(View.INVISIBLE);
            holder.star4Image.setVisibility(View.INVISIBLE);
            holder.star5Image.setVisibility(View.INVISIBLE);
        } else if (places.get(position).getScore() == 1) {
            holder.star1Image.setVisibility(View.VISIBLE);
            holder.star2Image.setVisibility(View.INVISIBLE);
            holder.star3Image.setVisibility(View.INVISIBLE);
            holder.star4Image.setVisibility(View.INVISIBLE);
            holder.star5Image.setVisibility(View.INVISIBLE);
        } else {
            holder.star1Image.setVisibility(View.INVISIBLE);
            holder.star2Image.setVisibility(View.INVISIBLE);
            holder.star3Image.setVisibility(View.INVISIBLE);
            holder.star4Image.setVisibility(View.INVISIBLE);
            holder.star5Image.setVisibility(View.INVISIBLE);
        }
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
