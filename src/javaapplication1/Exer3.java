/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Irene
 */
public class Exer3 {
    
    public static void main(String[] args) throws Exception {
        
    String json = FileReader.loadFileIntoString("C:/Users/Irene/Documents/catalogue.json", "utf-8");
    JSONArray livres = JSONArray.fromObject(json);
    
    System.out.println("Prix des livres disponibles");
    
    int compt = 0;
    for(int i=0; i<livres.size(); i++){
        JSONObject livre = livres.getJSONObject(i);
        if(livre.getBoolean("disponible")){
            compt += 1;
            System.out.println("* " + livre.getString("titre") + ": " + "$ "
                    + livre.getDouble("prix"));
        }
    }
    
    System.out.println("Il y a " + compt + " livres disponibles");
    
    }
    
}
