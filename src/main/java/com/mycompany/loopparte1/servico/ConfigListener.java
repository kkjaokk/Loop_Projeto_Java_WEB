/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loopparte1.servico;

/**
 *
 * @author João Henrique
 */
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ConfigListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
      
        sce.getServletContext().setAttribute("URL_BASE", WebConstantes.BASE_PATH);
        // estou usando no .jsp
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Limpeza, se necessário.
    }
}
