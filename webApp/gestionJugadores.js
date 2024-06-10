function llamada() {
    fetch('ServJugadores')
        .then(response => response.json())
        .then(data => mostrar(data))
        .catch(error => console.error('Error:', error));
}

function mostrar(resultados) {
    let html = "<table>";
    html += "<tr><th>ID</th><th>Nombre</th><th>Selección</th><th>Goles</th></tr>";

    for (let i=0; i<resultados.length; i++){
		let jugador = resultados[i];
		
        html += `<tr>
                    <td>${jugador.id}</td>
                    <td>${jugador.nombre}</td>
                    <td>${jugador.seleccion}</td>
                    <td>${jugador.goles}</td>
                    <td>
                        <a href='editarJugador.html?id=${jugador.id}&op=2'>Editar</a>
                    </td>
                 </tr>`;
    }
    html += "</table>";
    document.getElementById("tablaJugadores").innerHTML = html;
}
