/**
 * 
 */


window.onload = function(){
	loadNavbar();
	loadDashboardView();
}

function loadNavbar(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
				document.getElementById('navbar').innerHTML = xhr.responseText;
				if(document.getElementById('rr')){	//Employee
					document.getElementById('rr').addEventListener('click', loadRrView, false);
					document.getElementById('vPRR').addEventListener('click', loadPrrView, false);
					document.getElementById('vRRR').addEventListener('click', loadRrrView, false);
				}else{								//Manager
					document.getElementById('vAE').addEventListener('click', loadVaeView, false);
					document.getElementById('vAPRR').addEventListener('click', loadAllPrrView, false);
					document.getElementById('vARRR').addEventListener('click', loadAllRrrView, false);
					document.getElementById('register').addEventListener('click', loadRegisterEmpView, false);
				}
				
				document.getElementById('dashboard').addEventListener('click', loadDashboardView, false);		
		}
	}
	xhr.open("GET", "ajaxNavbar?r=" + new Date().getTime(), true);	//avoids caching the Navbar
	xhr.send();
}

function loadDashboardView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
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
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('view').innerHTML = xhr.responseText;
			document.getElementById('infoUpdateBtn').addEventListener('click', processUpdateInfo, false)
		}
	}
	xhr.open("GET", "ajaxUpdateInfo", true);
	xhr.send();
}

function loadRegisterEmpView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('view').innerHTML = xhr.responseText;
			document.getElementById('registerBtn').addEventListener('click', processRegisterEmp, false)
		}
	}
	xhr.open("GET", "ajaxRegisterEmp", true);
	xhr.send();
}

function loadRrView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
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
		if(xhr.readyState == 4 && xhr.status == 200){
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
		if(xhr.readyState == 4 && xhr.status == 200){
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
		if(xhr.readyState == 4 && xhr.status == 200){
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
		if(xhr.readyState == 4 && xhr.status == 200){
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


//lol
function loadArseView(evt) {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('view').innerHTML = xhr.responseText;
			retrieveArseInfo(evt.target.id)
		}
	}
	xhr.open("GET", "ajaxViewAllReqsSingleEmp", true);
	xhr.send();
}

function approveReq(event){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			window.alert('Approved request with ID: '+event.target.id+'!')
			document.getElementById('view').innerHTML = xhr.responseText;
			retrieveAllPrrInfo();
		}
	}
	xhr.open("GET", "ajaxApproveOrDenyReq?reqId="+event.target.id+"&choice=approve", true);
	xhr.send();
}

function denyReq(event){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			window.alert('Denied request with ID: '+event.target.id+'!')
			document.getElementById('view').innerHTML = xhr.responseText;
			retrieveAllPrrInfo();
		}
	}
	xhr.open("GET", "ajaxApproveOrDenyReq?reqId="+event.target.id+"&choice=deny", true);
	xhr.send();
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

