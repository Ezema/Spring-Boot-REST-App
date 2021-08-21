// Call the dataTables jQuery plugin
$(document).ready(function() {
});
async function loginUser(){
    let userData = {}
    userData.email = document.getElementById('exampleInputEmail').value;
    userData.password = document.getElementById('exampleInputPassword').value;

    const rawResponse = await fetch('api/login',
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
    if(res!= 'FAIL'){
        localStorage.token = res;
        localStorage.email = userData.email;
        window.location.href = 'index.html';
    }else{
        alert("Login error")
    }
}

