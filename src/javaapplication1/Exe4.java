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
public class Exe4 {
    
        public static void main(String[] args) throws Exception {
            
            JSONObject commande = new JSONObject();
            commande.accumulate("id", "123");
            commande.accumulate("total", 30.2);
            commande.accumulate("date","1/12/2010");
            commande.accumulate("validation", true);
            
            JSONObject livre = new JSONObject();
            livre.accumulate("id", "1");
            livre.accumulate("titre", "The big Show");
            
            JSONArray albums = new JSONArray();
            albums.add(livre);
            
            commande.accumulate("livres", albums);
            
            System.out.println(commande);
            
        }

    
}
