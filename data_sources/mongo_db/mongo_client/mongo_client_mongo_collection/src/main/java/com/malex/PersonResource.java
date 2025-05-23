package com.malex;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

  private final PersonRepository personRepository;

  @Inject
  public PersonResource(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @POST
  @Path("/person")
  public String createPerson(PersonEntity person) {
    return personRepository.add(person);
  }

  @GET
  @Path("/persons")
  public List<PersonEntity> getPersons() {
    return personRepository.getPersons();
  }

  @PUT
  @Path("/person/{id}")
  public long anniversaryPerson(@PathParam("id") String id) {
    return personRepository.anniversaryPerson(id);
  }

  @DELETE
  @Path("/person/{id}")
  public long deletePerson(@PathParam("id") String id) {
    return personRepository.deletePerson(id);
  }
}
