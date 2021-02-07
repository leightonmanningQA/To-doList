'use strict'
const showallToDo = document.querySelector("#showToDo")


const printTodoscreen = (todolist) => {
    let user = document.createElement("p"); // <p> </p>
    let text = document.createTextNode(`${todolist}`); // username
    user.appendChild(text); // <p> username </p>
    showallToDo.appendChild(user);
}




const readAllTodo = () => {
    fetch("http://localhost:8082/todo/readAll")
    .then((response) => {
        // check that the response is OK (i.e. 200)
        if(response.status !== 200){
            throw new Error("I don't have a status of 200");
        }else{
            console.log(response);
            console.log(`response is OK (200)`);
            //json-ify it (which returns a promise)
            response.json().then((infofromserver) =>{
                console.log(infofromserver);
                console.log(infofromserver.data); // key - return array(6)
                for(let todos of infofromserver.data){
                    
                    printNameToScreen(todos.id);
                    printNameToScreen(todos.title);
                }
            })
        }
    }).catch((err) => {
        console.error(err);
    })
}