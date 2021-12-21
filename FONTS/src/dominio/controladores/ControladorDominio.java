package dominio.controladores;

import dominio.clases.algorithm.collaborativefiltering.CollaborativeFiltering;
import dominio.clases.algorithm.contentbasedflitering.K_NN;
import dominio.clases.evaluation.Evaluation;
import persistencia.ControladorPersistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ControladorDominio {
    private ControladorPersistencia CP;
    private CollaborativeFiltering CF;
    private K_NN KNN;
    private Evaluation E;
    private boolean eval;

    public ControladorDominio(){
        //Cridar a Persistencia a que porti tots els mapes (En format List<String> i transformar-ho a maps)
        CP = new ControladorPersistencia();
    }

    public void computeK(Map<Integer, Map<Integer, Float>> mapRateKnown, Map<Integer, Map<Integer, Float>> mapRateUnknown, int maxItems){
        CollaborativeFiltering CFAux;
        int maxK = mapRateKnown.size()/50;
        float maxDCG = 0.0f;
        E = new Evaluation(mapRateUnknown);
        for(int i = maxK; i < mapRateKnown.size()/3; ++i){
            float DCG = 0.0f;
            CFAux = new CollaborativeFiltering(mapRateKnown, mapRateUnknown, i);
            for(Map.Entry<Integer, Map<Integer, Float>> entry: mapRateKnown.entrySet()){
                DCG += E.DCG(CFAux.recommend(entry.getKey(), maxItems, true));
            }
            if(DCG > maxDCG){
                maxK = i;
                maxDCG = DCG;
            }
        }

        CF = new CollaborativeFiltering(mapRateKnown, mapRateUnknown, maxK);
    }
    public void inicializar(String path){
        /*List<String> mapaS = CP.getMapRate(0);
        Map<Integer, Map<Integer, Float>> mapRate= tranformerMapRate(mapaS);

        CF = new CollaborativeFiltering(mapRate);
        KNN = new K_NN(mapRate);*/
    }

    public List<Integer> list_user(){
        return CP.list_user();
        //retorna una llista amb tots els id users
    }

    public List<Integer> list_item(){
        return CP.list_item();
        //retorna una llista amb tots els id items
    }

    public boolean addItem(int ID, List<String> tags){
        return CP.addItem(ID, tags);
    }
    public boolean exists(int idItem){
        return CP.exists(idItem);
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

    /*public List<List<String>> getMapRate(int a){ // 0 si es ratings, 1 si es known, 2 si es unknown
        return CP.getMapRate(a);
    }*/

    public List<List<String>> getMapItem(){
        return CP.getMapItem();
    }

    public void recommendCF(int k){

    }

    public void recommendCBF(int k){

    }

    public void recommendH(int k){
        /*r1 = CF.recommend();
        r2 = CBF.recommend()
        result = H.recommend(r1,r2);*/
    }

    public List<String> tag_list() {
        return CP.tag_list();
    }

    public boolean saveRecomendation() {
        return true;
    }

    public void evaluateRecomendation(boolean e) {
        eval = e;
    }

    public List<Integer> list_itemREC() {
        List<Integer> result = new ArrayList<>();
        return result;
        //retorna la llista de items de la recomanacio
    }

    public List<Float> list_valREC() {
        List<Float> result = new ArrayList<>();
        return result;
        //retorna la llista de valors de la recomanacio
    }

    public List<Float> list_valSavedREC(String path_rec) {
        return CP.list_valSavedREC(path_rec);
    }

    public List<Integer> list_itemSavedREC(String path_rec) {
        return CP.list_itemSavedREC(path_rec);
    }

    public List<Integer> get_item_rec(){
        return CP.get_item_rec();
    }

    public List<Integer> get_alg_rec(){
        return CP.get_alg_rec();
    }

    public List<String> get_dates_rec(){
        return CP.get_dates_rec();
    }
}
