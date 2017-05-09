package nightbars.nightbars.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import nightbars.nightbars.MenuActivity;
import nightbars.nightbars.PlacesListActivity;
import nightbars.nightbars.R;
import nightbars.nightbars.SignUpActivity;
import nightbars.nightbars.helper.SessionManager;
import nightbars.nightbars.helper.TaskCallback;

public class ConexionDBLogin extends AsyncTask<String, Integer, ResultSet> {
    private Context context;
    private TaskCallback mCallback;
    public final ProgressDialog progressDialog;
    private SessionManager sessionManager;

    public ConexionDBLogin(Context context, TaskCallback callback) {
        this.context = context;
        this.mCallback = callback;
        progressDialog = new ProgressDialog(this.context, R.style.AppTheme);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Authenticating...");

        sessionManager = new SessionManager(this.context);
    }

    @Override
    protected ResultSet doInBackground(String... strings) {
        publishProgress();
        ResultSet rs = null;
        try {
            Connection conn;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + strings[0] + strings[1], "sql8170846", "6mgd7pf7cJ");

            Statement estado = conn.createStatement();
            System.out.println("Conexion establecida");
            String peticion = "SELECT username FROM User WHERE email='" + strings[2] + "' AND pass='" + strings[3] + "'";
            rs = estado.executeQuery(peticion);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(ResultSet result) {
        progressDialog.dismiss();
        try {
            if (result != null){
                if (!result.next()) {
                    Toast.makeText(context, "Datos incorrectos", Toast.LENGTH_LONG).show();
                }else{
                    /*tvGenero.setText(result.getString("genero"));
                    tvValoracion.setText(Float.toString(result.getFloat("valoracion")));
                    tvPEGI.setText(Integer.toString(result.getInt("PEGI")));
                    tvPrecio.setText(Float.toString(result.getFloat("precio")));*/

                    Toast.makeText(context, "Login correcto!", Toast.LENGTH_LONG).show();

                    sessionManager.setLogin(true);
                    sessionManager.setUsername(result.getString("username"));

                    Intent intent = new Intent(this.context, PlacesListActivity.class);
                    this.context.startActivity(intent);

                    mCallback.done();
                }
            }else {
                Toast.makeText(context, "Se ha producido un error.", Toast.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
