package eac1.ex2;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 * Jugador d'un equip de e-sports
 * @author Aleix
 */
@XmlRootElement(name = "jugador")
@XmlType(propOrder = {"nom", "pais", "rol", "fiContracte"})
public class Jugador {

    String nom, pais, rol, fiContracte;
    
    @XmlElement
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    @XmlElement(name = "pais")
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    
    @XmlElement(name = "rol")
    public String getRol() {
        return this.rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    
    @XmlElement(name = "fi_contracte")
    public String getFiContracte() {
        return fiContracte;
    }
    public void setFiContracte(String fic) {
        this.fiContracte = fic;
    }
    
}
