/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.text.DecimalFormat;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Irene
 */
public class Ex5 {
    
    public static void main(String[] args) throws Exception {
        
       String json = FileReader.loadFileIntoString("C:/Users/Irene/Documents/catalogue.json", "utf-8");
       JSONArray lvrs = JSONArray.fromObject(json);
       
       JSONArray livres = new JSONArray();
       double total = 0.0;
       for(int i=0; i<lvrs.size();i++){
           JSONObject livre = lvrs.getJSONObject(i);
           if(livre.getDouble("prix")<100.0){
               total += livre.getDouble("prix");
               livres.add(livre);
           }
       }
       
       DecimalFormat format = new DecimalFormat();
       format.setMinimumFractionDigits(2);
       String totalStr = format.format(total);
       
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       Date date = new Date();
       String dateOrder = dateFormat.format(date);
       
       JSONObject order = new JSONObject();
       order.accumulate("id", "1243");
       order.accumulate("total", totalStr);
       order.accumulate("date", dateOrder);
       order.accumulate("validation", true);
       order.accumulate("livres",livres);
       
       System.out.println(order);
       
    }
    
    
 
    
}
