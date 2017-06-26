package nightbars.nightbars;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

public class ShowImageActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_image);

        Intent in = getIntent();
        int id = in.getIntExtra("img_id", 0);

        ImageView iv =(ImageView)findViewById(R.id.img_to_show);
        iv.setImageResource(id);
    }
}
