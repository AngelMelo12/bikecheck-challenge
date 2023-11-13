package br.com.fiap.bikecheck.domain.resource;

import br.com.fiap.bikecheck.domain.dto.BicicletaDTO;
import br.com.fiap.bikecheck.domain.entity.Bicicleta;
import br.com.fiap.bikecheck.domain.service.impl.BicicletaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/bicicleta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BicicletaResource implements Resource<BicicletaDTO, Long> {

    @Context
    UriInfo uriInfo;

    private final BicicletaService service = new BicicletaService();

    @GET
    @Override
    public Response findAll() {
        List<Bicicleta> all = service.findAll();
        return Response.ok( all.stream().map( BicicletaDTO::of ).toList() ).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        Bicicleta entity = service.findById( id );
        if (Objects.isNull( entity )) return Response.status( 404 ).build();
        return Response.ok(BicicletaDTO.of(entity)).build();
    }

    @POST
    @Override
    public Response persist(BicicletaDTO dto) {
        Bicicleta persisted = service.persist(BicicletaDTO.of(dto) );

        if (Objects.isNull( persisted )) return Response.status( 400 ).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path(String.valueOf(persisted.getId())).build();

        return Response.created(uri).entity(BicicletaDTO.of(persisted)).build();
    }
}
