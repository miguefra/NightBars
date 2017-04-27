package nightbars.nightbars.app;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB extends AsyncTask<String, Void, ResultSet> {
    private Context context;

    public ConexionDB(Context context) {
        this.context = context;
    }

    public ResultSet signUpUser(String... strings) {
        try {
            Connection conn;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+strings[1]+strings[2], "sql8170846", "6mgd7pf7cJ");

            Statement estado = conn.createStatement();
            System.out.println("Conexion establecida");
            String peticion = "INSERT INTO User(fname, lname, username, email, pass) VALUES('"+ strings[3] +"', '"+ strings[4] +"', '"+ strings[5] + "', '" + strings[6] +"', '"+ strings[7] + "')";
            int rs = estado.executeUpdate(peticion);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // TODO: Devolver respuesta correcta.
        return null;
    }

    public ResultSet loginUser(String... strings) {
        ResultSet rs = null;
        try {
            Connection conn;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+strings[1]+strings[2], "sql8170846", "6mgd7pf7cJ");

            Statement estado = conn.createStatement();
            System.out.println("Conexion establecida");
            String peticion = "SELECT pass FROM User WHERE email='" + strings[3] + "'";
            rs = estado.executeQuery(peticion);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // TODO: Devolver respuesta correcta.
        return rs;
    }

    // TODO: Completar.
    @Override
    protected ResultSet doInBackground(String... strings) {
        ResultSet rs = null;
        switch (strings[0]) {
            case "signUpUser": rs = signUpUser(strings);
                break;
            case "loginUser": rs = loginUser(strings);
                break;
        }

        return rs;
    }

    @Override
    protected void onPostExecute(ResultSet result) {

        // TODO: Modificar para mostrar mensaje de éxito o error.
        try {
            if (result != null){
                if (!result.next()) {
                    Toast.makeText(context, "Datos incorrectos", Toast.LENGTH_LONG).show();
                }else{
                    /*tvGenero.setText(result.getString("genero"));
                    tvValoracion.setText(Float.toString(result.getFloat("valoracion")));
                    tvPEGI.setText(Integer.toString(result.getInt("PEGI")));
                    tvPrecio.setText(Float.toString(result.getFloat("precio")));*/
                    String p = result.getString("pass");
                    Toast.makeText(context, p, Toast.LENGTH_LONG).show();
                }
            }else{
                //Toast.makeText(context, "No está en la base de datos",Toast.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
