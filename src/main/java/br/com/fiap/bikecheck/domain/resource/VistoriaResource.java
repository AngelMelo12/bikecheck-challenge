package br.com.fiap.bikecheck.domain.resource;

import br.com.fiap.bikecheck.domain.dto.VistoriaDTO;
import br.com.fiap.bikecheck.domain.entity.Vistoria;
import br.com.fiap.bikecheck.domain.service.impl.VistoriaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/vistoria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VistoriaResource implements Resource<VistoriaDTO, Long> {

    @Context
    UriInfo uriInfo;

    private final VistoriaService service = new VistoriaService();

    @GET
    @Override
    public Response findAll() {
        List<Vistoria> all = service.findAll();
        return Response.ok(all.stream().map( VistoriaDTO::of ).toList()).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        Vistoria entity = service.findById( id );
        if (Objects.isNull(entity)) return Response.status(404).build();
        return Response.ok(VistoriaDTO.of(entity)).build();
    }

    @POST
    @Override
    public Response persist(VistoriaDTO dto) {
        Vistoria persisted = service.persist(VistoriaDTO.of(dto) );

        if (Objects.isNull(persisted)) return Response.status(400).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path(String.valueOf(persisted.getId())).build();

        return Response.created(uri).entity(VistoriaDTO.of(persisted)).build();
    }
}
