const adminUrl = '/api/admin'

const renderUsers = (el) => {
        return`
                <tr>
                <td>${el.id}</td>
                <td>${el.userName}</td>
                <td>${el.userLastName}</td>
                <td>${el.userAge}</td>
                <td>${el.userEmail}</td>
                <td>${el.rolesString} </td>
                <td class="text-center"><button type="submit" class="btnEdit btn btn-primary"
                 data-bs-toggle="modal" data-bs-target="#editModal">Edit</button></td>
                 
                <td class="text-center"><button type="submit" class="btnDel btn btn-danger" 
                data-bs-toggle="modal" data-bs-target="#deleteModal">Delete</button></td>
                </tr>`

}


const renderPost = (data) =>{
    let temp = ""
    data.forEach((el)=>{
        temp += renderUsers(el)
    })
    document.getElementById("tbody").innerHTML += temp
}

fetch(`${adminUrl}/users`)
    .then(res=>res.json())
    .then(data=> renderPost(data))

document.getElementById("formAdd").addEventListener('submit', (e) => {
    e.preventDefault()

    let role = []

    let elem = document.getElementById("RoleN")
    for (let i = 0; i < elem.options.length; i++) {
        if (elem.options[i].selected)
           role.push(elem.options[i].text)
/*        if (elem.options[i].selected === 'ROLE_ADMIN') {
            role[0] = 1
            role[1] = role.push(elem.options[i].text)
        } else
        if (elem.options[i].selected === 'ROLE_USER') {
            role[0] = 2
            role[1] = role.push(elem.options[i].text) }*/

        elem.options[i].selected = false
    }




    let user = {
        id : 0,
        userName: document.getElementById("FirstNameN").value,
        userLastName: document.getElementById("LastNameN").value,
        userAge: document.getElementById("AgeN").value,
        userPassword: document.getElementById("PasswordN").value,
        userEmail: document.getElementById("EmailN").value,
        roles : role
    }
    document.getElementById("FirstNameN").value = ""
    document.getElementById("LastNameN").value = ""
    document.getElementById("AgeN").value = ""
    document.getElementById("PasswordN").value = ""
    document.getElementById("EmailN").value = ""

    let response = fetch(`${adminUrl}/users`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
        .then(res=>res.json())
        .then(data => {
            const dataArr = []
            dataArr.push(data)
            renderPost(dataArr)

        })
    console.log(role)
})



alert('ebat')








