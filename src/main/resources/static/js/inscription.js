/** Fonction pour vérifier si le formulaire est valide avant de soumettre */
let formInscription = document.querySelector('#formInscription'); // Sélectionne le formulaire d'inscription

// Écouteur d'événement pour le soumission du formulaire
formInscription.addEventListener('submit', function (event) {
    const formData = new FormData(formInscription); // Récupère les données du formulaire
    event.preventDefault(); // Empêche la soumission normale du formulaire

    // Convertit les données du formulaire en JSON
    const formDataJSON = {};
    formData.forEach((value, key) => {
        formDataJSON[key] = value;
    });

    // Envoie une requête fetch au backend pour la validation
    fetch('/api/verification/inscription', {
        method: 'POST', // Méthode HTTP POST
        headers: {
            'Content-Type': 'application/json' // Type de contenu JSON
        }, body: JSON.stringify(formDataJSON) // Corps de la requête contenant les données JSON
    }).then(response => response.json()) // Extrait la réponse JSON de la requête
        .then(data => {
            console.log(data); // Affiche la réponse JSON dans la console
            resetValidationForm("formInscription"); // Réinitialise les validations du formulaire

            if (data.length > 0) { // S'il y a des erreurs de validation
                document.querySelectorAll("form#formInscription input").forEach((unInput) => {
                    validateInput(unInput, data); // Valide chaque champ avec les erreurs
                    if (!unInput.classList.contains("is-invalid")) {
                        unInput.classList.add("is-valid"); // Marque le champ comme valide
                    }
                });
            } else { // Si aucune erreur de validation
                formInscription.submit(); // Soumet le formulaire
            }
        });
});

// Écouteurs d'événements pour réinitialiser la validation lorsque le champ obtient le focus ou une touche est relâchée
formInscription.querySelectorAll("input").forEach((unInput) => {
    unInput.addEventListener("focus", (event) => {
        resetValidationInput(unInput); // Réinitialise la validation du champ
    });

    unInput.addEventListener("keyup", (event) => {
        fetchValidation('/api/verification/inscription').then((erreurs) => {
            resetValidationInput(unInput); // Réinitialise la validation du champ
            validateInput(unInput, erreurs); // Valide le champ avec les erreurs récupérées
        });
    });
});

// Fonction pour réinitialiser toutes les validations du formulaire
function resetValidationForm(idForm) {
    document.querySelectorAll(`form#${idForm} input`).forEach((unInput) => {
        unInput.classList.remove("is-valid"); // Supprime la classe "is-valid" (champ valide)
        unInput.classList.remove("is-invalid"); // Supprime la classe "is-invalid" (champ invalide)
    });

    document.querySelectorAll(`form#${idForm} p`).forEach((unMsg) => {
        unMsg.innerHTML = ""; // Efface le contenu des messages d'erreur
        unMsg.classList.remove("show"); // Supprime la classe "show" (masque les messages)
    });
}

// Fonction pour réinitialiser la validation d'un champ particulier
function resetValidationInput(input) {
    if (input.id.includes("mdp")) { // Si le champ est un mot de passe
        let msg = document.querySelector("#msgformjs"); // Sélectionne le message d'erreur global
        msg.textContent = ""; // Efface le contenu du message
        msg.classList.remove("show"); // Supprime la classe "show" (masque le message)
    }

    input.classList.remove("is-valid"); // Supprime la classe "is-valid" (champ valide)
    input.classList.remove("is-invalid"); // Supprime la classe "is-invalid" (champ invalide)

    let divMessage = document.querySelector(`form #msg${input.id}js`); // Sélectionne le div pour les messages d'erreur du champ
    divMessage.innerHTML = ""; // Efface le contenu du div
    divMessage.classList.remove("show"); // Supprime la classe "show" (masque le div)

    let msg = document.querySelector("#msgformjs"); // Sélectionne le message d'erreur global
    msg.classList.remove("show"); // Supprime la classe "show" (masque le message)
}

// Fonction pour valider un champ et afficher les erreurs
function validateInput(input, listeErreurs) {
    if (listeErreurs.length > 0) { // S'il y a des erreurs de validation
        for (let uneErreur of listeErreurs) { // Parcourt la liste des erreurs
            if (input.id === uneErreur.field) { // Si l'erreur correspond au champ actuel
                input.classList.add("is-invalid"); // Ajoute la classe "is-invalid" (champ invalide)
                input.classList.remove("is-valid"); // Supprime la classe "is-valid" (champ valide)

                let inputMsg = document.querySelector("#msg" + uneErreur.field + "js"); // Sélectionne le message d'erreur du champ
                if (inputMsg) {
                    inputMsg.textContent += uneErreur.defaultMessage; // Ajoute le message d'erreur
                    inputMsg.classList.add("show"); // Ajoute la classe "show" (affiche le message)
                }
            }

            if (uneErreur?.field == null) { // S'il y a une erreur globale du formulaire
                let msg = document.querySelector("#msgformjs"); // Sélectionne le message d'erreur global
                msg.textContent = uneErreur.defaultMessage; // Définit le message d'erreur
                msg.classList.add("show"); // Ajoute la classe "show" (affiche le message)

                // Remarque : Ce bloc de code semble être en commentaire, donc il ne sera pas exécuté
                // let mdp1= document.querySelector("#mdp1NonEncoder")
                // let mdp2= document.querySelector("#mdp2NonEncoder")
                // mdp1.classList.add("is-invalid")
                // mdp2.classList.add("is-invalid")
            }
        }
    }

    if (!input.classList.contains("is-invalid")) { // Si le champ n'a pas d'erreur
        input.classList.add("is-valid"); // Ajoute la classe "is-valid" (champ valide)
        input.classList.remove("is-invalid"); // Supprime la classe "is-invalid" (champ invalide)
    }
}

// Fonction asynchrone pour effectuer une requête Ajax et récupérer les erreurs de validation
async function fetchValidation(urlAPI) {
    const formData = new FormData(formInscription); // Collecte les données du formulaire
    // Convertissez FormData en JSON
    const formDataJSON = {};
    formData.forEach((value, key) => {
        formDataJSON[key] = value;
    });

    // Effectue une requête fetch vers l'URL de l'API de validation
    let reponse = await fetch(urlAPI, {
        method: 'POST', // Méthode HTTP POST
        headers: {
            'Content-Type': 'application/json' // Type de contenu JSON
        }, body: JSON.stringify(formDataJSON) // Données JSON à envoyer
    });

    let data = reponse.json(); // Extrait les données JSON de la réponse
    return data; // Retourne les données JSON
}
// .then(response =>{
//     console.log(response)
//     console.log(reponse.ok)
//     event.preventDefault()
//
//     if(!response.ok) {
//         return response.json()}
// })
