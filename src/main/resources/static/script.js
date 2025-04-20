// script.js - Fixed Version
document.addEventListener('DOMContentLoaded', function() {
    // Load the full menu by default
    loadMenu('');

    // Set up form submission
    document.getElementById('signup-form').addEventListener('submit', function(e) {
        e.preventDefault();
        signup();
    });
});

function showPage(pageId) {
    // Hide all pages
    document.querySelectorAll('.page').forEach(page => {
        page.style.display = 'none';
    });

    // Show the requested page
    document.getElementById(pageId + '-page').style.display = 'block';

    // REMOVED the automatic menu loading from here
}

function loadMenu(endpoint) {
    // First show the menu page
    showPage('menu');  // This won't trigger loadMenu() again

    const menuTitle = document.getElementById('menu-title');
    const menuContainer = document.getElementById('menu-items');

    // Update title
    menuTitle.textContent = endpoint
        ? `${endpoint.charAt(0).toUpperCase() + endpoint.slice(1)} Menu`
        : 'All Menus';

    // Clear existing items
    menuContainer.innerHTML = '';

    // Build the API URL
    const url = endpoint
        ? `http://localhost:8080/merger/${endpoint}`
        : 'http://localhost:8080/merger';

    // Fetch menu items
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            if (data.length === 0) {
                menuContainer.innerHTML = '<p>No items found</p>';
                return;
            }

            data.forEach(item => {
                const menuItem = document.createElement('div');
                menuItem.className = 'menu-item';
                menuItem.innerHTML = `
                    <h3>${item.name}</h3>
                    <p>${item.description}</p>
                    <p>$${item.price.toFixed(2)} ${item.vegetarian ? '(Vegetarian)' : ''}</p>
                `;
                menuContainer.appendChild(menuItem);
            });
        })
        .catch(error => {
            console.error('Error loading menu:', error);
            menuContainer.innerHTML = '<p>Error loading menu items</p>';
        });
}

function signup() {
    const email = document.getElementById('email').value;
    const username = document.getElementById('signup-username').value;
    const password = document.getElementById('signup-password').value;

    fetch('http://localhost:8080/merger/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            username: username,
            password: password,
            email: email
        })
    })
    .then(response => {
        if (response.ok) {
            alert('Signup successful! Please login.');
            showPage('login');
        } else {
            alert('Signup failed. Please try again.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred during signup.');
    });
}