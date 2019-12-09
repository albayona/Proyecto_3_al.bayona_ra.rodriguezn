package model.logic;

import api.IEdge;
import com.teamdev.jxmaps.*;
import com.teamdev.jxmaps.swing.MapView;
import model.data_structures.HashTables.HashTable;
import model.data_structures.Lists.LinkedList;
import model.vo.SimpleEdge;
import model.vo.VOIntersection;
import model.vo.VOZone;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class JxMapsC1 extends MapView {

    // Objeto Google Maps
    private Map map;

    LatLng last;


    public JxMapsC1(HashTable<String, SimpleEdge> iEdges)
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

                    for (SimpleEdge e : iEdges ) {


                        VOZone inter1 = (VOZone) e.getVertex1();
                        VOZone inter2 = (VOZone) e.getVertex2();
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
