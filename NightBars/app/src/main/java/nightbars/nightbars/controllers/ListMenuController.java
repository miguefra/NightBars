package nightbars.nightbars.controllers;

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
    private PlacesListActivity activity;

    public ListMenuController(PlacesListActivity activity) {
        placeList = new ArrayList<>();
        this.activity = activity;
    }

    public void getPlace() {
        new ConexionDBListMenu(this.activity, new ConexionDBListMenu.RequestCallback() {
            @Override
            public void onSuccess(ResultSet places) throws SQLException {
                placeList = new ArrayList<>();
                do {
                    place = new Place();

                    String p = places.getString("name");
                    String j = places.getString("type");

                    place.setName(p);
                    place.setType(j);

                    placeList.add(place);
                } while (places.next());

                activity.paintPlaces(placeList);
            }

            @Override
            public void onFail(String message) {

            }
        }).execute();
    }
}
