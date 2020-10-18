const form = document.querySelector('form');

const formEvent = form.addEventListener('submit', event => {
    event.preventDefault();

    const usuario = document.querySelector('#usuario').value;
    const nombre = document.querySelector('#nombre').value;
    const email = document.querySelector('#email').value;
    const telefono = document.querySelector('#telefono').value;
    const lugar = document.querySelector('#lugar').value;
    const informacion = document.querySelector('#informacion').value;

    const user = { usuario, nombre, email, telefono, lugar, informacion};
    createUser(user);
});


const createLi = (usuario) => {
    const nuevo = document.createElement('nuevo');
    // add user details to `li`
    nuevo.textContent = `${usuario.id}: ${usuario.nombre} ${usuario.contraseña} ${usuario.email} ${usuario.telefono} ${usuario.lugar}  ${usuario.informacion}`;
    return li;
};

const appendToDOM = (usuarios) => {
    const ul = document.querySelector('ul');
    //iterate over all users
    useuarios.map(usuario => {
        ul.appendChild(createLi(usuario));
    });
};

const fetchUsers = () => {
    axios.get('direccion de la tabla usuarios')
        .then(response => {
            const usuario = response.data.data;
            console.log(`GET list users`, usuario);
            // append to DOM
            appendToDOM(usuario);
        })
        .catch(error => console.error(error));
};

const createUser = (usuario) => {
    axios.post('direccion de la tabla usuarios', usuario)
        .then(response => {
            const addedUsuario = response.data;
            console.log(`POST: user is added`, addedUsuario);
            // append to DOM
            appendToDOM([addedUsuario]);
        })
        .catch(error => console.error(error));
};

fetchUsers();



