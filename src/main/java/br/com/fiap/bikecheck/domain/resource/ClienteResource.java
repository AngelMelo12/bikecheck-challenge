package br.com.fiap.bikecheck.domain.resource;

import br.com.fiap.bikecheck.domain.dto.ClienteDTO;
import br.com.fiap.bikecheck.domain.entity.Bicicleta;
import br.com.fiap.bikecheck.domain.entity.Cliente;
import br.com.fiap.bikecheck.domain.service.impl.ClienteService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource implements Resource<ClienteDTO, Long> {

    @Context
    UriInfo uriInfo;

    private final ClienteService service = new ClienteService();

    @GET
    @Override
    public Response findAll() {
        List<Cliente> all = service.findAll();
        return Response.ok(all.stream().map( ClienteDTO::of ).toList()).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        Cliente entity = service.findById( id );
        if (Objects.isNull(entity)) return Response.status(404).build();
        return Response.ok(ClienteDTO.of(entity)).build();
    }

    @POST
    @Override
    public Response persist(ClienteDTO dto) {
        Cliente persisted = service.persist(ClienteDTO.of(dto) );

        if (Objects.isNull(persisted)) return Response.status(400).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path(String.valueOf(persisted.getId())).build();

        return Response.created(uri).entity(ClienteDTO.of(persisted)).build();
    }
}
