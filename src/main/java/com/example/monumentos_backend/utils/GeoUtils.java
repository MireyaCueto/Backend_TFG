package com.example.monumentos_backend.utils;

import com.example.monumentos_backend.model.Monument;

import static java.lang.Math.*;

public class GeoUtils {
    // ratio tierra = 6,378.1 km
    final static double RATIO_TIERRA = 6378.1;

    public static double teoremaHaversine(Monument monumentAlfa, Monument monumentOmega) {
        // FORMULA DE HAVERSINE
        // CALCULAR DISTANCIA ENTRE DOS COORDENADAS
        // https://user-images.githubusercontent.com/2789198/27240436-e9a459da-52d4-11e7-8f84-f96d0b312859.png
        double lat1 = toRadians(monumentOmega.getLat());
        double lon1 = toRadians(monumentOmega.getLon());
        double lat2 = toRadians(monumentAlfa.getLat());
        double lon2 = toRadians(monumentAlfa.getLon());
        double senoLat,senoLon, cosLat1, cosLat2;
        senoLat = senoDifCoordenadas(lat1, lat2);
        senoLon = senoDifCoordenadas(lon1, lon2);
        cosLat1 = cos(lat1);
        cosLat2 = cos(lat2);

        // Calcular interior raiz
        double contenido = senoLat + (cosLat1*cosLat2*senoLon);

        double resultadoRaiz = sqrt(contenido);

        return  2* RATIO_TIERRA* asin(resultadoRaiz);
    }

    private static double senoDifCoordenadas(Double coordenadaAlfa, Double coordenadaOmega) {
        double difLat = coordenadaOmega - coordenadaAlfa;
        return pow(sin(difLat/2),2);
    }
}
