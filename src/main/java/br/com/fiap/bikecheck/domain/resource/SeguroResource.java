package br.com.fiap.bikecheck.domain.resource;

import br.com.fiap.bikecheck.domain.dto.SeguroDTO;
import br.com.fiap.bikecheck.domain.entity.Seguro;
import br.com.fiap.bikecheck.domain.service.impl.SeguroService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/seguro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SeguroResource implements Resource<SeguroDTO, Long> {

    @Context
    UriInfo uriInfo;

    private final SeguroService service = new SeguroService();

    @GET
    @Override
    public Response findAll() {
        List<Seguro> all = service.findAll();
        return Response.ok(all.stream().map( SeguroDTO::of ).toList()).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        Seguro entity = service.findById( id );
        if (Objects.isNull(entity)) return Response.status(404).build();
        return Response.ok(SeguroDTO.of(entity)).build();
    }

    @POST
    @Override
    public Response persist(SeguroDTO dto) {
        Seguro persisted = service.persist(SeguroDTO.of(dto) );

        if (Objects.isNull(persisted)) return Response.status(400).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path(String.valueOf(persisted.getId())).build();

        return Response.created(uri).entity(SeguroDTO.of(persisted)).build();
    }
}
