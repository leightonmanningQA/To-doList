'use strict'
const showoneToDo = document.querySelector("#showToDo")
const todoIdTextbox = document.querySelector("#ToDolistid")
const showAllToDo = document.querySelector("#showToDoAll")
const showOneTodo=document.querySelector("#showToDoOne")


const printTodoscreen = (id, title, taskList) => {
    let todo = document.createElement("h3"); // <p> </p>
    let text = document.createTextNode(`${id}. ${title}:`);
    let todo1 = document.createElement("h4");
    let text1 = document.createTextNode(`${taskList}`);
    todo1.appendChild(text1);
    todo.appendChild(text); // <p> username </p>
    showOneTodo.appendChild(todo);
    showOneTodo.appendChild(todo1);

}

const printAllTodoscreen = (id, title, taskList) => {
    let todo = document.createElement("h3"); // <p> </p>
    let text = document.createTextNode(`${id}. ${title}:`);
    let todo1 = document.createElement("h4");
    let text1 = document.createTextNode(`${taskList}`);
    todo1.appendChild(text1);
    todo.appendChild(text); // <p> username </p>
    showAllToDo.appendChild(todo);
    showAllToDo.appendChild(todo1);

}




const readAllTodo = () => {
    fetch("http://localhost:8082/todo/readAll")
        .then((response) => {
            // check that the response is OK (i.e. 200)
            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`);
                //json-ify it (which returns a promise)
                response.json().then((infofromserver) => {
                    console.log(infofromserver);
                    console.log(infofromserver.data); // key - return array(6)
                    for (let todos of infofromserver) {
                        let myJSON = JSON.stringify(todos.taskList);
                        printAllTodoscreen(todos.id, todos.title, myJSON);




                    }
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}

const readTodo = () => {
    const searchid = todoIdTextbox.value;
    fetch("http://localhost:8082/todo/read/" + searchid)
        .then((response) => {
            // check that the response is OK (i.e. 200)
            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`);
                //json-ify it (which returns a promise)
                response.json().then((infofromserver) => {
                    console.log(infofromserver);
                    console.log(infofromserver.data);
                    let myJSON = JSON.stringify(infofromserver.taskList);
                    printTodoscreen(infofromserver.id, infofromserver.title, myJSON);

                })
            }

        }).catch((err) => {
            console.error(err);
        })
}