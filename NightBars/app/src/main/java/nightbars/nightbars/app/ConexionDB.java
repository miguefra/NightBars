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
            conn = DriverManager.getConnection("jdbc:mysql://"+strings[0]+strings[1], "sql8170846", "6mgd7pf7cJ");

            Statement estado = conn.createStatement();
            System.out.println("Conexion establecida");
            String peticion = "INSERT INTO User(name, email, pass) VALUES("+ strings[2] +", "+ strings[3] +", "+ strings[4] + ")";
            estado.executeUpdate(peticion);

            // TODO: Devolver respuesta correcta.
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO: Resolve.
    @Override
    protected ResultSet doInBackground(String... strings) {

        /*try {
            Connection conn;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+strings[0]+strings[1], "sql8170846", "6mgd7pf7cJ");

            Statement estado = conn.createStatement();
            System.out.println("Conexion establecida");
            String peticion = "INSERT INTO User(name, email, pass) VALUES("+ strings[2] +", "+ strings[3] +", "+ strings[4] + ")";
            estado.executeUpdate(peticion);

            // TODO: Devolver respuesta correcta.
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        return null;
    }

    @Override
    protected void onPostExecute(ResultSet result) {

        // TODO: Modificar para mostrar mensaje de éxito o error.
        try {
            if (result != null){
                if (!result.next()) {
                    //Toast.makeText(context, "Datos incorrectos", Toast.LENGTH_LONG).show();
                }else{
                    /*tvGenero.setText(result.getString("genero"));
                    tvValoracion.setText(Float.toString(result.getFloat("valoracion")));
                    tvPEGI.setText(Integer.toString(result.getInt("PEGI")));
                    tvPrecio.setText(Float.toString(result.getFloat("precio")));*/
                }
            }else{
                //Toast.makeText(context, "No está en la base de datos",Toast.LENGTH_LONG).show();
            }
            Toast.makeText(context, "Insertado correctamente",Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
