package joaopaulo.com.br.pingago.activities;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import joaopaulo.com.br.pingago.R;
import joaopaulo.com.br.pingago.fragments.MainFragment;

public class MapsActivity extends FragmentActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener {


    private GoogleApiClient mGoogleApi;
    private MainFragment mainFragment;
    public final  int LOCALIZACAO_INDICADOR = 2727;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.container_frame_main);

        if (mGoogleApi == null){
            mGoogleApi = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addConnectionCallbacks(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        if (mainFragment == null){
            mainFragment = new MainFragment().newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_frame_main, mainFragment)
                    .commit();
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCALIZACAO_INDICADOR);
            Log.v("MAPSD", "Requisitando Permissões");
        }else{
            Log.v("MAPSD", "Iniciou o Localizarme em OnConnected");
            locaLizarMe();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.v("MAPSD", "Longitude:"+location.getLongitude()+ " Latitude:"+location.getLatitude());
        mainFragment.setLocalizacaoUsuario(new LatLng(location.getLatitude(),location.getLongitude()));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCALIZACAO_INDICADOR: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locaLizarMe();
                    Log.v("MAPSD", "Permissão concedita - startando o services");
                } else {
                    Toast.makeText(this,"Não podemos te localizar",Toast.LENGTH_LONG).show();
                    Log.v("MAPSD", "Permissão negada :(");
                }
            }
        }
    }

    public void locaLizarMe(){
        Log.v("MAPSD", "Iniciou o Localizarme");
        try{
            LocationRequest request = LocationRequest.create().setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApi,request,this);
        }catch (SecurityException sec){
            Log.v("MAPSD", "Erro na Localizarme:"+sec.toString());
            Toast.makeText(this,"Erro de permissão de GPS",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        mGoogleApi.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApi.disconnect();
        super.onStop();
    }
}
