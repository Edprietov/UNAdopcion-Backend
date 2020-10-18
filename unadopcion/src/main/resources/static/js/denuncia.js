function validar() {
    var usuario, nombre;
    usuario = document.getElementById("usuario").value;
    nombre = document.getElementById("nombre").value;

    expresion = /\w+@\w+\.+[a-z]/;

    if (usuario === "" || nombre=== ""){
        alert("Todos los campos son obligatorios");
        return false;
    }
    else if (usuario.length>20){
        alert("El usuario solo debe tener 20 caracteres como máximo ");
        return false;
    }
    else if (nombre.length>30){
        alert("El nombre es muy largo");
        return false;
    }
}
