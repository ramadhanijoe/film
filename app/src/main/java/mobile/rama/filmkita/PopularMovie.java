package mobile.rama.filmkita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.Toast;

import java.net.SocketTimeoutException;

import mobile.rama.filmkita.model.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import mobile.rama.filmkita.adapter.MovieListAdapter;
import mobile.rama.filmkita.network.ApiService;

import static android.widget.Toast.makeText;

public class PopularMovie extends AppCompatActivity {
    MovieListAdapter movieListAdapter;
    RecyclerView recyclerView;
    private int page = 1;
    GridLayoutManager gridLayoutManager;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movie);

        movieListAdapter = new MovieListAdapter(this);
        recyclerView = (RecyclerView)findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(movieListAdapter);
        recyclerView.setHasFixedSize(true);

        loadData();
    }

    private void loadData() {
        apiService = new ApiService();
        apiService.getPopularMovies(page, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Movie movie = (Movie) response.body();
                if (movie!=null){
                    if (movieListAdapter!=null) {
                        movieListAdapter.addAll(movie.getResults());
                    }
                }else{
                    Toast.makeText(getBaseContext(),"Tidak ada Data",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    Toast.makeText(getBaseContext(), "Request Time Out", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(), "Connection Error", Toast.LENGTH_LONG).show();
                }

            }

        });
    }
}
