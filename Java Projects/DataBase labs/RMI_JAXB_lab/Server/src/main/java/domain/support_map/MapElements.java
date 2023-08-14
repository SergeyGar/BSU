package domain.support_map;

import jakarta.xml.bind.annotation.XmlElement;

public class MapElements {

    @XmlElement
    public Integer subject_id;
    @XmlElement
    public Integer mark;

    private MapElements() {} //Required by JAXB

    public MapElements(Integer subject_id, Integer mark)
    {
        this.subject_id = subject_id;
        this.mark = mark;
    }
}
