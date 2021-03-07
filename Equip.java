package eac1.ex2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 * Equip d'e-sports
 */

@XmlRootElement(name = "equip")
@XmlType(propOrder = {"nom", "pais", "potFitxar", "nombreJugadors", "plantilla"})
public class Equip {
    int nombreJugadors;
    boolean potFitxar;
    String nom, pais;
    
    List<Jugador> plantilla=new ArrayList<>();
    
    @XmlElementWrapper(name = "plantilla")
    @XmlElement(name = "jugador")
    public List<Jugador> getPlantilla() {
        return plantilla;
    }
    
    @XmlElement(name ="nombre_jugadors")
    public int getNombreJugadors() {
		return nombreJugadors;
    }
    public void setNombreJugadors(int nombreJugadors) {
		this.nombreJugadors = nombreJugadors;
    }
    
    @XmlElement(name = "pot_fitxar")
    public boolean isPotFitxar() {
		return potFitxar;
    }
    public void setPotFitxar(boolean potFitxar) {
		this.potFitxar = potFitxar;
    }
    
    @XmlElement
    public String getNom() {
		return nom;
    }
    public void setNom(String nom) {
		this.nom = nom;
    }
    
    @XmlElement
    public String getPais() {
		return pais;
    }
    public void setPais(String pais) {
		this.pais = pais;
    }
}
