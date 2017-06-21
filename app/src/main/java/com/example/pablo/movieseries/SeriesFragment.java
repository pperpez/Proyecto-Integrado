package com.example.pablo.movieseries;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.snowdream.android.widget.SmartImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeriesFragment extends Fragment {

    private RecyclerView recyclerSeries;
    private ArrayList<PeliculaSerie> listaSeries = new ArrayList<>();
    private AdaptadorSeries adaptador;

    private SharedPreferences spDatos;


    public SeriesFragment() {
        // Required empty public constructor
    }

    public static SeriesFragment newInstance(){
        SeriesFragment fragment = new SeriesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        spDatos = this.getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_series, container, false);

        recyclerSeries = (RecyclerView)v.findViewById(R.id.recyclerSeries);
        recyclerSeries.setHasFixedSize(true);

        getSeries();
        procesarSeries();

        return v;
    }


    private void getSeries(){
        String url = "http://www.pabloperezlopez.com/movieseries/obtener_series.php";

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            for (int i = 0; i < response.getJSONArray("peliculas").length(); i++) {
                                JSONObject jresponse = response.getJSONArray("peliculas").getJSONObject(i);
                                PeliculaSerie serie = new PeliculaSerie(Integer.parseInt(jresponse.getString("idPelicula")),jresponse.getString("Titulo"), jresponse.getString("Imagen"));
                                listaSeries.add(serie);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adaptador.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue.add(getRequest);
    }

    private void procesarSeries(){
        adaptador = new AdaptadorSeries();
        recyclerSeries.setAdapter(adaptador);
        recyclerSeries.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    class AdaptadorSeries extends RecyclerView.Adapter<AdaptadorSeries.SeriesViewHolder> {

        @Override
        public SeriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_pelicula, parent, false);

            SeriesViewHolder svh = new SeriesViewHolder(item);
            return svh;
        }

        @Override
        public void onBindViewHolder(SeriesViewHolder holder, int position) {
            PeliculaSerie serie = listaSeries.get(position);

            holder.bindSerie(serie);
        }

        @Override
        public int getItemCount() {
            return listaSeries.size();
        }

        class SeriesViewHolder extends RecyclerView.ViewHolder {

            private SmartImageView imageCard;
            private TextView txtTitulo, txtIdentifier, txtImagen;

            public SeriesViewHolder(View itemView) {
                super(itemView);

                txtTitulo = (TextView)itemView.findViewById(R.id.txtTitulo);
                txtIdentifier = (TextView)itemView.findViewById(R.id.txtIdentifier);
                txtImagen = (TextView)itemView.findViewById(R.id.txtImagen);
                imageCard = (SmartImageView)itemView.findViewById(R.id.imageCard);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), DetailsActivity.class);
                        SharedPreferences.Editor editor = spDatos.edit();
                        editor.putString("id", txtIdentifier.getText().toString());
                        editor.putString("titulo", txtTitulo.getText().toString());
                        editor.putString("imagen", txtImagen.getText().toString());
                        editor.commit();
                        startActivity(i);
                    }
                });
            }

            public void bindSerie(PeliculaSerie serie) {
                txtTitulo.setText(serie.getTitulo());
                txtIdentifier.setText(serie.getIdPelicula().toString());
                txtImagen.setText(serie.getImagen());
                Rect rect = new Rect(imageCard.getLeft(), imageCard.getTop(), imageCard.getRight(), imageCard.getBottom());
                imageCard.setImageUrl("http://www.pabloperezlopez.com/movieseries/images/"+ serie.getImagen(),rect);

            }
        }
    }
}
