document.addEventListener('DOMContentLoaded', (event) => {
    const urlParams = new URLSearchParams(window.location.search);
    const idPartido = urlParams.get('id');

    if (idPartido) {
        fetch(`ServAdminPartido?id=${idPartido}`)
            .then(response => response.json())
            .then(data => cargarPartido(data))
            .catch(error => console.error('Error:', error));
    }

    document.getElementById('formEditar').addEventListener('submit', function(event) {
        event.preventDefault();
        guardarCambios();
    });
});

function cargarPartido(partido) {
    document.getElementById('idPartido').value = partido.idPartido;
    document.getElementById('nomEquipoA').value = partido.nomEquipoA;
    document.getElementById('resultEquipoA').value = partido.resultEquipoA;
    document.getElementById('nomEquipoB').value = partido.nomEquipoB;
    document.getElementById('resultEquipoB').value = partido.resultEquipoB;
}

function submitForm() {
    const idPartido = document.getElementById('idPartido').value;
    const nomEquipoA = document.getElementById('nomEquipoA').value;
    const resultEquipoA = document.getElementById('resultEquipoA').value;
    const nomEquipoB = document.getElementById('nomEquipoB').value;
    const resultEquipoB = document.getElementById('resultEquipoB').value;

    const partido = {
        idPartido: idPartido,
        nomEquipoA: nomEquipoA,
        resultEquipoA: resultEquipoA,
        nomEquipoB: nomEquipoB,
        resultEquipoB: resultEquipoB
    };

    fetch('ServAdminPartido', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(partido)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Partido actualizado:', data);
        alert('Partido actualizado');
        window.location.href = 'adminPartidos.html';
    })
    .catch(error => console.error('Error:', error));
}
