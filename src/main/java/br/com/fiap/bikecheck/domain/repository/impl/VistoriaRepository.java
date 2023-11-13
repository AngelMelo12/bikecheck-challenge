package br.com.fiap.bikecheck.domain.repository.impl;

import br.com.fiap.bikecheck.domain.entity.Bicicleta;
import br.com.fiap.bikecheck.domain.entity.Seguro;
import br.com.fiap.bikecheck.domain.entity.Vistoria;
import br.com.fiap.bikecheck.domain.repository.Repository;
import br.com.fiap.bikecheck.domain.service.impl.BicicletaService;
import br.com.fiap.bikecheck.domain.service.impl.SeguroService;
import br.com.fiap.bikecheck.infra.ConnectionFactory;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class VistoriaRepository implements Repository<Vistoria, Long> {

    private final BicicletaService bicicletaService;
    private final SeguroService seguroService;

    private final ConnectionFactory connectionFactory;

    private static final AtomicReference<VistoriaRepository> instance = new AtomicReference<>();

    private VistoriaRepository() {
        this.bicicletaService = new BicicletaService();
        this.seguroService = new SeguroService();
        this.connectionFactory = ConnectionFactory.build();
    }

    public static VistoriaRepository build() {
        instance.compareAndSet(null, new VistoriaRepository());
        return instance.get();
    }


    @Override
    public List<Vistoria> findAll() {


        List<Vistoria> list = new ArrayList<>();
        Connection con = connectionFactory.getConnection();
        ResultSet rs = null;
        Statement st = null;

        var sql = "SELECT * FROM T_BC_VISTORIA";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    Long id = rs.getLong("ID_VISTORIA");
                    Blob fotosBlob = rs.getBlob("FOTOS");
                    byte[] fotos = fotosBlob.getBytes(1L, (int) fotosBlob.length());
                    Long idBicicleta = rs.getLong("ID_BICICLETA");
                    Bicicleta bicicleta = bicicletaService.findById(idBicicleta);
                    Long idSeguro = rs.getLong("ID_SEGURO");
                    Seguro seguro = seguroService.findById(idSeguro);

                    list.add(new Vistoria(
                            id,
                            fotos,
                            bicicleta,
                            seguro)
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível consultar as vistorias");
        } finally {
            fecharObjetos(rs, st, con);
        }
        return list;
    }

    @Override
    public Vistoria findById(Long id) {
        BicicletaRepository bicicletaRepository = BicicletaRepository.build();
        SeguroRepository seguroRepository = SeguroRepository.build();
        Vistoria vistoria = null;
        var sql = "SELECT * FROM T_BC_VISTORIA where ID_VISTORIA=?";

        Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    Blob fotosBlob = rs.getBlob("FOTOS");
                    byte[] fotos = fotosBlob.getBytes(1L, (int) fotosBlob.length());
                    Long idBicicleta = rs.getLong("ID_BICICLETA");
                    Bicicleta bicicleta = bicicletaRepository.findById(idBicicleta);
                    Long idSeguro = rs.getLong("ID_SEGURO");
                    Seguro seguro = seguroRepository.findById(idSeguro);

                     return new Vistoria(
                            id,
                            fotos,
                            bicicleta,
                            seguro);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possivel realizar a consulta: " + e.getMessage());
        } finally {
            fecharObjetos(rs, ps, conn);
        }
        return vistoria;
    }

    public List<Vistoria> findBySeguro(Long idSeguro) {
        BicicletaRepository bicicletaRepository = BicicletaRepository.build();
        SeguroRepository seguroRepository = SeguroRepository.build();
        List<Vistoria> list = new ArrayList<>();
        Connection con = connectionFactory.getConnection();
        ResultSet rs = null;
        Statement st = null;

        var sql = "SELECT * FROM T_BC_VISTORIA WHERE ID_SEGURO=?";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    Long id = rs.getLong("ID_VISTORIA");
                    Blob fotosBlob = rs.getBlob("FOTOS");
                    byte[] fotos = fotosBlob.getBytes(1L, (int) fotosBlob.length());
                    Long idBicicleta = rs.getLong("ID_BICICLETA");
                    Bicicleta bicicleta = bicicletaRepository.findById(idBicicleta);
                    Seguro seguro = seguroRepository.findById(idSeguro);

                    list.add(new Vistoria(
                            id,
                            fotos,
                            bicicleta,
                            seguro)
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível consultar as vistorias");
        } finally {
            fecharObjetos(rs, st, con);
        }
        return list;
    }

    @Override
    public Vistoria persist(Vistoria vistoria) {
        var sql = "INSERT INTO T_BC_VISTORIA " +
                " (ID_VISTORIA, FOTOS, ID_BICICLETA, ID_SEGURO) " +
                " values (0, ?, ?, ?)";

        Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBlob(1, new SerialBlob(vistoria.getFoto()));
            ps.setLong(2, vistoria.getBicicleta().getId());
            ps.setLong(3, vistoria.getSeguro().getId());
            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                final Long id = rs.getLong(1);
                vistoria.setId(id);
            }

        } catch (SQLException e) {
            System.err.println("Não foi possivel salvar o vistoria no banco de dados:  " + e.getMessage());
        } finally {
            fecharObjetos(null, ps, conn);
        }

        return vistoria;
    }
}
