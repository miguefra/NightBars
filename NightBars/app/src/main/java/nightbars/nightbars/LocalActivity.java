package nightbars.nightbars;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nightbars.nightbars.controllers.ListMenuController;
import nightbars.nightbars.model.Place;

public class LocalActivity extends AppCompatActivity {

    public static final String PLACE_POSITION = "PLACE_POSITION";
    public static final String PLACE_NAME = "PLACE_NAME";
    private static int position;

    private TextView name;
    private TextView type;
    private TextView price;
    private TextView location;
    private TextView score;
    private ListView photos;
    private ListView reviews;


    private ImageView place_photo;
    private ImageView expanded_photo;

    private String placeName;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        //Hide action bar
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        position = (int) extras.get(PLACE_POSITION);

        //presenter = new PeopleDetailPresenter(id, this);

        name = (TextView) findViewById(R.id.place_name_detail);
        type = (TextView) findViewById(R.id.place_type_detail);
        price = (TextView) findViewById(R.id.p_price);
        score = (TextView) findViewById(R.id.p_score);

        place_photo = (ImageButton) findViewById(R.id.place_profile_photo);
        place_photo.setBackgroundResource(R.color.cardview_light_background);
        place_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocalActivity.this, ShowImageActivity.class);
                intent.putExtra("img_id", R.drawable.app_logo);
                startActivityForResult(intent,0);
            }
        });

        //adapter = new ReviewsAdapter();
        //reviews.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadingPlaceDetails();
    }

    public void paintDetails(Place place) {

        placeName = place.getName();
        name.setText(placeName);
        type.setText(place.getType());
        score.setText("Score: " + place.getScore());
        price.setText("Price: " + place.getPrice());

        //adapter.setReviews(place.getReviews());

        // Se usa Picasso para mostrar la imagen.
        //Picasso.with(this).load(place.getPhoto()).resize(300,300).centerCrop().into(photo);
    }

    public void loadingPlaceDetails() {
        paintDetails(ListMenuController.getInstance(this).getPlaceList().get(position));
    }

    public void comoLlegar(View view) {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        intent.putExtra(LocalActivity.PLACE_NAME, placeName);
        startActivity(intent);
        finish();
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
