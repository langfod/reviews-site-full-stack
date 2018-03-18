/**
 * 
 */

// Globals
var tagElemArray = new Array();
var tagElemArrayIndex = 0;
const relUrl = window.location.protocol + "//" + window.location.host;
//
document.querySelector("button#tag_add").addEventListener('click', (event) => {
	document.querySelector("#tag-add-popup").classList.toggle("show-modal")
});

document.querySelector("button#tag_delete").addEventListener('click', (event) => {
	document.querySelector("#tag-delete-popup").classList.toggle("show-modal")
	document.querySelectorAll('[data-tagid]').forEach(elem => elem.addEventListener('click', (event) => {
		event.preventDefault();
		removeTagStage1(event.target.parentNode)
	}))
	document.querySelector("button#tag-delete-apply").addEventListener('click', () => {
		applyTagDeletion()
		location.reload() // XXX Cheat for now
	})
	document.querySelector("#tag-delete-cancel").addEventListener('click', () => location.reload())
});

const applyTagDeletion = () => {
	responses = Array()
	tagElemArray.forEach(tagElem => {
		const tagId = tagElem.getAttribute('data-tagid')
		const reviewId = tagElem.getAttribute('data-reviewid')
		const url = relUrl + "/api/review/" + reviewId + "/" + "tag" + "/" + tagId
		console.log(url)
		responses.push(deleteFetch(url))
	})
	//TODO XXX Handle error responses. For now just send to console 
	Promise.all(responses.map(p => p.catch(err => err)))
		.then(results => console.log(results))
		.catch(err => console.log(err));
}

const removeTagStage1 = (target) => {

	// add tag to list of removed
	tagElemArray.push(target)
	// hide tag item 
	target.remove();
	// activate apply button (once)
	// XXX 
}

const deleteFetch = (url) => {
	return fetch(url, { method: 'DELETE' })
	//XXX TODO Deal with reponse codes
};

const  putFetch = async (url, data) => {
	return await fetch(url, {
	   method: 'PUT',
	   body: JSON.stringify(data), 
	   headers: new Headers({'Content-Type': 'application/json'})
	 }).then(response => response)
   //XXX TODO Deal with reponse codes
};
// *** Add Tag Modal Stuff
const allowDrop = event => event.preventDefault()
const dragTag = event => event.dataTransfer.setData("text", event.target.innerText)
const dropTag = event => {
	event.preventDefault()
	appendTextToElementValue(event.target, event.dataTransfer.getData("text")) 
	document.querySelector("#tag-add-apply").disabled = !(event.target.value.trim())
}
const appendTextToElementValue = (target, text) => {
	if (target.value) target.value += ", "
	target.value += text
}

document.querySelector("button#tag-add-custom-enter").addEventListener('click', (event) => {
	const inputElem = document.querySelector("#tag-add-custom").value
	const outputElem = document.querySelector("#tag-add-textbox")
	inputElem.replace(/[\u2000-\u206F\u2E00-\u2E7F\\'!"#$%&()*+,\-.\/:;<=>?@\[\]^_`{|}~]/, '').replace(/\s+/g, '')
	appendTextToElementValue(outputElem,inputElem)
	inputElem.value=""
	document.querySelector("#tag-add-custom-enter").disabled = true
	document.querySelector("#tag-add-apply").disabled = true
})



document.querySelector("button#tag-add-apply").addEventListener('click', (event) => {
	const reviewId = event.target.getAttribute('data-reviewid')
	const newTags = document.querySelector("#tag-add-textbox").value.split(",").map(e => e.trim())
	const url = relUrl + "/api/review/" + reviewId + "/" + "tag" 
	putFetch(url, newTags ) 	//XXX TODO Deal with reponse codes
	location.reload() // XXX cheat for now
})
document.querySelector("input#tag-add-custom").addEventListener('keyup', (event) => {
	document.querySelector("button#tag-add-custom-enter").disabled = !(event.target.value.trim())
})
document.querySelector("button#tag-add-cancel").addEventListener('click', () => location.reload())

/*
* Setup Initial State
*/
document.querySelector("#tag-edit-buttons").classList.toggle("not-visible")
document.querySelectorAll("input").forEach((elem) => elem.value="")
document.querySelector("#tag-add-apply").disabled = true
document.querySelector("#tag-add-custom-enter").disabled = true

