/**
 * 
 */

// Globals
var tagElemArray = new Array();
var tagElemArrayIndex = 0;
var relUrl = window.location.protocol + "//" + window.location.host;
//
document.querySelector("#tag_delete").addEventListener('click', (event) => {

	document.querySelector("#tag-delete-popup").classList.toggle("show-modal")

	document.querySelectorAll('[data-tagid]').forEach(elem => elem.addEventListener('click', (event) => {
		event.preventDefault();
		removeTagStage1(event.target.parentNode)
	}))

	document.querySelector("#tag-delete-apply").addEventListener('click', () => {
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
		responses.push(deleteTagFromReview(reviewId, tagId))
	})
	console.log(responses)
	//TODO XXX Handle error responses. For just send to console 
	Promise.all(responses.map(p => p.catch(err => err)))
		.then(results => console.log(results))
		.catch(err => console.log(err));

}
// TODO XXX document.querySelector("#tag_add").addEventListener('click', (event) => {});

const removeTagStage1 = (target) => {

	// add tag to list of removed
	tagElemArray.push(target)
	// hide tag item 
	target.remove();
	// activate apply button (once)
	// XXX 
}

function reflect(promise) {
	return promise.then(function (v) { return { v: v, status: "resolved" } },
		function (e) { return { e: e, status: "rejected" } });
}

const deleteTagFromReview = (reviewId, tagId) => {
	const url = relUrl + "/api/review/" + reviewId + "/tag/" + tagId
	return fetch(url, { method: 'DELETE' })
	//XXX TODO Deal with reponse codes
};




document.querySelector("#tag-edit-buttons").classList.toggle("not-visible")
