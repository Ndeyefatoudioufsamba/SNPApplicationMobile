package sn.example.nafepset;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import sn.example.NaFepSet.R;

public class Geolocalisation extends AppCompatActivity  implements LocationListener{



        private MapView mapView;
        private IMapController mapController;
        private Marker marker;
        private LocationManager locationManager;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Configuration.getInstance().setUserAgentValue(getPackageName());
            setContentView(R.layout.activity_main);

            // Initialisation de la carte

            mapView = findViewById(R.id.map);
            mapView.setTileSource(TileSourceFactory.MAPNIK);
            mapView.setBuiltInZoomControls(true);
            mapView.setMultiTouchControls(true);
            mapController = mapView.getController();
            mapController.setZoom(15.0);






            // Affichage de la localisation de la capture d'image
            double latitude = 48.858093;
            double longitude = 2.294694;
            GeoPoint geoPoint = new GeoPoint(latitude, longitude);
            mapController.setCenter(geoPoint);
            marker = new Marker(mapView);
            marker.setPosition(geoPoint);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            mapView.getOverlays().add(marker);

            // Initialisation du gestionnaire de localisation
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }

        @Override
        protected void onResume() {
            super.onResume();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }

        @Override
        protected void onPause() {
            super.onPause();
            locationManager.removeUpdates(this);
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == 1) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                    }
                }
            }
        }

        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            GeoPoint geoPoint = new GeoPoint(latitude, longitude);
            mapController.setCenter(geoPoint);
            marker.setPosition(geoPoint);



        }
}