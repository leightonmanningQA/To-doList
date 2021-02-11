'use static'

const ToDoTitle = document.querySelector("#ToDoTitle");
const showidtext = document.querySelector("#showID")
const taskdesctext1 = document.querySelector("#task1input")
const taskdesctext2 = document.querySelector("#task2input")
const taskdesctext3 = document.querySelector("#task3input")
const taskidtext = document.querySelector("#inputid")
const taskfinishtext = document.querySelector("#showTaskText")

const taskupdatedesc = document.querySelector("#updatetaskdesc")
const taskupdatetodoid= document.querySelector("#updatetasktodoid")
const taskupdateid = document.querySelector("#inputtaskid")
const taskdisplayupdate = document.querySelector("#showUpdateTask")
const todoupdateid= document.querySelector("#inputid2")
const todoupdatetitle= document.querySelector("#todoinput2")
const todoupdateresult= document.querySelector("#showUpdate")

showTaskText
showUpdateTask

const printIDToScreen = (id,title) => {
    let todo = document.createElement("h4"); 
    let text = document.createTextNode(`Id: ${id}   Title: ${title}`); 
    todo.appendChild(text); 
    showidtext.appendChild(todo);
}
const printTaskToScreen = () => {
    let task = document.createElement("h4"); 
    let text = document.createTextNode(`Successfully added! Add more or head to read!`); 
    task.appendChild(text); 
    taskfinishtext.appendChild(task);
}
const printUpdatedTask = (id,description) => {
    let task = document.createElement("h4"); 
    let text = document.createTextNode(`Id: ${id}   New Description: ${description}`); 
    task.appendChild(text); 
    taskdisplayupdate.appendChild(task);
}
const printUpdatedToDo = (id,description) => {
    let task = document.createElement("h4"); 
    let text = document.createTextNode(`Id: ${id}   New Title: ${description}`); 
    task.appendChild(text); 
    todoupdateresult.appendChild(task);
}
const updateToDo = () => {
   
    const todoId =todoupdateid.value
    const todoTitle = todoupdatetitle.value
    let data = {
        title:todoTitle,      
    }
    console.log(data);

    fetch("http://localhost:8082/todo/update/"+todoId, {
        method: "PUT",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
            printUpdatedToDo(info.id,info.title);
            
        })
        .catch(err => console.error('ERROR!' + err));
}
const updateTask = () => {
   
    const taskId =taskupdateid.value
    const taskDescription = taskupdatedesc.value
    const todoId = taskupdatetodoid.value
    let data = {
        description:taskDescription,
        myToDo:{id:todoId}

    }
    console.log(data);

    fetch("http://localhost:8082/task/update/"+taskId, {
        method: "PUT",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
            printUpdatedTask(info.id,info.description);
            
        })
        .catch(err => console.error('ERROR!' + err));
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
            // deleteIDToScreen();
            printIDToScreen(info.id,info.title);
            
        })
        .catch(err => console.error('ERROR!' + err));
}


const createTask = () => {
    const task1 = taskdesctext1.value
    const task2 = taskdesctext2.value
    const task3 = taskdesctext3.value
	const todoid = taskidtext.value 
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
            printTaskToScreen();
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