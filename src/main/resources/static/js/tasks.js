async function createTaskTable() {
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

    /*let table = document.createElement('table');
    let thead = document.createElement('thead');
    let tbody = document.createElement('tbody');

    table.appendChild(thead);
    table.appendChild(tbody);

    let row_1 = document.createElement('tr');
    let heading_onHold = document.createElement('th');
    heading_onHold.innerHTML = "ON HOLD";
    let heading_sprintBack = document.createElement('th');
    heading_sprintBack.innerHTML = "BACKLOG";
    let heading_sprint = document.createElement('th');
    heading_sprint.innerHTML = "SPRINT BACKLOG";
    let heading_progress = document.createElement('th');
    heading_progress.innerHTML = "IN PROGRESS";
    let heading_review = document.createElement('th');
    heading_review.innerHTML = "UNDER REVIEW";
    let heading_done = document.createElement('th');
    heading_done.innerHTML = "DONE";

    row_1.appendChild(heading_onHold);
    row_1.appendChild(heading_sprintBack);
    row_1.appendChild(heading_sprint);
    row_1.appendChild(heading_progress);
    row_1.appendChild(heading_review);
    row_1.appendChild(heading_done);
    tbody.appendChild(row_1);

    let row_2 = document.createElement('tr');
*/
    //let td_onHold = document.createElement('td');
    let td_onHold = document.getElementById("tdHold");
    addToColoumn(td_onHold, arrHold);
    //let td_backlog = document.createElement('td');
    let td_backlog = document.getElementById("tdBacklog");
    addToColoumn(td_backlog, arrBacklog);
    //let td_sprintBack = document.createElement('td');
    let td_sprintBack = document.getElementById("tdSprint");
    addToColoumn(td_sprintBack, arrSprintBack);
    //let td_progress = document.createElement('td');
    let td_progress = document.getElementById("tdProgress");
    addToColoumn(td_progress, arrProgress);
    //let td_review = document.createElement('td');
    let td_review = document.getElementById("tdReview");
    addToColoumn(td_review, arrReview);
    //let td_done = document.createElement('td');
    let td_done = document.getElementById("tdDone");
    addToColoumn(td_done, arrDone);

    /*    row_2.appendChild(td_onHold);
        row_2.appendChild(td_backlog);
        row_2.appendChild(td_sprintBack);
        row_2.appendChild(td_progress);
        row_2.appendChild(td_review);
        row_2.appendChild(td_done);

        tbody.appendChild(row_2);


        document.getElementById('tableSpace').appendChild(table)
    */
    function addToColoumn(td, arr) {
        let ul = document.createElement('ul');
        //let ul = td.firstChild;

        for (let i = 0; i < arr.length; i++) {
            let assignee = arr[i].assignedEmail;
            let taskId = arr[i].taskId;
            let title = arr[i].taskTitle;

            //let button = "<button onclick=\"drawTask(" + taskId + ")\" id='btnDetails' name='btnDetails'>show Details</button>"
            let btn = document.createElement('button');
            //btn.id = "btnDetails";
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



    /*let br = document.createElement('br');

    let div = document.createElement('div');
    div.id = "editTask";

    let taskForm = document.createElement('form');
    taskForm.id = "taskForm";
    */
    //let inputTitle = document.createElement('input');
    let inputTitle = document.getElementById("tTitle");
    //inputTitle.id = "tTitle";
    //inputTitle.name = "tTitle";
    //inputTitle.type = "text";
    inputTitle.defaultValue = json.taskTitle;
   /* let lblTitle = document.createElement('label');
    lblTitle.htmlFor = "tTitle";
    lblTitle.innerHTML = "Title:";


    let inputText = document.createElement('input');*/
    let inputText =document.getElementById("tDescription");
    //inputText.id = "tDescription";
    //inputTitle.name = "tDescription";
    //inputText.type = "text";
    inputText.defaultValue = json.tdescription;
    /*let lblText = document.createElement('label');
    lblText.htmlFor = "tDescription"
    lblText.innerHTML = "Description:";

    let inputAssignee = document.createElement('input');
    inputAssignee.id = "assignedEmail";
    inputAssignee.name = "assignedEmail";
    inputAssignee.type = "text";*/
    let inputAssignee = document.getElementById("assignedEmail")
    inputAssignee.defaultValue = json.assignedEmail;
    /*let lblAssignee = document.createElement('label');
    lblAssignee.htmlFor = "assignedEmail";
    lblAssignee.innerHTML = "Assignee: ";

    taskForm.appendChild(lblTitle);
    taskForm.appendChild(inputTitle);
    taskForm.appendChild(lblAssignee);
    taskForm.appendChild(inputAssignee);
    taskForm.appendChild(lblText);
    taskForm.appendChild(inputText);


    let selectStatus = document.createElement('select');
    selectStatus.id = "status";
    selectStatus.name = "status";
    let lblStatus = document.createElement('label');
    lblStatus.htmlFor = "status"
    lblStatus.innerHTML = "Status:";
    let optHold = document.createElement('option');
    optHold.innerHTML = "ON_HOLD";
    optHold.value = "ON_HOLD";
    let optBacklog = document.createElement('option');
    optBacklog.innerHTML = "BACKLOG";
    optBacklog.value = "BACKLOG";
    let optSprintBack = document.createElement('option');
    optSprintBack.innerHTML = "SPRINT_BACKLOG";
    optSprintBack.value = "SPRINT_BACKLOG";
    let optProgress = document.createElement('option');
    optProgress.innerHTML = "IN_PROGRESS";
    optProgress.value = "IN_PROGRESS";
    let optReview = document.createElement('option');
    optReview.innerHTML = "UNDER_REVIEW";
    optReview.value = "UNDER_REVIEW";
    let optDone = document.createElement('option');
    optDone.innerHTML = "DONE";
    optDone.value = "DONE";

    selectStatus.appendChild(optHold);
    selectStatus.appendChild(optBacklog);
    selectStatus.appendChild(optSprintBack);
    selectStatus.appendChild(optProgress);
    selectStatus.appendChild(optReview);
    selectStatus.appendChild(optDone);

    let selectedStatus;
    switch (json.status) {
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



    let selectPrio = document.createElement('select');
    selectPrio.id = "tPriority";
    selectPrio.name = "tPriority";
    let lblPrio = document.createElement('label');
    lblPrio.htmlFor = "tPriority";
    lblPrio.innerHTML = "Priority";
    let optNormal = document.createElement('option');
    optNormal.value = "NORMAL";
    optNormal.innerHTML = "NORMAL";
    let optHigh = document.createElement('option');
    optHigh.value = "HIGH";
    optHigh.innerHTML = "HIGH";
    let optLow = document.createElement('option');
    optLow.value = "LOW";
    optLow.innerHTML = "LOW";

    selectPrio.appendChild(lblPrio);
    selectPrio.appendChild(optNormal);
    selectPrio.appendChild(optHigh);
    selectPrio.appendChild(optLow);

    let selectedPriority;
    switch (json.tPriority) {
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


    let pAuthor = document.createElement('p');
    pAuthor.id = "authorEmail";
    pAuthor.name = "authorEmail"*/
    let pAuthor =document.getElementById("pAuthor");
    pAuthor.innerHTML += json.authorEmail;

    /*let pId = document.createElement('p');
    pId.id = "pId";*/
    let hId = document.getElementById("hTaskId");
    hId.innerHTML = json.taskId;
    //let pCreateDate = document.createElement('p');
    let pCreateDate = document.getElementById("pCreated");
    pCreateDate.innerHTML += json.creationDate;
    //let pEditDate = document.createElement('p');
    let pEditDate = document.getElementById("pEdited");
    pEditDate.innerHTML += json.editDate;


    /*let btnSave = document.createElement('button');
    btnSave.id = "btnSave";
    btnSave.name = "btnSave";
    btnSave.innerHTML = "Save Changes";*/
    let btnSave = document.getElementById("btnSaveTask");
    btnSave.addEventListener('click',()=>saveEdits());


    /*div.appendChild(pId);
    div.appendChild(selectStatus);
    div.appendChild(br);
    div.appendChild(selectPrio);
    div.appendChild(taskForm);
    div.appendChild(pAuthor);
    div.appendChild(pCreateDate);
    div.appendChild(pEditDate);
    div.appendChild(btnSave);*/

    //document.getElementById("taskDetails").innerHTML ="";
    //document.getElementById("taskDetails").appendChild(div);
    await drawComments(taskId);


    setDropDowns(json.tpriority, json.status);

}

async function drawComments(taskId) {
    const urlCommentDoc = "http://localhost:8080/commentDoc";


    /*let resComDoc;
    try {
        const responsePromise = await fetch(urlCommentDoc)
        if (!responsePromise.ok) {
            throw new Error("Error! status: ${response.status}");
        }
        resComDoc = await responsePromise.text();
        console.log(resComDoc);
    } catch (err) {
        console.log(err);
    }



    document.getElementById("commentsSpace").innerHTML = resComDoc;
*/
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
    let priorityValue = document.getElementById("tPriority").value;
    let statusValue = document.getElementById("status").value;
    let temp = document.getElementById("pId").innerHTML;
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

    let title = document.getElementById("taskTitle").value;
    let description = document.getElementById("taskText").value;
    let assignee =document.getElementById("taskAssignee").value;
    let prio = document.getElementById("taskPriority").value;

    let obj = {
        taskTitle: title,
        tdescription: description,
        assignedEmail: assignee,
        tpriority: prio,
    }

    let createPayload = JSON.stringify(obj);

    const url1 = 'http://localhost:8080/task/create';

    let res1;

    try {
        const responsePromise = await fetch(url1,{
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
        res1 = await responsePromise.json();
        console.log(res1);
    } catch (err) {
        console.log(err);
    }


}


