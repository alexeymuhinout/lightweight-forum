package com.rustedbrain.web.model.jaxb.sequence;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sequences")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sequences {

    @XmlElement(name = "sequence")
    private List<Sequence> sequences;

    public List<Sequence> getSequences() {
        return sequences;
    }

    public void setSequences(List<Sequence> sequences) {
        this.sequences = sequences;
    }

    @Override
    public String toString() {
        return "Sequences{" +
                "sequences=" + sequences +
                '}';
    }
}
