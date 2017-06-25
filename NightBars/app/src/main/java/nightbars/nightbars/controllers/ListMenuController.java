package nightbars.nightbars.controllers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nightbars.nightbars.PlacesListActivity;
import nightbars.nightbars.app.ConexionDBListMenu;
import nightbars.nightbars.model.Place;

public class ListMenuController extends AppCompatActivity {
    private Place place;
    private List<Place> placeList;
    private Context context;
    private static ListMenuController instance;
    private PlacesListActivity placesListActivity;

    public ListMenuController(Context context) {
        placeList = new ArrayList<>();
        this.context = context;
    }

    public ListMenuController(Context context, PlacesListActivity placesListActivity) {
        placeList = new ArrayList<>();
        this.context = context;
        this.placesListActivity = placesListActivity;
    }

    public static ListMenuController getInstance(Context context) {
        if (instance == null) {
            instance = new ListMenuController(context);
        }

        return instance;
    }

    public static ListMenuController getInstance(Context context, PlacesListActivity placesListActivity) {
        if (instance == null) {
            instance = new ListMenuController(context, placesListActivity);
        }

        return instance;
    }

    public void getPlace() {
        new ConexionDBListMenu(this.context, new ConexionDBListMenu.RequestCallback() {
            @Override
            public void onSuccess(ResultSet places) throws SQLException {
                placeList = new ArrayList<>();
                do {
                    place = new Place();

                    String p = places.getString("name");
                    String j = places.getString("type");
                    int s = places.getInt("score");
                    int r = places.getInt("price");

                    place.setName(p);
                    place.setType(j);
                    place.setScore(s);
                    place.setPrice(r);

                    placeList.add(place);
                } while (places.next());


                placesListActivity.paintPlaces(placeList);
            }

            @Override
            public void onFail(String message) {
            }
        }).execute();
    }

    public List<Place> getPlaceList() {
        return this.placeList;
    }
}
