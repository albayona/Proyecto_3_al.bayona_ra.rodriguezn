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
import model.data_structures.Lists.Stack;
import model.vo.VOIntersection;

public class JxMapsB1 extends MapView {

    // Objeto Google Maps
    private Map map;

    //Coordenadas del camino a mostrar (secuencia de localizaciones (Lat, Long))
    private LatLng[] locations;

    public JxMapsB1(Stack<IEdge> iEdges)
    {

        VOIntersection last = null;

        locations = new LatLng[iEdges.size() +1];
        int i = 0;
        for (IEdge e: iEdges   ) {

            VOIntersection inter = (VOIntersection) e.either();

            locations[i ] = new LatLng(inter.getLatitud(), inter.getLongitude());

            last = (VOIntersection)e.getVertex2();
            i++;
        }

        locations[iEdges.size()] = new LatLng(last.getLatitud(), last.getLongitude());




        setOnMapReadyHandler( new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status)
            {
                if ( status == MapStatus.MAP_STATUS_OK )
                {
                    map = getMap();

                    // marker at the start of path
                    Marker startPath = new Marker(map);
                    startPath.setPosition(locations[0]);

                    // Configuracion de localizaciones intermedias del path (circulos)
                    CircleOptions middleLocOpt= new CircleOptions();
                    middleLocOpt.setFillColor("#00FF00");  // color de relleno
                    middleLocOpt.setFillOpacity(0.5);
                    middleLocOpt.setStrokeWeight(1.0);


                    for (LatLng c:
                         locations) {

                        Circle temp = new Circle(map);
                        temp.setOptions(middleLocOpt);
                        temp.setCenter(c);
                        temp.setRadius(200); //Radio del circulo

                    }


                    //Configuracion de la linea del camino
                    PolylineOptions pathOpt = new PolylineOptions();
                    pathOpt.setStrokeColor("#0000FF");	  // color de linea
                    pathOpt.setStrokeOpacity(1.75);
                    pathOpt.setStrokeWeight(1.5);
                    pathOpt.setGeodesic(false);

                    // Linea del camino
                    Polyline path = new Polyline(map);
                    path.setOptions(pathOpt);
                    path.setPath(locations);

                    // marker at the start of path
                    Marker endPath = new Marker(map);
                    endPath.setPosition(locations[iEdges.size()]);


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
        map.setCenter(locations[0]);
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
