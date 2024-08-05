/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.servico;

/**
 *
 * @author João Henrique
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConverteData {

   static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();
    

    public static Date somarData(int dias, Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DATE, dias);
        return calendar.getTime();
    }

    public static String dataAtual() {
        return sdf.format(new Date());
    }

    public Calendar converteCalendario(String data) {
        try {
            cal.setTime(sdf.parse(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }
    
        public static String getDataFormatada(Calendar data) {
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        return formatar.format(data.getTime());

    }

    public Calendar converteCalendario(Date data) {
        Calendar dataCal = Calendar.getInstance();
        dataCal.setTime(data);
        return dataCal;
    }

    public static String converteTela(Object value) {
        Calendar c = (Calendar) value;
        return sdf.format(c.getTime());
    }
    
     public static String formataDate(Calendar calendar) {
        if (calendar == null) {
            return "2024-04-20";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(calendar.getTime());
    }
     
   public static String formataDate(Object value) {
        Calendar c = (Calendar) value;
      SimpleDateFormat formata = new SimpleDateFormat("yyyy-MM-dd");
        return formata.format(c.getTime());
    }
   
 
public static String convertDateFormat(String dateStr) {
        // Define o formato de entrada
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        // Define o formato de saída
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Parse a data de entrada para um objeto Date
            Date date = inputFormat.parse(dateStr);
            // Formata o objeto Date para o formato de saída
            return outputFormat.format(date);
        } catch (ParseException e) {
            // Handle the possibility that the input was not in the expected format
            e.printStackTrace();
            return null;
        }
    }

  
     
      
    
     public java.sql.Date converteBanco(Object value) {
        Calendar c = (Calendar) value;
       return  new java.sql.Date(c.getTimeInMillis());
    }
    
}
