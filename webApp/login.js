/**
 * 
 */
function validarInicioSesion() {
    let nombre = document.getElementById('nombre').value;
    let contrasena = document.getElementById('contrasena').value;
    let ok = true;

    if (nombre === "") {
        ok = false;
        document.getElementById('nombre').style.background = "red";
    } else {
        document.getElementById('nombre').style.background = ""; // restaurar el fondo si no está vacío
    }

    if (contrasena === "") {
        ok = false;
        document.getElementById('contrasena').style.background = "red";
    } else {
        document.getElementById('contrasena').style.background = ""; // restaurar el fondo si no está vacío
    }

    if (ok === true) {
        document.getElementById("formularioInicioSesion").submit();
    }
}
