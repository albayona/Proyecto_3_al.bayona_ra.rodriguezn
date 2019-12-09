package model.logic;

import api.IEdge;
import com.teamdev.jxmaps.*;
import com.teamdev.jxmaps.swing.MapView;
import model.data_structures.Lists.LinkedList;
import model.data_structures.Lists.Stack;
import model.vo.VOZone;

import javax.swing.*;
import java.awt.*;

public class JxMapsC3 extends MapView {

    // Objeto Google Maps
    private Map map;

    LatLng last;


    public JxMapsC3(LinkedList<VOZone> vertices)
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


                    LatLng[] locs = new LatLng[vertices.getSize()];

                    int i = 0;
                    for (VOZone e: vertices   ) {

                        LatLng coord1 = new LatLng(e.getLatitud(), e.getLongitude());


                        Circle temp1 = new Circle(map);
                        temp1.setOptions(middleLocOpt);
                        temp1.setCenter(coord1);
                        temp1.setRadius(50);


                        locs[i] = coord1;

                        last = coord1;
                        i++;
                    }





                    // Linea del camino
                    Polyline path = new Polyline(map);
                    path.setOptions(pathOpt);
                    path.setPath(locs);


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
