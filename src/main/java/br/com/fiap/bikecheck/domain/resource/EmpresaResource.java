package br.com.fiap.bikecheck.domain.resource;

import br.com.fiap.bikecheck.domain.dto.EmpresaDTO;
import br.com.fiap.bikecheck.domain.entity.Empresa;
import br.com.fiap.bikecheck.domain.service.impl.EmpresaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/empresa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpresaResource implements Resource<EmpresaDTO, Long> {

    @Context
    UriInfo uriInfo;

    private final EmpresaService service = new EmpresaService();

    @GET
    @Override
    public Response findAll() {
        List<Empresa> all = service.findAll();
        return Response.ok(all.stream().map( EmpresaDTO::of ).toList()).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        Empresa entity = service.findById( id );
        if (Objects.isNull(entity)) return Response.status(404).build();
        return Response.ok(EmpresaDTO.of(entity)).build();
    }

    @POST
    @Override
    public Response persist(EmpresaDTO dto) {
        Empresa persisted = service.persist(EmpresaDTO.of(dto) );

        if (Objects.isNull(persisted)) return Response.status(400).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path(String.valueOf(persisted.getId())).build();

        return Response.created(uri).entity(EmpresaDTO.of(persisted)).build();
    }
}
