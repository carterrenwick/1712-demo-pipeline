/**
 * 
 */


window.onload = function(){
	loadNavbar();
	loadDashboardView();	//CHANGE NAME
}

function loadNavbar(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
				document.getElementById('navbar').innerHTML = xhr.responseText;
				if(document.getElementById('rr')){	//Employee
					document.getElementById('rr').addEventListener('click', loadRrView, false);
					document.getElementById('vPRR').addEventListener('click', loadPrrView, false);
					document.getElementById('vRRR').addEventListener('click', loadRrrView, false);
				}else{								//Manager
					document.getElementById('vAE').addEventListener('click', loadVaeView, false);
					document.getElementById('vAPRR').addEventListener('click', loadAllPrrView, false);
					document.getElementById('vARRR').addEventListener('click', loadAllRrrView, false);
				}
				
				document.getElementById('dashboard').addEventListener('click', loadDashboardView, false);		
		}
	}
	xhr.open("GET", "ajaxNavbar?r=" + new Date().getTime(), true);	//avoids caching
	xhr.send();
}

function loadDashboardView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			document.getElementById('view').innerHTML = xhr.responseText;
			document.getElementById('updateBtn').addEventListener('click', loadUpdateInfoView , false);
			retrieveUserInfo();
		}
	}
	xhr.open("GET", "ajaxDashboard", true);
	xhr.send();
}

function loadUpdateInfoView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			document.getElementById('view').innerHTML = xhr.responseText;
			document.getElementById('infoUpdateBtn').addEventListener('click', processUpdateInfo, false)
		}
	}
	xhr.open("GET", "ajaxUpdateInfo", true);
	xhr.send();
}

function loadRrView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			document.getElementById('view').innerHTML = xhr.responseText;
			document.getElementById('rrBtn').addEventListener('click', processRr , false);
		}
	}
	xhr.open("GET", "ajaxRequestReimbursement", true);
	xhr.send();
}

function loadPrrView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			document.getElementById('view').innerHTML = xhr.responseText;
			retrievePrrInfo();
		}
	}
	xhr.open("GET", "ajaxViewReimbursement", true);
	xhr.send();
}

function loadAllPrrView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			document.getElementById('view').innerHTML = xhr.responseText;
			retrieveAllPrrInfo();
		}
	}
	xhr.open("GET", "ajaxManagerViewReimbursement", true);
	xhr.send();
}

function loadRrrView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			document.getElementById('view').innerHTML = xhr.responseText;
			retrieveRrrInfo();
		}
	}
	xhr.open("GET", "ajaxViewReimbursement", true);
	xhr.send();
}

function loadAllRrrView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			document.getElementById('view').innerHTML = xhr.responseText;
			retrieveAllRrrInfo();
		}
	}
	xhr.open("GET", "ajaxManagerViewReimbursement", true);
	xhr.send();
}

function loadVaeView(){
	console.log('creating xhr object');
	var xhr = new XMLHttpRequest();
	console.log('created xhr object!');
	xhr.onreadystatechange = function() {
		console.log('xhr readystate changed! '+ xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log('ready state = 4 and status = 200');
			document.getElementById('view').innerHTML = xhr.responseText;
			retrieveVaeInfo();
		}
	}
	console.log('before open');
	xhr.open("GET", "ajaxViewEmployees", true);
	console.log('after open and before send');
	xhr.send();
	console.log('after send');
}

function retrieveUserInfo(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			var user = JSON.parse(xhr.responseText);
			document.getElementById('ErsName').innerText = user.firstname;
			document.getElementById('ErsUserId').innerText = user.id;
			document.getElementById('ErsUsername').innerText = user.username;
			document.getElementById('ErsFname').innerText = user.firstname;
			document.getElementById('ErsLname').innerText = user.lastname;
			document.getElementById('ErsEmail').innerText = user.email;
			if(user.role == 1)
				document.getElementById('ErsRole').innerText = 'Employee';
			else
				document.getElementById('ErsRole').innerText = 'Manager';
		}
	}
	xhr.open("GET", "ajaxUserInfo", true);
	xhr.send();
}

