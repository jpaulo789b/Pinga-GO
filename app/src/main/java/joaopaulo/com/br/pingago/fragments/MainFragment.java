package joaopaulo.com.br.pingago.fragments;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import joaopaulo.com.br.pingago.R;
import joaopaulo.com.br.pingago.modelo.PingaGo;
import joaopaulo.com.br.pingago.servicos.ServicoDeDados;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment  implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    private GoogleMap mMap;
    private MarkerOptions localizacaoUsuario;
    private EditText editTextBuscarPorCEP;

    // TODO: Rename and change types of parameters



    public MainFragment() {

    }

    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container,false);

        editTextBuscarPorCEP = (EditText) view.findViewById(R.id.editTextBuscarPorCEP);
        editTextBuscarPorCEP.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN && event.getAction() == KeyEvent.KEYCODE_ENTER){
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(editTextBuscarPorCEP.getWindowToken(),0);
                    updateMapaPorCEP(Integer.parseInt(editTextBuscarPorCEP.getText().toString()));
                }
                return true;
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return  view;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    public void setLocalizacaoUsuario(LatLng posicao){

        if (localizacaoUsuario == null){
            localizacaoUsuario = new MarkerOptions().position(posicao).title("Minha posição Atual");
            mMap.addMarker(localizacaoUsuario);

        }
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(posicao.latitude,posicao.longitude, 1);
            String cep = addresses.get(0).getPostalCode().toString();
            if(cep.contains("-")){
                cep = cep.split("-")[0]+cep.split("-")[1];
            }
            int zip = Integer.parseInt(cep);
            updateMapaPorCEP(zip);
        } catch (IOException exception){

        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicao,15));

    }

    private void updateMapaPorCEP(int cep){
        ArrayList<PingaGo> locais = ServicoDeDados.getInstance().trazerEstabelecimentosEmUmRadioDe10km(cep);

        for (PingaGo pingaGo:locais){
            MarkerOptions local = new MarkerOptions()
                    .position(new LatLng(pingaGo.getLatitude(),pingaGo.getLongitude()))
                    .title(pingaGo.getLocalizacaoTitulo());
            local.snippet(pingaGo.getLocalizacaoEndereco());
            local.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin));

            mMap.addMarker(local);

        }
    }
}
