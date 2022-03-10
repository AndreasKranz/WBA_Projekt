async function createTaskTable() {
    const url = 'http://localhost:8080/tasks';

    let json;

    try {
        const responsePromise = await fetch(url)
        if(!responsePromise.ok){
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

async function drawTask(taskId){
    const url = "http://localhost:8080/task/" + taskId+"/";

    let json;

    try {
        const responsePromise = await fetch(url);
        if(!responsePromise.ok){
            throw new Error("Error! status: ${response.status}");
        }
        json = await responsePromise.json();
    } catch (err) {
        console.error(err);
    }

    let div = document.createElement('div');
    let pTitle = document.createElement('p');
    pTitle.innerHTML = json.taskTitle;
    let pText = document.createElement('p');
    pText.innerHTML = json.description;
    let pAuthor = document.createElement('p');
    pAuthor.innerHTML = "Author: "+json.authorEmail;
    let pId = document.createElement('p');
    pId.innerHTML = json.taskId;
    let pStatus = document.createElement('p');
    pStatus.innerHTML = json.status;
    let pPrio = document.createElement('p');
    pPrio.innerHTML = "Priority: "+json.priority;
    let pAssignee = document.createElement('p');
    pAssignee.innerHTML = "Assignee: "+json.assignedEmail;
    let pCreateDate = document.createElement('p');
    pCreateDate.innerHTML = "created: "+json.creationDate;
    let pEditDate = document.createElement('p');
    pEditDate.innerHTML = "edited: "+json.editDate;

    div.appendChild(pId);
    div.appendChild(pTitle);
    div.appendChild(pAssignee);
    div.appendChild(pAuthor);
    div.appendChild(pText);
    div.appendChild(pStatus);
    div.appendChild(pPrio);
    div.appendChild(pCreateDate);
    div.appendChild(pEditDate);

    document.getElementById("taskSpace").appendChild(div);
}

async function drawComments(taskId){

}