function retrievePrrInfo(){
	document.getElementById('rStatus').innerText = 'Pending';
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			var requests = JSON.parse(xhr.responseText);			
			for(var i=0;i<requests.length;i++){
				var trElement = document.createElement('tr');
				
				var tdId = document.createElement('td');
				var tdAmount = document.createElement('td');
				var tdDesc = document.createElement('td');
				var tdType = document.createElement('td');
				
				tdId.innerText = requests[i].id;
				tdAmount.innerText = '$'+requests[i].amount;
				tdDesc.innerText = requests[i].description;
				
				switch(requests[i].type) {
				case 1:
					tdType.innerText = 'Business Expense';
					break;
				case 2:
					tdType.innerText = 'Travel';
					break;
				case 3:
					tdType.innerText = 'Medical';
					break;
				default:
					tdType.innerText = 'Unknown';
					break;
				}
				
				trElement.appendChild(tdId);
				trElement.appendChild(tdAmount);
				trElement.appendChild(tdDesc);
				trElement.appendChild(tdType);
				
				document.getElementById('rReqs').appendChild(trElement);
			}	
		}
	}
	xhr.open("GET", "ajaxPendingReimbursement", true);
	xhr.send();
}

function retrieveAllPrrInfo(){
	document.getElementById('rStatus').innerText = 'Pending';
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			var requests = JSON.parse(xhr.responseText);			
			for(var i=0;i<requests.length;i++){
				var trElement = document.createElement('tr');
				
				var tdId = document.createElement('td');
				var tdEmpId = document.createElement('td');
				var tdAmount = document.createElement('td');
				var tdDesc = document.createElement('td');
				var tdType = document.createElement('td');
				
				tdId.innerText = requests[i].id;
				tdEmpId.innerText = requests[i].authorId;
				tdAmount.innerText = '$'+requests[i].amount;
				tdDesc.innerText = requests[i].description;
				
				switch(requests[i].type) {
				case 1:
					tdType.innerText = 'Business Expense';
					break;
				case 2:
					tdType.innerText = 'Travel';
					break;
				case 3:
					tdType.innerText = 'Medical';
					break;
				default:
					tdType.innerText = 'Unknown';
					break;
				}
				
				trElement.appendChild(tdId);
				trElement.appendChild(tdEmpId)
				trElement.appendChild(tdAmount);
				trElement.appendChild(tdDesc);
				trElement.appendChild(tdType);
				
				document.getElementById('rReqs').appendChild(trElement);
			}	
		}
	}
	xhr.open("GET", "ajaxManagerGetPending", true);
	xhr.send();
}

function retrieveRrrInfo(){
	document.getElementById('rStatus').innerHTML = 'Resolved';
	
	var thStatus = document.createElement('th');
	thStatus.innerHTML = 'Status';
	
	document.getElementById('meta').appendChild(thStatus);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			var requests = JSON.parse(xhr.responseText);
			
			for(var i=0;i<requests.length;i++){
				var trElement = document.createElement('tr');
				
				var tdId = document.createElement('td');
				var tdAmount = document.createElement('td');
				var tdDesc = document.createElement('td');
				var tdType = document.createElement('td');
				var tdStatus = document.createElement('td');
				
				tdId.innerText = requests[i].id;
				tdAmount.innerText = '$'+requests[i].amount;
				tdDesc.innerText = requests[i].description;
				
				switch(requests[i].type) {
				case 1:
					tdType.innerText = 'Business Expense';
					break;
				case 2:
					tdType.innerText = 'Travel';
					break;
				case 3:
					tdType.innerText = 'Medical';
					break;
				default:
					tdType.innerText = 'Unknown';
					break;
				}
				
				switch(requests[i].status) {
				case -1:
					tdStatus.innerText = 'DENIED';
					break;
				case 1:
					tdStatus.innerText = 'APPROVED';
					break;
				default:
					tdStatus.innerHTML = 'Unknown';
					break;
				}
				
				trElement.appendChild(tdId);
				trElement.appendChild(tdAmount);
				trElement.appendChild(tdDesc);
				trElement.appendChild(tdType);
				trElement.appendChild(tdStatus);
				
				document.getElementById('rReqs').appendChild(trElement);
			}	
		}
	}
	xhr.open("GET", "ajaxResolvedReimbursement", true);
	xhr.send();
}