function retrieveArseInfo(id) {
	console.log('EmployeeID: '+id);
	
	var thStatus = document.createElement('th');
	thStatus.innerHTML = 'Status';
	
	document.getElementById('meta').appendChild(thStatus);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			var requests = JSON.parse(xhr.responseText);			
			for(var i=0;i<requests.length;i++){
				var trElement = document.createElement('tr');
				trElement.setAttribute("class", "table-light");
				
				var tdId = document.createElement('td');
				var tdEmpId = document.createElement('td');
				var tdAmount = document.createElement('td');
				var tdDesc = document.createElement('td');
				var tdType = document.createElement('td');
				var tdStatus = document.createElement('td');
				
				tdId.innerText = requests[i].id;
				tdEmpId.innerText = id;
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
				case 0:
					tdStatus.innerText = 'PENDING';
					break;
				case 1:
					tdStatus.innerText = 'APPROVED';
					break;
				default:
					tdStatus.innerHTML = 'Unknown';
					break;
				}
				
				trElement.appendChild(tdId);
				trElement.appendChild(tdEmpId);
				trElement.appendChild(tdAmount);
				trElement.appendChild(tdDesc);
				trElement.appendChild(tdType);
				trElement.appendChild(tdStatus)
				
				document.getElementById('rReqs').appendChild(trElement);
			}	
		}
	}
	xhr.open("GET", "ajaxAllReqsSingleEmpInfo?empId="+id, true);
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
				trElement.setAttribute("class", "table-light");
				
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
	
	var thResolve = document.createElement('th');
	thResolve.innerText = 'Resolve Action';
	
	var thReceipt = document.createElement('th');
	thReceipt.innerText = 'Receipt';
	
	document.getElementById('meta').appendChild(thReceipt);
	document.getElementById('meta').appendChild(thResolve);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			var requests = JSON.parse(xhr.responseText);			
			for(var i=0;i<requests.length;i++){
				var trElement = document.createElement('tr');
				trElement.setAttribute("class", "table-light");
				
				var tdId = document.createElement('td');
				var tdEmpId = document.createElement('td');
				var tdAmount = document.createElement('td');
				var tdDesc = document.createElement('td');
				var tdType = document.createElement('td');
				var tdReceipt = document.createElement('td');
				var tdResolve = document.createElement('td');
				
				tdId.innerText = requests[i].id;
				tdEmpId.innerText = requests[i].authorId;
				tdAmount.innerText = '$'+requests[i].amount;
				tdDesc.innerText = requests[i].description;
				
				if(requests[i].receipt){
					tdReceipt.innerHTML = '<img src="data:image/png;base64,' + requests[i].receipt + '" width="160" height="120">';
				}
				
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
				
				var approveId = 'approveReq'+requests[i].id;
				var denyId = 'denyReq'+requests[i].id;

				var approveLink = '<a id="'+approveId+'" style=color:green;cursor:pointer>Approve</a>';
				var denyLink = '<a id="'+denyId+'" style=color:red;cursor:pointer>Deny</a>'
				
				tdResolve.innerHTML = approveLink+' / '+denyLink;
				
				trElement.appendChild(tdId);
				trElement.appendChild(tdEmpId)
				trElement.appendChild(tdAmount);
				trElement.appendChild(tdDesc);
				trElement.appendChild(tdType);
				trElement.appendChild(tdReceipt);
				trElement.appendChild(tdResolve);
				
				document.getElementById('rReqs').appendChild(trElement);
				
				document.getElementById(approveId).addEventListener('click', approveReq ,false);
				document.getElementById(denyId).addEventListener('click', denyReq ,false);
				
				document.getElementById(approveId).id = requests[i].id;
				document.getElementById(denyId).id = requests[i].id;
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
				trElement.setAttribute("class", "table-light");
				
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
	var thStatus = document.createElement('th');
	
	thResolver.innerText = 'Resolver ID';
	thStatus.innerText = 'Status'
	
	document.getElementById('meta').appendChild(thResolver);
	document.getElementById('meta').appendChild(thStatus);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			var requests = JSON.parse(xhr.responseText);			
			for(var i=0;i<requests.length;i++){
				var trElement = document.createElement('tr');
				trElement.setAttribute("class", "table-light");
				
				var tdId = document.createElement('td');
				var tdEmpId = document.createElement('td');
				var tdAmount = document.createElement('td');
				var tdDesc = document.createElement('td');
				var tdType = document.createElement('td');
				var tdResolver = document.createElement('td');
				var tdStatus = document.createElement('td');
				
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
				trElement.appendChild(tdEmpId)
				trElement.appendChild(tdAmount);
				trElement.appendChild(tdDesc);
				trElement.appendChild(tdType);
				trElement.appendChild(tdResolver);
				trElement.appendChild(tdStatus);
				
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
				trElement.setAttribute("class", "table-light");
				
				var tdId = document.createElement('td');
				var tdUname = document.createElement('td');
				var tdFname = document.createElement('td');
				var tdLname = document.createElement('td');
				var tdEmail = document.createElement('td');
				var tdLink = document.createElement('td');
				
				tdId.innerText = emps[i].id;
				tdUname.innerText = emps[i].username;
				tdFname.innerText = emps[i].firstname;
				tdLname.innerText = emps[i].lastname;
				tdEmail.innerText = emps[i].email;
				
				var eleId = 'viewEmpReqs'+emps[i].id;
				
				tdLink.innerHTML = '<a id="'+eleId+'" style=color:red;cursor:pointer>All Requests</a>';
				
				trElement.appendChild(tdId);
				trElement.appendChild(tdUname);
				trElement.appendChild(tdFname);
				trElement.appendChild(tdLname);
				trElement.appendChild(tdEmail);
				trElement.appendChild(tdLink);
				
				document.getElementById('aEmps').appendChild(trElement);
				
				

				document.getElementById(eleId).addEventListener('click', loadArseView, false);
				document.getElementById(eleId).id = emps[i].id;
			}	
		}
	}
	xhr.open("GET", "ajaxAllEmployeeInfo", true);
	xhr.send();
}

function processRegisterEmp(){
	var uName = document.getElementById('uName').value;
	var pWord = document.getElementById('pWord').value;
	var uFname = document.getElementById('fName').value;
	var uLname = document.getElementById('lName').value;
	var uEmail = document.getElementById('email').value;
	
	var re = {
			username: uName,
			password: pWord,
			firstname: uFname,
			lastname: uLname,
			email: uEmail
	}
	re = JSON.stringify(re);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log('outcome: '+ xhr.responseText);
			if(xhr.responseText == "success")
				window.alert("Successfully registered new employee!");
			else
				window.alert("Failed to register new employee!");
		
		//Clear input fields
		document.getElementById('uName').value = "";
		document.getElementById('pWord').value = "";
		document.getElementById('fName').value = "";
		document.getElementById('lName').value = "";
		document.getElementById('email').value = "";
		}
	}
	xhr.open("POST", "ajaxRegisterEmp", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");	//Must set the content-type
	xhr.send('re='+re);
}

function processUpdateInfo(){
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
	var imgFile = document.getElementById('receipt').files[0];
	var rrAmount = document.getElementById('rrAmount').value;
	var rrType = 1;
	if(document.querySelector('input[name = "rrType"]:checked'))
		rrType = document.querySelector('input[name = "rrType"]:checked').value; //Radio button
	var rrDesc = document.getElementById('rrDesc').value;
	
	var rr = {
			amount: rrAmount,
			type: rrType,
			description: rrDesc
	}
	rr = JSON.stringify(rr);
	
	var formData = new FormData();
	if(imgFile)
		formData.append('file', imgFile);
	formData.append('rr', rr);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			
			//Clear input fields
			document.getElementById('rrAmount').value = "";
			document.getElementById('rrDesc').value = "";
			var radio = document.getElementsByName("rrType");
			   for(var i=0;i<radio.length;i++)
			      radio[i].checked = false;
			   
			//Display success message
			window.alert("Your request has been submitted!");
		}
	}
	xhr.open("POST", "ajaxRequestReimbursement", true);
	xhr.send(formData);
}

/*	Used to temporarily view image on request reimbursement page
function previewFile(){
	var imgFile = document.getElementById('receipt').files[0];
	console.log(imgFile);
	var preview = document.querySelector('img');
	var reader = new FileReader();
	
	reader.addEventListener('load', function () {
		    preview.src = reader.result;
		  }, false);

		  if (imgFile) {
		    reader.readAsDataURL(imgFile);
		  }
}
*/
