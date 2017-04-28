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

import nightbars.nightbars.R;

public class ConexionDBLogin extends AsyncTask<String, Integer, ResultSet> {
    private Context context;
    private final ProgressDialog progressDialog;

    public ConexionDBLogin(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(this.context, R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
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
            String peticion = "SELECT pass FROM User WHERE email='" + strings[2] + "' AND pass='" + strings[3] + "'";
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
                    Toast.makeText(context, "Datos incorrectos", Toast.LENGTH_LONG).show();
                }else{
                    /*tvGenero.setText(result.getString("genero"));
                    tvValoracion.setText(Float.toString(result.getFloat("valoracion")));
                    tvPEGI.setText(Integer.toString(result.getInt("PEGI")));
                    tvPrecio.setText(Float.toString(result.getFloat("precio")));*/
                    Toast.makeText(context, "Login correcto!", Toast.LENGTH_LONG).show();
                    // TODO: Cambiar de Activity.
                }
            }else{
                Toast.makeText(context, "Se ha producido un error.",Toast.LENGTH_LONG).show();
            }

            progressDialog.dismiss();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
