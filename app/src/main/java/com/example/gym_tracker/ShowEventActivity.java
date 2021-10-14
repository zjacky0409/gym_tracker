package com.example.gym_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ShowEventActivity extends AppCompatActivity {


    String mDate;

    String[] pokemonName = new String[] { "Pikachu", "Squirtle"
            , "Tauros", "Bouffalant", "Terrakion"
            ,"Rattata", "Raticate", "Patrat", "Pichu"
    };

    String[] pokemonImgUrl = new String[] {
            "https://img.pokemondb.net/artwork/large/pikachu.jpg",
            "https://img.pokemondb.net/artwork/squirtle.jpg",
            "https://img.pokemondb.net/artwork/large/tauros.jpg",
            "https://img.pokemondb.net/artwork/large/bouffalant.jpg",
            "https://img.pokemondb.net/artwork/terrakion.jpg"
            ,"https://img.pokemondb.net/artwork/rattata.jpg"
            ,"https://img.pokemondb.net/artwork/raticate.jpg"
            ,"https://img.pokemondb.net/artwork/patrat.jpg"
            ,"https://img.pokemondb.net/artwork/pichu.jpg"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);

        Bundle bundle = getIntent().getExtras();
        mDate = bundle.getString("Date");

        TextView showText = findViewById(R.id.textView);
        showText.setText(mDate);
        RecyclerView list = (RecyclerView) findViewById(R.id.event_list_recyclar_view);

        EventListAdapter customAdaptor = new EventListAdapter(this,
                R.layout.event_summary, pokemonImgUrl, pokemonName);
        list.setAdapter(customAdaptor);
        list.setLayoutManager(new GridLayoutManager(this, 1));
    }
}