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
public class Exer2 {
    
    public static void main(String[] args) throws Exception {
        
    String json = FileReader.loadFileIntoString("C:/Users/Irene/Documents/catalogue.json", "utf-8");
    JSONArray livres = JSONArray.fromObject(json);
    
    System.out.println("Livres parues depuis 2010");
    
    int compt = 0;
    for(int i=0;i<livres.size(); i++){
        JSONObject livre = livres.getJSONObject(i);
        if(livre.getInt("annee")>= 2010){
            compt += 1;
            System.out.println(" * " + livre.getString("titre")); 
            
        }
        }
        
        System.out.println("Il y a " + livres.size() + " livres dans le catalogue dont "
                + compt + " parus depuis 2010.");
    
        
    }
    
    
        
}



    