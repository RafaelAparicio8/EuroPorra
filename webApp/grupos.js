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

    // Creating HTML for each group
    let html = "";
    for (let grupo in grupos) {
        html += `<h2>Grupo ${grupo}</h2>`;
        html += "<table border='1'>";
        html += "<tr><th>Nombre</th><th>Puntos</th><th>GF</th><th>GC</th><th>DIF</th></tr>";
        for (let i = 0; i < grupos[grupo].length; i++) {
            let seleccion = grupos[grupo][i];
            html += `<tr><td>${seleccion.nombre}</td><td>${seleccion.puntos}</td><td>${seleccion.gf}</td><td>${seleccion.gc}</td><td>${seleccion.dif}</td></tr>`;
        }
        html += "</table>";
    }

    document.getElementById("listadoSelecciones").innerHTML = html;
}

window.onload = function() {
    llamada();
}
