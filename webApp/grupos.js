function llamada() {
    let xhr = new XMLHttpRequest();
    let resultados;

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                try {
                    resultados = JSON.parse(xhr.responseText);
                } catch (e) {
                    console.error("Error parsing JSON: ", e);
                }
            }
        }
    };

    xhr.open("GET", "ServGrupos", false);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();

    mostrar(resultados);
}

function mostrar(resultados) {
    // Grouping selections by group
    let grupos = {};
    for (let i = 0; i < resultados.length; i++) {
        let seleccion = resultados[i];
        if (!grupos[seleccion.grupo]) {
            grupos[seleccion.grupo] = [];
        }
        grupos[seleccion.grupo].push(seleccion);
    }

    // organización por grupos en tablas con estilos e iconos:
    let html = "";
    for (let grupo in grupos) {
        html += `<div class="grupos">`;
        html += `<h3>Grupo ${grupo}</h3>`;
        html += "<table>";
        html += "<tr><th></th><th>Equipo</th><th>Pts</th><th>GF</th><th>GC</th><th>DF</th></tr>";
        for (let i = 0; i < grupos[grupo].length; i++) {
            let seleccion = grupos[grupo][i];
            // Construye la URL de la imagen del icono
            let iconoUrl = `banderas/${seleccion.nombre}.png`;
            html += `<tr>`;
            // Inserta el icono
            html += `<td><img src="${iconoUrl}" alt="${seleccion.nombre}"></td>`;
            html += `<td>${seleccion.nombre}</td>`;
            html += `<td>${seleccion.puntos}</td>`;
            html += `<td>${seleccion.gf}</td>`;
            html += `<td>${seleccion.gc}</td>`;
            html += `<td>${seleccion.dif}</td>`;
            html += `</tr>`;
        }
        html += "</table>";
        html += `</div>`;
    }

    document.getElementById("listadoSelecciones").innerHTML = html;
}

window.onload = function() {
    llamada();

}
