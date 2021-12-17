package dominio.controladores;

import dominio.clases.algorithm.collaborativefiltering.CollaborativeFiltering;
import dominio.clases.algorithm.contentbasedflitering.K_NN;
import persistencia.ControladorPersistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ControladorDominio {
    private ControladorPersistencia CP;
    private CollaborativeFiltering CF;
    private K_NN KNN;

    public ControladorDominio(){
        //Cridar a Persistencia a que porti tots els mapes (En format List<String> i transformar-ho a maps)
        CP = new ControladorPersistencia();


    }

    public void inicializar(String path){
        /*List<String> mapaS = CP.getMapRate();
        Map<Integer, Map<Integer, Float>> mapRate= tranformerMapRate(mapaS);

        CF = new CollaborativeFiltering(mapRate);
        KNN = new K_NN(mapRate);*/
    }
    public boolean addItem(int ID, List<String> tags){
        return CP.addItem(ID, tags);
    }
    public boolean delItem(int ID) {
        return CP.delItem(ID);
    }
    public boolean modTag(int IDitem, String atribute, String newtag){
        return CP.modTag(IDitem, atribute, newtag);
    }
    public boolean delTag(int IDitem, String atribute){
        return CP.delTag(IDitem, atribute);
    }
    public boolean addUser(int ID) {
        return CP.addUser(ID);
    }
    public boolean delUser(int ID) {
        return CP.delUser(ID);
    }
    public boolean addRating(int IDuser, int IDitem, float valor){
        return CP.addRating(IDuser, IDitem, valor);
    }
    public boolean modRating(int IDuser, int IDitem, float new_rate) {
        return CP.modRating(IDuser, IDitem, new_rate);
    }
    public boolean delRating(int IDuser, int IDitem) {
        return CP.delRating(IDuser, IDitem);
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
        /*r1 = CF.recommend();
        r2 = CBF.recommend()
        result = H.recommend(r1,r2);*/
    }
}
