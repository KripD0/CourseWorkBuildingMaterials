package org.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({General.class, pesok.class, cement.class, brick.class, glass.class})
public class ForXML {
    @XmlElement(name = "material")
    public List  spisok = new ArrayList<>();

    public ForXML(){

    }
}
