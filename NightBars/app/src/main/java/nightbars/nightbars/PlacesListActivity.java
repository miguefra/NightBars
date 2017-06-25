package nightbars.nightbars;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.apache.commons.net.ftp.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nightbars.nightbars.controllers.ListMenuController;
import nightbars.nightbars.helper.SessionManager;
import nightbars.nightbars.model.Place;
import nightbars.nightbars.presenters.RecyclerViewOnItemClickListener;
import nightbars.nightbars.views.RVAdapter;

public class PlacesListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SessionManager sessionManager;
    private RecyclerView recyclerView;
    private ListMenuController listMenuController;
    private RVAdapter adapter;

    @InjectView(R.id.nav_header_username)
    public TextView navHeaderUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sessionManager = new SessionManager(getApplicationContext());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        adapter = new RVAdapter(new RecyclerViewOnItemClickListener(){
            @Override
            public void onClick(View view, int position) {
                printDetails(position);
            }
        });
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the Add Place activity.
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listMenuController = ListMenuController.getInstance(PlacesListActivity.this, this);

        try {
            Boolean b = this.connectFTP();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Boolean connectFTP(/*String server, int portNumber,
                                        String user, String password, String filename, File localFile*/)
            throws IOException {
        FTPClient ftp = null;

        try {
            ftp = new FTPClient();

            ftp.connect("ftp://ftp.byethost31.com", 21);

            System.out.println("Connected. Reply: " + ftp.getReplyString());
            ftp.login("b31_20293105", "nightbars12345");

            System.out.println("Que pedo");
            System.out.println("login");

            /*ftp.setFileType(FTP.BINARY_FILE_TYPE);
            Log.d(LOG_TAG, "Downloading");
            ftp.enterLocalPassiveMode();

            OutputStream outputStream = null;
            boolean success = false;
            try {
                outputStream = new BufferedOutputStream(new FileOutputStream(
                        localFile));
                success = ftp.retrieveFile(filename, outputStream);
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
            }

            return success;*/
            return true;
        } finally {
            if (ftp != null) {
                ftp.logout();
                ftp.disconnect();
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.places_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.logout) {
            sessionManager.setLogin(false);

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivityForResult(intent, 0);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void printDetails(int id) {
        Intent intent = new Intent(this, LocalActivity.class);
        intent.putExtra(LocalActivity.PLACE_POSITION, id);
        startActivity(intent);
    }

    public void paintPlaces(List<Place> place) {
        // Pintamos la lista de places
        adapter.setplaces(place);
        adapter.notifyDataSetChanged();
        //recyclerView.setAdapter(adapter);

        this.navHeaderUsername = (TextView)findViewById(R.id.nav_header_username);
        this.navHeaderUsername.setText(sessionManager.getUsername());
        this.navHeaderUsername.setTextColor(Color.BLACK);
    }

    @Override
    protected void onResume() {
        super.onResume();

        listMenuController.getPlace();
    }
}
