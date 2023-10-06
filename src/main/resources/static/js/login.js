// Récupérer le formulaire et les champs
const loginForm = document.querySelector('form');
const emailField = document.querySelector('#email');
const passwordField = document.querySelector('#password');

//Ecouter l'evenement de soumission du formulaire
loginForm.addEventListener('submit',function (event){
    event.preventDefault(); // permet d'empecher la soumission du formulaire par defaut

    // on reinitialise les erreurs
    clearErrors();

    // on valide le champs email
    if(!isValidEmail(emailField.value)) {
        displayError(emailField,'Veuillez entrer un email vailde.');
        return; // on arrete la validation si l'email n'est pas valide
    }

    // Valider le champ password
    if (passwordField.value.length < 8) {
        displayError(passwordField,'Le mot de passe doit contenir au moins 8 caractere.');
    }
    // Si les champs sont valides on envoi une requette a la url de validation  avec axios
    axios.post('votre/url/ de validation',
        {
            email: emailField,
            password: passwordField.value,
        })
        .then(function(response) {
            //On gere la reponse du serveur par exemple, afficher un mesage d'erreur ou redireiger l utilisateur
            console.log(response.data);
        })
        .catch(function (error) {
            // Gérer les erreurs, par exemple, afficher un message d'erreur
            console.error(error);
        });
});

// Fonction pour effacer les messages d'erreur
function clearErrors() {
    const errorMessages = document.querySelector('.validation-message');
    errorMessages.forEach(function (element) {
        element.textContent = '';
    });
}
// Fonction pour afficher un message d'erreur sous un champ
function displayError(field, message) {
    const errorElement = field.parentElement.querySelector('.validation-message');
    errorElement.textContent = message;
}
// Fonction pour valider une adresse email (vous pouvez utiliser une regex plus robuste ici)
function isValidEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}