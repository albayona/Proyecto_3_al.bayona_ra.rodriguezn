package model.logic;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import api.IEdge;
import com.teamdev.jxmaps.Circle;
import com.teamdev.jxmaps.CircleOptions;
import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.InfoWindowOptions;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.Polyline;
import com.teamdev.jxmaps.PolylineOptions;
import com.teamdev.jxmaps.swing.MapView;
import model.data_structures.Lists.DoublyLinkedList;
import model.vo.VOIntersection;

public class JxMapsB3 extends MapView {

    // Objeto Google Maps
    private Map map;

    LatLng last;


    public JxMapsB3(Iterable<IEdge> iEdges)
    {


        setOnMapReadyHandler( new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status)
            {
                if ( status == MapStatus.MAP_STATUS_OK )
                {
                    map = getMap();


                    // Configuracion de localizaciones intermedias del path (circulos)
                    CircleOptions middleLocOpt= new CircleOptions();
                    middleLocOpt.setFillColor("#00FF00");  // color de relleno
                    middleLocOpt.setFillOpacity(0.5);
                    middleLocOpt.setStrokeWeight(1.0);

                    //Configuracion de la linea del camino
                    PolylineOptions pathOpt = new PolylineOptions();
                    pathOpt.setStrokeColor("#0000FF");	  // color de linea
                    pathOpt.setStrokeOpacity(1.75);
                    pathOpt.setStrokeWeight(1.5);
                    pathOpt.setGeodesic(false);

                    for (IEdge e: iEdges   ) {

                        VOIntersection inter1 = (VOIntersection) e.getVertex1();
                        VOIntersection inter2 = (VOIntersection) e.getVertex2();
                        LatLng coord1 = new LatLng(inter1.getLatitud(), inter1.getLongitude());
                        LatLng coord2 = new LatLng(inter2.getLatitud(), inter2.getLongitude());

                        Circle temp1 = new Circle(map);
                        temp1.setOptions(middleLocOpt);
                        temp1.setCenter(coord1);
                        temp1.setRadius(50);

                        Circle temp2 = new Circle(map);
                        temp2.setOptions(middleLocOpt);
                        temp2.setCenter(coord2);
                        temp2.setRadius(50);


                        LatLng[] locations;

                        locations = new LatLng[]{coord1, coord2};
                        // Linea del camino
                        Polyline path = new Polyline(map);
                        path.setOptions(pathOpt);
                        path.setPath(locations);

                        last = coord1;
                    }


                    initMap( map );
                }
            }

        } );


    }

    public void initMap(Map map)
    {
        MapOptions mapOptions = new MapOptions();
        MapTypeControlOptions controlOptions = new MapTypeControlOptions();
        controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
        mapOptions.setMapTypeControlOptions(controlOptions);

        map.setOptions(mapOptions);
        map.setCenter(last);
        map.setZoom(14.0);

    }

    public void initFrame(String titulo)
    {
        JFrame frame = new JFrame(titulo);
        frame.setSize(800, 800);
        frame.add(this, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


}
