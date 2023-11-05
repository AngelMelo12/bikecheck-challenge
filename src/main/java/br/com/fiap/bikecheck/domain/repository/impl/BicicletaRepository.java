package br.com.fiap.bikecheck.domain.repository.impl;

import br.com.fiap.bikecheck.domain.entity.Bicicleta;
import br.com.fiap.bikecheck.domain.entity.Cliente;
import br.com.fiap.bikecheck.domain.entity.Vistoria;
import br.com.fiap.bikecheck.domain.repository.Repository;
import br.com.fiap.bikecheck.domain.service.impl.ClienteService;
import br.com.fiap.bikecheck.domain.service.impl.VistoriaService;
import br.com.fiap.infra.ConnectionFactory;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class BicicletaRepository implements Repository<Bicicleta, Long> {

    private VistoriaService vistoriaService = new VistoriaService();

    private ClienteService clienteService = new ClienteService();

    private ConnectionFactory connectionFactory;

    private static final AtomicReference<BicicletaRepository> instance = new AtomicReference<>();

    private BicicletaRepository() {
        this.connectionFactory = ConnectionFactory.build();
    }

    public static BicicletaRepository build() {
        instance.compareAndSet(null, new BicicletaRepository());
        return instance.get();
    }


    @Override
    public List<Bicicleta> findAll() {
        List<Bicicleta> list = new ArrayList<>();
        Connection con = connectionFactory.getConnection();
        ResultSet rs = null;
        Statement st = null;

        var sql = "SELECT * FROM T_BC_BICICLETA";

        try {
            st = con.createStatement();

            rs = st.executeQuery(sql);

            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    Long id = rs.getLong("ID_BICICLETA");
                    String marca = rs.getString("NM_MARCA");
                    LocalDate dataModelo = rs.getDate("DT_MODELO_BICICLETA").toLocalDate();
                    Double valor = rs.getDouble("VL_BICICLETA");
                    String cor = rs.getString("COR");
                    String descricaoPecas = rs.getString("DS_PECAS_BICICLETA");
                    Blob fotosBlob = rs.getBlob("FOTOS");
                    byte[] fotos = fotosBlob.getBytes(1L, (int) fotosBlob.length());
                    List<Vistoria> vistorias = vistoriaService.findByBicicleta(id);
                    Vistoria vistoria = vistorias.stream().findAny().orElse(null);
                    assert vistoria != null;
                    Cliente cliente = clienteService.findById(vistoria.getCliente().getId());

                    list.add(new Bicicleta(
                            id,
                            marca,
                            dataModelo,
                            valor,
                            cor,
                            descricaoPecas,
                            fotos,
                            vistorias,
                            cliente)
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível consultar as bicicletas");
        } finally {
            fecharObjetos(rs, st, con);
        }
        return list;
    }

    @Override
    public Bicicleta findById(Long id) {
        Bicicleta bicicleta = null;
        var sql = "SELECT * FROM T_BC_BICICLETA where ID_BICICLETA=?";

        Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    String marca = rs.getString("NM_MARCA");
                    LocalDate dataModelo = rs.getDate("DT_MODELO_BICICLETA").toLocalDate();
                    Double valor = rs.getDouble("VL_BICICLETA");
                    String cor = rs.getString("COR");
                    String descricaoPecas = rs.getString("DS_PECAS_BICICLETA");
                    Blob fotosBlob = rs.getBlob("FOTOS");
                    byte[] fotos = fotosBlob.getBytes(1L, (int) fotosBlob.length());
                    List<Vistoria> vistorias = vistoriaService.findByBicicleta(id);
                    Vistoria vistoria = vistorias.stream().findAny().orElse(null);
                    assert vistoria != null;
                    Cliente cliente = clienteService.findById(vistoria.getCliente().getId());

                    bicicleta = new Bicicleta(
                            id,
                            marca,
                            dataModelo,
                            valor,
                            cor,
                            descricaoPecas,
                            fotos,
                            vistorias,
                            cliente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possivel realizar a consulta: " + e.getMessage());
        } finally {
            fecharObjetos(rs, ps, conn);
        }
        return bicicleta;
    }

    @Override
    public Bicicleta persist(Bicicleta bicicleta) {
        var sql = "INSERT INTO T_BC_BICICLETA " +
                " (ID_BICICLETA, NM_MARCA, DT_MODELO_BICICLETA, VL_BICICLETA, COR, DS_PECAS_BICICLETA, FOTOS) " +
                " values (0, ?, ?, ?, ?, ?, ?)";

        Connection conn = connectionFactory.getConnection();
        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, bicicleta.getMarca());
            ps.setDate(2, Date.valueOf(bicicleta.getDataModelo()));
            ps.setDouble(3, bicicleta.getValor());
            ps.setString(4, bicicleta.getCor());
            ps.setString(5, bicicleta.getDescricaoPecas());
            ps.setBlob(6, new SerialBlob(bicicleta.getFotos()));

            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                final Long id = rs.getLong(1);
                bicicleta.setId(id);
            }

        } catch (SQLException e) {
            System.err.println("Não foi possivel salvar a bicicleta no banco de dados:  " + e.getMessage());
        } finally {
            fecharObjetos(null, ps, conn);
        }

        return bicicleta;
    }
}
