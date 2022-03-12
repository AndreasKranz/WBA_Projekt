async function fillTaskTable(editedTask) {
    document.getElementById("btnCreateForm").addEventListener('click', () => drawTaskForm());
    const url = 'http://localhost:8080/tasks';

    let json;
    try {
        const responsePromise = await fetch(url)
        if (!responsePromise.ok) {
            throw new Error("Error! status: ${response.status}");
        }
        json = await responsePromise.json();
    } catch (err) {
        console.log(err);
    }

    const arrHold = [];
    const arrSprintBack = [];
    const arrBacklog = [];
    const arrProgress = [];
    const arrReview = [];
    const arrDone = [];

    json.forEach(addToArray)



    let td_onHold = document.getElementById("tdHold");
    addToColoumn(td_onHold, arrHold);
    let td_backlog = document.getElementById("tdBacklog");
    addToColoumn(td_backlog, arrBacklog);
    let td_sprintBack = document.getElementById("tdSprint");
    addToColoumn(td_sprintBack, arrSprintBack);
    let td_progress = document.getElementById("tdProgress");
    addToColoumn(td_progress, arrProgress);
    let td_review = document.getElementById("tdReview");
    addToColoumn(td_review, arrReview);
    let td_done = document.getElementById("tdDone");
    addToColoumn(td_done, arrDone);

    function addToColoumn(td, arr) {
        td.innerHTML = "";
        let ul = document.createElement('ul');
        //let ul = td.firstChild;

        for (let i = 0; i < arr.length; i++) {
            let assignee = arr[i].assignedEmail;
            let taskId = arr[i].taskId;
            let title = arr[i].taskTitle;

            let btn = document.createElement('button');
            btn.name = "btnDetails";
            btn.innerHTML = "Show Details";
            btn.addEventListener('click', () => drawTask(taskId));

            let li = document.createElement('li');
            li.innerHTML = taskId + "<br/>" + title + "<br/>" + assignee;
            li.appendChild(btn);
            ul.appendChild(li);


        }
        td.appendChild(ul);
    }

    function addToArray(dto) {

        switch (dto.status) {
            case "ON_HOLD":
                arrHold.push(dto);
                break;
            case "BACKLOG":
                arrBacklog.push(dto);
                break;
            case "SPRINT_BACKLOG":
                arrSprintBack.push(dto);
                break;
            case "IN_PROGRESS":
                arrProgress.push(dto);
                break;
            case "UNDER_REVIEW":
                arrReview.push(dto);
                break;
            case "DONE":
                arrDone.push(dto);
                break;
            default:
                console.log("VALUE NOT DEFINED");
        }
    }
    if(editedTask != null){
        await drawTask(editedTask);
    } else {
        document.getElementById("taskDetails").innerHTML ="";
    }

}

async function drawTask(taskId) {
    const url = "http://localhost:8080/task/" + taskId + "/";

    let json;
    try {
        const responsePromise = await fetch(url);
        if (!responsePromise.ok) {
            throw new Error("Error! status: ${response.status}");
        }
        json = await responsePromise.json();
    } catch (err) {
        console.error(err);
    }

    const url1 = "http://localhost:8080/taskDetails";

    let resValue;
    try {
        const responsePromise = await fetch(url1);
        if(!responsePromise.ok){
            throw new Error("Error! status: {response.status}");
        }
        resValue = await responsePromise.text();
    } catch (err){
        console.log(err);
    }

    document.getElementById("taskDetails").innerHTML ="";
    document.getElementById("taskDetails").innerHTML = resValue;


    let inputTitle = document.getElementById("tTitle");
    inputTitle.defaultValue = json.taskTitle;
    let inputText =document.getElementById("tDescription");
    inputText.defaultValue = json.tdescription;
    let inputAssignee = document.getElementById("assignedEmail")
    inputAssignee.defaultValue = json.assignedEmail;
    let pAuthor =document.getElementById("pAuthor");
    pAuthor.innerHTML += json.authorEmail;
    let hId = document.getElementById("hTaskId");
    hId.innerHTML = json.taskId;
    let pCreateDate = document.getElementById("pCreated");
    pCreateDate.innerHTML += json.creationDate;
    let pEditDate = document.getElementById("pEdited");
    pEditDate.innerHTML += json.editDate;



    let btnSave = document.getElementById("btnSaveTask");
    btnSave.addEventListener('click',()=>saveEdits());
    let btnDelete = document.getElementById("btnDelete");
    btnDelete.addEventListener('click',()=>deleteTask(taskId));

    await drawComments(taskId);


    setDropDowns(json.tpriority, json.status);
}

async function deleteTask(taskId){
    const url = "http://localhost:8080/task/"+ taskId +"/delete";
    let res;
    try {
        const responsePromise = await fetch(url, {
            method:"DELETE",
            credentials:"include",
        })
        if(!responsePromise.ok){
            throw new Error()
        }
        res = await responsePromise.text();
    }catch (err){
        console.log(err);
    }

    await fillTaskTable();

}

