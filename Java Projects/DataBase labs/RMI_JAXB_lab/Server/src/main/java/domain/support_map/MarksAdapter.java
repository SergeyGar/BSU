package domain.support_map;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.HashMap;
import java.util.Map;

public class MarksAdapter extends XmlAdapter<MapElements[], Map<Integer, Integer>> {

    @Override
    public MapElements[] marshal(Map<Integer, Integer> arg0) throws Exception {
        MapElements[] mapElements = new MapElements[arg0.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : arg0.entrySet())
            mapElements[i++] = new MapElements(entry.getKey(), entry.getValue());

        return mapElements;
    }

    @Override
    public Map<Integer, Integer> unmarshal(MapElements[] arg0) throws Exception {
        Map<Integer, Integer> r = new HashMap<Integer, Integer>();
        for (MapElements mapElement : arg0)
            r.put(mapElement.subject_id, mapElement.mark);
        return r;
    }
}
