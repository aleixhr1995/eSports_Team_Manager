/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eac1.ex2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author aleix
 */
public class Exercici2 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        //Declarem variables que emmagatzemaran els valors passats per parametre.
        String nom, pais, rol, fi_contracte;
        
        //Variables adicionals
        Equip team;
        boolean nomExists = false;
        List<Jugador> plantilla;
        
        //Assignem els valors dels parametres a les variables.
        nom = args[0];
        pais = args[1];
        rol = args[2];
        fi_contracte = args[3];
        File fitxerOrigen = new File(args[4]);
        File fitxerDesti = new File(args[5]);
        
        //Comprovem que el nombre de paràmetres introduïts és correcte.
        if(args.length!=6){
            errorExit("Nombre de paràmetres incorrecte.");
        }
        
        //Comprovem que el fitxer existeix, que es un fitxer i que es llegible.
        if(!fitxerOrigen.isFile() || !fitxerOrigen.canRead()){
            errorExit("El fitxer d'origen és inaccessible.");
        }       
        
        try{
            //Creem context a partir de la classe Equip.
            JAXBContext jaxbContext = JAXBContext.newInstance(Equip.class);

            //Creem Unmarshaller a partir del context del JAXBContext.
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            
            //Creem Marshaller per tornar a volcar les dades un cop actualitzades al fitxer XML.
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            //Obtenim les dades.
            team = (Equip) jaxbUnmarshaller.unmarshal(fitxerOrigen);
            plantilla =(List<Jugador>) team.getPlantilla();
            
            //Recorrem la List per veure si existeix el nom.
            if(team.isPotFitxar()){
                for(int i = 0; i<plantilla.size();i++){
                    if(plantilla.get(i).nom.equalsIgnoreCase(nom)){
                        nomExists=true;
                    }
                }
            }else{
                errorExit("L'equip ja no pot adquirir més jugadors.");
            }
            
            
            //Si el nom no existeix i l'equip pot fitxar crearem el jugador.
            if(nomExists==false){
                
                //Creem el jugador i afegim les dades d'aquest.
                Jugador jugador = new Jugador();
                jugador.setNom(nom);
                jugador.setPais(pais);
                jugador.setRol(rol);
                jugador.setFiContracte(fi_contracte);

                //Afegim jugador a la plantilla.
                plantilla.add(jugador);

                //Actualitzem el nombre de jugadors.
                team.setNombreJugadors(team.getNombreJugadors()+1);
                
                //Donem format correcte a les dades a volcar.
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                
                //Gravem al fitxer de destí l'informació actualitzada.
                jaxbMarshaller.marshal(team, fitxerDesti);
                
                //Mostrem el fitxer actualitzat.
                mostraFitxer(fitxerDesti);
                
            }else{ 
                errorExit("El nom del jugador ja existeix.");
            }

        }catch(NumberFormatException e){
            errorExit("Error en el format: "+e.getCause());
        }
        catch(JAXBException j){
            errorExit("Error de grabació: "+ j.getCause());
        }
    }
    
    
    //Mètode per mostrar les dades de un fitxer per consola.
    private static void mostraFitxer(File fitxer){
        
        try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch(FileNotFoundException e){
            errorExit("El fitxer no s'ha trobat.");
        } catch (IOException ex) {
            errorExit("Error d'entrada/sortida.");
        }
    }
    
    
    //Mètode per mostrar l'error.
    private static void errorExit(String missatge) {
        System.err.println(missatge);
        System.exit(2);
    }
    
}
