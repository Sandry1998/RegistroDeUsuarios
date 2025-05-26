package com.ceatformacion.registrodeusuarios.controller;

import com.ceatformacion.registrodeusuarios.modell.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

@Controller
public class ControllerUser {

       @GetMapping("/alta")//la url o ruta que utilizaras para ver el formulario
        public String altaUsuario(Model model){
           model.addAttribute("usuario", new Usuario());
           //MOSTRAR EN EL FORMULARIO LA FECHA DE HOY
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
           LocalDate fechaRegistro = LocalDate.now();
           model.addAttribute("fechaRegistro", fechaRegistro.format(formatter));
           return "formulario";//debe tener el mismo nombre que en html de templates y envia al formulario.html
       }
       //Agregamos una coleccion para ir guardando los usuarios dados de alta
    ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        @PostMapping("/guardar")
        public String guardarUsuario(@ModelAttribute Usuario usuario, Model model) {
            //ASEGURATE QUE LA FECHA ESTE BIEN CONFIGURADA
            usuario.setFechaRegistro(String.valueOf(LocalDate.now()));
            usuario.asignarIdUsuario();

            //AGREGAR EL USUARIO A LA LISTA
            listaUsuarios.add(usuario);
            //MOSTRAR POR CONSOLA
            System.out.println(usuario);

            return "redirect:/crud";//REDIRIGE A LA VISTA DE LOS USUARIOS

        }
        //MOSTRAR TODOS LOS ALUMNOS EN UN LISTADO TIPO CRUD
    @GetMapping("/crud")
    public String verUsuarios(Model model){
            model.addAttribute("arrayDeUsuarios", listaUsuarios);
            return "crud";//NOMBRE DE LA VISTA PARA MOSTRAR LOS USUARIOS
    }

    @GetMapping("/editar/{idUsuario}")
    public String modificarUsuario(@PathVariable int idUsuario, Model model){
            //instancia de la clase usuario donde almacenara el objeto usuario encontrado en el iterator, para pasarlo a la vista->modificar.html
            Usuario usuarioE =null;
            //Iterator para recorrer el arraylist y ubicar el id que se pasa por parametro
        Iterator<Usuario> iterator = listaUsuarios.iterator();

        while(iterator.hasNext()){
            Usuario u=iterator.next();
            if(idUsuario==u.getIdUsuario()){
                usuarioE = u;
                break;
            }
        }
        if(listaUsuarios!=null){
            model.addAttribute("usuario", usuarioE);
            return "modificar";
        }else{//si no encuentra o la lista esta vacia se va a ir al crud
            return "redirect:/crud";
        }

    }
    @PostMapping("/modificar")
    public String guardarModificacion(Model model, Usuario usuarioActualizado){
            //recorremos el arraylist buscando el id del usuario que nos llega y luego, reemplazo todos sus datos.
        for(int i=0; i<listaUsuarios.size(); i++){
            if(listaUsuarios.get(i).getIdUsuario() == usuarioActualizado.getIdUsuario()){
                listaUsuarios.set(i, usuarioActualizado);//Se reemplaza todos sus datos
                break;//nos salimos
            }
        }
        return "redirect:/crud";
    }

    @GetMapping("/eliminar/{idUsuario}")
    public String eliminarUsuario(@PathVariable int idUsuario){
            for(int i=0; i<listaUsuarios.size(); i++){
                if(listaUsuarios.get(i).getIdUsuario() == idUsuario){
                    listaUsuarios.remove(i);//Se reemplaza todos sus datos
                    break;//nos salimos
                }
            }
            return "redirect:/crud";
    }
}
