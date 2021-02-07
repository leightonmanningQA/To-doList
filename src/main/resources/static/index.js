'use strict'

const ToDoTitle = document.querySelector("#ToDoTitle");
const showidtext = document.querySelector("#showID")
const createtask1 = document.querySelector("#task1input");
const createtask2 = document.querySelector("#task2input");
const createtask3 = document.querySelector("#task3input");

const todoidbox = document.querySelector("#inputid")

const printIDToScreen = (id) => {
    let todo = document.createElement("h3"); // <p> </p>
    let text = document.createTextNode(`${id} THIS IS YOUR TODOLIST ID USE BELOW`); // username
    todo.appendChild(text); // <p> username </p>
    showidtext.appendChild(todo);
}

const createToDo = () => {
    const toDoHeading = ToDoTitle.value;
    let data = {
        title: toDoHeading,
    }
    console.log(data);

    fetch("http://localhost:8082/todo/create", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
                    printIDToScreen(info.id);

        })
        .catch(err => console.error('ERROR!' + err));
}

const createTask = () => {
    const task1 = createtask1.value
    const task2 = createtask2.value
    const task3 = createtask3.value
		const todoid = todoidbox.value 
    let data = {
        description:task1,
        myToDo:{id:todoid}
    }
    let data1 = {
        description:task2,
        myToDo:{id:todoid}
    }
    let data2 = {
        description:task3,
        myToDo:{id:todoid}
    }
    console.log(data,data1,data2);
    fetch("http://localhost:8082/task/create", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
        })
        .catch(err => console.error('ERROR!' + err));
        fetch("http://localhost:8082/task/create", {
            method: "POST",
            body: JSON.stringify(data1),
            headers: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": "*"
            }
        })
            .then(response => response.json())
            .then(info => {
                console.log(info);
            })
            .catch(err => console.error('ERROR!' + err));
            fetch("http://localhost:8082/task/create", {
                method: "POST",
                body: JSON.stringify(data2),
                headers: {
                    "Content-Type": "application/json",
                    "Access-Control-Allow-Origin": "*"
                }
            })
                .then(response => response.json())
                .then(info => {
                    console.log(info);
                })
                .catch(err => console.error('ERROR!' + err));
        
}