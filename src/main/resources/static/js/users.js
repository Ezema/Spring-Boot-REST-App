// Call the dataTables jQuery plugin
$(document).ready(function() {
  loadUsers()
  $('#users').DataTable();
});
function loadUsers(){
  (async () => {
    const rawResponse = await fetch('api/users', {
      method: 'GET',
      headers: getHeaders()
    });
    const users = await rawResponse.json();

    let tableBodyOuterHTML = "<tr>";

    users.forEach((user)=>{
      let rowHTML =
          ` <td>${user.id}</td>
            <td>${user.name+' '+user.surname}</td>            
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>${user.phone==(null|'NULL'|'null')?'-':user.phone}</td>
            <td>
              <a href="#" class="btn btn-danger btn-circle btn-sm" onclick="deleteUserById(${user.id})">
                <i class="fas fa-trash"></i>
              </a>
            </td>
          </tr>
          `
      tableBodyOuterHTML=tableBodyOuterHTML+rowHTML;
      }
    )
    tableBodyOuterHTML+="</tr>";
    document.querySelector("tbody").outerHTML =tableBodyOuterHTML;
  })();
}

let getHeaders = ()=>{
  return {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'Authorization': localStorage.token,
  }
}
async function deleteUserById(id){
  if(!confirm('Are you sure you want to proceed?')){
    return;
  }
  const rawResponse = await fetch(`api/user/${id}`, {
    method: 'DELETE',
    headers: getHeaders()
  });
  location.reload()
}