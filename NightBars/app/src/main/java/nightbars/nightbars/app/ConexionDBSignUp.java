package nightbars.nightbars.app;

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.os.AsyncTask;
        import android.widget.Toast;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;
        import java.sql.Statement;

        import nightbars.nightbars.R;
        import nightbars.nightbars.helper.TaskCallback;

public class ConexionDBSignUp extends AsyncTask<String, Integer, Integer> {
    private Context context;
    private TaskCallback mCallback;
    private final ProgressDialog progressDialog;

    public ConexionDBSignUp(Context context, TaskCallback callback) {
        this.context = context;
        this.mCallback = callback;
        progressDialog = new ProgressDialog(this.context, R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
    }

    @Override
    protected Integer doInBackground(String... strings) {
        publishProgress();
        int rs = 0;
        try {
            Connection conn;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + strings[0] + strings[1], "sql8170846", "6mgd7pf7cJ");
            
            Statement estado = conn.createStatement();
            System.out.println("Conexion establecida");
            String peticion = "INSERT INTO User(fname, lname, username, email, pass) VALUES('"+ strings[2] + "', '" + strings[3] + "', '"+ strings[4] + "', '" + strings[5] +"', '"+ strings[6] + "')";
            rs = estado.executeUpdate(peticion);
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
    protected void onPostExecute(Integer result) {
        if (result != 0) {
            Toast.makeText(context, "Registrado con éxito!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Se ha producido un error en el registro.", Toast.LENGTH_LONG).show();
        }

        progressDialog.dismiss();
        mCallback.done();
    }
}
