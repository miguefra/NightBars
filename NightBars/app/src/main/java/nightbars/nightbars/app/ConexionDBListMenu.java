package nightbars.nightbars.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import nightbars.nightbars.helper.AppConfig;

public class ConexionDBListMenu extends AsyncTask<String, Integer, ResultSet> {
    private Context context;
    private final ProgressDialog progressDialog;
    private RequestCallback callback;

    public ConexionDBListMenu(Context context, RequestCallback callback) {
        this.context = context;
        this.callback = callback;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading Places...");
    }

    @Override
    protected ResultSet doInBackground(String... strings) {
        publishProgress();
        ResultSet rs = null;
        try {
            Connection conn;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + AppConfig.IP + AppConfig.DATABASE, "sql8170846", "6mgd7pf7cJ");

            Statement estado = conn.createStatement();
            System.out.println("Conexion establecida");
            String peticion = "SELECT * FROM Place";
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
        try {
            if (result != null){
                if (!result.next()) {
                    Toast.makeText(context, "No se han encontrado resultados.", Toast.LENGTH_LONG).show();
                } else {
                    /*tvGenero.setText(result.getString("genero"));
                    tvValoracion.setText(Float.toString(result.getFloat("valoracion")));
                    tvPEGI.setText(Integer.toString(result.getInt("PEGI")));
                    tvPrecio.setText(Float.toString(result.getFloat("precio")));*/

                    callback.onSuccess(result);
                }
            } else {
                Toast.makeText(context, "Se ha producido un error.",Toast.LENGTH_LONG).show();
            }

            progressDialog.dismiss();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public interface RequestCallback {
        void onSuccess(ResultSet persons) throws SQLException;
        void onFail(String message);
    }
}
