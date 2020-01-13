/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siakad;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author adib
 */
public class menuAdmin extends javax.swing.JFrame {

    /**
     * Creates new form menuAdmin
     */
    CardLayout cardlayout;

    public menuAdmin() {
        initComponents();
        konek();
        cardlayout = (CardLayout) (menuCard.getLayout());
        tampildata();
        tampildatados();
        matkul();
        tampildatakrs(nimtbh.getText());
    }

    public String sql;
    public com.mysql.jdbc.Connection con;
    public PreparedStatement ps;
    public Statement st;
    public ResultSet rs;

    public void konek() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/siakad", "root", "");
            st = con.createStatement();
        } catch (Exception e) {

        }
    }

    public void tampildata() {
        Object[] baris = {"NPM", "Nama", "Alamat", "Tempat Lahir", "Tgl Lahir", "Program Studi"};
        DefaultTableModel model = new DefaultTableModel(null, baris);

        try {
            sql = "select * from tbl_mahasiswa";
            ps = Siakad.koneksinya().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String[] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
                };
                model.addRow(data);
            }
            tabelnya.setModel(model);
        } catch (SQLException e) {

        }
    }

    public void matkul() {

        sql = "SELECT kode_matkul,nama_matkul FROM matakuliah";
        try {
            rs = st.executeQuery(sql);
            matkultbh.addItem("-pilih-");
            ubhkrscombo.addItem("-pilih-");
            while (rs.next()) {
                matkultbh.addItem(rs.getString("kode_matkul") + "." + rs.getString("nama_matkul"));
                ubhkrscombo.addItem(rs.getString("kode_matkul") + "." + rs.getString("nama_matkul"));
            }
        } catch (Exception e) {
        }
    }

    public void tampildatados() {
        Object[] baris = {"ID Dosen", "Nama", "Alamat", "JK", "No Telp"};
        DefaultTableModel model = new DefaultTableModel(null, baris);

        try {
            sql = "select * from tbl_dosen";
            ps = Siakad.koneksinya().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String[] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
                };
                model.addRow(data);
            }
            tabeldos.setModel(model);
        } catch (SQLException e) {

        }
    }

    public void tampildatakrs(String a) {
        Object[] baris = {"NO", "Kode", "Mata_kuliah", "SKS"};
        DefaultTableModel model = new DefaultTableModel(null, baris);

        try {
            sql = "select krs.id_krs,matakuliah.kode_matkul,matakuliah.nama_matkul,matakuliah.sks,tbl_mahasiswa.npm,tbl_mahasiswa.nama,tbl_mahasiswa.program_studi,thn_akad.thn_akada from krs inner join matakuliah on krs.kode_matkul=matakuliah.kode_matkul INNER JOIN tbl_mahasiswa ON krs.npm=tbl_mahasiswa.npm INNER JOIN thn_akad ON krs.id_thn_akad=thn_akad.id_thn_akad WHERE tbl_mahasiswa.npm=" + a;
            ps = Siakad.koneksinya().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String[] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)

                };
                model.addRow(data);
            }
            krstabel.setModel(model);
        } catch (SQLException e) {

        }
    }
        public void tampildatakhs(String a) {
        Object[] baris = {"NO", "Kode", "Mata_kuliah", "SKS","Nilai"};
        DefaultTableModel model = new DefaultTableModel(null, baris);

        try {
            sql = "select krs.id_krs,krs.nilai,matakuliah.kode_matkul,matakuliah.nama_matkul,matakuliah.sks,tbl_mahasiswa.npm,tbl_mahasiswa.nama,tbl_mahasiswa.program_studi,thn_akad.thn_akada from krs inner join matakuliah on krs.kode_matkul=matakuliah.kode_matkul INNER JOIN tbl_mahasiswa ON krs.npm=tbl_mahasiswa.npm INNER JOIN thn_akad ON krs.id_thn_akad=thn_akad.id_thn_akad WHERE tbl_mahasiswa.npm=" + a;
            ps = Siakad.koneksinya().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String[] data = {
                    rs.getString(1),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(2)
                };
                model.addRow(data);
            }
            krstabel1.setModel(model);
        } catch (SQLException e) {

        }
    }
        
         public void tampildatainput(String a) {
        Object[] baris = {"NO", "Nim", "Nama", "SKS","Nilai"};
        DefaultTableModel model = new DefaultTableModel(null, baris);

        try {
            sql = "select krs.id_krs,krs.nilai,matakuliah.kode_matkul,matakuliah.nama_matkul,matakuliah.sks,tbl_mahasiswa.npm,tbl_mahasiswa.nama,tbl_mahasiswa.program_studi,thn_akad.thn_akada from krs inner join matakuliah on krs.kode_matkul=matakuliah.kode_matkul INNER JOIN tbl_mahasiswa ON krs.npm=tbl_mahasiswa.npm INNER JOIN thn_akad ON krs.id_thn_akad=thn_akad.id_thn_akad WHERE matakuliah.kode_matkul=" + a;
            ps = Siakad.koneksinya().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String[] data = {
                    rs.getString(1),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(5),
                    rs.getString(2),                    
                };
                model.addRow(data);
            }
            inputTable.setModel(model);
        } catch (SQLException e) {

        }
    }

    public void bersih() {
        npm.setText("");
        nama.setText("");
        alamat.setText("");
        tempat.setText("");
        tgl_lahir.setDate(null);
        program_studi.setSelectedItem(null);
    }

    public void bersihdos() {
        id.setText("");
        namados.setText("");
        alamatdos.setText("");
        cekdos.clearSelection();
        telp.setText("");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cekdos = new javax.swing.ButtonGroup();
        krspunya = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        nimdialog = new javax.swing.JTextField();
        thndialog = new javax.swing.JComboBox<>();
        jButton15 = new javax.swing.JButton();
        tambahkrs = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        nimtbh = new javax.swing.JTextField();
        thntambah = new javax.swing.JTextField();
        matkultbh = new javax.swing.JComboBox<>();
        jButton16 = new javax.swing.JButton();
        ubhkrs = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        ubhkrscombo = new javax.swing.JComboBox<>();
        idubh = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        nimubh = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        khs = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        nimdialog1 = new javax.swing.JTextField();
        thndialog1 = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        inputnilai = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        inputkode = new javax.swing.JTextField();
        inputthn = new javax.swing.JComboBox<>();
        jButton20 = new javax.swing.JButton();
        tbhnil = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        nilai = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        kode = new javax.swing.JLabel();
        komat = new javax.swing.JLabel();
        split = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton11 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        menuCard = new javax.swing.JPanel();
        mahasiswa = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelnya = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        npm = new javax.swing.JTextField();
        nama = new javax.swing.JTextField();
        alamat = new javax.swing.JTextField();
        tempat = new javax.swing.JTextField();
        program_studi = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tgl_lahir = new com.toedter.calendar.JDateChooser();
        dosen = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        namados = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        alamatdos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        telp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabeldos = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        l = new javax.swing.JRadioButton();
        p = new javax.swing.JRadioButton();
        krs = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        nim = new javax.swing.JLabel();
        namakrs = new javax.swing.JLabel();
        prodikrs = new javax.swing.JLabel();
        thnkrs = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        krstabel = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        khsform = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        nim1 = new javax.swing.JLabel();
        namakrs1 = new javax.swing.JLabel();
        prodikrs1 = new javax.swing.JLabel();
        thnkrs1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        krstabel1 = new javax.swing.JTable();
        input = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        kodemat = new javax.swing.JLabel();
        namamat = new javax.swing.JLabel();
        sksmat = new javax.swing.JLabel();
        thnmat = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        inputTable = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel22.setText("NIM");

        jLabel23.setText("tahun akademik");

        thndialog.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018/2019", "2017/2018" }));

        jButton15.setText("proses");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nimdialog)
                    .addComponent(thndialog, 0, 234, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton15)
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(nimdialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(thndialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(jButton15)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout krspunyaLayout = new javax.swing.GroupLayout(krspunya.getContentPane());
        krspunya.getContentPane().setLayout(krspunyaLayout);
        krspunyaLayout.setHorizontalGroup(
            krspunyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        krspunyaLayout.setVerticalGroup(
            krspunyaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jLabel18.setText("NIM");

        jLabel19.setText("tahun akademik");

        jLabel20.setText("Mapel");

        thntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thntambahActionPerformed(evt);
            }
        });

        matkultbh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matkultbhActionPerformed(evt);
            }
        });

        jButton16.setText("tambah");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nimtbh)
                    .addComponent(thntambah)
                    .addComponent(matkultbh, 0, 173, Short.MAX_VALUE))
                .addContainerGap(91, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton16)
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(nimtbh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(thntambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(matkultbh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton16)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tambahkrsLayout = new javax.swing.GroupLayout(tambahkrs.getContentPane());
        tambahkrs.getContentPane().setLayout(tambahkrsLayout);
        tambahkrsLayout.setHorizontalGroup(
            tambahkrsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tambahkrsLayout.setVerticalGroup(
            tambahkrsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));

        jLabel21.setText("matkul");

        jButton14.setText("Ubah");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        ubhkrscombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubhkrscomboActionPerformed(evt);
            }
        });

        idubh.setText("jLabel24");

        jLabel24.setText("nim");

        jButton17.setText("hapus");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel24))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ubhkrscombo, 0, 221, Short.MAX_VALUE)
                    .addComponent(nimubh))
                .addGap(47, 47, 47))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(idubh)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton17)
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(nimubh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(ubhkrscombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14)
                    .addComponent(jButton17))
                .addGap(72, 72, 72)
                .addComponent(idubh))
        );

        javax.swing.GroupLayout ubhkrsLayout = new javax.swing.GroupLayout(ubhkrs.getContentPane());
        ubhkrs.getContentPane().setLayout(ubhkrsLayout);
        ubhkrsLayout.setHorizontalGroup(
            ubhkrsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ubhkrsLayout.setVerticalGroup(
            ubhkrsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));

        jLabel30.setText("NIM");

        thndialog1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018/2019", "2017/2018" }));

        jLabel31.setText("tahun akademik");

        jButton19.setText("proses");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nimdialog1)
                            .addComponent(thndialog1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton19)
                        .addGap(17, 17, 17)))
                .addGap(31, 31, 31))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(nimdialog1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(thndialog1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(jButton19)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout khsLayout = new javax.swing.GroupLayout(khs.getContentPane());
        khs.getContentPane().setLayout(khsLayout);
        khsLayout.setHorizontalGroup(
            khsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        khsLayout.setVerticalGroup(
            khsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));

        jLabel41.setText("tahun akademik");

        jLabel42.setText("kode matkul");

        inputthn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputthnActionPerformed(evt);
            }
        });

        jButton20.setText("proses");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(jLabel41))
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputthn, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton20)
                        .addComponent(inputkode, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(inputthn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(inputkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jButton20)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout inputnilaiLayout = new javax.swing.GroupLayout(inputnilai.getContentPane());
        inputnilai.getContentPane().setLayout(inputnilaiLayout);
        inputnilaiLayout.setHorizontalGroup(
            inputnilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputnilaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        inputnilaiLayout.setVerticalGroup(
            inputnilaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(0, 102, 102));

        jLabel37.setText("Nilai");

        jButton21.setText("proses");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        kode.setText("jLabel38");

        komat.setText("jLabel38");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton21)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(48, 48, 48)
                        .addComponent(nilai, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(131, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(kode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(komat))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(nilai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jButton21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kode)
                    .addComponent(komat)))
        );

        javax.swing.GroupLayout tbhnilLayout = new javax.swing.GroupLayout(tbhnil.getContentPane());
        tbhnil.getContentPane().setLayout(tbhnilLayout);
        tbhnilLayout.setHorizontalGroup(
            tbhnilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tbhnilLayout.setVerticalGroup(
            tbhnilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Siakad\n");
        setIconImages(null);
        setPreferredSize(new java.awt.Dimension(900, 550));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        split.setPreferredSize(new java.awt.Dimension(1366, 768));

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setPreferredSize(new java.awt.Dimension(250, 550));

        jButton1.setText("Mahasiswa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Dosen");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("SIAKAD");

        jButton11.setText("KRS");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton18.setText("KHS");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton13.setText("Input Nilai");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(87, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton13)
                .addContainerGap(542, Short.MAX_VALUE))
        );

        split.setLeftComponent(jPanel3);

        menuCard.setPreferredSize(new java.awt.Dimension(650, 550));
        menuCard.setLayout(new java.awt.CardLayout());

        mahasiswa.setBackground(new java.awt.Color(153, 255, 0));
        mahasiswa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mahasiswa.setPreferredSize(new java.awt.Dimension(900, 550));

        jButton3.setText("Tambah");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tabelnya.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelnya.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelnyaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelnya);

        jButton5.setText("Ubah");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Hapus");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("bersih");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel8.setText("NPM");

        program_studi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-PILIH-", "TI", "SI", "PGSD", "PG-PAUD", "FISIKA", "MATEMATIKA" }));

        jLabel13.setText("Program Studi");

        jLabel12.setText("Tgl Lahir");

        jLabel11.setText("Tempat Lahir");

        jLabel10.setText("Alamat");

        jLabel9.setText("Nama Mahasiswa");

        javax.swing.GroupLayout mahasiswaLayout = new javax.swing.GroupLayout(mahasiswa);
        mahasiswa.setLayout(mahasiswaLayout);
        mahasiswaLayout.setHorizontalGroup(
            mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mahasiswaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mahasiswaLayout.createSequentialGroup()
                        .addGroup(mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addGap(56, 56, 56))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mahasiswaLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(98, 98, 98)))
                .addGroup(mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mahasiswaLayout.createSequentialGroup()
                        .addGroup(mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tempat)
                            .addComponent(alamat)
                            .addComponent(nama)
                            .addComponent(npm)
                            .addComponent(program_studi, 0, 203, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(465, 465, 465))
                    .addGroup(mahasiswaLayout.createSequentialGroup()
                        .addComponent(tgl_lahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(mahasiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mahasiswaLayout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mahasiswaLayout.setVerticalGroup(
            mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mahasiswaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addGroup(mahasiswaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(npm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(tempat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(tgl_lahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(program_studi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mahasiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap(226, Short.MAX_VALUE))
        );

        menuCard.add(mahasiswa, "pnCard1");

        dosen.setBackground(new java.awt.Color(51, 204, 255));
        dosen.setPreferredSize(new java.awt.Dimension(650, 550));

        jButton4.setText("Tambah");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("ID Dosen");

        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });

        jLabel3.setText("Nama");

        namados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namadosActionPerformed(evt);
            }
        });

        jLabel4.setText("Alamat");

        alamatdos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alamatdosActionPerformed(evt);
            }
        });

        jLabel5.setText("JK");

        jLabel6.setText("No Telp");

        telp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telpActionPerformed(evt);
            }
        });

        tabeldos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabeldos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeldosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabeldos);

        jButton8.setText("Ubah");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("bersih");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Hapus");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        cekdos.add(l);
        l.setText("L");

        cekdos.add(p);
        p.setText("P");

        javax.swing.GroupLayout dosenLayout = new javax.swing.GroupLayout(dosen);
        dosen.setLayout(dosenLayout);
        dosenLayout.setHorizontalGroup(
            dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dosenLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(467, 467, 467))
            .addGroup(dosenLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dosenLayout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9))
                    .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dosenLayout.createSequentialGroup()
                            .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel3)
                                .addComponent(jLabel1))
                            .addGap(59, 59, 59)
                            .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(namados, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(alamatdos, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(dosenLayout.createSequentialGroup()
                                    .addComponent(l)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(p))
                                .addComponent(telp, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(478, Short.MAX_VALUE))
        );
        dosenLayout.setVerticalGroup(
            dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dosenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addGap(29, 29, 29)
                .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(namados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(alamatdos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(l)
                    .addComponent(p))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(telp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dosenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton10)
                    .addComponent(jButton9))
                .addContainerGap(298, Short.MAX_VALUE))
        );

        menuCard.add(dosen, "pnCard2");

        krs.setBackground(new java.awt.Color(204, 204, 0));

        jLabel7.setText("KARTU RENCANA STUDI");

        jLabel14.setText("NIM                 :");

        jLabel15.setText("Nama              :");

        jLabel16.setText("Prodi               :");

        jLabel17.setText("Tahun Akadamik    :");

        nim.setText("jLabel18");

        namakrs.setText("jLabel19");

        prodikrs.setText("jLabel20");

        thnkrs.setText("jLabel21");

        krstabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        krstabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                krstabelMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(krstabel);

        jButton12.setText("Tambah");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout krsLayout = new javax.swing.GroupLayout(krs);
        krs.setLayout(krsLayout);
        krsLayout.setHorizontalGroup(
            krsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(krsLayout.createSequentialGroup()
                .addGroup(krsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(krsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(krsLayout.createSequentialGroup()
                            .addGap(300, 300, 300)
                            .addComponent(jLabel7))
                        .addGroup(krsLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jSeparator2))
                        .addGroup(krsLayout.createSequentialGroup()
                            .addGap(234, 234, 234)
                            .addGroup(krsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(krsLayout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(namakrs))
                                .addGroup(krsLayout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(nim))
                                .addGroup(krsLayout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(prodikrs))))
                        .addGroup(krsLayout.createSequentialGroup()
                            .addGap(214, 214, 214)
                            .addComponent(jLabel17)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(thnkrs))
                        .addGroup(krsLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)))
                    .addGroup(krsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton12)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        krsLayout.setVerticalGroup(
            krsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(krsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(krsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(nim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(krsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(namakrs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(krsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(prodikrs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(krsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(thnkrs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton12)
                .addContainerGap(367, Short.MAX_VALUE))
        );

        menuCard.add(krs, "pnCard3");

        khsform.setBackground(new java.awt.Color(0, 153, 102));

        jLabel25.setText("KARTU HASIL STUDI");

        jLabel26.setText("NIM                 :");

        jLabel27.setText("Nama              :");

        jLabel28.setText("Prodi               :");

        jLabel29.setText("Tahun Akadamik    :");

        nim1.setText("jLabel18");

        namakrs1.setText("jLabel19");

        prodikrs1.setText("jLabel20");

        thnkrs1.setText("jLabel21");

        krstabel1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        krstabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                krstabel1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(krstabel1);

        javax.swing.GroupLayout khsformLayout = new javax.swing.GroupLayout(khsform);
        khsform.setLayout(khsformLayout);
        khsformLayout.setHorizontalGroup(
            khsformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khsformLayout.createSequentialGroup()
                .addGroup(khsformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(khsformLayout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(jLabel25))
                    .addGroup(khsformLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(khsformLayout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addGroup(khsformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(khsformLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(khsformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(khsformLayout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(namakrs1))
                                    .addGroup(khsformLayout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nim1))
                                    .addGroup(khsformLayout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(prodikrs1))))
                            .addGroup(khsformLayout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(thnkrs1))))
                    .addGroup(khsformLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        khsformLayout.setVerticalGroup(
            khsformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khsformLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(khsformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(nim1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(khsformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(namakrs1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(khsformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(prodikrs1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(khsformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(thnkrs1))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(285, Short.MAX_VALUE))
        );

        menuCard.add(khsform, "pnCard4");

        input.setBackground(new java.awt.Color(0, 102, 102));

        jLabel32.setText("jLabel32");

        jLabel33.setText("Kode Matkul");

        jLabel34.setText("Matkul");

        jLabel35.setText("SKS");

        jLabel36.setText("Tahun akademik");

        kodemat.setText("jLabel37");

        namamat.setText("jLabel38");

        sksmat.setText("jLabel39");

        thnmat.setText("jLabel40");

        inputTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        inputTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(inputTable);

        javax.swing.GroupLayout inputLayout = new javax.swing.GroupLayout(input);
        input.setLayout(inputLayout);
        inputLayout.setHorizontalGroup(
            inputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputLayout.createSequentialGroup()
                .addGroup(inputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(inputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(inputLayout.createSequentialGroup()
                            .addGap(329, 329, 329)
                            .addComponent(jLabel32))
                        .addGroup(inputLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(inputLayout.createSequentialGroup()
                            .addGap(231, 231, 231)
                            .addGroup(inputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel33)
                                .addComponent(jLabel34)
                                .addComponent(jLabel35)
                                .addComponent(jLabel36))
                            .addGap(82, 82, 82)
                            .addGroup(inputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(thnmat)
                                .addComponent(sksmat)
                                .addComponent(namamat)
                                .addComponent(kodemat)))))
                .addContainerGap(485, Short.MAX_VALUE))
        );
        inputLayout.setVerticalGroup(
            inputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(inputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(kodemat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(inputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(namamat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(inputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(sksmat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(inputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(thnmat))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(357, Short.MAX_VALUE))
        );

        menuCard.add(input, "pnCard5");

        split.setRightComponent(menuCard);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(split, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(split, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(923, 578));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cardlayout.show(menuCard, "pnCard2");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cardlayout.show(menuCard, "pnCard1");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        java.sql.Date sqlku = new java.sql.Date(tgl_lahir.getDate().getTime());

        if (npm.getText().isEmpty() || nama.getText().isEmpty() || alamat.getText().isEmpty() || tempat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Isikan terlebih dahulu");
        } else {
            sql = "insert into tbl_mahasiswa values ('" + npm.getText() + "','" + nama.getText() + "','" + alamat.getText() + "','" + tempat.getText() + "','" + sqlku + "','" + program_studi.getSelectedItem() + "')";
            try {
                st.executeUpdate(sql);
                tampildata();
                bersih();
                JOptionPane.showMessageDialog(null, "berhasil");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "salah");
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        java.sql.Date sqlku1 = new java.sql.Date(tgl_lahir.getDate().getTime());
        if (npm.getText().isEmpty() || nama.getText().isEmpty() || alamat.getText().isEmpty() || tempat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Isikan terlebih dahulu");
        } else {
            sql = "update tbl_mahasiswa set nama='" + nama.getText() + "',alamat='" + alamat.getText() + "',tempat_lahir='" + tempat.getText() + "',tgl_lahir='" + sqlku1 + "',program_studi='" + program_studi.getSelectedItem() + "' where npm='" + npm.getText() + "'";
            try {
                st.executeUpdate(sql);
                tampildata();
                bersih();
                JOptionPane.showMessageDialog(null, "berhasil");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tabelnyaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelnyaMouseClicked
        // TODO add your handling code here:

        npm.setText(tabelnya.getValueAt(tabelnya.getSelectedRow(), 0).toString());
        nama.setText(tabelnya.getValueAt(tabelnya.getSelectedRow(), 1).toString());
        alamat.setText(tabelnya.getValueAt(tabelnya.getSelectedRow(), 2).toString());
        tempat.setText(tabelnya.getValueAt(tabelnya.getSelectedRow(), 3).toString());
        //tgl_lahir.setDate((java.util.Date) tabelnya.getValueAt(tabelnya.getSelectedRow(), 4));
        program_studi.setSelectedItem(tabelnya.getValueAt(tabelnya.getSelectedRow(), 5).toString());

    }//GEN-LAST:event_tabelnyaMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        sql = "delete from tbl_mahasiswa where npm=" + npm.getText();
        try {
            st.executeUpdate(sql);
            tampildata();
            bersih();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        bersih();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

    private void namadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namadosActionPerformed

    private void alamatdosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alamatdosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamatdosActionPerformed

    private void telpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telpActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        String a;
        if (l.isSelected()) {
            a = "L";
        } else {
            a = "P";
        }
        sql = "update tbl_dosen set nama='" + namados.getText() + "',alamat='" + alamatdos.getText() + "',jk='" + a + "',no_telp='" + telp.getText() + "'where id_dosen='" + id.getText() + "'";
        try {
            st.executeUpdate(sql);
            tampildatados();
            bersihdos();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        bersihdos();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        sql = "delete from tbl_dosen where id_dosen=" + id.getText();
        try {
            st.executeUpdate(sql);
            tampildatados();
            bersihdos();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String a;
        if (l.isSelected()) {
            a = "L";
        } else {
            a = "P";
        }
        sql = "insert into tbl_dosen values ('" + id.getText() + "','" + namados.getText() + "','" + alamatdos.getText() + "','" + a + "','" + telp.getText() + "')";
        try {
            st.executeUpdate(sql);
            tampildatados();
            bersihdos();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tabeldosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeldosMouseClicked
        // TODO add your handling code here:

        id.setText(tabeldos.getValueAt(tabeldos.getSelectedRow(), 0).toString());
        namados.setText(tabeldos.getValueAt(tabeldos.getSelectedRow(), 1).toString());
        alamatdos.setText(tabeldos.getValueAt(tabeldos.getSelectedRow(), 2).toString());

        telp.setText(tabeldos.getValueAt(tabeldos.getSelectedRow(), 4).toString());

    }//GEN-LAST:event_tabeldosMouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        krspunya.setBounds(300, 250, 500, 300);
        krspunya.setVisible(true);


    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        sql = "SELECT tbl_mahasiswa.npm,tbl_mahasiswa.nama,tbl_mahasiswa.program_studi,thn_akad.thn_akada,krs.nilai FROM krs JOIN tbl_mahasiswa ON krs.npm=tbl_mahasiswa.npm JOIN thn_akad ON krs.id_thn_akad=thn_akad.id_thn_akad WHERE tbl_mahasiswa.npm=" + nimdialog1.getText();
        try {
            rs = st.executeQuery(sql);

            if (rs.next()) {
                nim.setText(rs.getString("npm"));
                namakrs.setText(rs.getString("nama"));
                prodikrs.setText(rs.getString("program_studi"));
                thnkrs.setText(rs.getString("thn_akada"));                                
                nimtbh.setText(rs.getString("npm"));
                thntambah.setText(rs.getString("thn_akada"));
                if (rs.getString("npm").equals(nimdialog.getText())) {
                    cardlayout.show(menuCard, "pnCard3");
                    tampildatakrs(nimdialog.getText());
                    krspunya.dispose();

                }
            } else {

                JOptionPane.showMessageDialog(rootPane, "nim tidak cocok");
            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        tambahkrs.setBounds(300, 250, 500, 300);
        tambahkrs.setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void matkultbhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matkultbhActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_matkultbhActionPerformed

    private void thntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thntambahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_thntambahActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:

        String sqlku = "SELECT tbl_mahasiswa.npm,thn_akad.id_thn_akad FROM krs JOIN tbl_mahasiswa ON krs.npm=tbl_mahasiswa.npm JOIN thn_akad ON krs.id_thn_akad=thn_akad.id_thn_akad WHERE tbl_mahasiswa.npm=" + nimtbh.getText();

        try {
            JOptionPane.showMessageDialog(rootPane, "masuk");
            rs = st.executeQuery(sqlku);
            while (rs.next()) {
                sql = "insert into krs (id_thn_akad,npm,kode_matkul) values ('" + rs.getString("id_thn_akad") + "','" + nimtbh.getText() + "','" + matkultbh.getSelectedIndex() + "')";
            }
            st.executeUpdate(sql);
            tampildatakrs(nimtbh.getText());
            JOptionPane.showMessageDialog(rootPane, "berhasil");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }

    }//GEN-LAST:event_jButton16ActionPerformed

    private void krstabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_krstabelMouseClicked
        // TODO add your handling code here:
        ubhkrs.setBounds(500, 250, 500, 500);
        ubhkrs.setVisible(true);
        idubh.setText(krstabel.getValueAt(krstabel.getSelectedRow(), 0).toString());
        nimubh.setText(nim.getText());
    }//GEN-LAST:event_krstabelMouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        sql = "update krs set kode_matkul='" + ubhkrscombo.getSelectedIndex() + "' where id_krs=" + idubh.getText();
        try {
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(rootPane, "berhasil di ubah");
            tampildatakrs(nimubh.getText());
            ubhkrs.setVisible(false);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void ubhkrscomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubhkrscomboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubhkrscomboActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        sql="delete from krs where id_krs="+idubh.getText();
        try {
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(rootPane,"hapus berhasil");
            tampildatakrs(nimubh.getText());
            ubhkrs.setVisible(false);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void krstabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_krstabel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_krstabel1MouseClicked

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
         sql = "SELECT tbl_mahasiswa.npm,tbl_mahasiswa.nama,tbl_mahasiswa.program_studi,thn_akad.thn_akada FROM krs JOIN tbl_mahasiswa ON krs.npm=tbl_mahasiswa.npm JOIN thn_akad ON krs.id_thn_akad=thn_akad.id_thn_akad WHERE tbl_mahasiswa.npm=" + nimdialog1.getText();
        try {
            rs = st.executeQuery(sql);

            if (rs.next()) {
                nim1.setText(rs.getString("npm"));
                namakrs1.setText(rs.getString("nama"));
                prodikrs1.setText(rs.getString("program_studi"));
                thnkrs1.setText(rs.getString("thn_akada"));
                
                if (rs.getString("npm").equals(nimdialog1.getText())) {
                    cardlayout.show(menuCard, "pnCard4");
                    tampildatakhs(nimdialog1.getText());
                    khs.dispose();

                }
            } else {

                JOptionPane.showMessageDialog(rootPane, "nim tidak cocok");
            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        khs.setBounds(300, 250, 500, 300);
        khs.setVisible(true);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void inputthnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputthnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputthnActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
         sql = "select krs.id_krs,matakuliah.kode_matkul,tbl_mahasiswa.npm,tbl_mahasiswa.nama,tbl_mahasiswa.program_studi,thn_akad.thn_akada,krs.nilai from krs inner join matakuliah on krs.kode_matkul=matakuliah.kode_matkul INNER JOIN tbl_mahasiswa ON krs.npm=tbl_mahasiswa.npm INNER JOIN thn_akad ON krs.id_thn_akad=thn_akad.id_thn_akad WHERE matakuliah.kode_matkul=" + inputkode.getText();
        try {
            rs = st.executeQuery(sql);

            if (rs.next()) {
                kodemat.setText(rs.getString("kode_matkul"));
                namamat.setText(rs.getString("nama"));
                sksmat.setText(rs.getString("program_studi"));
                thnmat.setText(rs.getString("thn_akada"));
                
                if (rs.getString("kode_matkul").equals(inputkode.getText())) {
                    cardlayout.show(menuCard, "pnCard5");
                    tampildatainput(inputkode.getText());
                    inputnilai.dispose();

                }
            } else {

                JOptionPane.showMessageDialog(rootPane, "nim tidak cocok");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        inputnilai.setBounds(300, 250, 500, 300);
        inputnilai.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void inputTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputTableMouseClicked
        // TODO add your handling code here:
        tbhnil.setBounds(300, 250, 500, 300);
        tbhnil.setVisible(true);
        kode.setText(inputTable.getValueAt(inputTable.getSelectedRow(), 0).toString());
        komat.setText(kodemat.getText());
    }//GEN-LAST:event_inputTableMouseClicked

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        sql = "update krs set nilai='"+nilai.getText()+"' where id_krs="+kode.getText();
        try {
            st.executeUpdate(sql);
            tampildatainput(komat.getText());
            tbhnil.dispose();
            JOptionPane.showMessageDialog(rootPane, "berhasil");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menuAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamat;
    private javax.swing.JTextField alamatdos;
    private javax.swing.ButtonGroup cekdos;
    private javax.swing.JPanel dosen;
    private javax.swing.JTextField id;
    private javax.swing.JLabel idubh;
    private javax.swing.JPanel input;
    private javax.swing.JTable inputTable;
    private javax.swing.JTextField inputkode;
    private javax.swing.JDialog inputnilai;
    private javax.swing.JComboBox<String> inputthn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JDialog khs;
    private javax.swing.JPanel khsform;
    private javax.swing.JLabel kode;
    private javax.swing.JLabel kodemat;
    private javax.swing.JLabel komat;
    private javax.swing.JPanel krs;
    private javax.swing.JDialog krspunya;
    private javax.swing.JTable krstabel;
    private javax.swing.JTable krstabel1;
    private javax.swing.JRadioButton l;
    private javax.swing.JPanel mahasiswa;
    private javax.swing.JComboBox<String> matkultbh;
    private javax.swing.JPanel menuCard;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField namados;
    private javax.swing.JLabel namakrs;
    private javax.swing.JLabel namakrs1;
    private javax.swing.JLabel namamat;
    private javax.swing.JTextField nilai;
    private javax.swing.JLabel nim;
    private javax.swing.JLabel nim1;
    private javax.swing.JTextField nimdialog;
    private javax.swing.JTextField nimdialog1;
    private javax.swing.JTextField nimtbh;
    private javax.swing.JTextField nimubh;
    private javax.swing.JTextField npm;
    private javax.swing.JRadioButton p;
    private javax.swing.JLabel prodikrs;
    private javax.swing.JLabel prodikrs1;
    private javax.swing.JComboBox<String> program_studi;
    private javax.swing.JLabel sksmat;
    private javax.swing.JSplitPane split;
    private javax.swing.JTable tabeldos;
    private javax.swing.JTable tabelnya;
    private javax.swing.JDialog tambahkrs;
    private javax.swing.JDialog tbhnil;
    private javax.swing.JTextField telp;
    private javax.swing.JTextField tempat;
    private com.toedter.calendar.JDateChooser tgl_lahir;
    private javax.swing.JComboBox<String> thndialog;
    private javax.swing.JComboBox<String> thndialog1;
    private javax.swing.JLabel thnkrs;
    private javax.swing.JLabel thnkrs1;
    private javax.swing.JLabel thnmat;
    private javax.swing.JTextField thntambah;
    private javax.swing.JDialog ubhkrs;
    private javax.swing.JComboBox<String> ubhkrscombo;
    // End of variables declaration//GEN-END:variables
}