async function drawComments(taskId) {


    const urlComments = "http://localhost:8080/task/" + taskId + "/comments";
    let json;
    try {
        const responsePromise = await fetch(urlComments)
        if (!responsePromise.ok) {
            throw new Error("Error! status: ${response.status}");
        }
        json = await responsePromise.json();
    } catch (err){
        console.log(err);
    }

    let ul = document.getElementById("commentList");
    let li ;//= document.createElement('li');

    json.forEach(commentToLi);

    function commentToLi(dto){
        li = document.createElement('li');
        li.innerHTML = "Author : " + dto.authorEmail + "<br>" + "Comment: " + dto.text  + "<br>" + "Created: " + dto.createDateTime;
        ul.appendChild(li);
    }

    document.getElementById("btnComment").addEventListener('click',()=> writeComment(taskId));
}

async function writeComment(idTask){
    const url = "http://localhost:8080/task/writecomment";
    const url1 = "http://localhost:8080/username";

    let commentText = document.getElementById("commentInput").value;


    const obj = {
        text : commentText,
        taskId: idTask,
    }

    let commentPayload = JSON.stringify(obj);

    let res;

    try {
        const responsePromise = fetch(url,{
            method: 'POST',
            credentials: 'include',
            headers : {
                'Content-Type': 'application/json'
            },
            body: commentPayload
        })
        if(responsePromise === null){
            throw new Error()
        }
        if(!responsePromise.ok){
            throw new Error()
        }
        res = await responsePromise.json();
        console.log(res);
    }catch (err){
        console.log(err)
    }


}

function setDropDowns(prio, status) {

    let selectedStatus;
    switch (status) {
        case "ON_HOLD":
            selectedStatus = 0;
            break;
        case "BACKLOG":
            selectedStatus = 1;
            break;
        case "SPRINT_BACKLOG":
            selectedStatus = 2;
            break;
        case "IN_PROGRESS":
            selectedStatus = 3;
            break;
        case "UNDER_REVIEW":
            selectedStatus = 4;
            break;
        case "DONE":
            selectedStatus = 5;
            break;
        default:
            selectedStatus = 1;
    }
    document.getElementById("selectStatus").selectedIndex = selectedStatus;

    let selectedPriority;
    switch (prio) {
        case "NORMAL":
            selectedPriority = 0;
            break;
        case "HIGH":
            selectedPriority = 1;
            break;
        case "LOW":
            selectedPriority = 2;
            break;
        default:
            selectedPriority = 0;
            break;
    }
    document.getElementById('selectPrio').selectedIndex = selectedPriority;
}

async function saveEdits() {

    let taskTitleValue = document.getElementById("tTitle").value;
    let descriptionValue = document.getElementById("tDescription").value;
    let assignedEmailValue = document.getElementById("assignedEmail").value;
    let priorityValue = document.getElementById("selectPrio").value;
    let statusValue = document.getElementById("selectStatus").value;
    let temp = document.getElementById("hTaskId").innerHTML;
    let taskIdValue = new Number(temp);

    const obj = {
        taskTitle: taskTitleValue,
        tdescription: descriptionValue,
        assignedEmail: assignedEmailValue,
        tpriority: priorityValue,
        status: statusValue,
        taskId: taskIdValue,
    }

    let payload = JSON.stringify(obj);

    const url = 'http://localhost:8080/task/edit';

    let res;

    try {
        const responsePromise = await fetch(url,{
            method: 'POST',
            credentials : 'include',
            headers : {
                'Content-Type': 'application/json'
            },
            body: payload
        })
        if (!responsePromise.ok) {
            throw new Error("Error! status: ${response.status}");
        }
        res = await responsePromise.json();
        console.log(res);
    } catch (err) {
        console.log(err);
    }
    await fillTaskTable(taskIdValue);
}

async function drawTaskForm() {
    const url = "http://localhost:8080/taskform";
    let res;
    try {
        const responsePromise = await fetch(url)
        if (!responsePromise.ok) {
            throw new Error("Error! status: ${response.status}");
        }
        res = await responsePromise.text();
        console.log(res);
    } catch (err) {
        console.log(err);
    }
    document.getElementById("taskDetails").innerHTML = res;

    document.getElementById("btnCreate").addEventListener('click',()=>createTask());


}

async function createTask(){
    const urlEmails = "http://localhost:8080/usernames";

    let upns;

    try {
        const responsePromise = await fetch(urlEmails)
        if (!responsePromise.ok){
            throw new Error();
        }
        upns = await responsePromise.json();
        console.log(upns);
    }
    catch (err){
        console.log(err);
    }



    let title = document.getElementById("taskTitle").value;
    let description = document.getElementById("taskText").value;
    let assignee =document.getElementById("taskAssignee").value;
    //Todo checken ob es User gibt
    let prio = document.getElementById("taskPriority").value;

    let obj = {
        taskTitle: title,
        tdescription: description,
        assignedEmail: assignee,
        tpriority: prio,
    }

    let createPayload = JSON.stringify(obj);

    const url = 'http://localhost:8080/task/create';

    let res;
    try {
        const responsePromise = await fetch(url,{
            method: 'POST',
            credentials : 'include',
            headers : {
                'Content-Type': 'application/json'
            },
            body: createPayload
        })
        if (!responsePromise.ok) {
            throw new Error("Error! status: ${response.status}");
        }
        res = await responsePromise.json();
        console.log(res);
    } catch (err) {
        console.log(err);
    }

    fillTaskTable();
}


