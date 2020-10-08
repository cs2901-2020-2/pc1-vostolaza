package practica1;

import java.util.logging.Logger;

class Cliente {
    String nombre;
    static final Logger logger = Logger.getLogger(Cliente.class.getName());

    public Cliente(String _nombre){nombre = _nombre;}

    public void notify(String title, String link){
        String output = String.format("%s:  Se ha registrado la clase \"%s\" con el URL %s", nombre, title, link);
        logger.info(output);
    }
}