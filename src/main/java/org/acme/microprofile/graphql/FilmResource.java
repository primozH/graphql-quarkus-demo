package org.acme.microprofile.graphql;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.graphql.*;

import javax.inject.Inject;
import java.util.List;

@GraphQLApi
public class FilmResource {

    @Inject
    GalaxyService service;

    @Query("allFilms")
    @Description("Get all Films from a galaxy far far away")
    public List<Film> getAllFilms() {
        return service.getAllFilms();
    }

    @Query
    @Description("Get a Film from a galaxy far far away")
    public Uni<Film> getFilm(@Name("filmId") int id) {
        return service.getFilm(id);
    }

    public List<Hero> heroes(@Source Film film) {
        return service.getHeroesByFilm(film);
    }

    @Mutation
    public Hero createHero(Hero hero) {
        service.addHero(hero);
        return hero;
    }

    @Mutation
    public Hero deleteHero(int id) {
        return service.deleteHero(id);
    }

    @Query
    public List<Hero> getHeroesWithSurname(@DefaultValue("Skywalker") String surname) {
        return service.getHeroesBySurname(surname);
    }
}