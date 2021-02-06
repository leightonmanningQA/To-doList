'use strict'

const createToDo = document.querySelector("#createToDo");

const createtask1 = document.querySelector("#task1input");
const createtask2 = document.querySelector("#task2input");
const createtask3 = document.querySelector("#task3input");

const createToDo = () => {

    const toDoHeading = createToDo.value;
    const task1 = createtask1.value
    const task2 = createtask2.value
    const task3 = createtask3.value
 
    let data = {
        title: toDoHeading,
        taskList: [
            task1,
            task2,
            task3
        ]
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
        })
        .catch(err => console.error('ERROR!' + err));
}