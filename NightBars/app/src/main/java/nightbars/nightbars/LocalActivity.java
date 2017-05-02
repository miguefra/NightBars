package nightbars.nightbars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nightbars.nightbars.controllers.ListMenuController;
import nightbars.nightbars.model.Place;

public class LocalActivity extends AppCompatActivity {

    public static final String PLACE_POSITION = "PLACE_POSITION";
    private static int position;
    //private PeopleDetailPresenter presenter;
    private TextView name;
    private TextView type;
    private TextView price;
    private TextView location;
    private TextView score;
    private ListView photos;
    private ListView reviews;
    //ReviewsAdapter adapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        position = (int) extras.get(PLACE_POSITION);

        //presenter = new PeopleDetailPresenter(id, this);

        name = (TextView) findViewById(R.id.place_name_detail);
        type = (TextView) findViewById(R.id.place_type_detail);
        /*price = (TextView) findViewById(R.id.);
        location = (TextView) findViewById(R.id.);
        score = (TextView) findViewById(R.id.);
        photos = (ListView) findViewById(R.id.);
        reviews = (ListView) findViewById(R.id.);*/

        //adapter = new ReviewsAdapter();
        //reviews.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadingPlaceDetails();
    }

    public void paintDetails(Place place) {

        name.setText(place.getName());
        type.setText(place.getType());

        //adapter.setReviews(place.getReviews());

        // Se usa Picasso para mostrar la imagen.
        //Picasso.with(this).load(place.getPhoto()).resize(300,300).centerCrop().into(photo);
    }

    public void loadingPlaceDetails() {
        paintDetails(ListMenuController.getInstance(this).getPlaceList().get(position));
    }

    /*private class ReviewsAdapter extends BaseAdapter {

        private List<String> reviews;

        public ReviewsAdapter() {
            this.reviews = new ArrayList<>();
        }

        public List<String> getReviews() {
            return reviews;
        }


        @Override
        public int getCount() {
            return reviews.size();
        }

        @Override
        public Object getItem(int i) {
            return reviews.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rootview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout., viewGroup,false);
            TextView textView = (TextView) rootview.findViewById(R.id.);
            String hobbie = (String) getItem(i);
            textView.setText(hobbie);

            return rootview;
        }

        public void setHobbies(List<String> hobbies) {
            this.reviews = hobbies;
        }
    }*/
}
