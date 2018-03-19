/**
 * 
 */

// Globals
const tagElemArray = new Array();
const relUrl = window.location.protocol + "//" + window.location.host;
const reviewId = document.querySelector("article").getAttribute('data-reviewid')

//


const showTagEditButtons = () => document.querySelector("#tag-edit-buttons").classList.remove("not-visible")
const hideTagEditButtons = () => document.querySelector("#tag-edit-buttons").classList.add("not-visible")

const showModal = (element) => document.querySelector(element).classList.add("show-modal")
const hideModal = (element) => document.querySelector(element).classList.remove("show-modal")
const toggleHidden = (elem) => elem.classList.toggle("hidden")

const getFetch = (url) => fetch(url).then(res => res.text())
const deleteFetch = (url) => fetch(url, { method: 'DELETE' })
const putFetch = (url, data) => {
	return fetch(url, {
		method: 'PUT', body: JSON.stringify(data),
		headers: new Headers({ 'Content-Type': 'application/json' })
	})
}
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
const applyTagDeletion = () => {
	responses = Array()
	tagElemArray.forEach(tagElem => {
		const tagId = tagElem.getAttribute('data-tagid')
		const url = relUrl + "/api/review/" + reviewId + "/" + "tag" + "/" + tagId
		responses.push(deleteFetch(url))
	})
	//TODO XXX Handle error responses. For now just send to console 
	Promise.all(responses.map(p => p.catch(err => err)))
		.then(results => console.log(results.status))
		.catch(err => console.log(err));
}
const replaceElemWithFragment = (url, element) => {
	fetch(url).then(response => response.text()).then(respText => {
		const htmlTemplate = document.createElement('template');
		htmlTemplate.innerHTML = respText
		element.replaceWith(htmlTemplate.content.firstChild)
	})
}
const refreshFooters = (reviewId) => {
	return Promise.all([replaceElemWithFragment(relUrl + "/review/" + reviewId + "/footer", document.querySelector("article>footer")),
	replaceElemWithFragment(relUrl + "/reviews/footer", document.querySelector("main>footer"))]).catch((err) => console.log(err))
}
const enableElement = (elem) => document.querySelector(elem).disabled = false
const disableElement = (elem) => document.querySelector(elem).disabled = true
const removeNonAlpha = (inputStr) => inputStr.replace(/[\u2000-\u206F\u2E00-\u2E7F\\'!"#$%&()*+,\-.\/:;<=>?@\[\]^_`{|}~]/, '').replace(/\s+/g, '')

const flipCaret = (elem) => {
	const caretDown = "fa-caret-down";
	const caretUp = "fa-caret-up";
	if (elem.className.includes(caretDown)) {
		elem.classList.replace(caretDown, caretUp)
	} else {
		elem.classList.replace(caretUp, caretDown)
	}
}


const startTagAddListeners = () => {
	document.querySelector("button#tag_add").addEventListener('click', (event) => {
		showModal("#tag-add-popup")
		hideTagEditButtons()
		document.querySelector("button#tag-add-custom-enter").addEventListener('click', (event) => {
			const userInputText = document.querySelector("#tag-add-custom").value
			const newTagField = document.querySelector("#tag-add-textbox")
			appendTextToElementValue(newTagField, removeNonAlpha(userInputText))
			document.querySelector("#tag-add-custom").value = ""
			disableElement("#tag-add-custom-enter")
			enableElement("#tag-add-apply")
		})

		document.querySelector("button#tag-add-apply").addEventListener('click', (event) => {
			const newTagArray = document.querySelector("#tag-add-textbox").value.split(",").map(e => e.trim())
			const url = relUrl + "/api/review/" + reviewId + "/" + "tag"
			putFetch(url, newTagArray).then(() => {
				//XXX TODO Deal with reponse codes
				refreshFooters(reviewId).then(() => initialStatey())
			})
			showTagEditButtons()
			hideModal("#tag-add-popup")
			location.reload()  //XXX Wrong Wrong Wrong but gets around subtle async bug.
		})

		document.querySelector("input#tag-add-custom").addEventListener('keyup', (event) => {
			(event.target.value.trim()) ? enableElement("button#tag-add-custom-enter") : disableElement("button#tag-add-custom-enter")
		})
		document.querySelector("button#tag-add-cancel").addEventListener('click', () => location.reload())
	});
}
const startTagDeleteListeners = () => {
	document.querySelector("button#tag_delete").addEventListener('click', (event) => {
		hideTagEditButtons()
		showModal("#tag-delete-popup")
		document.querySelectorAll('[data-tagid]').forEach(elem => elem.addEventListener('click', (event) => {
			event.preventDefault()
			tagElemArray.push(event.target.parentNode)
			event.target.parentNode.remove()
			document.querySelector("#tag-delete-apply").disabled = false
		}))
		document.querySelector("button#tag-delete-apply").addEventListener('click', (event) => {
			applyTagDeletion()
			document.querySelector("#tag-delete-apply").disabled = false
			hideModal("#tag-delete-popup")
			showTagEditButtons()
		})
		document.querySelector("#tag-delete-cancel").addEventListener('click', () => location.reload())
	})
}
//

const startCommentAreaListeners = () => {
	document.querySelector("#comment-switch").addEventListener('click', (event) => {
		document.querySelectorAll("#review-comments>article").forEach(article => toggleHidden(article))
		flipCaret(event.target)
	})
	document.querySelector("button#add-comment").addEventListener('click', (event) => showModal("#new-comment-form"))
	document.querySelector("input#new-comment-form-cancel").addEventListener('click', (event) => hideModal("#new-comment-form"))
}

//
/*
* Setup Initial State
*/
const initialStatey = () => {
	document.querySelectorAll("input[type='text']").forEach((elem) => elem.value = "")
	disableElement("#tag-add-apply")
	disableElement("#tag-delete-apply")
	disableElement("#tag-add-custom-enter")
	tagElemArray.length = 0
	showTagEditButtons()
	startTagAddListeners()
	startTagDeleteListeners()
	startCommentAreaListeners()
}
//
//

initialStatey()

