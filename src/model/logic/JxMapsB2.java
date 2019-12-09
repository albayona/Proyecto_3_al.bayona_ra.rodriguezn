package model.logic;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.*;

import api.IEdge;
import api.IVertex;
import com.teamdev.jxmaps.*;
import com.teamdev.jxmaps.Icon;
import com.teamdev.jxmaps.swing.MapView;
import model.data_structures.Lists.DoublyLinkedList;
import model.vo.VOIntersection;

public class JxMapsB2 extends MapView {

    // Objeto Google Maps
    private Map map;

    private LatLng first;

    //Coordenadas del camino a mostrar (secuencia de localizaciones (Lat, Long))


    public JxMapsB2(DoublyLinkedList<IVertex> iVertices)
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



                    for (IVertex e: iVertices   ) {

                        VOIntersection inter = (VOIntersection) e;

                      LatLng c =  new LatLng(inter.getLatitud(), inter.getLongitude());

                        Icon icon = new Icon();
                        icon.loadFromFile(new File("./data/icon.png"));
                        Marker temp = new Marker(map);
                        temp.setIcon(icon);
                        temp.setPosition(c);
                    }

                    VOIntersection firstV = (VOIntersection) iVertices.first();
                    first =  new LatLng(firstV.getLatitud(), firstV.getLongitude());
                    // marker at the start of path
                    Marker startPath = new Marker(map);
                    startPath.setPosition(first);



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
        map.setCenter(first);
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
