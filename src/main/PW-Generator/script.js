/**
 * This function initiates the password generating process
 * receiving all password parameters:
 * -password length
 * -if it shall include lowercase letters
 * -if it shall include capital letters
 * -if it shall include numbers
 * -if it shall include special characters
 * It then sets the generated password to the according label
 */
function initiatePasswordGenerating() {

    const lowercaseLetters = document.getElementById("cbxLowerCaseLetters").checked;
    const capitalLetters = document.getElementById("cbxCapitalLetters").checked;
    const numbers = document.getElementById("cbxNumbers").checked;
    const specialCharacters = document.getElementById("cbxSpecialCharacters").checked;

    console.log("lowercaseLetters = " + lowercaseLetters, "\ncapitalLetters = " + capitalLetters, "\nnumbers = " + numbers, "\nspecialCharacter = " + specialCharacters);

    let passwordLength = document.getElementById("lengthSlider").value;
    let password = generatePassword(passwordLength, lowercaseLetters, capitalLetters, numbers, specialCharacters);

    document.getElementById('generatedPasswordLabel').innerText = password;
}

/**
 * This function generates a password that matches the criteria provided as params
 * @param passwordLength the password length
 * @param lowercaseLetters boolean if it shall include lowercase letters
 * @param capitalLetters boolean if it shall include capital letters
 * @param numbers boolean if it shall include numbers
 * @param specialCharacters boolean if it shall include special characters
 * @returns {string} the generated password
 */
function generatePassword(passwordLength, lowercaseLetters, capitalLetters, numbers, specialCharacters) {
    let charset = '';
    if (lowercaseLetters) charset += 'abcdefghijklmnopqrstuvwxyz';
    if (capitalLetters) charset += 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    if (specialCharacters) charset += '!@#$%^&*()_+[]{}|;:,.<>?';
    if (numbers) charset += '0123456789';

    let password = '';
    let passwordSuccessful = false;

    while (!passwordSuccessful) {
        password = '';
        for (let i = 0; i < passwordLength; i++) {
            password += charset.charAt(Math.floor(Math.random() * charset.length));
        }
        passwordSuccessful = checkPassword(password, lowercaseLetters, capitalLetters, numbers, specialCharacters);
    }

    return password;
}

/**
 * This function copies the generated password to the clipboard.
 * @returns {Promise<void>} resolved if copying was successful, rejected if failed
 */
async function copyPasswordToClipboard() {
    let copiesPassword = document.getElementById("generatedPasswordLabel").textContent;
    try {
        await navigator.clipboard.writeText(copiesPassword);
        console.log("Copied to clipboard: " + copiesPassword);
    } catch (err) {
        console.error("Copy to clipboard failed: " + err);
    }
}

/**
 * This function updates the label of the password length
 * with the value provided by the range input slider
 */
function updateLengthLabel() {
    let length = document.getElementById("lengthSlider").value;
    document.getElementById("lengthValueLabel").textContent = length;
}

/**
 * This event listener checks whether one checkbox is selected / active
 * and then calls the checkCheckBoxes() function
 */
document.querySelectorAll('.checkBox').forEach(checkBox => {
    checkBox.addEventListener('change', checkCheckboxes);
});

/**
 * This function checks whether no checkbox is selected.
 * If that is the case, the generate and regenerate will be disabled
 */
function checkCheckboxes() {
    let lowercaseLetters = document.getElementById("cbxLowerCaseLetters").checked;
    let capitalLetters = document.getElementById("cbxCapitalLetters").checked;
    let numbers = document.getElementById("cbxNumbers").checked;
    let specialCharacters = document.getElementById("cbxSpecialCharacters").checked;

    const anyChecked = lowercaseLetters || capitalLetters || numbers || specialCharacters;

    document.getElementById('generateButton').disabled = !anyChecked;
    document.getElementById('regenerateButton').disabled = !anyChecked;
}

/**
 * this function checks whether a generated password matches the selected criteria
 * @param password the generated password
 * @param lowercaseLetter boolean if include lowercaseLetters is selected
 * @param capitalLetters boolean if include capitalLetters is selected
 * @param numbers boolean if include numbers is selected
 * @param specialCharacters boolean if include specialCharacters is selected
 * @returns {boolean} true if password matches criteria, false otherwise
 */
function checkPassword(password, lowercaseLetter, capitalLetters, numbers, specialCharacters) {
    const regexLowercaseLetters = /[a-z]/;
    const regexCapitalLetters = /[A-Z]/;
    const regexNumeric = /[0-9]/;
    const regexSpecialCharacters = /[!@#$%^&*()_+\[\]{}|;:,.<>?]/;

    let passwordValid = true;

    if (lowercaseLetter) {
        passwordValid = passwordValid && regexLowercaseLetters.test(password);
    }

    if (capitalLetters) {
        passwordValid = passwordValid && regexCapitalLetters.test(password);
    }

    if (numbers) {
        passwordValid = passwordValid && regexNumeric.test(password);
    }

    if (specialCharacters) {
        passwordValid = passwordValid && regexSpecialCharacters.test(password);
    }

    return passwordValid;
}

/**
 * This function changes the language of the page to the one selected in the dropdown menu
 * @param lang the language selected in the dropdown menu
 */
function changeLanguage(lang) {
    const langKeys = Object.keys(languages[lang]);
    langKeys.forEach(function(key) {
        document.getElementById(key).innerHTML = languages[lang][key];
    });
    document.documentElement.lang = lang; // Update the lang attribute in the <html> tag
}

/**
 * This function fetches the language data form the languages.json file and returns it.
 * @returns {Promise<any>}
 */
function getLanguages() {
    return fetch('../resources/languages.json')
        .then(r => r.json())
        .catch(error => console.error('Error fetching the JSON:', error));
}


let languages;
getLanguages().then(data => {
    languages = data;
    changeLanguage('en');
})
updateLengthLabel();
checkCheckboxes();