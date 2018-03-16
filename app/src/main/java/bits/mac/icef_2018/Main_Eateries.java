package bits.mac.icef_2018;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.macbitsgoa.icef.R;

import bits.mac.icef_2018.Adapters.Adapter_Eateries;

/**
 * Created by aayush on 18/12/17.
 */

@SuppressWarnings("ALL")
public class Main_Eateries extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter_Eateries adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eateries);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int colorPrimary = ContextCompat.getColor(this, R.color.colorPrimary);
            getWindow().setStatusBarColor(colorPrimary);
            getWindow().setNavigationBarColor(colorPrimary);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        if (myToolbar != null) {
            myToolbar.setNavigationOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onBackPressed();
                        }
                    });
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }


        recyclerView = findViewById(R.id.RV_Eateries);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (position) {
                    case 2:              //A Mess
                    case 3:             //C Mess
                    case 5:            //ice and spice
                    case 6:           //food king
                    case 8:          //NC_A
                    case 9:         //NC_C

                        return 1;
                    case 0:              //IC
                    case 1:             //RC
                    case 4:            //Gaja Laxmi Snacks
                    case 10:          //Monginies
                    case 7:          //Dominoes

                        return 2;

                    //Span 1

                }
                throw new IllegalStateException("internal error");
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter_Eateries(this);
        recyclerView.setAdapter(adapter);

    }
}
