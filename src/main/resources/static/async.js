document.getElementById('btnAdd').addEventListener('click', async ()=> {
    const name = document.getElementById("FirstNameN")
    const last = document.getElementById("LastNameN")
    const age = document.getElementById("AgeN")
    const pass = document.getElementById("PasswordN")
    const mail = document.getElementById("EmailN")
    const rol =  document.getElementById('RoleN')

    const userName = name.value
    const userLastName = last.value
    const userAge = age.value
    const userPassword = pass.value
    const userEmail = mail.value
    const roles =  rol.value

    const res = await fetch('http://localhost:8080/api/admin/users', {
        method:'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({userName, userLastName, userAge, userEmail, roles})
    })
    const todo = await res.json()
    todoToHTML(todo)

})

async function getAllUsers() {
    const res = await fetch('http://localhost:8080/api/admin/users')
    const todos = await res.json()

    todos.forEach(todo => todoToHTML(todo))
}

window.addEventListener('DOMContentLoaded', getAllUsers)

function todoToHTML({id, userName, userLastName, userAge, userEmail, roles}) {
    const todoList = document.getElementById('tbody')
    todoList.insertAdjacentHTML('beforeend',
        `
                <tr>
                <td>${id}</td>
                <td>${userName}</td>
                <td>${userLastName}</td>
                <td>${userAge}</td>
                <td>${userEmail}</td>
                <td>${roles.map(r=>r.roleName.replace('ROLE_',  ' '))} </td>
                <td class="text-center"><button type="submit" class="btnEdit btn btn-primary"
                 data-bs-toggle="modal" data-bs-target="#editModal">Edit</button></td>
                 
                <td class="text-center"><button type="submit" class="btnDel btn btn-danger" 
                data-bs-toggle="modal" data-bs-target="#deleteModal">Delete</button></td>
                </tr>`

    )
}