package com.registro.registropersonas.controller;

import com.registro.registropersonas.model.Usuario;
import com.registro.registropersonas.model.data.DBGenerator;
import com.registro.registropersonas.model.data.dao.UsuarioDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jooq.DSLContext;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "registroUsuarioServlet", value = "/registroUsuario")
public class RegistroUsuarioServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            DBGenerator.iniciarBD("UsuariosBD");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroUsuario.jsp");
        respuesta.forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroErroneo.jsp");
        if(req.getParameter("edad").length()!=0 && req.getParameter("nombre").length()!=0  &&
                req.getParameter("rut").length()!=0){
            String nombre = req.getParameter("nombre");
            int edad = Integer.parseInt(req.getParameter("edad"));
            String rut = req.getParameter("rut");
            Usuario usuario = new Usuario(nombre, edad, rut);
            try {
                if(agregarUsuario(usuario)){
                    req.setAttribute("usuario",usuario);
                    respuesta = req.getRequestDispatcher("/registroExitoso.jsp");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        respuesta.forward(req,resp);
    }
    private boolean agregarUsuario(Usuario usuario) throws ClassNotFoundException {
        DSLContext query= DBGenerator.conectarBD("UsuariosBD");
        List<Usuario> usuarios = UsuarioDAO.obtenerUsuario(query,"rut",usuario.getRut());
        if(usuarios.size()!=0){
            return false;
        }
        else{
            UsuarioDAO.agregarUsuario(query,usuario);
            return true;
        }
    }
}