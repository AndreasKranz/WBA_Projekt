async function createTaskTable() {
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


    let table = document.createElement('table');
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

    let td_onHold = document.createElement('td');
    addToColoumn(td_onHold, arrHold);
    let td_backlog = document.createElement('td');
    addToColoumn(td_backlog, arrBacklog);
    let td_sprintBack = document.createElement('td');
    addToColoumn(td_sprintBack, arrSprintBack);
    let td_progress = document.createElement('td');
    addToColoumn(td_progress, arrProgress);
    let td_review = document.createElement('td');
    addToColoumn(td_review, arrReview);
    let td_done = document.createElement('td');
    addToColoumn(td_done, arrDone);

    row_2.appendChild(td_onHold);
    row_2.appendChild(td_backlog);
    row_2.appendChild(td_sprintBack);
    row_2.appendChild(td_progress);
    row_2.appendChild(td_review);
    row_2.appendChild(td_done);

    tbody.appendChild(row_2);


    document.getElementById('tableSpace').appendChild(table)

    function addToColoumn(td, arr) {
        let ul = document.createElement('ul');

        for (let i = 0; i < arr.length; i++) {
            let assignee = arr[i].assignedEmail;
            let taskId = arr[i].taskId;
            let title = arr[i].taskTitle;

            let button = "<button onclick=\"drawTask(" + taskId + ")\">show Details</button>"

            let li = document.createElement('li');
            li.innerHTML = taskId + button + "<br/>" + title + "<br/>" + assignee;
            ul.appendChild(li);
            console.log(li);

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
                arrHold.push(dto);
                break;
            default:
                console.log("ALLLA IST KEIN ENUM WERT"); //TODO sinnvolle Meldung
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

    let br = document.createElement('br');
    //Edit and Display

    let div = document.createElement('div');
    div.id = "editTask";

    let taskForm = document.createElement('form');
    taskForm.id = "taskForm";

    let inputTitle = document.createElement('input');
    inputTitle.id = "taskTitle";
    inputTitle.name = "taskTitle";
    inputTitle.type = "text";
    inputTitle.defaultValue = json.taskTitle;
    let lblTitle = document.createElement('label');
    lblTitle.htmlFor = "taskTitle";
    lblTitle.innerHTML = "Title:";


    let inputText = document.createElement('input');
    inputText.id = "tDescription";
    inputTitle.name = "tDescription";
    inputText.type = "text";
    inputText.defaultValue = json.tDescription;
    let lblText = document.createElement('label');
    lblText.htmlFor = "tDescription"
    lblText.innerHTML = "Description:";


    let inputAuthor = document.createElement('input');
    inputAuthor.id = "authorEmail";
    inputAuthor.name = "authorEmail"
    inputAuthor.type = "text";
    inputAuthor.defaultValue = json.authorEmail;
    let lblAuthor = document.createElement('label');
    lblAuthor.htmlFor = "authorEmail";
    lblAuthor.innerHTML = "Author:";

    let inputAssignee = document.createElement('input');
    inputAssignee.id = "assignedEmail";
    inputAssignee.name = "assignedEmail";
    inputAssignee.type = "text";
    inputAssignee.defaultValue = json.assignedEmail;
    let lblAssignee = document.createElement('label');
    lblAssignee.htmlFor = "assignedEmail";
    lblAssignee.innerHTML = "Assignee: ";


    taskForm.appendChild(lblTitle);
    taskForm.appendChild(inputTitle);
    taskForm.appendChild(lblAuthor);
    taskForm.appendChild(inputAuthor);
    taskForm.appendChild(lblAssignee);
    taskForm.appendChild(inputAssignee);
    taskForm.appendChild(lblText);
    taskForm.appendChild(inputText);


    //Todo Funktion die Werte mit getelement nimmt



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
    //Todo an richtige Stelle verschieben



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

    //Todo an richtige Stelle verschieben






    let pId = document.createElement('p');
    pId.innerHTML = json.taskId;
    let pCreateDate = document.createElement('p');
    pCreateDate.innerHTML = "created: " + json.creationDate;
    let pEditDate = document.createElement('p');
    pEditDate.innerHTML = "edited: " + json.editDate;


    let btnSave = document.createElement('button');
    btnSave.onclick = saveEdits();
    btnSave.innerHTML = "Save Changes";



    div.appendChild(pId);
    div.appendChild(selectStatus);
    div.appendChild(br);
    div.appendChild(selectPrio);
    div.appendChild(taskForm);
    div.appendChild(pCreateDate);
    div.appendChild(pEditDate);
    div.appendChild(btnSave);


    //document.getElementById('tPriority').selectedIndex = selectedPriority;
    //document.getElementById("status").selectedIndex = selectedStatus;
    document.getElementById("taskSpace").appendChild(div);

    setDropDowns(json.tPriority,json.status);
}

function setDropDowns(prio,status){

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
    document.getElementById("status").selectedIndex = selectedStatus;

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
    document.getElementById('tPriority').selectedIndex = selectedPriority;
}



function saveEdits() {


    /*let status = document.getElementById("status").value;
    let tpriority = document.getElementById("tPriority").value;
    let taskTitle = document.getElementById("title").value;
    let tdescription = document.getElementById("tDescription").value;
    let assignedEmail =document.getElementById("assignedEmail").value;
*/


}

async function drawComments(taskId) {

}
