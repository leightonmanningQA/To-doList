'use strict'

const ToDoTitle = document.querySelector("#ToDoTitle");

const createtask1 = document.querySelector("#task1input");
const createtask2 = document.querySelector("#task2input");
const createtask3 = document.querySelector("#task3input");




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
            return info.retrieveData(id)
        })
        .catch(err => console.error('ERROR!' + err));
}

const readAllTodo = () => {
    fetch("http://localhost:8082/todo/readAll")
    .then(response => response.json())
    .then(info => {
        for(let todo of info) {
            console.log(todo);
        }
    } 
        
        
        
        )
}


// const createTask = () => {
//     const task1 = createtask1.value
//     const task2 = createtask2.value
//     const task3 = createtask3.value
//     let data = {
//         taskList:[{description:task1},{description:task2},{description:task3}]
//     }
//     console.log(data);
//     fetch("http://localhost:8082/task/create", {
//         method: "POST",
//         body: JSON.stringify(data),
//         headers: {
//             "Content-Type": "application/json",
//             "Access-Control-Allow-Origin": "*"
//         }
//     })
//         .then(response => response.json())
//         .then(info => {
//             console.log(info);
//         })
//         .catch(err => console.error('ERROR!' + err));
        
// }