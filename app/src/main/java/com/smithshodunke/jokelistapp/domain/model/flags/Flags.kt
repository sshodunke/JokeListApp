package com.smithshodunke.jokelistapp.domain.model.flags

enum class Flags(val flag: String) {
    NSFW("nsfw"),
    POLITICAL("political"),
    RELIGIOUS("religious"),
    RACIST("racist"),
    SEXIST("sexist"),
    EXPLICIT("explicit")
}