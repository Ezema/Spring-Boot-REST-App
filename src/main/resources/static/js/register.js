// Call the dataTables jQuery plugin
$(document).ready(function() {
});
async function registerUser(){
    let userData = {}
    userData.name = document.getElementById('exampleFirstName').value;
    userData.surname = document.getElementById('exampleLastName').value;
    userData.email = document.getElementById('exampleInputEmail').value;
    userData.password = document.getElementById('exampleInputPassword').value;
    let repeatedPassword = document.getElementById('exampleRepeatPassword').value;
    if (repeatedPassword != userData.password) {
        alert("ERROR: The passwords don't match");
        return;
    }
    console.log('userdata is ',JSON.stringify(userData))
    const rawResponse = await fetch('api/users',
    {
            method: 'POST',
            headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        }
    )
    const res = await rawResponse.text();
    if(res){
        alert("Account created")
        window.location.href = 'login.html';
    }else{
        alert("An error has occured")
        window.location.href = 'register.html';
    }
}

