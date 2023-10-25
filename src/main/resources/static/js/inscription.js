// Sélectionne le formulaire d'inscription par son ID
let formInscription = document.querySelector('#formInscription');

// Sélectionne les champs de mot de passe par leur ID
let inputmdp1 = document.querySelector("#mdp1NonEncoder");
let inputmdp2 = document.querySelector("#mdp2NonEncoder");

// Ajoute un gestionnaire d'événements pour la soumission du formulaire
formInscription.addEventListener('submit', function (event) {
    // Récupère les données du formulaire
    const formData = new FormData(formInscription);

    // Empêche la soumission normale du formulaire
    event.preventDefault();

    // Convertit les données du formulaire en JSON
    const formDataJSON = {};
    formData.forEach((value, key) => {
        formDataJSON[key] = value;
    });

    // Envoie une requête fetch au backend pour la validation
    fetch('/api/verification/inscription', {
        method: 'POST', // Utilise la méthode HTTP POST
        headers: {
            'Content-Type': 'application/json' // Utilise le type de contenu JSON
        },
        body: JSON.stringify(formDataJSON) // Corps de la requête contenant les données JSON
    }).then(response => response.json()) // Extrait la réponse JSON de la requête
        .then(data => {
            console.log(data); // Affiche la réponse JSON dans la console
            resetValidationForm("formInscription"); // Réinitialise les validations du formulaire

            if (data.length > 0) { // S'il y a des erreurs de validation
                // Itère sur tous les champs du formulaire
                document.querySelectorAll("form#formInscription input").forEach((unInput) => {
                    // Valide chaque champ avec les erreurs
                    validateInput(unInput, data);
                    if (!unInput.classList.contains("is-invalid")) {
                        // Marque le champ comme valide
                        unInput.classList.add("is-valid");
                    }
                });
            } else { // Si aucune erreur de validation
                // Soumet le formulaire
                formInscription.submit();
            }
        });
});

// Ajoute des écouteurs d'événements pour réinitialiser la validation lorsque le champ obtient le focus ou une touche est relâchée
formInscription.querySelectorAll("input").forEach((unInput) => {
    unInput.addEventListener("focus", (event) => {
        // Réinitialise la validation du champ
        resetValidationInput(unInput);
    });

    unInput.addEventListener("keyup", (event) => {
        // Effectue une requête de validation et valide le champ avec les erreurs récupérées
        fetchValidation('/api/verification/inscription').then((erreurs) => {
            resetValidationInput(unInput); // Réinitialise la validation du champ
            validateInput(unInput, erreurs); // Valide le champ avec les erreurs récupérées
        });
    });
});

// Fonction pour réinitialiser toutes les validations du formulaire
function resetValidationForm(idForm) {
    document.querySelectorAll(`form#${idForm} input`).forEach((unInput) => {
        // Supprime la classe "is-valid" (champ valide)
        unInput.classList.remove("is-valid");
        // Supprime la classe "is-invalid" (champ invalide)
        unInput.classList.remove("is-invalid");
    });

    document.querySelectorAll(`form#${idForm} p`).forEach((unMsg) => {
        // Efface le contenu des messages d'erreur
        unMsg.innerHTML = "";
        // Supprime la classe "show" (masque les messages)
        unMsg.classList.remove("show");
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

// Fonction pour valider un champ en fonction des erreurs
function validateInput(input, listeErreur) {
    const erreurInput = listeErreur.filter(uneErreur => uneErreur?.field === input.id);
    const inputMsg = document.querySelector("#msg" + input.id + "js");
    inputMsg.innerText = "";

    if (input === inputmdp1 || input === inputmdp2) {
        const erreurConcord = listeErreur.filter(uneErreur => uneErreur.field === undefined || uneErreur.field === null);

        inputmdp1.classList.remove("is-valid", "is-invalid");
        inputmdp2.classList.remove("is-valid", "is-invalid");

        if (erreurConcord.length > 0) {
            inputmdp1.classList.add("is-invalid");
            inputmdp2.classList.add("is-invalid");
            if (erreurInput.length > 0) {
                let lesErreurs = listeErreur.filter(uneErreur => uneErreur.field == input.id);
                inputMsg.innerText = lesErreurs.map(uneErreur => uneErreur.defaultMessage).join("");
                inputMsg.classList.add("show");
            } else {
                inputMsg.classList.remove("show");
            }
        } else if (erreurInput.length > 0) {
            inputmdp1.classList.add("is-invalid");
            inputmdp2.classList.add("is-invalid");
            let lesErreurs = listeErreur.filter(uneErreur => uneErreur.field == input.id);
            inputMsg.innerText = lesErreurs.map(uneErreur => uneErreur.defaultMessage).join("");
            inputMsg.classList.add("show");
        } else {
            inputmdp1.classList.add("is-valid");
            inputmdp2.classList.add("is-valid");
        }

        const msg = document.querySelector("#msgformjs");
        msg.innerText = erreurConcord.length > 0 ? erreurConcord[0].defaultMessage : "";
        msg.classList.toggle("show", erreurConcord.length > 0);
    } else {
        input.classList.remove("is-valid", "is-invalid");

        if (erreurInput.length > 0) {
            input.classList.add("is-invalid");
            let lesErreurs = listeErreur.filter(uneErreur => uneErreur.field == input.id);
            console.log(typeof lesErreurs);
            inputMsg.innerText = lesErreurs.map(uneErreur => uneErreur.defaultMessage).join("");
            inputMsg.classList.add("show");
        } else {
            input.classList.add("is-valid");
            inputMsg.classList.remove("show");
        }
    }
}

// Fonction asynchrone pour effectuer une requête Ajax et récupérer les erreurs de validation
async function fetchValidation(urlAPI) {
    const formData = new FormData(formInscription); // Collecte les données du formulaire
    // Convertit FormData en JSON
    const formDataJSON = {};
    formData.forEach((value, key) => {
        formDataJSON[key] = value;
    });

    // Effectue une requête fetch vers l'URL de l'API de validation
    let reponse = await fetch(urlAPI, {
        method: 'POST', // Méthode HTTP POST
        headers: {
            'Content-Type': 'application/json' // Type de contenu JSON
        },
        body: JSON.stringify(formDataJSON) // Données JSON à envoyer
    });

    let data = reponse.json(); // Extrait les données JSON de la réponse
    return data; // Retourne les données JSON
}
