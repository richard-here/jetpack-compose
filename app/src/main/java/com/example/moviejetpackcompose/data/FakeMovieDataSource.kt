package com.example.moviejetpackcompose.data

import com.example.moviejetpackcompose.R
import com.example.moviejetpackcompose.model.Movie

object FakeMovieDataSource {
    val dummyMovies = mutableListOf(
        Movie(
            id = 1,
            title = "The Peripheral",
            releaseYear = 2022,
            genres = listOf("Sci-Fi & Fantasy", "Drama"),
            userScore = 81,
            overview = "Stuck in a small Appalachian town, a young woman’s only escape from the daily grind is playing advanced video games. She is such a good player that a company sends her a new video game system to test…but it has a surprise in store. It unlocks all of her dreams of finding a purpose, romance, and glamour in what seems like a game…but it also puts her and her family in real danger.",
            creator = "Scott B. Smith",
            image = R.drawable.the_peripheral,
        ),
        Movie(
            id = 2,
            title = "The Woman King",
            releaseYear = 2022,
            genres = listOf("Action", "Drama", "History"),
            userScore = 79,
            overview = "The story of the Agojie, the all-female unit of warriors who protected the African Kingdom of Dahomey in the 1800s with skills and a fierceness unlike anything the world has ever seen, and General Nanisca as she trains the next generation of recruits and readies them for battle against an enemy determined to destroy their way of life.",
            creator = "Dana Stevens",
            image = R.drawable.the_woman_king,
        ),
        Movie(
            id = 3,
            title = "Black Adam",
            releaseYear = 2022,
            genres = listOf("Action", "Fantasy", "Science Fiction"),
            userScore = 73,
            overview = "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
            creator = "Adam Sztykiel",
            image = R.drawable.black_adam,
        ),
        Movie(
            id = 4,
            title = "Paradise City",
            releaseYear = 2022,
            genres = listOf("Crime", "Action", "Thriller"),
            userScore = 63,
            overview = "Renegade bounty hunter Ryan Swan must carve his way through the Hawaiian crime world to wreak vengeance on the kingpin who murdered his father.",
            creator = "Chuck Russell",
            image = R.drawable.paradise_city,
        ),
        Movie(
            id = 5,
            title = "Cop Secret",
            releaseYear = 2022,
            genres = listOf("Comedy", "Action", "Thriller"),
            userScore = 63,
            overview = "When Bússi, Iceland's toughest cop, is forced to work with a new partner to solve a series of bank robberies, the pressure to close the case as soon as possible proves too much for him.",
            creator = "Egill Einarsson",
            image = R.drawable.cop_secret,
        ),
        Movie(
            id = 6,
            title = "One Way",
            releaseYear = 2022,
            genres = listOf("Comedy", "Action", "Thriller"),
            userScore = 63,
            overview = "On the run with a bag full of cash after a robbing his former crime boss—and a potentially fatal wound—Freddy slips onto a bus headed into the unrelenting California desert. With his life slipping through his fingers, Freddy is left with very few choices to survive.",
            creator = "Andrew Baird",
            image = R.drawable.one_way,
        ),
        Movie(
            id = 7,
            title = "Polar",
            releaseYear = 2022,
            genres = listOf("Action", "Adventure", "Thriller"),
            userScore = 50,
            overview = "MG, a policewoman who has been expelled from the Corps due to the problems with alcohol and drugs that she has had since the loss of her son, receives a call from a man asking her to look for Macarena Gómez, a popular TV actress.",
            creator = "Alberto Palma",
            image = R.drawable.polar,
        ),
        Movie(
            id = 8,
            title = "Corrective Measures",
            releaseYear = 2022,
            genres = listOf("Science Fiction", "Action"),
            userScore = 51,
            overview = "Set in San Tiburon, the world's most dangerous maximum-security penitentiary and home to the world's most treacherous superpowered criminals, where tensions among the inmates and staff heighten, leading to anarchy that engulfs the prison and order is turned upside down.",
            creator = "Sean Patrick O'Reilly",
            image = R.drawable.corrective_measures,
        ),
        Movie(
            id = 9,
            title = "Disenchanted",
            releaseYear = 2022,
            genres = listOf("Comedy", "Family", "Fantasy"),
            userScore = 73,
            overview = "Disillusioned with life in the city, feeling out of place in suburbia, and frustrated that her happily ever after hasn’t been so easy to find, Giselle turns to the magic of Andalasia for help. Accidentally transforming the entire town into a real-life fairy tale and placing her family’s future happiness in jeopardy, she must race against time to reverse the spell and determine what happily ever after truly means to her and her family.",
            creator = "Richard LaGravenese",
            image = R.drawable.disenchanted,
        ),
        Movie(
            id = 10,
            title = "Frank and Penelope",
            releaseYear = 2022,
            genres = listOf("Thriller", "Horror", "Crime"),
            userScore = 78,
            overview = "A tale of love and violence when a man on his emotional last legs finds a savior seductively dancing in a run-down strip club. And a life most certainly headed off a cliff suddenly becomes redirected - as everything is now worth dying for.",
            creator = "Sean Patrick Flanery",
            image = R.drawable.frank_and_penelope,
        ),
    )
}