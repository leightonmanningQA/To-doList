'use strict'
const showoneToDo = document.querySelector("#showToDo")
const todoIdTextbox = document.querySelector("#ToDolistid")
const showAllToDo = document.querySelector("#showToDoAll")
const showOneTodo = document.querySelector("#showToDoOne")

const deleteTodoID = document.querySelector('#deletetodoid')
const deleteTaskID = document.querySelector('#deletetaskid')
const deleteShowTodo = document.querySelector('#showToDoDelete')
const deleteShowTask = document.querySelector('#showTaskDelete')


const deleteTodoscreen = () => {
    let todo = document.createElement("h3");
    let text = document.createTextNode(``);
    let todo1 = document.createElement("h4");
    let text1 = document.createTextNode(``);
    todo1.appendChild(text1);
    todo.appendChild(text); 
    showOneTodo.appendChild(todo);
    showOneTodo.appendChild(todo1);

}


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

const printAllTodoscreen = (id, title,taskid, taskList) => {
    let todo = document.createElement("h3"); // <p> </p>
    let text = document.createTextNode(`${id}. ${title}:`);
    let todo1 = document.createElement("h4");
    let text1 = document.createTextNode(`${taskid}. ${taskList}`);
    todo1.appendChild(text1);
    todo.appendChild(text); // <p> username </p>
    showAllToDo.appendChild(todo);
    showAllToDo.appendChild(todo1);

}

const printDelete = () => {
    let todo = document.createElement("h3");
    let text = document.createTextNode(`Successfully Deleted`);
    todo.appendChild(text);
    deleteShowTodo.appendChild(todo);
}
const printDelete2 = () => {
    let todo = document.createElement("h3");
    let text = document.createTextNode(`Successfully Deleted`);
    todo.appendChild(text);
    deleteShowTask.appendChild(todo);
}


function readAllData(data) {
    let listOfToDos = document.createElement("ul");
    for (let i = 0; i < data.length; i++) {
        let id = document.createElement("li")
        let title = document.createElement("li")
        let taskList = document.createElement("ul")

        for (let j = 0; j < data[i].taskList.length; j++) {
            let id = document.createElement("li")
            let description = document.createElement("li")

            description.innerText = `ID: ${data[i].taskList[j].id} Description: ${data[i].taskList[j].description}`
            taskList.appendChild(description)
        }
        title.innerText = `ID: ${data[i].id} Title: ${data[i].title}`;
        listOfToDos.appendChild(title)
        listOfToDos.appendChild(taskList)

    }
    showAllToDo.appendChild(listOfToDos);
}
function readAllData1(data) {
    let listOfToDos1 = document.createElement("ul");
    
        let id = document.createElement("li")
        let title = document.createElement("li")
        let taskList = document.createElement("ul")

        for (let j = 0; j < data.taskList.length; j++) {
            let id = document.createElement("li")
            let description = document.createElement("li")

            description.innerText = `ID: ${data.taskList[j].id} Description: ${data.taskList[j].description}`
            taskList.appendChild(description)
        }
        title.innerText = `ID: ${data.id} Title: ${data.title}`;
        listOfToDos1.appendChild(title)
        listOfToDos1.appendChild(taskList)

    
    showOneTodo.appendChild(listOfToDos1);
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
                response.json().then((data) => {
                    console.log(data);
                    
                    readAllData(data)
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
                response.json().then((info) => {
                    console.log(info);
                    readAllData1(info)
                })
            }

        }).catch((err) => {
            console.error(err);
        })
}






const deleteToDo = () => {
    const ID = deleteTodoID.value;
    fetch("http://localhost:8082/todo/delete/" + ID, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
    })
        .then(response => response)
        .then(info => {
            console.log(info);
            printDelete();
        })
        .catch(err => console.error('ERROR!' + err));
};

const deleteTask = () => {
    const ID = deleteTaskID.value;
    fetch("http://localhost:8082/task/delete/" + ID, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
    })
        .then(response => response)
        .then(info => {
            console.log(info);
            printDelete2();

        })
        .catch(err => console.error('ERROR!' + err));
};

