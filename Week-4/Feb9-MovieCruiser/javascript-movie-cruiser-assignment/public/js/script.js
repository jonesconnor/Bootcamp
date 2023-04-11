const movieList = [];
const favouritesList = [];

function getMovies() {
	const movieContainer = document.getElementById("moviesList");
	return fetch("http://localhost:3000/movies")
	.then(response => response.json())
	.then(data => {
		movieList.push(...data);
		// put each list item onto a carousel of cards maybe?
		movieList.forEach((movie, index) => {
			const movieCard = document.createElement("div");
			movieCard.classList.add("card", "mb-3");
			movieContainer.appendChild(movieCard);

			const cardBody = document.createElement("div");
			cardBody.classList.add("card-body");
			movieCard.appendChild(cardBody);

			const cardTitle = document.createElement("h5");
			cardTitle.classList.add("card-title");
			cardTitle.innerHTML = movie.title;
			cardBody.appendChild(cardTitle);

			const movieImage = document.createElement("img");
			movieImage.src = movie.image;
			movieImage.classList.add("card-img-top");
			movieCard.appendChild(movieImage);

			const button = document.createElement("button");
			button.type = "button";
			button.classList.add("btn", "btn-primary", "btn-sm");
			button.innerHTML = "Add to Favourites";
			button.id = `favourites-btn-${index}`;
			//movieListItem.appendChild(button);
			cardBody.appendChild(button);


			document.getElementById(button.id).addEventListener("click", function() {
				addFavourite(movie.id);
			});
		})
		return Promise.resolve(data);
	})
	.catch(error => {
		return Promise.reject(error)
	})
}

function getFavourites() {
	const favouriteContainer = document.getElementById("favouritesList");
	// Remove all existing child elements so that duplicates don't get printed on page
	while (favouriteContainer.firstChild) {
		favouriteContainer.removeChild(favouriteContainer.firstChild);
	}
	return fetch("http://localhost:3000/favourites")
	.then(response => response.json())
	.then(data => {
		data.forEach(favourite => {
			const favouritesListItem = document.createElement("li");
			favouritesListItem.innerHTML = favourite.title;
			favouriteContainer.appendChild(favouritesListItem);
		})
		return Promise.resolve(data);
	})
	.catch(error => {
		return Promise.reject(error)
	})
}

function addFavourite(id) {
	const movie = movieList.find(m => m.id === id);
	if (!movie) {
		alert("Movie not found");
		return Promise.reject(new Error("Movie not found"));
	}
	if (favouritesList.find(f => f.id === id)) {
		alert("Movie has already been added to favourites");
		return Promise.reject(new Error("Movie is already added to favourites"));
	} else {
		return fetch("http://localhost:3000/favourites", {
			method: "POST",
			headers: {
				"content-type": "application/json"
			},
			body: JSON.stringify(movie)
		})
		.then(response => {
			if (response.ok) {
				favouritesList.push(movie);
				displayFavourite(movie);
				console.log(favouritesList);
				return Promise.resolve(favouritesList);
			}
		})
		.catch(error => {
			return Promise.reject(error);
		});
	}
}

module.exports = {
	getMovies,
	getFavourites,
	addFavourite
};

function displayFavourite(movie) {
	const favouriteContainer = document.getElementById("favouritesList");

	const movieCard = document.createElement("div");
	movieCard.classList.add("card", "mb-3");
	favouriteContainer.appendChild(movieCard);

	const cardBody = document.createElement("div");
	cardBody.classList.add("card-body");
	movieCard.appendChild(cardBody);

	const cardTitle = document.createElement("h5");
	cardTitle.classList.add("card-title");
	cardTitle.innerHTML = movie.title;
	cardBody.appendChild(cardTitle);

	const movieImage = document.createElement("img");
	movieImage.src = movie.image;
	movieImage.classList.add("card-img-top");
	movieCard.appendChild(movieImage);
}

// You will get error - Uncaught ReferenceError: module is not defined
// while running this script on browser which you shall ignore
// as this is required for testing purposes and shall not hinder
// it's normal execution


