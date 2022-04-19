const adminUrl = '/api/admin'

const newUser = document.getElementById('tab2')
const roles = document.getElementById('RoleN')
const editRoles = document.getElementById('editRoles')
const editModal = document.getElementById('editModal')

const tbody = document.querySelector('tbody')
const getTableUsers=() => {
    let res = ''
    fetch(`${adminUrl}/users`)
        .then(res=>res.json())
        .then(users=>{
            users.forEach(user =>{
                res+=`
                <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.userLastName}</td>
                <td>${user.userAge}</td>
                <td>${user.userEmail}</td>
                <td>${user.roles.map(r=>r.roleName.replace('ROLE_',  ' '))} </td>
                 <td class="text-center"><button type="submit" class="btnEdit btn btn-primary"
                 data-bs-toggle="modal" data-bs-target="#editModal" >Edit</button></td>
                 
                <td class="text-center"><button type="submit" class="btnDel btn btn-danger" 
                data-bs-toggle="modal" data-bs-target="#deleteModal">Delete</button></td>
                </tr>`
            })
            tbody.innerHTML=res

        })
}


fetch(`${adminUrl}/users`)
    .then(response => response.json())
    .then(data => getTableUsers(data))
    .catch(error => console.log(error))

function getAllRoles(target) {
    fetch(`${adminUrl}/allroles`)
        .then(res => res.json())
        .then(roles => {
            let roleList = ''
            roles.forEach(role => {
                roleList += `<option value='${role.id}'>${role.roleName.replace('ROLE_', ' ')}</option>`
            })
            target.innerHTML = roleList
        })
}


let headers = new Headers();
headers.append('Content-Type', 'application/json; charset=utf-8');

let roleList = (options) => {
    let array = []
    for (let i = 0; i < options.length; i++) {
        if (options[i].selected) {
            let role = {id: options[i].value}
            array.push(role)
        }
    }
    return array;
}

getAllRoles(roles)

newUser.addEventListener('submit', (e) => {
    alert('privet')
    e.preventDefault()
    let options = document.querySelector('#RoleN');
    let setRoles = roleList(options)
    fetch(`${adminUrl}/users`, {
        method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({
            userName: document.getElementById("FirstNameN").value,
            userLastName: document.getElementById("LastNameN").value,
            userAge: document.getElementById("AgeN").value,
            userPassword: document.getElementById("PasswordN").value,
            userEmail: document.getElementById("EmailN").value,
            roles: setRoles
        })
    })
        .then(data => getTableUsers(data))
        .catch(error => console.log(error))

    document.getElementById("FirstNameN").value = ""
    document.getElementById("LastNameN").value = ""
    document.getElementById("AgeN").value = ""
    document.getElementById("PasswordN").value = ""
    document.getElementById("EmailN").value = ""
})

const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}


// EDIT user
let users = [];
const updateUser = (user) => {
    const foundIndex = users.findIndex(x => x.id === user.id);
    users[foundIndex] = user;
    getTableUsers(users);
}

on(document, 'click', '.btnEdit', e => {
    e.preventDefault()
    let userInfo = e.target.parentNode.parentNode
    document.getElementById('editId').value = userInfo.children[0].innerHTML
    document.getElementById('editFirstName').value = userInfo.children[1].innerHTML
    document.getElementById('editLastName').value = userInfo.children[2].innerHTML
    document.getElementById('editAge').value = userInfo.children[3].innerHTML
    document.getElementById('editEmail').value = userInfo.children[4].innerHTML
    document.getElementById('editPassword').value = userInfo.children[5].innerHTML
    editRoles.value = getAllRoles(editRoles)
    //document.getElementById('editRoles').value = userInfo.children[6].innerHTML
    alert('gotov')
    $('#editModal').modal('show')
     console.log(userInfo)
})


editModal.addEventListener('submit', (e) => {
    e.preventDefault();

    let options = document.querySelector('#editRoles');
    let setRoles = roleList(options)
    fetch(`${adminUrl}/users`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: document.getElementById('editId').value,
            userName: document.getElementById("editFirstName").value,
            userLastName: document.getElementById("editLastName").value,
            userAge: document.getElementById("editAge").value,
            userPassword: document.getElementById("editPassword").value,
            userEmail: document.getElementById("editEmail").value,
            roles: setRoles


        })
    })
        .then(res => res.json()).then(data => updateUser(data))
        .catch((e) => console.error(e))

    $('#editModal').modal('hide')
})
console.log(typeof jQuery)







