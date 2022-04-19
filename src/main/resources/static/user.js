const userUrl = '/api/user/users'
const tbody = document.getElementById('tbody')
function getInfoUsers() {
    let res = ''
    fetch(`${userUrl}`)
        .then(res=>res.json())
        .then(user=>{
                res+=`
                <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.userLastName}</td>
                <td>${user.userAge}</td>
                <td>${user.userEmail}</td>
               <td>${user.roles.map(r=>r.roleName.replace('ROLE_',  ' '))} </td>
                </tr>`
            tbody.innerHTML=res;
        })
}
getInfoUsers()
alert('ebat rabotaet?')




