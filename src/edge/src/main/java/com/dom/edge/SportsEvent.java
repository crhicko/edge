package com.dom.edge;

import com.dom.edge.proto.Executionevent;
import com.dom.edge.proto.Sportevent;

public class SportsEvent {

    String sport;
    String match_title;
    String data_event;

    private static Sportevent event;

    public SportsEvent(String sp, String mt, String de) {
        sport = sp;
        match_title = mt;
        data_event = de;
    }

}