function retrieveAllRrrInfo(){
	document.getElementById('rStatus').innerHTML = 'Resolved';
	
	var thResolver = document.createElement('th');
	thResolver.innerText = 'Resolver ID';
	
	document.getElementById('meta').appendChild(thResolver);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			var requests = JSON.parse(xhr.responseText);			
			for(var i=0;i<requests.length;i++){
				var trElement = document.createElement('tr');
				
				var tdId = document.createElement('td');
				var tdEmpId = document.createElement('td');
				var tdAmount = document.createElement('td');
				var tdDesc = document.createElement('td');
				var tdType = document.createElement('td');
				var tdResolver = document.createElement('td');
				
				tdId.innerText = requests[i].id;
				tdEmpId.innerText = requests[i].authorId;
				tdAmount.innerText = '$'+requests[i].amount;
				tdDesc.innerText = requests[i].description;
				tdResolver.innerText = requests[i].resolverId;
				
				switch(requests[i].type) {
				case 1:
					tdType.innerText = 'Business Expense';
					break;
				case 2:
					tdType.innerText = 'Travel';
					break;
				case 3:
					tdType.innerText = 'Medical';
					break;
				default:
					tdType.innerText = 'Unknown';
					break;
				}
				
				trElement.appendChild(tdId);
				trElement.appendChild(tdEmpId)
				trElement.appendChild(tdAmount);
				trElement.appendChild(tdDesc);
				trElement.appendChild(tdType);
				trElement.appendChild(tdResolver);
				
				document.getElementById('rReqs').appendChild(trElement);
			}	
		}
	}
	xhr.open("GET", "ajaxManagerGetResolved", true);
	xhr.send();
}

function retrieveVaeInfo(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			var emps = JSON.parse(xhr.responseText);
			
			for(var i=0;i<emps.length;i++){
				var trElement = document.createElement('tr');
				
				var tdId = document.createElement('td');
				var tdUname = document.createElement('td');
				var tdFname = document.createElement('td');
				var tdLname = document.createElement('td');
				var tdEmail = document.createElement('td');
				
				tdId.innerText = emps[i].id;
				tdUname.innerText = emps[i].username;
				tdFname.innerText = emps[i].firstname;
				tdLname.innerText = emps[i].lastname;
				tdEmail.innerText = emps[i].email;
				
				trElement.appendChild(tdId);
				trElement.appendChild(tdUname);
				trElement.appendChild(tdFname);
				trElement.appendChild(tdLname);
				trElement.appendChild(tdEmail);
				
				document.getElementById('aEmps').appendChild(trElement);
			}	
		}
	}
	xhr.open("GET", "ajaxAllEmployeeInfo", true);
	xhr.send();
}

function processUpdateInfo(){
//	console.log(document.getElementById('uName').validity.valid);
//	document.getElementById('fName').validity.valid;
//	document.getElementById('lName').validity.valid;
//	document.getElementById('email').validity.valid;
	
	var uName = document.getElementById('uName').value;
	var pWord = document.getElementById('pWord').value;
	var uFname = document.getElementById('fName').value;
	var uLname = document.getElementById('lName').value;
	var uEmail = document.getElementById('email').value;
	
	var uu = {
			username: uName,
			password: pWord,
			firstname: uFname,
			lastname: uLname,
			email: uEmail
	}
	uu = JSON.stringify(uu);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log('outcome: '+ xhr.responseText);
			if(xhr.responseText == "success")
				window.alert("User information successfully updated!");
			else
				window.alert("Failed to update user information!");
		
		//Clear input fields
		document.getElementById('uName').value = "";
		document.getElementById('pWord').value = "";
		document.getElementById('fName').value = "";
		document.getElementById('lName').value = "";
		document.getElementById('email').value = "";
		}
	}
	xhr.open("POST", "ajaxUpdateInfo", true);
	xhr.setRequestHeader("key",uu); //not required
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");	//Must set the content-type
	xhr.send(uu);
}

function processRr(){
	var rrAmount = document.getElementById('rrAmount').value;
	var rrType = document.querySelector('input[name = "rrType"]:checked').value; //Radio button
	var rrDesc = document.getElementById('rrDesc').value;
	console.log('amount: ' + rrAmount + ' type: ' + rrType + 'description: ' + rrDesc);
	
	var rr = {
			amount: rrAmount,
			type: rrType,
			description: rrDesc
	}
	rr = JSON.stringify(rr);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log(xhr.responseText);
			
		}
	}
	xhr.open("POST", "ajaxProcessReimbursement", true);
	xhr.setRequestHeader("key",rr); //not required
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");	//Must set the content-type
	xhr.send(rr);
	
	//Clear input fields
	document.getElementById('rrAmount').value = "";
	document.getElementById('rrDesc').value = "";
	var radio = document.getElementsByName("rrType");
	   for(var i=0;i<radio.length;i++)
	      radio[i].checked = false;
	
	
	//Display success message
	window.alert("Your request has been submitted!");
}
