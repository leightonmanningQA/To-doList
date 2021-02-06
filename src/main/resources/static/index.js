'use strict'

const ToDoTitle = document.querySelector("#ToDoTitle");

const createtask1 = document.querySelector("#task1input");
const createtask2 = document.querySelector("#task2input");
const createtask3 = document.querySelector("#task3input");





const retrieveData = () => {
    fetch("http://localhost:8082/todo/create")
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
                for(let users of infofromserver.data){
                    console.log(users.first_name);
                    printNameToScreen(users.first_name);
                }
            })
        }
    }).catch((err) => {
        console.error(err);
    })
}




const createToDo = () => {

    const toDoHeading = ToDoTitle.value;

 
    let data = {
        title: toDoHeading,
        taskList:[{description:task1},{description:task2},{description:task3}]
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
// const updateToDo = () => {
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