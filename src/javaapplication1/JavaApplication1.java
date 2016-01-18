/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import net.sf.json.JSONArray;


/**
 *
 * @author Irene
 */


public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        String json = FileReader.loadFileIntoString("C:/Users/Irene/Documents/catalogue.json", "utf-8");
        JSONArray livres = JSONArray.fromObject(json);
        System.out.println("Il y a " + livres.size() + " livre(s) dans le catalogue");
    }
    
}
