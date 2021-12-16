package dominio.controladores;

import dominio.clases.algorithm.collaborativefiltering.CollaborativeFiltering;
import dominio.clases.algorithm.contentbasedflitering.K_NN;
import persistencia.ControladorPersistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ControladorDominio {
    ControladorPersistencia CP;
    CollaborativeFiltering CF;
    K_NN KNN;

    public ControladorDominio(){
        //Cridar a Persistencia a que porti tots els mapes (En format List<String> i transformar-ho a maps)
        CP = new ControladorPersistencia();

        /*List<String> mapaS = CP.getMapRate();
        Map<Integer, Map<Integer, Float>> mapRate= tranformerMapRate(mapaS);

        CF = new CollaborativeFiltering(mapRate);
        KNN = new K_NN(mapRate);*/
    };
    public void addItem(int ID, List<String> tags){
        CP.addItem(ID, tags);
    }
    public void delItem(int ID) {
        CP.delItem(ID);
    }
    public void modTag(int IDitem, String atribute, String newtag){
        CP.modTag(IDitem, atribute, newtag);
    }
    public void delTag(int IDitem, String atribute){
        CP.delTag(IDitem, atribute);
    }
    public void addUser(int ID) {
        CP.addUser(ID);
    }
    public void delUser(int ID) {
        CP.delUser(ID);
    }
    public void addRating(int IDuser, int IDitem, float valor){
        CP.addRating(IDuser, IDitem, valor);
    }
    public void modRating(int IDuser, int IDitem, float new_rate) {
        CP.modRating(IDuser, IDitem, new_rate);
    }
    public void delRating(int IDuser, int IDitem) {
        CP.delRating(IDuser, IDitem);
    }

    public List<List<String>> getMapRate(int a){ // 0 si es ratings, 1 si es known, 2 si es unknown
        return CP.getMapRate(a);
    }

    public List<List<String>> getMapItem(){
        return CP.getMapItem();
    }

    public void recommendCF(boolean valoration, int k){

    }

    public void recommendCBF(boolean valoration, int k){

    }

    public void recommendH(boolean valoration, int k){

    }
}
