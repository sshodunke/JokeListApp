package com.smithshodunke.jokelistapp.data.mappers

import com.smithshodunke.jokelistapp.data.remote.dto.JokeDto
import com.smithshodunke.jokelistapp.data.remote.dto.JokeListItemDto
import com.smithshodunke.jokelistapp.domain.model.joke.Joke

fun JokeDto.toJoke(): Joke {
    if (this.type == "single") {
        return Joke.SinglePartJoke(
            category = this.category,
            type = this.type,
            joke = this.joke,
            flags = this.flags,
            id = this.id,
            safe = this.safe,
        )
    } else {
        return Joke.TwoPartJoke(
            category = this.category,
            type = this.type,
            setup = this.setup,
            delivery = this.delivery,
            flags = this.flags,
            id = this.id,
            safe = this.safe,
        )
    }
}

fun JokeListItemDto.toJokeList(): List<Joke> {
    return this.jokes.map { jokeDto -> jokeDto.toJoke() }
}